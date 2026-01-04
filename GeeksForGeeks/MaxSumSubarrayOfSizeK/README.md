# Max Sum Subarray of Size K
---

> Video description: https://youtu.be/OVi2JCsoR6s

[Problem](https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/OVi2JCsoR6s/0.jpg)](https://youtu.be/OVi2JCsoR6s)

---

**Difficulty:** Easy  
**Accuracy:** 52.08%  
**Submissions:** 471K+  
**Points:** 2  
**Average Time:** 15m

Given an array `arr[]` of size `N` and an integer `K`, find the maximum sum of a subarray of size `K`.

## Examples:

**Example 1:**
```
Input: N = 4, K = 2
arr[] = [100, 200, 300, 400]
Output: 700

Explanation: 
Subarrays of size 2:
[100, 200] → sum = 300
[200, 300] → sum = 500
[300, 400] → sum = 700 (maximum)
```

**Example 2:**
```
Input: N = 4, K = 4
arr[] = [100, 200, 300, 400]
Output: 1000

Explanation:
Only one subarray of size 4: [100, 200, 300, 400]
Sum = 1000
```

## Constraints:
- 1 ≤ N ≤ 10⁵
- 1 ≤ K ≤ N
- 1 ≤ arr[i] ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Microsoft | Google | Adobe | Accolite | Walmart | Samsung

## Topic Tags:
Arrays | Sliding Window | Two Pointers

## Approach:

### Brute Force (TLE):
```
maxSum = 0
for i from 0 to N-K:
    currentSum = 0
    for j from i to i+K-1:
        currentSum += arr[j]
    maxSum = max(maxSum, currentSum)
return maxSum
```

**Complexity:** O(N × K) - Too Slow!

### Optimal: Sliding Window Technique

**Key Insight:**
- Instead of recalculating sum for each window
- Slide the window: subtract left element, add right element
- Reuse previous calculation

**Algorithm:**
```
// Step 1: Calculate sum of first window
windowSum = sum of arr[0] to arr[K-1]
maxSum = windowSum

// Step 2: Slide window
for i from K to N-1:
    windowSum = windowSum - arr[i-K] + arr[i]
    maxSum = max(maxSum, windowSum)

return maxSum
```

**Complexity:** O(N) - Optimal!

### Visual Understanding:
```
arr = [100, 200, 300, 400], K = 2

Window 1: [100, 200]
          sum = 300

Window 2:  [200, 300]
          sum = 300 - 100 + 300 = 500

Window 3:      [300, 400]
          sum = 500 - 200 + 400 = 700 ✓

Maximum = 700
```

### Step-by-Step:
```
arr = [1, 4, 2, 10, 23, 3, 1, 0, 20], K = 4

Initial window [1, 4, 2, 10]:
sum = 17, max = 17

Slide → [4, 2, 10, 23]:
sum = 17 - 1 + 23 = 39, max = 39

Slide → [2, 10, 23, 3]:
sum = 39 - 4 + 3 = 38, max = 39

Slide → [10, 23, 3, 1]:
sum = 38 - 2 + 1 = 37, max = 39

Slide → [23, 3, 1, 0]:
sum = 37 - 10 + 0 = 27, max = 39

Slide → [3, 1, 0, 20]:
sum = 27 - 23 + 20 = 24, max = 39

Answer: 39
```

### Why Sliding Window Works:

**Overlapping Windows:**
```
Window 1: [a, b, c, d]
Window 2:    [b, c, d, e]

Instead of: sum(b,c,d,e)
Use: sum(a,b,c,d) - a + e

Saves K-1 operations per window!
```

### Edge Cases:

1. **K = N:** Only one window (entire array)
2. **K = 1:** Maximum element
3. **All negatives:** Return least negative sum
4. **Single element:** K=1, N=1

### Common Mistakes:

1. **Recalculating entire window sum**
   - Inefficient O(N×K)

2. **Off-by-one errors**
   - Loop from K to N-1, not K to N

3. **Not initializing maxSum**
   - Must start with first window sum

4. **Wrong index calculation**
   - Remove arr[i-K], add arr[i]

### Implementation:
```python
def maxSumSubarray(arr, N, K):
    # First window
    window_sum = sum(arr[:K])
    max_sum = window_sum
    
    # Slide window
    for i in range(K, N):
        window_sum += arr[i] - arr[i - K]
        max_sum = max(max_sum, window_sum)
    
    return max_sum
```

### Sliding Window Pattern:

**Template:**
```
1. Calculate first window
2. Set initial result
3. For remaining elements:
   a. Remove leftmost of previous window
   b. Add new rightmost element
   c. Update result
4. Return result
```

### Variations:

**Min Sum Subarray:**
- Same logic, track minimum

**Average of Subarrays:**
- Calculate sum, divide by K

**All Subarray Sums:**
- Store each window sum

## Related Problems:
- Sliding Window Maximum (LeetCode 239)
- Minimum Size Subarray Sum (LeetCode 209)
- Longest Substring Without Repeating Characters (LeetCode 3)
- Maximum Average Subarray I (LeetCode 643)
- Subarray Sum Equals K (LeetCode 560)

## Related Articles:
- Sliding Window Technique
- Two Pointer Approach
- Fixed Size Window Problems
- Subarray Problems
- Array Optimization

## Keywords:
max sum subarray, sliding window, fixed window size, subarray sum, array algorithms, O(n) solution, geeksforgeeks easy, sliding window pattern, competitive programming

---

**SEO Tags:** #SlidingWindow #Array #Subarray #MaxSum #FixedWindow #DSA #CodingInterview #GeeksforGeeks #OptimizationTechnique

**Problem Category:** Sliding Window, Array Manipulation

**Difficulty Level:** Easy (Classic sliding window problem)

**Prerequisites:**
- Basic Array Operations
- Understanding of Subarrays
- Loop Mechanics

**Learning Outcomes:**
- Master sliding window technique
- Optimize from O(N×K) to O(N)
- Pattern recognition
- Fixed window problems

**Interview Frequency:** Very High (Fundamental pattern)

**Key Technique:** Sliding window with fixed size K for optimal O(N) solution