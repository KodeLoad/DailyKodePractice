package Leetcode.CheckIfBinaryStringHasAtMostOneSegmentOfOnes.java_solution;

class Solution {
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
