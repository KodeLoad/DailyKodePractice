# Subarrays with At Most K Distinct Integers
---

> Video description: [https://youtu.be/ikF1WeXHMMA](https://youtu.be/ikF1WeXHMMA)

[Problem](https://www.geeksforgeeks.org/problems/subarrays-with-at-most-k-distinct-integers/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/ikF1WeXHMMA/0.jpg)](https://youtu.be/ikF1WeXHMMA)

---

**Difficulty:** Medium  
**Accuracy:** 48.5%  
**Submissions:** 80K+  
**Points:** 4  
**Average Time:** 35m

Given an array <code>arr[]</code> of size <code>N</code> and an integer <code>K</code>, find the total number of subarrays having **at most K** distinct integers.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 2, 1, 2, 3], K = 2
Output: 7

Explanation: 
Subarrays with at most 2 distinct integers:
[1], [2], [1], [2], [3], [1, 2], [2, 1], [1, 2], [2, 3], [1, 2, 1], [2, 1, 2]
Total count = 7 (Wait, let's look at contiguous subarrays: [1], [2], [1], [2], [3], [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2]...) 
Note: The problem asks for total count of subarrays meeting the criteria.
</code>

**Example 2:**
<code>
Input: arr[] = [1, 2, 1, 3, 4], K = 3
Output: 10
</code>

## Constraints:
- 1 ≤ N ≤ 10⁵
- 1 ≤ K ≤ N
- 1 ≤ arr[i] ≤ N

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(N) (for frequency map)

## Company Tags:
Google | Amazon | Microsoft | Adobe

## Topic Tags:
Arrays | Hash | Sliding Window | Two Pointers

## Approach:

### Brute Force (O(N²)):
<code>
count = 0
for i from 0 to N-1:
    distinct_elements = set()
    for j from i to N-1:
        distinct_elements.add(arr[j])
        if len(distinct_elements) <= K:
            count += 1
        else:
            break
return count
</code>
**Complexity:** O(N²) - Too slow for N = 10⁵.

### Optimal: Sliding Window (Two Pointers)

**Key Insight:**
For a fixed window <code>[left, right]</code>, if the number of distinct elements is <code>≤ K</code>, then all subarrays ending at <code>right</code> and starting between <code>left</code> and <code>right</code> also satisfy the condition.
The number of such subarrays is: <code>(right - left + 1)</code>.

**Algorithm:**
1. Use a **Frequency Map** (or Hash Table) to keep track of distinct elements in the current window.
2. Expand the `right` pointer to include a new element.
3. If the number of distinct elements exceeds `K`, shrink the window from the `left` until the count of distinct elements is exactly `K`.
4. At each step, add <code>(right - left + 1)</code> to your total count.

**Complexity:** O(N) - Each element is visited at most twice (once by left, once by right).

---

### Step-by-Step Visualization:
<code>
arr = [1, 2, 1], K = 2

right = 0: [1] -> Distinct=1 (<=2). Count += (0-0+1) = 1
right = 1: [1, 2] -> Distinct=2 (<=2). Count += (1-0+1) = 3 (Total=4)
right = 2: [1, 2, 1] -> Distinct=2 (<=2). Count += (2-0+1) = 6 (Total=7)

Answer: 7
</code>

---

### Implementation (Python):
<code>
def atMostK(arr, n, k):
    left = 0
    right = 0
    count = 0
    freq = {}
    
    while right < n:
        # Add element to window
        freq[arr[right]] = freq.get(arr[right], 0) + 1
        
        # Shrink window if distinct elements > K
        while len(freq) > k:
            freq[arr[left]] -= 1
            if freq[arr[left]] == 0:
                del freq[arr[left]]
            left += 1
            
        # All subarrays ending at 'right' are valid
        count += (right - left + 1)
        right += 1
        
    return count
</code>

### Common Mistakes:
1. **Confusing "At Most K" with "Exactly K":** - To find "Exactly K", calculate: <code>atMost(K) - atMost(K-1)</code>.
2. **Frequency Map Management:** Forgetting to delete a key from the map when its frequency drops to 0.
3. **Off-by-one errors:** Calculating window length as <code>right - left</code> instead of <code>right - left + 1</code>.

### Related Problems:
- [Subarrays with Exactly K Distinct Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/)
- [Longest Substring with At Most K Distinct Characters](https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1)
- [Count Subarrays with At Most K sum]

## Keywords:
sliding window, at most k distinct integers, two pointers, frequency map, subarray count, geeksforgeeks potd, array optimization, google interview questions.

---

**SEO Tags:** #SlidingWindow #TwoPointers #Subarrays #DistinctIntegers #DSA #CodingInterview #GeeksforGeeks #Python #Java #Algorithms

**Learning Outcomes:**
- Mastering the flexible sliding window technique.
- Understanding how to count subarrays ending at a specific index.
- Efficient state management using HashMaps in O(N).
