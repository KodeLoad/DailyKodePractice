class Solution {
    static int findPerimeter(int[][] a) {
        int sum = 0;
        
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != 1) continue;
                sum += dfs(a, i, j);
            }
        }
        
        return sum;
    }
    
    static int dfs(int[][] a, int i, int j) {
        if (
            i < 0 || i >= a.length ||
            j < 0 || j >= a[i].length
        ) {
            return 1;
        }
        
        if (a[i][j] != 1) {
            return a[i][j] == -1? 0 : 1;
        }
        
        a[i][j] = -1;
        
        int sum = 0;
        int[] d = new int[]{0, 1, 0, -1};
        
        for (int x = 0; x < d.length; x++) {
            sum += dfs(a, i + d[x], j + d[(x+1)%4]);
        }
        
        return sum;
    }
}
