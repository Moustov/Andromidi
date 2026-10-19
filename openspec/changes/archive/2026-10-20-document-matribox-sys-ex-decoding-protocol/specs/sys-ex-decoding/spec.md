## Purpose

Comprehensive protocol specification for Matribox II Pro USB MIDI communication, framing, nibble decoding, and preset state calculation.

## ADDED Requirements

### Requirement: REQ-SYSEX/PROTOCOL/001 Framing and Nibble Decoding
The system SHALL recognize standard Matribox SysEx headers (`F0 21 25 4D 50 00 00`) and decode nibble-encoded payloads by re-assembling high and low nibbles (`(high << 4) | low`).

#### Scenario: Nibble Re-assembly
- **WHEN** a raw nibble-encoded SysEx packet is received
- **THEN** every pair of nibbles is combined into an original payload byte

### Requirement: REQ-SYSEX/PROTOCOL/002 Preset Index Mapping
The system SHALL compute the exact bank number and sub-preset letter from the absolute preset index extracted from device state frames.

#### Scenario: Bank and Letter Calculation
- **WHEN** an absolute preset index is parsed from the decoded payload
- **THEN** the bank number is calculated as `(absoluteIndex / 4) + 1` and the sub-preset letter as `(absoluteIndex % 4) + 'A'`
