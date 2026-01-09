package GeeksForGeeks.SubarraysWithAtMostKDistinctIntegers;

import java.util.HashMap;
import java.util.Map;

/*
    optimal:
        k = 2
        0  1  2  3
        1, 2, 2, 3
        -------
        
        1 2
        1; 2; 1,2 => cnt = 3
        
        0 1 2
        1 2 2
        1; 2; 2; 1,2; 2,2; 1,2,2; => 6
        
        from 3 -> 6
        on addition of any element which is valid
        you consider the entire array and sum it up
        (2 -> 0) :: 3 (end - start + 1)
        
        How do I compute with duplicates?
        HashMap<> value -> frequency
        
        start -> end pointer
        valid sub array :: 
            count of how many can happen
*/
class Solution {
    public int countAtMostK(int a[], int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int cnt = 0;

        int start = 0;
        for (int end = 0; end < a.length; end++) {
            map.put(
                    a[end],
                    map.getOrDefault(a[end], 0) + 1);

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
