package GeeksForGeeks.URLifyAGivenString;

class Solution {
    String URLify(String s) {
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            sb.append(
                (ch == ' ')? "%20" : ch
            );
        }

        return sb.toString();
    }
}