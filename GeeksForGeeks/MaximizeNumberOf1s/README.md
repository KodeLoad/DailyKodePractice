# Maximize Number of 1s
---

> Video Solution: [https://youtu.be/cr5jkTC83pE](https://youtu.be/cr5jkTC83pE)

[Problem](https://www.geeksforgeeks.org/problems/maximize-number-of-1s0905/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/cr5jkTC83pE/0.jpg)](https://youtu.be/cr5jkTC83pE)

---

**Difficulty:** Medium  
**Accuracy:** 46.41%  
**Submissions:** 150K+  
**Points:** 4  
**Average Time:** 25m

Given a binary array `arr[]` (containing only 0s and 1s) and an integer `k`, you are allowed to flip at most `k` zeros to 1s. Find the maximum number of consecutive 1s that can be obtained.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1], k = 2
Output: 6
Explanation: By flipping the zeros at indices 5 and 7, the array becomes [1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1]. The longest subarray of 1s is from index 3 to 8, which has length 6.
</code>

**Example 2:**
<code>
Input: arr[] = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], k = 2
Output: 6
</code>

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ k ≤ arr.size()
- 0 ≤ arr[i] ≤ 1

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(1)

## Topic Tags:
Two Pointers | Sliding Window | Arrays

---

## Approach: Two Pointers / Sliding Window

### 1. Intuition
The problem asks for the longest subarray with at most `k` zeros. Instead of checking every possible subarray (which would be $O(N^2)$), we can use a **Sliding Window** to find the result in a single pass.

### 2. The Two-Step Logic
1.  **Expand (Right Pointer):** Move the right pointer `r` to include elements. If `arr[r]` is 0, increment a counter (`zeros`).
2.  **Shrink (Left Pointer):** If `zeros` exceeds `k`, move the left pointer `l` forward. If `arr[l]` was 0, decrement the `zeros` counter. This ensures the window always contains at most `k` zeros.

### 3. Result Calculation
After each step, the length of the valid window is `(r - l + 1)`. We keep track of the maximum length found during the process.

---

## Implementation (Conceptual):
<code>
public int maximizeOnes(int[] arr, int n, int k) {
    int l = 0, r = 0;
    int zeros = 0;
    int maxLen = 0;

    for (r = 0; r < n; r++) {
        if (arr[r] == 0) zeros++;
        
        while (zeros > k) {
            if (arr[l] == 0) zeros--;
            l++;
        }
        
        maxLen = Math.max(maxLen, r - l + 1);
    }
    return maxLen;
}
</code>

---

## Key Takeaways:
- **Efficiency:** The $O(N)$ approach is ideal for large constraints ($10^5$).
- **Dynamic Window:** The window size grows and shrinks dynamically to maintain the problem's constraints.
- **Space Optimization:** No extra space is required, making it $O(1)$.

## Related Problems:
- [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets/)

## Keywords:
maximize number of 1s, sliding window maximum consecutive 1s, two pointers dsa, gfg potd, binary array flips, longest subarray of 1s, java coding solution.

---

**SEO Tags:** #SlidingWindow #TwoPointers #Arrays #DSA #GeeksforGeeks #CodingInterview #Java #Algorithms #ProblemSolving #OBrutus

**Learning Outcomes:**
- Mastering the Sliding Window pattern for subarray problems.
- Handling conditional window shrinking.
- Optimizing search problems from quadratic to linear time.
