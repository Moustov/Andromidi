package com.pantesting.andromidi.midi;

import android.content.Context;
import android.util.Base64;
import com.pantesting.andromidi.FileManager;

import java.util.List;

public class PrstToSysEx {

    public static List<byte[]> generate_sysex_from_prst_effect_name(String prst_file, Context my_context) throws Exception {
        // 1. Lire le contenu du fichier texte (Base64)
//        InputStream is = my_context.getAssets().open(prst_file);
//        byte[] buffer = new byte[is.available()];
//        is.read(buffer);
//        is.close();
        byte[] buffer = FileManager.readFileFromAsset(prst_file, my_context);
        List<byte[]> sysexMessages = generate_sysex_from_prst_bytes(buffer);
        return sysexMessages;
    }

    public static List<byte[]> generate_sysex_from_prst_bytes(byte[] prst_buffer) throws Exception {
        String base64_string = new String(prst_buffer).trim();

        byte[] preset = Base64.decode(base64_string, Base64.DEFAULT);

        // 3. Générer les messages SysEx à partir du preset
        int maxDataBytes = 512; // Adapter si besoin
        List<byte[]> sysexMessages = MidiSysexEncoder.buildSysExMessages(preset, maxDataBytes);

        // 4. Afficher les messages SysEx en hexadécimal
//        int msgNum = 1;
//        for (byte[] msg : sysexMessages) {
//            System.out.printf("Message SysEx %d (%d octets) :\n", msgNum++, msg.length);
//            for (int i = 0; i < msg.length; i++) {
//                System.out.printf("%02X ", msg[i]);
//                if ((i + 1) % 16 == 0) System.out.println();
//            }
//            System.out.println("\n---");
//        }
        return sysexMessages;
    }
}