# Minimum Swaps to Group all 1's Together

---
> Video description: https://youtu.be/N2THnczq7Bk

[Problem](https://www.geeksforgeeks.org/problems/minimum-swaps-required-to-group-all-1s-together2451/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/N2THnczq7Bk/0.jpg)](https://youtu.be/N2THnczq7Bk)
---


Given an array of 0's and 1's, find the minimum number of swaps required to group all 1's together.

Examples:
```
Input: arr[] = [1, 0, 1, 0, 1, 1]
Output: 1
Explanation: Swap index 1 and 5 (or 3) to get [1, 1, 1, 1, 0, 0]. Only 1 swap needed.
```

```
Input: arr[] = [1, 0, 1, 0, 1]
Output: 1
Explanation: Swap to get [1, 1, 1, 0, 0]. Minimum 1 swap is required.
```

```
Input: arr[] = [1, 1, 1, 1]
Output: 0
Explanation: All 1's are already grouped together.
```

## Approach — Sliding Window

1. Count total number of 1's — this is the window size.
2. Slide a window of that size across the array.
3. Count the number of 0's inside each window position.
4. Minimum 0-count across all windows = minimum swaps needed (each 0 inside the window must be swapped with a 1 outside).

```
arr = [1, 0, 1, 0, 1, 1]  →  four 1's  →  window size = 4

[1, 0, 1, 0] 1  1   zeros = 2
 1 [0, 1, 0, 1] 1   zeros = 2
 1  0 [1, 0, 1, 1]  zeros = 1  ← minimum → answer = 1
```

> Constraints:
> 1 ≤ arr.size() ≤ 10^5
> 0 ≤ arr[i] ≤ 1
