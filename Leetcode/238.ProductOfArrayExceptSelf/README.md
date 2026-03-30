# 238. Product of Array Except Self
---

> Video Solution: [https://youtu.be/R31AOywTgzA](https://youtu.be/R31AOywTgzA)

[Problem](https://leetcode.com/problems/product-of-array-except-self/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/R31AOywTgzA/maxresdefault.jpg)](https://youtu.be/R31AOywTgzA)

---

**Difficulty:** Medium  
**Accuracy:** 66.2%  
**Submissions:** 2.5M+  
**Points:** 4 (Blind 75)  
**Average Time:** 25m

Given an integer array `nums`, return an array `answer` such that `answer[i]` is equal to the product of all the elements of `nums` except `nums[i]`.

## Key Challenges:
- **Time Complexity:** Must be $O(n)$. [00:01:22]
- **Constraint:** You must solve it **without** using the division operator. [00:01:25]
- **Follow-up:** Can you solve it in $O(1)$ extra space complexity? (The output array does not count as extra space). [00:08:38]

## Examples:

**Example 1:**
<code>
Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Explanation: 
- For index 0: 2*3*4 = 24
- For index 1: 1*3*4 = 12
... and so on. [00:01:45]
</code>

## Approach Evolution:

### 1. Brute Force ($O(N^2)$) [00:02:02]
For every element, iterate through the rest of the array to calculate the product. This is inefficient for large arrays.

### 2. Division Trick (Illegal) [00:02:53]
Calculate the total product of the array and then divide by `nums[i]`. While this is $O(N)$, it fails if the array contains `0` and is explicitly forbidden by the problem constraints. [00:03:47]

### 3. Prefix & Suffix Arrays ($O(N)$ Time, $O(N)$ Space) [00:04:35]
At any index `i`, the product except self is simply:
`Prefix Product (all elements before i) * Suffix Product (all elements after i)`


1. Create a `prefix` array where `prefix[i]` is the product of all elements to the left of `i`. [00:05:40]
2. Create a `suffix` array where `suffix[i]` is the product of all elements to the right of `i`. [00:05:54]
3. `result[i] = prefix[i] * suffix[i]`.

### 4. Optimized Solution ($O(N)$ Time, $O(1)$ Space) [00:08:09]
We can eliminate the extra prefix/suffix arrays by using the output array itself to store prefix products and using a single variable to keep track of the suffix product on the fly. [00:09:49]

---

## Implementation (Conceptual): [00:12:25]
<code>
public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    
    // Step 1: Calculate prefix products and store in res
    res[0] = 1;
    for (int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }
    
    // Step 2: Calculate suffix products on the fly and multiply with prefix
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        res[i] *= right;
        right *= nums[i];
    }
    
    return res;
}
</code>

---

## Key Takeaways:
- **Space Management:** The output array is often excluded from space complexity analysis in competitive programming, allowing us to "hide" our prefix array there. [00:10:41]
- **Two-Pass Strategy:** Instead of one complex pass, two simple passes (Left-to-Right and Right-to-Left) often simplify $O(N)$ problems. [00:14:02]
- **Deceptive Simplicity:** This is a classic interview question (Blind 75) because it tests if you can move beyond the obvious division solution. [00:00:07]

## Related Problems:
- [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
- [Prefix Sum Problems](https://leetcode.com/tag/prefix-sum/)

## Keywords:
product of array except self leetcode, leetcode 238 java, blind 75 leetcode solutions, prefix product array, competitive programming java, coding interview questions.

---

**SEO Tags:** #Blind75 #LeetCode #Algorithms #Java #CodingInterview #ProductExceptSelf #DataStructures #OBrutus #ProblemSolving #O1Space

**Learning Outcomes:**
- Solving array problems without division.
- Optimizing $O(N)$ space complexity down to $O(1)$.
- Mastering the Left-Right pass pattern for array transformations.
