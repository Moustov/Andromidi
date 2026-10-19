## Why

To display the full preset name (e.g., "09-A EMO Tremolo" or "SPACE ODDITY EN") directly from the Matribox II Pro hardware when a device button/footswitch is pressed, the application needs to request and cache the full preset list (`HT_CMD_CODE_ADDR_PRESET_LIST`) upon startup and map incoming preset indices to their corresponding names.

## What Changes

- Send the preset list request (`HT_CMD_CODE_ADDR_PRESET_LIST`) upon successful USB MIDI connection.
- Implement a parser for the bulk preset list response to store preset names in memory.
- Update the real-time feedback listener to combine the calculated bank/letter with the loaded preset name in the UI (`song_title_txtvw`).

## Capabilities

### New Capabilities
- `preset-catalog`: Capability to request, parse, and cache the full catalog of preset names from the Matribox II Pro hardware.

### Modified Capabilities
- `midi-feedback`: Update integration to utilize cached preset names when displaying hardware state updates.

## Impact

- Affected code: `MatriboxIIPro.java`, `MidiCCListenerThread.java`, and a new preset catalog manager.
- Performance: One-time bulk load upon startup to cache 240 preset names.
