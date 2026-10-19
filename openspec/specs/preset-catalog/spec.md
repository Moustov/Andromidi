# preset-catalog Specification

## Purpose
Enables loading, parsing, and caching of the complete preset name catalog from the Matribox II Pro hardware upon connection, and provides startup readiness feedback.

## Requirements

### Requirement: REQ-PRESET/CATALOG/001 Startup Preset List Request
The system SHALL request the full preset list from the Matribox II Pro device immediately after establishing a successful USB MIDI connection.

#### Scenario: Requesting Preset List
- **WHEN** the application connects to the Matribox II Pro device
- **THEN** it sends the `HT_CMD_CODE_ADDR_PRESET_LIST` SysEx request frame

### Requirement: REQ-PRESET/CATALOG/002 Preset Name Mapping
The system SHALL map incoming absolute preset indices to their corresponding full names fetched from the device catalog.

#### Scenario: Displaying Full Preset Name
- **WHEN** a preset change notification is received with an absolute index
- **THEN** the system resolves the preset name from the cached catalog and updates the UI with both the formatted identifier and the full name (e.g., "09-A EMO Tremolo")

### Requirement: REQ-PRESET/CATALOG/005 Startup Loading Synchronization
The system SHALL display a loading indication upon startup and transition the song title display to "Ready" once initialization and connection to the hardware are complete.

#### Scenario: Transition to Ready State
- **WHEN** connection to the Matribox II Pro is established and initial feedback is ready
- **THEN** the UI text view `song_title_txtvw` updates from "Loading presets..." or "(no song)" to "Ready"
