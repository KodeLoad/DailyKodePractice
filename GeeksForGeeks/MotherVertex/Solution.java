/*

    mv : ability to reach every node
    
    node 0 -> n: (n)
        if reaches or not rest of all (n)
            (n*n)
            
    
    
        a   b   c   d   e   f
        *       *           *
            *           *
        new means unvisited:: n
        
    node 0 -> n:
        already visited node (only once visit)
        keep a marking where you have visited and not
        and keep track of last node which you have seen
    
    
    check for the last candidate
        return node
    

    return mother vertex
    return the smallest if multiple     // keep aside 4 while
    return -1 if none
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int findMotherVertex(int V, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.putIfAbsent(edge[0], new HashSet<>());
            map.get(edge[0]).add(edge[1]);
        }
        
        boolean[] vis = new boolean[V];
        int candidate = -1;
        for (int i = 0; i < V; i++) {
            if (vis[i]) continue;
            dfs(map, i, vis);
            candidate = i;
        }
        
        // candidate should reach each node
        Arrays.fill(vis, false); // reset
        dfs(map, candidate, vis);
        for (boolean x : vis) {
            if (!x) return -1;
        }
        
        return candidate;
    }
    
    void dfs(
        Map<Integer, Set<Integer>> map,
        int i, boolean[] vis
    ) {
        if (vis[i]) {
            return;
        }
        
        vis[i] = true; // mark it
        if (!map.containsKey(i)) {
            return;
        }
        
        for (int adj : map.get(i)) {
            dfs(map, adj, vis);
        }
    }
}
