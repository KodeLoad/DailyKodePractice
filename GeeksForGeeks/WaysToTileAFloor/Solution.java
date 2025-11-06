/*

    2 x n
    
    
    * * * * ...
    * * * * ...

    i * * * ...
    i * * * ...
    
    _ _ * * ...
    - - * * ...
    
    fill():
        populate in vertical fashion
        fill rest again -- recusrsive call 
        
        populate in horizontal fashion
        fill rest again -- recursive call
        
        // sum up the both

    
    how do we move?
    - vertically 
        placing at ith position -> i+1
    - horizontally
        placing at ith position -> i+2
        
    * * * * ...
    * * * * ...
    
    2*4
    * * * *
    * * * *
    1 2 3 5 ...
    
    (consider placing verticals + horizontals) 
                way(i-1)  + way(i-2)
                
    
    - - || i i
    - - || i i
    
    
    // O(n)tc : O(1)sc
*/


class Solution {
    public int numberOfWays(int n) {
        if (n <= 3) return n;
        
        int prePrevious = 2;
        int previous = 3;
        for (int i = 4; i <= n; i++) {
            int x = prePrevious + previous;
            prePrevious = previous;
            previous = x;
        }
        
        return previous;
    }
};