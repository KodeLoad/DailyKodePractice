package GeeksForGeeks.CountSubarraysWithGivenXOR;

/*

    a ^ a => 0
    a ^ 0 => a

---------

k = 6

    -1  0   1
        4   2   2   6   4       :: array
    0   4   6   4   2   6       :: xor (cumulative)
    ---------
                -----
        -------------
    ----------------------                    
    
    cur xor
    target? xor ^ k

    only count should be provided
*/
class Solution {
    public long subarrayXor(int a[], int k) {
        int count = 0;
        int xor = 0; // cum xor
        
        // xor -> count
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        for (int i = 0; i < a.length; i++) {
            xor ^= a[i];
            int target = xor ^ k;
            
            count += map.getOrDefault(target, 0);
            
            map.put(xor, map.getOrDefault(xor, 0) + 1);
        }
        
        return count;
    }
}

