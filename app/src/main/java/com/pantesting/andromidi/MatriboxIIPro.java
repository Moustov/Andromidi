package com.pantesting.andromidi;

import android.content.Context;
import android.media.midi.MidiDeviceInfo;
import java.util.Set;

/**
 * see https://www.sonicake.com/pages/matribox-ii-pro-software-firmware-1
 */
public class MatriboxIIPro extends Midi{

    public String manufacturer;
    public String product;
    public MatriboxIIPro(Context a_context) {
        super(a_context);
    }

    public void connectToMatribox() {
        // Récupération des devices (API 33+)
        Set<MidiDeviceInfo> device_list = null;
        device_list = this.getMidiDevices();
        if (device_list == null || device_list.isEmpty()) {
            // Aucun device trouvé
            return;
        }

        for (MidiDeviceInfo info : device_list) {
            String manufacturer = "";
            String product = "";

            // Récupération des propriétés du device
            if (info.getProperties() != null) {
                manufacturer = info.getProperties().getString(MidiDeviceInfo.PROPERTY_MANUFACTURER, "");
                product = info.getProperties().getString(MidiDeviceInfo.PROPERTY_PRODUCT, "");
            }

            String name = manufacturer + " " + product;
            // Vérifie si le nom du device contient "Matribox"
//            if (name.toLowerCase().contains("MIDI")) {
                this.manufacturer = manufacturer;
                this.product = product;
                this.connectToMidiDevice(info); // Ta méthode existante
//                break; // On s'arrête après la première correspondance
//            }
        }
    }

    public void sendActivateLooperMenu(){
        this.sendControlChange(1, 59, 0);
    }

    public void sendDeactivateLooperMenu(){
        this.sendControlChange(1, 59, 127);
    }

    public void sendActivateDrumMenu(){
        this.sendControlChange(1, 92, 127);
    }

    public void sendDeactivateDrumMenu(){
        this.sendControlChange(1, 92, 0);
    }

    public void sendLooperPlay(){
        this.sendControlChange(1, 62, 127);
    }

    public void sendLooperPlay_Off(){
        this.sendControlChange(1, 62, 0);
    }

    /**
     * Updates the knob value
     * @param knob : 1..3
     * @param value : 0..100
     */
    public void sendKnobValue(int knob, int value){
        this.sendControlChange(1, 16 + (knob -1) * 2, value);
    }
}
