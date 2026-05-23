package GeeksForGeeks.LastCoinInAGameOfAlternates;

class Solution {
    public int coin(int[] a) {
        int l, r;
        l = 0; r = a.length - 1;

        while (l < r) {
            if (a[l] > a[r]) {
                // at l it is greater coin
                l++;
            } else {
                r--;
            }
        }

        return a[r];
    }
}
