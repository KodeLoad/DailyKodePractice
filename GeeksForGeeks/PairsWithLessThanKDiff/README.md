# Pairs with Difference Less than K | Sort + Two Pointer O(n log n) | GeeksForGeeks

---
> Video description: https://youtu.be/FKa2L56j_L8

[Problem](https://www.geeksforgeeks.org/problems/pairs-with-difference-less-than-k1348/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/FKa2L56j_L8/0.jpg)](https://youtu.be/FKa2L56j_L8)

---

**Difficulty:** Medium  
**Topics:** Array | Sorting | Two Pointer | Sliding Window  
**Companies:** Amazon | Microsoft | Flipkart | Adobe  
**Time Complexity:** O(n log n) | **Space Complexity:** O(1)

---

## What Does "Pairs with Difference Less than K" Mean?

Count all **unordered pairs** `(i, j)` where `i ≠ j` such that the **absolute difference** between the two elements is **strictly less than k**.

```
arr = [1, 10, 4, 2],  k = 3

All pairs and their differences:
  (1, 10) → |1-10| = 9  ≥ 3  ✗
  (1, 4)  → |1-4|  = 3  ≥ 3  ✗  (not strictly less)
  (1, 2)  → |1-2|  = 1  < 3  ✓
  (10, 4) → |10-4| = 6  ≥ 3  ✗
  (10, 2) → |10-2| = 8  ≥ 3  ✗
  (4, 2)  → |4-2|  = 2  < 3  ✓

Output: 2
```

---

## Problem Statement — Count Pairs with Absolute Difference Strictly Less than K

Given an integer array `arr[]` and an integer `k`, return the **count of pairs** `(i, j)` with `i < j` such that `|arr[i] - arr[j]| < k`.

## Examples

**Example 1:**
```
Input:  arr[] = [1, 10, 4, 2],  k = 3
Output: 2

Valid pairs: (1,2) diff=1 ✓,  (4,2) diff=2 ✓
```

**Example 2:**
```
Input:  arr[] = [1, 2, 3, 4, 5],  k = 3
Output: 7

Sorted: [1, 2, 3, 4, 5]
Valid pairs (diff < 3):
  (1,2)=1 ✓  (1,3)=2 ✓
  (2,3)=1 ✓  (2,4)=2 ✓
  (3,4)=1 ✓  (3,5)=2 ✓
  (4,5)=1 ✓
Count = 7
```

**Example 3:**
```
Input:  arr[] = [5, 5, 5],  k = 1
Output: 0

All differences = 0, which is not < 1 (strictly less than).
```

**Example 4:**
```
Input:  arr[] = [1, 3],  k = 5
Output: 1

|1-3| = 2 < 5  ✓
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ arr[i] ≤ 10⁵
- 1 ≤ k ≤ 10⁵

---

## Approach — Sort + Two Pointer Sliding Window

### Why Brute Force O(n²) Fails

Checking every pair `(i, j)` costs O(n²). For n = 10⁵ that is 10¹⁰ comparisons — too slow.

### Key Insight After Sorting

Once the array is sorted, `a[r] - a[l]` is always non-negative for `r > l`. More importantly:

> If `a[r] - a[l] >= k`, then for any `r' > r`, `a[r'] - a[l] >= k` too. No need to check further right.

This monotonicity makes the two-pointer approach valid and efficient.

### Algorithm

1. Sort the array.
2. Use two pointers `l` and `r` (both starting at 0, `r` at 1).
3. For each `l`, advance `r` as far right as `a[r] - a[l] < k`.
4. All elements from index `l+1` to `r-1` form valid pairs with `l`:
   - Count = `r - l - 1`.
5. Advance `l` — `r` never resets (monotone window).

### Walkthrough — Example 1

```
arr = [1, 10, 4, 2],  k = 3
After sort: [1, 2, 4, 10]

l=0 (a[l]=1), r starts at 1:
  r=1: 2-1=1  < 3 → r++
  r=2: 4-1=3 ≥ 3 → stop
  cnt += r-l-1 = 2-0-1 = 1   ← pair (1,2)

l=1 (a[l]=2), r=2:
  r=2: 4-2=2  < 3 → r++
  r=3: 10-2=8 ≥ 3 → stop
  cnt += 3-1-1 = 1   ← pair (2,4)

l=2 (a[l]=4), r=3:
  r=3: 10-4=6 ≥ 3 → stop immediately
  cnt += 3-2-1 = 0

l=3 (a[l]=10), r=4: r >= n → stop
  cnt += 0

Total = 1+1+0+0 = 2  ✓
```

### Walkthrough — Example 2

```
arr = [1, 2, 3, 4, 5],  k = 3
After sort: [1, 2, 3, 4, 5]

l=0: r advances to 3 (4-1=3, stops) → cnt += 3-0-1 = 2
l=1: r advances to 4 (5-2=3, stops) → cnt += 4-1-1 = 2
l=2: r advances to 5 (end, 5-3=2<3) → cnt += 5-2-1 = 2
l=3: r=5 (end, 5-4=1<3)            → cnt += 5-3-1 = 1
l=4: r=5                             → cnt += 0

Total = 2+2+2+1+0 = 7  ✓
```

### Why r Never Resets

After sorting, when `l` advances from `l` to `l+1`, the new `a[l+1] >= a[l]`. Any `r` that satisfied `a[r] - a[l] < k` may or may not satisfy `a[r] - a[l+1] < k`, but it can never go **below** the last valid `r`. So `r` only ever moves right — O(n) total moves.

### Complexity

| | Value |
|---|---|
| **Time** | O(n log n) — dominated by sorting; two-pointer scan is O(n) |
| **Space** | O(1) — sorting in-place, only scalar variables |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Using `≤ k` instead of `< k`**
The problem asks for difference **strictly less than k**. The condition must be `a[r] - a[l] < k`, not `<= k`.

**Mistake 2: Counting `r - l` instead of `r - l - 1`**
`r - l` includes `l` itself. Valid partners for `l` are indices `l+1` to `r-1` — that's `r - l - 1` elements.

**Mistake 3: Resetting `r = l + 1` for each `l`**
Resetting `r` each time makes it O(n²). Since the array is sorted, `r` only needs to advance — never go back.

**Mistake 4: Forgetting to sort first**
Without sorting, `a[r] - a[l]` is not monotone and the two-pointer logic breaks entirely.

---

## Related Problems
- Count of Pairs with Given Sum — GFG
- Pair with Given Difference — GFG
- Two Sum — LeetCode 1
- K-diff Pairs in an Array — LeetCode 532

---

## Tags

Two Pointer | Sorting | Sliding Window | Array | Count Pairs | Absolute Difference | Less than K | Java | GeeksForGeeks | Pairs with Difference Less than K

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/PairsWithLessThanKDiff

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ Why brute force O(n²) fails and how sorting unlocks O(n log n)
✅ The monotonicity insight — why r never needs to reset
✅ Counting formula: why it's r-l-1 and not r-l
✅ Two full dry runs with step-by-step pointer movement
✅ Common traps — strict vs non-strict, resetting r, not sorting

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Pairs with Difference Less than K · Two Pointer · Sorting · Sliding Window · Count Pairs · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/pairs-with-difference-less-than-k1348/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#GeeksforGeeks #array #twopointers #PairsWithLessThanKDiff #CodingInterview
#DSA #Algorithms #GFGPOTD #Programming #ProblemSolving #OBrutus #TechInterview
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
#SlidingWindow #Sorting #CountPairs
```
