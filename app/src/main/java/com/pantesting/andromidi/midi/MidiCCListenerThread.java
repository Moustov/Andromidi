package com.pantesting.andromidi.midi;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiInputPort;
import android.media.midi.MidiOutputPort;
import android.media.midi.MidiReceiver;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import android.os.Handler;
import android.os.Looper;

public class MidiCCListenerThread extends Thread {
    private final Context context;
    private final MidiDeviceInfo deviceInfo;
    private MidiDevice midiDevice;
    private MidiOutputPort outputPort;
    protected TextView usb_out_message_txvw;
    protected TextView usb_in_message_txvw;
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    public MidiCCListenerThread(Context context, MidiDeviceInfo deviceInfo, TextView usb_in, TextView usb_out) {
        this.context = context;
        this.deviceInfo = deviceInfo;
        this.usb_in_message_txvw = usb_in;
        this.usb_out_message_txvw = usb_out;
    }

    public void setSongTitleTextView(TextView songTitleTextView) {
        this.usb_in_message_txvw = songTitleTextView;
    }

    @Override
    public void run() {
        MidiManager midiManager = (MidiManager) context.getSystemService(Context.MIDI_SERVICE);

        midiManager.openDevice(deviceInfo, device -> {
            if (device == null) {
                Log.e("MIDI", "Impossible d'ouvrir le device");
                return;
            }
            Log.e("MIDI", "Device à l'écoute : " + device.toString());
            midiDevice = device;
            outputPort = midiDevice.openOutputPort(0);
            MidiOutputPort outputPortToRead = midiDevice.openOutputPort(0);
            if (outputPortToRead != null) {
                outputPortToRead.connect(new MidiReceiver() {
                    @Override
                    public void onSend(byte[] data, int offset, int count, long timestamp) {
                        int idx = offset;
                        int end = offset + count;
                        
                        while (idx < end) {
                            int status = data[idx] & 0xFF;

                            if (status == 0xF0) { // Début SysEx
                                StringBuilder sb = new StringBuilder("SysEx: ");
                                while (idx < end && data[idx] != (byte)0xF7) {
                                    sb.append(String.format("%02X ", data[idx]));
                                    idx++;
                                }
                                if (idx < end) sb.append(String.format("%02X ", data[idx])); // Append F7
                                
                                String fullMsg = sb.toString();
                                Log.i("MIDI", fullMsg);
                                
                                if (usb_in_message_txvw != null) {
                                    uiHandler.post(() -> usb_in_message_txvw.setText(fullMsg));
                                }
                                idx++; // Move past F7
                            } else if ((status & 0xF0) == 0xB0 && idx + 2 < end) { // Control Change
                                int controller = data[idx + 1] & 0x7F;
                                int value = data[idx + 2] & 0x7F;
                                
                                String ccMsg = "CC: " + controller + " Val: " + value;
                                Log.i("MIDI", ccMsg);
                                
                                if (usb_in_message_txvw != null) {
                                    uiHandler.post(() -> usb_in_message_txvw.setText(ccMsg));
                                }
                                idx += 3;
                            } else {
                                idx++;
                            }
                        }
                    }
                });
            }
        }, null);

        try {
            while (!isInterrupted()) {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Thread interrupted
        } finally {
            try {
                if (outputPort != null) outputPort.close();
                if (midiDevice != null) midiDevice.close();
            } catch (Exception e) {
                // Ignore
            }
        }
    }
}