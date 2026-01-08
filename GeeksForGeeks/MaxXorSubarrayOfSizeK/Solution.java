package GeeksForGeeks.MaxXorSubarrayOfSizeK;

/*
    BF
        TC: O(n**2) 
        SC: O(1)

    app:
        array of size k
        for max
        s  e
        0 k-1 // done
            s  e
            1  k
                2  k+1

        start + 1 then end + 1

        we remove the last from the previous window
        and we add the new incoming to cur window

        sliding window
            we adjust the windows as per the movement
            left -> right
            I'm removeing left
            and I'm adding right

            in terms of xor
*/

class Solution {
    public int maxSubarrayXOR(int[] a, int k) {
        int max = 0;
        int cur = 0;

        // the first window
        for (int i = 0; i < k; i++) {
            cur ^= a[i];
        }

        // we have covered the first window
        int start = 1;
        for (int end = k; end < a.length;) {
            max = Math.max(max, cur);
            // slide the window
            // remove left
            // window is start - end
            cur ^= a[start - 1]; // removal
            cur ^= a[end]; // addition

            start++;
            end++;
        }

        return Math.max(max, cur);
    }
}
