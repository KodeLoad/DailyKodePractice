# Palindrome in Binary | Bit Manipulation | GeeksForGeeks

---
> Video description: https://youtu.be/H9JBCpZnFlw

[Problem](https://www.geeksforgeeks.org/problems/palindrome-numbers0942/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/H9JBCpZnFlw/0.jpg)](https://youtu.be/H9JBCpZnFlw)

---

**Difficulty:** Easy  
**Topics:** Bit Manipulation | Binary Representation | Two Pointers  
**Companies:** Amazon | Paytm | Zoho

---

## Problem Statement

Given a number `n`, check whether its **binary representation is a palindrome** or not. Return `true` if it is a palindrome, `false` otherwise.

> A binary palindrome reads the same from left to right as it does from right to left.

## Examples

**Example 1:**
```
Input:  n = 5
Output: true

Binary: 101
        ^ ^
        palindrome ✓
```

**Example 2:**
```
Input:  n = 10
Output: false

Binary: 1010
        not the same forwards and backwards ✗
```

**Example 3:**
```
Input:  n = 9
Output: true

Binary: 1001
        ^  ^
        palindrome ✓
```

**Example 4:**
```
Input:  n = 7
Output: false

Binary: 111 → palindrome, but...

Wait — 7 in binary is 111 which IS a palindrome.
```

## Constraints
- 1 ≤ n ≤ 10⁵

---

## Approach 1 — String Conversion

Convert `n` to its binary string, then check if the string equals its reverse.

```
n = 5  →  "101"  →  reverse = "101"  →  equal ✓
n = 10 →  "1010" →  reverse = "0101" →  not equal ✗
```

Simple but uses O(log n) extra space for the string.

---

## Approach 2 — Pure Bit Manipulation (No Strings)

**Key Insight:** Reverse the bits of `n` using bitwise operations and compare with the original.

### How to Reverse Bits

Build the reversed number bit by bit — extract the LSB (last bit) of `n` and append it to `rev`:

```
n = 5  →  binary: 1 0 1

Step 1:  rev = 0 << 1 = 0       →  make space
         rev = 0 | (5 & 1) = 1  →  take LSB of n (1)
         n   = 5 >> 1 = 2       →  drop LSB

Step 2:  rev = 1 << 1 = 10      →  make space
         rev = 10 | (2 & 1) = 10 →  take LSB of n (0)
         n   = 2 >> 1 = 1       →  drop LSB

Step 3:  rev = 10 << 1 = 100    →  make space
         rev = 100 | (1 & 1) = 101 →  take LSB of n (1)
         n   = 1 >> 1 = 0       →  done

rev = 101 = 5 = ori  →  true ✓
```

### Walkthrough — Non-Palindrome

```
n = 10  →  binary: 1 0 1 0

Step 1:  take 0  →  rev = 0
Step 2:  take 1  →  rev = 01
Step 3:  take 0  →  rev = 010
Step 4:  take 1  →  rev = 0101 = 5

rev (5) ≠ ori (10)  →  false ✗
```

### Complexity

| | Value |
|---|---|
| **Time** | O(log n) — one bit processed per iteration |
| **Space** | O(1) — only integer variables used |

---

## Related Problems
- Check if a Number is Palindrome
- Reverse Bits of an Integer
- Number of 1 Bits

## Tags
`bit-manipulation` `binary` `palindrome` `bitwise-operations` `reverse-bits` `LSB` `java` `geeksforgeeks`
