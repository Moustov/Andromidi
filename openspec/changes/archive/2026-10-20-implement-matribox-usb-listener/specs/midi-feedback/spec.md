## Purpose

Enables the application to listen for and react to status messages or feedback sent from the Matribox II Pro hardware over USB, synchronizing hardware state with the application UI.

## ADDED Requirements

### Requirement: REQ-MIDI/FEEDBACK/001 Receive Device State
The system SHALL continuously listen for and parse incoming MIDI status messages sent by the Matribox II Pro device.

#### Scenario: Update Preset Display on Change
- **WHEN** the hardware sends a preset change notification
- **THEN** the UI updates to show the new current preset ID

#### Scenario: Update BPM Display
- **WHEN** the hardware sends a tempo/BPM update message
- **THEN** the application UI reflects the synchronized BPM

#### Scenario: Update Song Title on Device Event
- **WHEN** the Matribox II Pro sends a metadata update message
- **THEN** the UI text view `song_title_txtvw` reflects the updated song title
