# Kth Largest Element in a Stream | Min-Heap Sliding Window

---
> Video description: https://youtu.be/9ArsPnQcjz0

[Problem](https://www.geeksforgeeks.org/problems/kth-largest-element-in-a-stream2220/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/9ArsPnQcjz0/0.jpg)](https://youtu.be/9ArsPnQcjz0)

---

**Difficulty:** Medium  
**Topics:** Array | Heap | Priority Queue | Sliding Window  
**Companies:** Amazon | Microsoft | Google

---

## Problem Statement

Given a stream of integers represented as an array `arr[]` and an integer `k`, for each element added to the stream return the **k-th largest element** seen so far. If fewer than `k` elements have been seen, return `-1`.

## Examples

**Example 1:**
```
Input:  k = 4, arr[] = [1, 2, 3, 4, 5, 6]
Output: [-1, -1, -1, 1, 2, 3]

Explanation:
  After seeing [1]           → < 4 elements → -1
  After seeing [1,2]         → < 4 elements → -1
  After seeing [1,2,3]       → < 4 elements → -1
  After seeing [1,2,3,4]     → 4th largest  →  1
  After seeing [1,2,3,4,5]   → 4th largest  →  2
  After seeing [1,2,3,4,5,6] → 4th largest  →  3
```

**Example 2:**
```
Input:  k = 1, arr[] = [10, 20, 30]
Output: [10, 20, 30]

Explanation: k=1 means return the largest element seen so far.
```

## Constraints
- 1 ≤ k ≤ arr.size() ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁵

---

## Approach — Min-Heap of Size k

**Key Insight:** To find the k-th largest at any point, maintain a **min-heap** of exactly `k` elements. The root (minimum of the heap) is always the k-th largest among all elements seen so far.

### Why a Min-Heap?

```
Stream so far (sorted): [1, 2, 3, 4, 5, 6]
                                  ^
                              k=4 largest

Keep the top-k window:  [3, 4, 5, 6]
Min of this window = 3  →  that is the 4th largest
```

A min-heap of size k keeps the k largest elements and surfaces their minimum in O(1).

### Algorithm

1. For each incoming element, push it into the min-heap.
2. If heap size exceeds `k`, pop the smallest (it can never be the k-th largest again).
3. If heap size equals `k`, the root is the answer. Otherwise, output `-1`.

```
k=4, arr = [1, 2, 3, 4, 5, 6]

element=1 → heap=[1]         size<k → -1
element=2 → heap=[1,2]       size<k → -1
element=3 → heap=[1,2,3]     size<k → -1
element=4 → heap=[1,2,3,4]   size=k → peek=1
element=5 → heap=[2,3,4,5]   pop 1  → peek=2
element=6 → heap=[3,4,5,6]   pop 2  → peek=3
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n log k) — each insert/remove is O(log k) |
| **Space** | O(k) — heap holds at most k elements |

---

## Related Problems
- Kth Largest Element in an Array
- Find Median from Data Stream
- Top K Frequent Elements

## Tags
`heap` `priority-queue` `min-heap` `stream` `kth-largest` `sliding-window` `greedy` `java`
