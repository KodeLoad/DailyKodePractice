package GeeksForGeeks.Substring;

import java.util.*;


class Solution {
    public int countSubstr(String s, int k) {
        //  code here
        return countAtMostK(s.toCharArray(), k)
            - countAtMostK(s.toCharArray(), k-1);
    }
    
    public int countAtMostK(char[] a, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int cnt = 0;
        
        int start = 0;
        for (int end = 0; end < a.length; end++) {
            map.put(
                a[end],
                map.getOrDefault(a[end], 0) + 1
            );
            
            while (map.size() > k && start <= end) {
                // i've more distinct element
                // i wanted to remove some
                int updateCount = map.get(a[start]);
                // trying to remove elemnt
                // hence the count is decrmented
                updateCount -= 1;
                
                if (updateCount == 0) {
                    // no longer exist
                    map.remove(a[start]);
                } else {
                    map.put(a[start], updateCount);
                }
                
                start++;
            }
            
            // im sure that sub-array is valid
            cnt += end - start + 1;
        }
        
        return cnt;
    }
}
