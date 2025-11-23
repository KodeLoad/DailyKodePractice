package java_solution;
/**
arr size : n with k=k
O(n*k)
k >> n**2

we iterate more than one time.
array still is the same :: what sould we reuse

case 1:
    chalk = [5,1,5], k = 22
    sum = 11, k = 22
    1 -> 22 - 11 -> 11
    2 -> 11 - 11 -> 0

case 2:
    chalk = [3,4,1,2], k = 25
    sum = 10 k = 25
    1 -> 25 - 10 -> 15
    2 -> 15 - 10 -> 5
    rem => %
    k = 25 sum = 10
    25 % 10 => 5
    chalk = [3, ] -> 2
    chalk = [3, 4, ] -> 2 < 4 ie, 1
 */

class Solution {
    public int chalkReplacer(int[] chalks, int k) {
        if (chalks == null || chalks.length == 0) {
            return 0;
        }

        // get the remainder
        long sum = 0;
        for (int c : chalks) {
            sum += c;
        }

        // O(n)
        int rem = (int) (k % sum);

        // pin point the index
        int index = 0;
        // n - 1, didn't run or run till n-1
        while (chalks[index] <= rem) {
            rem = rem - chalks[index];
            index++;
        }
        // O(n-1) ~ O(n)

        return index;
    }
}