# Maximum Product Subarray | LeetCode 152 | Two-Pass O(n) Solution

---
> Video description: https://youtu.be/Y98sxHNqhrQ

[Problem](https://leetcode.com/problems/maximum-product-subarray/) | [Go Solution](./go_solution/main.go)

[![img](https://img.youtube.com/vi/Y98sxHNqhrQ/0.jpg)](https://youtu.be/Y98sxHNqhrQ)

---

**Difficulty:** Medium  
**Topics:** Array | Dynamic Programming | Two-Pass  
**Companies:** Amazon | LinkedIn | Apple | Microsoft

---

## Problem Statement

Given an integer array `nums`, find a **subarray** that has the **largest product**, and return that product.

The answer is guaranteed to fit in a 32-bit integer.

## Examples

**Example 1:**
```
Input:  nums = [2, 3, -2, 4]
Output: 6

Explanation: Subarray [2, 3] has the largest product 6.
```

**Example 2:**
```
Input:  nums = [-2, 0, -1]
Output: 0

Explanation: The result cannot be 2 because [-2, -1] is not a subarray
             (they are not contiguous). Best is [0].
```

**Example 3:**
```
Input:  nums = [-2, 3, 2, 4]
Output: 24

Explanation: Subarray [3, 2, 4] has product 24.
             Starting from -2 gives a negative product.
```

## Constraints
- 1 ≤ nums.length ≤ 2 × 10⁴
- -10 ≤ nums[i] ≤ 10
- The product of any subarray fits in a 32-bit integer

---

## Why This Is Harder Than Maximum Subarray Sum

With **sum**, a negative number always drags you down — safe to reset.  
With **product**, two negatives make a positive — a large negative could become the max if another negative appears later.

```
[-2,  3,  -4]
  \________/
  product = 24  ← best answer, but you need BOTH negatives
```

A zero is the only guaranteed "reset" point — any product through a zero is zero.

---

## Approach — Two-Pass (Left-to-Right + Right-to-Left)

**Key Insight:**  
- Zeros always break a subarray — reset whenever product hits 0.
- An **odd count of negatives** from one end becomes **even** when scanned from the other end.
- Scanning both **L→R** and **R→L** and taking the max covers all cases.

```
nums = [-2, 3, -4]

L→R:  mul = -2 → -6 → 24     res = 24
R→L:  mul = -4 → -12 → 24    res = 24

Output: 24 ✓


nums = [2, 3, -2, 4]

L→R:  mul = 2 → 6 → -12 → -48    res = 6
R→L:  mul = 4 → -8 → -24 → -48   res = 4

Output: 6 ✓


nums = [-2, 0, -1]

L→R:  mul = -2 → reset(0→1) → -1    res = 0
R→L:  mul = -1 → reset(0→1) → -2    res = 0

Output: 0 ✓
```

### Algorithm

1. Scan left to right — track running product, update max, reset to 1 on zero.
2. Reverse the array, scan again with the same logic.
3. Return the overall max from both passes.

### Walkthrough — Odd Negatives Case

```
nums = [-3, -1, -2]

L→R:
  mul = -3           res = -3
  mul = 3            res =  3
  mul = -6           res =  3   ← misses [-1, -2] = 2

R→L (reversed = [-2, -1, -3]):
  mul = -2           res = -2
  mul = 2            res =  2
  mul = -6           res =  2

Two-pass max = max(3, 2) = 3   ← correct (subarray [-3, -1])
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — two linear scans |
| **Space** | O(1) — no extra data structures |

---

## Related Problems
- Maximum Subarray — LeetCode 53
- Product of Array Except Self — LeetCode 238
- Maximum Product of Three Numbers — LeetCode 628

## Tags
`array` `dynamic-programming` `two-pass` `product-subarray` `negative-numbers` `kadane` `golang` `leetcode-152`
