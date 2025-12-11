# Transpose of Matrix
---

> Video description: https://youtu.be/dId6YD2D0DI

[Problem](https://www.geeksforgeeks.org/problems/transpose-of-matrix-1587115621/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/dId6YD2D0DI/0.jpg)](https://youtu.be/dId6YD2D0DI)

---

**Difficulty:** Easy  
**Accuracy:** 66.5%  
**Submissions:** 118K+  
**Points:** 2  
**Average Time:** 20m

You are given a square matrix of size n × n. Your task is to find the transpose of the given matrix.

The transpose of a matrix is obtained by converting all the rows to columns and all the columns to rows.

## Examples:

**Example 1:**
```
Input: mat[][] = [[1, 1, 1, 1],
                  [2, 2, 2, 2],
                  [3, 3, 3, 3],
                  [4, 4, 4, 4]]

Output: [[1, 2, 3, 4],
         [1, 2, 3, 4],
         [1, 2, 3, 4],
         [1, 2, 3, 4]]

Explanation: Converting rows into columns and columns into rows.

Original Matrix:      Transpose Matrix:
1 1 1 1               1 2 3 4
2 2 2 2      →        1 2 3 4
3 3 3 3               1 2 3 4
4 4 4 4               1 2 3 4
```

**Example 2:**
```
Input: mat[][] = [[1, 2],
                  [9, -2]]

Output: [[1, 9],
         [2, -2]]

Explanation: Converting rows into columns and columns into rows.

Original Matrix:      Transpose Matrix:
1   2        →        1   9
9  -2                 2  -2
```

## Constraints:
- 1 ≤ n ≤ 10³
- -10⁹ ≤ mat[i][j] ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(n²)
- **Space Complexity:** O(1) auxiliary space (in-place modification)

## Company Tags:
MakeMyTrip | InfoEdge | Bloomberg

## Topic Tags:
Matrix | Arrays | Two Pointers | In-place Algorithm

## Approach:

### Understanding Transpose:

**Definition:**
- Transpose of matrix A is denoted as A<sup>T</sup>
- Element at position (i, j) moves to position (j, i)
- mat<sup>T</sup>[i][j] = mat[j][i]

**Visual Understanding:**
```
Original:           Transpose:
mat[0][0] mat[0][1]    mat[0][0] mat[1][0]
mat[1][0] mat[1][1] →  mat[0][1] mat[1][1]

Rows become columns
Columns become rows
```

### Approach 1: Using Extra Space (Not Optimal)

**Algorithm:**
```
n = size of matrix
result = new matrix of size n x n

for i from 0 to n-1:
    for j from 0 to n-1:
        result[j][i] = mat[i][j]

return result
```

**Complexity:**
- Time: O(n²)
- Space: O(n²) - extra matrix

### Approach 2: In-Place Transpose (Optimal)

**Key Observation:**
- For square matrix, we can swap elements along the diagonal
- Only process elements above (or below) the main diagonal
- Swap mat[i][j] with mat[j][i] where i < j

**Algorithm:**
```
n = size of matrix

for i from 0 to n-1:
    for j from i+1 to n-1:  // Only upper triangle
        swap(mat[i][j], mat[j][i])

// Matrix is now transposed in-place
```

**Why this works:**
- Main diagonal (i = j) elements stay in same position
- Elements above diagonal swap with elements below diagonal
- Each pair is swapped exactly once

**Complexity:**
- Time: O(n²) - visit each element once
- Space: O(1) - no extra space, only constant variables

### Step-by-Step Walkthrough (Example 2):
```
Input: mat = [[1, 2],
              [9, -2]]

Initial matrix (with indices):
       j=0  j=1
i=0  |  1    2  |
i=1  |  9   -2  |

Iteration i=0, j=1:
- Swap mat[0][1] with mat[1][0]
- Swap 2 with 9
- Matrix becomes:
       j=0  j=1
i=0  |  1    9  |
i=1  |  2   -2  |

Iteration ends (i=1, j starts at 2 which is out of bounds)

Output: [[1, 9],
         [2, -2]] ✓
```

### Detailed Walkthrough (3×3 Matrix):
```
Input: mat = [[1, 2, 3],
              [4, 5, 6],
              [7, 8, 9]]

Initial:
1 2 3
4 5 6
7 8 9

i=0, j=1: Swap mat[0][1] and mat[1][0] → Swap 2 and 4
1 4 3
2 5 6
7 8 9

i=0, j=2: Swap mat[0][2] and mat[2][0] → Swap 3 and 7
1 4 7
2 5 6
3 8 9

i=1, j=2: Swap mat[1][2] and mat[2][1] → Swap 6 and 8
1 4 7
2 5 8
3 6 9

Output:
1 4 7
2 5 8
3 6 9

Verification:
Original row 0 (1,2,3) → Transpose column 0 (1,2,3) ✓
Original row 1 (4,5,6) → Transpose column 1 (4,5,6) ✓
Original row 2 (7,8,9) → Transpose column 2 (7,8,9) ✓
```

### Visual Understanding:

**Swapping Pattern:**
```
For 4x4 matrix, swap these pairs:
(0,1)↔(1,0)  (0,2)↔(2,0)  (0,3)↔(3,0)
             (1,2)↔(2,1)  (1,3)↔(3,1)
                          (2,3)↔(3,2)

Only upper triangle elements are processed
Diagonal elements (0,0), (1,1), (2,2), (3,3) remain unchanged
```

**Why j starts from i+1:**
```
i=0: j = 1,2,3,... (process entire first row except diagonal)
i=1: j = 2,3,...   (skip already swapped elements)
i=2: j = 3,...     (skip already swapped elements)
...
This ensures each pair is swapped exactly once
```

### Matrix Properties:

**Transpose Properties:**
1. **(A<sup>T</sup>)<sup>T</sup> = A** (transpose of transpose is original)
2. **(A + B)<sup>T</sup> = A<sup>T</sup> + B<sup>T</sup>**
3. **(AB)<sup>T</sup> = B<sup>T</sup>A<sup>T</sup>**
4. For symmetric matrix: **A<sup>T</sup> = A**

**Diagonal Elements:**
- Main diagonal elements (i = j) remain in same position
- mat[i][i] doesn't move during transpose

### Edge Cases:

1. **1×1 matrix:**
   - Input: [[5]]
   - Output: [[5]]
   - No changes needed

2. **2×2 identity matrix:**
   - Input: [[1, 0], [0, 1]]
   - Output: [[1, 0], [0, 1]]
   - Symmetric, stays same

3. **Negative values:**
   - Input: [[-1, -2], [-3, -4]]
   - Output: [[-1, -3], [-2, -4]]
   - Works same as positive

4. **All same elements:**
   - Input: [[7, 7], [7, 7]]
   - Output: [[7, 7], [7, 7]]
   - No visible change but swaps still happen

5. **Large matrix (n=1000):**
   - Must be efficient (O(n²) is acceptable)

### Implementation Details:

**Swap Function:**
```
swap(mat[i][j], mat[j][i]):
    temp = mat[i][j]
    mat[i][j] = mat[j][i]
    mat[j][i] = temp
```

**Loop Boundaries:**
```
for i from 0 to n-1:          // Outer loop: rows
    for j from i+1 to n-1:    // Inner loop: columns after diagonal
        swap(mat[i][j], mat[j][i])
```

**Why not j from 0?**
- If j starts from 0, we'd swap each pair twice
- Result would be original matrix (undoing the transpose)
- Starting from i+1 ensures one swap per pair

### Alternative Approach: Process by Quadrants

**For very large matrices:**
```
// Process in cache-friendly manner
// Divide matrix into blocks and transpose each block
// Then transpose positions of blocks
// This improves cache performance
```

### Common Mistakes:

1. **Swapping all elements:**
```
   Wrong: for j from 0 to n-1  (swaps twice, undoes transpose)
   Right: for j from i+1 to n-1 (swaps once)
```

2. **Starting j from 0:**
   - Results in identity transformation
   - Each swap is undone by second swap

3. **Not handling diagonal:**
   - Diagonal elements don't need swapping
   - j starts from i+1 handles this automatically

4. **Index confusion:**
   - mat[i][j] should swap with mat[j][i], not mat[i][j]

5. **Creating new matrix unnecessarily:**
   - Problem asks for O(1) space
   - In-place is required

### Performance Analysis:

**Number of swaps:**
- Total elements: n²
- Diagonal elements: n (no swap)
- Elements to swap: (n² - n) / 2
- For n=4: (16-4)/2 = 6 swaps ✓

**Time Complexity Breakdown:**
```
Outer loop: n iterations
Inner loop: For i=0 → n-1 iterations
           For i=1 → n-2 iterations
           ...
           For i=n-1 → 0 iterations

Total: (n-1) + (n-2) + ... + 1 + 0 = n(n-1)/2 = O(n²)
```

### Verification Method:

**Check if transpose is correct:**
```
For all i, j:
    transposed[i][j] should equal original[j][i]
```

## Related Interview Experiences:
- Rotate Matrix by 90 Degrees
- Spiral Matrix Traversal
- Set Matrix Zeroes
- Diagonal Traverse
- Matrix Multiplication

## Related Articles:
- Matrix Operations
- In-place Algorithms
- Two-Dimensional Array Manipulation
- Cache-Friendly Matrix Operations
- Linear Algebra in Programming