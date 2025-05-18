package com.pantesting.andromidi.midi;

import java.util.ArrayList;
import java.util.List;

public class MidiSysexEncoder {

    // Identifiant fabricant : [0x21, 0x25, 0x4D, 0x50]
    private static final byte[] MANUFACTURER_ID = { 0x21, 0x25, 0x4D, 0x50 };

    /**
     * Encode un tableau d'octets (preset) en format MIDI 7 bits
     */
    public static byte[] encode7Bit(byte[] data) {
        List<Byte> out = new ArrayList<>();
        int i = 0;
        while (i < data.length) {
            int header = 0;
            byte[] seven = new byte[7];
            int count = Math.min(7, data.length - i);
            for (int j = 0; j < count; j++) {
                int b = data[i + j] & 0xFF;
                if ((b & 0x80) != 0) {
                    header |= (1 << j);
                }
                seven[j] = (byte) (b & 0x7F);
            }
            out.add((byte) header);
            for (int j = 0; j < count; j++) {
                out.add(seven[j]);
            }
            i += 7;
        }
        // Convert List<Byte> to byte[]
        byte[] result = new byte[out.size()];
        for (int k = 0; k < out.size(); k++) {
            result[k] = out.get(k);
        }
        return result;
    }

    /**
     * Crée une liste de messages SysEx (tableaux d'octets) à partir du preset brut
     * @param preset Le preset brut (octets 0x00-0xFF)
     * @param maxDataBytes Par message SysEx (typiquement 256 ou 512, mais jamais plus que ~1000)
     * @return Liste de messages SysEx prêts à envoyer
     */
    public static List<byte[]> buildSysExMessages(byte[] preset, int maxDataBytes) {
        // 1. Encoder le preset au format 7 bits MIDI
        byte[] midiData = encode7Bit(preset);

        List<byte[]> messages = new ArrayList<>();

        int offset = 0;
        while (offset < midiData.length) {
            int chunkLen = Math.min(maxDataBytes, midiData.length - offset);

            // Taille totale = F0 + manufacturer + chunk + F7
            byte[] msg = new byte[1 + MANUFACTURER_ID.length + chunkLen + 1];

            int idx = 0;
            msg[idx++] = (byte) 0xF0;
            for (byte b : MANUFACTURER_ID) {
                msg[idx++] = b;
            }
            System.arraycopy(midiData, offset, msg, idx, chunkLen);
            idx += chunkLen;
            msg[idx] = (byte) 0xF7;

            messages.add(msg);
            offset += chunkLen;
        }
        return messages;
    }

    // --- Exemple d'utilisation ---
    public static void main(String[] args) {
        // EXEMPLE : preset d'octets (remplace par tes données)
        byte[] preset = new byte[] { (byte)0x03, (byte)0x02, (byte)0x0, (byte)0x0, (byte)0x10, (byte)0x0b, (byte)0x0, (byte)0x80 };
        // Utilise ton tableau complet ici !

        // Taille utile (sans header SysEx) par message (par ex. 256)
        int maxDataBytes = 256;

        List<byte[]> sysexMessages = buildSysExMessages(preset, maxDataBytes);

        // Exemple : affichage en hex
        int msgNum = 1;
        for (byte[] msg : sysexMessages) {
            System.out.printf("Message SysEx %d (%d octets) :\n", msgNum++, msg.length);
            for (int i = 0; i < msg.length; i++) {
                System.out.printf("%02X ", msg[i]);
                if ((i + 1) % 16 == 0) System.out.println();
            }
            System.out.println("\n---");
        }
    }
}