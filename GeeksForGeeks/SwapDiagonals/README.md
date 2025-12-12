# Swap Diagonals
---

> Video description: https://youtu.be/vP8gFqPofDQ

[Problem](https://www.geeksforgeeks.org/problems/swap-major-and-minor-diagonals-of-a-square-matrix/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/vP8gFqPofDQ/0.jpg)](https://youtu.be/vP8gFqPofDQ)

---

**Difficulty:** Easy  
**Accuracy:** 81.55%  
**Submissions:** 902+  
**Points:** 2  
**Average Time:** 9m

Given a square matrix `mat[][]`, the task is to swap the elements of the major and minor diagonals.

**Definitions:**
- **Major Diagonal:** Elements that lie from the top-left corner to the bottom-right corner of the matrix (i.e., where row index equals column index).
- **Minor Diagonal:** Elements that lie from the top-right corner to the bottom-left corner (i.e., where the sum of row and column indices equals n - 1).

## Examples:

**Example 1:**
```
Input: mat[][] = [[0, 1, 2],
                  [3, 4, 5],
                  [6, 7, 8]]

Output: [[2, 1, 0],
         [3, 4, 5],
         [8, 7, 6]]

Explanation: 
Major Diagonal = [0, 4, 8]
Minor Diagonal = [2, 4, 6]

Visual representation:
Original Matrix:        After Swapping:
0  1  2                 2  1  0
3  4  5        →        3  4  5
6  7  8                 8  7  6

Major diagonal elements swap with minor diagonal elements:
- mat[0][0] (0) ↔ mat[0][2] (2)
- mat[1][1] (4) ↔ mat[1][1] (4) [center remains same]
- mat[2][2] (8) ↔ mat[2][0] (6)
```

**Example 2:**
```
Input: mat[][] = [[2, 3],
                  [5, 4]]

Output: [[3, 2],
         [4, 5]]

Explanation:
Major Diagonal = [2, 4]
Minor Diagonal = [3, 5]

Visual representation:
Original Matrix:        After Swapping:
2  3           →        3  2
5  4                    4  5

Swaps:
- mat[0][0] (2) ↔ mat[0][1] (3)
- mat[1][1] (4) ↔ mat[1][0] (5)
```

## Constraints:
- 1 ≤ mat.size() ≤ 500
- 1 ≤ mat[i][j] ≤ 10⁶

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Microsoft | Adobe

## Topic Tags:
Matrix | Arrays | Two Pointers | In-place Algorithm

## Approach:

### Understanding Diagonals:

**Major Diagonal (Principal Diagonal):**
- Elements where row index = column index
- Position: mat[i][i] for i from 0 to n-1
- For 3×3: mat[0][0], mat[1][1], mat[2][2]

**Minor Diagonal (Secondary Diagonal):**
- Elements where row index + column index = n - 1
- Position: mat[i][n-1-i] for i from 0 to n-1
- For 3×3: mat[0][2], mat[1][1], mat[2][0]

**Visual Understanding (3×3):**
```
Indices:
[0,0]  [0,1]  [0,2]
[1,0]  [1,1]  [1,2]
[2,0]  [2,1]  [2,2]

Major Diagonal (i, i):
  ↓
[0,0]   -     -
  -   [1,1]   -
  -     -   [2,2]
            ↓

Minor Diagonal (i, n-1-i):
        ↓
  -     -   [0,2]
  -   [1,1]   -
[2,0]   -     -
↓
```

### Key Observations:

1. **Swap Pattern:**
   - For each row i, swap mat[i][i] with mat[i][n-1-i]
   - This swaps major diagonal element with minor diagonal element in same row

2. **Center Element (for odd n):**
   - When n is odd, center element is on both diagonals
   - mat[n/2][n/2] swaps with itself (no change)

3. **In-place Operation:**
   - Only need to swap elements, no extra space required
   - Each pair is swapped exactly once

4. **Number of Swaps:**
   - Total: n swaps (one per row)
   - For odd n: middle swap is redundant but harmless

### Algorithm:
```
n = size of matrix (mat.length)

for i from 0 to n-1:
    // Swap major diagonal element with minor diagonal element
    // Major diagonal: mat[i][i]
    // Minor diagonal: mat[i][n-1-i]
    
    swap(mat[i][i], mat[i][n-1-i])

// Matrix is now modified in-place
```

**Simple Implementation:**
```
n = mat.length

for i in range(n):
    temp = mat[i][i]
    mat[i][i] = mat[i][n-1-i]
    mat[i][n-1-i] = temp
```

### Step-by-Step Walkthrough (Example 1):
```
Input: mat = [[0, 1, 2],
              [3, 4, 5],
              [6, 7, 8]]
n = 3

Initial Matrix:
0  1  2
3  4  5
6  7  8

Iteration i=0:
- Major diagonal element: mat[0][0] = 0
- Minor diagonal element: mat[0][3-1-0] = mat[0][2] = 2
- Swap: mat[0][0] ↔ mat[0][2]
- Matrix becomes:
  2  1  0
  3  4  5
  6  7  8

Iteration i=1:
- Major diagonal element: mat[1][1] = 4
- Minor diagonal element: mat[1][3-1-1] = mat[1][1] = 4
- Swap: mat[1][1] ↔ mat[1][1] (same element, no visible change)
- Matrix remains:
  2  1  0
  3  4  5
  6  7  8

Iteration i=2:
- Major diagonal element: mat[2][2] = 8
- Minor diagonal element: mat[2][3-1-2] = mat[2][0] = 6
- Swap: mat[2][2] ↔ mat[2][0]
- Matrix becomes:
  2  1  0
  3  4  5
  8  7  6

Output: [[2, 1, 0],
         [3, 4, 5],
         [8, 7, 6]] ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: mat = [[2, 3],
              [5, 4]]
n = 2

Initial Matrix:
2  3
5  4

Iteration i=0:
- Major diagonal: mat[0][0] = 2
- Minor diagonal: mat[0][2-1-0] = mat[0][1] = 3
- Swap: mat[0][0] ↔ mat[0][1]
- Matrix becomes:
  3  2
  5  4

Iteration i=1:
- Major diagonal: mat[1][1] = 4
- Minor diagonal: mat[1][2-1-1] = mat[1][0] = 5
- Swap: mat[1][1] ↔ mat[1][0]
- Matrix becomes:
  3  2
  4  5

Output: [[3, 2],
         [4, 5]] ✓
```

### Visual Understanding (4×4 Matrix):
```
Original:
a  b  c  d
e  f  g  h
i  j  k  l
m  n  o  p

Major Diagonal: a, f, k, p
Minor Diagonal: d, g, j, m

After Swapping:
d  b  c  a      (row 0: swap a and d)
e  g  f  h      (row 1: swap f and g)
i  j  k  l      (row 2: swap k and j)
p  n  o  m      (row 3: swap p and m)
```

### Detailed Example (5×5 Matrix):
```
Original:
 1   2   3   4   5
 6   7   8   9  10
11  12  13  14  15
16  17  18  19  20
21  22  23  24  25

Major Diagonal: 1, 7, 13, 19, 25
Minor Diagonal: 5, 9, 13, 17, 21

Row-wise swaps:
Row 0: 1 ↔ 5
Row 1: 7 ↔ 9
Row 2: 13 ↔ 13 (center, no change)
Row 3: 19 ↔ 17
Row 4: 25 ↔ 21

After Swapping:
 5   2   3   4   1
 6   9   8   7  10
11  12  13  14  15
16  19  18  17  20
25  22  23  24  21
```

### Edge Cases:

1. **1×1 Matrix:**
   - Input: [[5]]
   - Output: [[5]]
   - Only one element, swaps with itself

2. **2×2 Matrix:**
   - Input: [[1, 2], [3, 4]]
   - Output: [[2, 1], [4, 3]]
   - Two swaps needed

3. **Even-sized Matrix (4×4):**
   - All diagonal elements are distinct
   - 4 swaps needed

4. **Odd-sized Matrix (3×3, 5×5):**
   - Center element on both diagonals
   - Center swap is redundant but harmless

5. **Large Matrix (500×500):**
   - Still O(n) operations
   - Very efficient

### Formula Verification:

**For element at row i:**
- Major diagonal column: i
- Minor diagonal column: n - 1 - i

**Proof for Minor Diagonal:**
```
For minor diagonal: row + col = n - 1
Given row = i
Therefore: i + col = n - 1
So: col = n - 1 - i ✓
```

**Example Verification (n=3):**
```
Row 0: col = 3 - 1 - 0 = 2 ✓
Row 1: col = 3 - 1 - 1 = 1 ✓
Row 2: col = 3 - 1 - 2 = 0 ✓
```

### Common Mistakes:

1. **Wrong minor diagonal formula:**
```
   Wrong: mat[i][n-i]     (off by one)
   Right: mat[i][n-1-i]
```

2. **Trying to swap all pairs:**
```
   Wrong: Nested loop swapping all diagonal elements
   Right: Single loop, one swap per row
```

3. **Modifying both diagonals separately:**
```
   Wrong: First modify major, then minor (double swap = original)
   Right: Swap pairs simultaneously
```

4. **Not handling center element:**
```
   Actually not an issue - swapping with itself works fine
   No special case needed
```

5. **Using extra space:**
```
   Wrong: Creating new matrix
   Right: In-place swapping
```

### Performance Analysis:

**Time Complexity: O(n)**
- Single loop through n rows
- Each iteration does constant work (one swap)
- Total: n swaps

**Space Complexity: O(1)**
- Only using one temporary variable for swap
- No additional data structures
- In-place modification

**Number of Operations:**
```
For n×n matrix:
- Swaps: n
- Comparisons: 0
- Memory reads/writes: 3n (read-read-write for each swap)
```

### Alternative Approach (Not Recommended):

**Using temporary array:**
```
// Store major diagonal
temp = []
for i in range(n):
    temp.append(mat[i][i])

// Copy minor to major
for i in range(n):
    mat[i][i] = mat[i][n-1-i]

// Copy temp to minor
for i in range(n):
    mat[i][n-1-i] = temp[i]
```

**Why not recommended:**
- Uses O(n) extra space
- More operations (3n vs n swaps)
- Same time complexity but worse space

### Verification Method:

**Check if swap is correct:**
```
After swap, for all i from 0 to n-1:
- Original major[i] should now be at minor[i] position
- Original minor[i] should now be at major[i] position
```

## Related Interview Experiences:
- Transpose of Matrix
- Rotate Matrix by 90 Degrees
- Diagonal Traverse
- Set Matrix Zeroes
- Spiral Matrix

## Related Articles:
- Matrix Diagonal Operations
- In-place Matrix Manipulation
- Two-Dimensional Array Indexing
- Matrix Transformations