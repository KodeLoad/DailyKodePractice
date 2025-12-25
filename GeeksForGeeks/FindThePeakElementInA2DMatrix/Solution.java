package GeeksForGeeks.FindThePeakElementInA2DMatrix;

/*

    What does a Peak Mean to you?
    local maxima could also work
    
    u, d, l, r <= cur
    
    
    ^^^X^^^^
    left and right and checkout where the peak can be
    and if you get the peak just walk to that direction
    

    any mid column / element could work!!
    
    0 ------------- m
            *
            
    may be -> 
        the lower row or the upper row can be greater
        
        N
    W   x   E
        S
        
    till the point you are at correct row
    once you fix the row you check W or E 
    ie, left or right!!!
    
*/
class Solution {
    public ArrayList<Integer> findPeakGrid(int[][] a) {
        ArrayList<Integer> res = new ArrayList<>();
        
        int l, r;
        l = 0; r = a[0].length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int row = getMaxRow(a, mid);
            
            int left = mid-1 < 0?
                Integer.MIN_VALUE : a[row][mid-1];
            int right = mid+1 >= a[0].length?
                Integer.MIN_VALUE : a[row][mid+1];
            
            int max = Math.max(left, right);
            if (max <= a[row][mid]) {
                res.add(row);
                res.add(mid);
                return res;
            }
            
            if (a[row][mid] < left) {
                // focus on left
                r = mid - 1;
            } else {
                // focus on right
                l = mid + 1;
            }
        }
        
        // never should occur
        return res;
    }
    
    int getMaxRow(int[][] a, int col) {
        int row = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i][col] > a[row][col]) {
                row = i;
            }
        }
        return row;
    }
}
