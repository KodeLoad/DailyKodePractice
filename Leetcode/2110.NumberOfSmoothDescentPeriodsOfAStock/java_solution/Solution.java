package Leetcode.NumberOfSmoothDescentPeriodsOfAStock.java_solution;

/**

    i -> j
        :: (cnt++)
    O(n**2)

 The period is continouse

    3   2   1   4
    i
        j   j   j
        i
            j   j

    do I currently am I in period or not?
    a[i-1] - a[i] == 1 => yes / no

    3   2   1   4
    +   +   +   -

    3           :: cnt = 1  // 3;
        2       :: cnt = 3  // 3; 2; 3,2; (+2)
            1   :: cnt = 6  // 3; 2; 3,2; 1; 2,1; 3,2,1; (+3)

    a number which is within the continus period
    would contribute to rest all continus preiod elements

    1 : 
        2nd element
        +2
    
    cotinus as 
    [3, 2]
        now I see 1
    cp = 2
    cp++ => 3

    3
    
    // take 2
    2
    3 2 // end of 2

    // take 1
    1
    2 1
    3 2 1 // end of 1

    now the count would be increased by cp (+3)
    


 */
class Solution {
    public long getDescentPeriods(int[] prices) {
        long cnt = 1;
        long cp = 1;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i-1] - prices[i] == 1) {
                // in the period
                cp++;
            } else {
                // not in period
                cp = 1;
            }

            cnt += cp;
        }

        return cnt;
    }
}