import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Solution {
    ArrayList<Integer> countDistinct(int a[], int k) {
        ArrayList<Integer> res = new ArrayList<>();
        // store the count's :: getting distinct numbers
        // number -> frequency
        Map<Integer, Integer> map = new HashMap<>();
        
        // slide the window
        // we need to adjust the map, and associated freq.
        for (int i = 0; i < k; i++) {
            // initial setup
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        
        for (int i = k; i < a.length; i++) {
            // check for the repetations
            int rep = map.size();
            res.add(rep);
            
            // remove the old
            int oldItem = a[i-k];
            if (map.get(oldItem) == 1) {
                map.remove(oldItem);
            } else {
                map.put(oldItem, map.get(oldItem) - 1);
            }
            // add the new
            int newItem = a[i];
            map.put(
                newItem, 
                map.getOrDefault(newItem, 0) + 1
            );
        }
        
        res.add(map.size());
        
        // return the result
        return res;
    }
}