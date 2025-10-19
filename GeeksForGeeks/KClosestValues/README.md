# K Closest Values in BST
---

> Video description: https://youtu.be/-84x2UXvZyc

[Problem](https://www.geeksforgeeks.org/problems/k-closest-values-in-bst/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/-84x2UXvZyc/0.jpg)](https://youtu.be/-84x2UXvZyc)

---

**Difficulty:** Medium  
**Accuracy:** 76.47%  
**Submissions:** 7K+  
**Points:** 4  
**Average Time:** 45m

Given the root of a Binary Search Tree, a target value, and an integer k. Your task is to find the k values in the BST that are closest to the target.

The closest value is taken by choosing the one that gives minimum absolute difference from target.

**Note:** 
- In case two values have same absolute difference from target, choose the smaller one
- The target may or may not be present in BST
- You can return the values in any order (the driver code will print them in sorted order only)

## Examples:

**Example 1:**
```
Input: root = [20, 8, 22, 4, 12, N, N, N, N, 10, 14], target = 17, k = 3
        20
       /  \
      8    22
     / \
    4   12
       /  \
      10  14

Output: [14, 20, 12]
Explanation: Absolute difference of 17 wrt 14 and 20 is 3 and 3, but we choose the smaller value in case of same absolute difference. So, 14 comes first and then 20. Then, 12 and 22 have same absolute difference, i.e., 5 from 17. But we choose the smaller value, i.e., 12.
```

**Example 2:**
```
Input: root = [5, 4, 8, 1], target = 5, k = 2
      5
     / \
    4   8
   /
  1

Output: [5, 4]
Explanation: The absolute difference of 5 wrt 5 is 0, and for 4, the absolute difference is 1.
```

## Constraints:
- 1 ≤ number of nodes, k ≤ 10⁴
- 1 ≤ node->data, target ≤ 10⁴

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

## Company Tags:
Google | Facebook | Amazon

## Topic Tags:
Trees | Binary Search Tree | Data Structures | In-order Traversal | Priority Queue | Two Pointers

## Approach:

### Approach 1: In-order Traversal + Sorting
1. **Collect All Values:**
   - Perform in-order traversal to get all BST values in sorted order
   - Store values in an array/list

2. **Sort by Difference:**
   - Create pairs of (value, |value - target|)
   - Sort based on:
     - Primary: Absolute difference from target
     - Secondary: Smaller value (in case of tie)
   
3. **Return k Elements:**
   - Take first k elements from sorted list

4. **Complexity:**
   - Time: O(n log n) - due to sorting
   - Space: O(n) - for storing all values

### Approach 2: Priority Queue (Max Heap)
1. **Use Max Heap of Size k:**
   - Traverse BST (any order)
   - Maintain a max heap of size k based on absolute difference
   - Heap stores pairs: (absolute_difference, value)
   
2. **Algorithm:**
   - For each node:
     - Calculate |node->data - target|
     - If heap size < k: Insert into heap
     - Else if current difference < heap top difference:
       - Remove heap top
       - Insert current element
     - Handle ties: prefer smaller value

3. **Complexity:**
   - Time: O(n log k) - n insertions in heap of size k
   - Space: O(k) - heap space

### Approach 3: Two Pointers (Most Optimized)
1. **In-order Traversal:**
   - Store all values in sorted array (in-order gives sorted order)
   
2. **Binary Search for Closest Position:**
   - Find the position closest to target using binary search
   - This gives a starting point

3. **Expand Using Two Pointers:**
   - Initialize two pointers around the closest value
   - Expand outward, comparing absolute differences
   - Choose k closest values
   - Handle tie-breaking: prefer smaller value

4. **Complexity:**
   - Time: O(n) - traversal + O(k) expansion = O(n)
   - Space: O(n) - for storing values

### Key Observations:
- **Tie-Breaking Rule:**
  - When |a - target| == |b - target|, choose min(a, b)
  - Important for consistent results

- **BST Property:**
  - In-order traversal gives sorted sequence
  - Helps in efficient two-pointer approach

- **Target Not Required in BST:**
  - Algorithm works whether target exists or not
  - Just find closest values based on absolute difference

### Example Walkthrough (Example 1):
```
Tree values (in-order): [4, 8, 10, 12, 14, 20, 22]
Target = 17, k = 3

Absolute differences from 17:
4:  |4-17|  = 13
8:  |8-17|  = 9
10: |10-17| = 7
12: |12-17| = 5
14: |14-17| = 3
20: |20-17| = 3  (tie, but 14 < 20, so 14 comes first)
22: |22-17| = 5  (tie with 12, but 12 < 22, so 12 comes first)

Sorted by difference (with tie-breaking):
14 (diff=3), 20 (diff=3), 12 (diff=5), 22 (diff=5), ...

First k=3 values: [14, 20, 12]
```

## Related Interview Experiences:
- Closest Binary Search Tree Value (LeetCode 270)
- Closest Binary Search Tree Value II (LeetCode 272)
- K Closest Points to Origin
- Finding Closest Elements in Sorted Array

## Related Articles:
- Binary Search Tree Traversals
- Priority Queue Applications
- Two Pointers Technique
- Binary Search in BST