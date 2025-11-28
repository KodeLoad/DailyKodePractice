# Subset XOR
---

> Video description: https://youtu.be/zdW1U6h-_Uw

[Problem](https://www.geeksforgeeks.org/problems/subset-xor--175953/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/zdW1U6h-_Uw/0.jpg)](https://youtu.be/zdW1U6h-_Uw)

---

**Difficulty:** Medium  
**Accuracy:** 73.09%  
**Submissions:** 7K+  
**Points:** 4  
**Average Time:** 40m

Given a positive integer n, find a subset of numbers from 1 to n (inclusive), where each number can be used at most once, such that:

1. The XOR of all elements in the subset is exactly n.
2. The size of the subset is as large as possible.
3. If multiple such subsets exist, choose the lexicographically smallest one.

**Lexicographical Order:** A subset A[] is lexicographically smaller than subset B[] if at the first index where they differ, A[i] < B[i] (based on character ASCII/Unicode values). If all elements match but one subset ends earlier, the shorter subset is considered smaller.

## Examples:

**Example 1:**
```
Input: n = 4
Output: [1, 2, 3, 4]
Explanation: We choose all the elements from 1 to 4. 
XOR: 1 ^ 2 ^ 3 ^ 4 = 4
This is the maximum possible size of the subset.
```

**Example 2:**
```
Input: n = 3
Output: [1, 2]
Explanation: 1 ^ 2 = 3
This is the smallest lexicographical answer possible with maximum size of subset i.e 2.
```

## Constraints:
- 1 ≤ n ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

## Company Tags:
Amazon | Google | Microsoft

## Topic Tags:
Bit Manipulation | XOR | Greedy | Mathematical | Arrays

## Approach:

### Problem Analysis:

**What do we need?**
- Subset from [1, 2, 3, ..., n]
- XOR of subset = n
- Maximum size
- Lexicographically smallest (prefer smaller numbers)

### Key Observations:

1. **XOR Properties:**
   - a ^ a = 0 (XOR of same number is 0)
   - a ^ 0 = a (XOR with 0 is identity)
   - XOR is commutative and associative

2. **Pattern Discovery:**
   - XOR of consecutive numbers from 1 to n has a pattern
   - Let's call XOR(1 to n) as xor_all
   - If xor_all = n, we can include all numbers [1, 2, ..., n]
   - Otherwise, we need to exclude one number

3. **Mathematical Pattern:**
```
   XOR(1 to n) follows a pattern based on n % 4:
   - n % 4 == 0: XOR = n
   - n % 4 == 1: XOR = 1
   - n % 4 == 2: XOR = n + 1
   - n % 4 == 3: XOR = 0
```

4. **Critical Insight:**
   - If XOR(1 to n) = n, answer is all numbers [1, 2, ..., n]
   - If XOR(1 to n) ≠ n, we need to remove one number
   - Which number to remove? 
     - We want: (XOR of all) ^ (removed number) = n
     - So: removed number = (XOR of all) ^ n

### Algorithm:
```
// Calculate XOR of all numbers from 1 to n
xor_all = 0
for i from 1 to n:
    xor_all ^= i

// Check if we can include all numbers
if xor_all == n:
    return [1, 2, 3, ..., n]

// Otherwise, find which number to exclude
exclude = xor_all ^ n

// Check if exclude is in range [1, n]
if 1 <= exclude <= n:
    return [1, 2, ..., n] excluding 'exclude'
else:
    // This shouldn't happen for valid inputs
    return []
```

### Optimized Algorithm Using Pattern:
```
// Calculate XOR(1 to n) using pattern
xor_all = calculateXOR(n)

if xor_all == n:
    return [1, 2, 3, ..., n]

exclude = xor_all ^ n

if 1 <= exclude <= n:
    result = []
    for i from 1 to n:
        if i != exclude:
            result.append(i)
    return result

return []

// Helper function
calculateXOR(n):
    switch n % 4:
        case 0: return n
        case 1: return 1
        case 2: return n + 1
        case 3: return 0
```

### Step-by-Step Walkthrough (Example 1):
```
Input: n = 4

Step 1: Calculate XOR(1 to 4)
1 ^ 2 ^ 3 ^ 4
= (1 ^ 2) ^ (3 ^ 4)
= 3 ^ 7
= 4

Or using pattern: 4 % 4 = 0, so XOR = 4

Step 2: Check if xor_all == n
xor_all = 4, n = 4
Yes! They are equal.

Step 3: Return all numbers
Output: [1, 2, 3, 4]
```

### Step-by-Step Walkthrough (Example 2):
```
Input: n = 3

Step 1: Calculate XOR(1 to 3)
1 ^ 2 ^ 3
= 3 ^ 3
= 0

Or using pattern: 3 % 4 = 3, so XOR = 0

Step 2: Check if xor_all == n
xor_all = 0, n = 3
No, they differ.

Step 3: Find number to exclude
exclude = xor_all ^ n = 0 ^ 3 = 3

Step 4: Verify exclude is in range
1 <= 3 <= 3? Yes

Step 5: Build result excluding 3
Output: [1, 2]

Verification: 1 ^ 2 = 3 ✓
```

### Detailed Examples:

**Example: n = 5**
```
XOR(1 to 5) = 1
Using pattern: 5 % 4 = 1, so XOR = 1

xor_all (1) != n (5)
exclude = 1 ^ 5 = 4

Result: [1, 2, 3, 5] (excluding 4)
Verification: 1 ^ 2 ^ 3 ^ 5 = 5 ✓
```

**Example: n = 6**
```
XOR(1 to 6) = 7
Using pattern: 6 % 4 = 2, so XOR = 6 + 1 = 7

xor_all (7) != n (6)
exclude = 7 ^ 6 = 1

Result: [2, 3, 4, 5, 6] (excluding 1)
Verification: 2 ^ 3 ^ 4 ^ 5 ^ 6 = 6 ✓
```

**Example: n = 7**
```
XOR(1 to 7) = 0
Using pattern: 7 % 4 = 3, so XOR = 0

xor_all (0) != n (7)
exclude = 0 ^ 7 = 7

Result: [1, 2, 3, 4, 5, 6] (excluding 7)
Verification: 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 = 7 ✓
```

**Example: n = 8**
```
XOR(1 to 8) = 8
Using pattern: 8 % 4 = 0, so XOR = 8

xor_all (8) == n (8) ✓

Result: [1, 2, 3, 4, 5, 6, 7, 8] (all numbers)
Verification: 1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 6 ^ 7 ^ 8 = 8 ✓
```

### Why This Works:

**Mathematical Proof:**
```
Given: We want subset S such that XOR(S) = n

Let xor_all = XOR(1 to n)

Case 1: xor_all == n
  Then S = [1, 2, ..., n] works directly
  Size = n (maximum possible)

Case 2: xor_all != n
  We need: XOR(S) = n where S ⊂ [1, 2, ..., n]
  
  If we exclude number k:
  XOR(all except k) = xor_all ^ k (property of XOR)
  
  We want: xor_all ^ k = n
  Therefore: k = xor_all ^ n
  
  Size = n - 1 (one less than maximum)
```

### XOR Pattern Explanation:
```
n     Binary   XOR(1 to n)   Pattern
1     0001     1             n % 4 = 1 → 1
2     0010     3             n % 4 = 2 → n+1
3     0011     0             n % 4 = 3 → 0
4     0100     4             n % 4 = 0 → n
5     0101     1             n % 4 = 1 → 1
6     0110     7             n % 4 = 2 → n+1
7     0111     0             n % 4 = 3 → 0
8     1000     8             n % 4 = 0 → n

Pattern repeats every 4 numbers!
```

### Lexicographical Order:

**Why our approach gives lexicographically smallest:**
- We always include numbers from 1 upwards
- Only exclude one number (if needed)
- Excluding the smallest possible number might give larger size
- But our approach gives maximum size by design
- Among maximum size subsets, starting from 1 gives lexicographically smallest

### Edge Cases:

1. **n = 1:**
   - XOR(1) = 1
   - Output: [1]

2. **n = 2:**
   - XOR(1 to 2) = 3
   - exclude = 3 ^ 2 = 1
   - Output: [2]

3. **Small values (n ≤ 4):**
   - Test all cases manually

4. **Large values (n = 10⁵):**
   - Must use pattern optimization
   - Can't iterate to calculate XOR

5. **Powers of 2:**
   - n = 4, 8, 16, 32, ...
   - Usually can include all numbers

### Common Mistakes:

1. **Not using XOR pattern:**
   - Calculating XOR by iteration is O(n)
   - Pattern gives O(1) calculation

2. **Wrong exclusion logic:**
   - Must exclude xor_all ^ n, not other number

3. **Not checking if excluded number is valid:**
   - exclude must be in range [1, n]

4. **Not maintaining lexicographical order:**
   - Must build result from 1 to n

5. **Forgetting edge cases:**
   - n = 1, 2 need special attention

### Optimization Notes:

**Time Optimization:**
- Use pattern instead of loop: O(1) vs O(n)
- Still need O(n) to build result array

**Space Optimization:**
- Result array requires O(n) space
- Can't optimize further (need to return array)

## Related Interview Experiences:
- Single Number (LeetCode 136)
- Single Number II (LeetCode 137)
- Maximum XOR of Two Numbers
- XOR Queries of a Subarray
- Find XOR of Numbers in Range

## Related Articles:
- XOR Properties and Applications
- Bit Manipulation Patterns
- Mathematical Number Patterns
- Greedy Algorithms
- Lexicographical Ordering