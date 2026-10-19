## 1. Catalog Parsing Implementation

- [x] 1.1 Implement binary parsing helper methods (`bytesToIntLE`, `bytesToString`) in `MatriboxIIPro.java`. Verify via unit test or logging.
- [x] 1.2 Implement `parsePresetList(byte[] decoded)` in `MatriboxIIPro.java` to populate `MatriboxIIPro.presetNames` from bulk SysEx data. Verify by checking log output of loaded preset names.
- [x] 1.3 Hook `parsePresetList` into `MidiCCListenerThread.java` when bulk preset list frames are received. Verify by displaying full preset names on screen when switching presets.
