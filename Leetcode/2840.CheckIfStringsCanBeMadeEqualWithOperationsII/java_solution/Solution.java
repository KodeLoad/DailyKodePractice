/**

    even index with even index
    and odd with odd

    app 1:
        even string -> s1 , s2 
        odd string -> s1, s2

    app 2:
        0   1   2   3   ...
        a   b   c   d   ...
        
        index 0, 2, 4 -> 

 */

class Solution {
    public boolean checkStrings(String a, String b) {
        return check(a, b, 0)
            && check(a, b, 1);
    }

    // TC: O(n)
    // SC: O(1)
    boolean check(String a, String b, int start) {
        int[] table = new int[26];

        for (int i = start; i < a.length(); i+=2) {
            table[a.charAt(i)-'a']++;
            table[b.charAt(i)-'a']--;
        }

        for (int i : table) {
            if (i != 0) return false;
        }

        return true;
    }
}
