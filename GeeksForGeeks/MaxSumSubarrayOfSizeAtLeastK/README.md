# Max Sum Subarray of Size at Least K | Sliding Window + Kadane | GeeksForGeeks

---
> Video description: https://youtu.be/YNlVPmShaMs

[Problem](https://www.geeksforgeeks.org/problems/largest-sum-subarray-of-size-at-least-k3121/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/YNlVPmShaMs/0.jpg)](https://youtu.be/YNlVPmShaMs)

---

**Difficulty:** Medium  
**Topics:** Array | Sliding Window | Kadane's Algorithm | Prefix Sum  
**Companies:** Amazon | Microsoft | Google | Adobe  
**Time Complexity:** O(n) | **Space Complexity:** O(1)

---

## What Makes This Different From Maximum Subarray?

Standard Kadane's algorithm finds the maximum subarray of **any** size. This problem adds a constraint: the subarray must have **at least k elements**. The first k elements are mandatory; beyond that, we greedily decide whether to extend or trim the prefix.

```
k = 2,  arr = [-4, -2, 1, -3]

Must include at least 2 elements.
Best subarray: [1, -3] → sum = -2
             or [-2, 1] → sum = -1  ← max
```

---

## Problem Statement — Largest Sum Subarray of Size at Least K

Given an integer array `arr[]` and an integer `k`, find the **maximum sum** of any contiguous subarray that has **at least k elements**.

## Examples

**Example 1:**
```
Input:  arr[] = [1, 2, 3, -10, -3], k = 2
Output: 6

Subarray [1, 2, 3] has sum 6 and size 3 ≥ k ✓
```

**Example 2:**
```
Input:  arr[] = [-4, -2, 1, -3], k = 2
Output: -1

All subarrays of size ≥ 2:
  [-4, -2]       = -6
  [-4, -2, 1]    = -5
  [-4, -2, 1,-3] = -8
  [-2, 1]        = -1  ← max
  [-2, 1, -3]    = -4
  [1, -3]        = -2

Output: -1
```

**Example 3:**
```
Input:  arr[] = [1, -2, 2, -3], k = 3
Output: 1

Subarrays of size ≥ 3:
  [1, -2, 2]      = 1  ← max
  [1, -2, 2, -3]  = -2
  [-2, 2, -3]     = -3

Output: 1
```

## Constraints
- 1 ≤ k ≤ arr.size() ≤ 10⁵
- -10⁵ ≤ arr[i] ≤ 10⁵

---

## Approach — Mandatory Window of k + Greedy Prefix Extension

**Key Insight:** Think of the subarray in two parts:
- **Mandatory block** — a sliding window of exactly k elements (always included).
- **Optional prefix** — elements to the left of the window that we can keep or drop.

As the right pointer advances (expanding the window), we accumulate the optional prefix in `last`. If `last` turns negative, dropping it strictly improves the sum — so we reset it to 0 (greedy discard).

### Why the Prefix, Not the Suffix?

As we move right, new elements are added to the right — they are always part of the window. The "old" left-side elements accumulate in `last`. Since we can freely extend left, dropping a negative prefix is safe (a larger window without those elements is still ≥ k elements long).

### Algorithm

1. Compute `sum` of the first k elements as the initial mandatory window.
2. Set `maxSum = sum`, `last = 0`.
3. Slide the right pointer from `k` to `n-1`:
   - Add `a[r]` to `sum` (extend right).
   - Add `a[l]` to `last`, advance `l` (accumulate prefix).
   - Update `maxSum = max(maxSum, sum)`.
   - If `last < 0` → drop the prefix: `sum -= last`, `last = 0`, update `maxSum`.
4. Return `maxSum`.

### Walkthrough

```
arr = [1, -2, 2, -3],  k = 3

Initial window [1, -2, 2]: sum = 1,  maxSum = 1,  last = 0

r=3 (a[r]=-3):
  sum  += -3  → sum = -2
  last += a[l=0]=1 → last=1,  l=1
  maxSum = max(1, -2) = 1
  last=1 >= 0 → no discard

Loop ends.  Output: 1  ✓
```

```
arr = [-4, -2, 1, -3],  k = 2

Initial window [-4, -2]: sum = -6,  maxSum = -6,  last = 0

r=2 (a[r]=1):
  sum  += 1   → sum = -5
  last += a[l=0]=-4 → last=-4,  l=1
  maxSum = max(-6, -5) = -5
  last=-4 < 0 → discard: sum -= -4 → sum=-1, last=0
  maxSum = max(-5, -1) = -1

r=3 (a[r]=-3):
  sum  += -3  → sum = -4
  last += a[l=1]=-2 → last=-2,  l=2
  maxSum = max(-1, -4) = -1
  last=-2 < 0 → discard: sum -= -2 → sum=-2, last=0
  maxSum = max(-1, -2) = -1

Output: -1  ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — single pass, each element visited once |
| **Space** | O(1) — only scalar variables used |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Using plain Kadane's without enforcing minimum size k**
Kadane's may return a subarray smaller than k. The mandatory initial window of size k ensures the constraint.

**Mistake 2: Discarding the suffix instead of the prefix**
The discard logic applies to the **accumulated prefix** (`last`), not the current element. Always track what has "exited" the mandatory window on the left.

**Mistake 3: Not updating maxSum after the discard**
After trimming a negative prefix (`sum -= last`), the new sum may be the best so far. Always re-check `maxSum` after the discard step.

**Mistake 4: O(n²) brute force — checking all pairs**
For n = 10⁵, checking all subarrays of size ≥ k is too slow. The sliding window brings it to O(n).

---

## Related Problems
- Maximum Subarray (Kadane's) — LeetCode 53
- Maximum Sum of Subarray of Size K (Exactly K) — GFG
- Minimum Size Subarray Sum — LeetCode 209

---

## Tags

Sliding Window | Kadane's Algorithm | Prefix Sum | Array | Maximum Subarray | At Least K Elements | Greedy | Java | GeeksForGeeks | Max Sum Subarray Size at Least K

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/MaxSumSubarrayOfSizeAtLeastK

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ Why plain Kadane's fails when minimum size k is required
✅ Mandatory window of k + greedy optional prefix — the full intuition
✅ When and why to discard the negative prefix (not the suffix!)
✅ O(n) time · O(1) space solution with full dry run
✅ Common traps — missing the post-discard maxSum update

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Max Sum Subarray of Size at Least K · Sliding Window · Kadane's Algorithm · Prefix Sum · Array · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/largest-sum-subarray-of-size-at-least-k3121/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#GeeksforGeeks #array #SlidingWindow #KadanesAlgorithm #MaxSumSubarray
#CodingInterview #DSA #Algorithms #GFGPOTD #Programming #ProblemSolving
#OBrutus #TechInterview #DataStructures #JavaProgramming #DailyChallenge
#DSAWithOBrutus #KodeLoad #greedy #PrefixSum #AtLeastK
```
