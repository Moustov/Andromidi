## Purpose

Provides robust decoding of proprietary Matribox II Pro SysEx frames, including nibble re-assembly and preset state mapping.

## ADDED Requirements

### Requirement: REQ-SYSEX/DECODE/001 Nibble Re-assembly
The system SHALL decode incoming SysEx byte streams by re-assembling nibbles into original payload bytes using high and low nibble combination.

#### Scenario: Successful Nibble Decoding
- **WHEN** a raw nibble-encoded SysEx byte array is received from the device
- **THEN** the system reconstructs the payload bytes via `(high << 4) | low`

### Requirement: REQ-SYSEX/DECODE/002 Preset State Mapping
The system SHALL calculate the correct preset bank and sub-preset letter from the decoded absolute preset index using official protocol formulas.

#### Scenario: Preset Calculation
- **WHEN** an absolute preset index is extracted from the decoded payload
- **THEN** the preset bank is computed as `(absoluteIndex / 4) + 1` and the sub-preset letter as `(absoluteIndex % 4) + 'A'`
