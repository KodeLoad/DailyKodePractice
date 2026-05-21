/*
    do not include 
    any terminal one's ie, on the edge
    
    we don't want don't count that remove it
    consider the rest
*/

import java.util.*;

class Solution {
    int cntOnes(int[][] a) {
        // x and y
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] == 1) {
                queue.offer(new int[] {i, 0});
            }
            if (a[i][a[i].length - 1] == 1) {
                queue.offer(new int []{i, a[i].length - 1});
            }
        }
        
        for (int j = 0; j < a[0].length; j++) {
            if (a[0][j] == 1) {
                queue.offer(new int[] {0, j});
            }
            
            if (a[a.length - 1][j] == 1) {
                queue.offer(new int[] {a.length - 1, j});
            }
        }
        
        // I've marked all the terminal elements
        bfs(a, queue);
        
        int cnt = 0;
        for (int i = 1; i < a.length - 1; i++) {
            for (int j = 1; j < a[i].length - 1; j++) {
                if (a[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    cnt += bfs(a, queue);
                }
            }
        }
        
        return cnt;
    }
    
    int bfs(int[][] a, Queue<int[]> queue) {
        int cnt = 0;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            
            if (a[x][y] == 0) {
                continue;
            }
            
            cnt++;        // cnt
            a[x][y] = 0; // set as visited
            
            // add the adj
            int[] dirs = new int[] {0, 1, 0, -1};
            for (int i = 0; i < dirs.length; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[(i+1)%dirs.length];
                if (
                    nx < 0 || nx >= a.length ||
                    ny < 0 || ny >= a[nx].length
                ) {
                    continue;
                }
                
                if (a[nx][ny] == 1) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        
        return cnt;
    }
    
}
