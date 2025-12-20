# Search Insert Position of K in a Sorted Array
---

> Video description: https://youtu.be/CS6HeEu9vHk

[Problem](https://www.geeksforgeeks.org/problems/search-insert-position-of-k-in-a-sorted-array/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/CS6HeEu9vHk/0.jpg)](https://youtu.be/CS6HeEu9vHk)

---

**Difficulty:** Easy  
**Accuracy:** 38.99%  
**Submissions:** 94K+  
**Points:** 2  
**Average Time:** 15m

Given a sorted array `arr[]` (0-index based) of distinct integers and an integer k, find the index of k if it is present in the `arr[]`. If not, return the index where k should be inserted to maintain the sorted order.

## Examples:

**Example 1:**
```
Input: arr[] = [1, 3, 5, 6], k = 5
Output: 2

Explanation: 
Since 5 is found at index 2 as arr[2] = 5, the output is 2.

Array: [1, 3, 5, 6]
Index:  0  1  2  3
             ↑
          k=5 found at index 2
```

**Example 2:**
```
Input: arr[] = [1, 3, 5, 6], k = 2
Output: 1

Explanation: 
The element 2 is not present in the array.
Inserting it at index 1 will maintain the sorted order.

Array: [1, 3, 5, 6]
Index:  0  1  2  3
           ↑
        Insert k=2 here

Result: [1, 2, 3, 5, 6]
```

**Example 3:**
```
Input: arr[] = [2, 6, 7, 10, 14], k = 15
Output: 5

Explanation: 
The element 15 is not present in the array.
Inserting it after index 4 (at index 5) will maintain the sorted order.

Array: [2, 6, 7, 10, 14]
Index:  0  1  2   3   4   5
                          ↑
                    Insert k=15 here

Result: [2, 6, 7, 10, 14, 15]
```

## Constraints:
- 1 ≤ arr.size() ≤ 10⁴
- -10³ ≤ arr[i] ≤ 10³
- -10³ ≤ k ≤ 10³

## Expected Complexities:
- **Time Complexity:** O(log n)
- **Space Complexity:** O(1)

## Company Tags:
Microsoft | Amazon | Google

## Topic Tags:
Arrays | Binary Search | Searching

## Approach:

### Problem Understanding:

**Two Cases:**
1. **k is present:** Return its index
2. **k is not present:** Return the index where it should be inserted

**Key Insight:**
- Array is sorted → Binary Search
- If k not found, binary search naturally gives us the insertion position

### Brute Force Analysis:

**Linear Search:**
```
for i from 0 to n-1:
    if arr[i] >= k:
        return i
return n  // k is greater than all elements
```

**Complexity:**
- Time: O(n)
- Not optimal for sorted array

### Optimal Approach: Binary Search

**Key Observations:**

1. **Binary search properties:**
   - If k is found, return its index
   - If k is not found, the search terminates with `left` pointer at insertion position

2. **Insertion position logic:**
   - Find the smallest element ≥ k
   - That's the correct insertion position
   - If no such element exists, insert at end

3. **Binary search invariant:**
   - All elements to left of `left` are < k
   - All elements at or right of `left` are ≥ k
   - When search ends, `left` is the insertion point

### Algorithm:
```
left = 0
right = n - 1

while left <= right:
    mid = left + (right - left) / 2
    
    if arr[mid] == k:
        return mid  // Found k
    
    else if arr[mid] < k:
        left = mid + 1  // Search right half
    
    else:
        right = mid - 1  // Search left half

// If not found, left is the insertion position
return left
```

### Step-by-Step Walkthrough (Example 1):
```
Input: arr = [1, 3, 5, 6], k = 5

Initialize: left = 0, right = 3

Iteration 1:
- mid = 0 + (3 - 0) / 2 = 1
- arr[1] = 3
- 3 < 5 → Search right
- left = 2, right = 3

Iteration 2:
- mid = 2 + (3 - 2) / 2 = 2
- arr[2] = 5
- 5 == 5 → Found!
- Return 2

Output: 2 ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: arr = [1, 3, 5, 6], k = 2

Initialize: left = 0, right = 3

Iteration 1:
- mid = 0 + (3 - 0) / 2 = 1
- arr[1] = 3
- 3 > 2 → Search left
- left = 0, right = 0

Iteration 2:
- mid = 0 + (0 - 0) / 2 = 0
- arr[0] = 1
- 1 < 2 → Search right
- left = 1, right = 0

Condition: left > right → Exit loop

Return left = 1

Verification:
Insert 2 at index 1: [1, 2, 3, 5, 6] ✓
```

### Step-by-Step Walkthrough (Example 3):
```
Input: arr = [2, 6, 7, 10, 14], k = 15

Initialize: left = 0, right = 4

Iteration 1:
- mid = 0 + (4 - 0) / 2 = 2
- arr[2] = 7
- 7 < 15 → Search right
- left = 3, right = 4

Iteration 2:
- mid = 3 + (4 - 3) / 2 = 3
- arr[3] = 10
- 10 < 15 → Search right
- left = 4, right = 4

Iteration 3:
- mid = 4 + (4 - 4) / 2 = 4
- arr[4] = 14
- 14 < 15 → Search right
- left = 5, right = 4

Condition: left > right → Exit loop

Return left = 5

Verification:
Insert 15 at index 5: [2, 6, 7, 10, 14, 15] ✓
```

### Visual Understanding:

**Case 1: k is present**
```
Array: [1, 3, 5, 6], k = 5

Binary search finds 5 at index 2:
[1, 3, 5, 6]
       ↑
    index 2
```

**Case 2: k should go in middle**
```
Array: [1, 3, 5, 6], k = 4

Binary search ends with left = 2:
[1, 3, 5, 6]
       ↑
Insert 4 here at index 2
Result: [1, 3, 4, 5, 6]
```

**Case 3: k should go at start**
```
Array: [2, 6, 7, 10], k = 1

Binary search ends with left = 0:
[2, 6, 7, 10]
 ↑
Insert 1 here at index 0
Result: [1, 2, 6, 7, 10]
```

**Case 4: k should go at end**
```
Array: [2, 6, 7, 10], k = 15

Binary search ends with left = 4:
[2, 6, 7, 10]
             ↑
Insert 15 here at index 4
Result: [2, 6, 7, 10, 15]
```

### Why This Works:

**Binary search invariant:**
```
At any point during binary search:
- All elements in arr[0...left-1] are < k
- All elements in arr[left...n-1] are potentially ≥ k

When search terminates:
- left points to first element ≥ k (or end if all < k)
- This is exactly the insertion position!
```

**Mathematical proof:**
```
If k not found, binary search terminates when left > right

At termination:
- arr[right] < k (if right >= 0)
- arr[left] > k (if left < n)

Therefore, k should be inserted at position left
to maintain sorted order: arr[right] < k < arr[left]
```

### Edge Cases:

1. **k smaller than all elements:**
   - Input: arr = [5, 10, 15], k = 1
   - Output: 0
   - Insert at beginning

2. **k larger than all elements:**
   - Input: arr = [1, 3, 5], k = 10
   - Output: 3 (arr.length)
   - Insert at end

3. **k equals first element:**
   - Input: arr = [1, 3, 5], k = 1
   - Output: 0

4. **k equals last element:**
   - Input: arr = [1, 3, 5], k = 5
   - Output: 2

5. **Single element array:**
   - Input: arr = [5], k = 3
   - Output: 0
   - Input: arr = [5], k = 7
   - Output: 1

6. **Negative numbers:**
   - Input: arr = [-5, -3, 0, 2], k = -4
   - Output: 1

7. **k equals middle element:**
   - Input: arr = [1, 3, 5, 7, 9], k = 5
   - Output: 2

### Common Mistakes:

1. **Using wrong comparison:**
```
   Wrong: if arr[mid] > k instead of arr[mid] >= k
```

2. **Integer overflow in mid calculation:**
```
   Wrong: mid = (left + right) / 2
   Right: mid = left + (right - left) / 2
```

3. **Returning wrong value:**
```
   Wrong: return right (when not found)
   Right: return left (insertion position)
```

4. **Not handling equal case:**
```
   Must return immediately when arr[mid] == k
```

5. **Forgetting to update pointers:**
   - Must update left = mid + 1 or right = mid - 1
   - Not left = mid or right = mid (infinite loop)

### Implementation Variations:

**Standard Binary Search:**
```python
def searchInsert(arr, k):
    left, right = 0, len(arr) - 1
    
    while left <= right:
        mid = left + (right - left) // 2
        
        if arr[mid] == k:
            return mid
        elif arr[mid] < k:
            left = mid + 1
        else:
            right = mid - 1
    
    return left
```

**Using Python bisect module:**
```python
import bisect

def searchInsert(arr, k):
    return bisect.bisect_left(arr, k)
```

**Recursive approach:**
```python
def searchInsert(arr, k, left=0, right=None):
    if right is None:
        right = len(arr) - 1
    
    if left > right:
        return left
    
    mid = left + (right - left) // 2
    
    if arr[mid] == k:
        return mid
    elif arr[mid] < k:
        return searchInsert(arr, k, mid + 1, right)
    else:
        return searchInsert(arr, k, left, mid - 1)
```

### Complexity Analysis:

**Time Complexity: O(log n)**
- Binary search halves search space each iteration
- Maximum iterations: log₂(n)
- Each iteration: O(1) operations

**Space Complexity: O(1)**
- Only using constant extra space (left, right, mid)
- Iterative approach (recursive uses O(log n) stack space)

**Comparison with linear search:**
```
For n = 10,000:
- Linear: 10,000 operations worst case
- Binary: ~14 operations worst case
Binary is ~700x faster!
```

### Why Binary Search is Optimal:

**Lower bound proof:**
- Must examine enough elements to determine position
- Information-theoretic bound: Ω(log n)
- Binary search achieves this bound

**Can't do better:**
- Even with perfect algorithm, need log n comparisons
- Binary search is optimal for comparison-based search

## Related Interview Experiences:
- Search Insert Position (LeetCode 35)
- First Bad Version
- Find First and Last Position of Element
- Search in Rotated Sorted Array
- Find Minimum in Rotated Sorted Array

## Related Articles:
- Binary Search Fundamentals
- Binary Search Variations
- Lower Bound and Upper Bound
- Sorted Array Problems