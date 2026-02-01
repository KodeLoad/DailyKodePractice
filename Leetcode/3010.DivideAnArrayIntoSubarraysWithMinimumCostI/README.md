# 3010. Divide an Array Into Subarrays With Minimum Cost I
---

> Video Solution: [https://youtu.be/NRGNILgZsIY](https://youtu.be/NRGNILgZsIY)

[Problem](https://leetcode.com/problems/divide-an-array-into-subarrays-with-minimum-cost-i/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/NRGNILgZsIY/0.jpg)](https://youtu.be/NRGNILgZsIY)

---

**Difficulty:** Easy  
**Accuracy:** 65.4%  
**Submissions:** 85K+  
**Points:** 1  
**Average Time:** 15m

You are given an array of integers `nums` of length `n`. The cost of an array is the value of its **first element**. You need to divide `nums` into **3 disjoint contiguous subarrays**. Return the **minimum possible sum** of the cost of these subarrays.

## Examples:

**Example 1:**
<code>
Input: nums = [1, 2, 3, 12]
Output: 6
Explanation: The best possible way to form 3 subarrays is: [1], [2], [3, 12] at a total cost of 1 + 2 + 3 = 6.
</code>

**Example 2:**
<code>
Input: nums = [5, 4, 3]
Output: 12
Explanation: The only way to form 3 subarrays is: [5], [4], [3] at a total cost of 5 + 4 + 3 = 12.
</code>

**Example 3:**
<code>
Input: nums = [10, 3, 1, 1]
Output: 12
Explanation: The best way is: [10], [3], [1, 1] at a cost of 10 + 1 + 1 = 12.
</code>

## Constraints:
- 3 ≤ n ≤ 50
- 1 ≤ nums[i] ≤ 50

## Expected Complexities:
- **Time Complexity:** O(N log N) or O(N)
- **Space Complexity:** O(1) (excluding sorting overhead)

## Topic Tags:
Array | Sorting | Greedy | Enumeration

---

## Approach: Greedy Observation

### 1. The Intuition
The problem asks to split the array into **3 subarrays**. 
- The first subarray **must** start at index `0`. Therefore, `nums[0]` is always part of the cost.
- We need to pick two more indices, say `i` and `j`, to be the start of the second and third subarrays.
- Since we want to **minimize the sum**, and the subarrays must be contiguous, we simply need to find the **two smallest elements** in the remaining part of the array (`nums[1]` to `nums[n-1]`).



### 2. Implementation Strategy
1. Keep `nums[0]` as the fixed cost of the first subarray.
2. Take the sub-array from index `1` to `n-1`.
3. Sort this sub-array and pick the first two elements (the two smallest).
4. **Total Min Cost = nums[0] + smallest1 + smallest2**.

---

## Implementation (Conceptual):
<code>
public int minimumCost(int[] nums) {
    int n = nums.length;
    int first = nums[0];
    
    // Extract remaining elements
    int[] remaining = new int[n - 1];
    for (int i = 1; i < n; i++) {
        remaining[i - 1] = nums[i];
    }
    
    // Sort to find the two smallest
    Arrays.sort(remaining);
    
    return first + remaining[0] + remaining[1];
}
</code>

---

## Key Takeaways:
- **Subarray Rule:** Since we only care about the *first* element of each subarray, the size of the subarrays doesn't matter as long as they are at least size 1.
- **Fixed Starting Point:** Recognizing that `nums[0]` is mandatory simplifies the problem from a partition problem to a "find k-smallest" problem.
- **Complexity:** Given the small constraints ($N \le 50$), even $O(N^2)$ would pass, but sorting provides a clean $O(N \log N)$ solution.

## Related Problems:
- [Minimum Sum of Four Digit Number After Splitting Digits](https://leetcode.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/)
- [Array Partition](https://leetcode.com/problems/array-partition/)

## Keywords:
minimum cost subarrays, divide array leetcode, leetcode 3033, greedy algorithms, sorting arrays, competitive programming, java leetcode solution.

---

**SEO Tags:** #LeetCode #GreedyAlgorithms #Sorting #Arrays #DSA #Algorithms #Java #Python #OBrutus

**Learning Outcomes:**
- Simplifying subarray partition problems through greedy observations.
- Identifying fixed costs versus variable costs in optimization problems.
