# Ways To Tile A Floor
---

> Video description: https://youtu.be/RqAqM5Z0KwI

[Problem](https://www.geeksforgeeks.org/problems/ways-to-tile-a-floor5836/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/RqAqM5Z0KwI/0.jpg)](https://youtu.be/RqAqM5Z0KwI)

---

**Difficulty:** Medium  
**Accuracy:** 32.31%  
**Submissions:** 59K+  
**Points:** 4  
**Average Time:** 40m

Given a floor of dimensions 2 × n and tiles of dimensions 2 × 1, the task is to find the number of ways the floor can be tiled. A tile can either be placed horizontally i.e as a 1 × 2 tile or vertically i.e as 2 × 1 tile.

**Note:** Two tiling arrangements are considered different if the placement of at least one tile differs.

## Examples:

**Example 1:**
```
Input: n = 3
Output: 3
Explanation: We need 3 tiles to tile the board of size 2 × 3.
We can tile in following ways:
1) Place all 3 tiles vertically.
2) Place first tile vertically and remaining 2 tiles horizontally.
3) Place first 2 tiles horizontally and remaining tiles vertically.

Visual representation:
Way 1: | | |        Way 2: | ==        Way 3: == |
       | | |               | ==               == |
```

**Example 2:**
```
Input: n = 4
Output: 5
Explanation: We need 4 tiles to tile the board of size 2 × 4.
We can tile in following ways:
1) All 4 vertical
2) All 4 horizontal
3) First 2 vertical, remaining 2 horizontal.
4) First 2 horizontal, remaining 2 vertical.
5) Corner 2 vertical, middle 2 horizontal.
```

## Constraints:
- 1 ≤ n ≤ 45

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(n) or O(1) with space optimization

## Company Tags:
Amazon

## Topic Tags:
Dynamic Programming | Recursion | Mathematical | Fibonacci Pattern | Combinatorics

## Approach:

### Problem Analysis:
This is a classic dynamic programming problem similar to the Fibonacci sequence.

**Floor Dimensions:** 2 × n (2 rows, n columns)  
**Tile Dimensions:** 2 × 1 (can be placed vertically or horizontally)

### Key Observations:

1. **Two Placement Options:**
   - **Vertical Placement:** One 2×1 tile covers one column (reduces problem to 2×(n-1))
   - **Horizontal Placement:** Two 1×2 tiles stacked cover two columns (reduces problem to 2×(n-2))

2. **Recurrence Relation:**
```
   dp[n] = dp[n-1] + dp[n-2]
```
   - dp[n-1]: Ways when placing first tile vertically
   - dp[n-2]: Ways when placing two tiles horizontally

3. **Base Cases:**
   - dp[1] = 1 (only one way: place one vertical tile)
   - dp[2] = 2 (two ways: two vertical tiles OR two horizontal tiles)

### Approach 1: Recursive Solution
```
Ways(n):
    if n == 1: return 1
    if n == 2: return 2
    return Ways(n-1) + Ways(n-2)
```

**Complexity:**
- Time: O(2^n) - exponential due to overlapping subproblems
- Space: O(n) - recursion stack

### Approach 2: Dynamic Programming (Memoization)
1. **Top-Down Approach:**
   - Use recursion with memoization
   - Store computed results in array/map
   - Avoid recomputing same subproblems

**Complexity:**
- Time: O(n)
- Space: O(n) - for dp array + recursion stack

### Approach 3: Dynamic Programming (Tabulation)
1. **Bottom-Up Approach:**
```
   dp[1] = 1
   dp[2] = 2
   for i = 3 to n:
       dp[i] = dp[i-1] + dp[i-2]
   return dp[n]
```

**Complexity:**
- Time: O(n)
- Space: O(n) - for dp array

### Approach 4: Space Optimized DP
1. **Observation:**
   - Only need last two values (like Fibonacci)
   - No need to store entire array

2. **Algorithm:**
```
   if n == 1: return 1
   if n == 2: return 2
   
   prev2 = 1  // dp[1]
   prev1 = 2  // dp[2]
   
   for i = 3 to n:
       current = prev1 + prev2
       prev2 = prev1
       prev1 = current
   
   return prev1
```

**Complexity:**
- Time: O(n)
- Space: O(1)

### Pattern Recognition:
This problem follows the **Fibonacci pattern**:
```
n = 1: 1
n = 2: 2
n = 3: 3
n = 4: 5
n = 5: 8
n = 6: 13
...
```
It's essentially Fibonacci sequence starting with F(1)=1, F(2)=2.

### Detailed Explanation (n=4):
```
Floor: 2 × 4

1) All vertical:     | | | |
                     | | | |

2) All horizontal:   = = = =
                     = = = =

3) V V H H:          | | = =
                     | | = =

4) H H V V:          = = | |
                     = = | |

5) V H H V:          | = = |
                     | = = |

Total = 5 ways
```

### Why This Recurrence Works:
- At position i, we can either:
  1. Place one vertical tile (leaving 2×(n-1) to fill)
  2. Place two horizontal tiles (leaving 2×(n-2) to fill)
- These are mutually exclusive choices
- Total ways = sum of both options

## Related Interview Experiences:
- Fibonacci Number
- Climbing Stairs (LeetCode 70)
- Decode Ways
- Domino and Tromino Tiling
- Tiling Problems Variations

## Related Articles:
- Dynamic Programming Fundamentals
- Fibonacci Pattern Problems
- Space Optimization in DP
- Combinatorial Game Theory