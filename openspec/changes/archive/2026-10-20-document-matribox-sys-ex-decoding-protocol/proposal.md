## Why

To ensure complete traceability and long-term maintainability, we need to formalize and document the reverse-engineered Matribox II Pro USB-MIDI protocol details in our OpenSpec specs, including framing, nibble encoding/decoding, preset calculation formulas (`60-A`), and the architectural separation between real-time hardware status feedback and local JSON setlist management (`SongsActivity`).

## What Changes

- Document the SysEx framing header (`F0 21 25 4D 50 00 00`) and task ID handling (`1A`, `1B`).
- Formalize the nibble re-assembly decoding algorithm (`(high << 4) | low`).
- Document the preset index calculation formulas derived from official desktop app sources (`tens = absoluteIndex / 4 + 1`, `letter = A + (absoluteIndex % 4)`).
- Document the architectural distinction between real-time hardware state feedback and local song setlist management (`SongsActivity`).

## Capabilities

### New Capabilities
- `sys-ex-decoding`: Comprehensive protocol specification for Matribox II Pro USB MIDI communication and state decoding.

### Modified Capabilities
- `midi-feedback`: Update specs to include complete protocol behavior and UI synchronization details.

## Impact

- Documentation: Updates OpenSpec specs to reflect the finalized protocol understanding.
- No breaking changes to existing implementation.
