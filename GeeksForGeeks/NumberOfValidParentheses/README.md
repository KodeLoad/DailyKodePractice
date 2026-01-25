# Number of Valid Parentheses
---

> Video Solution: [https://youtu.be/AtFWThboi5k](https://youtu.be/AtFWThboi5k)

[Problem](https://www.geeksforgeeks.org/problems/valid-number-of-parenthesis/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/AtFWThboi5k/0.jpg)](https://youtu.be/AtFWThboi5k)

---

**Difficulty:** Medium  
**Accuracy:** 43.12%  
**Submissions:** 45K+  
**Points:** 4  
**Average Time:** 25m

Given an integer `N`, find the total number of **valid combinations** of parentheses that can be formed using <code>N</code> characters. Each combination must contain an equal number of opening <code>(</code> and closing <code>)</code> brackets.

## Examples:

**Example 1:**
<code>
Input: N = 2
Output: 1
Explanation: The only valid combination of length 2 is "()". [00:00:29]
</code>

**Example 2:**
<code>
Input: N = 4
Output: 2
Explanation: Valid combinations of length 4 are "(())" and "()()".
</code>

**Example 3:**
<code>
Input: N = 3
Output: 0
Explanation: Valid parentheses must have an even length. [00:05:25]
</code>

## Constraints:
- 1 ≤ N ≤ 20
- If N is odd, the output is always 0.

## Expected Complexities:
- **Time Complexity:** O(N²) (with Memoization)
- **Space Complexity:** O(N²) (for the DP table)

---

## Approach: Recursive Backtracking to Dynamic Programming

### 1. The Recursive Idea [00:01:46]
Since we have `N` total characters, we must use `N/2` opening brackets and `N/2` closing brackets. At each step, we have two choices:
- Add an **Opening Bracket** <code>(</code> if we have any left.
- Add a **Closing Bracket** <code>)</code> if it won't violate validity.

**Validity Rule:** You can only add a closing bracket if the number of closing brackets used so far is **less than** the number of opening brackets used. [00:04:01]

### 2. Efficiency & Memoization [00:06:06]
A simple recursive approach would have a complexity of $O(2^N)$, which is too slow for larger $N$. However, many sub-problems (states defined by `remaining_open` and `remaining_close`) repeat. [00:06:55]

**Optimization:** Use a 2D DP array `memo[open][close]` to store the count of valid combinations for each state. This reduces the complexity to $O(N^2)$. [00:07:16]

### 3. Connection to Catalan Numbers [00:09:55]
This problem is a classic application of **Catalan Numbers**. The number of valid parentheses with `n` pairs (total length `2n`) is given by the $n$-th Catalan number:
$$C_n = \frac{1}{n+1} \binom{2n}{n}$$
This same sequence appears in Matrix Chain Multiplication and counting Binary Search Trees. [00:10:13]

---

## Implementation (Conceptual):
<code>
def countValidParentheses(n):
    if n % 2 != 0: return 0
    k = n // 2
    memo = [[-1] * (k + 1) for _ in range(k + 1)]
    
    def solve(open_rem, close_rem):
        if open_rem == 0 and close_rem == 0:
            return 1
        if memo[open_rem][close_rem] != -1:
            return memo[open_rem][close_rem]
            
        res = 0
        # Choice 1: Add Open
        if open_rem > 0:
            res += solve(open_rem - 1, close_rem)
        # Choice 2: Add Close (only if more open brackets were used)
        if close_rem > open_rem:
            res += solve(open_rem, close_rem - 1)
            
        memo[open_rem][close_rem] = res
        return res

    return solve(k, k)
</code>

---

## Key Takeaways:
- **Even Length Constraint:** Valid parentheses strings must have an even length. [00:05:31]
- **State Definition:** The state is determined by how many opening and closing brackets are still available to be placed.
- **Top-Down DP:** Memoization is the easiest way to optimize the overlapping sub-problems in this recursive tree. [00:08:10]

## Related Problems:
- [Generate Parentheses (LeetCode 22)](https://leetcode.com/problems/generate-parentheses/)
- [Matrix Chain Multiplication](https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1)
- [Total Number of Spanning Trees in a Graph]

## Keywords:
valid parentheses count, catalan numbers dsa, recursion with memoization, dynamic programming geeksforgeeks, parentheses combinations, coding interview questions, top-down dp.

---

**SEO Tags:** #DynamicProgramming #Recursion #CatalanNumbers #Parentheses #DSA #GeeksforGeeks #CodingInterview #Java #Python #Algorithms

**Learning Outcomes:**
- Designing recursive solutions for combinatorial problems.
- Identifying and optimizing overlapping sub-problems using 2D DP.
- Understanding the mathematical foundation of balanced sequences.
