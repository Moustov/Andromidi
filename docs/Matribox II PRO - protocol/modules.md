# Module and Parameter Documentation: Matribox II Pro

## Table of Contents
1. [Global Modules](#1-global-modules)
2. [Preset Modules & Algorithms](#2-preset-modules--algorithms)
    - [Primary Modules Overview](#primary-modules-overview)
    - [Detailed Algorithm Examples](#detailed-algorithm-examples)
3. [Communication Technical Specification](#3-communication-technical-specification)

---

## 1. Global Modules (`0x11000000`)
These control device-level environment, I/O, and global utilities.

| Parameter | Sub-Address | Type | Range |
| :--- | :--- | :--- | :--- |
| **System** | | | |
| Device ID | `0x0000` | Int | - |
| Firmware Version | `0x0100` | Int | - |
| Run Mode | `0x0200` | Int | - |
| **Display** | `0x1100` | Int | Language/Theme/Light |
| **Audio I/O** | `0x2100` | Float | Input Level (0.0-1.0) |
| **USB Audio** | `0x3100` | Float | Rec Level |

---

## 2. Preset Modules & Algorithms

The Matribox II Pro categorizes effects into modules (`DYN`, `FREQ`, `WAH`, `DRV`, `AMP`, `CAB`, `IR`, `EQ`, `MOD`, `DLY`, `RVB`, `CLONE`, `FXLOOP`, `FXSND`, `FXRTN`, `VOL`). 

### Primary Modules Overview

| Module | Purpose |
| :--- | :--- |
| **DYN** | Dynamics (Compressors, Boosts, Gates) |
| **FREQ** | Frequency/Pitch shifters (Filter, Octaver, Harmony) |
| **WAH** | Wah-wah variants |
| **DRV** | Overdrive & Distortion |
| **AMP** | Amplifier simulations |
| **CAB** | Cabinet IR & Simulation |
| **MOD** | Modulation (Chorus, Flanger, Phaser) |
| **DLY** | Delays |
| **RVB** | Reverbs |
| **EQ** | Equalizers |

### Detailed Algorithm Examples
*Note: For the exhaustive list of over 100+ effects, refer to the source `alg_data.json`.*

#### Module: AMP (Example: Fender B-Man)
| Parameter | Range | Default |
| :--- | :--- | :--- |
| Gain | 0-100 | 30 |
| Presence | 0-100 | 50 |
| Volume | 0-100 | 50 |
| Bass | 0-100 | 50 |
| Middle | 0-100 | 50 |
| Treble | 0-100 | 50 |

#### Module: DRV (Example: Skreamer)
| Parameter | Range | Default |
| :--- | :--- | :--- |
| Gain | 0-100 | 40 |
| Tone | 0-100 | 70 |
| Volume | 0-100 | 50 |

---

## 3. Communication Technical Specification
*   **Message Pattern**: Each module/algorithm parameter change uses:
    *   **CmdCode**: `PRESET_ALGR_PARA_VALUE` (`0x01050001`)
    *   **Payload**: `slotId` (Int32 LE), `paraId` (Int32 LE), `value` (Float32 LE).
*   **Encoding**: Data is LZO-compressed per block and wrapped in the USB record framing defined in `protocol.md`.
