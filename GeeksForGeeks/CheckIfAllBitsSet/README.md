# Check If All Bits Are Set | O(1) Bit Trick | GeeksForGeeks

---
> Video description: https://youtu.be/-BPk5Qgn4m4

[Problem](https://www.geeksforgeeks.org/problems/check-set-bits5408/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/-BPk5Qgn4m4/0.jpg)](https://youtu.be/-BPk5Qgn4m4)

---

**Difficulty:** Basic  
**Topics:** Bit Manipulation | Bitwise AND | Math  
**Companies:** Amazon | Adobe | Paytm  
**Time Complexity:** O(1) | **Space Complexity:** O(1)

---

## What Does "All Bits Set" Mean?

A number has **all bits set** if every bit in its binary representation is `1` — in other words, it is a **number of the form 2ⁿ - 1**: `1`, `3`, `7`, `15`, `31`, `63`, ...

```
1   →  1         ✓ all bits set
3   →  11        ✓ all bits set
7   →  111       ✓ all bits set
15  →  1111      ✓ all bits set
6   →  110       ✗ not all bits set (LSB is 0)
10  →  1010      ✗ not all bits set
```

---

## Problem Statement — Check Whether All Bits of a Number Are Set

Given a positive integer `n`, return `true` if **all bits** in its binary representation are `1`, otherwise return `false`.

## Examples

**Example 1:**
```
Input:  n = 7
Output: true

Binary: 111  →  all three bits are 1 ✓
```

**Example 2:**
```
Input:  n = 14
Output: false

Binary: 1110  →  LSB is 0 ✗
```

**Example 3:**
```
Input:  n = 15
Output: true

Binary: 1111  →  all four bits are 1 ✓
```

**Example 4:**
```
Input:  n = 0
Output: false

0 has no set bits → false
```

## Constraints
- 0 ≤ n ≤ 10⁹

---

## Approach 1 — Brute Force: O(log n)

Check each bit one by one using a right-shift loop. Return `false` the moment any `0` bit is found.

```
while (n > 0):
    if (n & 1) == 0 → return false
    n >>= 1
return true
```

Works, but O(log n). We can do better.

---

## Approach 2 — O(1) Bit Trick: n & (n+1) == 0

**Key Insight:** A number with all bits set looks like `111...1` in binary. Adding `1` to it flips all bits and produces a power of 2: `1000...0`. When you AND these two together the result is always `0`.

```
n     =  0111  (7)
n + 1 =  1000  (8)
n & (n+1) = 0000 = 0  →  all bits set ✓

n     =  1010  (10)
n + 1 =  1011  (11)
n & (n+1) = 1010 ≠ 0  →  not all bits set ✗

n     =  1111  (15)
n + 1 = 10000  (16)
n & (n+1) = 00000 = 0  →  all bits set ✓
```

So the one-liner check is:

```java
return (n & (n + 1)) == 0;
```

### Walkthrough

```
n = 7   →  0111
n+1 = 8 →  1000
            ----
n & (n+1) = 0000 → 0 == 0 → true ✓

n = 6   →  0110
n+1 = 7 →  0111
            ----
n & (n+1) = 0110 → 6 ≠ 0 → false ✗

n = 0   →  edge case → return false directly
```

### Complexity

| | Value |
|---|---|
| **Time** | O(1) — single bitwise AND operation |
| **Space** | O(1) — no extra space used |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Not handling n = 0**
`0 & 1 == 0` would incorrectly return `true` for `n = 0`. Always special-case zero.

**Mistake 2: Using a loop when O(1) exists**
Checking each bit in a loop is O(log n). The `n & (n+1) == 0` trick does it in one operation.

**Mistake 3: Confusing "all bits set" with "power of 2"**
Power of 2 check: `n & (n-1) == 0` (only one bit set).  
All bits set check: `n & (n+1) == 0` (every bit is 1).

---

## Related Problems
- Position of the Only Set Bit
- Power of Two — LeetCode 231
- Count Set Bits (Brian Kernighan's Algorithm)
- Reverse Bits — LeetCode 190

---

## Tags

Bit Manipulation | Bitwise AND | All Bits Set | Power of Two | O(1) | Java | GeeksForGeeks | Check Set Bits | isBitSet
