# 1784. Check if Binary String Has at Most One Segment of Ones
---

> Video Solution: [https://youtu.be/Jld4BufPdas](https://youtu.be/Jld4BufPdas)

[Problem](https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/) | [Java Solution](./java_solution/Solution.java) | [Python Solution](./python/solution.py) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/Jld4BufPdas/0.jpg)](https://youtu.be/Jld4BufPdas)

---

**Difficulty:** Easy  
**Accuracy:** 39.4%  
**Submissions:** 100K+  
**Points:** 1  
**Average Time:** 5m

Given a binary string `s` **without leading zeros**, return `true` if `s` contains at most **one contiguous segment of ones**. Otherwise, return `false`.

## Examples:

**Example 1:**
<code>
Input: s = "1001"
Output: false
Explanation: The ones do not form a contiguous segment. [00:00:37]
</code>

**Example 2:**
<code>
Input: s = "110"
Output: true
</code>

## Constraints:
- 1 ≤ s.length ≤ 100
- `s[i]` is either '0' or '1'.
- `s[0]` is '1'.

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(1)

## Topic Tags:
String | Greedy

---

## Approach: Pattern Search ("01")

### 1. The Intuition [00:01:20]
The problem guarantees that the string `s` starts with '1' (no leading zeros). 
- If there is only one segment of ones, all '1's must be at the beginning.
- If a second segment of '1's exists, there **must** be at least one '0' between the first segment and the second segment.
- Therefore, the only way to have more than one segment is if the pattern `"01"` exists anywhere in the string. [00:02:12]

### 2. Strategy [00:02:15]
We simply need to check if the substring `"01"` is absent from the string `s`.
- If `"01"` is found: It means a '1' appeared after a '0', indicating a new segment of ones has started. Return `false`.
- If `"01"` is NOT found: It means all '1's were contiguous at the start (or there were no '0's at all). Return `true`.

---

## Implementation (Conceptual): [00:02:20]
<code>
public boolean checkOnesSegment(String s) {
    // If "01" is not in the string, it's a single segment
    return !s.contains("01");
}
</code>

---

## Key Takeaways:
- **Constraint Leverage:** The fact that `s[0] == '1'` is key. It eliminates the need to worry about '1's appearing for the first time later in the string without a prior '0'. [00:01:30]
- **Simplicity:** While you could solve this by counting segments or using two pointers, a simple string search is the most concise and readable method. [00:02:40]
- **Complexity:** String searching takes $O(N)$ time, which is optimal for this problem. [00:02:45]

## Related Problems:
- [Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)
- [Detect Capital](https://leetcode.com/problems/detect-capital/)

## Keywords:
check if binary string has at most one segment of ones, leetcode 1784, binary string contiguous segment, string pattern matching java, coding interview questions, simple string algorithms.

---

**SEO Tags:** #LeetCode #String #Algorithms #DSA #CodingInterview #Java #Python #ProblemSolving #OBrutus #BinaryString

**Learning Outcomes:**
- Using problem constraints to simplify algorithmic logic.
- Identifying patterns in binary string segments.
- Implementing efficient string contains checks in Java/Python.
