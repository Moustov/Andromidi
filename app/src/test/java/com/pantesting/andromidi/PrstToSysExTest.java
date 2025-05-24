package com.pantesting.andromidi;

import com.pantesting.andromidi.midi.MidiSysexEncoder;
import com.pantesting.andromidi.midi.PrstToSysEx;
import junit.framework.TestCase;

import java.util.Base64;
import java.util.List;

public class PrstToSysExTest extends TestCase {

    public void testGenerate_sysex_from_AC_Bass_Sim_prst() {
        // 65 67 32 66 97 115 115 = AC Bass
        // 41 43 20 42 61 73 73
        //01000001  01000011 00100000 01000010 01100001 01110011 01110011
        String prst = "WzMsMiwwLDAsMTYsMTEsMCwxMjgsMCw1LDEsNCwzLDEyLDEsNSwxLDE1LDEwNSwyLDEwNSwxNjQsMiwwLDIsMSw1NCwxOTYsMTM3LDEzNSw2NSw2NywzMiw2Niw5NywxMTUsMTE1LDMyLDgzLDEwNSwxMDksMCw4MCw4Miw3OSwxMzIsMywxLDE4MywyMzEsNDIsMTEsMTQwLDAsMSwyNDksMTY3LDIxNSwxODIsMTA4LDAsMSwyMTAsMTk5LDYyLDE4MiwxMDgsMCwxLDEwLDE1Nyw4NCw1MywxMDgsMCwxLDIwNCwxOTYsOTUsMTgwLDEwOCwwLDEsMjMsNTIsMTk3LDU0LDEwOCwwLDEsMTM2LDEyMSwyNTMsMTgwLDEwOCwwLDEsMjMwLDEwNCw5Niw1NCwxMDgsMCw5LDIxNCwyMjMsNjUsOTksMTExLDExNywxMTUsMTE2LDEwNSw5OSwwLDAsOTcsMTQsMTAsMTUyLDEwLDMsMiwxLDAsMSwxMywwLDE2NSwyLDE1LDEzMiwyLDMsMywxLDEsMiwzLDQsMTA3LDEsNywwLDUsOTcsNSw3NiwxMDgsMSwyLDAsNCwxLDYsMCw5OCwzLDUsMjU1LDk2LDAsNCw1LDAsMCw3LDEwLDE0LDAsMTA0LDEsMCw4LDI1NSw2OSwwLDAsMTAsMSwwLDAsMSwwLDAsMCwxLDUzLDAsMCwxLDExLDAsMCwxMiwzLDAsMCw2LDI3LDE1Niw2LDQ4LDE2LDAsMTEwLDEwLDIzMCwyLDEyNSwyLDUsMTA4LDYsMiwwLDAsMCwxMTIsNjYsMTE2LDEsNDAsMTIsMCwzLDE1Miw2NSwwLDY2LDE1Niw3MCw0MCw2MCwwLDU0LDM2LDAsMSw3Miw2NiwwLDAsMTA5LDAsMTQwLDEyNCw3LDIsNjQsMCwwLDMyLDY1LDU0LDE2NCwwLDQ4LDkyLDAsMTI0LDYsMTA4LDAsNDgsMTAwLDAsMzIsMjEsNjgsMCwzMiw3LDQ1LDEsMjAwLDIwNCwyMCwxMjQsNSw0MiwxMiwwLDMyLDMsMjEzLDEsMjAwLDEyNCwyOCwzMiwzLDE2MCwwLDQ5LDE0MCwwLDEyNCwxNCwxMjQsMTMsNDksMTA0LDAsMzIsMCw0Myw3MywwLDEsMTYwLDAsMTM1LDQyLDUwLDAsMTIwLDE5MywxMDcsOTUsMTU5LDEsNywxLDAsMTc5LDEwMywyNTUsMjU1LDI1NSwxMjgsMiwzMiw5LDE2LDAsOTYsNjMsNjAsMTIsMCwxMTMsMTA2LDY5LDEyOCwxMCw0LDgsMSw4Niw1NiwxNTMsMTc3LDg2LDIwMCwxMDcsMTM2LDIsMTQwLDExNSwxNjQsMSwxMTIsMTE2LDM5LDM2LDAsOTYsMSwyNTIsNCwyNTIsMCwxMDgsMiwwLDIsMiwwLDAsMTYsMTIsMCwwLDAsMCwwLDksMSwwLDAsMTI4LDYzLDIwMCwwLDAsNDgsMTcsMCwwXQ==";
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(prst);
            int maxDataBytes = 512; // Adapter si besoin
            List<byte[]> sysexMessages = MidiSysexEncoder.buildSysExMessages(decodedBytes, maxDataBytes);

            // 4. Afficher les messages SysEx en hexadécimal
            int msgNum = 1;
            for (byte[] msg : sysexMessages) {
                System.out.printf("Message SysEx %d (%d octets) :\n", msgNum++, msg.length);
                for (int i = 0; i < msg.length; i++) {
                    System.out.printf("%02X ", msg[i]);
                    if ((i + 1) % 16 == 0) System.out.println();
                }
                System.out.println("\n---");
            }
            assert true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}