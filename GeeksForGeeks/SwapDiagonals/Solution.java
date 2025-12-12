package GeeksForGeeks.SwapDiagonals;

/*

    len = n
    row 0 :         [0 n-1]
    row 1 :         [1 n-2]
                    [2 n-3]
*/

class Solution {
    public void swapDiagonal(int[][] matrix) {
        int l = 0, r = matrix.length - 1;
        
        for (int[] row : matrix) {
            // swap  l && r
            int t = row[l]; row[l] = row[r]; row[r] = t;
            
            // updates
            l++; r--;
        }
    }
}