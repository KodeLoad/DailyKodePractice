package GeeksForGeeks.SearchInserPtositionOfKInASortedArray;

class Solution {
    public int searchInsertK(int a[], int k) {
        // code here
        // int x = Arrays.binarySearch(a, k);
        // return x >= 0? x : -x-1;
        
        // implementation!!
        /*
            [1, 3, 5, 6]
            k = 2
             0  1  2  3
             l        r
            [1, 3, 5, 6]
            
             l  r   
            [1, 3, 5, 6]
            k = 2
            mid = 0
            l = mid + 1;
            r = mid - 1;
            
            can I move ?
            l && r
            
            
            prior to jumping I wanted 
            to understand the best values for 
            L
        */
        
        int l = 0;
        int r = a.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a[mid] == k) {
                return mid;
            }
            
            if (a[mid] > k) {
                // search in lower
                // parts of array
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return l;
    }
}
