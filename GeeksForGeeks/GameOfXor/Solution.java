/*

xor::
    
    a ^ a = 0
    a ^ 0 = a
    
    a ^ a ^ a ^ a = 0
    a ^ a ^ a ^ a ^ a = a
    
    even :: xor = 0
    odd  :: xor = a

    how many times does a number occur in sub-array?
    
    [a[0] ... a[i] ... a[n-1]] at index i
        left       right
    how many times a[i] occurs in subarrays?
    
        left  :: 0 -> i         => i+1 (i - 0 + 1)
        right :: i -> n - 1     => n-i ((n-1) - i + 1)

    count of a[i] = (n-i) * (i+1)
*/

class Solution {
    public int subarrayXor(int[] a) {
        int n = a.length;
        int xor = 0;
        
        for (int i = 0; i < n; i+=2) {
            // know the count
            int cnt = (n-i) * (i+1);
            if (cnt % 2 != 0) {
                xor ^= a[i];
            }
        }
        
        return xor;
    }
}

