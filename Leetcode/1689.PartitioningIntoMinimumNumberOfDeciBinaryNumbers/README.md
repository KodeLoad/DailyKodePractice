# 1689. Partitioning Into Minimum Number Of Deci-Binary Numbers
---

> Video Solution: [https://youtu.be/ezlj6_FdijY](https://youtu.be/ezlj6_FdijY)

[Problem](https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/ezlj6_FdijY/0.jpg)](https://youtu.be/ezlj6_FdijY)

---

**Difficulty:** Medium  
**Accuracy:** 88.4%  
**Submissions:** 250K+  
**Points:** 1  
**Average Time:** 10m

A decimal number is called **deci-binary** if each of its digits is either `0` or `1` without any leading zeros. For example, `101` and `1100` are deci-binary, while `112` and `3001` are not.

Given a string `n` that represents a positive decimal integer, return the **minimum number** of positive deci-binary numbers needed so that they sum up to `n`.

## Examples:

**Example 1:**
<code>
Input: n = "32"
Output: 3
Explanation: 10 + 11 + 11 = 32. To get the '3' in the tens place, we need at least three 1s in that position across our deci-binary numbers. [00:00:44]
</code>

**Example 2:**
<code>
Input: n = "82734"
Output: 8
</code>

**Example 3:**
<code>
Input: n = "32"
Output: 3
</code>

## Constraints:
- 1 ≤ n.length ≤ 10⁵
- `n` consists of only digits.
- `n` does not contain any leading zeros and represents a positive integer.

## Expected Complexities:
- **Time Complexity:** O(N) where N is the length of the string.
- **Space Complexity:** O(1)

## Topic Tags:
Greedy | String

---

## Approach: Greedy (Maximum Digit)

### 1. The Intuition [00:01:43]
A deci-binary number can only contribute either a `0` or a `1` to any specific digit position in the sum. 
- To form the digit '3' at any position in the target number `n`, we need at least **three** deci-binary numbers that have a '1' in that specific position.
- Therefore, the minimum number of deci-binary numbers required is determined by the **largest digit** present in the string `n`. [00:02:42]

### 2. Strategy [00:02:58]
The problem reduces to a simple search for the maximum character in the string:
1. Iterate through each character in the string `n`.
2. Convert the character to its integer value.
3. Keep track of the maximum value encountered. [00:03:07]
4. Return that maximum value.

---

## Implementation (Conceptual): [00:03:15]
<code>
public int minPartitions(String n) {
    int maxDigit = 0;
    for (char c : n.toCharArray()) {
        maxDigit = Math.max(maxDigit, c - '0');
        // Optimization: If we find a '9', we can exit early
        if (maxDigit == 9) return 9;
    }
    return maxDigit;
}
</code>

---

## Key Takeaways:
- **Greedy Choice:** The bottleneck of the sum is the largest digit. Once you satisfy the requirement for the largest digit, you can always satisfy the smaller digits by using '0's in some of the deci-binary numbers. [00:02:16]
- **Efficiency:** The algorithm runs in $O(N)$ time, which is optimal because we must examine each digit at least once. [00:04:42]
- **Space Optimization:** We only store a single integer to track the maximum, achieving $O(1)$ space. [00:04:42]

## Related Problems:
- [Minimum Number of Taps to Open to Water a Garden](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/)
- [Add Strings](https://leetcode.com/problems/add-strings/)

## Keywords:
partitioning into minimum number of deci-binary numbers, leetcode 1689, greedy string problems, decimal to deci-binary, competitive programming java, max digit in string.

---

**SEO Tags:** #Greedy #Strings #LeetCode #DSA #Algorithms #CodingInterview #Java #Python #ProblemSolving #OBrutus

**Learning Outcomes:**
- Identifying greedy properties in numerical partition problems.
- Reducing complex-sounding problems to simple data properties (max digit).
- Implementing efficient string traversal in Java.
