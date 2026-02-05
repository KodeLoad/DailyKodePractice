package GeeksForGeeks.MaximizeNumberOf1s;

/*
bruteforce
    *        *
       *     *
          ...
             *           *
    1, 0, 0, 1, 0, 1, 0, 1

    TC: O(n**2)

Optimized:
    step 1: convert 0 -> 1
        start :: 0 -> n
        end   :: 0 -> n
        
            sta         end        
             *           *
    1, 0, 0, 1, 0, 1, 0, 1

    step 2: to limit your conversion till k
        now keep track of converted
        remove my conversion
        shrink the window
        move the start pointer

*/
class Solution {
    public int maxOnes(int a[], int maxConversion) {
        int start = 0, end = 0;
        int converted = 0;
        int max = 0;
        
        while (end < a.length) {
            // step 1: convert 0 -> 1
            if (a[end] == 0) {
                converted += 1;
            }
            
            // step 2: to limit your 
            // conversion till maxConversion
            while (converted > maxConversion) {
                if (a[start] == 0) {
                    // it was converted
                    converted -= 1;
                }
                start++;
            }
            
            end++;
            max = Math.max(max, end - start);
        }
        
        return max;
    }
}
