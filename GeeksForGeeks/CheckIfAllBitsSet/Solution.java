package CheckIfAllBitsSet;

/*
    bf :: each of the bits -> O(n)
    
    O(1)::
        7
            111 :: expected
             +1
           1000
           
           1111111  :: n
          10000000  :: n+1
          00000000
          
          
            1010    :: n
            1011    :: n+1
            1010    :: false
            
*/
class Solution {
    public boolean isBitSet(int n) {
        if (n == 0) {
            return false;
        }
        
        return (n & (n+1)) == 0;
    }
}
