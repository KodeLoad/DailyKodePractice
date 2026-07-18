# Find Greatest Common Divisor of Array | LeetCode 1979 | Euclidean Algorithm

---
> Video description: https://youtu.be/slvWVLDkXRw

[Problem](https://leetcode.com/problems/find-greatest-common-divisor-of-array/) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/slvWVLDkXRw/0.jpg)](https://youtu.be/slvWVLDkXRw)

---

**Difficulty:** Easy  
**Topics:** Array | Math | Number Theory | Euclidean Algorithm  
**Companies:** Amazon | Google | Adobe  
**Time Complexity:** O(n + log(min)) | **Space Complexity:** O(1)

---

## What Is the Greatest Common Divisor (GCD)?

The **GCD** of two integers is the **largest positive integer that divides both** without a remainder.

```
gcd(12, 8) = 4    →  4 divides both 12 and 8
gcd(7, 3)  = 1    →  only 1 divides both (co-prime)
gcd(9, 6)  = 3    →  3 divides both 9 and 6
```

---

## Problem Statement — GCD of the Smallest and Largest Elements in an Array

Given an integer array `nums`, return the **greatest common divisor** of the **smallest** and **largest** numbers in the array.

## Examples

**Example 1:**
```
Input:  nums = [2, 5, 6, 9, 10]
Output: 2

min = 2,  max = 10
gcd(2, 10) = 2  ✓
```

**Example 2:**
```
Input:  nums = [7, 5, 6, 8, 3]
Output: 1

min = 3,  max = 8
gcd(3, 8) = 1  ✓  (co-prime)
```

**Example 3:**
```
Input:  nums = [3, 3]
Output: 3

min = 3,  max = 3
gcd(3, 3) = 3  ✓
```

## Constraints
- 2 ≤ nums.length ≤ 1000
- 1 ≤ nums[i] ≤ 1000

---

## Approach — Find Min/Max + Euclidean GCD

The problem only asks for `gcd(min, max)`, not the GCD of the entire array. So the solution is two clean steps:

1. **Single pass** to find the minimum and maximum elements — O(n).
2. **Euclidean algorithm** to compute `gcd(min, max)` — O(log(min)).

### Euclidean Algorithm (Recursive)

The Euclidean algorithm is the fastest known method to compute GCD:

```
gcd(a, b):
  if b == 0 → return a
  return gcd(b, a % b)

gcd(12, 8):
  gcd(8, 12%8) = gcd(8, 4)
  gcd(4, 8%4)  = gcd(4, 0)
  b == 0       → return 4  ✓
```

Each call reduces the problem size — the number of steps is bounded by O(log(min(a,b))).

### Walkthrough

```
nums = [2, 5, 6, 9, 10]

Step 1 — Find min and max:
  Start: min=2, max=2
  i=1: a[1]=5  → max=5
  i=2: a[2]=6  → max=6
  i=3: a[3]=9  → max=9
  i=4: a[4]=10 → max=10

  min=2,  max=10

Step 2 — Euclidean GCD:
  gcd(2, 10):
    gcd(10, 2%10)... wait, need a >= b for clarity:
    gcd(2, 10) → gcd(10, 2%10)... 

  Actually: gcd(2, 10)
    b=10 ≠ 0 → gcd(10, 2%10) = gcd(10, 2)
    b=2  ≠ 0 → gcd(2, 10%2)  = gcd(2, 0)
    b=0       → return 2

Output: 2  ✓
```

```
nums = [7, 5, 6, 8, 3]

min=3,  max=8

gcd(3, 8):
  gcd(8, 3%8)  = gcd(8, 3)
  gcd(3, 8%3)  = gcd(3, 2)
  gcd(2, 3%2)  = gcd(2, 1)
  gcd(1, 2%1)  = gcd(1, 0)
  b=0 → return 1

Output: 1  ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) for min/max scan + O(log min) for GCD = O(n) overall |
| **Space** | O(log min) recursion stack for GCD (or O(1) with iterative) |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Computing GCD of the entire array**
The problem only asks for `gcd(min(nums), max(nums))` — not the GCD of all elements. Reading carefully saves time.

**Mistake 2: Using `Arrays.sort()` to find min and max — O(n log n)**
Sorting to get min/max is wasteful. A single linear scan is O(n) and O(1) space.

**Mistake 3: Assuming gcd(a, b) == gcd(min, max) always equals the GCD of all elements**
```
nums = [4, 6, 8]:  gcd(4, 8) = 4,  but gcd(4, 6, 8) = 2
```
They are different. This problem specifically wants `gcd(min, max)` only.

---

## Related Problems
- Greatest Common Divisor Traversal — LeetCode 2709
- Three Divisors — LeetCode 1952
- GCD of Strings — LeetCode 1071

---

## Tags

GCD | Euclidean Algorithm | Array | Math | Number Theory | Min Max | Java | LeetCode 1979 | Find GCD of Array | Easy

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/Leetcode/1979.FindGreatestCommonDivisorOfArray

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ What GCD means and how the Euclidean algorithm works
✅ Why only gcd(min, max) is needed — not the full array GCD
✅ Single-pass min/max find + recursive Euclidean GCD
✅ O(n) time · O(1) space solution
✅ Common trap: computing GCD of all elements vs. just min and max

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Find GCD of Array · Euclidean Algorithm · Number Theory · Math · LeetCode 1979 · Java · LeetCode Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://leetcode.com/problems/find-greatest-common-divisor-of-array/

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#FindGCDOfArray #LeetCode1979 #EuclideanAlgorithm #GCD #LeetCodeDaily
#JavaProgramming #NumberTheory #MathProblems #DSAWithOBrutus #KodeLoad
```
