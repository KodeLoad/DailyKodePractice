package Leetcode.MaximizeHappinessOfSelectedChildren.java_solution;

import java.util.Arrays;

/**
    Consider the max elements

    > rest all decrements by one?
        this is my ith element and then
        I will reduce the value by i

        0 -> -0
        1 -> -1
        2 -> -2

    return the score
 */
class Solution {
    public long maximumHappinessSum(int[] a, int k) {
        final int n = a.length;
        long score = 0;
        Arrays.sort(a);
        for (int i = 0; i < k; i++) {
            // the last ement as we want max?
            int curScore = a[n - i - 1] - i;

            score += Math.max(0, curScore);
        }

        return score;
    }
}
