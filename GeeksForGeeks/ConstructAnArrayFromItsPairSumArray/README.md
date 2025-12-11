# Construct an Array from its Pair-Sum Array
---

> Video description: https://youtu.be/0MzFL6aT1-8

[Problem](https://www.geeksforgeeks.org/problems/construct-an-array-from-its-pair-sum-array/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/0MzFL6aT1-8/0.jpg)](https://youtu.be/0MzFL6aT1-8)

---

**Difficulty:** Easy  
**Accuracy:** 53.94%  
**Submissions:** 5K+  
**Points:** 2  
**Average Time:** 10m

Given a pair-sum array `arr[]`, construct the original array. A pair-sum array for an array is the array that contains sum of all pairs in ordered form, i.e., `arr[0]` is sum of `res[0]` and `res[1]`, `arr[1]` is sum of `res[0]` and `res[2]` and so on.

**Note:** 
- If the size of original array `res[]` is n, then the size of pair-sum array `arr[]` would be n × (n - 1) / 2
- We may assume that the pair-sum array `arr[]` is appropriate in size
- If the original array is correct then the driver code will print true, else false

## Examples:

**Example 1:**
```
Input: arr[] = [4, 5, 3]
Output: true

Explanation: 
A valid original array is [3, 1, 2]
Pairwise sums: (3 + 1) = 4, (3 + 2) = 5, (1 + 2) = 3
Pair-sum array: [4, 5, 3] ✓
```

**Example 2:**
```
Input: arr[] = [3]
Output: true

Explanation: 
One of the valid original arrays is [1, 2]
Pairwise sum: (1 + 2) = 3
Pair-sum array: [3] ✓
```

## Constraints:
- 1 ≤ n ≤ 10³
- 1 ≤ arr[i] ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) auxiliary space

## Company Tags:
Amazon | Google | Microsoft

## Topic Tags:
Mathematical | Arrays | Algebra | Problem Solving

## Approach:

### Problem Understanding:

**What is a pair-sum array?**
- Given original array: [a, b, c]
- Pair-sum array contains: [a+b, a+c, b+c]
- For n elements, we get n×(n-1)/2 pair sums

**Example:**
```
Original: [3, 1, 2]
Pairs: (3,1), (3,2), (1,2)
Pair-sums: 4, 5, 3
Pair-sum array: [4, 5, 3]
```

### Key Observations:

1. **Relationship between sizes:**
   - Original array size: n
   - Pair-sum array size: n×(n-1)/2
   - For n=2: 2×1/2 = 1 pair
   - For n=3: 3×2/2 = 3 pairs
   - For n=4: 4×3/2 = 6 pairs

2. **Finding first element:**
   - arr[0] = res[0] + res[1]
   - arr[1] = res[0] + res[2]
   - arr[n-1] = res[1] + res[2]
   
3. **Mathematical trick:**
   - (res[0] + res[1]) + (res[0] + res[2]) - (res[1] + res[2]) = 2×res[0]
   - arr[0] + arr[1] - arr[n-1] = 2×res[0]
   - res[0] = (arr[0] + arr[1] - arr[n-1]) / 2

4. **Finding other elements:**
   - Once we have res[0], we can find others:
   - res[1] = arr[0] - res[0]
   - res[i] = arr[i-1] - res[0] (for i > 1)

### Algorithm:

**For n = 2 (special case):**
```
// Only one pair sum
// arr[0] = res[0] + res[1]
// Multiple valid answers: [1, arr[0]-1], [2, arr[0]-2], etc.
return [1, arr[0] - 1]
```

**For n ≥ 3:**
```
// Step 1: Calculate size of original array
// arr.length = n * (n-1) / 2
// Solve for n: n^2 - n - 2*arr.length = 0
n = (1 + sqrt(1 + 8 * arr.length)) / 2

// Step 2: Find first element
// res[0] = (arr[0] + arr[1] - arr[n-1]) / 2
res[0] = (arr[0] + arr[1] - arr[n - 1]) / 2

// Step 3: Build rest of array
result = [res[0]]
for i from 0 to n-2:
    result.append(arr[i] - res[0])

return result
```

### Step-by-Step Walkthrough (Example 1):
```
Input: arr[] = [4, 5, 3]

Step 1: Find n (size of original array)
arr.length = 3
n * (n-1) / 2 = 3
n * (n-1) = 6
n^2 - n - 6 = 0
(n - 3)(n + 2) = 0
n = 3 (positive value)

Step 2: Find first element
arr[0] = 4  (res[0] + res[1])
arr[1] = 5  (res[0] + res[2])
arr[2] = 3  (res[1] + res[2])

res[0] = (arr[0] + arr[1] - arr[2]) / 2
       = (4 + 5 - 3) / 2
       = 6 / 2
       = 3

Step 3: Find other elements
res[1] = arr[0] - res[0] = 4 - 3 = 1
res[2] = arr[1] - res[0] = 5 - 3 = 2

Output: [3, 1, 2] ✓

Verification:
3 + 1 = 4 ✓
3 + 2 = 5 ✓
1 + 2 = 3 ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: arr[] = [3]

Step 1: Find n
arr.length = 1
n * (n-1) / 2 = 1
n * (n-1) = 2
n^2 - n - 2 = 0
(n - 2)(n + 1) = 0
n = 2

Step 2: Special case for n = 2
Only one pair sum exists
Multiple valid answers possible
Choose: [1, 2]

Output: [1, 2] ✓

Verification:
1 + 2 = 3 ✓
```

### Mathematical Derivation:

**Finding res[0]:**
```
Given:
arr[0] = res[0] + res[1]
arr[1] = res[0] + res[2]
arr[n-1] = res[1] + res[2]  (last pair sum)

Add first two equations:
arr[0] + arr[1] = 2×res[0] + res[1] + res[2]

Rearrange:
2×res[0] = arr[0] + arr[1] - (res[1] + res[2])
2×res[0] = arr[0] + arr[1] - arr[n-1]

Therefore:
res[0] = (arr[0] + arr[1] - arr[n-1]) / 2
```

### Detailed Example (n = 4):
```
Original: [a, b, c, d]
Pair-sums (in order):
arr[0] = a + b
arr[1] = a + c
arr[2] = a + d
arr[3] = b + c
arr[4] = b + d
arr[5] = c + d

Finding a (res[0]):
arr[0] + arr[1] - arr[5] = (a+b) + (a+c) - (c+d)
                         = 2a + b - d
Wait, this doesn't work directly for n=4...

For n=4, use arr[3] (which is b+c):
a = (arr[0] + arr[1] - arr[3]) / 2
  = ((a+b) + (a+c) - (b+c)) / 2
  = (2a) / 2 = a ✓

Once we have a:
b = arr[0] - a
c = arr[1] - a
d = arr[2] - a
```

### Finding n from array length:
```
Given: m = n * (n-1) / 2 (where m is arr.length)

Multiply by 2: 2m = n^2 - n
Rearrange: n^2 - n - 2m = 0

Using quadratic formula:
n = (1 ± sqrt(1 + 8m)) / 2

Take positive root:
n = (1 + sqrt(1 + 8m)) / 2
```

### Edge Cases:

1. **n = 2 (minimum):**
   - Input: [5]
   - Output: [1, 4] or [2, 3] (multiple valid)

2. **Large values:**
   - Input: [10⁹, 10⁹, 10⁹]
   - Must handle large sums

3. **Small values:**
   - Input: [2, 3, 4]
   - Output: [1, 1, 2]

4. **All same pair-sums:**
   - Input: [6, 6, 6]
   - Original: [3, 3, 3]

### Implementation Details:

**Finding n efficiently:**
```python
import math

m = len(arr)
n = int((1 + math.sqrt(1 + 8 * m)) / 2)
```

**Building result:**
```python
if n == 2:
    return [1, arr[0] - 1]

res0 = (arr[0] + arr[1] - arr[n - 1]) // 2
result = [res0]

for i in range(n - 1):
    result.append(arr[i] - res0)

return result
```

### Why This Works:

**Proof of correctness:**
1. We correctly calculate n from array length
2. Mathematical derivation gives us res[0]
3. All other elements follow from pair-sum definition
4. Each arr[i] for i < n-1 represents res[0] + res[i+1]

**Uniqueness:**
- For n ≥ 3, solution is unique (up to adding constant to all)
- For n = 2, infinite solutions exist (we choose one)

### Common Mistakes:

1. **Wrong formula for res[0]:**
   - Not using arr[n-1] (last element)
   - Using wrong combination of elements

2. **Integer division issues:**
   - Must ensure (arr[0] + arr[1] - arr[n-1]) is even

3. **Not handling n=2 case:**
   - Special case with multiple valid answers

4. **Wrong index calculation:**
   - arr[n-1] is index n-1, not arr.length-1

5. **Not calculating n correctly:**
   - Quadratic formula errors
   - Taking negative root

### Optimization Notes:

**Time Complexity: O(n)**
- Calculate n: O(1)
- Build result: O(n)
- Total: O(n)

**Space Complexity: O(1) auxiliary**
- Only storing result (required output)
- No extra data structures

## Related Interview Experiences:
- Construct Binary Tree from Preorder and Inorder
- Find Missing and Repeating Numbers
- Reconstruct Original Array
- Pair Sum Problems

## Related Articles:
- Algebraic Problem Solving
- Array Reconstruction Problems
- Mathematical Derivations
- Quadratic Formula Applications