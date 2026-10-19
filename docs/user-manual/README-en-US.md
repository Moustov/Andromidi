# myAgenticTeam v3 — User Manual

**Table of Contents**

- [Summary](#summary)
- [Overview & Project Utility](#overview-project-utility)
  - [Why](#why)
  - [Offered Solution](#offered-solution)
- [FinOps & VSM Overview](#finops-vsm-overview)
  - [1. OpenCode FinOps/VSM Plugin (Telemetry Capture)](#1-opencode-finopsvsm-plugin-telemetry-capture)
  - [2. Statistics Utility (`/mat-stats` / `npm run stats`)](#2-statistics-utility-mat-stats-npm-run-stats)
  - [Summary Workflow Matrix](#summary-workflow-matrix)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [1. Build & Runtime Environment](#1-build-runtime-environment)
  - [2. Android SDK Specifications](#2-android-sdk-specifications)
  - [3. Hardware Features & Permissions](#3-hardware-features-permissions)
  - [4. Core Libraries & Dependencies](#4-core-libraries-dependencies)
  - [5. Testing Dependencies](#5-testing-dependencies)
  - [Procedure](#procedure)
  - [Step-by-Step Procedure to Enable and Run the Project](#step-by-step-procedure-to-enable-and-run-the-project)
  - [Verification](#verification)
  - [Project Verification Test](#project-verification-test)
- [Main workflow](#main-workflow)
- [Main Use Cases](#main-use-cases)
- [Main Use Cases for App Activities](#main-use-cases-for-app-activities)
  - [1. `MainActivity` (`activity_main.xml`)](#1-mainactivity-activity_mainxml)
  - [2. `SongsActivity` (`activity_songs.xml`)](#2-songsactivity-activity_songsxml)
- [Troubleshooting](#troubleshooting)

Andromidi
===


## Summary

## Overview & Project Utility

### Why

In the era of AI-driven software development, autonomous coding agents can generate code and test suites at blistering speeds. However, this velocity introduces significant systemic risks:

* **Confirmation Bias & "Canada Dry" Code:** AI agents that write production code often write unit tests to validate their own work. This produces code that appears robust on the surface but lacks true resilience, edge-case coverage, and fault tolerance.
* **Requirement Drift at Scale:** When AI agents execute ambiguous or flawed requirements without guardrails, they build flawed systems at scale. Without executable specifications, AI agents quickly drift from true business intent.
* **Uncontrolled Costs & Opaque Performance:** Agentic tool execution involves complex LLM calls across multiple providers. Without proper observability, teams suffer from unpredictable token consumption, unknown API costs, and unmonitored flow bottlenecks.
* **Traceability & Governance Gaps:** As agents rapidly iterate on features, maintaining alignment between original specifications, implemented code, and test coverage becomes nearly impossible without automated traceability.

---

### Offered Solution

**myAgenticTeam (`mat`)** is a deterministic, spec-driven development framework and telemetry engine designed for governing AI agent workflows in [OpenCode](https://opencode.ai). It turns AI agents from unguided code generators into structured, accountable engineering partners through:

1. **Spec-Driven Workflows:** Leverages OpenSpec-compatible change artifacts (proposals, specs, design, tasks) as the single source of truth. Every change is planned, implemented against strict requirement scenarios, and automatically merged back into main specifications upon archiving.
2. **FinOps & Value Stream Mapping (VSM) Telemetry:** Captures execution events via OpenCode lifecycle plugins to track total token consumption, estimated USD costs, process vs. wait times, flow efficiency, and First Pass Yield (FPY) across models, stories, and commands (`/mat-stats`).
3. **Automated Requirement Traceability:** Enforces strict requirement mapping (`[REQ-*]`) across Unit Tests (UT) and Integration Tests (IT), ensuring 100% verification coverage between test cases and specification IDs (`/mat-add-trace-on-ut-and-it`, `/mat-ensure-tu-ti-coverage`).
4. **Live Architecture & Governance Tooling:** Automatically generates component and class architecture diagrams (`/mat-archi-report`), risk analyses (`/mat-risks`), test plans (`/mat-test-plan`), and interactive project documentation (`/mat-user-manual`).
5. **Reusable Slash-Commands & Skills:** Equips developers and test engineers with modular commands and skills to inspect, benchmark, and direct AI agent fleets safely and transparently.

## FinOps & VSM Overview

The project incorporates an integrated **FinOps (Financial Operations)** and **VSM (Value Stream Mapping)** framework designed to monitor LLM token usage, estimate execution costs, track workflow efficiency, and audit test coverage. This architecture is driven by a two-stage pipeline: automated telemetry capture via an **OpenCode Plugin** and aggregation/reporting via the **Statistics Utility (`/mat-stats`)**.

---

### 1. OpenCode FinOps/VSM Plugin (Telemetry Capture)

The OpenCode FinOps plugin acts as an automated event collector running silently within the developer's OpenCode workspace session. It hooks into execution lifecycles to record granular event data without affecting core workspace runtime performance.

#### **Key Architecture & Capabilities**

* **Lifecycle Event Interception:**
  * Hooks into `tool.execute.before` and `tool.execute.after` to log execution timestamps, tool names, execution success/failure status, and session IDs (`batch_id`).
  * Intercepts `chat.message` and `event` streams to capture token consumption data for LLM interactions.
  * Intercepts `shell.env` to ensure proper environment management (e.g., prepending Python `.venv` paths when detected).

* **Cost Estimation & Model Rate Engine:**
  * **Token Breakdown:** Tracks prompt/input tokens, candidate/output tokens, reasoning/thoughts tokens, and cached tokens.
  * **USD Cost Calculation:** Computes `estimated_cost_usd` accurate to 7 decimal places using model-specific rate tables.
  * **Fallbacks & Handlers:**
    * *Unknown models within a known provider:* Uses the provider's average rates.
    * *Unknown models/providers:* Logs `-1.00` as a sentinel value indicating unknown cost.
    * *Local model runners (e.g., Ollama):* Resolved as free (`0.00 USD`).
    * *Copilot / GitHub aliases:* Automatically mapped to equivalent OpenAI pricing models.

* **Value Stream Mapping (VSM) & Rework Tracking:**
  * Tracks step timing metrics: `process_time_ms`, `wait_time_ms`, and session `lead_time_ms`.
  * Monitors `retry_count` per step/task within a session (`batch_id`) to analyze rework loops and First Pass Yield (FPY).

* **Story & Demarcation Tracking:**
  * Emits `story_transition` records whenever a developer switches active OpenSpec stories mid-session, maintaining clear metric boundaries across feature branches.

* **Test Execution Telemetry:**
  * Recognizes test-runner executions and extracts test metrics (`tests_run`, `tests_failed`, `tests_duration_ms`).

* **Telemetry Log Destination:**
  * Appends structured JSON lines (`.jsonl`) to `.opencode/finops-vsm/vsm_metrics.jsonl` (configurable via `logDir`).

---

### 2. Statistics Utility (`/mat-stats` / `npm run stats`)

The Statistics Utility processes the raw JSONL telemetry generated by the plugin to produce aggregated Markdown reports and raw CSV exports for analysis, CI/CD reporting, and dashboards.

#### **Command Execution**

```bash
# Run via project script
npm run stats

# Or directly via node runner
node src/mat-stats/run-stats.mjs [options]
```

#### **Key Command Options**
* `--file <path>`: Specifies custom input JSONL file path (default: `.opencode/finops-vsm/vsm_metrics.jsonl`).
* `--json`: Outputs aggregated metrics directly as JSON to `stdout`.
* `--lang <BCP47>`: Language locale for Markdown report generation (e.g., `en`, `fr`).

#### **Generated Output Artifacts**

The utility outputs structured statistics to `docs/stats/`:

1. **`stats-on-jobs.md` (FinOps & VSM KPI Report):**
   * **Global KPI Summary:** Total USD cost, total token consumption, overall First Pass Yield (FPY), average process time, wait time, and flow efficiency.
   * **Multi-Dimensional Breakdowns:**
     * **By Model:** Token distribution and costs across LLM providers/models.
     * **By Story:** Cost and effort invested per OpenSpec story/feature.
     * **By Command:** Resource consumption per automated script or slash command (`/mat-*`).

2. **`stats-on-testing.md` (Test Coverage & Traceability Report):**
   * Classifies source test coverage (derived from `@Coverage` and `@OpenSpec` test docstrings) into scenario coverage, requirement coverage, unmapped tests, and unresolved requirement backlog.

3. **CSV Data Exports:**
   * `stats-summary.csv` — Global execution metrics summary.
   * `stats-models.csv` — Model usage breakdown.
   * `stats-stories.csv` — Feature/Story cost distribution.
   * `stats-commands.csv` — Command execution performance and cost breakdown.

---

### Summary Workflow Matrix

| Stage | Trigger / Engine | Input Data | Output Artifacts |
| :--- | :--- | :--- | :--- |
| **Capture Phase** | OpenCode FinOps Plugin (`tool.execute`, `chat.message`) | Real-time tool execution & LLM token events | `.opencode/finops-vsm/vsm_metrics.jsonl` |
| **Analysis Phase** | `/mat-stats` (`node src/mat-stats/run-stats.mjs`) | `vsm_metrics.jsonl` + Test Source Annotations | `docs/stats/stats-on-jobs.md`<br>`docs/stats/stats-on-testing.md`<br>`docs/stats/*.csv` |

## Installation

### Prerequisites

#### Development
Here is the markdown snippet formatted below a `####` level heading:

##### Required Components and Versions for Android Development

To develop, build, and run the **Andromidi** project properly on Android, ensure your development environment satisfies the following components and version requirements:

###### 1. Build Tools & Runtime
* **JDK (Java Development Kit):** Java 11 (Source/Target Compatibility set to `11`)
* **Gradle Wrapper:** `8.11.1`
* **Android Gradle Plugin (AGP):** `8.10.0-alpha05`

###### 2. Android SDK Configuration
* **Compile SDK (`compileSdk`):** API Level `35` (Android 15)
* **Target SDK (`targetSdk`):** API Level `35` (Android 15)
* **Minimum SDK (`minSdk`):** API Level `28` (Android 9.0 Pie)

###### 3. Required Hardware Features & Permissions
* **USB Host Support:** Hardware feature `android.hardware.usb.host` (required for USB MIDI interfacing)
* **Permissions:**
  * `android.permission.MIDI`
  * `android.permission.USB_PERMISSION`
  * `android.permission.READ_EXTERNAL_STORAGE`

###### 4. Project Dependencies & Libraries
* **AndroidX Libraries:**
  * `androidx.appcompat:appcompat` — `1.7.0`
  * `androidx.activity:activity` — `1.10.1`
  * `androidx.constraintlayout:constraintlayout` — `2.2.1`
  * `androidx.gridlayout:gridlayout` — `1.1.0`
  * `androidx.navigation:navigation-fragment` — `2.9.0`
  * `androidx.navigation:navigation-ui` — `2.9.0`
* **Material Design:**
  * `com.google.android.material:material` — `1.12.0`
* **JSON Parsing:**
  * `com.google.code.gson:gson` — `2.11.0`

###### 5. Testing Frameworks
* **JUnit:** `4.13.2`
* **AndroidX Test JUnit Extension:** `1.2.1`
* **Espresso Core:** `3.6.1`
* **Test Runner:** `androidx.test.runner.AndroidJUnitRunner`

#### Running
#### Android Requirements and Dependencies

To build and run the project properly on Android, the following components, SDK versions, hardware features, and dependencies are required:

### 1. Build & Runtime Environment
* **Java Development Kit (JDK):** Version **11** (Source & Target compatibility set to `JavaVersion.VERSION_11`)
* **Gradle Build Tool:** Version **8.11.1**
* **Android Gradle Plugin (AGP):** Version **8.10.0-alpha05**

---

### 2. Android SDK Specifications
* **Compile SDK:** Version **35** (Android 15)
* **Target SDK:** Version **35** (Android 15)
* **Minimum SDK:** Version **28** (Android 9.0 Pie)

---

### 3. Hardware Features & Permissions
* **Hardware Feature:**
  * `android.hardware.usb.host` (USB Host mode support required to interact with hardware like Sonicake Matribox II Pro)
* **Declared Permissions:**
  * `android.permission.MIDI`
  * `android.permission.USB_PERMISSION`
  * `android.permission.READ_EXTERNAL_STORAGE`

---

### 4. Core Libraries & Dependencies
* **AndroidX & UI Libraries:**
  * `androidx.appcompat:appcompat:1.7.0`
  * `androidx.activity:activity:1.10.1`
  * `androidx.constraintlayout:constraintlayout:2.2.1`
  * `androidx.gridlayout:gridlayout:1.1.0`
  * `androidx.navigation:navigation-fragment:2.9.0`
  * `androidx.navigation:navigation-ui:2.9.0`
  * `com.google.android.material:material:1.12.0`
* **Utility Libraries:**
  * `com.google.code.gson:gson:2.11.0`

---

### 5. Testing Dependencies
* **Unit Testing:** `junit:junit:4.13.2`
* **Android Instrumentation Testing:**
  * `androidx.test.ext:junit:1.2.1`
  * `androidx.test.espresso:espresso-core:3.6.1`

### Procedure

### Step-by-Step Procedure to Enable and Run the Project

Follow these steps to set up, build, install, and execute the **myAgenticTeam (v3)** platform properly.

---

#### Step 1: Verify Prerequisites
Ensure your development environment meets the following requirements:
- **Node.js**: Version 18.0.0 or higher installed (v20+ recommended). Check with `node -v`.
- **Git**: Installed and accessible in your command line. Check with `git --version`.
- **Shell Environment**:
  - **Windows**: PowerShell 5.1+ or PowerShell 7+.
  - **Linux / macOS / WSL**: Bash or Zsh shell.

---

#### Step 2: Navigate to the Root Directory
Open your terminal and navigate to the extracted or cloned repository root:

```bash
cd /path/to/myagenticteam-v3.12.3
```

*(On Windows PowerShell, for example: `cd C:\dev\Andromidi\myagenticteam-v3.12.3`)*

---

#### Step 3: Install Core Dependencies and Build Modules
1. Move into the `core` directory:
   ```bash
   cd core
   ```
2. Install required Node.js dependencies (`yaml`, `zod`, `oxlint`, etc.):
   ```bash
   npm install
   ```
3. Build the internal TypeScript/ESM scripts and distribution binaries:
   ```bash
   npm run build
   ```
4. Return to the root directory:
   ```bash
   cd ..
   ```

---

#### Step 4: Run the Installation Script
Run the automated installer script from the root directory to deploy components into `~/.myagenticteam/` and configure the `mat` launcher on your system `PATH`.

- **On Windows (PowerShell)**:
  ```powershell
  .\install.ps1
  ```

- **On Linux / macOS / Git Bash / WSL**:
  ```bash
  chmod +x install.sh
  ./install.sh
  ```

---

#### Step 5: Verify the Installation
1. Close your current terminal and **open a fresh terminal window** to reload system environment variables and `PATH`.
2. Verify that the `mat` command is accessible globally:
   ```bash
   mat --version
   ```
3. Check available commands and online help:
   ```bash
   mat --help
   ```
4. *(Optional)* Run the internal test suite to verify component integrity:
   ```bash
   cd core
   npm test
   ```

---

#### Step 6: Initialize and Run in a Target Workspace
To start using `myAgenticTeam` inside any target project repository:

1. Navigate to your target project folder:
   ```bash
   cd /path/to/your-target-project
   ```
2. Initialize `myAgenticTeam` configurations, commands, and skills:
   ```bash
   mat init
   ```
3. Run any MAT workflow or analytics command, such as:
   ```bash
   mat stats
   mat workflow-live
   ```


### Verification

### Project Verification Test

To verify that **myAgenticTeam (MAT v3.12.3)** is set up correctly and running properly in this environment, you can run the following test commands from the project root directory.

---

#### 1. Test CLI Help Surface (`mat.mjs`)

Execute the main CLI bundle using Node.js to verify module dependencies and CLI command routing:

```bash
node core/bin/mat.mjs --help
```

**Expected Output:**
```text
mat — myAgenticTeam v3 CLI

Usage:
  mat <command> [args...]

Commands:
  add-trace
  archi-report
  build-release
  ensure-tu-ti-coverage
  init
  mat-add-trace-on-ut-and-it
  rest
  risks
  spec-ids
  stats
  swiss-cheese
  test-plan
  test-referential
  user-manual
  workflow-live
  workflow-on-demand

Run `mat <command> --help` for command-specific help.
```

---

#### 2. Run Automated Unit Tests

Run the built-in Node test runner on a core module test suite to verify module execution and runtime parsing:

```bash
node --test core/src/mat-squash-to-openapi-converter/extract-get-icons.test.ts
```

**Expected Output:**
```text
▶ extractIconOccurrences
  ✔ finds every GET icon, in the table of contents and in the body
  ✔ resolves the anchor, the title and the request line of a body occurrence
  ✔ ignores the table of contents on demand
  ✔ can target another verb icon, and still requires both attributes to match
  ✔ reports a line and column that actually contain the tag
✔ extractIconOccurrences
▶ buildReport
  ✔ splits the totals between the table of contents and the body
✔ buildReport
▶ html-utils
  ✔ measures the inner range of a heading that contains a nested span
  ✔ returns the anchor that is open at a given offset
✔ html-utils
ℹ tests 8
ℹ suites 3
ℹ pass 8
ℹ fail 0
ℹ cancelled 0
ℹ skipped 0
ℹ todo 0
```

---

#### 3. Test Subcommand Invocation (`spec-ids`)

Run a specific subcommand help flag to verify subcommand loading and parameter parsing:

```bash
node core/bin/mat.mjs spec-ids --help
```

**Expected Output:**
```text
Usage: node run-spec-ids.mjs <mode> [--specs-root <PATH>]

Assigns or validates the requirement identifiers of an OpenSpec specs tree.

Modes:
  assign     Assign an identifier to every requirement that has none, then
             renumber the affected capability files.
  validate   Report every requirement whose identifier is missing, malformed
             or duplicated. Exits 1 when violations are found, 0 otherwise.

Options:
  -h, --help
              Print this help and exit 0. Tested before the mode and before the
              specs root are validated, so it never fails for want of an input.
  --specs-root <PATH>
              Root of the specs tree (default: openspec/specs). Both the
              space-separated and the equals-separated forms are accepted.
```

## Main workflow

## Main Use Cases

## Main Use Cases for App Activities

The application includes two main Android activities defined in `app/src/main/AndroidManifest.xml` and tied to layouts in `app/src/main/res/layout/`: **`MainActivity`** (`activity_main.xml`) and **`SongsActivity`** (`activity_songs.xml`). Below is a breakdown of the primary use cases supported by each activity and its associated resources.

---

### 1. `MainActivity` (`activity_main.xml`)
`MainActivity` serves as the primary control center and dashboard for interfacing with the Matribox II Pro MIDI multi-effects unit over USB.

* **Looper Control**:
  * **Toggle Looper Menu**: Access or dismiss the looper interface on the MIDI device (`@id/looper_imgbtn`).
  * **Loop Playback**: Play or pause loop audio playback with active status indication (`@id/play_loop_imgbtn`).
  * **Delete Loop**: Clear the current audio loop recording (`@id/del_loop_imgbtn`).

* **Drum Machine & Rhythm Control**:
  * **Toggle Drum Menu**: Switch to and control the drum machine interface (`@id/drum_imgbtn`).
  * **Drum Playback**: Start and stop drum pattern playback (`@id/play_drum_imgbtn`).
  * **Tap Tempo**: Set the musical tempo on the fly by tapping along to the rhythm (`@id/tap_imgbtn`).

* **Preset & Patch Navigation**:
  * **Bank Surfing (+10 / -10)**: Fast-forward or reverse through bank presets in steps of 10 (`@id/next_preset_imgbtn` and `@id/prev_preset_imgbtn`).
  * **Tempo Sync**: Tap the bank/BPM view (`@id/bank_id_txtvw`) to transmit tempo sync commands directly to the hardware unit.

* **Real-time Sound Parameter Tweaking**:
  * **Effect Knobs Adjustment**: Dynamically adjust parameters (`knob1`, `knob2`, `knob3`) using interactive SeekBars (`@id/seekBar1`, `@id/seekBar2`, `@id/seekBar3`).
  * **Master Preset Volume**: Control output preset level using a vertical volume slider (`@id/volume_preset`).

* **MIDI Connection & System Status**:
  * **Device Identification**: Monitor connected MIDI hardware (displays device name, manufacturer, and serial number via `@id/midi_device_txtvw`).
  * **Re-connect / Refresh MIDI**: Re-initialize and update USB MIDI connection listeners (`@id/midi_imgbtn`).
  * **Keep Screen Awake**: Keeps the mobile display active continuously during live performance sessions.

* **Navigation**:
  * **Open Song Library**: Triggers `SongsActivity` via the "Songs" button (`@id/songs_btn`).

---

### 2. `SongsActivity` (`activity_songs.xml`)
`SongsActivity` provides setlist management and song preset loading functionality.

* **Setlist / Song File Import**:
  * **JSON Document Picker**: Opens the Android system file picker (`ACTION_OPEN_DOCUMENT`) to let users select and import JSON files containing song configurations, bank numbers, and BPMs.

* **Song List Browsing**:
  * **Interactive Song List**: Displays imported songs in a `ListView` (`@id/songs_lv`) using custom list item templates (`item_layout.xml`) showing song titles, bank IDs, and target BPMs.

* **Song Selection & Automated Preset Loading**:
  * **Bank Switching**: Selecting a song from the list returns the selected song's title, Bank ID, and BPM to `MainActivity`, which automatically sends MIDI bank step commands (`sendBankNextWaitMode` / `sendBankPrevWaitMode`) to change the Matribox II Pro hardware patch to match the song.


## Troubleshooting

| Symptom | Remedy |
| :--- | :--- |

---

---

Generated on : 2026-10-19 18:01:16
*powered by myAgenticTeam v3*
