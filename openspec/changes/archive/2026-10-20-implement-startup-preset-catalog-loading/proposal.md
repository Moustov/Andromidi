## Why

To ensure that preset names are fully loaded and available in memory before the user interacts with the mobile app or hardware, the application should display a loading indicator or synchronize startup until the preset catalog bulk packet has been successfully received and parsed.

## What Changes

- Add a loading state/indicator upon application startup while connecting to the Matribox II Pro and fetching the preset catalog.
- Ensure `MainActivity` waits for `MatriboxIIPro.presetNames` to populate or provides a visual feedback ("Loading presets...") before allowing interactive state updates.

## Capabilities

### New Capabilities
- `preset-catalog`: Add startup synchronization and loading UI for the preset catalog.

### Modified Capabilities
- none

## Impact

- Affected code: `MainActivity.java`, `MatriboxIIPro.java`.
- UI: Temporary loading state during MIDI initialization.
