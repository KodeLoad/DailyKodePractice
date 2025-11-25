/**
    k
    n % k == 0 (to find)
    the constraint :: n consist of only 1's

    n can be:
        1
        11
        111
        1111
        11111 ....

    for any value of k which is even we cannot get n
    for k like 5, 10, 15, 20 ... (k % 5 == 0) 
    res = -1

    for the rest?
    3, 7, 21, ...

    what does it takes to be a module and
    how can we figure out it incrementally

go inremental by:
    1 -> 11 -> 111 -> 1111
    n = n*10 + 1

what does it takes be mod?
    (n % k) == 0
    0, 1, 2, 3, 4, ... (k-1)

    we can have inremental numbers
    let's go ahead with understand the mod
    and update the inremental numbers with result of mod
 */
class Solution {
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }

        int n = 1;
        int len = 1;
        // for incremental behaviour of n
        for (int i = 0; i < k && n % k != 0; i++) {
            n = n * 10 + 1;
            n = n % k;
            len++;
        }

        return len;
    }
}
