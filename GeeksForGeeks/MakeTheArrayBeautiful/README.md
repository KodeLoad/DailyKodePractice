# Make the Array Beautiful | Remove Opposite Sign Adjacent Elements | Stack Greedy | GeeksForGeeks

---
> Video description: https://youtu.be/O5uXsmId654

[Problem](https://www.geeksforgeeks.org/problems/make-the-array-beautiful--170647/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/O5uXsmId654/0.jpg)](https://youtu.be/O5uXsmId654)

---

**Difficulty:** Easy  
**Topics:** Array | Stack | Greedy | Sign Detection  
**Companies:** Amazon | Flipkart | Adobe  
**Time Complexity:** O(n) | **Space Complexity:** O(n)

---

## What Is a Beautiful Array?

An array is **beautiful** if **no two adjacent elements have opposite signs** — every adjacent pair must be either both non-negative or both negative.

The goal is to remove the **minimum number of elements** from the given array to make it beautiful, then return the result.

> Note: `0` is treated as non-negative (positive sign).

---

## Problem Statement — Make Array Beautiful by Removing Minimum Elements

Given an integer array `arr`, remove the minimum number of elements so that no two adjacent elements have opposite signs. Return the resulting beautiful array.

## Examples

**Example 1:**
```
Input:  arr[] = [4, 2, -2, 1]
Output: [4, 1]

The pair (2, -2) are adjacent with opposite signs → both removed.
Remaining [4, 1] are both positive → beautiful ✓
```

**Example 2:**
```
Input:  arr[] = [4, -2, 2, 1]
Output: [2, 1]

4 (positive) meets -2 (negative) → 4 is removed (cancelled).
Remaining [2, 1] are both positive → beautiful ✓
```

**Example 3:**
```
Input:  arr[] = [4, 0, -2]
Output: [4, 0]

0 is treated as non-negative → (0, -2) are opposite signs → -2 removed.
[4, 0] both non-negative → beautiful ✓
```

**Example 4:**
```
Input:  arr[] = [1, 2, 3, 4]
Output: [1, 2, 3, 4]

All elements are positive → already beautiful, nothing removed ✓
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁵
- -10⁹ ≤ arr[i] ≤ 10⁹

---

## Approach — Greedy Stack to Cancel Opposite Sign Adjacent Elements

**Key Insight:** Process the array left to right. Maintain a stack of "kept" elements. When the incoming element has the **opposite sign** to the stack's top, the top is cancelled (removed). This is a greedy cancellation — the last kept element is always the best candidate to undo.

Think of it like: you're building a result list. Whenever a new element conflicts with the previous one (opposite signs), undo that previous element instead of keeping the conflict.

### Algorithm

1. For each element `n` in the array:
   - If the stack is empty → push `n`.
   - Else compare signs of `stack.peek()` and `n`:
     - **Same sign** → push `n` (both can coexist).
     - **Opposite signs** → pop the top (cancel it); do NOT push `n`.
2. Return the stack as the result.

### Walkthrough — Step by Step

```
arr = [4, 2, -2, 1]

n=4:   stack=[]          → push         stack=[4]
n=2:   peek=4(+), n=2(+) same sign      → push   stack=[4,2]
n=-2:  peek=2(+), n=-2(-) opposite sign → pop     stack=[4]
n=1:   peek=4(+), n=1(+) same sign      → push   stack=[4,1]

Output: [4, 1]
```

```
arr = [4, -2, 2, 1]

n=4:   stack=[]           → push          stack=[4]
n=-2:  peek=4(+), n=-2(-) opposite sign   → pop    stack=[]
n=2:   stack=[]           → push          stack=[2]
n=1:   peek=2(+), n=1(+)  same sign       → push  stack=[2,1]

Output: [2, 1]
```

```
arr = [4, 0, -2]

n=4:   stack=[]           → push           stack=[4]
n=0:   peek=4(+), n=0(+)  same sign        → push  stack=[4,0]
n=-2:  peek=0(+), n=-2(-) opposite sign    → pop   stack=[4]

Output: [4]
```

### Why Not Push the Incoming Element on Conflict?

When a conflict occurs, we pop the top but do **not** push the incoming element. The incoming element disappears because it "caused" the removal. This preserves the minimum deletions property — we remove one element from the kept list per conflict rather than accumulating opposing elements.

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — each element is pushed and popped at most once |
| **Space** | O(n) — stack holds at most n elements |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Treating 0 as negative**
```
0 >= 0 → non-negative (positive sign)
sign = (n >= 0) ? 1 : -1
```

**Mistake 2: Pushing the incoming element after a conflict**
When a conflict occurs, the incoming element must also be discarded — only pop, do not push.

**Mistake 3: Using a two-pointer instead of a stack**
Two-pointer can't undo a previously kept element. A stack (LIFO) is needed because a conflict can invalidate the last decision made.

---

## Related Problems
- Asteroid Collision — LeetCode 735
- Remove All Adjacent Duplicates in String — LeetCode 1047
- Next Greater Element — LeetCode 496

---

## Tags

Stack | Greedy | Array | Sign Detection | LIFO Cancellation | Adjacent Elements | Java | GeeksForGeeks | Make Array Beautiful | Remove Opposite Sign Elements
