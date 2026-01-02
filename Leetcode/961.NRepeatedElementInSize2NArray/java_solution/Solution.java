/**
    BF:
        for i in a[:] :: x
            for elemn in a[i:end] :: y
                if x == y? return x
    TC: O(n**2)
    SC: O(1)

    set/hashmap
    TC: O(n)
    SC: O(n)

    modify the array:
        constrain: no altering the array
    TC: O(n)
    SC: O(1)
    
            *
    1   2   3   3           :: 2
          | -----

    2   1   2   5   3   2   :: 3
    *       *           *

    array is of size 2*n
    and repetation is n

    best case or the worst case

    n | rest of array
    rest of array | n

    * 
          i
        n * n * n * n
        (2*n-1)
            the element we ignore was target
                let our itr check that ignore
            else target is in the rest of list
                then => 
                check for the repeation 
                within the n-1
                2*n-1 rep=>n


        you have 5 seates
        and 3 people

        _ _ _ _ _ (2n -1)
        * * *
          *   * *
           
        *   *   * (n)

        considering not one but next 2 elemnts?

        else you initially choose 2 elemnts?
 */

class Solution {
    public int repeatedNTimes(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = 1; j <= 3 && i + j < nums.length; j++) {
                if(nums[i] == nums[i+j]) return nums[i];
            }
        }
        return 0;
    }
}

