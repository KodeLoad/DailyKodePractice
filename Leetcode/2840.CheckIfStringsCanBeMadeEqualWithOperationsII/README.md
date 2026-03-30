# 2840. Check if Strings Can be Made Equal With Operations II
---

> Video Solution: [https://youtu.be/ZEFXRsrvrfE](https://youtu.be/ZEFXRsrvrfE)

[Problem](https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-ii/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/ZEFXRsrvrfE/maxresdefault.jpg)](https://youtu.be/ZEFXRsrvrfE)

---

**Difficulty:** Medium  
**Accuracy:** 62.4%  
**Submissions:** 45K+  
**Points:** 4  
**Average Time:** 20m

You are given two strings `s1` and `s2`, both of length `n`, consisting of lowercase English letters. 
You can apply the following operation any number of times on either string:
- Choose any two indices `i` and `j` such that `i < j` and the difference `j - i` is **even**, then swap the characters at those indices.

Return `true` if you can make the strings `s1` and `s2` equal, and `false` otherwise.

## Examples:

**Example 1:**
<code>
Input: s1 = "abcdba", s2 = "cabdab"
Output: true
Explanation: We can swap s1[0] with s1[2], s1[2] with s1[4], etc., because the difference is always even. [00:00:41]
</code>

**Example 2:**
<code>
Input: s1 = "abe", s2 = "bea"
Output: false
Explanation: Even though they are anagrams, the 'a' in s1 is at an even index (0) while in s2 it's at an odd index (2), and we can only swap even-to-even. [00:01:20]
</code>

## Constraints:
- `n == s1.length == s2.length`
- 1 ≤ n ≤ 10⁵
- `s1` and `s2` consist only of lowercase English letters.

## Expected Complexities:
- **Time Complexity:** O(N) where N is the length of the string.
- **Space Complexity:** O(1) (since the frequency array is constant size 26).

## Topic Tags:
Hash Table | String | Sorting

---

## Approach: Even and Odd Index Anagrams

### 1. The Intuition [00:01:38]
The key constraint is that `j - i` must be even. This means:
- Characters at **even indices** (0, 2, 4...) can only be swapped with other characters at even indices.
- Characters at **odd indices** (1, 3, 5...) can only be swapped with other characters at odd indices.

If we can swap characters freely within their respective "even" or "odd" groups, then `s1` can be made equal to `s2` if and only if the characters at even indices in `s1` are an **anagram** of those at even indices in `s2`, and similarly for the odd indices. [00:02:54]

### 2. Implementation Strategy [00:03:32]
Instead of creating new strings (which costs $O(N)$ extra space), we use a frequency array:
1.  Create a frequency table of size 26.
2.  **Check Even Indices:** Iterate through `s1` and `s2` at indices `0, 2, 4...`. Increment counts for `s1` and decrement for `s2`. If the final table isn't all zeros, return `false`. [00:04:14]
3.  **Check Odd Indices:** Reset the table and repeat for indices `1, 3, 5...`. [00:05:23]
4.  If both checks pass, return `true`.

---

## Implementation (Conceptual): [00:03:42]
<code>
public boolean checkStrings(String s1, String s2) {
    return isAnagram(s1, s2, 0) && isAnagram(s1, s2, 1);
}

private boolean isAnagram(String s1, String s2, int start) {
    int[] count = new int[26];
    for (int i = start; i < s1.length(); i += 2) {
        count[s1.charAt(i) - 'a']++;
        count[s2.charAt(i) - 'a']--;
    }
    for (int c : count) if (c != 0) return false;
    return true;
}
</code>

---

## Key Takeaways:
- **Index Partitioning:** Breaking the problem into two independent sub-problems (even vs. odd) simplifies the logic. [00:02:01]
- **Space Efficiency:** Using a fixed-size `int[26]` array keeps space complexity at $O(1)$, which is better than $O(N)$ string slicing. [00:05:07]
- **Linear Time:** We only traverse the strings once, achieving optimal $O(N)$ performance. [00:06:02]

## Related Problems:
- [Check if Strings Can be Made Equal With Operations I](https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-i/)
- [Valid Anagram](https://leetcode.com/problems/valid-anagram/)
- [Swap Adjacent in LR String](https://leetcode.com/problems/swap-adjacent-in-lr-string/)

## Keywords:
check if strings can be made equal, leetcode 2840, even odd index swap, anagram check java, string manipulation interview, coding challenge solution.

---

**SEO Tags:** #LeetCode #String #Hashset #Anagram #DSA #Algorithms #CodingInterview #Java #Python #ProblemSolving #OBrutus

**Learning Outcomes:**
- Understanding the implications of distance-based swap constraints.
- Efficiently checking anagram properties using frequency arrays.
- Reducing $O(N)$ space approaches to $O(1)$ constant space.