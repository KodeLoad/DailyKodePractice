# Game of XOR
---

> Video description: https://youtu.be/xPdf9HHgUL0

[Problem](https://www.geeksforgeeks.org/problems/game-of-xor1541/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/xPdf9HHgUL0/0.jpg)](https://youtu.be/xPdf9HHgUL0)

---

**Difficulty:** Medium  
**Accuracy:** 50.77%  
**Submissions:** 40K+  
**Points:** 4  
**Average Time:** 45m

You are given an integer array `arr[]`. The value of a subarray is defined as the bitwise XOR of all elements in that subarray. Your task is to compute the bitwise XOR of the values of all possible subarrays of `arr[]`.

## Examples:

**Example 1:**
```
Input: arr[] = [1, 2, 3]
Output: 2

Explanation:
All subarrays and their XOR values:
xor[1] = 1
xor[1, 2] = 1 ^ 2 = 3
xor[2] = 2
xor[2, 3] = 2 ^ 3 = 1
xor[3] = 3
xor[1, 2, 3] = 1 ^ 2 ^ 3 = 0

Result: 1 ^ 3 ^ 2 ^ 1 ^ 3 ^ 0 = 2
```

**Example 2:**
```
Input: arr[] = [1, 2]
Output: 0

Explanation:
All subarrays and their XOR values:
xor[1] = 1
xor[1, 2] = 1 ^ 2 = 3
xor[2] = 2

Result: 1 ^ 3 ^ 2 = 0
```

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ arr[i] ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon

## Topic Tags:
Arrays | Bit Manipulation | XOR | Mathematical | Combinatorics

## Approach:

### Brute Force Analysis (TLE):

**Naive Approach:**
```
result = 0
for i in range(n):
    for j in range(i, n):
        subarray_xor = 0
        for k in range(i, j+1):
            subarray_xor ^= arr[k]
        result ^= subarray_xor
return result
```

**Complexity:** O(n³) - Too slow for constraints

### Key Observations:

1. **XOR Properties:**
   - a ^ a = 0 (XOR of same number is 0)
   - a ^ 0 = a (XOR with 0 is identity)
   - XOR is commutative and associative

2. **Frequency Pattern:**
   - An element at index i appears in multiple subarrays
   - Count how many times each element contributes to final XOR
   - If an element appears even times → cancels out (XOR property)
   - If an element appears odd times → contributes to result

3. **Critical Insight:**
   - For array of length n, element at index i appears in:
     - Number of subarrays = (i + 1) × (n - i)
   - Why? 
     - (i + 1) choices for starting position (0 to i)
     - (n - i) choices for ending position (i to n-1)

4. **When does element contribute?**
   - Element contributes only if (i + 1) × (n - i) is ODD
   - For product to be odd, both factors must be odd
   - This happens when both (i + 1) and (n - i) are odd

5. **Simplification for even/odd n:**
   - **If n is EVEN:** No element appears odd times → result = 0
   - **If n is ODD:** Only elements at even indices (0, 2, 4, ...) appear odd times

### Optimized Algorithm:
```
n = length of array
result = 0

if n is even:
    return 0

// n is odd
for i = 0 to n-1 with step 2:  // only even indices
    result ^= arr[i]

return result
```

### Mathematical Proof:

**Case 1: n is even**
```
For any index i:
- (i + 1) and (n - i) have opposite parity
- One is even, one is odd
- Product is always even
- All elements appear even times
- XOR of all = 0
```

**Case 2: n is odd**
```
For index i to contribute (odd frequency):
- Need (i + 1) × (n - i) to be odd
- Both must be odd

When is (i + 1) odd? → i is even (0, 2, 4, ...)
When is (n - i) odd? → i is even (since n is odd)

Only even indices contribute!
```

### Step-by-Step Walkthrough (Example 1):
```
arr[] = [1, 2, 3], n = 3 (odd)

Count appearances:
Index 0 (value=1): (0+1) × (3-0) = 1 × 3 = 3 (odd) ✓
Index 1 (value=2): (1+1) × (3-1) = 2 × 2 = 4 (even) ✗
Index 2 (value=3): (2+1) × (3-2) = 3 × 1 = 3 (odd) ✓

Only indices 0 and 2 contribute:
Result = arr[0] ^ arr[2] = 1 ^ 3 = 2
```

### Step-by-Step Walkthrough (Example 2):
```
arr[] = [1, 2], n = 2 (even)

Since n is even:
Result = 0 (no element appears odd times)
```

### Verification (Example 1 - Long way):
```
arr[] = [1, 2, 3]

All subarrays:
[1]: XOR = 1
[1,2]: XOR = 3
[1,2,3]: XOR = 0
[2]: XOR = 2
[2,3]: XOR = 1
[3]: XOR = 3

Final XOR: 1 ^ 3 ^ 0 ^ 2 ^ 1 ^ 3
= (1 ^ 1) ^ (3 ^ 3) ^ 0 ^ 2
= 0 ^ 0 ^ 0 ^ 2
= 2 ✓
```

### Edge Cases:

1. **Single element (n=1, odd):**
   - Input: [5]
   - Only one subarray: [5]
   - Output: 5

2. **Two elements (n=2, even):**
   - Input: [a, b]
   - Output: 0 (always)

3. **All zeros:**
   - Input: [0, 0, 0]
   - Output: 0

4. **Large array with even length:**
   - Input: any array with even n
   - Output: 0 (no computation needed)

### Why This Works:

**XOR Cancellation:**
```
When element appears even times:
a ^ a ^ a ^ a = (a ^ a) ^ (a ^ a) = 0 ^ 0 = 0

When element appears odd times:
a ^ a ^ a = (a ^ a) ^ a = 0 ^ a = a
```

**Pattern Recognition:**
```
n = 1: result = arr[0]
n = 2: result = 0
n = 3: result = arr[0] ^ arr[2]
n = 4: result = 0
n = 5: result = arr[0] ^ arr[2] ^ arr[4]
n = 6: result = 0
```

### Common Mistakes:

1. **Computing all subarrays:** O(n²) or O(n³) solutions TLE
2. **Not recognizing even-length optimization:** Missing O(1) case
3. **Incorrect frequency calculation:** Wrong formula for appearances
4. **Not using XOR properties:** Missing cancellation patterns

## Related Interview Experiences:
- Single Number (LeetCode 136)
- Single Number II (LeetCode 137)
- XOR Queries of a Subarray
- Subarray XOR Problems
- Bit Manipulation Patterns

## Related Articles:
- XOR Properties and Applications
- Bit Manipulation Techniques
- Subarray Problems Optimization
- Combinatorial Counting in Arrays
