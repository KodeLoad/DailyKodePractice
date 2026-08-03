package MaxSumSubarrayOfSizeAtLeastK;

/*
    k = 3
    [1, -2, 2, -3]
         i
                j
    TC: O(n * n)
    
    diff app
    more efficient
    
    k = 3
    1 -2 2 -3
    *  * *
    l    r
    
        l && r  -> 
        only r then l
        I'm free to move right
    
    k = 2    
    1 1 1 1 1 1
    l         r -> 6
    
    as I like?
        -> I wanted to be greatest
        
        k=2
        -4, -2, 1, -3   
        l    r          :: size == 2
        l       r       :: size == 3
                    I can 
                    should I?
        last          
                    will removing -4 benifit me?
            l   r       :: size == 2 but, sum = -1 (-2 + -1) 
            l       -3  :: size == 3 
            last
                    will removing the last 
                                    -2 benifit me?
                                    
                                    
        l -> r
        0 -> n
        TC: O(n)
*/
class Solution {
    public int maxSumWithK(int[] a, int k) {
        int l, r;
        int last;
        
        l = 0;
        r = 0;
        last = 0;
        
        int sum = 0;        // current scan
        for (int i = 0; i < k; i++) {
            sum += a[i];
        }
        
        int maxSum = sum;   // ultimate scan
        
        // hard part
        // i start from k till the end?
        for (l = 0, r = k; r < a.length; r++) {
            sum += a[r];
            last += a[l];
            l++;
            maxSum = Math.max(maxSum, sum);

            // remove the last or not to calculate sum?
            if (last < 0) {
                // -ve value
                // remove this
                sum -= last;
                last = 0;
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}
