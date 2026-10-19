## Purpose

Defines the parsing and caching mechanism for the 240 hardware preset names fetched via bulk SysEx responses.

## ADDED Requirements

### Requirement: REQ-PRESET/CATALOG/003 Bulk Preset List Parsing
The system SHALL parse incoming bulk SysEx data packets corresponding to `HT_CMD_CODE_ADDR_PRESET_LIST` to extract preset indices and 17-byte ASCII names into an in-memory cache.

#### Scenario: Successful Bulk Parsing
- **WHEN** a preset list bulk packet is received from the hardware
- **THEN** the system iterates through the 240 entries, extracts each actual index and name, and stores them in `MatriboxIIPro.presetNames`
