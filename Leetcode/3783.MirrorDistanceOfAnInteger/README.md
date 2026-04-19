# 3783. Mirror Distance of an Integer
---

> Video Solution: [https://youtu.be/jPW9SebJ4uo](https://youtu.be/jPW9SebJ4uo)

[Problem](https://leetcode.com/problems/mirror-distance-of-an-integer/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://leetcode.com/problems/mirror-distance-of-an-integer/solutions/7996919/two-different-solution-under-3-min-by-ob-ftcp/)

[![img](https://img.youtube.com/vi/jPW9SebJ4uo/maxresdefault.jpg)](https://youtu.be/jPW9SebJ4uo)

---

**Difficulty:** Easy  
**Points:** 3

The **mirror** of an integer is obtained by reversing its digits. The **mirror distance** of an integer `n` is the absolute difference between `n` and its mirror.

Return the mirror distance of `n`.

## Examples:

**Example 1:**
<code>
Input: n = 12
Output: 9
Explanation: mirror(12) = 21, |21 - 12| = 9
</code>

**Example 2:**
<code>
Input: n = 11
Output: 0
Explanation: mirror(11) = 11, |11 - 11| = 0
</code>

**Example 3:**
<code>
Input: n = 123
Output: 198
Explanation: mirror(123) = 321, |321 - 123| = 198
</code>

## Constraints:
- `1 <= n <= 10^4`

---

## Approach:

### 1. StringBuilder Reverse ($O(d)$ where d = number of digits)
Convert `n` to a string, reverse it using `StringBuilder.reverse()`, parse it back to an integer, and return the absolute difference.

### 2. Arithmetic Reverse
Compute the mirror manually using modulo and division without string conversion — equally clean and avoids object allocation.

---

## Implementation:

### Solution 1 — StringBuilder
<code>
public int mirrorDistance(int n) {
    return Math.abs(Integer.parseInt(
        new StringBuilder(n + "").reverse().toString()
    ) - n);
}
</code>

### Solution 2 — Arithmetic
<code>
public int mirrorDistance(int n) {
    int original = n, mirror = 0;
    while (n > 0) {
        mirror = mirror * 10 + n % 10;
        n /= 10;
    }
    return Math.abs(mirror - original);
}
</code>

---

## Key Takeaways:
- **String vs Arithmetic:** Both approaches are $O(d)$; the arithmetic approach avoids object allocation which is slightly more efficient in tight loops.
- **Palindrome Check:** If mirror distance is `0`, the number is a palindrome.
- **Simplicity:** Despite being an Easy problem, it tests familiarity with both string manipulation and digit extraction patterns.

## Keywords:
mirror distance of an integer leetcode 3783, leetcode easy java, reverse integer digits, palindrome number check, coding interview java.

---

**SEO Tags:** #LeetCode #Java #EasyProblems #MirrorDistance #StringBuilder #ArithmeticReverse #OBrutus #CodingInterview

**Learning Outcomes:**
- Using `StringBuilder.reverse()` for digit reversal.
- Extracting digits using modulo arithmetic.
- Recognizing palindrome numbers via mirror distance.
