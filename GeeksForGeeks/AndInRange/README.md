# AND In Range
---

> Video description: https://youtu.be/7thv1dZQRmU

[Problem](https://www.geeksforgeeks.org/problems/and-operation5726/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/7thv1dZQRmU/0.jpg)](https://youtu.be/7thv1dZQRmU)

---

**Difficulty:** Medium  
**Accuracy:** 48.03%  
**Submissions:** 10K+  
**Points:** 4  
**Average Time:** 35m

You are given two integers l and r. Find the result after applying the series of Bitwise AND ( & ) operation on every natural number between the range l to r (including both).

## Examples:

**Example 1:**
```
Input: l = 8, r = 13
Output: 8

Explanation: 
8 AND 9 AND 10 AND 11 AND 12 AND 13 = 8

Binary representation:
8  = 1000
9  = 1001
10 = 1010
11 = 1011
12 = 1100
13 = 1101

Result = 1000 (8 in decimal)
```

**Example 2:**
```
Input: l = 2, r = 3
Output: 2

Explanation: 
2 AND 3 = 2

Binary representation:
2 = 10
3 = 11

Result = 10 (2 in decimal)
```

## Constraints:
- 1 ≤ l ≤ r ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(log(max(l, r)))
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Google | Microsoft

## Topic Tags:
Bit Manipulation | Mathematics | Binary Operations

## Approach:

### Brute Force Analysis (TLE):

**Naive Approach:**
```
result = l
for i in range(l+1, r+1):
    result = result & i
return result
```

**Why this fails:**
- Time Complexity: O(r - l) which can be up to 10⁹
- Will cause TLE for large ranges

### Key Observations:

1. **AND Operation Property:**
   - If any bit position differs between numbers, AND will make it 0
   - Only common prefix bits remain 1 in the result

2. **Binary Pattern:**
   - When we AND consecutive numbers, the result contains only the common prefix
   - Example: 8 to 13
```
     8  = 1000
     9  = 1001
     10 = 1010
     11 = 1011
     12 = 1100
     13 = 1101
     Common prefix = 1000 (only first bit is common)
```

3. **Critical Insight:**
   - Result = Common prefix of l and r in binary
   - All bits that differ between l and r become 0
   - Find the longest common prefix of l and r

4. **Why does this work?**
   - In any range [l, r], there will be numbers that differ in lower bits
   - When we AND them, differing bits become 0
   - Only the stable prefix bits remain

### Algorithm 1: Bit Shifting Approach

**Find Common Prefix by Right Shifting:**
```
shift = 0

// Right shift both until they become equal
while l != r:
    l = l >> 1
    r = r >> 1
    shift++

// Left shift back to get result
result = l << shift
return result
```

**How it works:**
- Keep removing rightmost bits until l == r
- This gives us the common prefix
- Shift back to restore position

### Algorithm 2: Bit Manipulation Approach

**Clear Rightmost Bits of r:**
```
while r > l:
    r = r & (r - 1)  // Clear rightmost set bit
return r
```

**How it works:**
- `r & (r - 1)` clears the rightmost set bit
- Keep clearing until r ≤ l
- Result is the common prefix

### Step-by-Step Walkthrough (Example 1):

**Method 1 (Bit Shifting):**
```
l = 8 (1000), r = 13 (1101)

Iteration 1:
l = 8 >> 1 = 4 (100)
r = 13 >> 1 = 6 (110)
shift = 1

Iteration 2:
l = 4 >> 1 = 2 (10)
r = 6 >> 1 = 3 (11)
shift = 2

Iteration 3:
l = 2 >> 1 = 1 (1)
r = 3 >> 1 = 1 (1)
shift = 3

Now l == r = 1

Result = 1 << 3 = 8 (1000)
```

**Method 2 (Clear Bits):**
```
l = 8 (1000), r = 13 (1101)

Iteration 1:
r & (r-1) = 13 & 12 = 1101 & 1100 = 1100 (12)
r = 12 > l = 8, continue

Iteration 2:
r & (r-1) = 12 & 11 = 1100 & 1011 = 1000 (8)
r = 8 == l = 8, stop

Result = 8
```

### Detailed Example (l = 5, r = 7):
```
Binary representation:
5 = 101
6 = 110
7 = 111

AND operation:
  101
& 110
-----
  100

& 111
-----
  100 (4 in decimal)

Common prefix approach:
5 = 101
7 = 111
Common prefix = 1 (after removing differing bits)
But we need to keep the position
Result = 100 (4)
```

### Visual Understanding:
```
Range [8, 13]:

8  = 1 0 0 0
9  = 1 0 0 1
10 = 1 0 1 0
11 = 1 0 1 1
12 = 1 1 0 0
13 = 1 1 0 1

Position: ↓ ↓ ↓ ↓
Common:   1 × × ×

Only first bit is common to all
Result = 1000 = 8
```

### Why Common Prefix Works:

**Mathematical Proof:**
```
For any bit position i:
- If l and r differ at position i
- Then there exists a number n in [l, r] where bit i flips
- When we AND all numbers, this bit becomes 0

Only bits where l and r are identical throughout the range stay 1
These are exactly the common prefix bits
```

### Edge Cases:

1. **l == r:**
   - Input: l = 5, r = 5
   - Output: 5 (single number)

2. **Consecutive numbers:**
   - Input: l = 10, r = 11
   - 10 = 1010, 11 = 1011
   - Output: 1010 = 10

3. **Power of 2 range:**
   - Input: l = 8, r = 15
   - 8 = 1000, 15 = 1111
   - Output: 1000 = 8

4. **Large values:**
   - Input: l = 10⁹ - 100, r = 10⁹
   - Must handle efficiently

5. **Different bit lengths:**
   - Input: l = 7, r = 8
   - 7 = 111, 8 = 1000
   - Output: 0 (no common prefix)

### Complexity Analysis:

**Time Complexity: O(log(max(l, r)))**
- Maximum 32 iterations (for 32-bit integers)
- Each iteration does constant work

**Space Complexity: O(1)**
- Only using constant extra space

### Common Patterns:

**Pattern 1: Same MSB**
```
If l and r have same most significant bits:
Result will have those bits set
```

**Pattern 2: Different bit length**
```
If l has fewer bits than r:
Result is likely 0 or very small
Example: l = 7 (111), r = 8 (1000) → 0
```

**Pattern 3: Close numbers**
```
If r - l is small:
More common prefix bits remain
Example: l = 10, r = 11 → 10
```

### Common Mistakes:

1. **Iterating through range:**
   - O(r - l) approach will TLE
   - Must use bit manipulation

2. **Not handling large numbers:**
   - l and r can be up to 10⁹
   - Must use efficient algorithm

3. **Incorrect bit operations:**
   - Using OR instead of AND
   - Not preserving bit positions

4. **Off-by-one errors:**
   - Forgetting to include l or r
   - Range is inclusive [l, r]

## Related Interview Experiences:
- Bitwise AND of Numbers Range (LeetCode 201)
- Range Bitwise OR
- Count of Range Sum
- Bitwise Operations in Range

## Related Articles:
- Bit Manipulation Techniques
- Binary Number System
- Common Prefix Finding
- Bitwise Operations Optimization