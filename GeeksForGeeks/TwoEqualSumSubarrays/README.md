# Split an Array into Two Equal Sum Subarrays
---
> video description: https://youtu.be/D5KQJqI7iHI

[Problem Link](https://www.geeksforgeeks.org/problems/split-an-array-into-two-equal-sum-subarrays/1) | [Java Solution](./Solution.java)

[![image](https://img.youtube.com/vi/D5KQJqI7iHI/0.jpg
)](https://youtu.be/D5KQJqI7iHI)

---

> **Can you cut the array in half — without a sword?**
> Find the magic split point where both sides weigh exactly the same.

Given an array of non-negative integers `arr[]`, determine whether the array can be split into **two contiguous subarrays** such that the sum of elements in both halves is **equal**. Return `true` if such a split exists, otherwise return `false`.

> Think of it like balancing a seesaw — can you find the pivot index where both sides tip equally?

---

## Examples

```
Input:  arr[] = [1, 2, 3, 4, 5, 5]
Output: true
Explanation: arr[0..3] = {1, 2, 3, 4} → sum = 10
             arr[4..5] = {5, 5}        → sum = 10
             Split at index 3 gives two equal halves.
```

```
Input:  arr[] = [4, 1, 2, 3]
Output: true
Explanation: arr[0..1] = {4, 1} → sum = 5
             arr[2..3] = {2, 3} → sum = 5
```

```
Input:  arr[] = [4, 3, 2, 1]
Output: false
Explanation: Total sum = 10 (even), but no prefix sum equals 5.
             No valid split point exists.
```

---

## Approach — Prefix Sum

The core insight: if the total sum is **odd**, no equal split is possible. If it's even, walk the array and check if the running prefix sum ever hits exactly `total / 2` (before reaching the last element).

```
totalSum = sum of all elements
if totalSum is odd → return false

prefixSum = 0
for i from 0 to n-2:
    prefixSum += arr[i]
    if prefixSum == totalSum / 2 → return true

return false
```

> No extra space needed. Just one pass after computing the total.

---

## Complexity

| | |
|---|---|
| **Time**  | O(n) |
| **Space** | O(1) |

---

## Constraints

```
2 ≤ arr.size() ≤ 10^5
0 ≤ arr[i] ≤ 10^6
```

---

## Topic Tags

Arrays | Prefix Sum | Data Structures | Algorithms

---

## FAQ — People Also Ask

**Can you split an array into two parts with equal sum?**
Yes — compute the total sum. If it's odd, it's impossible. Otherwise, use a running prefix sum and check if it equals `totalSum / 2` at any intermediate index.

**What is the time complexity of finding equal sum partition?**
O(n) — a single pass with prefix sum is all you need.

**How do you check if an array can be divided into two equal halves?**
The split is valid only when `prefixSum == totalSum - prefixSum`, which simplifies to `prefixSum == totalSum / 2`.
