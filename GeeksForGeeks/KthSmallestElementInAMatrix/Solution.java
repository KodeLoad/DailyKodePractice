/*
bf::
    mat -> array
        TC: O(n*n * log(n*n))
        SC: O(n*n)

ap1::
    Heap / Priority queue
        TC: O(n*n)

ap2::
    binary search
        l => lowest
        r => highest
        
    understand the middle
    understand @ what position is this middle
    
*/

class Solution {
    public int kthSmallest(int[][] a, int k) {
        final int n = a.length;
        // l and r are actual values we 
        // experiment with
        int l = a[0][0], r = a[n-1][n-1];
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            int pos = getPos(a, mid);
            
            if (pos < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        
        return l;
    }
    
    // understand @ what position a element is
    int getPos(int[][] a, int x) {
        int cnt = 0;
        for (int[] row : a) {
            int i = firstOcc(row, x + 1);
            cnt += i;
        }
        return cnt;
    }
    
    int firstOcc(int[] a, int x) {
        // l and r are indices
        int l = 0, r = a.length - 1;
        
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (a[mid] > x) {
                r = mid - 1;
            } else if (a[mid] < x) {
                l = mid + 1;
            } else if (mid - 1 >= l && a[mid-1] == a[mid]) {
                r = mid - 1;
            } else {
                return mid;
            }
        }
        
        return l;
    }
}
