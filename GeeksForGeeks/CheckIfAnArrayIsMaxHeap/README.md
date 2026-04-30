# Check If an Array Represents a Max Heap

---
> Video description: https://youtu.be/LmIJXPRPedU

[Problem](https://www.geeksforgeeks.org/problems/does-array-represent-heap4345/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/LmIJXPRPedU/0.jpg)](https://youtu.be/LmIJXPRPedU)
---


Given an array, check whether the array represents a Max Heap or not.

Examples:
```
Input: arr[] = [90, 15, 10, 7, 12, 2]
Output: true
Explanation: 90 is root, its children 15 and 10 are smaller. Their children 7, 12 and 2 are also smaller than their parents.
```

```
Input: arr[] = [9, 15, 10, 7, 12, 11]
Output: false
Explanation: 15 is greater than its parent 9 — violates the max heap property.
```

## Max Heap Property

In a max heap represented as an array:
- Every node must be **greater than or equal to** its children.
- For a node at index `i`:
  - Left child  → `2*i + 1`
  - Right child → `2*i + 2`
  - Parent      → `(i - 1) / 2`

## Approach — Recursive DFS

Starting from the root (index 0), for each node check that its value does not exceed its parent's value, then recurse into its left and right children.

```
arr = [90, 15, 10, 7, 12, 2]

          90          index 0
        /    \
      15      10      index 1, 2
     /  \    /
    7   12  2         index 3, 4, 5

Each child < parent → true
```

> Constraints:
> 1 ≤ arr.size() ≤ 10^5
> 1 ≤ arr[i] ≤ 10^5
