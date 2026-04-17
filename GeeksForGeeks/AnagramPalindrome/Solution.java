package GeeksForGeeks.AnagramPalindrome;

/*
    dad, mom
    sis, bro -> orb
    
    ...
    
    abba :: every char is even in count
    or atleast one is odd
    
    abcba
    abcda
*/
class Solution {
    boolean canFormPalindrome(String s) {
        // count of odd should be == 1 at max
        int[] table = new int[26];
        for (char ch : s.toCharArray()) {
            table[ch - 'a']++;
        }
        
        int oddCnt = 0;
        for (int cnt : table) {
            oddCnt += cnt % 2;
        }
        
        return oddCnt <= 1;
    }
}