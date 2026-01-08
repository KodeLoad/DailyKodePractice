package GeeksForGeeks.CountSubarrayWithKOdds;

/*
    BF:
        for i 
            for j
                <i, j> :: odd 
        O(n**2)
        
    
    optimal 1 
        k = 2
        0  1  2  3
        2, 5, 6, 9
        0  1  1  2
        
        for index 0 (ie, 2) -> even number 0
        but, i dont no number chosen?
                  0 -> no choice
        
    map : (odd_count_in_array) -> freq
            0 -> 2
            1 -> 2
            2 -> 1
        
        2, 5, 6, ..
        2 -> 1                      -> result
        1 -> 1(from 5) and          -> result
        next 1 ?
        
        k = 2
        ..X...... 100 101 2003
          29      30  31
          

    optimal 2
        exactly k odd elements?
        
        exactK(k)
            atMost(K) - atMost(k-1)

            
*/

class Solution {
    int atMost(int[] a, int k) {
        if (k < 0) return 0;
        
        int start = 0;
        int end = 0;
        int oddCount = 0;
        int cnt = 0;
        
        for (end = 0; end < a.length; end++) {
            oddCount += a[end] & 1;
            
            while (oddCount > k) {
                // excessive elements 
                // shrink the window
                oddCount -= a[start] & 1;
                start++;
            }
            
            cnt += end - start + 1;
        }
        
        return cnt;
    }
    
    public int countSubarrays(int[] a, int k) {
        return atMost(a, k) - atMost(a, k-1);
    }
}





