package GeeksForGeeks.SortInSpecificOrder;

import java.util.Arrays;

/*
partition :: 
    o o o o o ? ? ? ? e e
              l         r
    
sorting ::
    I can very well sort the partitions
    
*/

class Solution {
    public void sortIt(int[] a) {
        // parition
        int l = 0;              // insertion point odd
        int r = a.length - 1;   // insert even point
        
        while (l <= r) {
            if (a[l] % 2 == 0) {
                // even
                swap(a, l, r);
                r--;
            } else {
                // odd
                // swap(a, l, l);
                l++;
            }
        }
        
        // my array is paritioned
        // odd  : [0 -> r]
                    // [0, r+1)
        // even : [r+1 -> a.length - 1]
                    // [r+1, a.length)
        
        
        // sorting
        Arrays.sort(a, 0, r+1);         // ascending
        Arrays.sort(a, r+1, a.length);
        
        // reverse 0, r
        l = 0;
        while (l < r) {
            swap(a, l, r);
            l++; r--;
        }
    }
    
    void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
