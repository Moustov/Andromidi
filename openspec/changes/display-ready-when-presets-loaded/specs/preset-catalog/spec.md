## MODIFIED Requirements

### Requirement: REQ-PRESET/CATALOG/005 Startup Loading Synchronization
The system SHALL display a loading indication upon startup and transition the song title display to "Ready" once initialization and connection to the hardware are complete.

#### Scenario: Transition to Ready State
- **WHEN** connection to the Matribox II Pro is established and initial feedback is ready
- **THEN** the UI text view `song_title_txtvw` updates from "Loading presets..." or "(no song)" to "Ready"
