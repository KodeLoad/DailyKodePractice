/**

    you have to have

        - find the divisors
        - only 4 divisors

BF:
    1 -> n :: each element and check div
    span is big
    every elemnt to iterate
    
better:
    21: 1, 3, 7, 21
    how the diviors are?
        occur in pairs!!

        i -> 1
        hey 1 is divisible by 21
            1, 21
        
        i, n/i

    6:
        2
        i dont want you it iterate furthe 
    and tell me what is yet another divisor

        n=6, i=2 => 6/2 => 3
        next : 3 for 2
        6: 2,3 ;; 1,6 ;; 
    
    I know how find pair elemnt?
    till what point I have to look?

    1 -> n
    1 ---------- | ----------- n
               sqrt(n)

        n -> i (i = sqrt(n))
        i * i = n
        so overall only 1 elemnt contribution
        as pair is of same numbers.

        count exactly 4

        apart for i every other divisor 
        is in pairs (even number)

        even number + 1 => odd number
        odd number of count of divisor
            4
        1, 4 ;; 2 ;; -> 3 count 
        
 */

class Solution {
    // it returns sum of divisors
    // only if divisrs count == 4
    int getAptSum(int n) {
        int sum = 0;
        int cnt = 0;

        int sqrt = (int)Math.sqrt(n);
        // if n is perfect square
        if (sqrt * sqrt == n) {
            return 0;
        }

        for (int i = 1; i <= sqrt; i++) {
            if (n % i != 0) continue;
            
            // divisible
            sum += i + (n/i);
            cnt += 2;

            if (cnt > 4) return 0;
        }

        return cnt == 4? sum : 0;
    }

    public int sumFourDivisors(int[] nums) {
        int sum = 0;

        for (int i : nums) {
            sum += getAptSum(i);
        }

        return sum;
    }
}
