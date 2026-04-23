package GeeksForGeeks.TwoEqualSumSubarrays;

import java.util.Arrays;

/*
    example
    0 ------>
    1, 2, 3, 4, 5, 5
              |
        s1       s1

        s1 + s1 = 2s1 = sum

        sum => 
        target (s1) = sum / 2;


    two parts

    0   1   2   ....    n-1     n
            |------------|
*/
class Solution {
    public boolean canSplit(int a[]) {
        int sum = Arrays.stream(a).sum();
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        int runner = 0;

        for (int n : a) {
            runner += n;
            if (runner == target) {
                return true;
            }
        }

        return false;
    }
}
