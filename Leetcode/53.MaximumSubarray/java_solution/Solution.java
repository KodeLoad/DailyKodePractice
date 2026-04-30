package Leetcode.MaximumSubarray.java_solution;

enum ApproachType {
    KadaneAlgorithm,
    DivideAndConqure
}

public class Solution {
    final ApproachType approach = ApproachType.KadaneAlgorithm;

    public int maxSubArray(int[] nums) {
        switch(approach) {
            case ApproachType.KadaneAlgorithm:
                return new DivideAndConqureSolution().maxSubArray(nums);
            case ApproachType.DivideAndConqure:
                return new DynamicProgrammingSolution().maxSubArray(nums);
        }

        throw new UnsupportedOperationException();
    }

}

/**

    o o o | o o o 

    o o | o


    sum is where?
    complete left
    complete right
    across the middle point
*/
class DivideAndConqureSolution {
    public int maxSubArray(int[] nums) {
        return find(nums, 0, nums.length-1);
    }

    int find(int[] a, int l, int r) {
        if (l == r) {
            return a[l];
        }
        int mid = l + (r-l)/2;
        int left = find(a, l, mid);
        int right = find(a, mid + 1, r);
        int across = across(a, l, mid, r);

        return Math.max(across, Math.max(left, right));
    }

    /*
        -------!---------
               ^
            <-
                  ->
    */
    int across(int[] a, int l, int mid, int r) {
        int leftSuf = Integer.MIN_VALUE, rightSuf = Integer.MIN_VALUE;
        int running;

        running = 0;
        for (int i = mid; i >= l; i--) {
            running += a[i];
            leftSuf = Math.max(leftSuf, running);
        }

        running = 0;
        for (int i = mid + 1; i <= r; i++) {
            running += a[i];
            rightSuf = Math.max(rightSuf, running);
        }

        return leftSuf + rightSuf;
    }
}

/*


        -2    1    -3    4    -1    2    1    -5    400


    where should I stop?
    if the sum does not help you please stop 
        if the sum is -ve then it wont help

*/
class DynamicProgrammingSolution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;

        for (int num : nums) {
            sum += num;
            if (res < sum ) {
                res = sum;
            }
            if (sum < 0) {
                sum = 0;
            }
        }

        return res;
    }
}
