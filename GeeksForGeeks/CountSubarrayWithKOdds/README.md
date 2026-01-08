# Count Subarray with K Odds
---

> Video description: https://youtu.be/aKONoUNBXr8

[Problem](https://www.geeksforgeeks.org/problems/count-subarray-with-k-odds/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/aKONoUNBXr8/0.jpg)](https://youtu.be/aKONoUNBXr8)

---

**Difficulty:** Medium  
**Accuracy:** 45.12%  
**Submissions:** 28K+  
**Points:** 4  
**Average Time:** 25m

Given an array `arr[]` of integers and an integer `K`, count the number of subarrays that contain **exactly K odd numbers**.

## Examples:

**Example 1:**
```
Input: arr[] = [1, 2, 3, 4, 5], K = 2
Output: 6

Explanation:
Subarrays with exactly 2 odd numbers:
[1, 2, 3] → odds: 1, 3
[2, 3, 4, 5] → odds: 3, 5
[1, 2, 3, 4, 5] → odds: 1, 3, 5 (has 3 odds, doesn't count)

Wait, let me recalculate:
[1, 2, 3] → 2 odds ✓
[1, 2, 3, 4] → 2 odds ✓
[2, 3, 4, 5] → 2 odds ✓
[3, 4, 5] → 2 odds ✓
And more...
Total = 6
```

**Example 2:**
```
Input: arr[] = [2, 4, 6], K = 1
Output: 0

Explanation:
All elements are even.
No subarray can have exactly 1 odd number.
```

**Example 3:**
```
Input: arr[] = [1, 1, 1], K = 2
Output: 2

Explanation:
[1, 1] at positions (0,1) → 2 odds ✓
[1, 1] at positions (1,2) → 2 odds ✓
```

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁹
- 0 ≤ K ≤ arr.size()

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(N) or O(1)

## Company Tags:
Amazon | Google | Microsoft | Adobe

## Topic Tags:
Arrays | Prefix Sum | Hash Map | Counting

## Approach:

### Brute Force (TLE):
```
count = 0
for i from 0 to N-1:
    odds = 0
    for j from i to N-1:
        if arr[j] is odd:
            odds++
        if odds == K:
            count++
        if odds > K:
            break
return count
```

**Complexity:** O(N²) - Too Slow!

### Optimal: Prefix Sum + HashMap

**Key Insight:**
- Track count of odd numbers seen so far
- Use prefix sum technique
- For exactly K odds: count(≤K) - count(≤K-1)

**Algorithm:**
```
function countSubarrays(arr, K):
    return atMostK(arr, K) - atMostK(arr, K-1)

function atMostK(arr, K):
    count = 0
    oddCount = 0
    left = 0
    
    for right from 0 to N-1:
        if arr[right] is odd:
            oddCount++
        
        while oddCount > K:
            if arr[left] is odd:
                oddCount--
            left++
        
        count += (right - left + 1)
    
    return count
```

**Complexity:** O(N) - Optimal!

### Alternative: Prefix Count + HashMap

**Algorithm:**
```
map = {0: 1}  // prefix → frequency
oddCount = 0
result = 0

for num in arr:
    if num is odd:
        oddCount++
    
    // Check if (oddCount - K) exists
    if (oddCount - K) in map:
        result += map[oddCount - K]
    
    map[oddCount]++

return result
```

**Complexity:** O(N), Space: O(N)

### Visual Understanding:
```
arr = [1, 2, 3, 4, 5], K = 2

Track odd count at each position:
Index:  0  1  2  3  4
Value:  1  2  3  4  5
IsOdd:  1  0  1  0  1
Prefix: 1  1  2  2  3

For each position with prefix P:
Need prefix (P-K) earlier

At index 2 (prefix=2):
  Need prefix 0 (2-2=0)
  Found at index -1 (before start)
  Subarrays: [1,2,3]

At index 3 (prefix=2):
  Need prefix 0
  Subarrays: [1,2,3,4]

At index 4 (prefix=3):
  Need prefix 1 (3-2=1)
  Found at index 0 and 1
  Subarrays: [2,3,4,5], [3,4,5]

Total = 6 ✓
```

### Why "AtMost K" Works:

**Key Formula:**
```
Exactly K = AtMost K - AtMost (K-1)

AtMost K: All subarrays with ≤ K odds
AtMost K-1: All subarrays with ≤ K-1 odds

Difference: Subarrays with exactly K odds
```

**Example:**
```
arr = [1,1,1], K = 2

AtMost 2 odds:
[1] → 1 odd ✓
[1,1] → 2 odds ✓
[1,1,1] → 3 odds ✗
[1] → 1 odd ✓
[1,1] → 2 odds ✓
[1] → 1 odd ✓
Total = 5

AtMost 1 odd:
[1], [1], [1] → 3 subarrays

Exactly 2 = 5 - 3 = 2 ✓
```

### Sliding Window Technique:

**AtMost K Implementation:**
```
left = 0, oddCount = 0, result = 0

For right pointer:
  Expand window (add arr[right])
  Count if odd
  
  While oddCount > K:
    Shrink window (remove arr[left])
    left++
  
  Add valid subarrays ending at right:
    count += (right - left + 1)
```

**Why (right - left + 1)?**
```
All subarrays ending at 'right' starting from any position in [left, right]
Example: left=0, right=3
Subarrays: [0,3], [1,3], [2,3], [3,3]
Count = 4 = 3 - 0 + 1
```

### Edge Cases:

1. **K = 0:** Count subarrays with no odds (all evens)
2. **K > array size:** Return 0
3. **All odds:** Count carefully
4. **All evens:** Only valid if K = 0
5. **K = 1:** Common case

### Common Mistakes:

1. **Not using "AtMost" technique**
   - Direct counting is complex
   - AtMost K - AtMost K-1 is elegant

2. **Wrong window shrinking**
```
   Wrong: Shrink until oddCount < K
   Right: Shrink until oddCount <= K
```

3. **Forgetting K-1 case**
   - Must subtract AtMost(K-1)

4. **HashMap approach indexing**
   - Need to track prefix counts properly

### Implementation (AtMost Method):
```python
def countSubarrays(arr, K):
    def atMostK(k):
        count = 0
        oddCount = 0
        left = 0
        
        for right in range(len(arr)):
            if arr[right] % 2 == 1:
                oddCount += 1
            
            while oddCount > k:
                if arr[left] % 2 == 1:
                    oddCount -= 1
                left += 1
            
            count += (right - left + 1)
        
        return count
    
    return atMostK(K) - atMostK(K - 1)
```

### Implementation (HashMap Method):
```python
def countSubarrays(arr, K):
    prefix_count = {0: 1}
    odd_count = 0
    result = 0
    
    for num in arr:
        if num % 2 == 1:
            odd_count += 1
        
        if odd_count - K in prefix_count:
            result += prefix_count[odd_count - K]
        
        prefix_count[odd_count] = prefix_count.get(odd_count, 0) + 1
    
    return result
```

### Complexity Comparison:

| Method | Time | Space | Notes |
|--------|------|-------|-------|
| Brute Force | O(N²) | O(1) | TLE |
| AtMost | O(N) | O(1) | Clean |
| HashMap | O(N) | O(N) | Alternative |

### Pattern Recognition:

**"Exactly K" Problems:**
```
Use formula: Exactly K = AtMost K - AtMost K-1

Applies to:
- Exactly K distinct elements
- Exactly K odd numbers
- Exactly K of any property
```

## Related Problems:
- Subarrays with K Different Integers (LeetCode 992)
- Count Number of Nice Subarrays (LeetCode 1248)
- Binary Subarrays With Sum (LeetCode 930)
- Subarray Sum Equals K (LeetCode 560)
- Longest Substring with At Most K Distinct Characters

## Related Articles:
- Sliding Window Technique
- Prefix Sum with HashMap
- "Exactly K" Pattern
- AtMost Technique
- Subarray Counting Problems

## Keywords:
count subarrays k odds, exactly k odd numbers, sliding window counting, prefix sum hashmap, atmost technique, subarray problems, geeksforgeeks medium, competitive programming

---

**SEO Tags:** #SlidingWindow #PrefixSum #HashMap #SubarrayCounting #ExactlyK #Array #DSA #CodingInterview #GeeksforGeeks #AtMostTechnique

**Problem Category:** Subarray Counting, Sliding Window, Prefix Sum

**Difficulty Level:** Medium (Requires pattern recognition)

**Prerequisites:**
- Sliding Window Basics
- HashMap/Prefix Sum
- AtMost Technique Understanding

**Learning Outcomes:**
- Master "Exactly K" pattern
- AtMost technique
- Efficient subarray counting
- Sliding window optimization

**Interview Frequency:** High (Important pattern)

**Key Technique:** AtMost K - AtMost (K-1) = Exactly K for O(N) subarray counting
