# Elements in the Range | Check All Integers Present | Math Sum Trick | GeeksForGeeks

---
> Video description: https://youtu.be/0kpkBPrIuKo

[Problem](https://www.geeksforgeeks.org/problems/elements-in-the-range2834/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/0kpkBPrIuKo/0.jpg)](https://youtu.be/0kpkBPrIuKo)

---

**Difficulty:** Easy  
**Topics:** Array | Math | Hashing | Range Check  
**Companies:** Amazon | Snapdeal | Adobe  
**Time Complexity:** O(n) | **Space Complexity:** O(1)

---

## What Does "Elements in the Range" Mean?

Given an array and a range `[start, end]`, the task is to verify that **every integer from `start` to `end` (inclusive)** is present somewhere in the array. Elements outside the range are ignored.

```
arr = [1, 4, 5, 2, 7, 8, 3],  start = 2,  end = 5

Required: {2, 3, 4, 5}
Present:   2 ✓,  3 ✓,  4 ✓,  5 ✓

Output: true
```

---

## Problem Statement — Check If Array Contains All Elements from start to end

Given an integer array `arr[]` and two integers `start` and `end`, return `true` if the array contains **all integers** in the closed range `[start, end]`, otherwise return `false`.

## Examples

**Example 1:**
```
Input:  arr[] = [1, 4, 5, 2, 7, 8, 3],  start = 2,  end = 5
Output: true

Elements {2, 3, 4, 5} all present ✓
```

**Example 2:**
```
Input:  arr[] = [1, 4, 5, 2, 7, 8, 3],  start = 2,  end = 6
Output: false

6 is missing ✗
```

**Example 3:**
```
Input:  arr[] = [10, 5, 3],  start = 1,  end = 5
Output: false

1, 2, 4 are missing ✗
```

**Example 4:**
```
Input:  arr[] = [100, 101, 102],  start = 100,  end = 102
Output: true

{100, 101, 102} all present ✓
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁹
- 1 ≤ start ≤ end ≤ 10⁹

---

## Approach — Range Normalization + Gauss Sum

**Key Insight:** Shift the required range `[start, end]` down to `[1, n]` where `n = end - start + 1`. Apply the same shift to every array element. The sum of all integers `1 + 2 + ... + n = n*(n+1)/2` (Gauss formula) is unique for a complete set — if the sum of the in-range elements matches, all integers are present.

### Why Sum Works

The only way the normalized sum equals `n*(n+1)/2` is if each value from `1` to `n` appears at least once and the total contribution is exact. Missing any value produces a sum below the expected, and extra out-of-range values are filtered out before summing.

### Algorithm

1. Compute `newEnd = end - start + 1` (size of required range).
2. For each element in `arr`, normalize: `n = arr[i] - start + 1`.
3. If `1 <= n <= newEnd`, add `n` to a running `sum`.
4. Compare `sum` against `expectedSum = newEnd * (newEnd + 1) / 2`.
5. If equal → all integers in range are present → return `true`.

### Walkthrough

```
arr = [1, 4, 5, 2, 7, 8, 3],  start = 2,  end = 5

newEnd      = 5 - 2 + 1 = 4
expectedSum = 4 * 5 / 2  = 10

Normalize each element (n = arr[i] - 2 + 1 = arr[i] - 1):
  arr[i]=1 → n=0   out of [1..4] → skip
  arr[i]=4 → n=3   in range      → sum += 3   (sum=3)
  arr[i]=5 → n=4   in range      → sum += 4   (sum=7)
  arr[i]=2 → n=1   in range      → sum += 1   (sum=8)
  arr[i]=7 → n=6   out of [1..4] → skip
  arr[i]=8 → n=7   out of [1..4] → skip
  arr[i]=3 → n=2   in range      → sum += 2   (sum=10)

sum (10) == expectedSum (10) → true ✓
```

```
arr = [1, 4, 5, 2, 7, 8, 3],  start = 2,  end = 6

newEnd      = 6 - 2 + 1 = 5
expectedSum = 5 * 6 / 2  = 15

Normalize (n = arr[i] - 1):
  values in [1..5]: 3, 4, 1, 2  → sum = 3+4+1+2 = 10
  (6 → n=5 would map to arr[i]=6, but 6 is not in arr)

sum (10) ≠ expectedSum (15) → false ✗  (6 is missing)
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — single pass over the array |
| **Space** | O(1) — only scalar variables used |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Using a HashSet — O(n) space when O(1) is possible**
A HashSet approach works but costs extra space. The sum trick is cleaner and more efficient.

**Mistake 2: Forgetting to normalize before range-checking**
Directly checking `start <= arr[i] <= end` and summing raw values compares against the wrong expected sum. Always normalize to `[1, newEnd]` first.

**Mistake 3: Integer overflow on large ranges**
`newEnd * (newEnd + 1) / 2` can overflow `int` for large ranges. Use `long` for the sum and expected sum.

**Mistake 4: Off-by-one in newEnd**
```java
// Wrong
int newEnd = end - start;       // misses the last element

// Correct
int newEnd = end - start + 1;   // inclusive range size
```

---

## Related Problems
- Find Missing Number in Array — LeetCode 268
- Check if Array Contains Contiguous Integers — GFG
- Pair with Given Sum in a Sorted Array

---

## Tags

Array | Range Check | Math | Gauss Sum | Normalization | O(1) Space | Java | GeeksForGeeks | Elements in the Range | Check All Integers Present

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/ElementsInTheRange

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ What "elements in the range" really asks (no HashSet needed)
✅ The Gauss Sum trick — O(1) space alternative to hashing
✅ Range normalization explained step by step
✅ O(n) time · O(1) space solution
✅ Common mistakes — overflow, off-by-one, wrong normalization

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Elements in the Range · Math Sum · Range Normalization · Array · Gauss Formula · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/elements-in-the-range2834/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#ElementsInTheRange #GeeksForGeeks #MathTrick #GaussSum #ArrayProblems
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
```
