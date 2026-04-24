package GeeksForGeeks.BuildingsWithSunlight;

/*
    6, 2, 8, 4, 11, 13
       -     -
    len = 6
    no  = 2
    rem = 4 = (6 - 2)
*/
class Solution {
    public int visibleBuildings(int a[]) {
        int no = 0;
        int max = a[0];

        for (int cur : a) {
            if (max > cur) {
                // cannot see
                no++;
            } else {
               max = cur;
            }
        }

        return a.length - no;
    }
}
