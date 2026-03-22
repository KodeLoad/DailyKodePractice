# Best Time to Buy and Sell Stock
---

> Video description: https://youtu.be/COJ-KY7DIOs

[Problem](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/)

[![img](https://img.youtube.com/vi/COJ-KY7DIOs/0.jpg)](https://youtu.be/COJ-KY7DIOs)

---

**Difficulty:** Easy  
**Acceptance Rate:** 54.8%  
**Submissions:** 6.2M+  
**Accepted:** 3.4M+  
**Topics:** Array | Dynamic Programming  
**Average Time:** 15m

You are given an array `prices` where `prices[i]` is the price of a given stock on the i<sup>th</sup> day.

You want to maximize your profit by choosing a **single day** to buy one stock and choosing a **different day in the future** to sell that stock.

Return the **maximum profit** you can achieve from this transaction. If you cannot achieve any profit, return `0`.

## Examples:

**Example 1:**
```
Input: prices = [7,1,5,3,6,4]
Output: 5

Explanation: 
Buy on day 2 (price = 1) and sell on day 5 (price = 6)
Profit = 6 - 1 = 5

Note: Buying on day 2 and selling on day 1 is not allowed 
because you must buy before you sell.
```

**Example 2:**
```
Input: prices = [7,6,4,3,1]
Output: 0

Explanation: 
In this case, no transactions are done and max profit = 0.
Prices keep decreasing, so we can't make any profit.
```

## Constraints:
- 1 ≤ prices.length ≤ 10⁵
- 0 ≤ prices[i] ≤ 10⁴

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Google | Microsoft | Facebook | Apple | Bloomberg | Adobe | Uber | Goldman Sachs | Oracle

## Topic Tags:
Array | Dynamic Programming | Greedy | Kadane's Algorithm

## Approach:

### Brute Force (TLE):
```
maxProfit = 0
for i from 0 to n-2:
    for j from i+1 to n-1:
        profit = prices[j] - prices[i]
        maxProfit = max(maxProfit, profit)
return maxProfit
```

**Complexity:** O(n²) - Too Slow!

### Optimal: One Pass Solution

**Key Insight:**
- Track minimum price seen so far
- Calculate profit if selling today
- Update maximum profit

**Algorithm:**
```
minPrice = infinity
maxProfit = 0

for price in prices:
    // Update minimum buying price
    minPrice = min(minPrice, price)
    
    // Calculate profit if selling today
    profit = price - minPrice
    
    // Update maximum profit
    maxProfit = max(maxProfit, profit)

return maxProfit
```

**Complexity:** O(n) Time, O(1) Space - Optimal!

### Visual Understanding:
```
prices = [7, 1, 5, 3, 6, 4]

Day 0: price=7
  minPrice = 7
  profit = 7-7 = 0
  maxProfit = 0

Day 1: price=1
  minPrice = min(7,1) = 1 ← Best buy point!
  profit = 1-1 = 0
  maxProfit = 0

Day 2: price=5
  minPrice = 1
  profit = 5-1 = 4
  maxProfit = 4

Day 3: price=3
  minPrice = 1
  profit = 3-1 = 2
  maxProfit = 4

Day 4: price=6
  minPrice = 1
  profit = 6-1 = 5 ← Best profit!
  maxProfit = 5

Day 5: price=4
  minPrice = 1
  profit = 4-1 = 3
  maxProfit = 5

Answer: 5 (Buy at 1, Sell at 6)
```

### Why This Works:

**Greedy Strategy:**
```
For maximum profit:
- Buy at lowest price before selling day
- Sell at highest price after buying day

By tracking minimum so far:
- We ensure we buy at best price available
- Each day, we check profit if selling today
- Naturally satisfies "buy before sell" constraint
```

**Mathematical Proof:**
```
Optimal profit = max(prices[j] - prices[i]) where i < j

For each j:
  Best i = min(prices[0..j-1])
  
By tracking running minimum:
  We always have best buying price for current selling day
```

### Step-by-Step Trace:
```
prices = [7, 6, 4, 3, 1]

Day 0: minPrice=7, profit=0, maxProfit=0
Day 1: minPrice=6, profit=0, maxProfit=0
Day 2: minPrice=4, profit=0, maxProfit=0
Day 3: minPrice=3, profit=0, maxProfit=0
Day 4: minPrice=1, profit=0, maxProfit=0

Answer: 0 (Prices always decreasing)
```

### Edge Cases:

1. **Single day:** Cannot make transaction
   - Input: [5]
   - Output: 0

2. **Decreasing prices:** No profit possible
   - Input: [5,4,3,2,1]
   - Output: 0

3. **Increasing prices:** Best to buy first day, sell last
   - Input: [1,2,3,4,5]
   - Output: 4 (5-1)

4. **All same prices:** No profit
   - Input: [3,3,3,3]
   - Output: 0

5. **Two elements:** Simple case
   - Input: [2,5]
   - Output: 3

### Common Mistakes:

1. **Selling before buying**
```
   Wrong: Check prices[i] - prices[j] where i > j
   Right: Only calculate profit for future days
```

2. **Not tracking minimum properly**
```
   Wrong: Reset minPrice when finding new max
   Right: Always track global minimum so far
```

3. **Returning negative profit**
```
   Wrong: Return prices[j] - prices[i] (could be negative)
   Right: Initialize maxProfit = 0, never go below
```

4. **Using extra space**
   - Don't need arrays to track all minimums/profits

### Implementation:
```python
def maxProfit(prices):
    min_price = float('inf')
    max_profit = 0
    
    for price in prices:
        min_price = min(min_price, price)
        profit = price - min_price
        max_profit = max(max_profit, profit)
    
    return max_profit
```
```java
public int maxProfit(int[] prices) {
    int minPrice = Integer.MAX_VALUE;
    int maxProfit = 0;
    
    for (int price : prices) {
        minPrice = Math.min(minPrice, price);
        int profit = price - minPrice;
        maxProfit = Math.max(maxProfit, profit);
    }
    
    return maxProfit;
}
```

### Pattern Recognition:

**This is Kadane's Algorithm variant:**
```
Maximum Subarray: Find max sum subarray
Stock Problem: Find max difference with constraint

Both use:
- Single pass
- Track running minimum/maximum
- Update global result
```

### Why O(1) Space:
```
Only need to track:
1. Current minimum price (1 variable)
2. Maximum profit so far (1 variable)

No need for:
- Arrays of previous minimums
- Arrays of profits
- Dynamic programming table
```

### Optimization Notes:

**Early Termination (Optional):**
```python
# If prices only decrease, can return 0 early
if all(prices[i] >= prices[i+1] for i in range(len(prices)-1)):
    return 0

# But this adds O(n) check, not worth it
```

**Best Practice:**
- Simple one-pass solution is already optimal
- Clean, readable, and efficient

### Related Variations:

**Multiple Transactions:**
- Best Time to Buy and Sell Stock II (LeetCode 122)
- Unlimited transactions allowed

**With Cooldown:**
- Best Time to Buy and Sell Stock with Cooldown (LeetCode 309)

**With Fee:**
- Best Time to Buy and Sell Stock with Transaction Fee (LeetCode 714)

**K Transactions:**
- Best Time to Buy and Sell Stock IV (LeetCode 188)

## Related Problems:
- Maximum Subarray (LeetCode 53)
- Best Time to Buy and Sell Stock II (LeetCode 122)
- Best Time to Buy and Sell Stock III (LeetCode 123)
- Best Time to Buy and Sell Stock IV (LeetCode 188)
- Maximum Difference Between Increasing Elements (LeetCode 2016)

## Related Articles:
- Kadane's Algorithm
- Dynamic Programming Basics
- Greedy Algorithms
- Array Optimization Techniques
- Stock Trading Problems

## Keywords:
best time buy sell stock, maximum profit stock, kadane algorithm, single transaction profit, leetcode easy, stock market algorithm, buy sell stock leetcode, array maximum difference, greedy algorithm, dynamic programming

---

**SEO Tags:** #Array #DynamicProgramming #Greedy #KadaneAlgorithm #StockProfit #LeetCode #Easy #FAANG #Interview #MaximumProfit

**Problem Category:** Array, Greedy, Dynamic Programming

**Difficulty Level:** Easy (Must-know problem)

**Prerequisites:**
- Basic Array Iteration
- Min/Max Operations
- Understanding of Constraints

**Learning Outcomes:**
- Master single-pass optimization
- Understand Kadane's pattern
- Greedy algorithm thinking
- Constraint-based problem solving

**Interview Frequency:** Extremely High (Classic question)

**Real-world Application:**
- Stock trading analysis
- Financial algorithms
- Profit optimization
- Time-series analysis

**Key Technique:** Track minimum price and maximum profit in single O(n) pass with O(1) space
