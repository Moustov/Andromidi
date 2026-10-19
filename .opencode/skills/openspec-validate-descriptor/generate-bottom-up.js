import { execSync } from "node:child_process";
import fs from "node:fs";
import path from "node:path";

const specsRoot = "openspec/specs";
const domains = fs.readdirSync(specsRoot).filter(d => fs.statSync(path.join(specsRoot, d)).isDirectory());

const sleep = (ms) => new Promise(resolve => setTimeout(resolve, ms));

async function run() {
    for (const domain of domains) {
        console.log(`\n--- Processing domain: ${domain} ---`);
        try {
            // 1. Generate test plan (Isolated process)
            console.log(`Generating test plan for ${domain}...`);
            execSync(`node src/mat-test-plan/run-test-plan.mjs openspec/specs/${domain}`, { stdio: "inherit" });
            
            // 2. Pause to prevent rate-limiting (5 seconds)
            await sleep(5000);
            
            // 3. Validate (Isolated process)
            console.log(`Validating ${domain}...`);
            execSync(`node src/mat-swiss-cheese/dist/run-swiss-cheese.mjs --domain ${domain}`, { stdio: "inherit" });
            
            // 4. Longer pause between domains (10 seconds)
            await sleep(10000);
        } catch (e) {
            console.error(`Failed domain ${domain}: ${e.message}`);
        }
    }
    console.log("Bottom-up generation complete.");
}

run();
