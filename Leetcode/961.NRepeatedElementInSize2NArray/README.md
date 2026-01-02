# N-Repeated Element in Size 2N Array
---

> Video description: https://youtu.be/Wx6YyLkGZig

[Problem](https://leetcode.com/problems/n-repeated-element-in-size-2n-array/) | [Java Solution Quick](./java_solution/Solution.java) | [Java Solution Randomized](./java_solution/RandomizedSolution.java) | [Discussion](https://leetcode.com/problems/n-repeated-element-in-size-2n-array/discuss/)

[![img](https://img.youtube.com/vi/Wx6YyLkGZig/0.jpg)](https://youtu.be/Wx6YyLkGZig)

---

**Difficulty:** Easy  
**Acceptance Rate:** 76.8%  
**Submissions:** 487K+  
**Accepted:** 374K+  
**Topics:** Array | Hash Table  
**Average Time:** 10m

You are given an integer array `nums` with the following properties:

- `nums.length == 2 * n`
- `nums` contains `n + 1` unique elements
- Exactly one element of `nums` is repeated `n` times

Return the element that is repeated `n` times.

## Examples:

**Example 1:**
```
Input: nums = [1,2,3,3]
Output: 3
Explanation: n = 2, array has 2*2 = 4 elements.
Element 3 appears 2 times (n times).
```

**Example 2:**
```
Input: nums = [2,1,2,5,3,2]
Output: 2
Explanation: n = 3, array has 2*3 = 6 elements.
Element 2 appears 3 times (n times).
```

**Example 3:**
```
Input: nums = [5,1,5,2,5,3,5,4]
Output: 5
Explanation: n = 4, array has 2*4 = 8 elements.
Element 5 appears 4 times (n times).
```

## Constraints:
- 2 ≤ n ≤ 5000
- nums.length == 2 * n
- 0 ≤ nums[i] ≤ 10⁴
- nums contains n + 1 unique elements and one of them is repeated exactly n times

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) or O(n)

## Company Tags:
Amazon | Google | Microsoft | Apple

## Topic Tags:
Array | Hash Table | Math | Randomized

## Approach:

### Problem Understanding:

**Key Properties:**
1. Array length = 2n
2. Contains n+1 unique elements
3. One element appears exactly n times
4. All other elements appear once

**Mathematical Insight:**
- Total elements: 2n
- Repeated element appears: n times
- Remaining unique elements: n (each appears once)
- The repeated element takes up exactly half the array!

### Approach 1: Hash Map (Standard)

**Algorithm:**
```
map = new HashMap()
for num in nums:
    map[num]++
    if map[num] > 1:
        return num
```

**Complexity:**
- Time: O(n)
- Space: O(n)

### Approach 2: Comparison with Neighbors (Optimal)

**Key Insight:**
- Since element appears n times in 2n array (50% frequency)
- High probability of finding it within first 3 elements
- Check adjacent and nearby elements

**Algorithm:**
```
// Check pairs at distance 1, 2, 3
for i from 0 to n-3:
    if nums[i] == nums[i+1] or 
       nums[i] == nums[i+2] or
       nums[i] == nums[i+3]:
        return nums[i]
```

**Why this works:**
- Element appears n times = 50% of array
- Must have at least 2 occurrences within 4 consecutive elements
- Pigeonhole principle guarantees this

**Complexity:**
- Time: O(n)
- Space: O(1)

### Approach 3: Mathematical (Space Optimized)

**Using Set:**
```
seen = new Set()
for num in nums:
    if num in seen:
        return num
    seen.add(num)
```

**Complexity:**
- Time: O(n)
- Space: O(n+1) ≈ O(n)

### Visual Understanding:

**Example: [1,2,3,3]**
```
n = 2 (2n = 4)
Element 3 appears 2 times

Index: 0  1  2  3
Array: 1  2  3  3
          ↑  ↑
       Found duplicate!
```

**Example: [5,1,5,2,5,3,5,4]**
```
n = 4 (2n = 8)
Element 5 appears 4 times (50% of array)

Every 2 elements, high chance of finding 5
Index: 0  1  2  3  4  5  6  7
Array: 5  1  5  2  5  3  5  4
       ↑     ↑
    Found within distance 2!
```

### Why Neighbor Check Works:

**Pigeonhole Principle:**
```
If element appears n times in 2n positions:
- Occupies 50% of array
- In any window of 4 consecutive elements,
  must appear at least twice
  
Probability calculation:
- P(not in position i) = n/(2n) = 1/2
- P(not in 4 consecutive) = (1/2)^4 = 1/16
- Very unlikely to miss in first few checks
```

### Edge Cases:

1. **Minimum size (n=2):**
   - Input: [1,2,1,3]
   - Output: 1

2. **Adjacent duplicates:**
   - Input: [2,2,1,3]
   - Output: 2

3. **Separated duplicates:**
   - Input: [1,3,2,3]
   - Output: 3

4. **Maximum frequency:**
   - Input: [9,9,9,9,8,7,6,5]
   - Output: 9

5. **All at end:**
   - Input: [1,2,3,4,5,5,5,5]
   - Output: 5

### Common Mistakes:

1. **Wrong frequency check:**
   - Looking for frequency > n/2
   - Should look for any duplicate

2. **Not handling adjacent case:**
   - Missing simple adjacent check

3. **Overcomplicated solution:**
   - Problem is simpler than it appears

4. **Index out of bounds:**
   - Not checking i+3 < n in neighbor approach

### Quick Comparison:

| Approach | Time | Space | Best For |
|----------|------|-------|----------|
| Hash Map | O(n) | O(n) | Standard solution |
| Hash Set | O(n) | O(n) | Clean code |
| Neighbors | O(n) | O(1) | Space optimal |
| Sort | O(n log n) | O(1) | Not recommended |

### Implementation Hints:

**Shortest Solution (Python):**
```python
def repeatedNTimes(nums):
    seen = set()
    for num in nums:
        if num in seen:
            return num
        seen.add(num)
```

**Space Optimized:**
```python
def repeatedNTimes(nums):
    for i in range(len(nums) - 2):
        if nums[i] == nums[i+1] or nums[i] == nums[i+2]:
            return nums[i]
    return nums[-1]  # Must be last element
```

### Mathematical Property:

**Why exactly one duplicate exists:**
```
Total positions: 2n
Unique elements: n+1
If each appeared once: need n+1 positions
But we have 2n positions (n-1 extra)
One element must use those extra positions
That element appears: 1 + (n-1) = n times
```

## Related Problems:
- Majority Element (LeetCode 169)
- Find the Duplicate Number (LeetCode 287)
- Single Number (LeetCode 136)
- Missing Number (LeetCode 268)
- Contains Duplicate (LeetCode 217)

## Related Articles:
- Hash Table Applications
- Pigeonhole Principle
- Frequency Counting
- Space-Time Trade-offs
- Array Pattern Recognition

## Keywords:
n repeated element, find duplicate, array frequency, hash table leetcode, pigeonhole principle, 2n array, duplicate detection, leetcode easy, array algorithms, space optimized solution

---

**SEO Tags:** #Array #HashTable #Duplicate #Frequency #LeetCode #Easy #PigeonholePrinciple #SpaceOptimization #FAANG #CodingInterview

**Problem Category:** Array Manipulation, Frequency Analysis, Duplicate Detection

**Difficulty Level:** Easy (Multiple Solution Approaches)

**Prerequisites:**
- Basic Array Operations
- Hash Table/Set Usage
- Mathematical Reasoning

**Learning Outcomes:**
- Multiple solution approaches
- Space-time trade-offs
- Pigeonhole principle application
- Pattern recognition in arrays

**Interview Frequency:** Medium (Good warm-up question)

**Optimal Solution:** O(n) time, O(1) space using neighbor comparison
