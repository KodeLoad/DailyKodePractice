package GeeksForGeeks.CountSubarrayWithKOdds;

import java.util.HashMap;

class StorageWithArraySolution {
    int atMost(int[] a, int k) {
        if (k < 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int oddCount = 0;
        int cnt = 0;

        for (end = 0; end < a.length; end++) {
            oddCount += a[end] & 1;

            while (oddCount > k) {
                // excessive elements
                // shrink the window
                oddCount -= a[start] & 1;
                start++;
            }

            cnt += end - start + 1;
        }

        return cnt;
    }

    public int countSubarrays(int[] a, int k) {
        return atMost(a, k) - atMost(a, k - 1);
    }
}

class StorageWithMapSolution {
    public int countSubarrays(int[] a, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: 0 odds seen once

        int oddCount = 0;
        int result = 0;

        for (int num : a) {
            // Count odd numbers so far
            if (num % 2 == 1) {
                oddCount++;
            }

            // If we've seen (oddCount - k) before,
            // those positions can be starting points for valid subarrays
            if (map.containsKey(oddCount - k)) {
                result += map.get(oddCount - k);
            }

            // Store current oddCount
            map.put(oddCount, map.getOrDefault(oddCount, 0) + 1);
        }

        return result;
    }
}

// Example walkthrough for [2, 2, 1, 2, 1], k=2
//
// Index 0 (num=2): oddCount=0, map={0:1}, result=0
// Update map={0:2}
//
// Index 1 (num=2): oddCount=0, map={0:2}, result=0
// Update map={0:3}
//
// Index 2 (num=1): oddCount=1, map={0:3}, result=0
// Update map={0:3, 1:1}
//
// Index 3 (num=2): oddCount=1, map={0:3, 1:1}, result=0
// Update map={0:3, 1:2}
//
// Index 4 (num=1): oddCount=2, need (2-2)=0, found 3 times!
// result += 3 = 3 ✓
//
// Valid subarrays: [2,2,1,2,1], [2,1,2,1], [1,2,1]