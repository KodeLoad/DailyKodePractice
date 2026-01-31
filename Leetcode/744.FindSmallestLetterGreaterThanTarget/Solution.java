package Leetcode.FindSmallestLetterGreaterThanTarget;

class Solution {
    public char nextGreatestLetter(char[] a, char target) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            left = (a[mid] <= target) ? mid + 1 : left;
            right = (a[mid] <= target) ? right : mid - 1;
        }

        return a[left % a.length];
    }
}