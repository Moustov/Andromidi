## MODIFIED Requirements

### Requirement: REQ-MIDI/FEEDBACK/001 Receive Device State
The system SHALL continuously listen for, parse, and decode incoming MIDI status messages and SysEx frames sent by the Matribox II Pro device using nibble re-assembly and preset state mapping.

#### Scenario: Update Preset Display on Change
- **WHEN** the hardware sends a preset change notification SysEx frame
- **THEN** the system decodes the absolute preset index and updates the UI to show the correctly formatted bank and sub-preset (e.g., "09-A")

#### Scenario: Update BPM Display
- **WHEN** the hardware sends a tempo/BPM update message
- **THEN** the application UI reflects the synchronized BPM

#### Scenario: Update Song Title on Device Event
- **WHEN** the Matribox II Pro sends a metadata update message
- **THEN** the UI text view `song_title_txtvw` reflects the updated song title
