# Binary Prefix Divisible By 5
---

> Video description: https://youtu.be/99SKqrPM-kg

[Problem](https://leetcode.com/problems/binary-prefix-divisible-by-5/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/99SKqrPM-kg/0.jpg)](https://youtu.be/99SKqrPM-kg)

---

**Difficulty:** Easy  
**Acceptance Rate:** 53.0%  
**Submissions:** 295.7K+  
**Accepted:** 156.7K+  
**Topics:** Array | Math | Bit Manipulation  
**Average Time:** 20m

You are given a binary array `nums` (0-indexed).

We define x<sub>i</sub> as the number whose binary representation is the subarray `nums[0..i]` (from most-significant-bit to least-significant-bit).

For example, if `nums = [1,0,1]`, then x<sub>0</sub> = 1, x<sub>1</sub> = 2, and x<sub>2</sub> = 5.

Return an array of booleans `answer` where `answer[i]` is `true` if x<sub>i</sub> is divisible by 5.

## Examples:

**Example 1:**
```
Input: nums = [0,1,1]
Output: [true,false,false]

Explanation: 
The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.
Only the first number is divisible by 5, so answer[0] is true.

Step-by-step:
- x₀ = 0 (binary: 0) → 0 % 5 = 0 → true
- x₁ = 1 (binary: 01) → 1 % 5 = 1 → false
- x₂ = 3 (binary: 011) → 3 % 5 = 3 → false
```

**Example 2:**
```
Input: nums = [1,1,1]
Output: [false,false,false]

Explanation:
- x₀ = 1 (binary: 1) → 1 % 5 = 1 → false
- x₁ = 3 (binary: 11) → 3 % 5 = 3 → false
- x₂ = 7 (binary: 111) → 7 % 5 = 2 → false
```

## Constraints:
- 1 ≤ nums.length ≤ 10⁵
- nums[i] is either 0 or 1

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) excluding output array

## Company Tags:
Google | Amazon | Microsoft

## Topic Tags:
Array | Math | Bit Manipulation | Modular Arithmetic

## Approach:

### Problem Analysis:

**Binary to Decimal Conversion:**
- When reading binary from left to right: `new_value = (old_value × 2) + current_bit`
- Example: [1,0,1]
  - After bit 1: 1
  - After bit 0: 1×2 + 0 = 2
  - After bit 1: 2×2 + 1 = 5

### Key Observations:

1. **Overflow Problem:**
   - Array can be up to 10⁵ elements
   - Direct binary to decimal conversion can cause integer overflow
   - Numbers can become extremely large

2. **Modular Arithmetic Property:**
   - We only care if number is divisible by 5 (i.e., `num % 5 == 0`)
   - Property: `(a × b + c) % m = ((a % m) × b + c) % m`
   - We can keep tracking remainder instead of actual number

3. **Solution Insight:**
   - Instead of storing full number, store only `current_value % 5`
   - Formula: `current_remainder = ((previous_remainder × 2) + current_bit) % 5`
   - Check if `current_remainder == 0` for divisibility

### Algorithm:
```
result = []
current_value = 0

for each bit in nums:
    // Build number incrementally with modulo
    current_value = (current_value * 2 + bit) % 5
    
    // Check if divisible by 5
    result.append(current_value == 0)

return result
```

### Step-by-Step Walkthrough (Example 1):
```
nums = [0, 1, 1]

Initial: current_value = 0

Iteration 1 (bit = 0):
- current_value = (0 * 2 + 0) % 5 = 0 % 5 = 0
- Is divisible? 0 == 0 → true
- result = [true]

Iteration 2 (bit = 1):
- current_value = (0 * 2 + 1) % 5 = 1 % 5 = 1
- Is divisible? 1 == 0 → false
- result = [true, false]

Iteration 3 (bit = 1):
- current_value = (1 * 2 + 1) % 5 = 3 % 5 = 3
- Is divisible? 3 == 0 → false
- result = [true, false, false]

Output: [true, false, false]
```

### Why Modular Arithmetic Works:

**Mathematical Proof:**
```
Given: x_i = x_{i-1} * 2 + nums[i]

We want: x_i % 5

Using modular property:
x_i % 5 = (x_{i-1} * 2 + nums[i]) % 5
        = ((x_{i-1} % 5) * 2 + nums[i]) % 5

So we only need to track x_i % 5, not the full value!
```

**Example with larger numbers:**
```
nums = [1,0,1,0,1] (binary: 10101 = 21 in decimal)

Without modulo tracking:
1 → 2 → 5 → 10 → 21

With modulo tracking (% 5):
1 → 2 → 0 → 0 → 1

Both give same divisibility results!
```

### Edge Cases:

1. **Single element:**
   - Input: [0] → Output: [true]
   - Input: [1] → Output: [false]

2. **All zeros:**
   - Input: [0,0,0,0] → Output: [true,true,true,true]
   - Every prefix is 0, divisible by 5

3. **All ones:**
   - Input: [1,1,1,1] (1, 3, 7, 15)
   - Output: [false,false,false,false]

4. **Large array:**
   - Input: 10⁵ elements
   - Modular arithmetic prevents overflow

### Common Mistakes:

1. **Converting entire binary to decimal:**
   - Causes overflow for large arrays
   - Unnecessary computation

2. **Not using modular arithmetic:**
   - Will fail for large inputs
   - Integer overflow errors

3. **Forgetting left-to-right processing:**
   - Binary is read MSB to LSB
   - Formula: `value = value * 2 + bit` (not `value + bit * 2`)

### Optimization Notes:

- **Space:** O(1) auxiliary space (only tracking one integer)
- **Time:** O(n) single pass through array
- **No conversion needed:** Never build actual decimal number
- **Overflow-proof:** Modulo operation keeps value bounded

## Related Interview Experiences:
- Binary Number to Integer Conversion
- Modular Arithmetic Problems
- String to Integer (atoi)
- Add Binary
- Binary Addition

## Related Articles:
- Modular Arithmetic Properties
- Binary Number Systems
- Overflow Prevention Techniques
- Bit Manipulation Fundamentals