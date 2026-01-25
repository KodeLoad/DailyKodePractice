package GeeksForGeeks.NumberOfValidParentheses;

import java.util.Arrays;

/*
    n:
        2**n
            (   :: opt 1
            )   :: opt 2
        
    
    n = 2
            2, 2
        (           
       1, 2
    (       )
    ((      ()
    0, 2    1, 1
            
            
    n = 3
            3,3
        2,3
    2,2     1,3
        1,2
    
    
    op = clo 
    n/2 && n/2
    

    TC: O(n)
    SC: O(n*n)
*/
class Solution {
    int findWays(int n) {
        if (n % 2 == 1 || n <= 0) {
            return 0;
        }
        n /= 2;
        
        int[][] dp = new int[n+1][n+1];
        for (int[] x : dp) {
            Arrays.fill(x, -1);
        }
        
        return count(dp, n, n);
    }
    
    int count(int[][] count, int open, int close) {
        if (count[open][close] != -1) {
            return count[open][close];
        }
        
        if (open == 0 && close == 0) {
            return count[open][close] = 1;
        }
        
        int cnt = 0;
        
        // (
        if (open > 0) {
            cnt += count(count, open-1, close);
        }
            
        // )
        if (close > 0 && close > open) {
            cnt += count(count, open, close-1);
        }
        
        return count[open][close] = cnt;
    }
}
