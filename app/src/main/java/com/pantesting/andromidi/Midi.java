package com.pantesting.andromidi;
import java.util.ArrayList;

import android.content.Context;
import android.media.midi.*;
import android.os.Build;

import java.io.IOException;
import java.util.Set;

import static android.content.Context.MIDI_SERVICE;

public class Midi {
    protected Context mContext;
    protected MidiDevice midiDevice;
    protected MidiInputPort inputPort;
    protected MidiOutputPort outputPort;

    public Midi(Context a_context){
        this.mContext = a_context;
    }

    public Set<MidiDeviceInfo> getMidiDevices(){
        MidiManager midiManager = (MidiManager) this.mContext.getSystemService(MIDI_SERVICE);
        Set<MidiDeviceInfo> infos = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            infos = midiManager.getDevicesForTransport(MidiManager.TRANSPORT_MIDI_BYTE_STREAM);
        }
        return infos;
    }

    // Connexion au dispositif MIDI (à appeler avec le bon MidiDeviceInfo)
    public void connectToMidiDevice(MidiDeviceInfo info) {
        MidiManager midiManager = (MidiManager) this.mContext.getSystemService(MIDI_SERVICE);
        midiManager.openDevice(info, device -> {
            midiDevice = device;
            inputPort = midiDevice.openInputPort(0);
            outputPort = midiDevice.openOutputPort(1);
        }, null);
    }

    // Envoi d'un message Control Change (par exemple, CC #7 (volume) à 100 sur canal 1)
    public void sendControlChange(int channel, int controller, int value) {
        System.out.println("channel:" + channel + " / controller: " + controller + " / value:" + value);
        if (this.inputPort != null) {
            byte[] msg = new byte[]{
                    (byte) (0xB0 | (channel & 0x0F)),
                    (byte) (controller & 0x7F),
                    (byte) (value & 0x7F)
            };
            try {
                // Ancienne méthode
                this.inputPort.send(msg, 0, msg.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int receiveControlChange(byte[] msg) {
        int channel = msg[0] & 0x0F; // 4 bits de droite
        int controller = msg[1] & 0x7F; // 7 bits de droite
        int value = msg[2] & 0x7F; // 7 bits de droite

        String message = "Channel: " + channel + " / Controller: " + controller + " / Value: " + value;
        return value;
    }

    // Envoi d'un message SysEx (exemple : F0 7D 10 01 00 F7)
    public void sendSysEx(byte[] device, byte[] data) {
        if (inputPort != null) {
            try {
                ArrayList<Byte> arrayList = new ArrayList<>();
                arrayList.add((byte) 0xf0);
                for (byte b : device) {
                    arrayList.add(b);
                }
                for (byte b : data) {
                    arrayList.add(b);
                }
                arrayList.add((byte) 0xf7);
                // Convertir l'ArrayList en tableau
                byte[] newArray = new byte[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    newArray[i] = arrayList.get(i);
                }
                inputPort.send(newArray, 0, newArray.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
