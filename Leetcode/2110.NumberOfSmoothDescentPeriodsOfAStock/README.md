# Number of Smooth Descent Periods of a Stock
---

> Video description: https://youtu.be/7nxCvVceP3g

[Problem](https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/7nxCvVceP3g/0.jpg)](https://youtu.be/7nxCvVceP3g)

---

**Difficulty:** Medium  
**Acceptance Rate:** 66.6%  
**Submissions:** 165.5K+  
**Accepted:** 110.3K+  
**Topics:** Array | Dynamic Programming | Math  
**Average Time:** 30m

You are given an integer array `prices` representing the daily price history of a stock, where `prices[i]` is the stock price on the i<sup>th</sup> day.

A **smooth descent period** of a stock consists of one or more contiguous days such that the price on each day is lower than the price on the preceding day by **exactly 1**. The first day of the period is exempted from this rule.

Return the number of smooth descent periods.

## Examples:

**Example 1:**
```
Input: prices = [3,2,1,4]
Output: 7

Explanation: There are 7 smooth descent periods:
[3], [2], [1], [4], [3,2], [2,1], and [3,2,1]

Breakdown:
- Single day periods: [3], [2], [1], [4] = 4 periods
- Two day periods: [3,2], [2,1] = 2 periods
- Three day periods: [3,2,1] = 1 period
Total = 7

Note that a period with one day is a smooth descent period by the definition.
```

**Example 2:**
```
Input: prices = [8,6,7,7]
Output: 4

Explanation: There are 4 smooth descent periods: [8], [6], [7], and [7]

Note that [8,6] is NOT a smooth descent period as 8 - 6 ≠ 1.

Breakdown:
- Single day periods only: [8], [6], [7], [7] = 4 periods
- No multi-day periods (no consecutive descent by exactly 1)
Total = 4
```

**Example 3:**
```
Input: prices = [1]
Output: 1

Explanation: There is 1 smooth descent period: [1]
```

## Constraints:
- 1 ≤ prices.length ≤ 10⁵
- 1 ≤ prices[i] ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Google | Bloomberg

## Topic Tags:
Array | Dynamic Programming | Math | Sliding Window

## Approach:

### Problem Understanding:

**What is a smooth descent period?**
- Consecutive days where each day's price is exactly 1 less than previous day
- Single day always counts as smooth descent period
- Example: [5, 4, 3] is smooth descent (5→4 decrease by 1, 4→3 decrease by 1)
- Example: [5, 3] is NOT smooth descent (5→3 decrease by 2, not 1)

**What are we counting?**
- All possible contiguous subarrays that form smooth descent periods
- Includes single elements (always valid)

### Brute Force Analysis (TLE):

**Naive Approach:**
```
count = 0
for i from 0 to n-1:
    for j from i to n-1:
        if subarray[i..j] is smooth descent:
            count++
return count
```

**Complexity:**
- Time: O(n³) - checking each subarray
- Too slow for n = 10⁵

### Key Observations:

1. **Single elements always count:**
   - Every individual day is a smooth descent period
   - Base count = n (array length)

2. **Consecutive descent pattern:**
   - If we have k consecutive days forming smooth descent
   - Number of subarrays = k × (k + 1) / 2
   - This is the formula for counting all subarrays

3. **Mathematical insight:**
```
   For array [3, 2, 1] (length 3):
   - Subarrays of length 1: [3], [2], [1] = 3
   - Subarrays of length 2: [3,2], [2,1] = 2
   - Subarrays of length 3: [3,2,1] = 1
   Total = 3 + 2 + 1 = 6 = 3 × 4 / 2
```

4. **Dynamic counting:**
   - Track current consecutive descent length
   - When descent breaks, calculate contribution and reset
   - Add contributions as we go

### Optimal Algorithm:

**Approach 1: Count consecutive lengths, then calculate**
```
result = 0
length = 1  // Current smooth descent period length

for i from 1 to n-1:
    if prices[i] == prices[i-1] - 1:
        length++  // Extend current descent
    else:
        // Add contribution of current descent period
        result += length * (length + 1) / 2
        length = 1  // Reset for new period

// Don't forget last period
result += length * (length + 1) / 2

return result
```

**Approach 2: Dynamic increment (More intuitive)**
```
result = 0
dp = 1  // Number of smooth descent periods ending at current position

for i from 0 to n-1:
    if i > 0 and prices[i] == prices[i-1] - 1:
        dp++  // All previous periods + current element form new periods
    else:
        dp = 1  // Only single element period
    
    result += dp

return result
```

**Complexity:**
- Time: O(n) - single pass
- Space: O(1) - only using variables

### Step-by-Step Walkthrough (Example 1):

**Using Approach 2 (Dynamic Increment):**
```
Input: prices = [3, 2, 1, 4]

Initialize: result = 0, dp = 1

Index 0 (price = 3):
- First element, dp = 1
- result += dp = 0 + 1 = 1
- Periods: [3]

Index 1 (price = 2):
- prices[1] = 2, prices[0] = 3
- 2 == 3 - 1? Yes! ✓
- dp++ = 2 (we can form [2] and [3,2])
- result += dp = 1 + 2 = 3
- Periods so far: [3], [2], [3,2]

Index 2 (price = 1):
- prices[2] = 1, prices[1] = 2
- 1 == 2 - 1? Yes! ✓
- dp++ = 3 (we can form [1], [2,1], [3,2,1])
- result += dp = 3 + 3 = 6
- Periods so far: [3], [2], [1], [3,2], [2,1], [3,2,1]

Index 3 (price = 4):
- prices[3] = 4, prices[2] = 1
- 4 == 1 - 1? No! ✗
- Descent breaks, dp = 1 (reset)
- result += dp = 6 + 1 = 7
- Final periods: [3], [2], [1], [4], [3,2], [2,1], [3,2,1]

Output: 7 ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: prices = [8, 6, 7, 7]

Initialize: result = 0, dp = 1

Index 0 (price = 8):
- First element, dp = 1
- result = 1
- Periods: [8]

Index 1 (price = 6):
- 6 == 8 - 1? No (8 - 6 = 2) ✗
- dp = 1 (reset)
- result = 1 + 1 = 2
- Periods: [8], [6]

Index 2 (price = 7):
- 7 == 6 - 1? No (6 - 7 = -1) ✗
- dp = 1 (reset)
- result = 2 + 1 = 3
- Periods: [8], [6], [7]

Index 3 (price = 7):
- 7 == 7 - 1? No (7 - 7 = 0) ✗
- dp = 1 (reset)
- result = 3 + 1 = 4
- Periods: [8], [6], [7], [7]

Output: 4 ✓
```

### Mathematical Formula Explanation:

**For k consecutive descent days:**
```
Number of subarrays = k + (k-1) + (k-2) + ... + 2 + 1
                    = k × (k + 1) / 2

Example with k = 4:
Length 1 subarrays: 4
Length 2 subarrays: 3
Length 3 subarrays: 2
Length 4 subarrays: 1
Total = 4 + 3 + 2 + 1 = 10 = 4 × 5 / 2 ✓
```

**Why dp increments by 1:**
```
At position i, if descent continues:
- All periods ending at i-1 can be extended by including i
- Plus one new single-element period [i]
- Therefore: dp[i] = dp[i-1] + 1
```

### Detailed Example (Longer Sequence):
```
Input: prices = [10, 9, 8, 7, 5]

Index 0: [10] → dp = 1, result = 1
Index 1: [9] (10→9 ✓) → dp = 2, result = 3
  Periods: [10], [9], [10,9]

Index 2: [8] (9→8 ✓) → dp = 3, result = 6
  New periods: [8], [9,8], [10,9,8]
  
Index 3: [7] (8→7 ✓) → dp = 4, result = 10
  New periods: [7], [8,7], [9,8,7], [10,9,8,7]

Index 4: [5] (7→5 ✗, diff = 2) → dp = 1, result = 11
  Descent breaks! New period: [5]

Total periods:
- From [10,9,8,7]: 4×5/2 = 10 periods
- From [5]: 1 period
Total = 11
```

### Edge Cases:

1. **Single element:**
   - Input: [5]
   - Output: 1
   - Only one period

2. **All descending by 1:**
   - Input: [5, 4, 3, 2, 1]
   - Output: 5×6/2 = 15 periods

3. **No consecutive descent:**
   - Input: [1, 3, 5, 7]
   - Output: 4 (only single elements)

4. **Repeated values:**
   - Input: [3, 3, 3]
   - Output: 3 (no descent, only singles)

5. **Alternating pattern:**
   - Input: [5, 4, 6, 5, 7]
   - Descents at: (5,4), (6,5)
   - Output: 2 + 2 + 1 = 5

### Why dp Approach Works:

**Intuition:**
```
dp[i] = number of smooth descent periods ending at position i

If descent continues (prices[i] = prices[i-1] - 1):
  - All periods ending at i-1 can extend to i
  - Plus new single-element period [i]
  - dp[i] = dp[i-1] + 1

If descent breaks:
  - Start new sequence
  - dp[i] = 1 (only single element)

Total count = sum of all dp[i]
```

**Example visualization:**
```
prices = [5, 4, 3, 6]

Position 0 (5): dp = 1
  Periods ending here: [5]

Position 1 (4): dp = 2
  Periods ending here: [4], [5,4]

Position 2 (3): dp = 3
  Periods ending here: [3], [4,3], [5,4,3]

Position 3 (6): dp = 1 (reset)
  Periods ending here: [6]

Total = 1 + 2 + 3 + 1 = 7
```

### Common Mistakes:

1. **Checking wrong condition:**
```
   Wrong: prices[i] < prices[i-1]
   Right: prices[i] == prices[i-1] - 1
```

2. **Forgetting single elements:**
   - Every element forms at least one period
   - Don't skip in counting

3. **Off-by-one in formula:**
```
   Wrong: k * (k - 1) / 2
   Right: k * (k + 1) / 2
```

4. **Not resetting dp:**
   - When descent breaks, must reset dp to 1
   - Not 0 (current element still forms single period)

5. **Integer overflow:**
   - For very long sequences, use long
   - n = 10⁵, max result ≈ 5×10⁹

### Optimization Notes:

**Time Complexity: O(n)**
- Single pass through array
- Constant work per element

**Space Complexity: O(1)**
- Only using two variables (result, dp)
- No extra data structures

**Why this is optimal:**
- Must examine each element at least once: Ω(n)
- Our solution: O(n)
- Cannot do better than linear time

## Related Interview Experiences:
- Longest Continuous Increasing Subsequence
- Arithmetic Slices
- Count Subarrays With Fixed Bounds
- Number of Sub-arrays of Size K
- Subarray Product Less Than K

## Related Articles:
- Dynamic Programming Patterns
- Counting Subarrays
- Arithmetic Sequences
- Sliding Window Techniques