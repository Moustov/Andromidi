## Context

See proposal.md - Why. The current architecture uses a `MidiCCListenerThread` to initiate a listener, but requires refinement to ensure proper stream parsing and UI updates without thread congestion.

## Goals / Non-Goals

**Goals:**
- Establish a reliable USB MIDI input stream listener.
- Implement a thread-safe update mechanism to push feedback to `MainActivity`.

**Non-Goals:**
- Handling audio signals (MIDI only).
- Creating new UI components beyond basic feedback indicators.

## Decisions

- **Approach**: Utilize the existing `MidiCCListenerThread` class. Refactor it to support standard input port listening as defined by Android's MIDI API.
- **Alternative considered**: Implementing a dedicated `MidiService`. Rejected due to complexity; local thread usage in the activity context is sufficient for the current scale.

## Risks / Trade-offs

- [Risk] Memory leaks if view references persist longer than the activity → Mitigation: Ensure thread interrupts in `onDestroy()`.
- [Risk] USB stream interruption during high traffic → Mitigation: Implement a small buffer for incoming MIDI packets in the listener.
- [Risk] UI thread blocking during updates → Mitigation: Use `runOnUiThread()` to update UI elements from the listener thread.
- [Risk] UI thread blocking during updates → Mitigation: Use `runOnUiThread()` to update UI elements from the listener thread.
