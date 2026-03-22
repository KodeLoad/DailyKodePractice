package GeeksForGeeks.RottenOranges;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*

    time required
    step required need to calculated

    rotten oranges :: source

    medium of fresh oranges
    if empty cell no infection

    check if all oranges are rotten -> what is step

*/

class Solution {
    public int orangesRot(int[][] a) {
        // BFS
        if (a == null || a.length == 0) {
            return -1;
        }

        // rotten oranges :: source
        Queue<int[]> source = new LinkedList<>();
        // [x, y, dis]
        int[][] dis = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            Arrays.fill(dis[i], -1);
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] != 2) continue;
                source.add(new int[]{i, j, 0});
            }
        }

        // source is filled
        while (!source.isEmpty()) {
            int[] cur = source.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];

            if (dis[x][y] != -1 && dis[x][y] < d) {
                continue;
            }

            dis[x][y] = d;
            a[x][y] = 2; // marked infected

            // up, down, left, right
            int[] dx = new int[]{-1, 1,  0, 0 };
            int[] dy = new int[]{ 0, 0, -1, 1 };
            for (int k = 0; k < 4; k++) {
                int i = x + dx[k];
                int j = y + dy[k];
                if (i < 0 || i >= a.length 
                    || j < 0 || j >= a[i].length
                ) {
                    continue;
                }

                if (a[i][j] == 0) {
                    continue;
                }

                source.add(new int[]{
                    i, j, d + 1
                });
            }
        }

        // verfiy step
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 1) {
                    return -1;
                }
                res = Math.max(res, dis[i][j]);
            }
        }

        return res;
    }
}
