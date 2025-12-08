package GeeksForGeeks.ConstructListUsingGivenQXorQueries;

import java.util.ArrayList;
import java.util.Collections;

/*
 6
 6 3
 6 3 2
 
 6 3 2 || 4
 6 3 2 || 4 ^ 5
 6 3 2 4^5^x || 4 ^ 5
 
 6^4 3^4 2^4
 6^4^5 3^4^5 2^4^5

*/

class Solution {
    public static ArrayList<Integer> constructList(int _q, int[][] queries) {
        // code here
        int xor = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        for (int[] q : queries) {
            if (q[0] == 0) {
                // add
                int newVal = xor ^ q[1];
                list.add(newVal);
            } else {
                // xor
                xor ^= q[1];
            }
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, xor ^ list.get(i));
        }
        
        Collections.sort(list);
        
        return list;
    }
}