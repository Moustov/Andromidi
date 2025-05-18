Sending PRST files to Matribox
===

1. PRST files are base64 encoded files => you must decode them.
2. Then for each 7 bytes of the preset, we create an 8th "header" byte: each bit indicates whether the corresponding byte has its bit 7 set to 1.
3. Next, we store the 7 least significant bits of each byte.
4. SysEx messages start with F0, contain the manufacturer ID, then your data, and end with F7.
5. The preset can be split into several messages if required (for example, 256 or 512 useful bytes per message).
