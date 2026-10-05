# DEVTO.md house format

Written to `DEVTO.md` beside `README.md`. The README is a reference page; this is
a narrative post. It must not be a reworded README — different job, different shape.

**Form: an interview transcript.** An interviewer and a candidate work the problem
in real time. This is the format because it teaches *approach* — clarifying
questions, dead ends, recovery — which a reference page structurally cannot.

> **Platform rules live elsewhere.** Front matter fields, tag limits, title
> length, embeds, thumbnails and anchor slugs are all in the `devto-publishing`
> skill (`references/frontmatter.md`, `references/embeds.md`). This file covers
> only what makes a *DSA problem* post good. Read both.

## Length is a deliberate choice

Before shipping a long post, check the author's existing reading times — the
`devto-publishing` skill's client can list them (`/articles/me/published` returns
`reading_time_minutes` per post).

If the draft is far longer than their norm, say so and name the options — ship it
as a pillar piece with jump links, split it into a dev.to `series:`, or trim it —
rather than silently publishing an outlier. A 14-minute post on a feed of 2-minute
posts is a different product, not a longer version of the same one.

## Finding the hook

Before writing, answer: **what does this problem punish people for not noticing?**
That is the post. Not "here is how to solve X."

Examples of real hooks:
- The constraint that silently changes the data structure (a graph that is a tree).
- `O(n²)` that is optimal because the output is `O(n²)` — "can you do better?" is a trap.
- A traversal whose *order* is load-bearing and that nobody tells you about.

The title names the hook. The problem name goes in parentheses so it stays searchable:
`The Interview Question Where "Optimize It" Is a Trap (Your Social Network, Explained as a Real Interview)`

If no hook exists, the problem is a poor blog subject — say so rather than
producing a reworded README.

## Beats

Timestamped headings (`## Minute 0:`, `## Minute 5:` …) — they signal real-time
pacing and make the post scannable.

| Beat | Content |
|---|---|
| Framing | Why a transcript. State the hook. Then the **spoiler paragraph**, then the **video embed** (see Linking the YouTube Video). |
| Minute 0 | Interviewer states the problem **vaguely and verbally**, omitting indexing, format, constraints. Note what is missing and that omission is deliberate. |
| Clarifying questions | Candidate asks 3-4 specific questions. One must be the question whose answer is the hook. |
| Hand-trace | Candidate draws the smallest real instance before proposing anything. Say why this beat matters. |
| The observation | The structural insight, derived **from the constraint** — never from a remembered pattern. |
| Pushback | Interviewer challenges it. Candidate defends with a **concrete counterexample**, not assertion. |
| Brute force | Stated and costed out loud, establishing a baseline before optimizing. |
| "Can you do better?" | The pivot. Either a real optimization, or a justified refusal with a lower-bound argument. Interviewer pressure-tests the answer. |
| Code | Written with the gotcha called out as it is typed. |
| Subtlety probe | Interviewer asks about the one line that is load-bearing. |
| Trace | Output from an actual run. |
| Edge cases | Including **where the solution stops being appropriate** and the fix. |
| Grading table | `\| Beat \| What it signals \|` — what was actually being assessed. |
| Red flags | Real failure modes, shared with the README's Common Mistakes. |
| FAQ | See below. |
| Takeaways | 4 numbered lessons that **transfer to other problems**. |
| Footer | Repo link, plain-markdown video link, one question inviting comments. |

Dialogue format — bolded speaker labels, `You` for the candidate so the reader
occupies the seat:

```markdown
> **Interviewer:** Can you do better?

> **You:** No — and I want to be precise about why.
```

Close each substantive beat with a short italic or bolded note:
`**Why this beat scores:** …`. These make it instruction rather than theatre.

## Ranking in search and AI answers

Three requirements:

1. **Spoiler paragraph in the framing section.** Full answer in plain prose,
   before the transcript starts. Label it so its purpose is explicit
   (`The one-line spoiler, for the skimmers and the language models:`). This is
   the chunk a model extracts when summarizing the page.
2. **FAQ with question-shaped `###` headings**, 5-6 of them, phrased the way
   someone types a query: *"Why doesn't this problem need a visited array?"*,
   *"Is O(n²) really the best possible here?"*. Each answer self-contained in
   2-4 sentences. These are the units that get cited.
3. **No section depends on a previous one.** Readers and crawlers arrive mid-page.

## Honesty

- The transcript is a teaching device. Never present it as a recording of a real
  interview at a named company.
- Every code block runs — including variants that are not in `Solution.java`.
  See the gate in SKILL.md.
- Complexity claims are your analysis, not GFG's. The API publishes none.
- The video ID comes from the author, never from memory or a guess. A wrong ID
  publishes a cover image and a player for someone else's video.
