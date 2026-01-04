/*
    BF:
        for i
            for j
                i to j :: sub array
                sum
        TC: O(n**2)
        
    app:
        k = 4
        0   1 2   3   4
        1, 4, 2, 10, 23, 3, 1, 0, 20
        -----------   N         :: s1
        X  ------------         :: (s1 - X) + N => s2
              ------------
              
        i = @4 I'm sure that the size is complete
            now remove last 
            i - k
              
        sum for array of size k
        
        roll the sum over and over
            remove the last 
            and the new
        
        keep track of maxSum found so far
        also don't lose track of curSum

*/
class Solution {
    public int maxSubarraySum(int[] a, int k) {
        int maxSum = 0;
        int curSum = 0;

        for (int i = 0; i < a.length; i++) {
            // cur sum is empty
            // the size is zero
            // no element is considered
            // so condier atleast k elemetns?
            if (i < k) {
                curSum += a[i];
                continue;
            }
            
            maxSum = Math.max(maxSum, curSum);
            
            // I'm sure to have k element
            // remove the last 
            curSum -= a[i-k];
            
            // and the new
            curSum += a[i];
        }
        
        return Math.max(maxSum, curSum);
    }
}
