## MODIFIED Requirements

### Requirement: [REQ-MIDI/CONTROL/001] MIDI Device Connection
The system SHALL establish a connection to a compatible MIDI device (MatriboxIIPro) upon application startup, supporting both outgoing control messages and incoming status updates.

#### Scenario: [REQ-MIDI/CONTROL/001-SC01] Device Connection Success
- **WHEN** application starts
- **THEN** it identifies and connects to the MatriboxIIPro device, enabling both input and output ports for bidirectional communication.
