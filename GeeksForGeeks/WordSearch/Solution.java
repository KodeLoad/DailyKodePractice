package GeeksForGeeks.WordSearch;

/*
    DFS: (yes / no) preffered
    
    BFS: (count)
    
    ---------------
    
    0, 0 -> 0, 1 or 1, 0
    s[0] -> s[1]

*/

class Solution {
    public boolean isWordExist(char[][] a, String s) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != s.charAt(0)) {
                    continue;
                }
                if (dfs(a, s, i, j)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(
        char[][] a, String s, 
        int i, int j
    ) {
        if (s.length() == 0) return true;
        
        if (i < 0 || i >= a.length 
            || j < 0 || j >= a[i].length
        ) {
            return false;
        }
        
        if (s.charAt(0) != a[i][j]) {
            return false;
        }
        
        char ch = a[i][j];
        a[i][j] = '*';
        
        boolean res = false;
        
        res |= dfs(a, s.substring(1), i+1, j);
        if (res) return true;
        res |= dfs(a, s.substring(1), i-1, j);
        if (res) return true;
        res |= dfs(a, s.substring(1), i, j+1);
        if (res) return true;
        res |= dfs(a, s.substring(1), i, j-1);
        
        a[i][j] = ch;
        return res;
    }
}
