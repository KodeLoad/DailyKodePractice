# Kth Missing Positive Number in a Sorted Array
---

> Video description: https://youtu.be/jG9JIgMfvyE

[Problem](https://www.geeksforgeeks.org/problems/kth-missing-positive-number-in-a-sorted-array/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/jG9JIgMfvyE/0.jpg)](https://youtu.be/jG9JIgMfvyE)

---

**Difficulty:** Medium  
**Accuracy:** 45.23%  
**Submissions:** 45K+  
**Points:** 4  
**Average Time:** 30m

Given a sorted array of distinct positive integers `arr[]`, find the k<sup>th</sup> positive integer that is missing from the array.

## Examples:

**Example 1:**
```
Input: arr[] = [2, 3, 4, 7, 11], k = 5
Output: 9

Explanation: 
Missing positive numbers are: 1, 5, 6, 8, 9, 10, 12, 13, ...
The 5th missing positive number is 9.

Visual representation:
Present:  2  3  4     7        11
Missing:  1     5  6     8  9     10 12 13 ...
                              ↑
                           5th missing = 9
```

**Example 2:**
```
Input: arr[] = [1, 2, 3], k = 2
Output: 5

Explanation:
Missing positive numbers are: 4, 5, 6, 7, ...
The 2nd missing positive number is 5.

Visual representation:
Present:  1  2  3
Missing:           4  5  6  7 ...
                      ↑
                   2nd missing = 5
```

**Example 3:**
```
Input: arr[] = [3, 5, 9, 10, 11, 12], k = 2
Output: 2

Explanation:
Missing positive numbers are: 1, 2, 4, 6, 7, 8, 13, ...
The 2nd missing positive number is 2.

Visual representation:
Missing:  1  2     (before array)
Present:        3     5        9 10 11 12
Missing:           4     6  7  8           13 ...
             ↑
          2nd missing = 2
```

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 1 ≤ k ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁶

## Expected Complexities:
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Google | Microsoft | Facebook

## Topic Tags:
Arrays | Binary Search | Math

## Approach:

### Problem Understanding:

**What are we looking for?**
- Positive integers starting from 1: 1, 2, 3, 4, 5, ...
- Find which ones are missing from the array
- Return the k<sup>th</sup> missing number

**Key Insight:**
- Array is sorted and contains distinct positive integers
- Can use binary search to efficiently find answer

### Brute Force Analysis:

**Approach 1: Check each positive number**
```
count = 0
num = 1
while count < k:
    if num not in arr:
        count++
        if count == k:
            return num
    num++
```

**Complexity:**
- Time: O(max(arr) × n) - too slow
- Check membership: O(n) for each number

**Approach 2: Iterate and count missing**
```
count = 0
current = 1
i = 0

while count < k:
    if i < n and arr[i] == current:
        i++
    else:
        count++
        if count == k:
            return current
    current++
```

**Complexity:**
- Time: O(n + k)
- Better but not optimal

### Key Observations for Binary Search:

1. **Missing count formula:**
   - At index i, element is arr[i]
   - Expected element at index i: i + 1
   - Missing count up to index i: `arr[i] - (i + 1)`

2. **Binary search insight:**
   - Find the smallest index where missing count ≥ k
   - Use this to calculate the k<sup>th</sup> missing number

3. **Relationship between position and missing:**
```
   At index i:
   - Actual value: arr[i]
   - Expected value if no missing: i + 1
   - Missing count: arr[i] - (i + 1)
```

4. **Result calculation:**
   - If we find index where missing count changes around k
   - k<sup>th</sup> missing = left + k
   - Where left is the position after binary search

### Optimal Algorithm: Binary Search
```
function kthMissing(arr, k):
    n = arr.length
    left = 0
    right = n - 1
    
    // Binary search to find position
    while left <= right:
        mid = left + (right - left) / 2
        
        // Calculate missing count at mid
        missing = arr[mid] - (mid + 1)
        
        if missing < k:
            left = mid + 1
        else:
            right = mid - 1
    
    // After binary search:
    // left is the insertion point
    // kth missing number = left + k
    return left + k
```

### Step-by-Step Walkthrough (Example 1):
```
Input: arr = [2, 3, 4, 7, 11], k = 5

Expected sequence: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]
Actual array:      [   2, 3, 4,       7,             11, ...]
Missing:           [1,          5, 6,    8,  9, 10,      ...]
                                             ↑
                                          5th missing

Initialize: left = 0, right = 4

Iteration 1:
- mid = 0 + (4 - 0) / 2 = 2
- arr[2] = 4
- Expected at index 2: 2 + 1 = 3
- Missing count: 4 - 3 = 1
- 1 < 5? Yes → Move right
- left = 3, right = 4

Iteration 2:
- mid = 3 + (4 - 3) / 2 = 3
- arr[3] = 7
- Expected at index 3: 3 + 1 = 4
- Missing count: 7 - 4 = 3
- 3 < 5? Yes → Move right
- left = 4, right = 4

Iteration 3:
- mid = 4 + (4 - 4) / 2 = 4
- arr[4] = 11
- Expected at index 4: 4 + 1 = 5
- Missing count: 11 - 5 = 6
- 6 < 5? No → Move left
- left = 4, right = 3

Condition: left > right → Exit

Result: left + k = 4 + 5 = 9

Output: 9 ✓

Verification:
Missing before index 4: [1, 5, 6, 8]  (4 numbers)
We need 5th missing, so 1 more after index 3
After arr[3] = 7, the next missing is 8, then 9
5th missing = 9 ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: arr = [1, 2, 3], k = 2

Initialize: left = 0, right = 2

Iteration 1:
- mid = 1
- arr[1] = 2
- Missing: 2 - (1 + 1) = 0
- 0 < 2? Yes
- left = 2, right = 2

Iteration 2:
- mid = 2
- arr[2] = 3
- Missing: 3 - (2 + 1) = 0
- 0 < 2? Yes
- left = 3, right = 2

Exit: left = 3

Result: 3 + 2 = 5

Output: 5 ✓

Explanation:
Array has [1, 2, 3]
No missing before or within array
Missing after: [4, 5, 6, ...]
2nd missing = 5 ✓
```

### Step-by-Step Walkthrough (Example 3):
```
Input: arr = [3, 5, 9, 10, 11, 12], k = 2

Initialize: left = 0, right = 5

Iteration 1:
- mid = 2
- arr[2] = 9
- Missing: 9 - (2 + 1) = 6
- 6 < 2? No
- left = 0, right = 1

Iteration 2:
- mid = 0
- arr[0] = 3
- Missing: 3 - (0 + 1) = 2
- 2 < 2? No
- left = 0, right = -1

Exit: left = 0

Result: 0 + 2 = 2

Output: 2 ✓

Explanation:
Before arr[0] = 3, missing: [1, 2]
2nd missing = 2 ✓
```

### Visual Understanding:

**Missing Count at Each Index:**
```
arr = [2, 3, 4, 7, 11]
idx =  0  1  2  3   4

Index 0: arr[0]=2,  expected=1,  missing=1  [1]
Index 1: arr[1]=3,  expected=2,  missing=1  [1]
Index 2: arr[2]=4,  expected=3,  missing=1  [1]
Index 3: arr[3]=7,  expected=4,  missing=3  [1,5,6]
Index 4: arr[4]=11, expected=5,  missing=6  [1,5,6,8,9,10]

For k=5, find where missing count crosses 5
This happens between index 3 (missing=3) and index 4 (missing=6)
Binary search gives left=4
Result: 4 + 5 = 9
```

### Why Formula Works:

**Mathematical Derivation:**
```
At index i:
- Elements processed: i + 1 positions (0 to i)
- Actual value: arr[i]
- If no missing: would have values [1, 2, ..., i+1]
- Missing count = arr[i] - (i + 1)

After binary search:
- left points to first index where missing ≥ k
- OR points beyond array if all missing < k

Result formula: left + k

Why?
- Before index left, there are 'left' elements
- Expected values: [1, 2, ..., left]
- We need k more numbers from this sequence
- k-th missing = left + k
```

**Example:**
```
arr = [2, 3, 4, 7], k = 3

left = 3 (after binary search)
Missing before index 3: [1, 5, 6]  (3 missing)

We need 3rd missing:
Result = 3 + 3 = 6 ✓
```

### Edge Cases:

1. **All missing before array:**
   - Input: arr = [5, 6, 7], k = 2
   - Missing: [1, 2, 3, 4]
   - Output: 2

2. **All missing after array:**
   - Input: arr = [1, 2, 3], k = 3
   - Missing after: [4, 5, 6, ...]
   - Output: 6

3. **k = 1:**
   - Input: arr = [2, 3, 4], k = 1
   - Output: 1 (first missing)

4. **Large gap in array:**
   - Input: arr = [1, 100], k = 5
   - Missing: [2, 3, 4, 5, 6, ...]
   - Output: 6

5. **Array starts from 1:**
   - Input: arr = [1, 3, 5], k = 2
   - Missing: [2, 4, 6, ...]
   - Output: 4

6. **Single element:**
   - Input: arr = [10], k = 5
   - Missing: [1, 2, 3, 4, 5, 6, ...]
   - Output: 5

### Common Mistakes:

1. **Wrong missing count formula:**
```
   Wrong: arr[i] - i
   Right: arr[i] - (i + 1)
```

2. **Off-by-one in result:**
```
   Wrong: left + k - 1
   Right: left + k
```

3. **Not considering elements before array:**
   - If arr[0] > 1, there are missing numbers before array

4. **Wrong binary search condition:**
```
   Wrong: missing <= k
   Right: missing < k
```

5. **Integer overflow:**
   - For large values of arr[i] and k
   - Use appropriate data types

### Alternative Approach: Direct Calculation

**If we know missing count at boundaries:**
```python
def kthMissing(arr, k):
    n = len(arr)
    
    # Check if kth missing is before array
    if k < arr[0]:
        return k
    
    # Check if kth missing is after array
    missing_total = arr[-1] - n
    if k > missing_total:
        return arr[-1] + (k - missing_total)
    
    # Binary search for position
    left, right = 0, n - 1
    while left <= right:
        mid = (left + right) // 2
        missing = arr[mid] - (mid + 1)
        
        if missing < k:
            left = mid + 1
        else:
            right = mid - 1
    
    return left + k
```

### Complexity Analysis:

**Time Complexity: O(log n)**
- Binary search: O(log n)
- Constant operations per iteration
- Optimal for sorted array

**Space Complexity: O(1)**
- Only using constant variables
- No additional data structures

**Why Binary Search is Optimal:**
- Array is sorted - key property
- Can eliminate half search space each iteration
- Linear search would be O(n + k)
- Binary search achieves O(log n)

### Performance Comparison:
```
For n = 100,000:
- Linear search: ~100,000 operations
- Binary search: ~17 operations
- 5,000x faster!

For n = 1,000,000:
- Linear search: ~1,000,000 operations
- Binary search: ~20 operations
- 50,000x faster!
```

## Related Interview Experiences:
- Kth Missing Positive Number (LeetCode 1539)
- Missing Number
- Find the Duplicate Number
- First Missing Positive
- Missing Element in Sorted Array

## Related Articles:
- Binary Search Applications
- Missing Number Problems
- Sorted Array Search Techniques
- Counting Missing Elements