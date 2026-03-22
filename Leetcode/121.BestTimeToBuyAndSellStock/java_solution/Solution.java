package Leetcode.BestTimeToBuyAndSellStock.java_solution;

class Solution {
    public int maxProfit(int[] a) {
        int min = a[0];
        int profit = 0;

        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i]);
            profit = Math.max(profit, a[i] - min);
        }

        return profit;
    }
}
