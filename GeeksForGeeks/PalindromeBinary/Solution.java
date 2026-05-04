package GeeksForGeeks.PalindromeBinary;

/*
appraoch 1:
    take out the bbin rep
    store as string
    check palindrome
    (showcase much of your bit magic skills)
    
    string(bin(n)) == string(bin(n)).reverse()
    
approach 2:
    bit ops

    n = 5
        1* 0  1 
    
        1  0  1*
        -------
        1* 0  1 (r)
           ^  $         
              
        
        store:: 
                | 1 |       one bit    _ -> 1
                | 1 0 |     two bits _ _ -> 1 0
                
                1 make place for one bit next
                n << 1
                
                1 0 1*
                
            
        1 1 0
        ^ 
        ->
            0
            0 _
            0 1
            0 1 _    
            0 1 1
        
*/
class Solution {
    public boolean isBinaryPalindrome(int n) {
        int rev = 0;
        int ori = n;
        
        // manipulate n
        while (n > 0) {
            // make space for new bit in reverse
            rev = rev << 1;
            
            // add a bit from n to reverse
            // last bit gettiingi via (n & 1)
            rev = rev | (n & 1);
            
            // update to remove numbers from right
            n = n >> 1;
        }
        
        return rev == ori;
    }
}
