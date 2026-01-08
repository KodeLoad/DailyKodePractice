package GeeksForGeeks.CountSubarrayWithKOdds;

public class NoExtraSpaceSolution {
    public int countSubarrays(int[] a, int k) {
        return atMostK(a, k) - atMostK(a, k - 1);
    }

    private int atMostK(int[] a, int k) {
        if (k < 0) {
            return 0;
        }

        int start = 0, cnt = 0, oddCount = 0;

        for (int end = 0; end < a.length; end++) {
            oddCount += a[end] & 1;

            while (oddCount > k) {
                oddCount -= a[start] & 1;
                start++;
            }

            cnt += end - start + 1;
        }

        return cnt;
    }

}
