package GeeksForGeeks.SortBySetBitCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
    numbers -> 
        buckets:
        
    bitcount        32      31      30
    numbers         List    List    List
        
        32 
        
        
        Segregate
        Merge
    
    ----
    Sorting:
        Senior
        5+

*/
class Solution {
    public ArrayList<Integer> sortBySetBitCount(int[] a) {
        ArrayList<Integer> list = Arrays.stream(a)
                                .boxed()
                                .collect(
                                    java.util.stream.Collectors.toCollection(
                                        ArrayList::new
                                    )
                                );
        
        Collections.sort(
            list,
            (_i, _j) -> Integer.bitCount(_j) - Integer.bitCount(_i)
        );
        
        
        return list;
    }
}


