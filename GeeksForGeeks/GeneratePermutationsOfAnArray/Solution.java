package GeneratePermutationsOfAnArray;

import java.util.ArrayList;

/*
    [a, b, c]
    
                [fixed]|[pool]
                
                | a, b, c
        a               b              c
    a | bc          b | ac
                    
ab | c   ac | b
abc         acb

*/


class Solution {
    public static ArrayList<ArrayList<Integer>> 
    permuteDist(int[] a) {
        // 
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        permute(a, 0, res);
        
        return res;
    }
    
    static void permute(
        int[] a, 
        int fixed,
        ArrayList<ArrayList<Integer>> res
    ) {
        if (fixed == a.length) {
            // no option
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : a) list.add(i);
            res.add(list);
            return;
        }
        
        // fixed -> end of array
        for (int i = fixed; i < a.length; i++) {
            // fixed can be sub i
            swap(a, i, fixed);
            permute(a, fixed + 1, res);
            swap(a, i, fixed);
        }
    }
    
    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
};