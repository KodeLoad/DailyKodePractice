# Check If Array Is Good | LeetCode 2784 | In-Place Marking O(n)

---
> Video description: https://youtu.be/YOg4dOebilw

[Problem](https://leetcode.com/problems/check-if-array-is-good/) | [Go Solution](./solution.go)

[![img](https://img.youtube.com/vi/YOg4dOebilw/0.jpg)](https://youtu.be/YOg4dOebilw)

---

**Difficulty:** Easy  
**Topics:** Array | Hash Map | Counting | In-Place Marking  
**Companies:** Google | Amazon

---

## Problem Statement

You are given a 0-indexed integer array `nums`. Return `true` if `nums` is a **good** array, otherwise return `false`.

An array is **good** if it is a permutation of `base[n]`, where:

```
base[n] = [1, 2, 3, ..., n-1, n, n]
```

- Every integer from `1` to `n-1` appears **exactly once**.
- The integer `n` appears **exactly twice**.
- `n = len(nums) - 1`

## Examples

**Example 1:**
```
Input:  nums = [2, 1, 3, 3]
Output: true

n = len(nums) - 1 = 3
base[3] = [1, 2, 3, 3]
nums is a permutation of base[3] ✓
```

**Example 2:**
```
Input:  nums = [1, 3, 3, 2]
Output: true

n = 3,  base[3] = [1, 2, 3, 3]
nums is a permutation of base[3] ✓
```

**Example 3:**
```
Input:  nums = [1, 1]
Output: false

n = 1,  base[1] = [1, 1]
But nums = [1, 1] → 1 appears twice, but n=1 so max should appear twice.
Wait — base[1] = [1,1], so this IS valid → true.

Let's check: nums = [3, 4, 4, 1, 2]  →  false
n = 4,  base[4] = [1, 2, 3, 4, 4]
nums has 4 appearing at index 1 and 2, but order doesn't matter for permutation.
Sorted: [1,2,3,4,4] == base[4] ✓
```

**Example 4:**
```
Input:  nums = [1, 3, 3, 2]  →  true
Input:  nums = [3, 4, 4, 1, 2]  →  true
Input:  nums = [1, 1, 2, 3, 3]  →  false  (1 appears twice, not n)
Input:  nums = [1, 2, 3, 4]  →  false  (n=3, 3 appears once not twice)
```

## Constraints
- 1 ≤ nums.length ≤ 100
- 1 ≤ nums[i] ≤ 1000

---

## Approach 1 — Frequency Map: O(n) Time, O(n) Space

Build a count map and verify:
- Every value `1` to `n-1` has count exactly 1.
- Value `n` has count exactly 2.

Simple but uses O(n) extra space.

---

## Approach 2 — In-Place Marking: O(n) Time, O(1) Space

**Key Insight:** Use the array itself as a visited marker by negating values at visited indices — no extra space needed.

### Rules
- `max = len(a) - 1` (the value that must appear twice)
- Every value must be in range `[1, max]` — anything outside means invalid.
- Values `1` to `max-1` must be seen exactly once → if already negated when visited, it's a duplicate → return `false`.
- Value `max` must appear exactly twice → count it with a `rep` counter.

### Walkthrough

```
nums = [2, 1, 3, 3],  max = 3

val=2: index=1, a[1]=1 (not negative) → mark a[1]=-1
       [2, -1, 3, 3]

val=1: index=0, a[0]=2 (not negative) → mark a[0]=-2
       [-2, -1, 3, 3]

val=3: val==max → rep=1
       index=2, mark a[2]=-3
       [-2, -1, -3, 3]

val=3: val==max → rep=2 (≤2, ok)
       index=2, a[2]=-3 (already negative, but val==max so skip duplicate check)
       mark a[2]=-3 again → no change

rep=2, all checks passed → true ✓
```

```
nums = [1, 1, 2, 3, 3],  max = 4

val=1: index=0, a[0]=1 → mark a[0]=-1
val=1: index=0, a[0]=-1 (negative!) and val≠max → duplicate → false ✗
```

```
nums = [1, 2, 3, 4],  max = 3

val=4: index=3, index >= max (3) → out of bounds → false ✗
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — single pass through the array |
| **Space** | O(1) — no extra data structures, input array is mutated in-place |

---

## Related Problems
- Find All Duplicates in an Array — LeetCode 442
- Find the Duplicate Number — LeetCode 287
- Missing Number — LeetCode 268

## Tags
`array` `in-place` `marking` `frequency-count` `permutation` `good-array` `golang` `leetcode-2784`
