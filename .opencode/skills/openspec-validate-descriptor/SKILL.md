---
name: openspec-validate-descriptor
description: Use when creating or updating a command descriptor (.opencode/commands/mat-*.md). Validates adherence to the standard structure (Title, Workflow, Scripts, Options, Notes) and slash-command documentation.
---

# openspec-validate-descriptor

This skill provides the standard template and validation rules for OpenSpec command descriptors.

## Standard Structure (Mandatory)

Every command descriptor MUST follow this structure to ensure consistency and readability:

1.  **# /mat-<name>** (Mandatory)
2.  **Introduction** (One-liner description, slash notation `/mat-<name>` mentioned)
3.  **## Workflow** (Numbered procedure steps)
4.  **## Scripts** (Section listing internal modules: `node src/...` invoking `src/mat-<name>/src/*.mjs`)
5.  **## Options** (Flag enumeration)
6.  **## Notes** (Operational/Safety/TTY notes)

## Validation Template

When creating a new descriptor:

```markdown
---
description: "..."
---

# /mat-<name>

Introduction ... /mat-<name> ...

## Workflow

1. ...
2. ...
3. ...

## Scripts

This command executes `node src/<path>/<entrypoint.mjs>`, which invokes:
- `src/<path>/src/<module>.mjs` — <purpose>

## Options

Every option below is transcribed from `node src/<path>/<entrypoint.mjs> --help`.

Options:
  ...

## Notes

- ...
```

## How to use

- **Creation**: Use the template above for any new `mat-*` command.
- **Verification**: Run `npm test` after editing to ensure drift check and manual-collector tests still pass.
- **Automation**: Use the `entryPoint` heuristic in `collect.js` to ensure the manual generation correctly captures the execution path.
