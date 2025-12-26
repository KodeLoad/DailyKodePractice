package Leetcode.MinimumPenaltyForAShop.java_solution;

/*
    prev N's
    upcoming Y's

    prefix sum

    running faschion to count Y and N

    Y and N
    score based system

    if the shop is **open** and we see
        'Y' : +ve
        'N' : -ve
    we need to check the max score !!!

    and based upon max score let's fix the best time to close!!

    0   1   2   3
    Y   Y   N   Y
    1   2   1   2
        *

    return beyond 1 I'll close
    return bestTime + 1;

          |
    0   1 | 2   3   4  5 |
    Y   Y | N   Y   Y  Y
    1   2 | 1   2   3   4
 */
class Solution {
    public int bestClosingTime(String customers) {
        int bestTime = -1;
        int score = 0;
        // char[] s = customers.toCharArray();
        // SIMPLEY CONVERTS STRING TO BYTE ARRAY
        byte[] s = customers.getBytes(
            java.nio.charset.Charset.forName("ISO-8859-1")
        );
        byte Y = (byte) 'Y';

        // let's iterate
        for (int i = 0; i < s.length; i++) {
            if (s[i] == Y) {
                // it's +ve score
                score++;
                if (score > 0) {
                    bestTime = i;
                    score = 0;
                }
            } else {
                // it's -ve score
                score--;
            }
        }

        return bestTime + 1;
    }
}
