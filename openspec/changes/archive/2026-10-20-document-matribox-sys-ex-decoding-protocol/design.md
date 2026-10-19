## Context

Reverse-engineering results and reference desktop app sources (`HTMIDIMessage.dart`, `HTModelPresetControl.dart`) establish the exact transport, framing, and decoding rules for Matribox II Pro hardware integration.

## Goals / Non-Goals

**Goals:**
- Formally record protocol specifications in OpenSpec.
- Document architectural boundaries between real-time hardware state feedback and local song management (`SongsActivity`).

**Non-Goals:**
- Changing runtime code behavior (already implemented and verified).

## Decisions

- **Approach**: Maintain clean separation where real-time hardware status feedback provides accurate preset indexing (`60-A`), while song titles and setlists are managed via local JSON assets (`SongsActivity`).
