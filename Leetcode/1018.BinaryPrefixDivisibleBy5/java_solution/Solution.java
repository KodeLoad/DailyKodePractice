import java.util.*;

/**
    [0..i] :: direct conversion giver err?
    0/1
    len(1, 0, 0, 1) = 32
    int limit = 4 byte = 32 bits

    // int / long

    1, 0, 1, 0 keep comming in
    to create a number from the stream is not big deal

    % 5 or not?

    0, 5, 10, 15, 20
    0 - 5
    5 - 10
    10 - 15

    to understand :: number lies where from 
    0 to 5

    1000 % 5 = on zero
    104 % 5 = 4
    105 % 5 = 0 its true


    2 things
    not to store the direct number
        1
        1, 0
        1 << 1(position)
        1_
        1_ + 0 = 10

        1, 0, 1
        10 << 1
        10_ | 1
        101 => 5 it is divisible
    keep checking just between 5
        number 
        to keep bet 0 to 5
        number % 5
 */

class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();

        int runningNumber = 0;
        for (int bit : nums) {
            runningNumber = ((runningNumber << 1) | bit);
            runningNumber = runningNumber % 5;

            res.add(runningNumber == 0);
        }

        return res;
    }
}
