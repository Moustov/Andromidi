## Why

The Matribox II Pro hardware communicates via proprietary SysEx messages with nibble-encoding (splitting bytes into high/low nibbles) and structured payloads. To ensure robust bi-directional synchronization (such as displaying the correct preset number, sub-preset letter A/B/C/D, and status), we need to formalize the frame decoding logic based on the reference desktop application source code and protocol documentation.

## What Changes

- Formalize SysEx frame parsing, including nibble re-assembly (`(high << 4) | low`).
- Implement accurate preset indexing logic using the official formula (`absoluteIndex / 4 + 1` for bank number, `absoluteIndex % 4` for sub-preset letter A-D).
- Document and implement handling for response task IDs (`1A`, `1B`) and state mapping.

## Capabilities

### New Capabilities
- `sys-ex-decoding`: Formalized protocol decoder for incoming Matribox II Pro SysEx messages.

### Modified Capabilities
- `midi-feedback`: Update requirements to reflect structured SysEx frame decoding and preset state synchronization.

## Impact

- Affected code: `com.pantesting.andromidi.midi.MidiCCListenerThread` and related MIDI parsing utilities.
- Compatibility: Aligns Android app telemetry with the official desktop application's protocol implementation.
