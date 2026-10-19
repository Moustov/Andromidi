# /mat-compile

Compile, deploy, launch, and inspect runtime logs for the Andromidi Android application.

## Workflow
1. Update the displayed version string in `MainActivity.java` (e.g., incrementing build timestamp or version number).
2. Compile the debug APK using Gradle:
   `cmd /c .\gradlew assembleDebug`
3. Deploy and install the APK on the connected mobile device:
   `cmd /c .\gradlew installDebug`
4. Force-stop and launch the app via ADB:
   `adb shell am force-stop com.pantesting.andromidi`
   `adb shell monkey -p com.pantesting.andromidi -c android.intent.category.LAUNCHER 1`
5. Capture and inspect runtime logs using ADB:
   `adb logcat -d -s MIDI MIDI_DECODED MIDI_DEBUG`

## Scripts
This command executes the build and deployment pipeline sequentially across Gradle and ADB.

## Options
  -h, --help
              Print this help and exit 0. No build is executed.

## Notes
- Requires an Android device connected via USB with USB debugging enabled.
- Automatically handles timestamp version bumping to verify fresh deployments.
