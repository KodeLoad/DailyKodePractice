package Leetcode.RotateFunction.java_solution;

import java.util.Arrays;

/*
    0 * a[0]
    1 * a[0] 
    2 * a[0]
    ...
    n-1 * a[0]

    array = [a, b, c, d]
    
        0a + 1b + 2c + 3d :: cur sum for this combination
            + sum of array
        1a + 2b + 3c + 0d
            + a +  b +  c +  d   // sum of array
            = 2a + 3b + 4c + 1d 
            - 4c (a.length * rem)  // removal of one such element
        2a + 3b + 0c + 1d
    
*/
class Solution {
    public int maxRotateFunction(int[] a) {
        int res = Integer.MIN_VALUE;
        int sum = 0;
        int arraySum = Arrays.stream(a).sum();

        for (int i = 0; i < a.length; i++) {
            sum += a[i] * i;
        }
        res = sum;

        for (int i = a.length - 1; i > 0; i--) {
            int remove = a[i];
            int cur = sum - (remove * a.length) + arraySum;
            res = Math.max(res, cur);
            sum = cur;
        }
        
        return res;
    }
}
