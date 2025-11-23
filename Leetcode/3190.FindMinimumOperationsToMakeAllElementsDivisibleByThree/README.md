# Find Minimum Operations to Make All Elements Divisible by Three
---

> Video description: https://youtu.be/JghQz-zqW-M

[Problem](https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/JghQz-zqW-M/0.jpg)](https://youtu.be/JghQz-zqW-M)

---

**Difficulty:** Easy  
**Topics:** Array | Math | Greedy  
**Average Time:** 15m

You are given an integer array `nums`. In one operation, you can add or subtract 1 from any element of `nums`.

Return the minimum number of operations to make all elements of `nums` divisible by 3.

## Examples:

**Example 1:**
```
Input: nums = [1,2,3,4]
Output: 3

Explanation:
All array elements can be made divisible by 3 using 3 operations:
- Subtract 1 from 1 → 0 (divisible by 3)
- Add 1 to 2 → 3 (divisible by 3)
- Subtract 1 from 4 → 3 (divisible by 3)
```

**Example 2:**
```
Input: nums = [3,6,9]
Output: 0

Explanation:
All elements are already divisible by 3, so no operations are needed.
```

## Constraints:
- 1 ≤ nums.length ≤ 50
- 1 ≤ nums[i] ≤ 50

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Google | Amazon

## Topic Tags:
Array | Math | Greedy | Modular Arithmetic

## Approach:

### Key Observations:

1. **Modular Arithmetic:**
   - For any number n, n % 3 gives remainder: 0, 1, or 2
   - If remainder is 0: already divisible by 3 (0 operations needed)
   - If remainder is 1: need to subtract 1 (1 operation)
   - If remainder is 2: need to add 1 (1 operation)

2. **Greedy Choice:**
   - Each element can be made divisible by 3 independently
   - Minimum operations for each element = remainder when remainder ≤ 1, else 1
   - Or simply: if remainder != 0, we need exactly 1 operation

3. **Optimal Strategy:**
   - For remainder 1: subtract 1 → moves to nearest multiple of 3 below
   - For remainder 2: add 1 → moves to nearest multiple of 3 above
   - Both cases require exactly 1 operation

### Algorithm:

**Simple Approach:**
```
count = 0
for each num in nums:
    remainder = num % 3
    if remainder != 0:
        count += 1
return count
```

**Alternative (More Explicit):**
```
count = 0
for each num in nums:
    remainder = num % 3
    if remainder == 1:
        count += 1  // subtract 1
    else if remainder == 2:
        count += 1  // add 1
    // remainder == 0: no operation needed
return count
```

### Step-by-Step Walkthrough (Example 1):
```
nums = [1, 2, 3, 4]

Element 1:
- 1 % 3 = 1 (remainder 1)
- Operation: 1 - 1 = 0 (divisible by 3)
- Operations needed: 1

Element 2:
- 2 % 3 = 2 (remainder 2)
- Operation: 2 + 1 = 3 (divisible by 3)
- Operations needed: 1

Element 3:
- 3 % 3 = 0 (remainder 0)
- Already divisible by 3
- Operations needed: 0

Element 4:
- 4 % 3 = 1 (remainder 1)
- Operation: 4 - 1 = 3 (divisible by 3)
- Operations needed: 1

Total operations = 1 + 1 + 0 + 1 = 3
```

### Why This Works:

1. **Distance to Nearest Multiple:**
   - Remainder 1: distance to lower multiple = 1
   - Remainder 2: distance to upper multiple = 1
   - Both require exactly 1 operation

2. **No Better Alternative:**
   - Can't make num divisible by 3 with 0 operations (unless already divisible)
   - Can't need more than 1 operation per element
   - Greedy choice is optimal

3. **Mathematical Proof:**
   - Any number n can be written as: n = 3k + r, where r ∈ {0, 1, 2}
   - If r = 0: n is divisible by 3
   - If r = 1: n - 1 = 3k (1 operation)
   - If r = 2: n + 1 = 3k + 3 = 3(k+1) (1 operation)

### Edge Cases:

1. **All elements divisible by 3:**
   - Input: [3, 6, 9, 12]
   - Output: 0

2. **Single element:**
   - Input: [1]
   - Output: 1

3. **All elements have same remainder:**
   - Input: [1, 4, 7, 10] (all have remainder 1)
   - Output: 4

4. **Maximum constraints:**
   - Input: [50, 50, ..., 50] (50 times)
   - 50 % 3 = 2, so output = 50

## Related Interview Experiences:
- Minimum Moves to Equal Array Elements
- Minimum Operations to Make Array Equal
- Make Array Elements Equal to Target
- Divisibility Problems

## Related Articles:
- Modular Arithmetic Basics
- Greedy Algorithm Applications
- Array Manipulation Problems
- Mathematical Optimization