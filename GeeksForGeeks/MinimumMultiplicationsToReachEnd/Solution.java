package MinimumMultiplicationsToReachEnd;

import java.util.LinkedList;
import java.util.Queue;

/*
    bf:
        try all 
        2 :: take / not take
        2 ** n
        start -> end
        
    other:
        start -> end
        multipler = x
        start * x = end (%1000)
        end % start == 0
        
    
    try out limited optioins
        3 -> 30
        
        2       5   7
        *       *
        3(s)    3
        |       |
        6*------15 -----> 90....
        
        DFS :   -> why? ...... %1000
        BFS :   would work
                has accountibility of each step
        
*/
class Solution {
    public int minSteps(int[] a, int start, int end) {
        if (start == end) {
            return 0;
        }
        
        // [val, step]
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        
        boolean[] visited = new boolean[1000];
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int val = cur[0];
            int step = cur[1];
            
            if (val == end) {
                return step;
            }
            visited[val] = true;
            
            
            // try every element within the array
            for (int n : a) {
                int x = n * val;
                x %= 1000;
                
                if (x == end) {
                    return step + 1;
                }
                
                if (visited[x]) {
                    continue;
                }
                
                visited[x] = true;
                queue.offer(new int[]{x, step+1});
            }
        }
        
        return -1;
    }
}
