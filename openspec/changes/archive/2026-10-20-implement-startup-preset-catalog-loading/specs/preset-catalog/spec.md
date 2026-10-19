## Purpose

Ensures application startup synchronizes with hardware preset catalog loading.

## ADDED Requirements

### Requirement: REQ-PRESET/CATALOG/005 Startup Loading Synchronization
The system SHALL display a loading indication and withhold full interactivity until the preset catalog has been successfully requested and parsed from the hardware.

#### Scenario: Startup Loading State
- **WHEN** the application starts and connects to the Matribox II Pro
- **THEN** it displays a loading message in the UI until the preset name cache is populated
