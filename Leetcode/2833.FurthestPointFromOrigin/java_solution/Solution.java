package Leetcode.FurthestPointFromOrigin.java_solution;

/**
    L_RL__R
    -?+-??+

    - & +
    ? :: bonus, amplify
 */
public class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int mov = 0; // iniitially it iis at origin
        int bonus = 0;

        for (char ch : moves.toCharArray()) {
            if (ch == 'L') {
                mov--;
            } else if (ch == 'R') {
                mov++;
            } else {
                bonus++;
            }
        }

        return Math.abs(mov) + bonus;
    }
}