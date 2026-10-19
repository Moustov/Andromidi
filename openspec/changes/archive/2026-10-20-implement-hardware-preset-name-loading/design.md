## Context

See proposal.md. The desktop app requests and parses the 240 preset names at startup using `startLoadPresetList`. We will replicate this binary parsing in Java.

## Goals / Non-Goals

**Goals:**
- Parse little-endian integers and 17-byte padded strings from raw decoded SysEx chunks.
- Populate `MatriboxIIPro.presetNames` successfully.

**Non-Goals:**
- Persistent disk caching of preset names.
