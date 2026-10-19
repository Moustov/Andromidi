## 1. Preset Catalog Loading

- [x] 1.1 Implement `requestPresetList()` in `MatriboxIIPro.java` using the correct `HT_CMD_CODE_ADDR_PRESET_SIGNAL` and `HT_CMD_CODE_ADDR_PRESET_LIST` addresses. Verify by checking outgoing SysEx logs upon connection.
- [x] 1.2 Implement bulk response parsing in `MidiCCListenerThread.java` or `MatriboxIIPro.java` to extract 240 preset names into an in-memory map. Verify by logging parsed preset names.
- [x] 1.3 Update preset change UI rendering to retrieve and display the cached preset name alongside "XX-Y". Verify by switching presets on the hardware and observing the full name on screen.
