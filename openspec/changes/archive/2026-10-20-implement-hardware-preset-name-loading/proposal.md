## Why

Users want the application to display the full, official preset name (e.g., "09-A EMO Tremolo") directly from the Matribox II Pro hardware memory upon connection and preset selection, rather than relying solely on local JSON files.

## What Changes

- Send `HT_CMD_CODE_ADDR_PRESET_LIST` upon connection to fetch all 240 preset names from the hardware.
- Implement a binary TLV / bulk response parser in `MatriboxIIPro.java` to populate `MatriboxIIPro.presetNames`.
- Update `MidiCCListenerThread.java` to look up and display the hardware preset name alongside the formatted bank/letter.

## Capabilities

### New Capabilities
- `preset-catalog`: Full hardware preset catalog loading and caching from incoming SysEx bulk transfers.

### Modified Capabilities
- `midi-feedback`: Display cached hardware preset names in real-time UI feedback.

## Impact

- Affected code: `MatriboxIIPro.java`, `MidiCCListenerThread.java`.
- Network/USB: One-time bulk data transfer upon connection.
