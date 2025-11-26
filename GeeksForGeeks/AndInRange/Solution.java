// i : (l -> r)

/*
    bit magic
    -----------
    
    AND : 1 1 => 1 else zer0
        Consistent one's 
        any number anding with zer0 is zer0
    
    LSB : least significant (rhs)
    MSB : most significant (lhs)
    
    example: 8 -> 13
        
        1000 :: 8
        1001
        1010
        1011   -> 1011
        1100
        1101
        ----
        1000   -> 1000
        
        lsb (0th) : flips alternatively
            (1th) : flips after 2
            (2nd) : flips after 4
            
        if 2nd position is flipping then 
        i'm sure that first position is zero
        
        taking out the previous bit
        if it is yes then good I can test further

        take the MSB via : log2(n)
        
        check if this MSB is consistent:
            if this is not: return 0
            
            if this is consistent
                we can take this consistency
                then check from number beyond msb
                1100 -> 1 | check(100)
*/
class Solution {
    public int andInRange(int l, int r) {
        if (l == 0 || r == 0) {
            return 0;
        }
        
        int msbl = 1 << (int)(Math.log(l) / Math.log(2));
        int msbr = 1 << (int)(Math.log(r) / Math.log(2));
        
        if (msbl != msbr) {
            return 0;
        }
        
        /*
            we can take this consistency
            then check from number beyond msb
            1100 -> 1 | check(100)
        */
        
        return msbl | andInRange(l ^ msbl, r ^ msbl);
    }
}
