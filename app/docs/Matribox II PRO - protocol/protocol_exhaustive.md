# Matribox II Pro: Exhaustive Protocol & Parameter Specification

## Table of Contents
1. [Framing & Transport Layer](#1-framing--transport-layer)
2. [Command & Addressing Matrix](#2-command--addressing-matrix)
    - [System Commands](#system-commands)
    - [Parameter Access](#parameter-access)
3. [Exhaustive Module & Parameter Matrix](#3-exhaustive-module--parameter-matrix)
    - [DYN](#module-dyn)
    - [FREQ](#module-freq)
    - [WAH](#module-wah)
    - [DRV](#module-drv)
    - [AMP](#module-amp)
    - [CAB](#module-cab)
    - [IR](#module-ir)
    - [EQ](#module-eq)
    - [MOD](#module-mod)
    - [DLY](#module-dly)
    - [RVB](#module-rvb)
    - [CLONE](#module-clone)
    - [FXLOOP](#module-fxloop)
    - [FXSND](#module-fxsnd)
    - [FXRTN](#module-fxrtn)
    - [VOL](#module-vol)
4. [Communication Technical Specification](#4-communication-technical-specification)

---
## 1. Framing & Transport Layer
Standard Header: `F0 21 25 4D 50 00 00` + TaskID(1) + Flag(1) + v1(2) + v2(2).
Payload: CmdCode(4) + dataLen(4) + LZO1X-1(DataMap).

## 2. Exhaustive Module & Parameter Matrix
### Module: DYN (Index: 0)

#### Effect: COMP 1 (ID: 0, Index: 0)
- Origin: Ross Compressor

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Sustain | 0 | 0-100 |
| Volume | 1 | 0-100 |

#### Effect: COMP 2 (ID: 1, Index: 1)
- Origin: Keeley Compressor

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Sustain | 0 | 0-100 |
| Attack | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Clipping | 3 | 0-100 |

#### Effect: COMP 3 (ID: 3, Index: 2)
- Origin: Original: Compressor

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Threshold | 0 | 0-100 |
| Ratio | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Attack | 3 | 0-100 |
| Release | 4 | 0-100 |
| Tone | 5 | 0-100 |
| Blend | 6 | 0-100 |

#### Effect: M-Boost (ID: 20, Index: 3)
- Origin: MXR M133 Micro Amp

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |

#### Effect: E-Boost (ID: 26, Index: 4)
- Origin: Xotic EP Booster

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| +3dB | 1 | Off/On |
| Bright | 2 | Off/On |

#### Effect: AC-Boost (ID: 10, Index: 5)
- Origin: Xotic AC Booster

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Treble | 3 | 0-100 |

#### Effect: BB-Boost (ID: 11, Index: 6)
- Origin: Xotic BB Preamp

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Treble | 3 | 0-100 |

#### Effect: RC-Boost (ID: 12, Index: 7)
- Origin: Xotic RC Booster

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Treble | 3 | 0-100 |

#### Effect: Fat Boost (ID: 25, Index: 8)
- Origin: Boss FA-1

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Bass | 0 | 0-100 |
| Treble | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Low Cut | 3 | Off/On |

#### Effect: AC Woody (ID: 16777216, Index: 9)
- Origin: Original: loosely based on TC Bodyrez

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Shape | 0 | 0-100 |

#### Effect: AC Sim (ID: 16777217, Index: 10)
- Origin: Boss AC-2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Body | 0 | 0-100 |
| Top | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Mode | 3 | Standard, Jumbo, Enhanced, Piezo |

#### Effect: Gate 1 (ID: 27, Index: 11)
- Origin: ISP Decimator
Ampero Name: Smart Gate

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Threshold | 0 | 0-100 |

#### Effect: Gate 2 (ID: 29, Index: 12)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Threshold | 0 | 0-100 |
| Attack | 1 | 0-100 |
| Release | 2 | 0-100 |

#### Effect: Gate 3 (ID: 33, Index: 13)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Threshold | 0 | 0-100 |
| Ratio | 1 | 0-100 |
| Attack | 2 | 0ms-1.0s-1.1s-10.0s |
| Release | 3 | 0ms-1.0s-1.1s-10.0s |
| Hold | 4 | 0ms-1.0s-1.1s-10.0s |

### Module: FREQ (Index: 1)

#### Effect: Filter (ID: 16777241, Index: 0)
- Origin: Original: Ampero 4-step filter

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Step 1 | 0 | 0-100 |
| Step 2 | 1 | 0-100 |
| Step 3 | 2 | 0-100 |
| Step 4 | 3 | 0-100 |
| Rate | 4 | 0-100 |
| Sync | 5 | Off/On |

#### Effect: Octaver (ID: 16777249, Index: 1)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Low Oct | 0 | 0-100 |
| High Oct | 1 | 0-100 |
| Dry | 2 | 0-100 |

#### Effect: Dual Melody (ID: 16777251, Index: 2)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Hi Pitch | 0 | 0 - +24 |
| Low Pitch | 1 | 0 - -24 |
| Dry | 2 | 0-100 |
| Hi Vol | 4 | 0-100 |
| Low Vol | 5 | 0-100 |

#### Effect: Pitch (ID: 16777252, Index: 3)
- Origin: Original: GP-100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Hi Pitch | 0 | 0- +12 |
| Low Pitch | 1 | 0- -12 |
| Wet | 2 | 0-100 |
| Dry | 3 | 0-100 |
| Range | 4 | 0-100 |

#### Effect: Harmony D (ID: 16777294, Index: 4)
- Origin: Original: dual voice auto harmonizer (Delay Line)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Key | 1 | C, C#, D, D#, E, F, F#, G, G#, A, A#, B |
| Mode | 2 | Major, Minor, H. Minor, Dorian, Phrygian, Lydian, Mixolydian, Locrian |
| Interval 1 | 3 | -Oct, -7th, -6th, -5th, -4th,-3rd, -2nd, +2nd, +3rd, +4th, +5th, +6th, +7th, +Oct |
| Interval 2 | 4 | -Oct, -7th, -6th, -5th, -4th,-3rd, -2nd, +2nd, +3rd, +4th, +5th, +6th, +7th, +Oct |
| Smooth | 6 | Off/On |

#### Effect: Pitch S (ID: 16777301, Index: 5)
- Origin: Freebird Monophonic PS

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Range | 0 | -2 Oct, -1 Oct, +1 Oct, +2 Oct, +/-1 Oct, +/-2 Oct |
| Position | 1 | 0-100 |
| Mix | 2 | 0-100 |
| Level | 3 | 0-100 |

#### Effect: Ring Mod (ID: 16777263, Index: 6)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Freq | 1 | 0-100 |
| Fine | 2 | -50 - +50 |
| Tone | 3 | 0-100 |

#### Effect: Tape Mod (ID: 16777267, Index: 7)
- Origin: Original: power amp saturation simulator

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Saturation | 0 | 0-100 |
| Mix | 1 | 0-100 |
| Volume | 2 | 0-100 |
| High Cut | 3 | 0-100 |

### Module: WAH (Index: 2)

#### Effect: Voks Wah (ID: 83886081, Index: 0)
- Origin: Vox V845

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Range | 0 | 0-100 |
| Q | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Position | 3 | 0-100 |

#### Effect: Cry Wah (ID: 83886088, Index: 1)
- Origin: Dunlop Cry Baby

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Range | 0 | 0-100 |
| Q | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Position | 3 | 0-100 |

#### Effect: Rack Wah (ID: 83886090, Index: 2)
- Origin: Dunlop JP95

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Range | 0 | 0-100 |
| Q | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Position | 3 | 0-100 |
| EQ | 4 | Off/On |

#### Effect: Bass Wah (ID: 83886087, Index: 3)
- Origin: Hotone Bass Press

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Range | 0 | 0-100 |
| Q | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Position | 3 | 0-100 |

#### Effect: Touch Wah (ID: 16777231, Index: 4)
- Origin: Original: Toucher (Ampero)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Sense | 0 | 0-100 |
| Range | 1 | 0-100 |
| Q | 2 | 0-100 |
| Mix | 3 | 0-100 |
| Mode | 4 | Guitar, Bass |

#### Effect: Auto Wah (ID: 16777237, Index: 5)
- Origin: Original: Crier (Ampero)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Low | 3 | 0-100 |
| Q | 4 | 0-100 |
| High | 5 | 0-100 |
| Sync | 6 | Off/On |

### Module: DRV (Index: 3)

#### Effect: Skreamer (ID: 50331648, Index: 0)
- Origin: Ibanez TS808

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Skreamer 9 (ID: 50331649, Index: 1)
- Origin: Ibanez TS9

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Butter OD (ID: 50331650, Index: 2)
- Origin: Boss OD-1

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |

#### Effect: Warm OD (ID: 50331652, Index: 3)
- Origin: Providence SOV-2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Super OD (ID: 50331654, Index: 4)
- Origin: Boss SD-1 (MIJ)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Blues OD (ID: 50331657, Index: 5)
- Origin: Boss BD-2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Full OD (ID: 50331658, Index: 6)
- Origin: Fulltone OCD

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Mode | 3 | LP, HP |

#### Effect: Breaker OD (ID: 50331662, Index: 7)
- Origin: Marshall Bluesbreaker

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Gerden OD (ID: 50331664, Index: 8)
- Origin: Hermida Zendrive

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Voice | 3 | 0-100 |

#### Effect: Timmy OD (ID: 50331678, Index: 9)
- Origin: Paul Cochrane Timmy (V2)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Treble | 3 | 0-100 |
| Mode | 4 | I, II, III |

#### Effect: Master OD (ID: 50331663, Index: 10)
- Origin: Marshall Drivemaster

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Solar Fuzz (ID: 50331686, Index: 11)
- Origin: Sola Sound Tone Bender Mk II

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Fuzz | 0 | 0-100 |
| Volume | 1 | 0-100 |

#### Effect: Fuzz Cream (ID: 50331682, Index: 12)
- Origin: EH Big Muff Pi

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Sustain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Red Fuzz (ID: 50331684, Index: 13)
- Origin: Dallas-Arbiter Fuzz Face (Germanium)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Fuzz | 0 | 0-100 |
| Volume | 1 | 0-100 |

#### Effect: JP Dist (ID: 50331690, Index: 14)
- Origin: Boss DS-1 (MIJ)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Dark Mouse (ID: 50331691, Index: 15)
- Origin: ProCo The Rat (LM308 Ver. )

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Filter | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Plexi Dist (ID: 50331693, Index: 16)
- Origin: Marshall Guv' Nor

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Master Dist (ID: 50331694, Index: 17)
- Origin: Marshall Shred Master

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Contour | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Dist Plus (ID: 50331689, Index: 18)
- Origin: MXR M104 Distortion+

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |

#### Effect: Shark (ID: 50331696, Index: 19)
- Origin: MI Audio Crunch Box

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Strive (ID: 50331698, Index: 20)
- Origin: Suhr Riot

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Mode | 3 | I, II, III |

#### Effect: Sardar Dist (ID: 50331730, Index: 21)
- Origin: Friedman BE-OD

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Treble | 3 | 0-100 |
| Presence | 4 | 0-100 |
| Tight | 5 | 0-100 |

#### Effect: Bass OD (ID: 50331711, Index: 22)
- Origin: Original: Bass Overdrive

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Mode | 3 | Normal, Scoop, Edge |
| Blend | 4 | 0-100 |

#### Effect: Bass Dist (ID: 50331712, Index: 23)
- Origin: Boss ODB-3
XTOMP/Ampero Name: Bass Crusher

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Blend | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Treble | 4 | 0-100 |

### Module: AMP (Index: 4)

#### Effect: TWD Deluxe (ID: 117440513, Index: 0)
- Origin: Fender Tweed Deluxe 5E3

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: B-Man N (ID: 117440515, Index: 1)
- Origin: Fender 5F6-A Tweed bassman Norm input

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: B-Man Bri (ID: 117440548, Index: 2)
- Origin: Fender 5F6-A Tweed bassman Bright input

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Dark Double (ID: 117440516, Index: 3)
- Origin: Fender '65 Blackface Twin Reverb

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |
| Bright | 5 | Off/On |

#### Effect: Dark Deluxe (ID: 117440517, Index: 4)
- Origin: Fender Blackface Deluxe Reverb
Norm CH

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Treble | 3 | 0-100 |

#### Effect: Supero 2 CL (ID: 117440527, Index: 5)
- Origin: Supro Dual-Tone 1624T

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Volume | 2 | 0-100 |

#### Effect: Supero 2 OD (ID: 117440552, Index: 6)
- Origin: Supro Dual-Tone 1624T (1+2)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain 1 | 0 | 0-100 |
| Tone 1 | 1 | 0-100 |
| Gain 2 | 2 | 0-100 |
| Tone 2 | 3 | 0-100 |
| Volume | 4 | 0-100 |

#### Effect: Voks 15TB (ID: 117440528, Index: 7)
- Origin: VOX AC15 Top Boost

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone Cut | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Voks 30N (ID: 117440529, Index: 8)
- Origin: VOX AC30

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone Cut | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bright | 3 | Off/On |

#### Effect: Voks 30TB (ID: 117440551, Index: 9)
- Origin: VOX AC30

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone Cut | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Treble | 4 | 0-100 |
| Char | 5 | Cool, Hot |

#### Effect: Jazz 120 (ID: 117440532, Index: 10)
- Origin: Roland JC120

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Bass | 1 | 0-100 |
| Middle | 2 | 0-100 |
| Treble | 3 | 0-100 |
| Bright | 4 | Off/On |

#### Effect: Superb CL (ID: 117440533, Index: 11)
- Origin: Matchless Chieftain

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Superb OD (ID: 117440584, Index: 12)
- Origin: Matchless Chieftain Hi input

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif Star CL (ID: 117440537, Index: 13)
- Origin: Mesa/Boogie Lone Star CH1

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif Star OD (ID: 117440586, Index: 14)
- Origin: Mesa/Boogie Lone Star CH2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Input | 0 | 0-100 |
| Gain | 1 | 0-100 |
| Presence | 2 | 0-100 |
| Volume | 3 | 0-100 |
| Bass | 4 | 0-100 |
| Middle | 5 | 0-100 |
| Treble | 6 | 0-100 |

#### Effect: Bog SV CL (ID: 117440538, Index: 15)
- Origin: Bogner Shiva (20th anniversary ver.) clean ch

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Treble | 4 | 0-100 |
| Bright | 5 | Off/On |

#### Effect: Bog SV OD (ID: 117440573, Index: 16)
- Origin: Bogner Shiva

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Bog XT Blue (ID: 117440579, Index: 17)
- Origin: 过载 Drive

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Bog XT Red (ID: 117440622, Index: 18)
- Origin: Bogner XTC red CH (modern)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Doctor CL (ID: 117440539, Index: 19)
- Origin: Dr. Z Maz 38 Lo input

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone Cut | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Doctor OD (ID: 117440585, Index: 20)
- Origin: Dr. Z Maz 38 (hi input)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Tone Cut | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Dragon CL (ID: 117440543, Index: 21)
- Origin: Grindrod Pendragon

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Dragon CL B (ID: 117440635, Index: 22)
- Origin: Grindrod Pendragon (Bright)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Dragon OD (ID: 117440636, Index: 23)
- Origin: Grindrod Pendragon Drive

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Sol 100 CL (ID: 117440547, Index: 24)
- Origin: Soldano SLO100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Sol 100 OD (ID: 117440583, Index: 25)
- Origin: Soldano SLO100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Sol 100 LD (ID: 117440601, Index: 26)
- Origin: Soldano SLO100 OD ch

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Brit 45 (ID: 117440554, Index: 27)
- Origin: Marshall JTM45 Norm

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Brit 45+ (ID: 117440555, Index: 28)
- Origin: Marshall JTM45 Treble

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Brit 45JP (ID: 117440556, Index: 29)
- Origin: Marshall JTM45 Jump

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain 1 | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |
| Gain 2 | 6 | 0-100 |

#### Effect: Brit 50 (ID: 117440557, Index: 30)
- Origin: Marshall JMP50 Norm

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Brit 50+ (ID: 117440558, Index: 31)
- Origin: Marshall JMP50 Treble

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Brit 50JP (ID: 117440559, Index: 32)
- Origin: Marshall JMP50 Jump

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain 1 | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |
| Gain 2 | 6 | 0-100 |

#### Effect: Brit SLP (ID: 117440560, Index: 33)
- Origin: Marshall SLP Norm

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Brit 800 (ID: 117440565, Index: 34)
- Origin: Marshall JCM800

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Brit 900 (ID: 117440590, Index: 35)
- Origin: Marshall JCM900

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Flyman 1 (ID: 117440576, Index: 36)
- Origin: Friedman BE100 (BE ch)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Flyman 2 (ID: 117440577, Index: 37)
- Origin: Friedman BE100 (BE ch)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Flyman+ 1 (ID: 117440605, Index: 38)
- Origin: Friedman BE100 (HBE ch)
Ampero name: Fryman HB+

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Flyman+ 2 (ID: 117440606, Index: 39)
- Origin: Friedman BE100 (HBE ch)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif IIC+ 1 (ID: 117440569, Index: 40)
- Origin: Mesa/Boogie Mark II C+

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif IIC+ 2 (ID: 117440570, Index: 41)
- Origin: Mesa/Boogie Mark II C+

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif IIC+ 3 (ID: 117440571, Index: 42)
- Origin: Mesa/Boogie Mark II C+

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif IV LD 1 (ID: 117440597, Index: 43)
- Origin: Mesa/Boogie Mark IV Lead ch

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif IV LD 2 (ID: 117440598, Index: 44)
- Origin: Mesa/Boogie Mark IV Lead ch

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif IV LD 3 (ID: 117440599, Index: 45)
- Origin: Mesa/Boogie Mark IV Lead ch

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif Dual V (ID: 117440616, Index: 46)
- Origin: Mesa/Boogie Dual Rectifier (CH3, vintage)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Calif Dual M (ID: 117440617, Index: 47)
- Origin: Mesa/Boogie Dual Rectifier (CH3, modern)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Tanger R100 (ID: 117440595, Index: 48)
- Origin: Orange Rockerverb 100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Halen 51 (ID: 117440602, Index: 49)
- Origin: Peavey 5150

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |
| Presence | 6 | 0-100 |

#### Effect: Eng 120 (ID: 117440607, Index: 50)
- Origin: ENGL Savage 120 CH4

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Eng 120+ (ID: 117440608, Index: 51)
- Origin: ENGL Savage 120 CH4

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Dizzy VH (ID: 117440613, Index: 52)
- Origin: Diezel VH4 CH3 (blue panel)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Dizzy VH S (ID: 117440614, Index: 53)
- Origin: Diezel VH4 CH4 (silver panel)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Dizzy VH+ (ID: 117440618, Index: 54)
- Origin: Diezel VH4 CH4 (blue panel)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: Dizzy VH+ S (ID: 117440619, Index: 55)
- Origin: Diezel VH4 CH4 (silver panel)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Presence | 1 | 0-100 |
| Volume | 2 | 0-100 |
| Bass | 3 | 0-100 |
| Middle | 4 | 0-100 |
| Treble | 5 | 0-100 |

#### Effect: A BassVT (ID: 117440627, Index: 56)
- Origin: Ampeg SVT

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Bass | 1 | 0-100 |
| Middle | 2 | 0-100 |
| Midrange | 3 | 220Hz, 450Hz, 800Hz, 1.6kHz, 3kHz |
| Treble | 4 | 0-100 |
| Volume | 5 | 0-100 |

#### Effect: Voks Bass (ID: 117440629, Index: 57)
- Origin: VOX AC100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 0 | 0-100 |
| Bass | 1 | 0-100 |
| Treble | 2 | 0-100 |

#### Effect: Calif Bass (ID: 117440631, Index: 58)
- Origin: Mesa/Boogie Bass 400+

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: A BassFT (ID: 134217845, Index: 59)
- Origin: Ampeg B-15

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 0 | 0-100 |
| Bass | 1 | 0-100 |
| Treble | 2 | 0-100 |

#### Effect: F-2Bass (ID: 134217846, Index: 60)
- Origin: Alembic F-2B

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 0 | 0-100 |
| Bright | 1 | Off/On |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: AC Preamp (ID: 134217850, Index: 61)
- Origin: AER Colourizer (EQ f1)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Balance | 2 | 0-100 |
| EQ Freq | 3 | 0-100 |
| EQ Q | 4 | 0-100 |
| EQ Gain | 5 | 0-100 |

#### Effect: AC Preamp 2 (ID: 134217851, Index: 62)
- Origin: AER Colourizer (EQ f2)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 0 | 0-100 |
| Tone | 1 | 0-100 |
| Balance | 2 | 0-100 |
| EQ Freq | 3 | 0-100 |
| EQ Q | 4 | 0-100 |
| EQ Gain | 5 | 0-100 |

### Module: CAB (Index: 5)

#### Effect: Supero 1x6 (ID: 167772160, Index: 0)
- Origin: Binary IR Cab: 39

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Chap 1x8 (ID: 167772161, Index: 1)
- Origin: 31

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Prince 1x10 (ID: 167772162, Index: 2)
- Origin: 32

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: TWD 2x10 (ID: 167772180, Index: 3)
- Origin: 88

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: TWD LUX 1x12 (ID: 167772171, Index: 4)
- Origin: 7

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Dark LUX 1x12 (ID: 167772163, Index: 5)
- Origin: 6

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Twin Verb 2x12 (ID: 167772178, Index: 6)
- Origin: 4

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Custom 2x12 (ID: 167772187, Index: 7)
- Origin: 58

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: B-Man 2x10 (ID: 167772182, Index: 8)
- Origin: 33

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: B-Man 4x10 (ID: 167772190, Index: 9)
- Origin: 10

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Jazz 2x12 (ID: 167772177, Index: 10)
- Origin: 8

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit 1x12 (ID: 167772174, Index: 11)
- Origin: 84

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit GN 2x12 (ID: 167772179, Index: 12)
- Origin: 14

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit LD 4x12 (ID: 167772191, Index: 13)
- Origin: 1

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit TD 4x12 (ID: 167772192, Index: 14)
- Origin: 5

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit MD 4x12 (ID: 167772193, Index: 15)
- Origin: 12

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit GN 4x12 (ID: 167772194, Index: 16)
- Origin: 13

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit 75 4x12 (ID: 167772208, Index: 17)
- Origin: 11

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Brit BK 4x12 (ID: 167772203, Index: 18)
- Origin: 47

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Voks 1x12 (ID: 167772168, Index: 19)
- Origin: 42

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Voks 2x12 (ID: 167772175, Index: 20)
- Origin: 3

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Bog SV 1x12 (ID: 167772166, Index: 21)
- Origin: 55

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Chief 2x12 (ID: 167772176, Index: 22)
- Origin: 9

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Calif Dual 4x12 (ID: 167772196, Index: 23)
- Origin: 2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Calif Star 1x12 (ID: 167772169, Index: 24)
- Origin: 54

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Calif Star 2x12 (ID: 167772185, Index: 25)
- Origin: 53

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Calif 1x12 (ID: 167772172, Index: 26)
- Origin: 38

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Supero 2x12 (ID: 167772183, Index: 27)
- Origin: 40

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Superb 2x12 (ID: 167772184, Index: 28)
- Origin: 52

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Blue 2x12 (ID: 167772189, Index: 29)
- Origin: 99

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Halen 4x12 (ID: 167772195, Index: 30)
- Origin: 25

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Bog 4x12 (ID: 167772197, Index: 31)
- Origin: 16

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Eng 4x12 (ID: 167772198, Index: 32)
- Origin: 17

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Bog Ub 4x12 (ID: 167772199, Index: 33)
- Origin: 20

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Sol 4x12 (ID: 167772200, Index: 34)
- Origin: 22

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Tanger 4x12 (ID: 167772201, Index: 35)
- Origin: 29

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Watt 4x12 (ID: 167772202, Index: 36)
- Origin: 45

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: WAM 4x12 (ID: 167772204, Index: 37)
- Origin: 46

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Humble 4x12 (ID: 167772205, Index: 38)
- Origin: 62

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Dizzy 4x12 (ID: 167772206, Index: 39)
- Origin: 21

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Calif 4x12 (ID: 167772209, Index: 40)
- Origin: 28

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: DV 1x15 (ID: 167772210, Index: 41)
- Origin: 72

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: DV 4x10 (ID: 167772215, Index: 42)
- Origin: 73

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Work 1x15 (ID: 167772211, Index: 43)
- Origin: 67

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Work 4x10 (ID: 167772217, Index: 44)
- Origin: 77

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Calif 2x10 (ID: 167772213, Index: 45)
- Origin: 71

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Mak 2x10 (ID: 167772214, Index: 46)
- Origin: 74

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: A Bass 1x15 (ID: 167772212, Index: 47)
- Origin: 68

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: A Bass 4x10 (ID: 167772216, Index: 48)
- Origin: 69

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: A Bass 8x10 (ID: 167772219, Index: 49)
- Origin: 66

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Hart 4x12 (ID: 167772218, Index: 50)
- Origin: 76

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: D 1 (ID: 167772220, Index: 51)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: D 2 (ID: 167772221, Index: 52)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: OM (ID: 167772222, Index: 53)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Jumbo (ID: 167772223, Index: 54)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Bird (ID: 167772224, Index: 55)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: GA (ID: 167772225, Index: 56)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Classical AC (ID: 167772226, Index: 57)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Mandolin (ID: 167772227, Index: 58)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Fretless Bass (ID: 167772228, Index: 59)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: Double Bass (ID: 167772229, Index: 60)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

### Module: IR (Index: 6)

#### Effect: User IR 1 (ID: 168820736, Index: 0)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 2 (ID: 168820737, Index: 1)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 3 (ID: 168820738, Index: 2)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 4 (ID: 168820739, Index: 3)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 5 (ID: 168820740, Index: 4)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 6 (ID: 168820741, Index: 5)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 7 (ID: 168820742, Index: 6)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 8 (ID: 168820743, Index: 7)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 9 (ID: 168820744, Index: 8)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 10 (ID: 168820745, Index: 9)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 11 (ID: 168820746, Index: 10)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 12 (ID: 168820747, Index: 11)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 13 (ID: 168820748, Index: 12)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 14 (ID: 168820749, Index: 13)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 15 (ID: 168820750, Index: 14)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 16 (ID: 168820751, Index: 15)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 17 (ID: 168820752, Index: 16)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 18 (ID: 168820753, Index: 17)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 19 (ID: 168820754, Index: 18)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

#### Effect: User IR 20 (ID: 168820755, Index: 19)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 1 | 0-100 |
| Low Cut | 5 | Off-20Hz-2000Hz |
| High Cut | 6 | Off-20000Hz-2000Hz |

### Module: EQ (Index: 7)

#### Effect: Guitar EQ 1 (ID: 16777269, Index: 0)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| 125Hz | 0 | -50~0~+50 |
| 400Hz | 1 | -50~0~+50 |
| 800Hz | 2 | -50~0~+50 |
| 1.6kHz | 3 | -50~0~+50 |
| 4kHz | 4 | -50~0~+50 |
| Volume | 5 | 0-100 |

#### Effect: Guitar EQ 2 (ID: 16777270, Index: 1)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| 100Hz | 0 | -50~0~+50 |
| 500Hz | 1 | -50~0~+50 |
| 1kHz | 2 | -50~0~+50 |
| 3kHz | 3 | -50~0~+50 |
| 6kHz | 4 | -50~0~+50 |
| Volume | 5 | 0-100 |

#### Effect: Bass EQ 1 (ID: 16777273, Index: 2)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| 33Hz | 0 | -50~0~+50 |
| 150Hz | 1 | -50~0~+50 |
| 600Hz | 2 | -50~0~+50 |
| 2kHz | 3 | -50~0~+50 |
| 8kHz | 4 | -50~0~+50 |
| Volume | 5 | 0-100 |

#### Effect: Bass EQ 2 (ID: 16777274, Index: 3)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| 50Hz | 0 | -50~0~+50 |
| 120Hz | 1 | -50~0~+50 |
| 400Hz | 2 | -50~0~+50 |
| 800Hz | 3 | -50~0~+50 |
| 4.5kHz | 4 | -50~0~+50 |
| Volume | 5 | 0-100 |

#### Effect: Calif EQ  (ID: 16777276, Index: 4)
- Origin: Mesa Boogie 5-band EQ

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| 80Hz | 0 | -50~0~+50 |
| 240Hz | 1 | -50~0~+50 |
| 750Hz | 2 | -50~0~+50 |
| 2.2kHz | 3 | -50~0~+50 |
| 6.6kHz | 4 | -50~0~+50 |

### Module: MOD (Index: 8)

#### Effect: E-Chorus (ID: 67108865, Index: 0)
- Origin: Boss CE-1 (Ch Mode)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Sync | 3 | Off/On |

#### Effect: D-Chorus (ID: 67108866, Index: 1)
- Origin: Boss DC-2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mode | 0 | 1, 2, 3, 4 |

#### Effect: B-Chorus (ID: 67108872, Index: 2)
- Origin: Boss CE-2B

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Sync | 3 | Off/On |

#### Effect: M-Chorus (ID: 67108879, Index: 3)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Filter | 2 | 0-100 |
| Depth L | 3 | 0-100 |
| Depth C | 4 | 0-100 |
| Depth R | 5 | 0-100 |
| Sync | 6 | Off/On |

#### Effect: Flanger (ID: 67108881, Index: 4)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Pre Delay | 2 | 0-100 |
| Feedback | 3 | 0-100 |
| Sync | 4 | Off/On |

#### Effect: Flanger N (ID: 67108883, Index: 5)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Pre Delay | 2 | 0-100 |
| Feedback | 3 | 0-100 |
| Sync | 4 | Off/On |

#### Effect: Trem Jet (ID: 67108884, Index: 6)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Flg Depth | 0 | 0-100 |
| Flg Rate | 1 | 0.10Hz-10.00Hz |
| Feedback | 2 | 0-100 |
| Trm Depth | 3 | 0-100 |
| Trm Rate | 4 | 0.10Hz-10.00Hz |
| Flg Sync | 5 | Off/On |
| Trm Sync | 6 | Off/On |

#### Effect: Bass Jet (ID: 67108882, Index: 7)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Pre Delay | 2 | 0-100 |
| Feedback | 3 | 0-100 |
| Sync | 4 | Off/On |

#### Effect: Vibrato (ID: 67108887, Index: 8)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Sync | 3 | Off/On |

#### Effect: BBD Roto (ID: 67108885, Index: 9)
- Origin: Boss VB-2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Sync | 2 | Off/On |

#### Effect: CE-Roto (ID: 67108886, Index: 10)
- Origin: Boss CE-1 (Vib Mode)

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Sync | 3 | Off/On |

#### Effect: Phaser (ID: 67108889, Index: 11)
- Origin: MXR Phase 90

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Rate | 0 | 0.10Hz-10.00Hz |
| Sync | 1 | Off/On |

#### Effect: BBD Phaser (ID: 67108890, Index: 12)
- Origin: Boss PH-1

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Sync | 2 | Off/On |

#### Effect: Phaser ST (ID: 67108891, Index: 13)
- Origin: EH Small Stone

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Color | 0 | Warm, Sharp |
| Rate | 1 | 0.10Hz-10.00Hz |
| Sync | 2 | Off/On |

#### Effect: Pan Phaser (ID: 67108894, Index: 14)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Phs Depth | 0 | 0-100 |
| Phs Rate | 1 | 0.10Hz-10.00Hz |
| Pan Depth | 2 | 0-100 |
| Pan Rate | 3 | 0.10Hz-10.00Hz |
| Phs Sync | 4 | Off/On |
| Pan Sync | 5 | Off/On |

#### Effect: Vibe (ID: 67108895, Index: 15)
- Origin: Voodoo Lab Micro Vibe

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Sync | 2 | Off/On |

#### Effect: U-Vibe (ID: 67108896, Index: 16)
- Origin: Shin-Ei Uni-Vibe

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Mode | 3 | Chorus, Vibrato |
| Sync | 4 | Off/On |

#### Effect: Tremolo (ID: 67108897, Index: 17)
- Origin: Demeter Tremulator

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Sync | 2 | Off/On |

#### Effect: Sine Trem (ID: 67108902, Index: 18)
- Origin: Original: GP-100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Sync | 3 | Off/On |

#### Effect: Triangle Trem (ID: 67108903, Index: 19)
- Origin: Original: GP-100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Sync | 3 | Off/On |

#### Effect: Bias Trem (ID: 67108904, Index: 20)
- Origin: Original: GP-100

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Depth | 0 | 0-100 |
| Rate | 1 | 0.10Hz-10.00Hz |
| Volume | 2 | 0-100 |
| Sync | 3 | Off/On |
| Bias | 4 | 0-100 |

#### Effect: Detune (ID: 16777257, Index: 21)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Detune | 0 | -50 cents - +50 cents |
| Wet | 1 | 0-100 |
| Dry | 2 | 0-100 |

#### Effect: Lofi Bit (ID: 16777262, Index: 22)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Krush | 1 | 0-100 |
| Bit | 2 | 0-100 |
| Hi Cut | 3 | 0-100 |
| Lo Cut | 4 | 0-100 |

### Module: DLY (Index: 9)

#### Effect: Warm (ID: 184549377, Index: 0)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | 0-100 |
| Sync | 3 | Off/On |
| Trail | 4 | Off/On |

#### Effect: Pure (ID: 184549376, Index: 1)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | 0-100 |
| Sync | 3 | Off/On |
| Trail | 4 | Off/On |

#### Effect: Mag (ID: 184549378, Index: 2)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | 0-100 |
| Sync | 3 | Off/On |
| Trail | 4 | Off/On |

#### Effect: Tube (ID: 184549387, Index: 3)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | 0-100 |
| Sync | 3 | Off/On |
| Trail | 4 | Off/On |

#### Effect: BBD (ID: 184549405, Index: 4)
- Origin: FAS BBD Stereo

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | -100-+100 |
| Time R% | 3 | 1.0%~100.0% |
| Spread | 4 | -100-+100 |
| Level | 5 | 0-100 |
| Sync | 6 | Off/On |
| Trail | 7 | Off/On |

#### Effect: Ping Pong (ID: 184549380, Index: 5)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 2 | 20ms-4000ms |
| Feedback | 1 | 0-100 |
| Sync | 3 | Off/On |
| Trail | 4 | Off/On |

#### Effect: Slapback (ID: 184549381, Index: 6)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-300ms |
| Feedback | 2 | 0-100 |
| Trail | 4 | Off/On |

#### Effect: Sweep (ID: 184549382, Index: 7)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 2 | 20ms-4000ms |
| Feedback | 1 | 0-100 |
| Sweep Depth | 3 | 0-100 |
| Sweep Rate | 4 | 0-100 |
| Sweep Sync | 5 | Off/On |
| Time Sync | 6 | Off/On |
| Trail | 7 | Off/On |

#### Effect: Ring (ID: 184549385, Index: 8)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Dly Mix | 0 | 0-100 |
| Time | 2 | 20ms-4000ms |
| Feedback | 1 | 0-100 |
| Ring Mix | 3 | 0-100 |
| Freq | 4 | 0-100 |
| Tone | 5 | 0-100 |
| Sync | 6 | Off/On |
| Trail | 7 | Off/On |

#### Effect: Multi Tape (ID: 184549388, Index: 9)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | 0-100 |
| Tone | 3 | 0-100 |
| Mode | 4 | 1-12 |
| Sync | 5 | Off/On |
| Trail | 6 | Off/On |

#### Effect: Sweet (ID: 184549389, Index: 10)
- Origin: Boss DM-2

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | 0-100 |
| Sync | 3 | Off/On |
| Trail | 4 | Off/On |

#### Effect: 999 Echo (ID: 184549394, Index: 11)
- Origin: Maxon AD999

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | 0-100 |
| Sync | 3 | Off/On |
| Trail | 4 | Off/On |

#### Effect: Rack (ID: 184549396, Index: 12)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 2 | 20ms-4000ms |
| Feedback | 1 | 0-100 |
| Mod | 3 | 0-100 |
| Tone | 4 | 0-100 |
| Sync | 5 | Off/On |
| Trail | 6 | Off/On |

#### Effect: Lo-Fi (ID: 184549414, Index: 13)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | -100-+100 |
| Crush | 3 | 0-100 |
| Bit | 4 | 0-100 |
| Sync | 6 | Off/On |
| Trail | 7 | Off/On |

#### Effect: Reverse (ID: 184549416, Index: 14)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | -100-+100 |
| Level | 3 | 0-100 |
| Sync | 4 | Off/On |
| Trail | 5 | Off/On |

#### Effect: Eko D (ID: 184549379, Index: 15)
- Origin: Original

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix A | 0 | 0-100 |
| Time A | 2 | 20ms-4000ms |
| Feedback A | 1 | -100-+100 |
| Mix B | 3 | 0-100 |
| Time B | 5 | 20ms-4000ms |
| Feedback B | 4 | -100-+100 |
| A Sync | 6 | Off/On |
| B Sync | 7 | Off/On |
| Trail | 8 | Off/On |

#### Effect: Ice Delay (ID: 184549420, Index: 16)
- Origin: TC Electronic  2290 Dynamic Digital Delay

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Time | 1 | 20ms-4000ms |
| Feedback | 2 | -100-+100 |
| Mod Rate | 3 | 0.10Hz-10.00Hz |
| Mod Depth | 4 | 0-100 |
| Phase Reverse | 5 | None, Left, Right, Both |
| Low Cut | 6 | Off, 100Hz, 200Hz, 400Hz |
| High Cut | 7 | 33kHz, 8kHz, 4kHz, 2kHz |
| Level | 8 | 0-100 |
| Sync | 9 | Off/On |
| Trail | 10 | Off/On |

### Module: RVB (Index: 10)

#### Effect: Studio (ID: 201326603, Index: 0)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-300ms |
| Decay | 2 | 0-100 |
| Low Damp | 3 | 0-100 |
| Hi Damp | 4 | 0-100 |
| Mod | 5 | 0-100 |
| Trail | 6 | Off/On |

#### Effect: Club (ID: 201326604, Index: 1)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-300ms |
| Decay | 2 | 0-100 |
| Low Damp | 3 | 0-100 |
| Hi Damp | 4 | 0-100 |
| Mod | 5 | 0-100 |
| Trail | 6 | Off/On |

#### Effect: Room (ID: 201326592, Index: 2)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-100ms |
| Decay | 2 | 0-100 |
| Trail | 3 | Off/On |

#### Effect: Hall (ID: 201326593, Index: 3)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-100ms |
| Decay | 2 | 0-100 |
| Trail | 3 | Off/On |

#### Effect: Church (ID: 201326594, Index: 4)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-100ms |
| Decay | 2 | 0-100 |
| Trail | 3 | Off/On |

#### Effect: Plate (ID: 201326595, Index: 5)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Decay | 1 | 0-100 |
| High Damp | 2 | 0-100 |
| Trail | 3 | Off/On |

#### Effect: Spring (ID: 201326596, Index: 6)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Decay | 1 | 0-100 |
| Tone | 2 | 0-100 |
| Trail | 3 | Off/On |

#### Effect: Sky (ID: 201326598, Index: 7)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Decay | 1 | 0-100 |
| Trail | 2 | Off/On |

#### Effect: Sea (ID: 201326599, Index: 8)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Decay | 1 | 0-100 |
| Trail | 2 | Off/On |

#### Effect: Mod Reverb  (ID: 201326600, Index: 9)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-100ms |
| Decay | 2 | 0-100 |
| Lo End | 3 | -50~0~+50 |
| Hi End | 4 | -50~0~+50 |
| Trail | 5 | Off/On |

#### Effect: Shimmer (ID: 201326601, Index: 10)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-100ms |
| Decay | 2 | 0-100 |
| Lo End | 3 | -50~0~+50 |
| Hi End | 4 | -50~0~+50 |
| Trail | 5 | Off/On |

#### Effect: Haze (ID: 201326613, Index: 11)
- Origin: Original: Ampero

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Mix | 0 | 0-100 |
| Pre Delay | 1 | 0ms-300ms |
| Decay | 2 | 0-100 |
| Low Damp | 3 | 0-100 |
| Hi Damp | 4 | 0-100 |
| Mod | 5 | 0-100 |
| Trail | 6 | Off/On |

### Module: CLONE (Index: 11)

#### Effect: Clone 1 (ID: 251658240, Index: 0)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 2 (ID: 251658241, Index: 1)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 3 (ID: 251658242, Index: 2)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 4 (ID: 251658243, Index: 3)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 5 (ID: 251658244, Index: 4)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 6 (ID: 251658245, Index: 5)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 7 (ID: 251658246, Index: 6)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 8 (ID: 251658247, Index: 7)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 9 (ID: 251658248, Index: 8)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

#### Effect: Clone 10 (ID: 251658249, Index: 9)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Gain | 0 | 0-100 |
| Volume | 1 | 0-100 |
| Bass | 2 | 0-100 |
| Middle | 3 | 0-100 |
| Treble | 4 | 0-100 |

### Module: FXLOOP (Index: 12)

#### Effect: FX Loop (ID: 100663296, Index: 0)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Send Level | 0 | 0-100 |
| Return Level | 1 | 0-100 |
| Mix | 2 | 0-100 |

### Module: FXSND (Index: 13)

#### Effect: SND (ID: 100663297, Index: 0)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Send Level | 0 | 0-100 |

### Module: FXRTN (Index: 14)

#### Effect: RTN (ID: 100663298, Index: 0)
- Origin: 

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Return Level | 0 | 0-100 |
| Mix | 1 | 0-100 |

### Module: VOL (Index: 15)

#### Effect: Volume (ID: 100663299, Index: 0)
- Origin: Volume

| Parameter | ID | Range / Type |
| :--- | :--- | :--- |
| Volume | 0 | 0-100 |


---
## 3. Communication Technical Specification
* **CmdCode for Parameter Write**: `0x01050001` (Preset Algorithm Parameter Value)
* **Addressing**: `SlotID` (Int32 LE), `ParaID` (Int32 LE), `Value` (Float32 LE).
