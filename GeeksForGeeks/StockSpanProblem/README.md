# Stock Span Problem
---

> Video Solution: [https://youtu.be/xSLukNHqB_E](https://youtu.be/xSLukNHqB_E)

[Problem](https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/xSLukNHqB_E/0.jpg)](https://youtu.be/xSLukNHqB_E)

---

**Difficulty:** Medium  
**Accuracy:** 43.53%  
**Submissions:** 180K+  
**Points:** 4  
**Average Time:** 30m

The stock span problem is a financial problem where we have a series of daily stock prices and we need to calculate the span of the stock’s price for all days. The span of the stock’s price on a given day $i$ is defined as the maximum number of consecutive days (including the current day) just before that day, for which the price of the stock on the current day is less than or equal to its price on the given day.

## Examples:

**Example 1:**
<code>
Input: arr[] = [100, 80, 60, 70, 60, 75, 85]
Output: [1, 1, 1, 2, 1, 4, 6]

Explanation: 
- Day 0 (100): Span is 1.
- Day 3 (70): 70 is > 60, so span is 2 (70 and 60). [00:01:38]
- Day 5 (75): 75 is > 60, 70, 60, so span is 4.
</code>

**Example 2:**
<code>
Input: arr[] = [10, 4, 5, 90, 120, 80]
Output: [1, 1, 2, 4, 5, 1]
</code>

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(N)

## Topic Tags:
Stack | Data Structures | Monotonic Stack

---

## Approach: Monotonic Stack

### 1. The Brute Force Limitation [00:02:44]
A naive $O(N^2)$ approach would involve checking all previous days for every single day. This will lead to **Time Limit Exceeded** for $10^5$ elements.

### 2. The Monotonic Stack Intuition [00:06:09]
We only need to find the **Nearest Greater Element to the Left**. Once we find that "violating" index, the span is simply <code>current_index - violating_index</code>. If no such element exists, the span is <code>current_index + 1</code>. [00:09:51]

**Algorithm:** [00:08:11]
1. Use a **Stack** to store indices of elements in a strictly decreasing order of their prices.
2. For each new price at <code>index i</code>:
    - Pop from the stack while the price at the stack's top is **less than or equal to** the current price. [00:09:05]
    - If the stack becomes empty, the span is <code>i + 1</code>. [00:10:28]
    - If the stack is not empty, the span is <code>i - stack.peek()</code>. [00:11:18]
    - Push the current <code>index i</code> onto the stack. [00:11:34]

---

## Visualizing the Logic: [00:03:55]
<code>
Prices = [100, 80, 60, 70]
Stack (Indices)

1. i=0, Price=100: Stack empty -> Span = 1. Stack = [0]
2. i=1, Price=80: 80 < 100 -> Span = 1-0 = 1. Stack = [0, 1]
3. i=2, Price=60: 60 < 80 -> Span = 2-1 = 1. Stack = [0, 1, 2]
4. i=3, Price=70: 
   - 70 > 60 (Pop index 2)
   - 70 < 80 (Stop)
   - Span = 3-1 = 2. Stack = [0, 1, 3]
</code>

---

## Implementation (Conceptual):
<code>
def calculateSpan(price, n):
    stack = [] # Stores indices
    span = [0] * n
    
    for i in range(n):
        # Pop elements with smaller or equal price
        while stack and price[stack[-1]] <= price[i]:
            stack.pop()
            
        if not stack:
            span[i] = i + 1
        else:
            span[i] = i - stack[-1]
            
        stack.append(i)
    return span
</code>

---

## Key Takeaways:
- **Why it's O(N):** Each element is pushed and popped exactly once. [00:12:53]
- **Monotonic Property:** The stack always contains indices of prices in a strictly decreasing order. [00:12:18]
- **Indices over Values:** Storing indices in the stack is more powerful than storing values because indices allow us to calculate distances (spans) easily.

## Related Problems:
- [Next Greater Element](https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)
- [Largest Rectangular Area in Histogram](https://www.geeksforgeeks.org/problems/largest-rectangular-area-in-histogram-1587115620/1)
- [Trapping Rain Water](https://www.geeksforgeeks.org/problems/trapping-rain-water-1587115621/1)

## Keywords:
stock span problem, monotonic stack, nearest greater to left, gfg potd, stack data structure, financial problem dsa, O(n) solution, interview preparation.

---

**SEO Tags:** #Stack #MonotonicStack #DataStructures #DSA #GeeksforGeeks #StockSpan #Java #Python #AlgorithmOptimization

**Learning Outcomes:**
- Mastering the Monotonic Stack pattern for "nearest" element problems.
- Converting distances between array elements into meaningful spans.
- Handling empty stack edge cases for global maximums.
