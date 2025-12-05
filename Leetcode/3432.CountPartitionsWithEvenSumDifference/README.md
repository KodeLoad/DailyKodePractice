# Count Partitions with Even Sum Difference
---

> Video description: https://youtu.be/52DM8n8kZ_I

[Problem](https://leetcode.com/problems/count-partitions-with-even-sum-difference/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/52DM8n8kZ_I/0.jpg)](https://youtu.be/52DM8n8kZ_I)

---

**Difficulty:** Easy  
**Topics:** Array | Prefix Sum | Math  
**Average Time:** 20m

You are given an integer array `nums` of length n.

A partition is defined as an index i where 0 ≤ i < n - 1, splitting the array into two non-empty subarrays such that:
- Left subarray contains indices [0, i].
- Right subarray contains indices [i + 1, n - 1].

Return the number of partitions where the difference between the sum of the left and right subarrays is even.

## Examples:

**Example 1:**
```
Input: nums = [10,10,3,7,6]
Output: 4

Explanation:
The 4 partitions are:
1. [10] | [10,3,7,6] → sum diff = 10 - 26 = -16 (even)
2. [10,10] | [3,7,6] → sum diff = 20 - 16 = 4 (even)
3. [10,10,3] | [7,6] → sum diff = 23 - 13 = 10 (even)
4. [10,10,3,7] | [6] → sum diff = 30 - 6 = 24 (even)
```

**Example 2:**
```
Input: nums = [1,2,2]
Output: 0

Explanation:
Possible partitions:
1. [1] | [2,2] → sum diff = 1 - 4 = -3 (odd)
2. [1,2] | [2] → sum diff = 3 - 2 = 1 (odd)

No partition results in an even sum difference.
```

**Example 3:**
```
Input: nums = [2,4,6,8]
Output: 3

Explanation:
All possible partitions:
1. [2] | [4,6,8] → sum diff = 2 - 18 = -16 (even)
2. [2,4] | [6,8] → sum diff = 6 - 14 = -8 (even)
3. [2,4,6] | [8] → sum diff = 12 - 8 = 4 (even)

All partitions result in an even sum difference.
```

## Constraints:
- 2 ≤ n == nums.length ≤ 100
- 1 ≤ nums[i] ≤ 100

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Google | Microsoft

## Topic Tags:
Array | Prefix Sum | Math | Parity

## Approach:

### Brute Force Analysis:

**Naive Approach:**
```
count = 0
for i from 0 to n-2:
    left_sum = sum(nums[0..i])
    right_sum = sum(nums[i+1..n-1])
    if (left_sum - right_sum) % 2 == 0:
        count++
return count
```

**Complexity:**
- Time: O(n²) - for each partition, calculate both sums
- Can be optimized!

### Key Observations:

1. **Even/Odd Properties:**
   - Even - Even = Even
   - Odd - Odd = Even
   - Even - Odd = Odd
   - Odd - Even = Odd

2. **Critical Insight:**
   - Difference is even if and only if both sums have same parity
   - left_sum and right_sum must both be even OR both be odd

3. **Mathematical Property:**
   - left_sum + right_sum = total_sum (constant)
   - If left_sum is even → right_sum = total_sum - left_sum
   - right_sum has same parity as left_sum if total_sum is even

4. **Simplified Condition:**
   - If total_sum is even:
     - left_sum even → right_sum even → difference even ✓
     - left_sum odd → right_sum odd → difference even ✓
     - ALL partitions give even difference!
   
   - If total_sum is odd:
     - left_sum even → right_sum odd → difference odd ✗
     - left_sum odd → right_sum even → difference odd ✗
     - NO partition gives even difference!

### Optimal Algorithm:
```
// Calculate total sum
total_sum = sum of all elements in nums

// Check parity of total sum
if total_sum is even:
    return n - 1  // All possible partitions
else:
    return 0      // No valid partitions
```

**Complexity:**
- Time: O(n) - single pass to calculate sum
- Space: O(1) - only storing sum

### Mathematical Proof:

**Given:**
- left_sum = sum of nums[0..i]
- right_sum = sum of nums[i+1..n-1]
- total_sum = left_sum + right_sum

**Want:** (left_sum - right_sum) is even

**Analysis:**
```
left_sum - right_sum = even
⟺ left_sum and right_sum have same parity

Since: right_sum = total_sum - left_sum

Case 1: total_sum is even
  - If left_sum is even → right_sum = even - even = even ✓
  - If left_sum is odd → right_sum = even - odd = odd ✓
  - Same parity always! Answer = n - 1

Case 2: total_sum is odd
  - If left_sum is even → right_sum = odd - even = odd ✗
  - If left_sum is odd → right_sum = odd - odd = even ✗
  - Different parity always! Answer = 0
```

### Step-by-Step Walkthrough (Example 1):
```
nums = [10,10,3,7,6]

Step 1: Calculate total sum
total_sum = 10 + 10 + 3 + 7 + 6 = 36

Step 2: Check if even
36 % 2 = 0 → Even!

Step 3: Count partitions
All partitions will have even difference
Number of partitions = n - 1 = 5 - 1 = 4

Output: 4 ✓
```

### Step-by-Step Walkthrough (Example 2):
```
nums = [1,2,2]

Step 1: Calculate total sum
total_sum = 1 + 2 + 2 = 5

Step 2: Check if even
5 % 2 = 1 → Odd!

Step 3: Count partitions
No partition will have even difference
Number of partitions = 0

Output: 0 ✓
```

### Step-by-Step Walkthrough (Example 3):
```
nums = [2,4,6,8]

Step 1: Calculate total sum
total_sum = 2 + 4 + 6 + 8 = 20

Step 2: Check if even
20 % 2 = 0 → Even!

Step 3: Count partitions
All partitions will have even difference
Number of partitions = n - 1 = 4 - 1 = 3

Output: 3 ✓
```

### Verification (Example 1 - Long Way):
```
nums = [10,10,3,7,6], total = 36 (even)

Partition 1: [10] | [10,3,7,6]
  left = 10 (even), right = 26 (even)
  diff = -16 (even) ✓

Partition 2: [10,10] | [3,7,6]
  left = 20 (even), right = 16 (even)
  diff = 4 (even) ✓

Partition 3: [10,10,3] | [7,6]
  left = 23 (odd), right = 13 (odd)
  diff = 10 (even) ✓

Partition 4: [10,10,3,7] | [6]
  left = 30 (even), right = 6 (even)
  diff = 24 (even) ✓

All 4 partitions valid! ✓
```

### Why This Works:

**Parity Mathematics:**
```
Difference = left_sum - right_sum

For difference to be even:
→ left_sum and right_sum must have same parity

Since: left_sum + right_sum = total_sum (constant)

If total_sum is even:
  → left_sum and right_sum always have same parity
  → Difference always even
  → Answer = n - 1

If total_sum is odd:
  → left_sum and right_sum always have different parity
  → Difference always odd
  → Answer = 0
```

### Visual Understanding:
```
total_sum = EVEN (e.g., 36)

Possible combinations:
left=even, right=even → diff=even ✓
left=odd,  right=odd  → diff=even ✓

All n-1 partitions work!
```
```
total_sum = ODD (e.g., 5)

Possible combinations:
left=even, right=odd → diff=odd ✗
left=odd, right=even → diff=odd ✗

No partition works!
```

### Edge Cases:

1. **Minimum length array:**
   - Input: [1, 1], sum = 2 (even)
   - Output: 1 (one partition possible)

2. **All even numbers:**
   - Input: [2, 4, 6, 8], sum = 20 (even)
   - Output: 3

3. **All odd numbers:**
   - Input: [1, 3, 5, 7], sum = 16 (even)
   - Output: 3

4. **Mixed parity, odd sum:**
   - Input: [1, 2, 3], sum = 6 (even)
   - Output: 2

5. **Mixed parity, even sum:**
   - Input: [1, 2, 2], sum = 5 (odd)
   - Output: 0

### Common Patterns:

**Pattern 1: Even total sum**
- Input has even number of odd elements
- OR all elements are even
- Result: n - 1

**Pattern 2: Odd total sum**
- Input has odd number of odd elements
- Result: 0

### Common Mistakes:

1. **Computing all partitions:**
   - O(n²) solution when O(n) is possible
   - Not recognizing the pattern

2. **Not understanding parity:**
   - Missing the even/odd sum relationship
   - Computing differences unnecessarily

3. **Forgetting constraint:**
   - Partition count is n - 1 (not n)
   - Both subarrays must be non-empty

4. **Off-by-one errors:**
   - Counting partitions incorrectly
   - Valid partitions: 0 to n-2 (total n-1)

### Optimization Notes:

**Key Optimization:**
- Don't compute individual partition sums
- Just check total sum parity
- O(1) decision after O(n) sum calculation

**Why This is Optimal:**
- Must read all elements at least once: Ω(n)
- Our solution: O(n) for sum + O(1) for decision
- Cannot do better than O(n)

## Related Interview Experiences:
- Partition Array for Maximum Sum
- Partition Equal Subset Sum
- Split Array into Consecutive Subsequences
- Continuous Subarray Sum
- Subarray Sum Equals K

## Related Articles:
- Prefix Sum Techniques
- Parity in Mathematics
- Array Partitioning Problems
- Even-Odd Number Properties
