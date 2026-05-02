package GeeksForGeeks.PositionOfTheSetBit;

/*
    only one set bit?
    
    0   0   0   0   1   0   0   0
    .....           !   .....
    (bin)
  ...  1   0   0   0   => n
            -   1
   ---------------
  ... 0   1   1   1   => (n-1)
    
    n & (n-1) => 0 // number has only one active bit
    
    
    1           1
    10          2
    100         4
    1000 ....   8 ...
    
    powers of 2
    
    2^x => n
    x?
    x = log2(n)
        log(n) / log(2)
    
    return pos.
*/
class Solution {
    public int findPosition(int n) {
        if ((n & (n-1)) != 0) {
            return -1;
        }
        
        
        return (int)(Math.log(n) / Math.log(2)) + 1;
    }
}
