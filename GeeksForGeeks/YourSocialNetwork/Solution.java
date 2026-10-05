package GeeksForGeeks.YourSocialNetwork;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
(2 ≤ i ≤ n) has exactly one friend

arr[i - 2] is the friend of user i.

 2  3(1+2)
[1, 2]
2 -> 1
3 -> 2

3 -> 2 -> 1
3 -> 1 [2]

rechable 
and jump

*/
class Solution {
    public ArrayList<ArrayList<Integer>> socialNetwork(
        int[] a
    ) {
        // code here
        // src -> adj friend
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(i+2, a[i]);
        }
        
        
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        for (int i = 2; i < a.length+2; i++) {
            dfs(map, i, i, res, 1);
        }
        
        return res;
    }
    
    
    void dfs(
        Map<Integer, Integer> map,
        int ori, int cur,
        ArrayList<ArrayList<Integer>> res,
        int jump
    ) {
        var adj = map.get(cur);
        if (adj == null) return;
        
        // get to the depths
        dfs(map, ori, adj, res, jump+1);
        
        // state save
        ArrayList<Integer> list = new ArrayList<>();
        list.add(ori); list.add(adj); list.add(jump);
        res.add(list);
    }
}

/*

    2 -> 1
    3 -> 2
    4 -> 3
------
    2 -> 1
    3 -> 2 -> 1 ::
        3, 2, 1
        3, 1, 2
    
    4 -> 3
        4, 3, 1
           2, 2 (1 + 1)
           1, 3 (2 + 1)
*/


