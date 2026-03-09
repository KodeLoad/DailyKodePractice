package GeeksForGeeks.LargestNumberWithOneSwapAllowed;

/*
    Most common:
        the max within the string 

            3 4 6 9 2
                  ^
            9 
        (Greedy approach)


    Sort
        selection sort
            3 4 6 9 2

        figure out max while we go ahead

*/
class Solution {
    public String largestSwap(String str) {
        char[] s = str.toCharArray();

        for (int i = 0; i < s.length; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < s.length; j++) {
                if (s[maxIndex] <= s[j]) {
                    maxIndex = j;
                }
            }

            // check
            if (s[maxIndex] != s[i]) {
                // swap
                char t = s[i];
                s[i] = s[maxIndex];
                s[maxIndex] = t;

                break;
            }
        } // loop ends

        return new String(s);
    }
}
