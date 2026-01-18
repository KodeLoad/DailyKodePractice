# Next Element With Greater Frequency
---

> Video Solution: [https://youtu.be/ROAIYsUzd90](https://youtu.be/ROAIYsUzd90)

[Problem](https://www.geeksforgeeks.org/problems/next-element-with-greater-frequency--170637/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/ROAIYsUzd90/0.jpg)](https://youtu.be/ROAIYsUzd90)

---

**Difficulty:** Medium  
**Accuracy:** 49.34%  
**Submissions:** 35K+  
**Points:** 4  
**Average Time:** 30m

Given an array `arr[]` of `n` integers. For each element in the array, find the next element (to its right) which has a **higher frequency** than the current element. If no such element exists, return -1.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 1, 2, 3, 4, 2, 1]
Output: [-1, -1, 1, 2, 2, 1, -1]

Explanation: 
Frequencies: {1: 3, 2: 2, 3: 1, 4: 1}
- For 1 (freq 3): No element to the right has freq > 3. Output: -1
- For 2 (freq 2): Next element with freq > 2 is 1 (freq 3). Output: 1
</code>

**Example 2:**
<code>
Input: arr[] = [1, 1, 1, 2, 2, 2]
Output: [-1, -1, -1, -1, -1, -1]
</code>

## Constraints:
- 1 ≤ n ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(N)

## Topic Tags:
Stack | Hash | Data Structures

---

## Approach: Hash Map + Monotonic Stack


### 1. Count Frequencies
We first need to know how many times each element appears in the array. A simple **Frequency Map** (or integer array if constraints allow) can store this.
- `freqMap[element] = count`

### 2. Monotonic Stack Logic
This is a variation of the "Next Greater Element" problem. Instead of comparing the values themselves, we compare their **frequencies**.

**Algorithm:**
1. Initialize an answer array `res[]` with -1.
2. Use a **Stack** to store the indices of elements whose "next greater frequency" element hasn't been found yet.
3. Traverse the array from left to right:
    - While the stack is not empty AND the frequency of the current element `arr[i]` is greater than the frequency of the element at the index stored at `stack.top()`:
        - We found the answer for the index at `stack.top()`.
        - `res[stack.pop()] = arr[i]`
    - Push the current index `i` onto the stack.
4. Return `res[]`.

---

## Visualizing the Logic:
<code>
arr = [1, 1, 2, 3], Freq = {1:2, 2:1, 3:1}

1. Index 0 (val 1, freq 2): Stack = [0]
2. Index 1 (val 1, freq 2): Stack = [0, 1]
3. Index 2 (val 2, freq 1): freq(2) < freq(stack top), so Stack = [0, 1, 2]
4. Index 3 (val 3, freq 1): freq(3) < freq(stack top), so Stack = [0, 1, 2, 3]

Result: [-1, -1, -1, -1] (Since no right element had freq > 2 or freq > 1)
</code>

---

## Implementation (Python):
<code>
def printNextGreaterFreq(arr, n):
    # Step 1: Count frequencies
    freq = {}
    for x in arr:
        freq[x] = freq.get(x, 0) + 1
    
    # Step 2: Monotonic Stack
    res = [-1] * n
    stack = [] # stores indices
    
    for i in range(n):
        # Compare frequencies of current vs stack top
        while stack and freq[arr[i]] > freq[arr[stack[-1]]]:
            res[stack.pop()] = arr[i]
        stack.append(i)
        
    return res
</code>

---

## Key Takeaways:
- **Monotonic Stack Pattern:** Use a stack when you need to find the "first element to the right/left" that satisfies a condition.
- **Frequency as Comparison:** The value of the element doesn't matter for the stack comparison; only the value stored in the `freq` map matters.
- **Pre-calculation:** Computing frequencies first ensures we have O(1) access to comparison values during the stack traversal.

## Related Problems:
- [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/)
- [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
- [Next Smaller Element](https://www.geeksforgeeks.org/problems/next-smaller-element/1)

## Keywords:
next greater frequency, monotonic stack, frequency map, geeksforgeeks solution, array optimization, stack data structure, O(n) solution.

---

**SEO Tags:** #Stack #MonotonicStack #Hashing #DataStructures #DSA #GeeksforGeeks #CodingInterview #Python #Java

**Learning Outcomes:**
- Applying the Monotonic Stack pattern to non-value comparisons.
- Efficiently using HashMaps for frequency counting.
- Reducing $O(N^2)$ brute force to $O(N)$ linear time.
