package NextElementWithGreaterFrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
    higer freq
    
    just next
    
    freq::
        1: 3
        2: 2
        3: 1
    
    0   1   2   3   4   5   :: index                    [*]
    2   1   1   3   2   1   :: actual                   [*]
    2   3   3   1   2   3   :: freq                     [*]
    1   -   -   4   1   -   :: next index greater freq  [*]
    1   -   -   2   1   -   :: result actual            [?]
    
    
    
    100 60 3 6 10 70 101
        * -------> |
        
    100 
        60 70 101 ->
        
*/  

class Solution {
    public ArrayList<Integer> nextFreqGreater(int[] a) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i : a) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        
        // map is filled
        final int n = a.length;
        int[] nextBigIndex = new int[n];
        
        nextBigIndex[n-1] = -1;
        // freq. based next index
        for (int i = n - 2; i >= 0; i--) {
            int next = i + 1;
            
            // if next is acutlly smaller or not
            while (
                next != -1 && 
                freq.get(a[next]) <= freq.get(a[i])
            ) {
                // I've got the larger on right
                next = nextBigIndex[next];
            }
            
            nextBigIndex[i] = next;
        }
        
        
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int incoming = nextBigIndex[i];
            res.add(
                incoming == -1? -1 : a[incoming]
            );
        }
        
        return res;
    }
}
