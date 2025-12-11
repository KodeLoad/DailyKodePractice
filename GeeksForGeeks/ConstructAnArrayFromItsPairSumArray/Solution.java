
/**
 * 
 *  pair sum::
 *  
 *      inpt(res) ?? -> output is give (arr)
 *  
 *      res[
 *          0   1   2   3   4
 *      ]    :: size = n
 * 
 * 
 * op:
 *      arr[
 *          0(0+1)   1(0+2)   2(0+3)....
 *      ]   :: size = n * (n-1) / 2
 * 
 *      // assumption : arr is valid in size
 * 
 */
 
 
 /*
    arr / a : [4, 5, 3] a[2] = res[n-1] + res[1]
    res     : [3, 1, 2] 
 */

/*
    a = [contains only sums]
    a = [s01, s02, s03, .... s[n-1 && 1]]
    
    s01 = res[0] + res[1]   // 1
    s02 = res[0] + res[2]   // 2
    s12 = res[1] + res[2]   // 3

    // from eq. 1 and 3 (eq1 - eq3)
    s01 - s12 = res[0] - res[2]  // 4
    
    res[2] = res[0] - s01 + s12  // input in eq2
    s02 = res[0] + res[0] - s01 + s12
    2 * res[0] = s02 + s01 - s12
    
    res[0] = (s02 + s01 - s12) / 2 // huge win !!
    now with this res[0]
    I will calculate res array
    
    res[0] + res[1] = s01
    res[0] + res[2] = s02
    res[0] + res[i] = s0i
    
    res[i] = s0i - res[0]
           = a[i-1] - res[0]
    
    
    how to calculate the res array
    
    a.length = n * (n-1) / 2
    where n = length of array res
    
    find for n given a.length (= c)
    c = n * (n - 1) / 2
    n**2 - n - 2c = 0
    
    n = ?
    
*/

class Solution {
    public ArrayList<Integer> constructArr(int[] a) {
        ArrayList<Integer> res = new ArrayList<>();
        if (a.length == 1) {
            res.add(1);
            res.add(a[0] - 1);
            return res;
        }
        
        final int n = (int) (1 + Math.sqrt(1 + 8*a.length) / 2);
        
        res.add((a[1] + a[0] - a[n-1]) / 2);
        for (int i = 1; i < n; i++) {
            res.add(a[i-1] - res.get(0));
        }
        
        return res;
    }
}
