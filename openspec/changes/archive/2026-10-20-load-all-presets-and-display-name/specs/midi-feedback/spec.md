## MODIFIED Requirements

### Requirement: REQ-MIDI/FEEDBACK/001 Receive Device State
The system SHALL continuously listen for, parse, and decode incoming MIDI status messages and SysEx frames sent by the Matribox II Pro device, combining preset bank/letter mapping with cached hardware preset names.

#### Scenario: Update Preset Display with Full Name
- **WHEN** the hardware sends a preset change notification SysEx frame
- **THEN** the system decodes the absolute index, looks up the cached hardware preset name, and updates the UI (e.g., "09-A EMO Tremolo")
