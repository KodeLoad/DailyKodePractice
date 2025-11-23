package Leetcode.StringCompressionIII;

class Solution {

    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); ) {
            int nextIndex = getNextIndex(word, i);
            int cnt = nextIndex - i;

            do {
                sb.append(Math.min(9, cnt))
                    .append((char)(word.charAt(i)));
                cnt -= 9;
            } while (cnt > 0);

            i = nextIndex;
        }

        return sb.toString();
    }

    int getNextIndex(String word, int i) {
        // the moment we see different or end of string
        // it retruns that index
        int j = i + 1;

        while (j < word.length() && word.charAt(j) == word.charAt(i)) {
            j++;
        }

        return j;
    }
}
