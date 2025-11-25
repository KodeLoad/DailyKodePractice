# Smallest Integer Divisible by K
---

> Video description: https://youtu.be/eahnkqx53So

[Problem](https://leetcode.com/problems/smallest-integer-divisible-by-k/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/eahnkqx53So/0.jpg)](https://youtu.be/eahnkqx53So)

---

**Difficulty:** Medium  
**Topics:** Math | Hash Table | Modular Arithmetic  
**Average Time:** 30m

Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.

Return the length of n. If there is no such n, return -1.

**Note:** n may not fit in a 64-bit signed integer.

## Examples:

**Example 1:**
```
Input: k = 1
Output: 1
Explanation: The smallest answer is n = 1, which has length 1.
```

**Example 2:**
```
Input: k = 2
Output: -1
Explanation: There is no such positive integer n divisible by 2.
```

**Example 3:**
```
Input: k = 3
Output: 3
Explanation: The smallest answer is n = 111, which has length 3.
```

## Constraints:
- 1 ≤ k ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(k)
- **Space Complexity:** O(k) or O(1) depending on implementation

## Company Tags:
Google | Amazon | Microsoft

## Topic Tags:
Math | Hash Table | Modular Arithmetic | Number Theory | Pigeonhole Principle

## Approach:

### Problem Analysis:

**What are we looking for?**
- Numbers containing only digit 1: 1, 11, 111, 1111, 11111, ...
- Find smallest such number divisible by k
- Return its length (number of 1's)

**Key Insight:**
- We can't store these numbers directly (may exceed 64-bit)
- Use modular arithmetic: track remainders instead of actual numbers

### Key Observations:

1. **When is solution impossible?**
   - If k is even (divisible by 2) → impossible
   - If k is divisible by 5 → impossible
   - Why? Numbers with only 1's are always odd and never end in 0 or 5
   - **Exception:** k = 1 (always possible)

2. **Mathematical Property:**
   - Number with n ones = 111...1 (n times)
   - Can be built incrementally: 
     - 1 = 1
     - 11 = 1 × 10 + 1
     - 111 = 11 × 10 + 1
     - 1111 = 111 × 10 + 1

3. **Modular Arithmetic:**
   - Instead of tracking actual number, track remainder
   - Formula: `new_remainder = (prev_remainder × 10 + 1) % k`
   - If remainder becomes 0 → found answer!

4. **Pigeonhole Principle:**
   - Possible remainders: 0, 1, 2, ..., k-1 (total k values)
   - If we don't find 0 in k iterations → cycle detected → no solution
   - If solution exists, we'll find it within k iterations

### Algorithm:
```
// Quick check for impossible cases
if k % 2 == 0 or k % 5 == 0:
    return -1

remainder = 0
length = 0

for i from 1 to k:
    // Build next number: append 1
    remainder = (remainder * 10 + 1) % k
    length++
    
    // Check if divisible
    if remainder == 0:
        return length

// Cycle detected, no solution
return -1
```

### Step-by-Step Walkthrough (Example 3):
```
k = 3

Iteration 1:
- Build: 1
- remainder = (0 × 10 + 1) % 3 = 1 % 3 = 1
- 1 ≠ 0, continue
- length = 1

Iteration 2:
- Build: 11
- remainder = (1 × 10 + 1) % 3 = 11 % 3 = 2
- 2 ≠ 0, continue
- length = 2

Iteration 3:
- Build: 111
- remainder = (2 × 10 + 1) % 3 = 21 % 3 = 0
- 0 == 0, found!
- length = 3

Output: 3
```

### Why Modular Arithmetic Works:

**Building numbers incrementally:**
```
Number:     1    11    111    1111
Actual:     1    11    111    1111
Modulo k:   1%k  11%k  111%k  1111%k

Instead of storing 1111, we track: 1111 % k

Formula: next = (current × 10 + 1) % k
```

**Example with k = 7:**
```
n = 1:    1 % 7 = 1
n = 11:   11 % 7 = 4
n = 111:  111 % 7 = 6
n = 1111: 1111 % 7 = 5
n = 11111: 11111 % 7 = 3
n = 111111: 111111 % 7 = 0 ✓

Answer: 6
```

### Impossible Cases:

**When k is even (k = 2, 4, 6, 8, ...):**
```
Numbers with only 1's: 1, 11, 111, 1111, ...
All are odd (always end in 1)
Can never be divisible by even number
```

**When k is divisible by 5 (k = 5, 10, 15, 20, ...):**
```
For divisibility by 5, number must end in 0 or 5
Numbers with only 1's always end in 1
Impossible to be divisible by 5
```

**Exception: k = 1**
```
Every number is divisible by 1
Answer: 1 (length = 1)
```

### Detailed Examples:

**Example: k = 1**
```
Check: 1 % 1 = 0
Answer: 1
```

**Example: k = 2**
```
Quick check: 2 is even
Answer: -1 (impossible)
```

**Example: k = 7**
```
1 % 7 = 1
11 % 7 = 4
111 % 7 = 6
1111 % 7 = 5
11111 % 7 = 3
111111 % 7 = 0 ✓
Answer: 6
```

**Example: k = 13**
```
After iterations:
1, 11, 111, 1111, 11111, 111111 (all not divisible)
Continue until remainder = 0
Answer: varies (compute using algorithm)
```

### Edge Cases:

1. **k = 1:**
   - Input: 1
   - Output: 1

2. **Even k:**
   - Input: 2, 4, 6, 8, ...
   - Output: -1

3. **k divisible by 5:**
   - Input: 5, 10, 15, 20, ...
   - Output: -1

4. **Prime k (not 2 or 5):**
   - Always has solution
   - Example: k = 7 → output = 6

5. **Maximum k:**
   - Input: k = 100000
   - Must handle efficiently

### Optimization Notes:

**Early termination:**
```
if k % 2 == 0 or k % 5 == 0:
    return -1  // O(1) check
```

**Cycle detection:**
```
// At most k different remainders possible
// If no solution in k iterations, no solution exists
for i in range(k):
    // ... check remainder
```

**Why k iterations max?**
- By Pigeonhole Principle
- k possible remainders (0 to k-1)
- If we see k+1 remainders, one must repeat
- Repeat means cycle → no solution

### Common Mistakes:

1. **Trying to build actual number:**
   - Will overflow for large k
   - Use modular arithmetic instead

2. **Not handling impossible cases:**
   - Missing check for even k or k divisible by 5
   - Leads to infinite loop or TLE

3. **Incorrect remainder calculation:**
   - Formula must be: `(remainder × 10 + 1) % k`
   - Not: `(remainder + 1) % k`

4. **Not limiting iterations:**
   - Could loop forever without cycle detection
   - Must stop after k iterations

## Related Interview Experiences:
- Repeated String Match
- Super Palindromes
- Largest Multiple of Three
- Numbers With Repeated Digits
- Divisibility Problems

## Related Articles:
- Modular Arithmetic Applications
- Pigeonhole Principle
- Number Theory Basics
- Cycle Detection in Sequences