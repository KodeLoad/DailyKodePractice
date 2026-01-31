# 744. Find Smallest Letter Greater Than Target
---

> Video Solution: [https://youtu.be/r_OXaGsGIp4](https://youtu.be/r_OXaGsGIp4)

[Problem](https://leetcode.com/problems/find-smallest-letter-greater-than-target/) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/r_OXaGsGIp4/0.jpg)](https://youtu.be/r_OXaGsGIp4)

---

**Difficulty:** Easy  
**Accuracy:** 54.3%  
**Submissions:** 500K+  
**Points:** 1  
**Average Time:** 10m

You are given an array of characters `letters` that is sorted in **non-decreasing order**, and a character `target`. There are at least two different characters in `letters`.

Return the smallest character in `letters` that is lexicographically greater than `target`. If such a character does not exist, return the first character in `letters`.

## Examples:

**Example 1:**
<code>
Input: letters = ["c","f","j"], target = "a"
Output: "c"
Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.
</code>

**Example 2:**
<code>
Input: letters = ["c","f","j"], target = "c"
Output: "f"
Explanation: The smallest character that is lexicographically greater than 'c' in letters is 'f'.
</code>

**Example 3:**
<code>
Input: letters = ["x","x","y","y"], target = "z"
Output: "x"
Explanation: There are no characters in letters that is lexicographically greater than 'z' so we return letters[0].
</code>

## Constraints:
- 2 ≤ letters.length ≤ 10⁴
- letters[i] is a lowercase English letter.
- letters is sorted in non-decreasing order.
- target is a lowercase English letter.

## Expected Complexities:
- **Time Complexity:** O(log N)
- **Space Complexity:** O(1)

## Topic Tags:
Array | Binary Search

---

## Approach: Optimized Binary Search

### 1. Intuition
Since the array is already sorted, we can avoid a linear $O(N)$ scan. Instead, we use **Binary Search** to find the upper bound of the `target` in $O(\log N)$ time.

### 2. Handling the "Wrap Around" Condition
The problem states that if no character is greater than the target, we must return the first character. 
- In Binary Search, if the `low` pointer ends up equal to the array length `N`, it means every element was less than or equal to the target.
- We can handle this simply by returning `letters[low % N]`.

### 3. Algorithm Steps:
1. Initialize `low = 0` and `high = letters.length - 1`.
2. While `low <= high`:
    - Calculate `mid = low + (high - low) / 2`.
    - If `letters[mid]` is less than or equal to `target`, search in the right half (`low = mid + 1`).
    - Otherwise, the potential answer is in the left half, so move `high = mid - 1`.
3. Return `letters[low % letters.length]`.

---

## Implementation (Conceptual):
<code>
public char nextGreatestLetter(char[] letters, char target) {
    int n = letters.length;
    int low = 0, high = n - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (letters[mid] <= target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return letters[low % n];
}
</code>

---

## Key Takeaways:
- **Binary Search Variation:** This is a variation of the "ceiling" or "upper bound" problem.
- **Lexicographical Comparison:** Characters in Java/C++/Python can be compared directly using `<` and `>` operators as they compare ASCII/Unicode values.
- **Modulo Operator:** Using `% n` is a clean way to handle the circular array property described in the problem.

## Related Problems:
- [Search Insert Position](https://leetcode.com/problems/search-insert-position/)
- [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

## Keywords:
find smallest letter greater than target, binary search, leetcode 744, leetcode daily challenge, sorted array search, java binary search, wrap around array.

---

**SEO Tags:** #LeetCode #BinarySearch #Algorithms #DSA #CodingInterview #Java #Python #ProblemSolving #OBrutus

**Learning Outcomes:**
- Efficiently searching in sorted data using Binary Search.
- Managing boundary conditions and index overflows with the modulo operator.
- Understanding the "Upper Bound" search pattern.
