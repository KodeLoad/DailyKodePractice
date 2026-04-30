# Maximum Subarray
---

> Video description: https://youtu.be/yUbQJOVwOYE

[Problem](https://leetcode.com/problems/maximum-subarray/) | [Java Solution](./java_solution/Solution.java)

[![img](https://img.youtube.com/vi/yUbQJOVwOYE/0.jpg)](https://youtu.be/yUbQJOVwOYE)

---

**Difficulty:** Medium  
**Topics:** Array | Dynamic Programming | Divide and Conquer

Given an integer array `nums`, find the subarray with the largest sum, and return its sum.

## Examples:

**Example 1:**
```
Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Output: 6
Explanation: The subarray [4, -1, 2, 1] has the largest sum 6.
```

**Example 2:**
```
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
```

**Example 3:**
```
Input: nums = [5, 4, -1, 7, 8]
Output: 23
Explanation: The subarray [5, 4, -1, 7, 8] has the largest sum 23.
```

## Constraints:
- 1 ≤ nums.length ≤ 10⁵
- -10⁴ ≤ nums[i] ≤ 10⁴

---

## Approach 1 — Kadane's Algorithm (Dynamic Programming)

**Key Insight:** A negative running sum can never help a future subarray. Reset it to 0 whenever it goes negative.

```
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 400]

         sum: -2  →  reset to 0
               1  →  sum = 1
              -3  →  sum = -2 → reset to 0
               4  →  sum = 4
              -1  →  sum = 3
               2  →  sum = 5
               1  →  sum = 6  ← max
              -5  →  sum = 1
             400  →  sum = 401 ← new max
```

- Walk through the array keeping a running sum.
- Track the global maximum at each step.
- If the running sum drops below 0, discard it (start fresh).

**Time:** O(n) | **Space:** O(1)

---

## Approach 2 — Divide and Conquer

**Key Insight:** The maximum subarray must lie entirely in the left half, entirely in the right half, or span across the midpoint.

```
arr = [o o o | o o o]
               ^
           <---  --->
        left-suffix + right-prefix = cross-middle contribution
```

Recursively find the maximum in the left and right halves, then compute the best subarray crossing the midpoint (max suffix of left + max prefix of right). Return the best of the three.

**Time:** O(n log n) | **Space:** O(log n) stack

---

## Walkthrough — Kadane's (Example 1):

```
nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
res  =  -2

i=0: sum=-2 → res=-2 → sum<0 → sum=0
i=1: sum=1  → res=1
i=2: sum=-2 → sum<0 → sum=0
i=3: sum=4  → res=4
i=4: sum=3
i=5: sum=5
i=6: sum=6  → res=6
i=7: sum=1
i=8: sum=5

Output: 6  (subarray [4, -1, 2, 1])
```
