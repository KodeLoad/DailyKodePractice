# Count Number of Substrings (Exactly K Distinct)
---

> Video Solution (152 Seconds): [https://youtu.be/UOV42D30s4s](https://youtu.be/UOV42D30s4s)

[Problem](https://www.geeksforgeeks.org/problems/count-number-of-substrings4528/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/UOV42D30s4s/0.jpg)](https://youtu.be/UOV42D30s4s)

---

**Difficulty:** Medium  
**Accuracy:** 15.83%  
**Submissions:** 137K+  
**Points:** 4  
**Average Time:** 25m

Given a string <code>s</code> and an integer <code>k</code>, your task is to count all possible substrings that have **exactly k** distinct characters.

## Examples:

**Example 1:**
<code>
Input: s = "aba", k = 2
Output: 3

Explanation: 
Substrings with exactly 2 distinct characters:
"ab", "ba", "aba". [Watch explanation @0:11]
</code>

**Example 2:**
<code>
Input: s = "abaaca", k = 1
Output: 7

Explanation: 
Substrings with exactly 1 distinct character:
"a", "b", "a", "a", "c", "a", "aa".
</code>

## Constraints:
- 1 ≤ |s| ≤ 10⁶
- 1 ≤ k ≤ 26
- String <code>s</code> consists of lowercase English letters.

## Expected Complexities:
- **Time Complexity:** O(|s|)
- **Space Complexity:** O(1) (since the character set is limited to 26)

## Topic Tags:
Strings | Sliding Window | Hash | Two Pointers | Bit Magic

---

## Approach: The "At Most" Transformation

**Key Insight:** [Watch explanation @0:50]
It is difficult to count "exactly K" substrings directly using a sliding window because the window doesn't shrink in a monotonic way that allows for easy counting.

Instead, we use the logic:
<code>Exactly(K) = AtMost(K) - AtMost(K-1)</code> [Watch logic @1:34]

**Why this works?**
- `AtMost(K)` counts all substrings with 1, 2, ..., K distinct characters.
- `AtMost(K-1)` counts all substrings with 1, 2, ..., K-1 distinct characters.
- Subtracting the two leaves you with exactly the substrings that have K distinct characters.

### Optimal Algorithm:
1. Create a helper function <code>atMost(k)</code>.
2. Inside `atMost`, use two pointers (sliding window) and a frequency array/map.
3. For every <code>right</code> pointer position, shrink the <code>left</code> pointer until there are at most `k` distinct characters.
4. Add <code>(right - left + 1)</code> to the total. [Watch @1:41]
5. Return <code>atMost(s, k) - atMost(s, k-1)</code>.

---

## Implementation (Conceptual):
<code>
def countSubstrings(s, k):
    return atMost(s, k) - atMost(s, k - 1)

def atMost(s, k):
    if k < 0: return 0
    left = 0
    res = 0
    cnt = 0
    freq = [0] * 26 # Use array for O(1) space
    
    for right in range(len(s)):
        # If new character, increment distinct count
        if freq[ord(s[right]) - ord('a')] == 0:
            cnt += 1
        freq[ord(s[right]) - ord('a')] += 1
        
        # Shrink window if distinct > k
        while cnt > k:
            freq[ord(s[left]) - ord('a')] -= 1
            if freq[ord(s[left]) - ord('a')] == 0:
                cnt -= 1
            left += 1
            
        res += (right - left + 1)
    return res
</code>

---

## Key Takeaways:
- **String to Array:** The video suggests converting the string to a character array (<code>s.toCharArray()</code>) for faster access in Java. [Watch @1:05]
- **Space Optimization:** Since we only have lowercase English letters, a frequency array of size 26 is better than a HashMap. [Watch @1:28]
- **Reusability:** This logic is almost identical to the "Subarrays with K Distinct Integers" problem, just applied to strings. [Watch @0:27]

## Common Mistakes:
1. **Directly counting K:** Trying to find exactly K in one pass is much harder and usually leads to $O(N^2)$ or complex logic.
2. **Handling K=0:** Ensure the helper handles cases where <code>k-1</code> becomes negative.
3. **Map vs Array:** Using a full HashMap might be slower than a fixed-size integer array for character counts.

## Related Problems:
- [Subarrays with At Most K Distinct Integers](https://www.geeksforgeeks.org/problems/subarrays-with-at-most-k-distinct-integers/1)
- [Longest K unique characters substring](https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1)
- [Fruit Into Baskets (LeetCode 904)](https://leetcode.com/problems/fruit-into-baskets/)

## Keywords:
substrings with k distinct characters, sliding window exactly k, exactly k distinct strings, geeksforgeeks potd solution, at most k technique, string optimization, coding interview patterns.

---

**SEO Tags:** #SlidingWindow #Strings #ExactlyK #Substrings #DSA #CodingInterview #GeeksforGeeks #ProblemSolving #Algorithms

**Learning Outcomes:**
- Transforming "Exactly K" problems into "At Most K" problems. [Watch @1:34]
- Efficient window sliding with character frequency tracking.
- Reducing time complexity from $O(N^2)$ to $O(N)$.
