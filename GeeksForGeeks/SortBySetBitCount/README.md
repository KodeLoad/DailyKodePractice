# Sort by Set Bit Count | Bit Manipulation | Custom Comparator | GeeksForGeeks

---
> Video description: https://youtu.be/e5ZuC1MLTAE

[Problem](https://www.geeksforgeeks.org/problems/sort-by-set-bit-count1153/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/e5ZuC1MLTAE/0.jpg)](https://youtu.be/e5ZuC1MLTAE)

---

**Difficulty:** Easy  
**Topics:** Bit Manipulation | Sorting | Custom Comparator  
**Companies:** Amazon | Paytm | Samsung

---

## Problem Statement

Given an array of integers, sort the array in **decreasing order of set bit count** (number of `1`s in binary representation). Elements with equal set bit counts must maintain their **original relative order** (stable sort).

> A "set bit" is a bit with value `1` in the binary representation of a number.

## Examples

**Example 1:**
```
Input:  arr[] = [5, 2, 3, 9, 4, 6, 7, 15, 32]
Output: [15, 7, 5, 3, 9, 6, 2, 4, 32]

Set bit counts:
  15  → 1111  → 4 bits
   7  → 0111  → 3 bits
   5  → 0101  → 2 bits ┐
   3  → 0011  → 2 bits ├── original order preserved
   9  → 1001  → 2 bits ┘
   6  → 0110  → 2 bits ┐
   2  → 0010  → 1 bit  ├── original order preserved
   4  → 0100  → 1 bit  ┘
  32  → 100000 → 1 bit
```

**Example 2:**
```
Input:  arr[] = [1, 2, 3, 4, 5, 6]
Output: [3, 5, 6, 1, 2, 4]

  3 → 011 → 2 bits ┐ original order
  5 → 101 → 2 bits ┘ preserved
  6 → 110 → 2 bits ┘
  1 → 001 → 1 bit  ┐ original order
  2 → 010 → 1 bit  ├── preserved
  4 → 100 → 1 bit  ┘
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁶

---

## Approach — Custom Comparator Sort

Use a **stable sort** with a custom comparator that compares numbers by their set bit count in descending order.

Java's `Integer.bitCount(n)` counts set bits in O(1) using hardware intrinsics.

```
sort by:  bitCount(b) - bitCount(a)   →  descending order of set bits
```

### Why Stable Sort?

The problem requires elements with equal set bit counts to keep their original relative order. Java's `Collections.sort` is a stable merge sort, so equal-count elements are never swapped.

### Walkthrough

```
arr = [5, 2, 3, 9, 4, 6, 7, 15, 32]

Compute set bit counts:
  5  → 101  → 2
  2  → 010  → 1
  3  → 011  → 2
  9  → 1001 → 2
  4  → 100  → 1
  6  → 110  → 2
  7  → 111  → 3
  15 → 1111 → 4
  32 → 100000 → 1

Sort descending by count (stable):
  15 (4) → 7 (3) → 5,3,9,6 (2) → 2,4,32 (1)

Output: [15, 7, 5, 3, 9, 6, 2, 4, 32]
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n log n) — sorting with O(1) comparator |
| **Space** | O(n) — output ArrayList |

---

## Bit Counting Reference

```
n       binary      set bits
1     → 0001    →   1
2     → 0010    →   1
3     → 0011    →   2
4     → 0100    →   1
5     → 0101    →   2
6     → 0110    →   2
7     → 0111    →   3
8     → 1000    →   1
15    → 1111    →   4
```

---

## Related Problems
- Position of the Only Set Bit
- Count Set Bits in an Integer
- Sort Array by Increasing Frequency

## Tags
`bit-manipulation` `bitcount` `sorting` `custom-comparator` `stable-sort` `set-bits` `java` `geeksforgeeks`
