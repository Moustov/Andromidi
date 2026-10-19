## Context

See proposal.md. Based on `HTModelPresetControl.dart` (`startLoadPresetList`), the desktop app requests all 240 preset names at startup using `HT_CMD_CODE_ADDR_PRESET_LIST` (`0x010a0000`) wrapped in `HT_CMD_CODE_ADDR_PRESET_SIGNAL` (`0x10000001`).

## Goals / Non-Goals

**Goals:**
- Replicate the preset list request mechanism in Java.
- Parse the bulk incoming preset list to build an in-memory index-to-name lookup table.
- Display full preset names on hardware state updates.

**Non-Goals:**
- Persisting preset names to local storage (in-memory cache upon connection is sufficient).

## Decisions

- **Approach**: Send the preset list request on connection, parse the incoming chunked SysEx responses using nibble decoding and TLV parsing, and store names in a static lookup map in `MatriboxIIPro`.
