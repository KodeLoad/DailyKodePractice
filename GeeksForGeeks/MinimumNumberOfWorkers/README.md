# Minimum Number of Workers (Minimum Sprinklers)
---

> Video Solution: [https://youtu.be/kcDLSqmkRsM](https://youtu.be/kcDLSqmkRsM)

[Problem](https://www.geeksforgeeks.org/problems/minimum-sprinklers/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/kcDLSqmkRsM/0.jpg)](https://youtu.be/kcDLSqmkRsM)

---

**Difficulty:** Medium  
**Accuracy:** 65.68%  
**Submissions:** 2K+  
**Points:** 4  
**Average Time:** 30m

You are given an array `arr[]`, where `arr[i]` denotes the range of working hours a person at position `i` can cover. If `arr[i]` ≠ -1, the person at index `i` can work and cover the time interval `[i - arr[i], i + arr[i]]`. Find the **minimum** number of people required to cover the entire day from 0 to $n-1$.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 2, 1, 0]
Output: 1
Explanation: Index 1 with range 2 covers [1-2, 1+2] = [-1, 3], which bounds to [0, 3]. 
This covers the entire array of size 4. [00:01:10]
</code>

**Example 2:**
<code>
Input: arr[] = [2, 3, 4, -1, 2, 0, 0, -1, 0]
Output: -1
Explanation: Even with the best workers, index 7 cannot be covered. [00:01:54]
</code>

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- -1 ≤ arr[i] ≤ arr.size()

## Expected Complexities:
- **Time Complexity:** O(N log N) (due to sorting)
- **Space Complexity:** O(N) (to store intervals)

## Topic Tags:
Greedy | Sorting | Intervals

---

## Approach: Greedy with Interval Sorting

### 1. Transform into Intervals [00:08:26]
First, we ignore unavailable workers (`-1`). For every other worker at index `i`, we calculate their coverage interval:
- `start = max(0, i - arr[i])`
- `end = min(n-1, i + arr[i])`

### 2. Sorting [00:10:18]
We sort all these intervals based on their **starting point**. This allows us to process the day chronologically and always pick the worker who gives the maximum "reach" for the current uncovered section.

### 3. Greedy Selection [00:12:16]
- Maintain `farthest`: the end of the day currently covered (initially -1).
- While `farthest < n-1`:
    - Look at all available intervals that start at or before `farthest + 1`. [00:13:33]
    - Out of these, pick the one that ends the latest (`max_end`).
    - Update `farthest = max_end` and increment count.
    - If no interval can extend the `farthest` point, it's impossible to cover the day; return -1. [00:16:42]

---

## Visualizing the Logic:
<code>
Day: [0, 1, 2, 3, 4, 5, 6]
Intervals: [0,4], [0,6], [1,5]

Start at -1. We need to cover index 0.
Intervals starting <= 0 are [0,4] and [0,6].
Greedy Choice: Pick [0,6] because 6 is larger.
Count: 1. Farthest: 6.
Done!
</code>

---

## Implementation (Conceptual):
<code>
def minWorkers(arr, n):
    intervals = []
    for i in range(n):
        if arr[i] != -1:
            intervals.append((max(0, i - arr[i]), min(n - 1, i + arr[i])))
    
    # Sort by start point
    intervals.sort()
    
    count = 0
    farthest = -1
    i = 0
    
    while farthest < n - 1:
        max_reach = farthest
        # Check all intervals that can start at or before current boundary
        while i < len(intervals) and intervals[i][0] <= farthest + 1:
            max_reach = max(max_reach, intervals[i][1])
            i += 1
            
        if max_reach <= farthest: # Could not extend
            return -1
            
        farthest = max_reach
        count += 1
        
    return count
</code>

---

## Key Takeaways:
- **Greedy Choice:** Always pick the worker who extends the current covered boundary the furthest. [00:17:25]
- **Boundary Handling:** Use `max(0, ...)` and `min(n-1, ...)` to keep intervals valid. [00:09:53]
- **Failure Condition:** If `max_reach` doesn't improve after checking all valid starts, a gap exists in the working day. [00:16:11]

## Related Problems:
- [Video Stitching (LeetCode 1024)](https://leetcode.com/problems/video-stitching/)
- [Jump Game II](https://leetcode.com/problems/jump-game-ii/)
- [Minimum Number of Taps to Open to Water a Garden](https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/)

## Keywords:
minimum sprinklers, minimum workers, greedy algorithm, interval sorting, reachability problem, geeksforgeeks medium solution, O(n log n) solution.

---

**SEO Tags:** #GreedyAlgorithms #Intervals #Sorting #DSA #CodingInterview #GeeksforGeeks #Python #Java #ProblemSolving

**Learning Outcomes:**
- Mastering interval-based greedy problems.
- Optimizing reachability with sorted starts.
- Efficiently handling "uncovered gaps" in continuous ranges.