/*
    BF:
        i -> j
        0   1   2   3
        i
                    j
                    
        O(n * n)
    
    app:
    l = 4   ;   r = 13
    
        0   1   2   3
        2   3   5   8
                i
                    j
        
        sum = 13    :: take sum down?
        [2, 3]
        [2, 3, 5]
        [5, 8]

        3 -> 8
        
        0  1  2
        1, 4, 6
              i
              j
        
        1,4
        6
        
        array
        target
            -> I will give you the count sub arrays 
               having sum <= target
*/
class Solution {
    public int countSubarray(int[] a, int l, int r) {
        return lessTargetCount(a, r)    // all nums < r
            - lessTargetCount(a, l-1);   // all nums < l-1
        /*   
            0 ---------- r
            0 ---l-1
                   l-----r
       */
    }
    
    int lessTargetCount(int[] a, int target) {
        int sum = 0;
        int l, r; l = 0;
        int cnt =0;
        
        for (r = 0; r < a.length; r++) {
            sum += a[r];
            
            while (l < a.length && sum > target) {
                sum -= a[l];
                l++;
            }
            
            cnt += r - l + 1;
        }
        
        return cnt;
    }
}
