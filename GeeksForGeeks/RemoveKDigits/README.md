# Remove K Digits - Smallest Possible Number
---

> Video Solution: [https://youtu.be/a5IbCLC1ibM](https://youtu.be/a5IbCLC1ibM)

[Problem](https://www.geeksforgeeks.org/problems/remove-k-digits/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/a5IbCLC1ibM/0.jpg)](https://youtu.be/a5IbCLC1ibM)

---

**Difficulty:** Medium  
**Accuracy:** 26.69%  
**Submissions:** 130K+  
**Points:** 4  
**Average Time:** 35m

Given a non-negative integer `S` represented as a string and an integer `K`, remove **exactly K** digits from the string so that the resulting number is the **smallest possible**.

## Examples:

**Example 1:**
<code>
Input: S = "149811", K = 3
Output: "111"
Explanation: Remove 4, 9, and 8 to get 111, which is the smallest. [00:01:03]
</code>

**Example 2:**
<code>
Input: S = "1002991", K = 3
Output: "21"
Explanation: Remove 1, 9, 9 to get 0021, which becomes 21 after removing leading zeros. [00:01:19]
</code>

## Constraints:
- 1 ≤ |S| ≤ 10⁶
- 1 ≤ K ≤ |S|

## Expected Complexities:
- **Time Complexity:** O(|S|)
- **Space Complexity:** O(|S|)

## Topic Tags:
Stack | Greedy | Data Structures

---

## Approach: Monotonic Stack (Greedy Strategy)

### 1. The Core Intuition [00:03:52]
To make a number smaller, we want smaller digits at higher significance levels (leftmost positions). If we find a scenario where a digit is larger than its next digit (e.g., `...43...`), removing the larger digit (`4`) will always result in a smaller number than removing the smaller one (`3`).

### 2. Stack-Based Solution [00:08:58]
- We traverse the string and maintain a **Monotonic Increasing Stack**.
- If the current digit is smaller than the top of the stack and `K > 0`, we **pop** the stack (remove the larger digit) and decrement `K`. [00:10:14]
- We do this repeatedly until either the stack is empty, the current digit is no longer smaller, or `K` reaches zero. [00:11:06]

### 3. Edge Case: Remaining K [00:11:52]
If we finish the loop and `K` is still greater than 0 (this happens for sorted strings like "12345"), we simply pop the remaining `K` digits from the end of the stack.

### 4. Sanitization [00:13:56]
- **Leading Zeros:** After building the result from the stack, remove any leading '0' characters.
- **Empty String:** If removing leading zeros results in an empty string, return "0". [00:15:02]

---

## Visualizing the Logic:
<code>
S = "1432219", K = 3

1. Push '1': [1]
2. '4' > '1': Push '4' -> [1, 4]
3. '3' < '4': Pop '4', K=2, Push '3' -> [1, 3]
4. '2' < '3': Pop '3', K=1, Push '2' -> [1, 2]
5. '2' == '2': Push '2' -> [1, 2, 2]
6. '1' < '2': Pop '2', K=0, Push '1' -> [1, 2, 1]
7. K=0: Push remaining '9' -> [1, 2, 1, 9]

Final Result: "1219"
</code>

---

## Implementation (Conceptual):
<code>
def removeKdigits(S, K):
    stack = []
    for digit in S:
        while stack and K > 0 and stack[-1] > digit:
            stack.pop()
            K -= 1
        stack.append(digit)
    
    # Remove remaining K from back
    while K > 0:
        stack.pop()
        K -= 1
        
    # Join and strip leading zeros
    res = "".join(stack).lstrip('0')
    return res if res else "0"
</code>

---

## Key Takeaways:
- **Greedy logic:** Local optimal choices (removing a peak digit) lead to the global optimal solution. [00:05:36]
- **Stack Property:** A stack is perfect for comparing a current element with its immediate predecessor. [00:08:04]
- **Time Complexity:** O(N) because each character is pushed and popped at most once.

## Related Problems:
- [Next Greater Element](https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)
- [Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/)
- [Create Maximum Number](https://leetcode.com/problems/create-maximum-number/)

## Keywords:
remove k digits, smallest possible number, monotonic stack, greedy algorithm, stack data structure, geeksforgeeks potd, string manipulation, O(n) solution.

---

**SEO Tags:** #Stack #Greedy #MonotonicStack #StringManipulation #DSA #CodingInterview #GeeksforGeeks #Python #Java #Algorithms

**Learning Outcomes:**
- Understanding the relationship between digit significance and number value.
- Mastering the monotonic stack for "peak removal" problems.
- Handling string edge cases like leading zeros and empty results.