## 1. Preset Name Extraction

- [x] 1.1 Analyze raw decoded payload bytes to locate ASCII character sequences for preset names. Verify by logging raw payload bytes upon preset change.
- [x] 1.2 Update `MidiCCListenerThread.java` to extract and trim the preset name string. Verify by checking if the UI displays the correct preset name alongside the bank and letter.
- [x] 1.3 Perform end-to-end verification of preset name display. Verify by switching between different presets and observing UI updates.
