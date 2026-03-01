# Move All Zeroes to End
---

> Video Solution: [https://www.youtube.com/watch?v=muCrVeOp4o4](https://www.youtube.com/watch?v=muCrVeOp4o4)

[Problem](https://www.geeksforgeeks.org/problems/move-all-zeroes-to-end-of-array0751/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/muCrVeOp4o4/0.jpg)](https://www.youtube.com/watch?v=muCrVeOp4o4)

---

**Difficulty:** Easy  
**Accuracy:** 45.51%  
**Submissions:** 300K+  
**Points:** 1  
**Average Time:** 15m

Given an array `arr[]`, the task is to move all the zeros to the end of the array while maintaining the relative order of all non-zero elements. The operation should be performed **in-place**.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 2, 0, 4, 3, 0, 5, 0]
Output: [1, 2, 4, 3, 5, 0, 0, 0]
Explanation: There are three 0s that are moved to the end.
</code>

**Example 2:**
<code>
Input: arr[] = [10, 20, 30]
Output: [10, 20, 30]
Explanation: No 0s are present.
</code>

**Example 3:**
<code>
Input: arr[] = [0, 0]
Output: [0, 0]
</code>

## Constraints:
- 1 ≤ arr.size() ≤ 10⁶
- 0 ≤ arr[i] ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(1)

## Topic Tags:
Arrays | Two Pointers | Algorithms | In-place Operations

---

## Approach: Optimized Two-Pointer (Single Pass)

### 1. The Intuition
The brute force approach would involve creating a new array, filling it with non-zero elements, and then padding with zeroes. However, that takes $O(N)$ extra space. To achieve $O(1)$ space, we use two pointers to track the position of the next "non-zero" element.



### 2. How it Works
1.  **Pointer `j`:** Tracks the position where the next non-zero element should go.
2.  **Pointer `i`:** Iterates through the entire array.
3.  Whenever `arr[i]` is not zero, we swap `arr[i]` with `arr[j]` and increment `j`.
4.  By doing this, all non-zero elements are "pushed" to the front in their original relative order, and zeroes naturally move to the back.

### 3. Algorithm Steps:
- Initialize `count = 0` (this will be our `j` pointer).
- Loop through the array from `0` to `n-1`.
- If `arr[i] != 0`:
    - Swap `arr[i]` and `arr[count]`.
    - Increment `count`.

---

## Implementation (Conceptual):
<code>
public void pushZerosToEnd(int[] arr) {
    int n = arr.length;
    int count = 0; // Pointer for non-zero elements

    for (int i = 0; i < n; i++) {
        if (arr[i] != 0) {
            // Swap non-zero element with the element at 'count' index
            int temp = arr[i];
            arr[i] = arr[count];
            arr[count] = temp;
            
            count++;
        }
    }
}
</code>

---

## Key Takeaways:
- **In-place Sorting:** This approach avoids using extra memory, making it highly efficient for large datasets ($10^6$ elements).
- **Maintaining Order:** By swapping non-zero elements sequentially, we ensure their relative order remains unchanged.
- **Single Pass:** The algorithm only traverses the array once, resulting in a linear $O(N)$ time complexity.

## Related Problems:
- [Remove Duplicates from Sorted Array](https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
- [Sort Colors (Dutch National Flag)](https://leetcode.com/problems/sort-colors/)
- [Move Zeroes (LeetCode 283)](https://leetcode.com/problems/move-zeroes/)

## Keywords:
move zeroes to end, array manipulation java, two pointers technique, in-place array algorithm, geeksforgeeks potd, dsa interview questions, optimize array space.

---

**SEO Tags:** #TwoPointers #Arrays #DSA #Algorithms #GeeksforGeeks #CodingInterview #Java #Python #LinearTime #OBrutus

**Learning Outcomes:**
- Understanding in-place array transformations.
- Efficiently managing memory in competitive programming.
- Applying the two-pointer pattern to partition data.
