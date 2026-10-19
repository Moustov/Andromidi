## Purpose

Complete binary bulk parsing of Matribox II Pro preset catalogs to map actual indices to preset names.

## ADDED Requirements

### Requirement: REQ-PRESET/CATALOG/004 Binary Bulk Parsing
The system SHALL parse 4-byte little-endian actual indices and 17-byte null-terminated strings from incoming bulk SysEx data blocks.

#### Scenario: Bulk Parsing Execution
- **WHEN** a bulk SysEx packet (> 1000 bytes) is received
- **THEN** the system parses all 240 preset definitions and stores them in `MatriboxIIPro.presetNames`
