package Leetcode.FindGreatestCommonDivisorOfArray;

class Solution {
    public int findGCD(int[] a) {
        int min, max;
        min = max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (min > a[i]) min = a[i];
            if (max < a[i]) max = a[i];
        }

        return gcd(min, max);
    }

    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
