## Why

Currently, the Matribox II Pro integration is largely outbound (sending commands to the device). To enable full feedback and bi-directional control, we need to listen for actions (CC messages, status updates) sent by the Matribox II Pro over USB. This allows the application to stay in sync with the hardware state in real-time.

## What Changes

- Implement a USB input listener to capture MIDI messages from the Matribox II Pro.
- Create a handler for incoming MIDI CC messages to update the application state (e.g., current preset, BPM, device status).
- Enhance `MidiCCListenerThread` or similar mechanism to process incoming USB packets.
- Update UI components (specifically `song_title_txtvw`) in real-time based on incoming status messages.

## Capabilities

### New Capabilities
- `midi-feedback`: Capability to handle incoming MIDI feedback from the connected Matribox II Pro.

### Modified Capabilities
- `midi-control`: Updating the existing capability to include reception of device-state messages.

## Impact

- Affected code: `com.pantesting.andromidi.midi.Midi`, `com.pantesting.andromidi.midi.MidiCCListenerThread`, and `MainActivity`.
- APIs: Enhanced `Midi` class to support input port reading.
