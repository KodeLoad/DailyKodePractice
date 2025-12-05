package Leetcode.CountPartitionsWithEvenSumDifference.java_solution;

class Solution {
    public int countPartitions(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] % 2;
        }
        if (sum % 2 == 1) {
            return 0;
        }

        return a.length - 1;
    }
}
