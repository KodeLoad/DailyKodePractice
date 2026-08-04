# Find Missing Elements in a Range | LeetCode 3731 | HashSet O(n) | Easy

---
> Video description: https://youtu.be/jmKsQeFpcTg

[Problem](https://leetcode.com/problems/find-missing-elements/) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/jmKsQeFpcTg/0.jpg)](https://youtu.be/jmKsQeFpcTg)

---

**Difficulty:** Easy  
**Topics:** Array | HashSet | Sorting | Range  
**Companies:** Amazon | Google | Microsoft  
**Time Complexity:** O(n) | **Space Complexity:** O(n)

---

## What Does "Find Missing Elements" Mean?

Given an array that originally contained **every integer in some range** `[min, max]`, some integers may have been removed. The smallest and largest values of the original range are guaranteed to still be present. Return all the **missing integers** in sorted order.

```
nums = [1, 4, 2, 5]

min = 1,  max = 5
Full range: [1, 2, 3, 4, 5]
Present:    {1, 2, 4, 5}
Missing:    [3]
```

---

## Problem Statement — Return All Missing Integers in the Original Range

Given an integer array `nums` of **unique** integers where the smallest and largest elements of the original range are still present, return a **sorted list** of all integers missing from the range `[min(nums), max(nums)]`.

## Examples

**Example 1:**
```
Input:  nums = [1, 4, 2, 5]
Output: [3]

Range [1..5]. All present: {1,2,4,5}. Missing: 3.
```

**Example 2:**
```
Input:  nums = [7, 8, 6, 9]
Output: []

Range [6..9]. All four integers present → nothing missing.
```

**Example 3:**
```
Input:  nums = [5, 1]
Output: [2, 3, 4]

Range [1..5]. Only 1 and 5 present. Missing: 2, 3, 4.
```

## Constraints
- 2 ≤ nums.length ≤ 100
- 1 ≤ nums[i] ≤ 100
- All integers in `nums` are **unique**
- The smallest and largest of the original range are always present

---

## Approach — HashSet for O(1) Lookup

**Key Insight:** Load all elements into a HashSet, find min and max in one pass, then iterate through every integer in `[min, max]` and collect those absent from the set.

### Algorithm

1. Add all elements to a `HashSet` and track `min` and `max` simultaneously — single O(n) pass.
2. Iterate from `min` to `max`:
   - If integer `i` is **not** in the set → it is missing → add to result.
3. Return result (already in sorted order since we iterate ascending).

### Walkthrough — Example 1

```
nums = [1, 4, 2, 5]

Pass 1 — build set + find min/max:
  set = {1, 2, 4, 5},  min = 1,  max = 5

Pass 2 — scan [1..5]:
  i=1: in set → skip
  i=2: in set → skip
  i=3: NOT in set → add 3
  i=4: in set → skip
  i=5: in set → skip

Output: [3]  ✓
```

### Walkthrough — Example 3

```
nums = [5, 1]

set = {1, 5},  min = 1,  max = 5

Scan [1..5]:
  i=1: in set → skip
  i=2: NOT in set → add 2
  i=3: NOT in set → add 3
  i=4: NOT in set → add 4
  i=5: in set → skip

Output: [2, 3, 4]  ✓
```

### Why the Result Is Always Sorted

We iterate from `min` to `max` in ascending order and collect missing elements in that order — no extra sorting step needed.

### Complexity

| | Value |
|---|---|
| **Time** | O(n + range) — O(n) to build set + O(max-min) to scan range; both O(n) given constraints |
| **Space** | O(n) — HashSet stores all elements |

---

## Alternative Approach — Sort First

Sort the array, then walk through comparing each consecutive pair. If `nums[i+1] - nums[i] > 1`, fill in the gap. O(n log n) time, O(1) extra space (excluding output).

The HashSet approach is preferred for its O(n) time.

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Hardcoding range as [1, n]**
The range is `[min(nums), max(nums)]` — not always starting from 1. Always derive min and max from the array.

**Mistake 2: Returning unsorted output**
Collect missing integers by iterating `min → max` to get them in sorted order automatically. Avoid collecting then sorting.

**Mistake 3: Using nested loops — O(n × range)**
Checking each candidate against the full array is unnecessary. A HashSet gives O(1) per lookup.

**Mistake 4: Forgetting that all nums are unique**
The uniqueness guarantee means no duplicates will accidentally fill a gap — each set entry represents exactly one original integer.

---

## Related Problems
- Find All Numbers Disappeared in an Array — LeetCode 448
- Missing Number — LeetCode 268
- Find Missing Ranges — LeetCode 163

---

## Tags

HashSet | Array | Range | Missing Elements | Find Missing Integers | Sorted Output | Java | LeetCode 3731 | Find Missing Elements in Range | Easy

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/Leetcode/3731.FindMissingElements

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ How to reconstruct the original range from min and max
✅ HashSet approach — O(n) time with O(1) lookup per integer
✅ Why iterating min→max gives a sorted result automatically
✅ Two full dry runs covering all example cases
✅ Common mistakes — wrong range, unsorted output, nested loops

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Find Missing Elements · HashSet · Array · Range · LeetCode 3731 · Java · LeetCode Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://leetcode.com/problems/find-missing-elements/

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#GeeksforGeeks #array #hashset #FindMissingElements #CodingInterview #DSA
#Algorithms #LeetCodeDaily #Programming #ProblemSolving #OBrutus #TechInterview
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
#LeetCode3731 #MissingNumbers #EasyLeetCode
```
