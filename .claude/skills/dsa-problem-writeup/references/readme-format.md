# README.md house format

Section order is fixed. Every `## ` heading below is REQUIRED and appears in this
order. Optional sections are marked. Em-dashes in headings are part of the format.

## Skeleton

````markdown
# <Problem Name> | <Technique in 2-5 words> | GeeksForGeeks

---
> Video description: https://youtu.be/<VIDEO_ID>

[Problem](<problem url>) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/<VIDEO_ID>/0.jpg)](https://youtu.be/<VIDEO_ID>)

---

**Difficulty:** <API value>  
**Topics:** <A | B | C | D>  
**Companies:** <A | B | C | D>  
**Time Complexity:** O(...) | **Space Complexity:** O(...)

---

## What Does "<Problem Name>" Mean?

Plain-language restatement of the core idea, then a fenced ASCII diagram on a
tiny concrete instance. This section exists to win the featured snippet — it
must make sense to someone who has not read the problem.

---

## Problem Statement — <keyword-rich restatement>

The formal statement, rewritten in your own words from the API text.

## Examples

**Example 1:**
```
Input:  ...
Output: ...

<why>
```

Two or three examples. At least one must be an official API sample, verbatim
input and output. Add one case that exposes behavior the official samples hide
(branching, duplicates, all-equal, single element).

## Constraints
- Taken from the API `constraints_display` or extracted from the statement
- Add any guarantee stated in prose but absent from the formal list

---

## Why Brute Force O(...) Fails          <!-- OPTIONAL: only when a naive
                                              approach is the natural first
                                              instinct and is too slow -->

---

## Approach — <named approach>

**Key Insight:** one bolded sentence. The whole section hangs off this.

### <Named sub-step>                      <!-- as many as the approach needs -->

Code blocks here must match `Solution.java` exactly, including its idioms.

### Algorithm

Numbered steps, 4-8 of them.

### Walkthrough — Example 1

```
Fenced trace, produced by RUNNING the code, not by hand.
```

Add a second walkthrough when one case cannot show the behavior (worst case,
branching case).

### Complexity

| | Value |
|---|---|
| **Time** | O(...) — <why> |
| **Space** | O(...) — <why> |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: <short imperative label>**
One or two sentences. Add a wrong-vs-correct code pair when the fix is a line.

4-7 mistakes. Each must be a real failure mode of THIS problem — derived from
the solution's load-bearing lines, not generic advice. If removing a line from
`Solution.java` breaks it, that line is a mistake entry.

---

## Related Problems
- <Name> — LeetCode <number>
- <Name> — GFG

4-5 entries, named platform and number so they are searchable.

---

## Tags

Pipe-separated, 10-14 terms: techniques, data structures, `Java`,
`GeeksForGeeks`, the exact problem name, and the common alternate name people
search for.

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/<Dir>

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ <5-6 lines, each a specific insight from the post, not a topic name>

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
<Problem Name> · <technique> · <technique> · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → <problem url>

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#GeeksforGeeks #<topic> #<technique> #CodingInterview #DSA #Algorithms
#GFGPOTD #Programming #ProblemSolving #OBrutus #TechInterview #DataStructures
#JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad #<extra> #<extra>
```
````

## Hashtag set

These five appear in every existing post — never drop them:

```
#DSAWithOBrutus #KodeLoad #JavaProgramming #DataStructures #DailyChallenge
```

Then the standard block — `#GeeksforGeeks #GFGPOTD #DSA #Algorithms
#CodingInterview #Programming #ProblemSolving #OBrutus #TechInterview` — plus
2-4 problem-specific tags. Target 18-20 total.

## Writing rules

- **Two trailing spaces** after each metadata line, or the markdown line breaks collapse.
- **ASCII diagrams over prose** for anything spatial. Matrices, trees, and windows get drawn.
- **Every section standalone.** Readers arrive mid-page from search; no section may depend on an earlier one.
- **Name the technique in the H1.** `| DFS Flood Fill |` outranks `| Solution |`.
- **The "What Does X Mean?" heading is a question.** Question headings win snippets.
