class Solution {
public:
    int maxProfit(vector<int>& prices) 
    {
        int maxProfit=0;
        int minBuyPrice=prices[0];

        for(int i=1; i<prices.size(); ++i)
        {
            maxProfit=max(prices[i]-minBuyPrice, maxProfit);
            minBuyPrice=min(minBuyPrice, prices[i]);
        }

        return maxProfit;    
    }
};
