# Position of the Only Set Bit | Bit Manipulation | GeeksForGeeks

---
> Video description: https://youtu.be/N0TZx834n2k

[Problem](https://www.geeksforgeeks.org/problems/find-position-of-set-bit3706/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/N0TZx834n2k/0.jpg)](https://youtu.be/N0TZx834n2k)

---

**Difficulty:** Basic  
**Topics:** Bit Manipulation | Math | Logarithm  
**Companies:** Amazon | Flipkart | Morgan Stanley

---

## Problem Statement

Given a number `n`, find the **1-based position of the only set bit** in its binary representation. If `n` has more than one set bit, return `-1`.

> A "set bit" is a bit with value `1` in the binary representation.

## Examples

**Example 1:**
```
Input:  n = 2
Output: 2

Binary: 10
         ^--- bit at position 2 is set
```

**Example 2:**
```
Input:  n = 4
Output: 3

Binary: 100
          ^--- bit at position 3 is set
```

**Example 3:**
```
Input:  n = 6
Output: -1

Binary: 110  →  two bits are set → return -1
```

**Example 4:**
```
Input:  n = 8
Output: 4

Binary: 1000
           ^--- bit at position 4 is set
```

## Constraints
- 1 ≤ n ≤ 10⁸

---

## Approach — Bit Trick + Log₂

### Step 1: Check exactly one set bit

A number with exactly one set bit is a **power of 2**. Powers of 2 satisfy this property:

```
n     →  ... 1 0 0 0
n - 1 →  ... 0 1 1 1
              -------
n & (n-1) = 0   ✓ only one set bit

n = 6  →  1 1 0
n - 1  →  1 0 1
           -----
n & (n-1) = 1 0 0 ≠ 0  ✗ more than one set bit → return -1
```

### Step 2: Find the position

A number with one set bit is a power of 2:

```
2^0 = 1   → position 1
2^1 = 2   → position 2
2^2 = 4   → position 3
2^x = n   → position x + 1
```

So the 1-based position = `log₂(n) + 1`.

### Walkthrough

```
n = 16

Step 1: 16 & 15 = 10000 & 01111 = 0 → exactly one set bit ✓
Step 2: log₂(16) + 1 = 4 + 1 = 5

Output: 5

Verify: 16 = 1 0 0 0 0
                    ^--- position 5 (1-based) ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(1) — constant time bitwise and log operations |
| **Space** | O(1) — no extra space used |

---

## Related Problems
- Count Set Bits in an Integer
- Power of Two
- Bit Difference

## Tags
`bit-manipulation` `power-of-two` `logarithm` `set-bit` `bitwise-AND` `java` `geeksforgeeks`
