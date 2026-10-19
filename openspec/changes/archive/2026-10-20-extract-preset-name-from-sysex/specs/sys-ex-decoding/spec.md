## MODIFIED Requirements

### Requirement: REQ-SYSEX/DECODE/002 Preset State Mapping
The system SHALL calculate the correct preset bank, sub-preset letter, and extract the preset name from incoming SysEx frames.

#### Scenario: Preset Name Extraction
- **WHEN** a preset state SysEx frame is received and decoded
- **THEN** the system extracts the 16-byte preset name string from the payload and updates the UI alongside the preset bank and letter
