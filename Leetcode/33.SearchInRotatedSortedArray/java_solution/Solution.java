package Leetcode.SearchInRotatedSortedArray.java_solution;

/*
bin search? 
    l && r

    l                       r
                m
    4   5   6   7   0   1   2
*/
class Solution {
    public int search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    int search(int[] a, int l, int r, int x) {
        if (l > r) {
            return -1;
        }

        int mid = l + (r - l)/ 2;

        if (a[mid] == x) {
            return mid;
        }

        if (a[l] <= a[mid]) {
            // l -> mid
            if (a[l] <= x && x <= a[mid]) {
                return search(a, l, mid-1, x);
            } else {
                return search(a, mid+1, r, x);
            }
        } else {
            // mid -> r
            if (a[mid] <= x && x <= a[r]) {
                return search(a, mid+1, r, x);
            } else {
                return search(a, l, mid-1, x);
            }
        }
    }
}
