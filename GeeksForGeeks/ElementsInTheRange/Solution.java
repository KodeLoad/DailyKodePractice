package GeeksForGeeks.ElementsInTheRange;

class Solution {
    public boolean checkElements(int start, int end, int[] arr) {
        // 1 + 2 + 3 ... + 99 + .. 999999
        // start = 999  end = 9999
        // start = 1    end = 9001
        int newStart = 1;
        int newEnd = end - start + 1;

        int sum = 0;
        for (int n : arr) {
            n = n - start + 1;
            if (1 <= n && n <= newEnd) {
                sum += n;
            }
        }

        // sum (1 ... newEnd)
        int expSum = newEnd * (newEnd+1) / 2;

        return sum == expSum;
    }
}