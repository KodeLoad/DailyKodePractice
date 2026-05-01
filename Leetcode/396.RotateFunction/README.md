# Rotate Function | LeetCode 396 | O(n) Math Trick

---
> Video description: https://youtu.be/EUbHyvTVNQ4

[Problem](https://leetcode.com/problems/rotate-function/) | [Java Solution](./java_solution/Solution.java)

[![img](https://img.youtube.com/vi/EUbHyvTVNQ4/0.jpg)](https://youtu.be/EUbHyvTVNQ4)

---

**Difficulty:** Medium  
**Topics:** Array | Math | Dynamic Programming  
**Companies:** Amazon | Google | Microsoft

---

## Problem Statement

You are given an integer array `nums` of length `n`. Define a rotation function `F(k)` for rotation `k` as:

```
F(k) = 0 * Bk[0] + 1 * Bk[1] + 2 * Bk[2] + ... + (n-1) * Bk[n-1]
```

where `Bk` is the array after rotating `nums` left by `k` positions.

Return the **maximum value** of `F(k)` across all possible rotations `k = 0, 1, ..., n-1`.

## Examples

**Example 1:**
```
Input:  nums = [4, 3, 2, 6]
Output: 26

F(0) = 0*4 + 1*3 + 2*2 + 3*6 = 0  + 3 + 4  + 18 = 25
F(1) = 0*6 + 1*4 + 2*3 + 3*2 = 0  + 4 + 6  + 6  = 16
F(2) = 0*2 + 1*6 + 2*4 + 3*3 = 0  + 6 + 8  + 9  = 23
F(3) = 0*3 + 1*2 + 2*6 + 3*4 = 0  + 2 + 12 + 12 = 26  ← max
```

**Example 2:**
```
Input:  nums = [100]
Output: 0

Explanation: Only one element, F(0) = 0 * 100 = 0.
```

## Constraints
- 1 ≤ n ≤ 10⁵
- -100 ≤ nums[i] ≤ 100

---

## Approach — O(n) Math Derivation

### Brute Force: O(n²)

Computing F(k) from scratch for each of the n rotations costs O(n) each → O(n²) total. Too slow for n = 10⁵.

### Key Observation

Notice how `F(k)` relates to `F(k-1)`:

```
arr = [a, b, c, d]   (n = 4,  arraySum = a+b+c+d)

F(0) =  0a + 1b + 2c + 3d
F(1) =  0d + 1a + 2b + 3c
      = (0a + 1b + 2c + 3d)   ← F(0)
        + (a  + b  + c  + d)  ← + arraySum
        - 4d                  ← - n * removed_element
      = F(0) + arraySum - n*d
```

**General recurrence:**

```
F(k) = F(k-1) + arraySum - n * nums[n-k]
```

This lets us derive every rotation's value from the previous one in **O(1)**, giving an overall **O(n)** solution.

### Step-by-Step Walkthrough

```
nums = [4, 3, 2, 6]
n = 4,  arraySum = 15

F(0) = 0*4 + 1*3 + 2*2 + 3*6 = 25

rotate once  →  last element removed = nums[3] = 6
F(1) = 25 + 15 - 4*6 = 25 + 15 - 24 = 16

rotate once  →  last element removed = nums[2] = 2
F(2) = 16 + 15 - 4*2 = 16 + 15 - 8  = 23

rotate once  →  last element removed = nums[1] = 3
F(3) = 23 + 15 - 4*3 = 23 + 15 - 12 = 26  ← max

Output: 26
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — one pass for F(0), one pass for rotations |
| **Space** | O(1) — no extra data structures |

---

## Related Problems
- Best Time to Buy and Sell Stock
- Maximum Sum Circular Subarray
- Rotate Array

## Tags
`array` `math` `rotate-function` `dynamic-programming` `prefix-sum` `O(n)` `java` `leetcode-396`
