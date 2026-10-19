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
                                if (count > 7) {
                                    // Extraction : On ignore le header (7 octets) et on décode le reste par nibbles
                                    // La doc dit: Header(7) + TaskID(1) + Flag(1) + v1(2) + v2(2) + CmdCode(4) + dataLen(4) + Data
                                    // Dans les logs, nous avons F0 21 25 4D 50 00 00 [1A/1B] [00] [14 00 00 00] [00 00 00 0C]
                                    // La donnée utile commence probablement après ces octets
                                    int payloadStart = 7;
                                    byte[] rawPayload = new byte[count - payloadStart - 1]; // -1 pour F7
                                    System.arraycopy(data, offset + payloadStart, rawPayload, 0, rawPayload.length);
                                    
                                    // Recombinaison des demi-octets (NIBBLES)
                                    byte[] decoded = new byte[rawPayload.length / 2];
                                    for (int i = 0; i < decoded.length; i++) {
                                        decoded[i] = (byte) ((rawPayload[i * 2] << 4) | (rawPayload[i * 2 + 1] & 0x0F));
                                    }
                                    
                                    // Debug: Afficher tout le payload brut pour analyse
                                    StringBuilder sb = new StringBuilder("RawDecoded: ");
                                    for (byte b : decoded) {
                                        sb.append(String.format("%02X ", b));
                                    }
                                    Log.i("MIDI_DEBUG_RAW", sb.toString());
                                    
                                    // Debug: Log raw decoded bytes as hex and ascii
                                    StringBuilder hexSb = new StringBuilder();
                                    StringBuilder asciiSb = new StringBuilder();
                                    for (byte b : decoded) {
                                        hexSb.append(String.format("%02X ", b));
                                        char c = (char) b;
                                        asciiSb.append((c >= 32 && c <= 126) ? c : '.');
                                    }
                                    Log.i("MIDI_RAW_HEX", hexSb.toString());
                                    Log.i("MIDI_RAW_ASCII", asciiSb.toString());

                                    // Extraction exacte basée sur le code officiel (HTModelPresetControl.dart)
                                    int absoluteIndex = (decoded.length > 16) ? (decoded[16] & 0xFF) : 0;
                                    int tens = (absoluteIndex / 4) + 1;
                                    char presetLetter = (char) ('A' + (absoluteIndex % 4));
                                    
                                    String presetName = "";
                                    if (decoded.length >= 33) {
                                        byte[] nameBytes = new byte[16];
                                        System.arraycopy(decoded, 17, nameBytes, 0, 16);
                                        presetName = new String(nameBytes).trim();
                                    }

                                    String displayMsg = String.format("%02d-%s %s", tens, presetLetter, presetName).trim();
                                    Log.i("MIDI_DECODED", displayMsg);

                                    
                                    if (usb_in_message_txvw != null) {
                                        uiHandler.post(() -> usb_in_message_txvw.setText(displayMsg));
                                    }
                                }
                                idx = end;
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