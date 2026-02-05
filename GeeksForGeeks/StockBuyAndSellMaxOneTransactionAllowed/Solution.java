package GeeksForGeeks.StockBuyAndSellMaxOneTransactionAllowed;

/*
    buy the stock in min value
    
    [any] sell in the max
    
        profit ...
        maxProfit
        
*/
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minBuy = prices[0];
        
        for (int day = 1; day < prices.length; day++) {
            if (prices[day] < minBuy) {
                minBuy = prices[day];
            }
            
            maxProfit = Math.max(
                maxProfit,
                prices[day] - minBuy
            );
        }
        
        
        return maxProfit;
    }
}
