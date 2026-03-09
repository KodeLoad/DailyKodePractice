# Largest Number in One Swap
---

> Video Solution: [https://youtu.be/Bps4Z6DdHFY](https://youtu.be/Bps4Z6DdHFY)

[Problem](https://www.geeksforgeeks.org/problems/largest-number-in-one-swap1520/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/Bps4Z6DdHFY/0.jpg)](https://youtu.be/Bps4Z6DdHFY)

---

**Difficulty:** Medium  
**Accuracy:** 48.33%  
**Submissions:** 50K+  
**Points:** 4  
**Average Time:** 30m

Given a string `s` representing a large integer, your task is to return the **lexicographically largest** string that can be obtained by swapping at most **one pair** of characters in `s`.

## Examples:

**Example 1:**
<code>
Input: s = "768"
Output: "867"
Explanation: By swapping '7' and '8', we get "867", which is the largest possible number with one swap. [00:00:39]
</code>

**Example 2:**
<code>
Input: s = "333"
Output: "333"
Explanation: All digits are the same, so no swap will change the number. [00:00:54]
</code>

**Example 3:**
<code>
Input: s = "987"
Output: "987"
Explanation: The number is already the largest possible.
</code>

## Constraints:
- 1 ≤ s.length ≤ 10⁵
- `s` consists of only digits.

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(1) (ignoring the output string)

## Topic Tags:
Greedy | Strings | Sorting

---

## Approach: Selection Sort Inspired Greedy Strategy

### 1. The Intuition [00:01:08]
To make a number as large as possible with only one swap, we want the largest possible digit to be as close to the **front** (most significant position) as possible. 
- We look for the first position from the left where a larger digit exists later in the string.
- When we find such a digit, we swap it with the **rightmost** occurrence of the largest possible digit to maximize the result. [00:04:45]

### 2. Strategy (Step-by-Step) [00:02:22]
Instead of a complex greedy approach, we can adapt the logic of **Selection Sort** but limit it to exactly **one swap**:
1.  **Convert to Array:** Since Java strings are immutable, convert `s` to a character array `char[]`. [00:03:40]
2.  **Find the Swap Candidate:** Iterate through the string from left to right (`i`).
3.  **Find the Best Max:** For each `i`, look at all characters to its right (`j`). Find the maximum character that is strictly greater than `s[i]`. [00:04:19]
4.  **Important:** If there are multiple occurrences of the same maximum character, pick the **rightmost** one to keep the larger digits at higher significant positions. [00:04:38]
5.  **Execute Swap:** Once the first profitable swap is found, perform the swap and **break** the loop immediately. [00:05:40]

---

## Implementation (Conceptual): [00:03:58]
<code>
public String largestWithOneSwap(String s) {
    char[] arr = s.toCharArray();
    int n = arr.length;
    
    for (int i = 0; i < n; i++) {
        int maxIdx = i;
        // Look for the largest digit to the right
        for (int j = n - 1; j > i; j--) {
            if (arr[j] > arr[maxIdx]) {
                if (maxIdx == i || arr[j] > arr[maxIdx]) {
                    maxIdx = j;
                }
            }
        }
        
        // If a larger digit was found, swap and return
        if (maxIdx != i) {
            char temp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = temp;
            break;
        }
    }
    return new String(arr);
}
</code>

---

## Key Takeaways:
- **Lexicographical Order:** Moving a larger digit to a higher power of 10 always results in a larger number. [00:01:54]
- **Rightmost Occurrence:** Swapping with the rightmost maximum digit is crucial for cases like "199", where swapping the first '1' with the *last* '9' is optimal. [00:04:53]
- **Efficiency:** While the implementation looks like $O(N^2)$, it is highly efficient for this specific constraint and can be optimized further to $O(N)$ using a last-occurrence array. [00:06:43]

## Related Problems:
- [Maximum Swap (LeetCode 670)](https://leetcode.com/problems/maximum-swap/)
- [Smallest Number in One Swap](https://www.geeksforgeeks.org/problems/smallest-number-in-one-swap/)

## Keywords:
largest number in one swap, maximum swap leetcode, greedy string algorithms, swap digits to maximize number, geeksforgeeks potd java, string manipulation interview questions.

---

**SEO Tags:** #GreedyAlgorithms #Strings #Sorting #DSA #GeeksforGeeks #CodingInterview #Java #Python #ProblemSolving #OBrutus #TechInterview

**Learning Outcomes:**
- Applying selection sort logic to optimization problems.
- Handling string immutability in Java.
- Understanding why rightmost swaps are preferred in lexicographical optimization.