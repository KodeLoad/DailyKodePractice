# Split Array into Minimum Subsets of Consecutive Integers | HashSet O(n) | GeeksForGeeks

---
> Video description: https://youtu.be/-0r6LpB4hkk

[Problem](https://www.geeksforgeeks.org/problems/split-array-into-minimum-subsets/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/-0r6LpB4hkk/0.jpg)](https://youtu.be/-0r6LpB4hkk)

---

**Difficulty:** Medium  
**Topics:** Array | HashSet | Greedy | Consecutive Sequences  
**Companies:** Amazon | Microsoft | Google  
**Time Complexity:** O(n) | **Space Complexity:** O(n)

---

## What Does "Split into Minimum Subsets" Mean?

Given an array of integers, partition it into the **fewest possible subsets** where each subset consists of **consecutive integers** (no gaps, in any order within the subset).

```
arr = [100, 56, 5, 6, 102, 58, 101, 57, 7, 103, 59]

Consecutive groups:
  {5, 6, 7}                →  subset 1
  {56, 57, 58, 59}         →  subset 2
  {100, 101, 102, 103}     →  subset 3

Minimum subsets = 3
```

---

## Problem Statement — Minimum Number of Subsets of Consecutive Integers

Given an integer array `arr[]`, return the **minimum number of subsets** the array can be split into such that every element in each subset forms a **consecutive sequence**.

## Examples

**Example 1:**
```
Input:  arr[] = [100, 56, 5, 6, 102, 58, 101, 57, 7, 103, 59]
Output: 3

Groups:
  {5, 6, 7}            ← starts at 5  (4 not in array)
  {56, 57, 58, 59}     ← starts at 56 (55 not in array)
  {100, 101, 102, 103} ← starts at 100 (99 not in array)
```

**Example 2:**
```
Input:  arr[] = [1, 2, 3, 4, 5]
Output: 1

All elements form one consecutive group {1, 2, 3, 4, 5}.
```

**Example 3:**
```
Input:  arr[] = [1, 3, 5, 7]
Output: 4

No two elements are consecutive → 4 subsets of size 1 each.
```

**Example 4:**
```
Input:  arr[] = [5, 4, 3, 2, 1, 10, 9, 8]
Output: 2

{1, 2, 3, 4, 5} and {8, 9, 10} → 2 subsets.
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ arr[i] ≤ 10⁶

---

## Approach — HashSet + Count Sequence Starts

**Key Insight:** A consecutive sequence must start at some number `x` where `x - 1` does **not** exist in the array. Every such number is the beginning of a new subset. Counting these starting points gives the minimum number of subsets.

```
arr = [5, 6, 7, 56, 57, 58]

Is 5  a start?  4  not in set → YES ← new subset
Is 6  a start?  5  in set     → NO  (extends subset started at 5)
Is 7  a start?  6  in set     → NO
Is 56 a start?  55 not in set → YES ← new subset
Is 57 a start?  56 in set     → NO
Is 58 a start?  57 in set     → NO

Count = 2 ✓
```

### Why Not Just Sort and Scan? — O(n log n)

Sorting works but costs O(n log n). Using a HashSet gives O(1) lookup for `x - 1`, reducing the overall complexity to O(n).

### Algorithm

1. Add all elements to a `HashSet` for O(1) lookup.
2. For each element `a[i]`:
   - If `a[i] - 1` is **not** in the set → this element starts a new consecutive sequence → `count++`.
3. Return `count`.

### Walkthrough

```
arr = [100, 56, 5, 6, 102, 58, 101, 57, 7, 103, 59]

HashSet = {5, 6, 7, 56, 57, 58, 59, 100, 101, 102, 103}

Scan each element:
  a[0]=100: 99  not in set → count=1 (start of 100,101,102,103)
  a[1]=56:  55  not in set → count=2 (start of 56,57,58,59)
  a[2]=5:   4   not in set → count=3 (start of 5,6,7)
  a[3]=6:   5   IN set     → skip
  a[4]=102: 101 IN set     → skip
  a[5]=58:  57  IN set     → skip
  a[6]=101: 100 IN set     → skip
  a[7]=57:  56  IN set     → skip
  a[8]=7:   6   IN set     → skip
  a[9]=103: 102 IN set     → skip
  a[10]=59: 58  IN set     → skip

Output: 3 ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — one pass to build set + one pass to count starts |
| **Space** | O(n) — HashSet stores all n elements |

---

## Why O(n²) Brute Force Fails

The naive approach checks every pair `(i, j)` to see if they are consecutive — O(n²). For n = 10⁵ that is 10¹⁰ operations, far too slow. The HashSet reduces each lookup to O(1).

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Counting elements without checking for sequence start**
Counting every element gives n subsets. Only count elements where `a[i] - 1` is absent.

**Mistake 2: Sorting and then counting gaps**
Works but is O(n log n). HashSet approach is faster and simpler.

**Mistake 3: Using a frequency map instead of a set**
For this problem, existence (not count) is all that matters. A `HashSet` is cleaner than a `HashMap`.

**Mistake 4: Forgetting that order in the array doesn't matter**
Elements don't need to be adjacent in the original array to belong to the same consecutive subset — only their values matter.

---

## Related Problems
- Longest Consecutive Sequence — LeetCode 128
- Consecutive Elements Check — GFG
- Divide Array in Sets of K Consecutive Numbers — LeetCode 1296

---

## Tags

HashSet | Consecutive Sequence | Greedy | Array | Minimum Subsets | Sequence Start | Java | GeeksForGeeks | Split Array into Minimum Subsets

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/SplitArrayIntoMinimumSubsets

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ What "consecutive subsets" means with visual examples
✅ The "sequence start" insight — why check a[i]-1 in set
✅ How HashSet reduces brute force O(n²) to O(n)
✅ Step-by-step dry run with full walkthrough
✅ Common mistakes — sorting, frequency maps, and overcounting

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Split Array into Minimum Subsets · Consecutive Sequence · HashSet · Greedy · Array · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/split-array-into-minimum-subsets/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#SplitArrayMinimumSubsets #GeeksForGeeks #HashSet #ConsecutiveSequence #ArrayProblems
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
```
