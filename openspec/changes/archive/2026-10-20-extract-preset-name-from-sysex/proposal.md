## Why

While we successfully decode preset indices and banks (e.g., "09-A"), the actual preset name (e.g., "EMO Tremolo") is not yet extracted and displayed in the application UI. Based on the extracted desktop application source (`HTModelPresetControl.dart` and `HTModelPreset.dart`), preset info including its name is contained within specific SysEx responses.

## What Changes

- Locate and parse the exact offset/structure for preset names within decoded SysEx frames.
- Update the UI (`song_title_txtvw`) to display both the formatted preset identifier and its real name (e.g., "09-A EMO Tremolo").

## Capabilities

### New Capabilities
- none

### Modified Capabilities
- `sys-ex-decoding`: Extend SysEx decoding capability to parse and extract preset names from incoming device frames.

## Impact

- Affected code: `MidiCCListenerThread.java`.
- UI: `song_title_txtvw` will show both preset number/letter and preset name.
