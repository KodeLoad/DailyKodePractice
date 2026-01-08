# Max XOR Subarray of Size K
---

> Video description: [https://youtu.be/mZ9a21gXYEE](https://youtu.be/mZ9a21gXYEE)

[Problem](https://www.geeksforgeeks.org/problems/max-xor-subarray-of-size-k/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/mZ9a21gXYEE/0.jpg)](https://youtu.be/mZ9a21gXYEE)

---

**Difficulty:** Easy  
**Accuracy:** 55.12%  
**Submissions:** 120K+  
**Points:** 2  
**Average Time:** 20m

Given an array <code>arr[]</code> of size <code>N</code> and an integer <code>K</code>, find the maximum XOR sum of a subarray of size <code>K</code>.

## Examples:

**Example 1:**
<code>
Input: N = 4, K = 3
arr[] = [2, 5, 8, 1]
Output: 15

Explanation: 
Subarrays of size 3:
[2, 5, 8] → 2 ^ 5 ^ 8 = 15
[5, 8, 1] → 5 ^ 8 ^ 1 = 12
Maximum XOR is 15. [See video @0:42](https://youtu.be/mZ9a21gXYEE?t=42)
</code>

**Example 2:**
<code>
Input: N = 3, K = 2
arr[] = [1, 2, 3]
Output: 3
</code>

## Constraints:
- 1 ≤ N ≤ 10⁵
- 1 ≤ K ≤ N
- 1 ≤ arr[i] ≤ 10⁶

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Microsoft | Samsung | Adobe | FactSet

## Topic Tags:
Arrays | Sliding Window | Bit Magic | Bit Manipulation

## Approach:

### Brute Force (TLE):
<code>
maxXor = 0
for i from 0 to N-K:
    currentXor = 0
    for j from i to i+K-1:
        currentXor ^= arr[j]
    maxXor = max(maxXor, currentXor)
return maxXor
</code>

**Complexity:** O(N × K) - [Watch explanation @1:16](https://youtu.be/mZ9a21gXYEE?t=76)

### Optimal: Sliding Window Technique (XOR Property)

**Key Insight:** [Watch explanation @8:10](https://youtu.be/mZ9a21gXYEE?t=490)
- Unlike addition, XORing an element twice cancels it out (A ^ A = 0).
- To slide: XOR out the leftmost element, XOR in the new rightmost element.
- Reuse previous calculation to keep complexity at O(N).



**Algorithm:**
<code>
// Step 1: Calculate XOR of first window [Watch @4:45]
currentXor = XOR of arr[0] to arr[K-1]
maxXor = currentXor

// Step 2: Slide window [Watch @7:20]
for i from K to N-1:
    currentXor = currentXor ^ arr[i-K] ^ arr[i]
    maxXor = max(maxXor, currentXor)

return maxXor
</code>

**Complexity:** O(N) - Optimal!

### Visual Understanding:
<code>
arr = [2, 5, 8, 1], K = 3

Window 1: [2, 5, 8]
          XOR = 15

Window 2: [5, 8, 1]
          XOR = 15 ^ 2 (remove) ^ 1 (add) = 12

Maximum = 15
</code>

### Why Sliding Window Works for XOR:
<code>
Window 1: [a, b, c]
Window 2:    [b, c, d]

Instead of re-calculating (b ^ c ^ d):
Use: (a ^ b ^ c) ^ a ^ d
Result: (b ^ c ^ d)
</code>

### Edge Cases:
1. **K = N:** Single window (full array XOR).
2. **K = 1:** Find the maximum single element.
3. **Array with 0s:** XORing 0 doesn't change the value.

### Common Mistakes:
1. **Using addition/subtraction:** XOR requires the <code>^</code> operator.
2. **Loop boundaries:** Forgetting to handle the last element correctly. [Watch @5:32](https://youtu.be/mZ9a21gXYEE?t=332)
3. **Re-calculating XOR:** Defeats the purpose of the O(N) optimization.

### Implementation (Python):
<code>
def max_xor_subarray(arr, n, k):
    current_xor = 0
    for i in range(k):
        current_xor ^= arr[i]
    
    max_xor = current_xor
    
    for i in range(k, n):
        current_xor = current_xor ^ arr[i-k] ^ arr[i]
        max_xor = max(max_xor, current_xor)
        
    return max_xor
</code>

## Related Problems:
- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)
- [Subarray with Given XOR](https://www.interviewbit.com/problems/subarray-with-given-xor/)
- [Max Sum Subarray of Size K](https://www.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1)

## Keywords:
max xor subarray, sliding window xor, bit manipulation, bit magic, fixed size window, competitive programming, geeksforgeeks solution, O(n) xor subarray.

---

**SEO Tags:** #BitManipulation #SlidingWindow #BitMagic #Array #DSA #CodingInterview #GeeksforGeeks #XOR #ProblemSolving

**Learning Outcomes:**
- Master XOR properties in a sliding window.
- Recognize fixed-window patterns.
- Optimize brute force solutions using bitwise logic.