package Leetcode.PartitioningIntoMinimumNumberOfDeciBinaryNumbers.java_solution;

/**

    3 2 4
    1 1 1
    1 1 1
    1 0 1
        1
   -----
    3 2 4

 */

class Solution {
    public int minPartitions(String n) {
        return n.chars()
            .max()
            .getAsInt() - '0';
    }
}
