package DailyKodePractice.GeeksForGeeks.MoveAllZeroesToEnd;

/*
    if non zero -> fill else not fill

    // no extra array

sample:

    1, 2, 0, 4, 3, 0, 5, 0
          *        *     *
             i
          l

          move 4(i) and zero(l) (swap)
    1, 2, 4, 0, 3, 0, 5, 0
             i
          l

    l should follow zeros
    1, 2, 4, 3, 0, 0, 5, 0
                      i
                l

*/
class Solution {

    void pushZerosToEnd(int[] a) {
        int l = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                swap(a, i, l);
            }

            if (a[l] != 0) {
                l++;
            }
        }
    }

    void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
