# Buildings Receiving Sunlight | Stack | Monotonic Stack | GeeksForGeeks
---
> video walkthrough: https://www.youtube.com/watch?v=WgDLEPYvjaI&ab_channel=OBrutus

[Problem Link](https://www.geeksforgeeks.org/problems/buildings-receiving-sunlight3032/1) | [Java Solution](./Solution.java)

[![thumbnail](https://img.youtube.com/vi/WgDLEPYvjaI/0.jpg
)](https://www.youtube.com/watch?v=WgDLEPYvjaI&ab_channel=OBrutus)

---

## Problem Statement

Given an array `height[]` of `N` buildings standing in a straight line, count the number of buildings that receive sunlight. The sun rises from the **east** (left side of the array). A building receives sunlight only if **no building to its left is taller than or equal to it** — in other words, it must be strictly taller than all buildings preceding it.

## Examples

```
Input: N = 5, height[] = {7, 4, 8, 2, 9}
Output: 3
Explanation: Building at index 0 (height 7) always gets sunlight.
             Building at index 1 (height 4) is blocked by height 7.
             Building at index 2 (height 8) is taller than all to its left → sunlight.
             Building at index 3 (height 2) is blocked by height 8.
             Building at index 4 (height 9) is taller than all to its left → sunlight.
             Total = 3
```

```
Input: N = 4, height[] = {2, 3, 4, 5}
Output: 4
Explanation: Each building is strictly taller than all buildings to its left,
             so all 4 buildings receive sunlight.
```

```
Input: N = 3, height[] = {5, 3, 2}
Output: 1
Explanation: Only the first building receives sunlight; every subsequent
             building is shorter and blocked.
```

## Approach — Linear Scan (Monotonic Maximum)

Keep track of the **maximum height seen so far** from the left. For each building:
- If its height is **strictly greater** than the running maximum, it receives sunlight → increment count and update the maximum.
- Otherwise, it is blocked.

```
maxHeight = height[0], count = 1
for i from 1 to N-1:
    if height[i] > maxHeight:
        count++
        maxHeight = height[i]
return count
```

## Complexity Analysis

| Metric | Value |
|---|---|
| Time Complexity | O(N) |
| Space Complexity | O(1) |

## Constraints

- 1 ≤ N ≤ 10^6
- 1 ≤ height[i] ≤ 10^8

## Company Tags

Amazon | Microsoft | Adobe

## Topic Tags

Arrays | Stack | Monotonic Stack | Greedy | Data Structures | Algorithms
