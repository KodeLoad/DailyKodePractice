# Two Sum
---

> Video description: https://youtu.be/fHLTC5n-TeY

[Problem](https://leetcode.com/problems/two-sum/) | [Java Solution](./java_solution) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/fHLTC5n-TeY/0.jpg)](https://youtu.be/fHLTC5n-TeY)

---

**Difficulty:** Easy  
**Acceptance Rate:** 56.6%  
**Submissions:** 34.7M+  
**Accepted:** 19.6M+  
**Topics:** Array | Hash Table  
**Average Time:** 20m

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.

You may assume that each input would have **exactly one solution**, and you may not use the same element twice.

You can return the answer in any order.

**Follow-up:** Can you come up with an algorithm that is less than O(n²) time complexity?

## Examples:

**Example 1:**
```
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
```

**Example 2:**
```
Input: nums = [3,2,4], target = 6
Output: [1,2]
Explanation: nums[1] + nums[2] == 6, so we return [1, 2].
```

**Example 3:**
```
Input: nums = [3,3], target = 6
Output: [0,1]
Explanation: nums[0] + nums[1] == 6, so we return [0, 1].
```

## Constraints:
- 2 ≤ nums.length ≤ 10⁴
- -10⁹ ≤ nums[i] ≤ 10⁹
- -10⁹ ≤ target ≤ 10⁹
- Only one valid answer exists

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

## Company Tags:
Amazon | Google | Microsoft | Facebook | Apple | Adobe | Bloomberg | Uber | Oracle | Yahoo

## Topic Tags:
Array | Hash Table | Two Pointers

## Approach:

### Approach 1: Brute Force (Not Recommended)

**Algorithm:**
```
for i from 0 to n-2:
    for j from i+1 to n-1:
        if nums[i] + nums[j] == target:
            return [i, j]
```

**Complexity:**
- Time: O(n²) - nested loops
- Space: O(1) - no extra space

**Why this fails:**
- Too slow for large arrays (n = 10⁴)
- Checks every possible pair

### Approach 2: Hash Map (Optimal Solution)

**Key Insight:**
- For each number `nums[i]`, we need to find if `target - nums[i]` exists
- Use hash map to store seen numbers and their indices
- Check in O(1) time if complement exists

**Algorithm:**
```
map = empty hash map  // stores {value: index}

for i from 0 to n-1:
    complement = target - nums[i]
    
    if complement exists in map:
        return [map[complement], i]
    
    map[nums[i]] = i

return []  // no solution (won't reach here as per problem)
```

**Complexity:**
- Time: O(n) - single pass through array
- Space: O(n) - hash map storage

### Step-by-Step Walkthrough (Example 1):
```
nums = [2, 7, 11, 15], target = 9

Initial: map = {}

Iteration 0 (i=0, nums[0]=2):
- complement = 9 - 2 = 7
- Is 7 in map? No
- Add to map: {2: 0}

Iteration 1 (i=1, nums[1]=7):
- complement = 9 - 7 = 2
- Is 2 in map? Yes! (index 0)
- Return [0, 1] ✓

Output: [0, 1]
```

### Step-by-Step Walkthrough (Example 2):
```
nums = [3, 2, 4], target = 6

Initial: map = {}

Iteration 0 (i=0, nums[0]=3):
- complement = 6 - 3 = 3
- Is 3 in map? No
- Add to map: {3: 0}

Iteration 1 (i=1, nums[1]=2):
- complement = 6 - 2 = 4
- Is 4 in map? No
- Add to map: {3: 0, 2: 1}

Iteration 2 (i=2, nums[2]=4):
- complement = 6 - 4 = 2
- Is 2 in map? Yes! (index 1)
- Return [1, 2] ✓

Output: [1, 2]
```

### Why Hash Map Works:

**Problem Breakdown:**
```
Given: nums[i] + nums[j] = target
We need: Find i and j

Rearranging: nums[j] = target - nums[i]

Strategy:
1. For current number nums[i]
2. Calculate complement = target - nums[i]
3. Check if we've seen this complement before
4. If yes, we found our pair!
```

**Visual Representation (Example 1):**
```
Array: [2, 7, 11, 15]
Target: 9

At index 0 (value=2):
  Looking for: 9 - 2 = 7
  Seen so far: {} → Not found
  Store: {2: 0}

At index 1 (value=7):
  Looking for: 9 - 7 = 2
  Seen so far: {2: 0} → Found at index 0!
  Answer: [0, 1]
```

### Approach 3: Two Pointers (Only if Sorting is Allowed)

**Note:** This approach changes the original array order and loses original indices.

**Algorithm (if we can modify array):**
```
1. Create array of (value, originalIndex) pairs
2. Sort by value
3. Use two pointers: left=0, right=n-1
4. If sum < target: left++
5. If sum > target: right--
6. If sum == target: return indices
```

**Complexity:**
- Time: O(n log n) - due to sorting
- Space: O(n) - for pairs array

**Why Hash Map is better:**
- No need to sort
- Preserves original indices
- O(n) instead of O(n log n)

### Edge Cases:

1. **Duplicate values:**
   - Input: [3, 3], target = 6
   - Output: [0, 1]
   - Both indices point to same value but different positions

2. **Negative numbers:**
   - Input: [-1, -2, -3, -4, -5], target = -8
   - Output: [2, 4] (for -3 and -5)

3. **Zero in array:**
   - Input: [0, 4, 3, 0], target = 0
   - Output: [0, 3]

4. **Minimum array size:**
   - Input: [1, 2], target = 3
   - Output: [0, 1]

5. **Large values:**
   - Input: [10⁹, -10⁹], target = 0
   - Must handle without overflow

### Implementation Details:

**Important Considerations:**

1. **Don't use same element twice:**
   - Check `i != j` or use the hash map approach which naturally handles this

2. **Order doesn't matter:**
   - Can return [0, 1] or [1, 0]
   - Problem accepts any order

3. **Exactly one solution exists:**
   - No need to handle "no solution" case
   - No need to find multiple solutions

4. **Hash Map Choice:**
   - Use HashMap/Dictionary/Object
   - Key: array value
   - Value: array index

### Common Mistakes:

1. **Using same element twice:**
```
   Wrong: nums[i] + nums[i] = target
   Correct: nums[i] + nums[j] = target where i != j
```

2. **Overwriting index in map:**
   - If duplicate values exist, later index overwrites earlier
   - This is fine since we find solution as soon as we see complement

3. **Not checking before adding:**
   - Must check if complement exists BEFORE adding current to map
   - Otherwise might match element with itself

4. **Returning values instead of indices:**
   - Problem asks for indices, not values
   - Return [i, j] not [nums[i], nums[j]]

### Optimization Notes:

**Why One-Pass Hash Map is Optimal:**
- Can't do better than O(n) time (must examine all elements)
- Hash map gives O(1) lookup
- Single pass is sufficient
- Space trade-off (O(n)) is acceptable

**Memory Optimization (if needed):**
- If memory is critical and array is sorted, use two pointers
- But loses O(n) time complexity (becomes O(n log n))

### Problem Variants:

1. **Two Sum II (Sorted Array):**
   - Use two pointers
   - O(1) space possible

2. **Two Sum III (Design):**
   - Design data structure supporting add() and find()

3. **3Sum:**
   - Find three numbers that sum to target
   - O(n²) solution

4. **4Sum:**
   - Find four numbers that sum to target
   - O(n³) solution

## Related Interview Experiences:
- Two Sum II - Input Array Is Sorted (LeetCode 167)
- Two Sum III - Data Structure Design (LeetCode 170)
- 3Sum (LeetCode 15)
- 4Sum (LeetCode 18)
- Two Sum IV - Input is a BST (LeetCode 653)

## Related Articles:
- Hash Table Fundamentals
- Two Pointers Technique
- Array Problems Optimization
- Time-Space Trade-offs