package Leetcode.LongestSubstringWithoutRepeatingCharacters.java_solution;

import java.util.HashSet;
import java.util.Set;

/*
    pwwkew

    O(n) :: TC && SC

    set :: no duplication
        O(1) :: 

    start and end
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // len
        int maxLen = 0;
        Set<Character> seen = new HashSet<>();

        // bet these 2 index always 
        // have valid  non repeating string
        int start = 0;
        int end = 0;

        while (end < s.length()) {
            char incoming = s.charAt(end);

            // every element within seen
            // once added   :: O(n)
            // once removed :: O(n)
            while (seen.contains(incoming)) {
                // make this valid
                // shrink
                seen.remove(s.charAt(start));
                start += 1;
            }

            seen.add(incoming);
            int newLen = end - start + 1;
            maxLen = Math.max(maxLen, newLen);
            end++;
        }
        
        return maxLen;
    }
}/*
    pwwkew
    
    O(n) :: TC && SC

    set :: no duplication
        O(1) :: 

    start and end
*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // len
        int maxLen = 0;
        Set<Character> seen = new HashSet<>();

        // bet these 2 index always 
        // have valid  non repeating string
        int start = 0;
        int end = 0;

        while (end < s.length()) {
            char incoming = s.charAt(end);

            // every element within seen
            // once added   :: O(n)
            // once removed :: O(n)
            while (seen.contains(incoming)) {
                // make this valid
                // shrink
                seen.remove(s.charAt(start));
                start += 1;
            }

            seen.add(incoming);
            int newLen = end - start + 1;
            maxLen = Math.max(maxLen, newLen);
            end++;
        }

        return maxLen;
    }
}