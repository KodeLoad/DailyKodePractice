package GeeksForGeeks.MeetingRooms;

/*
    attain all meet
    i
        j (all rest)
    TC: O(n**2)

    overlap then strict no
    
    [1, 4], [10, 15], [7, 10]
    
    1-4 -> 7-10 -> 10-15
    start first
    end first
*/

class Solution {
    static boolean canAttend(int[][] a) {
        Arrays.sort(a, (ii, jj) -> ii[1] - jj[1]);
        
        int[] last = a[0];
        for (int i = 1; i < a.length; i++) {
            if (last[1] > a[i][0]) {
                // this overlaps
                return false;
            }
            last = a[i];
        }
        
        return true;
    }
}
