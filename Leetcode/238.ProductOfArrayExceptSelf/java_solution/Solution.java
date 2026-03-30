package Leetcode.ProductOfArrayExceptSelf.java_solution;

import java.util.Arrays;

/*
    BF:
        TC: O(n*n)
        SC: O(1)

    Division:
        mul = muliply(array)
        a[i] -> res[i] = mul/a[i];
        TC: O(n)
        SC: O(1)
        // do not use division operator

    
    prefix-suffix array:
        TC: O(n)
        SC: O(n + n) = O(n)

    optimized prefix-suffix array:
        TC: O(n)
        SC: O(1)

*/
class Solution {
    public int[] productExceptSelf(int[] a) {
        final int n = a.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);    // default value is 1

        // prefix populate
        for (int i = 1; i < a.length; i++) {
            res[i] = res[i-1] * a[i-1];
        }

        // back populating
        int right = 1;
        for (int i = n-2; i >= 0; i--) {
            right *= a[i+1];
            res[i] *= right;
        }

        return res;
    }
}
