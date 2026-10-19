## Why

The previous stub for `parsePresetList` only logged the size of the bulk packet without parsing the binary index and name arrays. To display full preset names (e.g., "60-A SPACE ODDITY EN"), we must implement the full TLV bulk parser matching the desktop app source.

## What Changes

- Implement little-endian integer conversion (`bytesToIntLE`) and null-terminated string extraction (`bytesToString`) in `MatriboxIIPro.java`.
- Implement full iteration over the 240 preset entries in `parsePresetList` to populate `MatriboxIIPro.presetNames`.
- Invoke `parsePresetList` when incoming decoded SysEx payloads exceed 1000 bytes.

## Capabilities

### New Capabilities
- `preset-catalog`: Complete binary bulk parsing of Matribox II Pro preset catalogs.

### Modified Capabilities
- none

## Impact

- Affected code: `MatriboxIIPro.java`, `MidiCCListenerThread.java`.
