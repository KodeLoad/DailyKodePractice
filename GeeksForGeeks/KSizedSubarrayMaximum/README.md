# K Sized Subarray Maximum
---

> Video Solution: [https://youtu.be/T0Gb8GHnmFY](https://youtu.be/T0Gb8GHnmFY)

[Problem](https://www.geeksforgeeks.org/problems/maximum-of-all-subarrays-of-size-k3101/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/T0Gb8GHnmFY/0.jpg)](https://youtu.be/T0Gb8GHnmFY)

---

**Difficulty:** Medium  
**Accuracy:** 26.04%  
**Submissions:** 300K+  
**Points:** 4  
**Average Time:** 30m

Given an array `arr[]` of integers and an integer `k`, find the maximum element for each and every contiguous subarray of size `k`.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 2, 3, 1, 4, 5, 2, 3, 6], k = 3
Output: [3, 3, 4, 5, 5, 5, 6]
Explanation: 
1st contiguous subarray = [1, 2, 3], Max = 3
2nd contiguous subarray = [2, 3, 1], Max = 3
3rd contiguous subarray = [3, 1, 4], Max = 4
...
</code>

**Example 2:**
<code>
Input: arr[] = [8, 5, 10, 7, 9, 4, 15, 12, 90, 13], k = 4
Output: [10, 10, 10, 15, 15, 90, 90]
</code>

## Constraints:
- 1 ≤ arr.size() ≤ 10⁶
- 1 ≤ k ≤ arr.size()
- 0 ≤ arr[i] ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(K)

## Topic Tags:
Queue | Deque | Sliding Window | Data Structures | Algorithms

---

## Approach: Monotonic Deque (Sliding Window Maximum)

### 1. The Intuition
The brute force approach takes $O(N \times K)$ by checking every subarray. To optimize to $O(N)$, we need a data structure that helps us track the maximum in the current window. A **Deque** (Double Ended Queue) allows us to store indices of elements in a way that the largest element is always at the front.

### 2. Maintaining the Deque Property
We maintain a "Monotonic Decreasing" Deque:
- **Remove Out-of-Window Indices:** If the index at the front of the deque is outside the current window ($front \le i - k$), we remove it.
- **Maintain Decreasing Order:** Before inserting a new element `arr[i]`, we remove all elements from the back of the deque that are smaller than `arr[i]`. They can never be the maximum for the current or any future window.
- **Add Current Index:** Add the current index `i` to the back.
- **Capture Maximum:** Once the first window is processed ($i \ge k-1$), the element at the `front` of the deque is the maximum for that window.

### 3. Complexity Analysis
- **Time Complexity:** $O(N)$. Each element is pushed and popped from the deque at most once.
- **Space Complexity:** $O(K)$. The deque stores at most `k` indices.

---

## Implementation (Conceptual):
<code>
public ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
    ArrayList<Integer> res = new ArrayList<>();
    Deque<Integer> dq = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
        // Remove indices out of current window
        if (!dq.isEmpty() && dq.peekFirst() == i - k) {
            dq.pollFirst();
        }

        // Remove elements smaller than current element from back
        while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
            dq.pollLast();
        }

        dq.offerLast(i);

        // Add max of current window to result
        if (i >= k - 1) {
            res.add(arr[dq.peekFirst()]);
        }
    }
    return res;
}
</code>

---

## Key Takeaways:
- **Sliding Window:** Useful for processing contiguous segments of data.
- **Monotonic Queue Pattern:** Efficiently tracking the maximum/minimum in a moving window.
- **Space Optimization:** Storing indices instead of values in the Deque makes it easier to track window boundaries.

## Related Problems:
- [Sliding Window Maximum (LeetCode 239)](https://leetcode.com/problems/sliding-window-maximum/)
- [First Negative Integer in every window of size K](https://www.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1)
- [Smallest window containing all characters](https://www.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string-1587115621/1)

## Keywords:
k sized subarray maximum, sliding window maximum, monotonic deque java, geeksforgeeks potd, sliding window problems dsa, deque data structure, linear time complexity.

---

**SEO Tags:** #SlidingWindow #Deque #MonotonicQueue #DataStructures #DSA #GeeksforGeeks #CodingInterview #Java #Algorithms #OBrutus

**Learning Outcomes:**
- Mastering the Sliding Window technique for array problems.
- Leveraging Deques to maintain order and efficiently find window maximums.
- Reducing $O(N \times K)$ problems to $O(N)$ linear time.
