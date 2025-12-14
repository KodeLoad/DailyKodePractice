/*
    count needs to be returned

         0  1  2  3
    a : [2, 1, 6, 4]
    
    @1 ::
        0   1   2
        2   6   4
        even :: 2 + 4 => 6
        odd  :: 6
        inc cnt by 1

    ----------------------------
    
    naive : 
        for all elemtn in array
            remove an element
            shift it and check the sum
        
        O(n**2)
    
    better app :
        for all elements:
            re computing the sum?
            can we reduce this complexity
        
i       0   1   2   3    
a       2   1   6   4
even    2   2   8   8  :: array
odd     0   1   1   5  :: array

// quickly compute sum
odd index sum for index 0 to 3 : [entire array]
    5

odd index sum for index 2 to 3: [parital]
    odd[3] - odd[2-1]
        5  - 1 => 4

even index sum for index 2 to 3: [parital]
    even[3] - even[2-1]
        8   - 2 => 6



    for element : array
        # element is being removed
        
        even odd sum 
            lhs 
            lhs 
        
        even odd sum
            rhs
            rhs
*/

class Solution {
    public int cntWays(int[] a) {
        int[] even = new int[a.length];
        int[] odd = new int[a.length];
        even[0] = a[0];
        odd[0] = 0;
        
        for (int i = 1; i < a.length; i++) {
            odd[i] = odd[i-1];
            even[i] = even[i-1];
            
            if (i % 2 == 0) {
                even[i] += a[i];
            } else {
                odd[i] += a[i];
            }
        }
        
        int cnt = 0;
        // now check one by one eleminating
        for (int i = 0; i < a.length; i++) {
            // trying to remove ith index
            
            int eLhs = i==0? 0 : even[i-1];
            int oLhs = i==0? 0 : odd[i-1];
            
            // operated to alternate
            int eRhs = odd[a.length - 1] - odd[i];
            int oRhs = even[a.length - 1] - even[i];
            
            if ((eLhs + eRhs) == (oLhs + oRhs)) {
                cnt++;
            }
        }
        
        return cnt;
    }
}
