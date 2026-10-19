## Context

See proposal.md. Reference implementation in `HTModelPresetControl.dart` (`readInfoData`) parses preset names with a fixed length of 17 bytes (`nameSize`) from data blocks.

## Goals / Non-Goals

**Goals:**
- Identify the exact byte offset in decoded SysEx frames containing preset names.
- Combine preset number, letter, and name into a single UI string.

**Non-Goals:**
- Modifying preset names on the hardware.

## Decisions

- **Approach**: Scan decoded payload segments or target specific offsets identified via raw debugging to extract ASCII character arrays representing the preset name.
