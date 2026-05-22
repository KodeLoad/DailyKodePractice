# Search in Rotated Sorted Array | LeetCode 33 | Binary Search O(log n)

---
> Video description: https://youtu.be/Db-ldKds9iM

[Problem](https://leetcode.com/problems/search-in-rotated-sorted-array/) | [Java Solution](./java_solution/Solution.java)

[![img](https://img.youtube.com/vi/Db-ldKds9iM/0.jpg)](https://youtu.be/Db-ldKds9iM)

---

**Difficulty:** Medium  
**Topics:** Array | Binary Search | Divide and Conquer  
**Companies:** Amazon | Microsoft | Facebook | Apple | Google  
**Time Complexity:** O(log n) | **Space Complexity:** O(log n) recursion stack

---

## What Is a Rotated Sorted Array?

A sorted array is **rotated** when some prefix is moved to the end:

```
Original:  [0, 1, 2, 4, 5, 6, 7]
Rotated:   [4, 5, 6, 7, 0, 1, 2]   ← rotated at index 4
Rotated:   [6, 7, 0, 1, 2, 4, 5]   ← rotated at index 2
```

The key property: **one half of the array around any midpoint is always fully sorted**.

---

## Problem Statement — Find Target Index in a Rotated Sorted Array

Given a rotated sorted array `nums` of **distinct integers** and an integer `target`, return the **index** of `target` if it exists, or `-1` if it does not. Must run in **O(log n)** time.

## Examples

**Example 1:**
```
Input:  nums = [4, 5, 6, 7, 0, 1, 2],  target = 0
Output: 4

The array was rotated. 0 is at index 4.
```

**Example 2:**
```
Input:  nums = [4, 5, 6, 7, 0, 1, 2],  target = 3
Output: -1

3 is not present in the array.
```

**Example 3:**
```
Input:  nums = [1],  target = 0
Output: -1
```

**Example 4:**
```
Input:  nums = [1, 3],  target = 3
Output: 1
```

## Constraints
- 1 ≤ nums.length ≤ 5000
- -10⁴ ≤ nums[i] ≤ 10⁴
- All values in `nums` are **distinct**
- `nums` is sorted and rotated between `1` and `n` times

---

## Why Plain Binary Search Fails

Standard binary search assumes the entire array is sorted. After rotation, `nums[mid]` may be smaller than `nums[l]`, so the usual "go left if target < mid" rule breaks.

```
nums = [4, 5, 6, 7, 0, 1, 2],  target = 0

l=0, r=6, mid=3  →  nums[mid]=7
target (0) < nums[mid] (7)  →  plain BS goes LEFT
But 0 is on the RIGHT side!  ✗
```

---

## Approach — Modified Binary Search on the Sorted Half

**Key Insight:** Even in a rotated array, **one of the two halves around `mid` is always fully sorted**. Identify the sorted half, check if `target` falls within its range, and recurse into the correct half.

### Decision Logic

```
if nums[l] <= nums[mid]:          ← left half [l..mid] is sorted
    if nums[l] <= target <= nums[mid]:
        search left half
    else:
        search right half
else:                              ← right half [mid..r] is sorted
    if nums[mid] <= target <= nums[r]:
        search right half
    else:
        search left half
```

### Walkthrough — Example 1

```
nums = [4, 5, 6, 7, 0, 1, 2],  target = 0

l=0, r=6, mid=3  →  nums[mid]=7
nums[l]=4 <= nums[mid]=7  →  LEFT half [4,5,6,7] is sorted
target=0 NOT in [4..7]  →  go RIGHT

l=4, r=6, mid=5  →  nums[mid]=1
nums[l]=0 <= nums[mid]=1  →  LEFT half [0,1] is sorted
target=0 IN [0..1]  →  go LEFT

l=4, r=4, mid=4  →  nums[mid]=0 == target  →  return 4 ✓
```

### Walkthrough — Example 2

```
nums = [4, 5, 6, 7, 0, 1, 2],  target = 3

l=0, r=6, mid=3  →  nums[mid]=7
LEFT half sorted [4..7],  target=3 NOT in range  →  go RIGHT

l=4, r=6, mid=5  →  nums[mid]=1
LEFT half sorted [0..1],  target=3 NOT in range  →  go RIGHT

l=6, r=6, mid=6  →  nums[mid]=2 ≠ 3
l=6, r=5  →  l > r  →  return -1 ✓
```

### Walkthrough — No Rotation

```
nums = [1, 2, 3, 4, 5],  target = 4

l=0, r=4, mid=2  →  nums[mid]=3
nums[l]=1 <= nums[mid]=3  →  LEFT sorted
target=4 NOT in [1..3]  →  go RIGHT

l=3, r=4, mid=3  →  nums[mid]=4 == target  →  return 3 ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(log n) — halves the search space each step |
| **Space** | O(log n) — recursion stack depth |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Using strict `<` instead of `<=` for the sorted half check**
```java
// Wrong
if (a[l] < a[mid])

// Correct — handles the case where l == mid (single element)
if (a[l] <= a[mid])
```

**Mistake 2: Checking target range with strict inequalities**
The target can equal either boundary:
```java
// Wrong
if (a[l] < x && x < a[mid])

// Correct
if (a[l] <= x && x <= a[mid])
```

**Mistake 3: Forgetting the base case**
Always check `a[mid] == target` before deciding which half to recurse into.

**Mistake 4: Assuming the rotation point must be found first**
You don't need to find the pivot. The sorted-half insight handles everything in a single binary search pass.

---

## Related Problems
- Search in Rotated Sorted Array II — LeetCode 81 (with duplicates)
- Find Minimum in Rotated Sorted Array — LeetCode 153
- Binary Search — LeetCode 704

---

## Tags

Binary Search | Rotated Sorted Array | Divide and Conquer | Array | Modified Binary Search | Java | LeetCode 33 | Search in Rotated Array | O(log n)

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/Leetcode/33.SearchInRotatedSortedArray

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ Why plain binary search fails on a rotated array
✅ The "one half is always sorted" key insight
✅ Step-by-step dry run with multiple examples
✅ O(log n) time — no need to find the pivot first
✅ Common mistakes with boundary conditions

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Search in Rotated Sorted Array · Binary Search · Divide and Conquer · LeetCode 33 · Java · LeetCode Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://leetcode.com/problems/search-in-rotated-sorted-array/

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#SearchInRotatedSortedArray #LeetCode33 #BinarySearch #LeetCodeDaily #DataStructures
#JavaProgramming #CodingInterview #DSAWithOBrutus #KodeLoad #LeetCode2026
```
