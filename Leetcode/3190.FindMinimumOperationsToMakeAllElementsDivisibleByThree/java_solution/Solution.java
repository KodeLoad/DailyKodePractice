package Leetcode.FindMinimumOperationsToMakeAllElementsDivisibleByThree.java_solution;

/**
array
 :: [1,  2,  3,  4]
     *   *   0   *
     -   +       - 
     ++

    if div : 0
    not div : + / - : 1
    element : count :: 1:1
 */
class Solution {
    public int minimumOperations(int[] nums) {
        int cnt = 0;

        for (int n : nums) {
            if (n%3 != 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
