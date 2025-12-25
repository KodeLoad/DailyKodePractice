package GeeksForGeeks.KthMissingPositiveNumberInASortedArray;

/*
    bf:
        2 iterators
            1 for array
            1 for range
        keep track of missing elements
        miss == k => return the element
        o(max(k, n))
    

    binary search:
        1   2   3   4   5 :: i +1
        2   3   4   7   11 :: array
misses: 1   1   1   3   6 (11 - 5)
                    k=5
                    +2 numbers
                    7 + 2 => 9

        1   2   3   4 :: range (i+1)
        1   2   3   4 :: array

    should tell us the apt position
*/
class Solution {
    public int kthMissing(int[] a, int k) {
        int l = 0, r = a.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            int missingCount = a[mid] - (mid+1);
            
            if (missingCount < k) {
                // look on right
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return (r+1) + k;
    }
}

/*
    0   1  2
    1, 2, 3
    0  0  0
        k = 2
    r = 2 => r+1 = 3
    k = 2 
*/




