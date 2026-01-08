# Count Distinct Elements in Every Window
---

> Video description: https://youtu.be/uCCQbyTyTZs

[Problem](https://www.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/uCCQbyTyTZs/0.jpg)](https://youtu.be/uCCQbyTyTZs)

---

**Difficulty:** Easy  
**Accuracy:** 50.87%  
**Submissions:** 149K+  
**Points:** 2  
**Average Time:** 20m

Given an array `arr[]` of size `N` and an integer `K`, find the count of distinct elements in every window of size `K` in the array.

## Examples:

**Example 1:**
```
Input: N = 7, K = 4
arr[] = [1, 2, 1, 3, 4, 2, 3]
Output: [3, 4, 4, 3]

Explanation:
Window 1: [1, 2, 1, 3] → Distinct: {1, 2, 3} → Count = 3
Window 2: [2, 1, 3, 4] → Distinct: {1, 2, 3, 4} → Count = 4
Window 3: [1, 3, 4, 2] → Distinct: {1, 2, 3, 4} → Count = 4
Window 4: [3, 4, 2, 3] → Distinct: {2, 3, 4} → Count = 3
```

**Example 2:**
```
Input: N = 3, K = 2
arr[] = [4, 1, 1]
Output: [2, 1]

Explanation:
Window 1: [4, 1] → Distinct: {1, 4} → Count = 2
Window 2: [1, 1] → Distinct: {1} → Count = 1
```

## Constraints:
- 1 ≤ K ≤ N ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(K)

## Company Tags:
Amazon | Microsoft | Google | Adobe | Samsung | Accolite

## Topic Tags:
Sliding Window | Hash Map | Arrays

## Approach:

### Brute Force (TLE):
```
for each window:
    set = new HashSet()
    for element in window:
        set.add(element)
    result.append(set.size())
```

**Complexity:** O(N × K) - Too Slow!

### Optimal: Sliding Window + Hash Map

**Key Insight:**
- Use HashMap to track frequency of elements
- Slide window: remove left, add right
- Map size = distinct count

**Algorithm:**
```
map = new HashMap()
result = []

// Build first window
for i from 0 to K-1:
    map[arr[i]]++

result.add(map.size())

// Slide window
for i from K to N-1:
    // Remove leftmost element
    map[arr[i-K]]--
    if map[arr[i-K]] == 0:
        map.remove(arr[i-K])
    
    // Add rightmost element
    map[arr[i]]++
    
    result.add(map.size())

return result
```

**Complexity:** O(N) - Optimal!

### Visual Understanding:
```
arr = [1, 2, 1, 3, 4, 2, 3], K = 4

Window 1: [1, 2, 1, 3]
map = {1:2, 2:1, 3:1}
distinct = 3 ✓

Slide → Remove 1, Add 4
Window 2: [2, 1, 3, 4]
map = {1:1, 2:1, 3:1, 4:1}
distinct = 4 ✓

Slide → Remove 2, Add 2
Window 3: [1, 3, 4, 2]
map = {1:1, 2:1, 3:1, 4:1}
distinct = 4 ✓

Slide → Remove 1, Add 3
Window 4: [3, 4, 2, 3]
map = {2:1, 3:2, 4:1}
distinct = 3 ✓

Output: [3, 4, 4, 3]
```

### Step-by-Step Trace:
```
arr = [1, 2, 1, 3], K = 3

Initial window [1, 2, 1]:
Add 1: map = {1:1}
Add 2: map = {1:1, 2:1}
Add 1: map = {1:2, 2:1}
Size = 2 → result = [2]

Slide to [2, 1, 3]:
Remove arr[0]=1: map[1]-- → {1:1, 2:1}
Add arr[3]=3: map[3]++ → {1:1, 2:1, 3:1}
Size = 3 → result = [2, 3]

Output: [2, 3]
```

### Why HashMap Works:

**Frequency Tracking:**
```
- HashMap stores element → frequency
- Size of map = number of distinct elements
- When frequency becomes 0 → remove from map
- Map size always reflects distinct count
```

**Sliding Mechanism:**
```
Remove left: 
  freq--
  if freq == 0: delete entry

Add right:
  freq++ (or initialize to 1)

Map size = answer for current window
```

### Edge Cases:

1. **K = N:** Single window (entire array)
2. **K = 1:** Each element is distinct count 1
3. **All same elements:** Count always 1
4. **All unique:** Count always K
5. **Duplicates:** Handle frequency properly

### Common Mistakes:

1. **Not removing from map when freq = 0**
```
   Wrong: Just decrement frequency
   Right: Remove entry when frequency becomes 0
```

2. **Using Set instead of Map**
   - Can't track frequencies
   - Can't handle duplicates properly

3. **Wrong window indices**
   - Remove arr[i-K], not arr[i-1]

4. **Not handling first window separately**
   - Need to build initial map first

### Implementation:
```python
def countDistinct(arr, N, K):
    freq = {}
    result = []
    
    # First window
    for i in range(K):
        freq[arr[i]] = freq.get(arr[i], 0) + 1
    
    result.append(len(freq))
    
    # Slide window
    for i in range(K, N):
        # Remove left element
        left = arr[i - K]
        freq[left] -= 1
        if freq[left] == 0:
            del freq[left]
        
        # Add right element
        freq[arr[i]] = freq.get(arr[i], 0) + 1
        
        result.append(len(freq))
    
    return result
```

### Sliding Window Pattern:

**Template:**
```
1. Build first window in map
2. Record size (distinct count)
3. For remaining elements:
   a. Decrease freq of leftmost element
   b. Remove if freq becomes 0
   c. Increase freq of new element
   d. Record map size
4. Return results
```

### HashMap Operations:

**Key Operations:**
```
Insert/Update: O(1) average
Delete: O(1) average
Size: O(1)
Total per window: O(1)
```

**Why Not Set:**
```
Set can track presence but not frequency
Example: [1,1,2]
After removing first 1, need to keep 1 in set
Can't do this with Set alone
```

### Optimization Notes:

**Space:** O(K) for HashMap
- At most K distinct elements in window

**Time:** O(N) 
- Each element added once, removed once
- HashMap ops are O(1)

### Comparison:

| Approach | Time | Space | Notes |
|----------|------|-------|-------|
| Brute Force | O(N×K) | O(K) | TLE |
| Sliding + Set | Wrong | O(K) | Can't handle duplicates |
| Sliding + Map | O(N) | O(K) | Optimal ✓ |

## Related Problems:
- Longest Substring Without Repeating Characters (LeetCode 3)
- Substring with Concatenation of All Words (LeetCode 30)
- Minimum Window Substring (LeetCode 76)
- Find All Anagrams in a String (LeetCode 438)
- Subarrays with K Different Integers (LeetCode 992)

## Related Articles:
- Sliding Window with HashMap
- Frequency Counting Techniques
- Variable vs Fixed Window
- Hash Map Applications
- Window Problems Patterns

## Keywords:
count distinct elements, sliding window hash map, distinct count window, array windowing, frequency map, geeksforgeeks sliding window, hash map frequency, subarray distinct elements

---

**SEO Tags:** #SlidingWindow #HashMap #FrequencyCount #DistinctElements #Array #DSA #CodingInterview #GeeksforGeeks #WindowProblems

**Problem Category:** Sliding Window, Hash Map, Frequency Counting

**Difficulty Level:** Easy-Medium (Requires HashMap understanding)

**Prerequisites:**
- Sliding Window Basics
- HashMap/Dictionary Operations
- Frequency Counting

**Learning Outcomes:**
- Sliding window with HashMap
- Frequency tracking techniques
- Distinct element counting
- Efficient window management

**Interview Frequency:** Very High (Common pattern)

**Key Technique:** Sliding window with HashMap for O(N) distinct counting in all windows