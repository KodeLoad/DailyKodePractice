# Stock Buy and Sell – Multiple Transactions
---

> Video Solution: [https://youtu.be/Wu4NBn67YxM](https://youtu.be/Wu4NBn67YxM)

[Problem](https://www.geeksforgeeks.org/problems/buy-stock-2/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/Wu4NBn67YxM/0.jpg)](https://youtu.be/Wu4NBn67YxM)

---

**Difficulty:** Medium  
**Accuracy:** 53.58%  
**Submissions:** 150K+  
**Points:** 4  
**Average Time:** 35m

The cost of stock on each day is given in an array `prices[]`. You are allowed to complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times). Your goal is to find the maximum profit you can achieve.

*Note: You must buy before you can sell.*

## Examples:

**Example 1:**
<code>
Input: prices[] = [100, 180, 260, 310, 40, 535, 695]
Output: 865
Explanation: 
Buy on day 0, sell on day 3 (310 - 100 = 210)
Buy on day 4, sell on day 6 (695 - 40 = 655)
Total Profit = 210 + 655 = 865. [00:01:01]
</code>

**Example 2:**
<code>
Input: prices[] = [4, 2, 2, 2, 4]
Output: 2
Explanation: Buy on day 3, sell on day 4 (4 - 2 = 2).
</code>

## Constraints:
- 1 ≤ prices.size() ≤ 10⁵
- 0 ≤ prices[i] ≤ 10⁴

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(1)

## Topic Tags:
Greedy | Arrays | Algorithms

---

## Approach: Greedy Strategy (Capture All Peaks)

### 1. Intuition [00:01:29]
Since we can perform multiple transactions, the best way to maximize profit is to capture every single upward price movement. If the price tomorrow is higher than the price today, we "buy" today and "sell" tomorrow. Even if the price keeps rising for several days, the sum of daily profits is mathematically equal to buying at the start and selling at the peak.

### 2. Logic [00:01:46]
1.  Initialize `maxProfit = 0`.
2.  Iterate through the `prices` array from the second day (index 1).
3.  Check if `prices[i] > prices[i-1]`.
4.  If it is, add the difference `prices[i] - prices[i-1]` to `maxProfit`. [00:03:32]
5.  By the end of the loop, `maxProfit` will contain the sum of all profitable segments.

---

## Implementation (Conceptual): [00:02:45]
<code>
public int maximumProfit(int[] prices) {
    int maxProfit = 0;
    
    for (int i = 1; i < prices.length; i++) {
        // If price is increasing, capture the profit
        if (prices[i] > prices[i - 1]) {
            maxProfit += prices[i] - prices[i - 1];
        }
    }
    
    return maxProfit;
}
</code>

---

## Key Takeaways:
- **Greedy is Optimal:** For multiple transactions, we don't need to find the absolute minimum and maximum; we just need to sum all positive slopes. [00:04:50]
- **Time Complexity:** $O(N)$ because we traverse the array exactly once. [00:04:42]
- **Space Complexity:** $O(1)$ as we only use a single variable to track total profit. [00:04:42]

## Related Problems:
- [Stock Buy and Sell – Max one Transaction Allowed](https://www.geeksforgeeks.org/problems/stock-buy-and-sell2615/1)
- [Best Time to Buy and Sell Stock with Transaction Fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

## Keywords:
stock buy and sell multiple transactions, greedy algorithm stock profit, maximize stock profit java, gfg potd stock buy sell, array greedy problems, coding interview stock problem.

---

**SEO Tags:** #Greedy #Arrays #StockMarket #Algorithms #DSA #GeeksforGeeks #CodingInterview #Java #OBrutus #ProblemSolving

**Learning Outcomes:**
- Understanding the greedy property in stock trading problems.
- Reducing complex transaction logic into simple price difference summation.
- Achieving linear time complexity for optimization problems.
