---
name: dsa-problem-writeup
description: Use when writing or filling in a README.md or a dev.to blog post for a solved problem in this repo's GeeksForGeeks/LeetCode/Codeforces directories - including "fill this readme", "make it SEO friendly", "write a dev.to post about this problem", or publishing a write-up alongside a YouTube video.
---

# DSA Problem Write-up

## Overview

Produces two artifacts beside a solved problem: `README.md` (SEO reference page) and `DEVTO.md` (narrative blog post). Both already have a fixed house format in this repo.

**Core principle: every factual claim is fetched or executed, never recalled.** Problem statements come from the API, sample outputs come from running the code, complexity comes from reading the code. Nothing comes from memory — these pages are published, and a wrong constraint or a wrong sample output is public.

## Workflow

1. **Fetch the problem.** From the repo root:
   ```bash
   bash .claude/skills/dsa-problem-writeup/scripts/fetch-problem.sh <slug-or-problem-url>
   ```
   The GFG problem page is client-side rendered — fetching the URL returns nav chrome with no statement. The script hits the practice API instead and prints statement, constraints, samples, difficulty, and tags.
2. **Read the existing `Solution.java`** in the problem directory. Document the approach that is there; do not substitute your own.
3. **Run it against the official samples.** See the gate below, and
   `references/test-harness.md` for the compile-and-run pattern. Do this before
   writing any prose.
4. **Write `README.md`** per `references/readme-format.md`.
5. **Write `DEVTO.md`** per `references/devto-format.md` for the post's shape, and
   the `devto-publishing` skill for front matter and embeds — only when a blog post
   was requested. Ask for the YouTube link if one exists and was not given.
6. **Report every unsourced field** to the user (see Never Assert What You Did Not Fetch).
7. **Publish, if asked.** Use the `devto-publishing` skill — it owns the API
   client, auth, and platform rules. Going live is never automatic.

## The Gate: Nothing Is Written Before The Code Runs

```
NO WRITE-UP BEFORE THE SOLUTION RUNS AGAINST THE OFFICIAL SAMPLES.
```

Compile and execute. Confirm each documented output — including element order — matches the API's expected output. This is how you discover which lines of the solution are load-bearing, and it is the only way the walkthroughs and "Common Mistakes" sections come out true instead of plausible.

**This applies to every code block you publish**, not just `Solution.java`. An iterative variant, an optimized version, or a one-pass alternative that you introduce in the prose must be compiled and diffed against the original's output. Untested snippets in a published post are wrong snippets.

| Rationalization | Reality |
|---|---|
| "The solution is already accepted on GFG" | Accepted tells you nothing about the output *order* or edge behavior you are about to describe in a walkthrough. |
| "I can trace it by hand faster than compiling" | Hand-traces are where fabricated walkthroughs come from. Compiling takes one tool call. |
| "The snippet is a trivial variant" | Trivial variants are exactly where off-by-one and reversed-order bugs hide. Run it. |
| "I'll note it as untested" | A hedge in a published post is still a wrong code block. Run it or cut it. |
| "No main method / no test harness exists" | Write one in the scratchpad — `references/test-harness.md` has the pattern. Takes one minute. |

**Red flags — stop and go run the code:**
- You are about to type a "Walkthrough" or "Dry run" section and have not executed anything.
- You are describing expected output you read off the problem page rather than produced.
- You introduced a second implementation in the prose and only reasoned about it.

## Publishing And Platform Rules Live In `devto-publishing`

That skill owns dev.to front matter, tag limits, title length, embeds, thumbnails,
anchor slugs, auth, and the publisher script. Load it alongside this one whenever a
`DEVTO.md` is written or pushed — do not restate its rules here, and do not push a
post without reading it.

The one rule worth repeating: the publisher creates a **draft** by default, and
`--publish` is never passed on an agent's own initiative.

## Copy The Current Format, Not The Common One

Most READMEs under `GeeksForGeeks/` use a **legacy scraped format** — `## Topic Tags:`, `## Expected Complexities:`, `## Related Articles:`, `## Keywords:`. It is the majority, so grepping the repo for "the format" finds the wrong one.

The current format is the one carrying `## YouTube Comment — Copy-Paste Ready`. Identify current examples with:

```bash
grep -l "YouTube Comment" GeeksForGeeks/*/README.md
```

Canonical skeleton: `references/readme-format.md`. Closest reference examples: `PerimeterOfShapesInBinaryMatrix`, `SubarraysWithSumInRange`, `YourSocialNetwork`.

## Never Assert What You Did Not Fetch

The house format has slots the API does not fill. Fill them, then say so in your reply to the user:

| Field | Source | If unavailable |
|---|---|---|
| **Difficulty** | API `difficulty` | Use verbatim. Never restate from memory — it disagrees with repo history in places. |
| **Topics** | API `topic_tags`, widened with the techniques actually used | — |
| **Companies** | API `company_tags` — usually `[]` | Write plausible ones to match house format, then tell the user it is convention, not sourced. |
| **Time/Space Complexity** | Your analysis of `Solution.java` | The API has no such field. Derive it, state it is your analysis. |

## Common Mistakes

- **Writing the walkthrough from the problem page's examples** instead of from program output. The order may differ.
- **Reproducing `Solution.java` loosely** in the README. Snippets must match the file, including its idioms — readers diff them.
- **Dropping the author's inline notes.** `Solution.java` often carries scratch comments; they reveal the intended insight. Read them before designing the Approach section.
- **More than 4 dev.to tags**, or tags with uppercase/punctuation. dev.to silently rejects them.
- **Inventing a YouTube ID, or leaving a `<VIDEO_ID>` placeholder in a file.** The
  link comes from the user. No video means no thumbnail block in the README and no
  embed in `DEVTO.md` — ask, then write the post without them and say what to add later.
- **Using the README's `0.jpg` thumbnail as the dev.to `cover_image`.** That one is
  120x90. Covers need `maxresdefault.jpg`.
- **Assuming dev.to liquid tags render on GitHub.** They do not. `DEVTO.md` keeps a
  plain markdown video link in the footer for that reason — do not "tidy" it away.
