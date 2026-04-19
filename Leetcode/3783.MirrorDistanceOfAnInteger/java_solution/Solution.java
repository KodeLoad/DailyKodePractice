package Leetcode.java_solution.MirrorDistanceOfAnInteger;

class Solution {
    public int mirrorDistance(int n) {
        return Math.abs(Integer.parseInt(
            new StringBuilder(n+"").reverse().toString()
        ) - n);
    }
}

