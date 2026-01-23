# Sum of Subarray Ranges
---

> Video Solution: [https://youtu.be/j99Dc14OY38](https://youtu.be/j99Dc14OY38)

[Problem](https://www.geeksforgeeks.org/problems/sum-of-subarray-ranges/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/j99Dc14OY38/0.jpg)](https://youtu.be/j99Dc14OY38)

---

**Difficulty:** Medium  
**Accuracy:** 68.23%  
**Submissions:** 40K+  
**Points:** 4  
**Average Time:** 40m

You are given an integer array `arr[]` of size `n`. The **range** of a subarray is the difference between the largest and smallest element in that subarray. Return the **sum of all subarray ranges**.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 2, 3]
Output: 4

Explanation: 
Subarrays:
[1] -> Range: 1-1=0
[2] -> Range: 2-2=0
[3] -> Range: 3-3=0
[1,2] -> Range: 2-1=1
[2,3] -> Range: 3-2=1
[1,2,3] -> Range: 3-1=2
Total Sum = 0+0+0+1+1+2 = 4.
</code>

**Example 2:**
<code>
Input: arr[] = [4, -2, -3, 4, 1]
Output: 59
</code>

## Constraints:
- 1 ≤ n ≤ 10⁵
- -10⁹ ≤ arr[i] ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(N)

## Topic Tags:
Stack | Monotonic Stack | Arrays | Data Structures

---

## Approach: Monotonic Stack (The Contribution Technique)

### 1. Mathematical Intuition [00:03:15]
The Sum of Subarray Ranges can be expressed as:
$$\sum (\text{max}(Subarray) - \text{min}(Subarray))$$
Which is equivalent to:
$$\sum \text{max}(Subarray) - \sum \text{min}(Subarray)$$

Instead of finding the range for every subarray ($O(N^2)$), we calculate how many subarrays each element `arr[i]` acts as the **Maximum** and how many it acts as the **Minimum**. [00:05:42]

### 2. Contribution Formula [00:08:12]
For an element `arr[i]`, let:
- `L` = number of elements to the left before a larger/smaller element.
- `R` = number of elements to the right before a larger/smaller element.
- **Contribution** = `arr[i] * (L + 1) * (R + 1)`.

### 3. Using Monotonic Stack [00:12:30]
We use a monotonic stack to find the **Next Greater/Smaller** and **Previous Greater/Smaller** elements for every index in $O(N)$ time.



**The Algorithm:**
1. Calculate `Sum of Subarray Minimums` using a monotonic stack. [00:15:20]
2. Calculate `Sum of Subarray Maximums` using a monotonic stack. [00:22:45]
3. **Result = Sum(Max) - Sum(Min)**.

---

## Handling Duplicate Elements [00:25:10]
To avoid counting the same subarray twice (e.g., in `[2, 2]`), we use a strict comparison (`<`) on one side and a non-strict comparison (`<=`) on the other when finding boundaries.

---

## Implementation (Conceptual):
<code>
def sumSubarrayRanges(arr):
    def get_contribution(is_max):
        n = len(arr)
        left = [-1] * n
        right = [n] * n
        stack = []
        
        for i in range(n):
            while stack and (arr[stack[-1]] < arr[i] if is_max else arr[stack[-1]] > arr[i]):
                right[stack.pop()] = i
            if stack:
                left[i] = stack[-1]
            stack.append(i)
            
        total = 0
        for i in range(n):
            total += arr[i] * (i - left[i]) * (right[i] - i)
        return total

    return get_contribution(True) - get_contribution(False)
</code>

---

## Key Takeaways:
- **Linear Efficiency:** By decoupling Max and Min sums, we move from $O(N^2)$ to $O(N)$. [00:28:40]
- **Stack Logic:** The stack stores indices to easily calculate the distance to the next boundary.
- **Large Numbers:** Since the sum can be very large, ensure you use 64-bit integers (long in Java/C++).

## Related Problems:
- [Sum of Subarray Minimums](https://www.geeksforgeeks.org/problems/sum-of-subarray-minimum/1)
- [Next Greater Element](https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)
- [Count Subarrays with Median K](https://leetcode.com/problems/count-subarrays-with-median-k/)

## Keywords:
sum of subarray ranges, monotonic stack, subarray contribution, sum of subarray minimums, subarray maximums, geeksforgeeks potd, O(n) solution, stack data structures.

---

**SEO Tags:** #MonotonicStack #SubarrayContribution #Arrays #DataStructures #DSA #GeeksforGeeks #CodingInterview #Java #Python #AlgorithmOptimization

**Learning Outcomes:**
- Decomposing complex range problems into simpler sum components.
- Utilizing Monotonic Stacks to find boundaries in linear time.
- Applying the contribution technique to avoid $O(N^2)$ iterations.
