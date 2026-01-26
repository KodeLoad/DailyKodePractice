package Leetcode.MinimumAbsoluteDifference.java_solution;

import java.util.*;

/**
    find min diff. of that array
        O(n**2)

    take the pairs and understand 
        qualifies or not

    sort

 */
class Solution {
    public List<List<Integer>> 
        minimumAbsDifference(int[] a) {

        List<List<Integer>> res = new ArrayList<>(); 
        Arrays.sort(a);
        int min = a[1] - a[0];
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i] - a[i-1]);
        }

        for (int i = 1; i < a.length; i++) {
            if (a[i] - a[i-1] == min) {
                res.add(List.of(a[i-1], a[i]));
            }
        }

        return res;
    }
}
