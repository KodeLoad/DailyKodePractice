import java.util.*;
/*
    a b c d
    i
      j
     
    i && j
TC: O(n*n)
    
    
    [100, 56, 5, 6, 102, 58, 101, 57, 7, 103, 59]
    
    100 .....
    56  .....
    5   .....
    
    find the start of the consicative number list
    find?
        a[i] = 100
        find(a[i] - 1)
            -> scan array
            -> hash map type lookup
            
    TC: O(n)
    SC: O(n)
*/

class Solution {
    int minSubsets(int a[]) {
        Set<Integer> set = new HashSet<>();
        for (int i : a) set.add(i);
        
        int cnt = 0;
        
        for (int i = 0; i < a.length; i++) {
            if (!set.contains(a[i] - 1)) {
                // it is the start
                cnt++;
            }
        }
        
        return cnt;
    }
}
