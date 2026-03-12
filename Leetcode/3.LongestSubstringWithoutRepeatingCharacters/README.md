# 3. Longest Substring Without Repeating Characters
---

> Video Solution: [https://youtu.be/r7EotNjoTQM](https://youtu.be/r7EotNjoTQM)

[Problem](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/r7EotNjoTQM/0.jpg)](https://youtu.be/r7EotNjoTQM)

---

**Difficulty:** Medium  
**Accuracy:** 35.8%  
**Submissions:** 6.2M+  
**Points:** 1  
**Average Time:** 25m

Given a string `s`, find the length of the **longest substring** without repeating characters.

## Examples:

**Example 1:**
<code>
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. [00:00:29]
</code>

**Example 2:**
<code>
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1. [00:00:54]
</code>

**Example 3:**
<code>
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring. [00:00:58]
</code>

## Constraints:
- 0 ≤ s.length ≤ 5 * 10⁴
- `s` consists of English letters, digits, symbols and spaces.

## Expected Complexities:
- **Time Complexity:** O(N) where N is the length of the string.
- **Space Complexity:** O(min(m, n)) where m is the size of the charset.

## Topic Tags:
Hash Table | String | Sliding Window

---

## Approach: Sliding Window with HashSet

### 1. The Intuition [00:01:21]
To find the longest substring without duplicates, we don't need to check every possible substring (which would be $O(N^2)$). Instead, we can use a **Sliding Window** that expands and shrinks dynamically.
- Use two pointers: `start` and `end`.
- Use a `HashSet` to keep track of the unique characters currently in the window. [00:03:04]

### 2. Strategy (Step-by-Step) [00:04:00]
1.  **Expand:** Move the `end` pointer to the right and add each character to the `HashSet`. [00:05:05]
2.  **Validate:** If the character at `end` is already in the set (meaning we found a duplicate), we have an "invalid" window. [00:05:59]
3.  **Shrink:** While the window is invalid, remove characters from the `HashSet` starting from the `start` pointer and move `start` forward. [00:06:21]
4.  **Update Result:** At each step where the window is valid, calculate its length (`end - start + 1`) and update the `maxLength`. [00:07:38]

---

## Implementation (Conceptual): [00:05:31]
<code>
public int lengthOfLongestSubstring(String s) {
    int start = 0, end = 0, maxLen = 0;
    Set<Character> seen = new HashSet<>();
    
    while (end < s.length()) {
        char incoming = s.charAt(end);
        
        // Shrink the window from the left if duplicate found
        while (seen.contains(incoming)) {
            seen.remove(s.charAt(start));
            start++;
        }
        
        // Add new character and update max length
        seen.add(incoming);
        maxLen = Math.max(maxLen, end - start + 1);
        end++;
    }
    return maxLen;
}
</code>

---

## Key Takeaways:
- **Sliding Window Pattern:** This technique reduces the time complexity from $O(N^2)$ to $O(N)$ by ensuring each character is visited at most twice (once by `end` and once by `start`). [00:08:46]
- **HashSet Efficiency:** Provides $O(1)$ average time complexity for `add`, `remove`, and `contains` operations. [00:03:16]
- **Linear Space:** The space complexity is proportional to the number of unique characters in the input string. [00:02:43]

## Related Problems:
- [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)
- [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)
- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

## Keywords:
longest substring without repeating characters, leetcode 3, sliding window hashset, two pointers string problem, java sliding window tutorial, coding interview questions, dsa substrings.

---

**SEO Tags:** #SlidingWindow #HashSet #Strings #LeetCode #DSA #Algorithms #CodingInterview #Java #Python #ProblemSolving #OBrutus

**Learning Outcomes:**
- Mastery of the sliding window technique for substring problems.
- Efficiently handling duplicates using Hash data structures.
- Achieving optimal linear time solutions for string manipulation.