## 1. Thread Refactoring

- [x] 1.1 Analyze the current `MidiCCListenerThread` and identify the necessary hooks to process input stream data. Verify by identifying the read loop in the code.
- [x] 1.2 Update `Midi.java` to properly expose the `MidiInputPort` for reading, if necessary. Verify by adding a logging statement during connection.

## 2. Implementation

- [x] 2.1 Refactor `MidiCCListenerThread` to correctly parse MIDI CC messages received from the USB input port. Verify by checking if received CC indices match hardware outputs.
- [x] 2.2 Implement state update mechanisms in `MainActivity` (UI updates) triggered by incoming MIDI events (including song title updates). Verify by changing a preset on the Matribox II Pro and checking the app UI update.
- [x] 2.3 Implement BPM synchronization logic in the listener to capture tempo changes from the hardware. Verify by observing the BPM text view updates when the hardware tempo changes.

## 3. Verification

- [x] 3.1 Perform full bidirectional integration test: Send control from app -> observe change -> confirm device sends feedback -> observe app UI update. Verify all three phases are successful.
