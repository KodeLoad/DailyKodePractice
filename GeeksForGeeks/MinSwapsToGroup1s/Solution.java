package GeeksForGeeks.MinSwapsToGroup1s;

/*
    1 0 1 0 1 1

    group all one's
    1 1 1 1 0 0
    0 0 [1 1 1 1] <--- actual

    minimum swaps
    window of 4 one's

    swap means?
    0 -> 1
    min swap
    min zero's
    within the window

    1 0 1 0 1 1
    +-----+     :: count zero = 2
      +-----+   :: count zero = 2
        +-----+ :: count zero = 1 // min count 
*/
class Solution {
    public int minSwaps(int[] a) {
        // possible?
        // window size
        int oneCount = Arrays.stream(a).sum();
        int window = oneCount;

        if (window == 0) {
            return -1;
        }

        // min zero in window
        int res = Integer.MAX_VALUE;
        int zeros = 0;
        // initial window
        int l = 0, r = 0;
        for (r = 0; r < window; r++) {
            zeros += 1 - a[r]; // +1 if a[r] is zero
        }
        res = Math.min(zeros, res);

        // l -> r
        while (r < a.length) {
            if (a[r] == 0) {
                // inculcating yet another zero
                zeros++;
            }

            if (a[l] == 0) {
                // removing another zero
                zeros--;
            }

            res = Math.min(zeros, res);

            // advance pointers
            l++; r++;
        }

        return res;
    }
}
