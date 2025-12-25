# Find the Peak Element in a 2D Matrix
---

> Video description: https://youtu.be/DZQkDVQ-0MM

[Problem](https://www.geeksforgeeks.org/problems/find-the-peak-element-in-a-2d-matrix/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/DZQkDVQ-0MM/0.jpg)](https://youtu.be/DZQkDVQ-0MM)

---

**Difficulty:** Medium  
**Accuracy:** 57.85%  
**Submissions:** 9K+  
**Points:** 4  
**Average Time:** 35m

Given a 2D matrix `mat[][]`, identify any peak element within the matrix.

An element is considered a **peak** if it is **greater than or equal to** its four immediate neighbors: top, bottom, left, and right. For corner and edge elements, any missing neighbors are treated as having a value of negative infinity.

**Note:** 
- A peak element is not necessarily the global maximum, it only needs to satisfy the condition relative to its adjacent elements
- Multiple peak elements may exist, return any one of them
- The driver code will print true if you return the correct position of peak element, else it will print false

## Examples:

**Example 1:**
```
Input: mat[][] = [[10, 20, 15],
                  [21, 30, 14],
                  [ 7, 16, 32]]

Output: true

Explanation: 
One of the peak elements is 30 at index (1, 1).
It is greater than or equal to all its valid neighbors:
- Left = 21 ✓ (30 > 21)
- Right = 14 ✓ (30 > 14)
- Top = 20 ✓ (30 > 20)
- Bottom = 16 ✓ (30 > 16)

Visual representation:
    10  20  15
    21 [30] 14    ← 30 is peak
     7  16  32

Alternatively, (2, 2) with value 32 also qualifies as a peak:
- Left = 16 ✓ (32 > 16)
- Top = 14 ✓ (32 > 14)
- Right = -∞ (out of bounds) ✓
- Bottom = -∞ (out of bounds) ✓
```

**Example 2:**
```
Input: mat[][] = [[17, 7],
                  [11, 10]]

Output: true

Explanation: 
17 is the only peak element at index (0, 0).
Its neighbors are:
- Right = 7 ✓ (17 > 7)
- Bottom = 11 ✓ (17 > 11)
- Top = -∞ (out of bounds) ✓
- Left = -∞ (out of bounds) ✓

Visual representation:
   [17]  7     ← 17 is peak
    11  10
```

## Constraints:
- 1 ≤ n × m ≤ 10⁶
- -10⁶ ≤ mat[i][j] ≤ 10⁶

## Expected Complexities:
- **Time Complexity:** O(n log m) or O(m log n)
- **Space Complexity:** O(1)

## Company Tags:
Google | Amazon | Microsoft | Adobe

## Topic Tags:
Binary Search | Matrix | Algorithms | Divide and Conquer

## Approach:

### Problem Understanding:

**Peak Element Definition:**
- Element ≥ all four neighbors (top, bottom, left, right)
- Out-of-bounds neighbors treated as -∞
- Corner elements have 2 neighbors
- Edge elements have 3 neighbors
- Internal elements have 4 neighbors

**Key Insight:**
- We don't need the global maximum
- Just need an element that's locally maximum
- Multiple peaks can exist - return any one

### Brute Force Analysis:

**Naive Approach:**
```
for i from 0 to n-1:
    for j from 0 to m-1:
        if mat[i][j] is peak:
            return (i, j)
```

**Complexity:**
- Time: O(n × m) - check every element
- Too slow for n × m = 10⁶

### Optimal Approach: Binary Search on Columns

**Key Observations:**

1. **Binary search applicability:**
   - For each column, find the maximum element
   - Check if it's a peak by comparing with left and right neighbors
   - If not peak, move towards the greater neighbor

2. **Why this works:**
   - Maximum element in a column is guaranteed to be ≥ top and bottom
   - Only need to check left and right
   - Can use binary search on columns

3. **Algorithm intuition:**
   - Pick middle column
   - Find max element in that column (handles top/bottom)
   - Compare with left and right neighbors
   - If peak found, return it
   - If left > current, peak must be in left half
   - If right > current, peak must be in right half

### Algorithm:
```
function findPeak(mat):
    n = rows, m = cols
    left = 0, right = m - 1
    
    while left <= right:
        mid = left + (right - left) / 2
        
        // Find max element in column mid
        maxRow = findMaxInColumn(mat, mid, n)
        
        // Get neighbors
        leftVal = (mid > 0) ? mat[maxRow][mid-1] : -∞
        rightVal = (mid < m-1) ? mat[maxRow][mid+1] : -∞
        current = mat[maxRow][mid]
        
        // Check if peak
        if current >= leftVal AND current >= rightVal:
            return (maxRow, mid)
        
        // Move towards greater neighbor
        else if leftVal > current:
            right = mid - 1
        else:
            left = mid + 1
    
    return (-1, -1)  // Should never reach here

function findMaxInColumn(mat, col, n):
    maxVal = mat[0][col]
    maxRow = 0
    for i from 1 to n-1:
        if mat[i][col] > maxVal:
            maxVal = mat[i][col]
            maxRow = i
    return maxRow
```

### Step-by-Step Walkthrough (Example 1):
```
Input: mat = [[10, 20, 15],
              [21, 30, 14],
              [ 7, 16, 32]]
n = 3, m = 3

Initialize: left = 0, right = 2

Iteration 1:
- mid = 0 + (2 - 0) / 2 = 1
- Find max in column 1: [20, 30, 16]
  - maxRow = 1, maxVal = 30
- Get neighbors:
  - leftVal = mat[1][0] = 21
  - rightVal = mat[1][2] = 14
  - current = mat[1][1] = 30
- Check: 30 >= 21 AND 30 >= 14? Yes! ✓
- Peak found at (1, 1)

Return (1, 1)

Verification:
mat[1][1] = 30
- Top: mat[0][1] = 20 ✓ (30 > 20)
- Bottom: mat[2][1] = 16 ✓ (30 > 16)
- Left: mat[1][0] = 21 ✓ (30 > 21)
- Right: mat[1][2] = 14 ✓ (30 > 14)
30 is a peak! ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: mat = [[17, 7],
              [11, 10]]
n = 2, m = 2

Initialize: left = 0, right = 1

Iteration 1:
- mid = 0 + (1 - 0) / 2 = 0
- Find max in column 0: [17, 11]
  - maxRow = 0, maxVal = 17
- Get neighbors:
  - leftVal = -∞ (out of bounds)
  - rightVal = mat[0][1] = 7
  - current = mat[0][0] = 17
- Check: 17 >= -∞ AND 17 >= 7? Yes! ✓
- Peak found at (0, 0)

Return (0, 0)

Verification:
mat[0][0] = 17
- Top: -∞ ✓
- Bottom: mat[1][0] = 11 ✓ (17 > 11)
- Left: -∞ ✓
- Right: mat[0][1] = 7 ✓ (17 > 7)
17 is a peak! ✓
```

### Detailed Example (Complex Case):
```
Input: mat = [[10, 8,  10, 10],
              [14, 13, 12, 11],
              [15, 9,  11, 21],
              [16, 17, 19, 20]]

Initialize: left = 0, right = 3

Iteration 1:
- mid = 1
- Column 1: [8, 13, 9, 17]
- maxRow = 3, maxVal = 17
- Neighbors: left=16, right=19
- 17 >= 16? Yes ✓
- 17 >= 19? No ✗
- Move right: left = 2, right = 3

Iteration 2:
- mid = 2
- Column 2: [10, 12, 11, 19]
- maxRow = 3, maxVal = 19
- Neighbors: left=17, right=20
- 19 >= 17? Yes ✓
- 19 >= 20? No ✗
- Move right: left = 3, right = 3

Iteration 3:
- mid = 3
- Column 3: [10, 11, 21, 20]
- maxRow = 2, maxVal = 21
- Neighbors: left=11, right=-∞
- 21 >= 11? Yes ✓
- 21 >= -∞? Yes ✓
- Peak found at (2, 3)

Return (2, 3)
```

### Why This Works:

**Correctness proof:**

1. **Maximum in column is ≥ top and bottom:**
   - By definition of maximum
   - Already satisfies 2 out of 4 conditions

2. **Binary search convergence:**
   - If current element < left neighbor:
     - A peak must exist in left half
     - Because we're moving towards higher values
   
3. **Guarantee of finding peak:**
   - We always move towards increasing values
   - At array boundaries, neighbors are -∞
   - Must eventually find a peak

**Visual intuition:**
```
If current < left, peak is in left half:
    ... ← ← [higher] current ← ...
             peak must be here ↑

If current < right, peak is in right half:
    ... → current [higher] → → ...
             peak must be here ↑
```

### Edge Cases:

1. **Single element:**
   - Input: [[5]]
   - Output: (0, 0)
   - Only element is peak by definition

2. **Single row:**
   - Input: [[1, 3, 2]]
   - Maximum element is peak

3. **Single column:**
   - Input: [[1], [3], [2]]
   - Maximum element is peak

4. **All elements same:**
   - Input: [[5, 5], [5, 5]]
   - Any element is peak

5. **Peak at corner:**
   - Input: [[20, 1], [1, 1]]
   - (0, 0) is peak

6. **Peak at edge:**
   - Input: [[1, 5, 1], [1, 1, 1]]
   - (0, 1) is peak

7. **Multiple peaks:**
   - Return any one

8. **Negative numbers:**
   - Input: [[-5, -3], [-10, -20]]
   - (-3 at (0,1) is peak)

### Common Mistakes:

1. **Not finding maximum in column first:**
   - Must ensure element ≥ top and bottom
   - Then only check left and right

2. **Wrong binary search direction:**
```
   Wrong: Always move right
   Right: Move towards greater neighbor
```

3. **Not handling boundaries:**
   - Must treat out-of-bounds as -∞
   - Check array bounds before accessing

4. **Comparing with all 4 neighbors every time:**
   - Inefficient - O(n × m)
   - Use binary search optimization

5. **Not handling equal neighbors:**
   - Condition is ≥, not just >
   - Equal neighbors still qualify as peak

### Alternative Approach: Binary Search on Both Dimensions

**More complex but can be O(log n + log m):**
```
1. Binary search on rows to find a row
2. Binary search on columns in that row
3. Verify if element is peak
4. Adjust search space accordingly
```

**Trade-off:**
- More complex implementation
- Similar time complexity in practice
- Original approach is simpler and sufficient

### Implementation Details:

**Finding maximum in column:**
```python
def findMaxInColumn(mat, col):
    maxVal = mat[0][col]
    maxRow = 0
    for i in range(1, len(mat)):
        if mat[i][col] > maxVal:
            maxVal = mat[i][col]
            maxRow = i
    return maxRow
```

**Checking if peak:**
```python
def isPeak(mat, row, col):
    n, m = len(mat), len(mat[0])
    val = mat[row][col]
    
    # Check all 4 directions
    if row > 0 and mat[row-1][col] > val:
        return False
    if row < n-1 and mat[row+1][col] > val:
        return False
    if col > 0 and mat[row][col-1] > val:
        return False
    if col < m-1 and mat[row][col+1] > val:
        return False
    
    return True
```

### Complexity Analysis:

**Time Complexity: O(n log m)**
- Binary search on columns: O(log m)
- For each mid column, find max: O(n)
- Total: O(n × log m)

**Alternative: O(m log n)**
- Binary search on rows: O(log n)
- For each mid row, find max: O(m)
- Total: O(m × log n)

**Choose based on shape:**
- If n < m: Use O(n log m)
- If m < n: Use O(m log n)

**Space Complexity: O(1)**
- Only using constant extra variables
- No additional data structures

### Why Better Than O(n × m):
```
For n = 1000, m = 1000:
- Brute force: 1,000,000 operations
- Binary search: 1000 × log(1000) ≈ 10,000 operations
- 100x faster!
```

## Related Interview Experiences:
- Find Peak Element (LeetCode 162)
- Find a Peak Element II (LeetCode 1901)
- Search a 2D Matrix
- Search a 2D Matrix II
- Find Minimum in Rotated Sorted Array II

## Related Articles:
- Binary Search in 2D Arrays
- Divide and Conquer Algorithms
- Matrix Search Problems
- Peak Finding Algorithms