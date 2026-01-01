# Sort an Array of 0s, 1s and 2s
---

> Video description: https://www.youtube.com/watch?v=mUbEMBq0VUQ

[Problem](https://www.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s4231/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/mUbEMBq0VUQ/0.jpg)](https://www.youtube.com/watch?v=mUbEMBq0VUQ)

---

**Difficulty:** Easy  
**Accuracy:** 50.58%  
**Submissions:** 1.1M+  
**Points:** 2  
**Average Time:** 15m

Given an array `arr[]` containing only 0s, 1s, and 2s. Sort the array in ascending order.

## Examples:

**Example 1:**
```
Input: arr[] = [0, 1, 2, 0, 1, 2]
Output: [0, 0, 1, 1, 2, 2]
Explanation: 0s, 1s, and 2s are segregated into ascending order.
```

**Example 2:**
```
Input: arr[] = [0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1]
Output: [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]
Explanation: 0s, 1s, and 2s are segregated into ascending order.
```

## Constraints:
- 1 ≤ arr.size() ≤ 10⁶
- 0 ≤ arr[i] ≤ 2

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Microsoft | Google | Facebook | Adobe | Samsung | Accolite | Paytm | Walmart

## Topic Tags:
Arrays | Two Pointers | Sorting | Dutch National Flag Algorithm

## Approach:

### Dutch National Flag Algorithm (Optimal):

**Key Idea:**
- Use three pointers: low, mid, high
- Partition array into three regions: [0s | 1s | 2s]
- Single pass solution

**Algorithm:**
```
low = 0, mid = 0, high = n-1

while mid <= high:
    if arr[mid] == 0:
        swap(arr[low], arr[mid])
        low++, mid++
    else if arr[mid] == 1:
        mid++
    else:  // arr[mid] == 2
        swap(arr[mid], arr[high])
        high--
```

**Visual:**
```
[0s...] [1s...] [unknown] [2s...]
   ↑       ↑        ↑        ↑
  low     mid              high
```

### Alternative Approaches:

**1. Count and Fill (Simple):**
- Count 0s, 1s, 2s
- Fill array accordingly
- Time: O(2n), Space: O(1)

**2. Built-in Sort:**
- sort(arr)
- Time: O(n log n), Space: O(1)
- Not optimal

## Related Problems:
- Sort Colors (LeetCode 75)
- Partition Array
- Three Way Partitioning
- Segregate Even and Odd Numbers

## Keywords:
sort 0 1 2, dutch national flag, three way partitioning, array sorting, two pointers, DNF algorithm, geeksforgeeks sorting

---

**Tags:** #Arrays #TwoPointers #Sorting #DutchNationalFlag #DNF #InPlaceSorting #DSA #CodingInterview