package com.pantesting.andromidi.midi;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiOutputPort;
import android.media.midi.MidiReceiver;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class MidiCCListenerThread extends Thread {
    private final Context context;
    private final MidiDeviceInfo deviceInfo;
    private MidiDevice midiDevice;
    private MidiOutputPort outputPort;
    protected TextView usb_out_message_txvw;
    protected TextView usb_in_message_txvw;

    public MidiCCListenerThread(Context context, MidiDeviceInfo deviceInfo, TextView usb_in, TextView usb_out) {
        this.context = context;
        this.deviceInfo = deviceInfo;
        this.usb_in_message_txvw = usb_in;
        this.usb_out_message_txvw = usb_out;
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
            outputPort = midiDevice.openOutputPort(1);
            if (outputPort == null) {
                Log.e("MIDI", "Impossible d'ouvrir le port de sortie");
                return;
            }

            try {
                outputPort.connect(new MidiReceiver() {
                    @Override
                    public void onSend(byte[] data, int offset, int count, long timestamp) {
                        if (usb_in_message_txvw != null){
                            usb_in_message_txvw.setText(data.toString());
                        }

                        int idx = offset;
                        int end = offset + count;
                        while (idx < end) {
                            int status = data[idx] & 0xFF;
                            if ((status & 0xF0) == 0xB0 && idx + 2 < end) { // Control Change
                                int channel = status & 0x0F;
                                int controller = data[idx + 1] & 0x7F;
                                int value = data[idx + 2] & 0x7F;
                                Log.i("MIDI", "CC #" + controller + " value " + value + " sur canal " + (channel + 1));
                                idx += 3;
                            } else if ((status & 0x80) == 0x80) {
                                idx += 3;
                            } else {
                                idx++;
                            }
                        }
                    }
                });
            } catch (Exception e) {
                Log.e("MIDI", "Erreur connexion receiver", e);
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