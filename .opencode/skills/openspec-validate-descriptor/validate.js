import fs from "node:fs";
import path from "node:path";

const descriptorDir = ".opencode/commands";
const descriptors = fs.readdirSync(descriptorDir).filter(f => f.startsWith("mat-") && f.endsWith(".md"));

let exitCode = 0;

for (const file of descriptors) {
    const filePath = path.join(descriptorDir, file);
    const content = fs.readFileSync(filePath, "utf8");

    const errors = [];
    if (!content.match(/^# \//m)) errors.push("Missing '# /mat-<name>' title");
    if (!content.includes("## Workflow")) errors.push("Missing '## Workflow'");
    if (!content.includes("## Scripts")) errors.push("Missing '## Scripts'");
    if (!content.includes("## Options")) errors.push("Missing '## Options'");
    if (!content.includes("## Notes")) errors.push("Missing '## Notes'");

    if (errors.length > 0) {
        console.error(`Validation failed for ${file}:`);
        errors.forEach(e => console.error(`  - ${e}`));
        exitCode = 1;
    }
}

process.exit(exitCode);
