package com.pantesting.andromidi.midi;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.midi.*;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Set;

import static android.content.Context.MIDI_SERVICE;

public class Midi {
    protected static Context mContext;
    protected static MidiDevice midiDevice;
    protected static MidiInputPort inputPort;
    protected static MidiOutputPort outputPort;

    protected static View my_view;

    public static void set_context(Context a_context, View a_view)
    {
        Midi.mContext = a_context;
        Midi.my_view = a_view;
    }

    public static Set<MidiDeviceInfo> getMidiDevices(){
        MidiManager midiManager = (MidiManager) Midi.mContext.getSystemService(MIDI_SERVICE);
        Set<MidiDeviceInfo> infos = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            infos = midiManager.getDevicesForTransport(MidiManager.TRANSPORT_MIDI_BYTE_STREAM);
        }
        return infos;
    }

    // Connexion au dispositif MIDI (à appeler avec le bon MidiDeviceInfo)
    public static void connectToMidiDevice(MidiDeviceInfo info) {
        MidiManager midiManager = (MidiManager) Midi.mContext.getSystemService(MIDI_SERVICE);
        midiManager.openDevice(info, device -> {
            midiDevice = device;
            inputPort = midiDevice.openInputPort(0);
            outputPort = midiDevice.openOutputPort(1);
        }, null);
    }

    // Envoi d'un message Control Change (par exemple, CC #7 (volume) à 100 sur canal 1)
    public static void sendControlChange(int channel, int controller, int value) {
        System.out.println("channel:" + channel + " / controller: " + controller + " / value:" + value);
        if (Midi.inputPort != null) {
            byte[] msg = new byte[]{
                    (byte) (0xB0 | (channel & 0x0F)),
                    (byte) (controller & 0x7F),
                    (byte) (value & 0x7F)
            };
            try {
                if (Midi.my_view != null) {
                    Snackbar.make(Midi.my_view, "CC out:  " + msg.toString(), Snackbar.LENGTH_SHORT).show();
                }
                Midi.inputPort.send(msg, 0, msg.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int receiveControlChange(byte[] msg) {
        int channel = msg[0] & 0x0F; // 4 rightmost bits
        int controller = msg[1] & 0x7F; // 7 rightmost bits
        int value = msg[2] & 0x7F; // 7 rightmost bits

        String message = "Channel: " + channel + " / Controller: " + controller + " / Value: " + value;
        return value;
    }

    // Envoi d'un message SysEx (exemple : F0 7D 10 01 00 F7)
    public static void sendSysEx(byte[] device, byte[] data) {
        if (inputPort != null) {
            try {
                ArrayList<Byte> arrayList = new ArrayList<>();
                arrayList.add((byte) 0xf0);
                for (byte b : device) {
                    arrayList.add(b);
                }
                for (byte b : data) {
                    if (((b & 0x80) != 0)) throw new AssertionError("A data will be sent over SySex with a value greater than 0x7F");        // if the MSB is ON
                    arrayList.add((byte)(b & 0x7F));    // 7 rightmost bits
                }
                arrayList.add((byte) 0xf7);
                // Convertir l'ArrayList en tableau
                byte[] newArray = new byte[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    newArray[i] = arrayList.get(i);
                }
                if (Midi.my_view != null) {
                    Snackbar.make(Midi.my_view, "SX out:  " + newArray.toString(), Snackbar.LENGTH_SHORT).show();
                }
                inputPort.send(newArray, 0, newArray.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendURB_BULK_in(byte[] data) {
        if (Midi.inputPort != null) {
            try {
                if (Midi.my_view != null) {
                    Snackbar.make(Midi.my_view, "URB out:  " + data.toString(), Snackbar.LENGTH_SHORT).show();
                }
                Midi.inputPort.send(data, 0, data.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
