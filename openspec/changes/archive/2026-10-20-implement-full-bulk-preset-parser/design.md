## Context

See proposal.md. Replicating `HTModelPresetControl.startLoadPresetList` parsing logic in Java.

## Goals / Non-Goals

**Goals:**
- Correctly parse 4-byte LE integers and 17-byte strings from decoded byte arrays.
- Populate the preset name cache successfully.

**Non-Goals:**
- Modifying local preset files.
