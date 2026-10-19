# Matribox II Pro: Protocol & Module Reference

## Table of Contents
1. [USB-MIDI Protocol Reference](#1-usb-midi-protocol-reference)
    - [Framing Specification](#framing-specification)
    - [Message Serialization](#message-serialization)
2. [Module & Parameter Reference](#2-module--parameter-reference)
    - [Global Modules](#global-modules)
    - [Preset Modules](#preset-modules)
        - [DYN, FREQ, WAH, DRV, AMP...](#module-mapping)
3. [Protocol Construction Guide](#3-protocol-construction-guide)
    - [Building a Parameter Write Frame](#building-a-parameter-write-frame)
    - [Addressing Schema](#addressing-schema)

---

## 1. USB-MIDI Protocol Reference

### Framing Specification
Each USB record is a 13-byte header followed by a nibble-encoded payload.
*   **Header:** `F0 21 25 4D 50 00 00` + `TaskID` + `Flag` + `v1` (2 bytes) + `v2` (2 bytes)
*   **Terminator:** Only the final record of a multi-record message ends in `F7`.

### Message Serialization
1.  **Logical Body:** `CmdCode` (4 bytes LE) + `dataLen` (4 bytes LE) + `DataMapBytes`.
2.  **Compression:** Apply `LZO1X-1` to the `DataMapBytes`.
3.  **Nibble Encode:** Convert the `CmdCode + dataLen + LZO(DataMap)` to nibbles (2 nibbles per byte).
4.  **Wrap:** Embed in the Record Header format.

---

## 2. Module & Parameter Reference

### Global Modules (`0x11000000`)
Global parameters are accessed using the `GLOBAL_SINGLE` command (`0x11000001`).

| Module | Sub-Address | Example Parameter | Range |
| :--- | :--- | :--- | :--- |
| Audio | `0x2100` | Input Level | 0.0 - 1.0 |
| MIDI | `0x6100` | MIDI Channel | 1 - 4 |
| Drum | `0x8400` | BPM | 40 - 240 |

### Module Mapping
| ID | Module Name | Primary Use |
| :--- | :--- | :--- |
| `0x01` | DYN | Dynamics processing |
| `0x02` | FREQ | Pitch/Filter/Harmony |
| `0x03` | WAH | Wah-wah variants |
| `0x04` | DRV | Drives/Distortion |
| `0x05` | AMP | Amp models |
| `0x06` | CAB | Cabinet IRs |
| ... | ... | ... |

---

## 3. Protocol Construction Guide

### Building a Parameter Write Frame
To modify a parameter, follow this sequence:

1.  **Define Header:** `0xF0 0x21 0x25 0x4D 0x50 0x00 0x00`
2.  **Define Command:** Use `0x01050001` (`PRESET_ALGR_PARA_VALUE`).
3.  **Construct Payload:**
    *   `slotId`: 4-byte LE (e.g., Effect Slot 0 = `00 00 00 00`)
    *   `paraId`: 4-byte LE (e.g., Gain = `00 00 00 00`)
    *   `value`: Float32 LE (e.g., `0.5` = `00 00 00 3F`)
4.  **Compress & Nibble:**
    *   `LZO1X-1_Compress(Payload)`
    *   Nibble-encode the result.
5.  **Calculate Header Fields:**
    *   `v1` = Total length of `(CmdCode + dataLen + LZO_Data)`
    *   `v2` = Current record offset

### Addressing Schema
*   **CmdCode:** The base identifier for the transaction (e.g., `0x10000001` for Preset Signaling).
*   **Sub-Address:** Used within modules to index specific parameters (e.g., the knob slot `0x01020001`).
*   **Value Format:** 
    *   `Integer`: Standard 32-bit LE.
    *   `Float`: Standard IEEE 754 32-bit LE.
    *   `Boolean`: 0 (Off) / 1 (On).
