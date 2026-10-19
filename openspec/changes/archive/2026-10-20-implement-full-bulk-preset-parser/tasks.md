## 1. Bulk Parser Implementation

- [x] 1.1 Implement `bytesToIntLE` and `bytesToString` in `MatriboxIIPro.java`. Verify by unit test or logging.
- [x] 1.2 Implement full `parsePresetList(byte[] decoded)` in `MatriboxIIPro.java` iterating over 240 presets. Verify by checking log output of loaded preset names.
- [x] 1.3 Invoke `parsePresetList` from `MidiCCListenerThread.java` on large decoded frames. Verify by observing full preset names on screen.
