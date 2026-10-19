## Why

When the application finishes loading or connecting to the Matribox II Pro and is ready for use, the placeholder text "(no song)" in the song title view should update to "Ready" to provide clear visual confirmation of successful initialization.

## What Changes

- Update `MainActivity.java` or the MIDI connection listener so that once connection is established and initial state is received, the song title text view (`song_title_txtvw`) updates from "(no song)" to "Ready" (unless an active preset or song is explicitly selected).

## Capabilities

### New Capabilities
- none

### Modified Capabilities
- `preset-catalog`: Update UI state to display "Ready" upon successful connection and catalog readiness.

## Impact

- Affected code: `MainActivity.java`, `MidiCCListenerThread.java`.
- UI: Improves user feedback by changing "(no song)" to "Ready" upon readiness.
