# 1200. Minimum Absolute Difference
---

> Video Solution: [https://youtu.be/wmoQ6-WtWS0](https://youtu.be/wmoQ6-WtWS0)

[Problem](https://leetcode.com/problems/minimum-absolute-difference/description/) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/wmoQ6-WtWS0/0.jpg)](https://youtu.be/wmoQ6-WtWS0)

---

**Difficulty:** Easy  
**Accuracy:** 70.1%  
**Submissions:** 320K+  
**Points:** 1  
**Average Time:** 15m

Given an array of **distinct** integers `arr`, find all pairs of elements with the minimum absolute difference of any two elements. 

Return a list of pairs in ascending order(with respect to pairs), where each pair `[a, b]` follows:
- `a, b` are from `arr`
- `a < b`
- `b - a` equals the minimum absolute difference of any two elements in `arr`

## Examples:

**Example 1:**
<code>
Input: arr = [4, 2, 1, 3]
Output: [[1, 2], [2, 3], [3, 4]]
Explanation: The minimum absolute difference is 1. All pairs with difference 1 are [1,2], [2,3], [3,4]. [00:00:51]
</code>

**Example 2:**
<code>
Input: arr = [1, 3, 6, 10, 15]
Output: [[1, 3]]
</code>

**Example 3:**
<code>
Input: arr = [3, 8, -10, 23, 19, -4, -14, 27]
Output: [[-14, -10], [19, 23], [23, 27]]
</code>

## Constraints:
- 2 ≤ arr.length ≤ 10⁵
- -10⁶ ≤ arr[i] ≤ 10⁶

## Expected Complexities:
- **Time Complexity:** O(N log N)
- **Space Complexity:** O(N) (to store the result)

## Topic Tags:
Array | Sorting

---

## Approach: Sorting + Single Pass

### 1. The Intuition [00:01:25]
The absolute difference between any two elements is minimized when the elements are as close as possible. In an unsorted array, we would need $O(N^2)$ to check every pair. However, by **sorting** the array, the minimum difference must exist between adjacent elements. [00:02:30]

### 2. Finding the Minimum Difference [00:03:20]
1. Sort the array `arr` in ascending order.
2. Iterate through the sorted array once to find the global minimum difference between any two consecutive elements `arr[i]` and `arr[i-1]`. [00:03:44]

### 3. Collecting the Pairs [00:04:14]
Iterate through the sorted array a second time (or combine with the first pass). If the difference between `arr[i]` and `arr[i-1]` equals our previously found `min_diff`, add the pair `[arr[i-1], arr[i]]` to the result list. [00:04:40]

---

## Implementation (Conceptual): [00:03:28]
<code>
public List<List<Integer>> minimumAbsDifference(int[] arr) {
    Arrays.sort(arr);
    int minDiff = Integer.MAX_VALUE;
    List<List<Integer>> result = new ArrayList<>();

    // First pass to find the minimum difference
    for (int i = 1; i < arr.length; i++) {
        minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
    }

    // Second pass to collect pairs with that difference
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] - arr[i - 1] == minDiff) {
            result.add(Arrays.asList(arr[i - 1], arr[i]));
        }
    }
    return result;
}
</code>

---

## Key Takeaways:
- **Sorting is Key:** Sorting reduces the search space for the minimum difference from all pairs ($N^2$) to just adjacent neighbors ($N$). [00:05:30]
- **Time Complexity:** Dominated by the sort, which is $O(N \log N)$. The subsequent passes are $O(N)$. [00:06:14]
- **Space Complexity:** $O(N)$ for the output list. [00:06:17]

## Related Problems:
- [Minimum Difference Between Highest and Lowest of K Scores](https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/)
- [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)

## Keywords:
minimum absolute difference, sorting array, adjacent elements, leetcode daily question, java solution, n log n complexity, array manipulation.

---

**SEO Tags:** #LeetCode #Sorting #Arrays #DSA #Algorithms #Java #CodingInterview #MinimumAbsoluteDifference

**Learning Outcomes:**
- Optimizing $O(N^2)$ brute force to $O(N \log N)$ using sorting.
- Extracting specific relationships from sorted data.
- Handling list of lists for pair representation in Java.
