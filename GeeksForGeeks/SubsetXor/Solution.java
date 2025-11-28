package GeeksForGeeks.SubsetXor;

/*
    examples:
        n = 4
        [1, 4]
        
        0001
        0010
        0011
        0100
        ------
        0100 :: 4
        
        
        TC:: 2**n :: possiblity O(n) :: O(2**n) ? 
        
        n = 3
        1 -> n
        0001
        0010
        0011
        ----
        0000 :: 0011
        ++-- :: ignore bits
        
        
        [1 -> 3] 
        why this is because 3 would negate all of them
        
        that means there exist something from the range
        [1 -> n]
            which is not allowing
            
        
        
        n = 5
        [1, 5]
        
        0001
        0010
        0011
        0100
        0101
        ------
        0001 :: expected 0101
        +-++ :: bit named 0100 so 4 should not be included
            everything from 1 to n expect 4
            1, 2, 3, 5
            
        0001 ^ 0101 => 0100
        
        0100 0110 0010
        
        result :: 
            a b c d e f g h i j == x
            a b c d e == x

*/
class Solution {
    public static ArrayList<Integer> subsetXOR(int n) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        
        int xor = 0;
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        
        // happiest case
        if (xor == n) {
            for (int i = 1; i <= n; i++) {
                res.add(i);
            }
            return res;
        }
        
        // we need to figure the odd person
        // the bits where result dosen't match
        var ignore = xor ^ n;
        for (int i = 1; i <= n; i++) {
            if (i != ignore) {
                res.add(i);
            }
        }
        
        return res;
    }
}
