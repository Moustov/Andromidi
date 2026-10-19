## Context

See proposal.md. The reference desktop application implementation (`HTMIDIMessage.dart` and `HTModelPresetControl.dart`) demonstrates that MIDI SysEx frames are nibble-encoded and require specific decoding formulas for preset indices.

## Goals / Non-Goals

**Goals:**
- Formalize the nibble decoding algorithm in Java.
- Standardize preset calculation based on official formulas (`tens = absoluteIndex / 4 + 1`, `letter = A + (absoluteIndex % 4)`).

**Non-Goals:**
- Implementing full LZO decompression unless required for complex preset dumps.

## Decisions

- **Approach**: Replicate the exact nibble-encoding reversal (`(high << 4) | low`) found in the desktop app's `DecodeToMIDSysEx`.
- **Alternative considered**: Using raw byte mapping. Rejected due to half-byte nibble splitting used in Matribox USB transport layer.

## Risks / Trade-offs

- [Risk] Variations in firmware message length → Mitigation: Add bounds checking (`decoded.length`) before index access.
