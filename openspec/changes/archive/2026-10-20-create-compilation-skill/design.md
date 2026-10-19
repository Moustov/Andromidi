## Context

See proposal.md. Development requires repeatedly building, deploying, launching, and checking logs. A custom command descriptor (`.opencode/commands/mat-compile.md`) integrates this into the agent's toolset.

## Goals / Non-Goals

**Goals:**
- Provide a standardized multi-step automation workflow.
- Ensure all 5 steps (version update, build, install, launch, log analysis) execute reliably via shell/adb commands.

**Non-Goals:**
- Release signing (debug builds only for local testing).
