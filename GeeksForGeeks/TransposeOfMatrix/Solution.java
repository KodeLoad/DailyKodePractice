import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> transpose(int[][] mat) {
        // must and should be a square matrix
        if (mat == null || mat.length == 0 || mat.length != mat[0].length) {
            throw new IllegalArgumentException("invalid matrix");
        }

        // core logic
        // make row element to column
        // how do we identify
        // mat[i][j]
        // i -> row element
        // j -> col element
        // now in new matrix 
        // i -> col && j -> row

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < mat.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < mat[i].length; j++) {
                list.add(mat[j][i]);
            }
            res.add(list);
        }

        return res;
    }
}