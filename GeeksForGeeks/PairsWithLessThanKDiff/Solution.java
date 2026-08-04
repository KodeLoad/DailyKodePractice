package PairsWithLessThanKDiff;

import java.util.*;

/*
    k = 3
    [1, 10, 4, 2]

bf:
    [1, 10, 4, 2]
        i
                j
    
    TC: O(n*n)
    
    comparing each element here
    
app1:
    sort it up
    k = 3
    1   2   4   10  ....
    i       j   (4-1) => 3
        i   j
        
        i :: 0 -> n
            j :: i+1 -> n
    TC: O(n*n)
    
app2:
    i :: 0 -> n
        j :: i+1 -> n
    
    k=3
    1   2   3   4   10
    i           j
    
        i   j       :: app2
        
        i       j (diff > k) for small number
                            why check small again
        
    
    a[j]  a[i]    
    3   -   1 < k (3)
    3   -   2 < k 
    
    i :: 0 -> n
    j :: 0 -> n
*/  
class Solution {
    public static int countPairs(int a[], int k) {
        int cnt = 0;
        
        // l -> r
        Arrays.sort(a);
        int l, r;
        final int n = a.length;
        
        l = 0; r = 1;
        // 0th and 1th
        for (l=0; l < n; l++) { // l :: 0 -> n
            // r :: 1 -> n
            while (r < n && a[r] - a[l] < k) {
                // well and good i can increase the pointer
                r++;
            }
            
            // check and update count
            cnt += r - l - 1;
        }
        
        return cnt;
    }
}
