---
name: openspec-traceability
description: Enforces OpenSpec requirement traceability annotations across all generated unit-tests and integration-tests.
AlwaysActive: true
license: MIT
compatibility: Requires OpenSpec specification files and test generation workflows.
metadata:
  author: openspec
  version: "1.0"
  generatedBy: "opencode"
---

Whenever generating, updating, or refactoring tests (both unit-tests and integration-tests):

1. DO NOT force OpenSpec IDs into test function names. Keep function and method names clean, expressive, and idiomatic for the target programming language.

2. MANDATORY TRACEABILITY HEADER:
   Prepend every test block, function, or method with a standardized docstring/comment header formatted natively for the target language. The four tags below must appear in exactly this order; additional tags are allowed after them.

3. REQUIRED HEADER FORMAT (matches `/mat-add-trace-on-ut-and-it`):
   [Language Native Comment Start]
   @Coverage: <REQUIREMENT_ID_1> [, <REQUIREMENT_ID_2>, ...] | NONE
   @Type: [unit-test | integration-test]
   @Rationale: <REQUIREMENT_ID>::<scenario title> — <why this test verifies it>
   @Timestamp: <YYYY-MM-DD HH:mm:ss>
   [Language Native Comment End]

4. TRACEABILITY RULES:
   - For unit-tests: Typically map to a single requirement ID (e.g., `@Coverage: REQ-CORE/TRACEABILITY/001`).
   - For integration-tests: Map all relevant requirement IDs covered by the scenario as a comma-separated list (e.g., `@Coverage: REQ-CART-001, REQ-PAY-005, REQ-NOTIF-002`).
   - Unmapped tests are still annotated `@Coverage: NONE` — keep the `@Type`, `@Rationale`, and `@Timestamp` lines in that case too.
   - Legacy headers (`@OpenSpec`, `@Description`, `@Generated on`) are still RECOGNIZED on input (the trace command strips them before re-annotation) but MUST NOT be used when authoring new headers.
   - Use standard language-native comment syntaxes (e.g., JSDoc `/** ... */` for JS/TS, Block Docstrings `""" ... """` for Python, XML/Doc comments `///` or `/** ... */` for C#/Java, etc.).

5. BEHAVIOR ALIGNMENT & CONTEXT RESOLUTION:
   1. Locate the corresponding OpenSpec requirement file(s) before generating or updating any test (e.g., in `.openspec/`, `openspec/`, or specs directory).
   2. Read the requirement specifications, including acceptance criteria, inputs, expected outputs, edge cases, and failure modes.
   3. Align test assertions strictly with the OpenSpec behaviors:
      - Ensure each acceptance criterion maps to at least one assertion in the generated unit-test or integration-test.
      - Respect nominal flows, boundary conditions, and error states defined in the specification.
   4. If an OpenSpec requirement specifies a behavioral change (e.g., throwing a custom exception instead of returning null), update existing tests to match the new specified behavior.

EXAMPLES:

Example A (TypeScript / Javascript - Unit-test):
/**
 * @Coverage: REQ-CORE/TRACEABILITY/001::Locks user account after three consecutive failed attempts (0.90)
 * @Type: unit-test
 * @Rationale: REQ-CORE/TRACEABILITY/001::Locks user account after three consecutive failed attempts — locks the account after three failed attempts, asserting the derived state.
 * @Timestamp: 2026-09-22 08:17:55
 */
test('locks user account on excess failed attempts', () => { ... });

Example B (Python - Integration-test):
"""
@Coverage: REQ-CART-001, REQ-PAY-005, REQ-NOTIF-002::Complete checkout flow (0.85)
@Type: integration-test
@Rationale: REQ-CART-001::Complete checkout flow — validates cart, payment, and notification in one pipeline.
@Timestamp: 2026-09-22 08:17:55
"""
def test_full_checkout_pipeline(db, mail_client):
    ...