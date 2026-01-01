package Leetcode.66.PlusOne.java_solution;

/*
   (1)
    6 9
    + 1
-------------
    7 0

- take last digits and add
- understand the carry-over and the value
- add (carry, next digit2, next digit1)

*/

class Solution {
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        int carry = 0;
        int initialOne = 1;

        while (i >= 0) {
            int sum = carry + digits[i] + initialOne;
            initialOne = 0;

            digits[i] = sum % 10;
            carry = sum / 10;

            i--;
        }

        if (carry == 0) {
            return digits;
        }

        int[] res = new int[digits.length + 1];
        res[0] = carry;

        for (i = 1; i < res.length; i++) {
            res[i] = digits[i-1];
        }

        return res;
    }
}

