# Kth Element in Matrix
---

> Video description: https://youtu.be/4NJNhLaS8-w

[Problem](https://www.geeksforgeeks.org/problems/kth-element-in-matrix/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/4NJNhLaS8-w/0.jpg)](https://youtu.be/4NJNhLaS8-w)

---

**Difficulty:** Medium  
**Accuracy:** 42.35%  
**Submissions:** 85K+  
**Points:** 4  
**Average Time:** 35m

Given a N × N matrix, where every row and column is sorted in non-decreasing order. Find the k<sup>th</sup> smallest element in the matrix.

## Examples:

**Example 1:**
```
Input: 
mat[][] = [[16, 28, 60, 64],
           [22, 41, 63, 91],
           [27, 50, 87, 93],
           [36, 78, 87, 94]]
k = 3

Output: 27

Explanation:
Sorted order: 16, 22, 27, 28, 36, 41, 50, 60, 63, 64, 78, 87, 87, 91, 93, 94
The 3rd smallest element is 27.
```

**Example 2:**
```
Input:
mat[][] = [[10, 20, 30, 40],
           [15, 25, 35, 45],
           [24, 29, 37, 48],
           [32, 33, 39, 50]]
k = 7

Output: 30

Explanation:
Sorted order: 10, 15, 20, 24, 25, 29, 30, 32, 33, 35, 37, 39, 40, 45, 48, 50
The 7th smallest element is 30.
```

## Constraints:
- 1 ≤ N ≤ 50
- 1 ≤ mat[i][j] ≤ 10⁴
- 1 ≤ k ≤ N²

## Expected Complexities:
- **Time Complexity:** O(N² log N) or O(N log N × log(max - min))
- **Space Complexity:** O(N) or O(1)

## Company Tags:
Amazon | Google | Microsoft | Facebook

## Topic Tags:
Matrix | Binary Search | Heap | Sorting

## Approach:

### Problem Understanding:

**Key Properties:**
1. N × N matrix (square matrix)
2. Each row is sorted in non-decreasing order
3. Each column is sorted in non-decreasing order
4. Find k<sup>th</sup> smallest element (1-indexed)

**Important Note:**
- Rows are sorted individually
- Columns are sorted individually
- But matrix is NOT fully sorted (diagonal property doesn't hold globally)

### Brute Force Analysis:

**Approach 1: Flatten and Sort**
```
// Step 1: Copy all elements to array
array = []
for i from 0 to N-1:
    for j from 0 to N-1:
        array.append(mat[i][j])

// Step 2: Sort array
sort(array)

// Step 3: Return kth element
return array[k-1]
```

**Complexity:**
- Time: O(N² log N²) = O(N² log N) - sorting all elements
- Space: O(N²) - storing all elements
- Works but not optimal

### Approach 1: Min Heap (Priority Queue)

**Key Insight:**
- Smallest element is at mat[0][0]
- Next smallest is either mat[0][1] or mat[1][0]
- Use min heap to always extract minimum and add next candidates

**Algorithm:**
```
function kthSmallest(mat, k):
    n = mat.length
    minHeap = new PriorityQueue()
    visited = new Set()
    
    // Start with top-left element
    minHeap.add((mat[0][0], 0, 0))
    visited.add((0, 0))
    
    count = 0
    while count < k:
        (value, i, j) = minHeap.poll()
        count++
        
        if count == k:
            return value
        
        // Add right neighbor
        if j+1 < n and (i, j+1) not in visited:
            minHeap.add((mat[i][j+1], i, j+1))
            visited.add((i, j+1))
        
        // Add bottom neighbor
        if i+1 < n and (i+1, j) not in visited:
            minHeap.add((mat[i+1][j], i+1, j))
            visited.add((i+1, j))
    
    return -1  // Should never reach
```

**Complexity:**
- Time: O(k log N) - k operations, heap size ≤ 2N
- Space: O(N) - heap and visited set

### Approach 2: Binary Search on Value Range

**Key Insight:**
- Search space: [mat[0][0], mat[N-1][N-1]]
- For a value mid, count elements ≤ mid
- If count < k, search right; else search left

**Algorithm:**
```
function kthSmallest(mat, k):
    n = mat.length
    low = mat[0][0]
    high = mat[n-1][n-1]
    
    while low < high:
        mid = low + (high - low) / 2
        
        // Count elements <= mid
        count = countLessEqual(mat, mid)
        
        if count < k:
            low = mid + 1
        else:
            high = mid
    
    return low

function countLessEqual(mat, target):
    n = mat.length
    count = 0
    
    // Start from bottom-left corner
    row = n - 1
    col = 0
    
    while row >= 0 and col < n:
        if mat[row][col] <= target:
            count += (row + 1)  // All elements in this column up to row
            col++
        else:
            row--
    
    return count
```

**Complexity:**
- Time: O(N log(max - min)) - binary search on range, O(N) to count
- Space: O(1) - constant space

### Step-by-Step Walkthrough (Min Heap Approach):
```
Input: mat = [[16, 28, 60, 64],
              [22, 41, 63, 91],
              [27, 50, 87, 93],
              [36, 78, 87, 94]]
       k = 3

Step 1: Initialize
- minHeap = [(16, 0, 0)]
- visited = {(0,0)}
- count = 0

Step 2: Extract minimum (count = 1)
- Poll: (16, 0, 0)
- count = 1
- Add right: (28, 0, 1)
- Add bottom: (22, 1, 0)
- minHeap = [(22, 1, 0), (28, 0, 1)]

Step 3: Extract minimum (count = 2)
- Poll: (22, 1, 0)
- count = 2
- Add right: (41, 1, 1)
- Add bottom: (27, 2, 0)
- minHeap = [(27, 2, 0), (28, 0, 1), (41, 1, 1)]

Step 4: Extract minimum (count = 3)
- Poll: (27, 2, 0)
- count = 3
- count == k, return 27

Output: 27 ✓
```

### Step-by-Step Walkthrough (Binary Search Approach):
```
Input: mat = [[10, 20, 30, 40],
              [15, 25, 35, 45],
              [24, 29, 37, 48],
              [32, 33, 39, 50]]
       k = 7

Initialize:
- low = 10 (mat[0][0])
- high = 50 (mat[3][3])

Iteration 1:
- mid = 10 + (50-10)/2 = 30
- Count elements ≤ 30:
  Start from (3, 0):
  mat[3][0] = 32 > 30, row--
  mat[2][0] = 24 ≤ 30, count = 3, col++
  mat[2][1] = 29 ≤ 30, count = 6, col++
  mat[2][2] = 37 > 30, row--
  mat[1][2] = 35 > 30, row--
  mat[0][2] = 30 ≤ 30, count = 7, col++
  mat[0][3] = 40 > 30, row--
  row < 0, stop
  count = 7
- count = 7, k = 7
- count >= k, high = mid = 30

Iteration 2:
- low = 10, high = 30
- mid = 20
- Count elements ≤ 20:
  count = 2 (only 10, 15, 20)
- count < k, low = mid + 1 = 21

Iteration 3:
- low = 21, high = 30
- mid = 25
- Count elements ≤ 25:
  count = 5 (10, 15, 20, 24, 25)
- count < k, low = mid + 1 = 26

Iteration 4:
- low = 26, high = 30
- mid = 28
- Count elements ≤ 28:
  count = 5 (no change from 25)
- count < k, low = mid + 1 = 29

Iteration 5:
- low = 29, high = 30
- mid = 29
- Count elements ≤ 29:
  count = 6 (10, 15, 20, 24, 25, 29)
- count < k, low = mid + 1 = 30

Iteration 6:
- low = 30, high = 30
- Exit loop

Output: 30 ✓
```

### Visual Understanding (Counting Technique):

**Matrix:**
```
10  20  30  40
15  25  35  45
24  29  37  48
32  33  39  50
```

**Counting elements ≤ 30 (start from bottom-left):**
```
Step-by-step from (3,0):

10  20  30  40
15  25  35  45
24  29  37  48
↑   ↑   ↑
32  33  39  50
↑
start here

Position (3,0): 32 > 30, move up
Position (2,0): 24 ≤ 30, count all 3 elements in column 0, move right
Position (2,1): 29 ≤ 30, count all 3 elements in column 1, move right
Position (2,2): 37 > 30, move up
Position (1,2): 35 > 30, move up
Position (0,2): 30 ≤ 30, count 1 element in column 2, move right
Position (0,3): 40 > 30, move up
Position (-1,3): out of bounds, stop

Total count: 3 + 3 + 1 = 7
```

### Why Binary Search Works:

**Monotonicity Property:**
```
For any value x:
- count(x) = number of elements ≤ x
- If x < y, then count(x) ≤ count(y)
- This monotonicity allows binary search

Goal: Find smallest x where count(x) ≥ k
This x is the kth smallest element
```

**Correctness:**
```
Binary search finds the smallest value mid where:
- count(mid) ≥ k

This mid is guaranteed to be in the matrix because:
- If count(mid) >= k, there are at least k elements ≤ mid
- The kth smallest must be ≤ mid
- Binary search converges to exact value present in matrix
```

### Edge Cases:

1. **k = 1 (smallest element):**
   - Input: mat = [[1, 5], [3, 7]], k = 1
   - Output: 1 (mat[0][0])

2. **k = N² (largest element):**
   - Input: mat = [[1, 5], [3, 7]], k = 4
   - Output: 7 (mat[N-1][N-1])

3. **All elements same:**
   - Input: mat = [[5, 5], [5, 5]], k = 2
   - Output: 5

4. **Single element matrix:**
   - Input: mat = [[10]], k = 1
   - Output: 10

5. **Diagonal matrix pattern:**
   - Values increase uniformly

6. **Duplicate values:**
   - Input: mat = [[1, 2, 2], [2, 3, 3], [3, 4, 4]], k = 5
   - Handle duplicates correctly

### Common Mistakes:

1. **Assuming fully sorted matrix:**
```
   Wrong: mat[i][j] < mat[i+1][j+1] (not always true)
   Right: Only rows and columns are sorted individually
```

2. **Wrong counting in binary search:**
```
   Must use proper technique from bottom-left or top-right
   Simple iteration won't work efficiently
```

3. **Off-by-one in heap approach:**
```
   Remember k is 1-indexed
   Extract k times, not k-1 times
```

4. **Not handling boundaries in counting:**
   - Check row >= 0 and col < n
   - Don't go out of bounds

5. **Wrong binary search condition:**
```
   Wrong: if count <= k
   Right: if count < k
```

### Comparison of Approaches:

**Approach 1: Flatten and Sort**
- Time: O(N² log N)
- Space: O(N²)
- Pros: Simple to implement
- Cons: High space complexity

**Approach 2: Min Heap**
- Time: O(k log N)
- Space: O(N)
- Pros: Good for small k
- Cons: Slower for large k

**Approach 3: Binary Search**
- Time: O(N log(max - min))
- Space: O(1)
- Pros: Optimal space, consistent time
- Cons: Slightly more complex

**When to use which:**
- Small k (k << N²): Use Min Heap
- Large k or tight space: Use Binary Search
- Teaching/Simple implementation: Flatten and sort

### Implementation Details:

**Counting from bottom-left:**
```python
def countLessEqual(mat, target):
    n = len(mat)
    count = 0
    row = n - 1
    col = 0
    
    while row >= 0 and col < n:
        if mat[row][col] <= target:
            # All elements above in this column are smaller
            count += (row + 1)
            col += 1
        else:
            # Move up to find smaller elements
            row -= 1
    
    return count
```

**Min Heap with tuple:**
```python
import heapq

def kthSmallest(mat, k):
    n = len(mat)
    heap = [(mat[0][0], 0, 0)]
    visited = {(0, 0)}
    
    for _ in range(k):
        val, i, j = heapq.heappop(heap)
        
        if _ == k - 1:
            return val
        
        if j + 1 < n and (i, j + 1) not in visited:
            heapq.heappush(heap, (mat[i][j + 1], i, j + 1))
            visited.add((i, j + 1))
        
        if i + 1 < n and (i + 1, j) not in visited:
            heapq.heappush(heap, (mat[i + 1][j], i + 1, j))
            visited.add((i + 1, j))
```

### Complexity Analysis Summary:

**Min Heap Approach:**
- Time: O(k log N) where heap size ≤ 2N
- Space: O(N) for heap and visited set
- Best for: Small k values

**Binary Search Approach:**
- Time: O(N × log(range)) where range = max - min
- Space: O(1) constant space
- Best for: Space-constrained, large k

**Trade-offs:**
```
For k = N²/2 (middle element):
- Min Heap: ~(N²/2) × log N
- Binary Search: N × log(range)

Binary search is usually faster for large k
```

## Related Interview Experiences:
- Kth Smallest Element in a Sorted Matrix (LeetCode 378)
- Find K Pairs with Smallest Sums
- Kth Smallest Element (Quickselect)
- Median of Two Sorted Arrays
- Search a 2D Matrix II

## Related Articles:
- Binary Search Applications
- Heap Data Structure
- Matrix Search Techniques
- Order Statistics
- Counting in Sorted Matrix