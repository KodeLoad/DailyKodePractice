# Count Subarrays with Sum in Range [L, R] | Sliding Window + Prefix Count | GeeksForGeeks

---
> Video description: https://youtu.be/92DycznOt2U

[Problem](https://www.geeksforgeeks.org/problems/count-the-number-of-subarrays/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/92DycznOt2U/0.jpg)](https://youtu.be/92DycznOt2U)

---

**Difficulty:** Medium  
**Topics:** Array | Sliding Window | Prefix Count | Two Pointer  
**Companies:** Amazon | Microsoft | Google | Adobe  
**Time Complexity:** O(n) | **Space Complexity:** O(1)

---

## What Does "Subarrays with Sum in Range" Mean?

Count all contiguous subarrays whose **sum falls within a closed range `[l, r]`** — i.e., `l ≤ subarray_sum ≤ r`.

```
arr = [2, 3, 5, 8],  l = 4,  r = 13

All subarrays and their sums:
  [2]       = 2   ✗
  [2,3]     = 5   ✓ (4 ≤ 5 ≤ 13)
  [2,3,5]   = 10  ✓
  [2,3,5,8] = 18  ✗
  [3]       = 3   ✗
  [3,5]     = 8   ✓
  [3,5,8]   = 16  ✗
  [5]       = 5   ✓
  [5,8]     = 13  ✓
  [8]       = 8   ✓

Output: 6
```

---

## Problem Statement — Count Subarrays Whose Sum Lies Between L and R (Inclusive)

Given an array `arr[]` of **non-negative integers** and two integers `l` and `r`, return the **count of subarrays** whose sum satisfies `l ≤ sum ≤ r`.

## Examples

**Example 1:**
```
Input:  arr[] = [2, 3, 5, 8],  l = 4,  r = 13
Output: 6

Valid subarrays: [2,3], [2,3,5], [3,5], [5], [5,8], [8]
(see full breakdown above)
```

**Example 2:**
```
Input:  arr[] = [1, 4, 6],  l = 3,  r = 8
Output: 3

  [1,4]   = 5  ✓
  [4]     = 4  ✓
  [1,4,6] = 11 ✗
  [4,6]   = 10 ✗
  [6]     = 6  ✓
  [1]     = 1  ✗

Output: 3
```

**Example 3:**
```
Input:  arr[] = [5],  l = 5,  r = 5
Output: 1

Only [5] with sum 5 is valid.
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ arr[i] ≤ 10⁴
- 0 ≤ l ≤ r ≤ 10⁹

---

## Why Brute Force O(n²) Fails

Checking every pair `(i, j)` and computing subarray sums costs O(n²). For n = 10⁵ that is too slow. We need O(n).

---

## Approach — Count(sum ≤ r) − Count(sum ≤ l−1)

**Key Insight:** Counting subarrays with sum in `[l, r]` is equivalent to:

```
count(sum ≤ r)  −  count(sum ≤ l−1)

         0 ─────────────────── r
         0 ────── l-1
                        l ──── r   ← what we want
```

Both `count(sum ≤ target)` calls use the **sliding window** technique — valid because all elements are non-negative (sum only grows as the window expands).

### Sliding Window for count(sum ≤ target)

For each right pointer `r`, shrink from the left while `sum > target`. All subarrays ending at `r` with starting index in `[l, r]` are valid → count `+= r - l + 1`.

```
for r = 0 to n-1:
    sum += arr[r]
    while sum > target:
        sum -= arr[l]
        l++
    cnt += r - l + 1   ← subarrays ending at r with sum ≤ target
```

### Walkthrough — Example 1

```
arr = [2, 3, 5, 8],  l = 4,  r = 13

Step 1: lessTargetCount(arr, 13)
  r=0: sum=2  ≤ 13  cnt += 1  (subarrays: [2])
  r=1: sum=5  ≤ 13  cnt += 2  (subarrays: [3],[2,3])
  r=2: sum=10 ≤ 13  cnt += 3  (subarrays: [5],[3,5],[2,3,5])
  r=3: sum=18 > 13  → l++, sum=16 > 13 → l++, sum=13 ≤ 13
       cnt += 2      (subarrays: [8],[5,8])
  Total = 1+2+3+2 = 8

Step 2: lessTargetCount(arr, l-1=3)
  r=0: sum=2  ≤ 3  cnt += 1  (subarrays: [2])
  r=1: sum=5  > 3  → l++, sum=3  ≤ 3
       cnt += 1     (subarrays: [3])
  r=2: sum=8  > 3  → l++, sum=5 > 3 → l++, sum=5>3 → l++
       l=3, sum=5>3, l=3 already... actually:
       sum=3+5=8 → l++, sum=8-3=5>3 → l++, sum=5-5=0... 
       Hmm, let me redo cleanly:
       
  r=2 (a[r]=5): sum = 3+5 = 8 → shrink:
    l=1: sum -= a[1]=3 → sum=5 > 3 → l=2
    l=2: sum -= a[2]=5 → sum=0 ≤ 3 → stop, l=3
    cnt += r-l+1 = 2-3+1 = 0
  r=3 (a[r]=8): sum=0+8=8 → shrink:
    l=3: sum -= a[3]=8 → sum=0 ≤ 3, l=4
    cnt += 3-4+1 = 0
  Total = 1+1+0+0 = 2

Answer = 8 - 2 = 6  ✓
```

### Walkthrough — Example 2

```
arr = [1, 4, 6],  l = 3,  r = 8

lessTargetCount(arr, 8):
  r=0: sum=1 ≤ 8 → cnt=1
  r=1: sum=5 ≤ 8 → cnt=3  (+2)
  r=2: sum=11 > 8 → l++, sum=10 > 8 → l++, sum=6 ≤ 8
       cnt += 1 → cnt=4

lessTargetCount(arr, 2):  (l-1 = 2)
  r=0: sum=1 ≤ 2 → cnt=1
  r=1: sum=5 > 2 → l++, sum=4 > 2 → l++, sum=0 ≤ 2
       cnt += 0 → cnt=1
  r=2: sum=6 > 2 → l=2, sum-=a[2]=6 → sum=0 ≤ 2, l=3
       cnt += 0 → cnt=1

Answer = 4 - 1 = 3  ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — each pointer moves at most n times across both calls |
| **Space** | O(1) — only scalar variables, no extra arrays |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Applying sliding window to arrays with negative numbers**
The sliding window works here because all `arr[i] ≥ 0`. With negative elements, shrinking from the left doesn't guarantee reducing the sum.

**Mistake 2: Using `l` instead of `l-1` in the subtraction**
```java
// Wrong
lessTargetCount(a, r) - lessTargetCount(a, l)   // excludes l itself

// Correct
lessTargetCount(a, r) - lessTargetCount(a, l-1) // includes l
```

**Mistake 3: Counting `r - l` instead of `r - l + 1`**
For a fixed right pointer `r`, valid left pointers are `l, l+1, ..., r` — that is `r - l + 1` subarrays, not `r - l`.

**Mistake 4: Forgetting to reset `l = 0` between the two sliding window calls**
Each call to `lessTargetCount` is independent — the left pointer must start fresh at 0 each time.

---

## Related Problems
- Subarray Sum Equals K — LeetCode 560
- Count Number of Nice Subarrays — LeetCode 1248
- Max Sum Subarray of Size at Least K — GFG
- Minimum Size Subarray Sum — LeetCode 209

---

## Tags

Sliding Window | Two Pointer | Prefix Count | Range Sum | Count Subarrays | Non-negative Array | Java | GeeksForGeeks | Subarrays with Sum in Range | Count Subarrays Between L and R

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/SubarraysWithSumInRange

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ The count(≤r) − count(≤l−1) trick — why it works
✅ Sliding window for "count subarrays with sum ≤ target"
✅ Why this only works for non-negative arrays
✅ Two full dry runs covering both helper calls
✅ Common mistakes — wrong boundary, resetting l, off-by-one count

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Subarrays with Sum in Range · Sliding Window · Two Pointer · Prefix Count · Array · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/count-the-number-of-subarrays/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#GeeksforGeeks #array #SlidingWindow #SubarraySum #CountSubarrays #CodingInterview
#DSA #Algorithms #GFGPOTD #Programming #ProblemSolving #OBrutus #TechInterview
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
#twopointers #RangeSum #PrefixCount
```
