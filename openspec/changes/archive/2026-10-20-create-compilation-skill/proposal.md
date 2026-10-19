## Why

To automate the development cycle and streamline testing on physical Android devices, we need a dedicated automation/compilation workflow (or command/skill descriptor) that automatically updates the version string, builds the project, installs the APK, launches the app via ADB, and inspects runtime logs.

## What Changes

- Create a custom command/skill descriptor (e.g., `.opencode/commands/mat-compile.md` or similar) defining the 5-step compilation and deployment workflow:
  1. Increment or update displayed version string.
  2. Compile debug APK using Gradle (`gradlew assembleDebug`).
  3. Deploy/install APK on connected device (`gradlew installDebug` or `adb install`).
  4. Launch mobile app (`adb shell monkey`).
  5. Capture and analyze runtime logs (`adb logcat`).

## Capabilities

### New Capabilities
- `compilation-workflow`: Automated build, deployment, launch, and log analysis workflow.

### Modified Capabilities
- none

## Impact

- Workflow: Adds automated tooling for developer feedback loops.
- Files: New command descriptor file under `.opencode/commands/`.
