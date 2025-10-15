# k-th Smallest in BST
---

> Video description: https://youtu.be/1L07w4A_erc

[Problem](https://www.geeksforgeeks.org/problems/find-k-th-smallest-element-in-bst/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/1L07w4A_erc/0.jpg)](https://youtu.be/1L07w4A_erc)

---

**Difficulty:** Medium  
**Accuracy:** 43.53%  
**Submissions:** 152K+  
**Points:** 4  
**Average Time:** 40m

Given the root of a BST and an integer k, the task is to find the kth smallest element in the BST. If there is no kth smallest element present then return -1.

## Examples:

**Example 1:**
```
Input: root = [20, 8, 22, 4, 12, N, N, N, N, 10, 14], k = 3
        20
       /  \
      8    22
     / \
    4   12
       /  \
      10  14

Output: 10
Explanation: 10 is the 3rd smallest element in the BST.
```

**Example 2:**
```
Input: root = [2, 1, 3], k = 5
        2
       / \
      1   3

Output: -1
Explanation: There is no 5th smallest element in the BST as the size of BST is 3.
```

## Constraints:
- 1 ≤ number of nodes, k ≤ 10⁴
- 1 ≤ node->data ≤ 10⁴

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(h) where h is the height of the tree

## Company Tags:
Accolite | Amazon | Google

## Topic Tags:
Trees | Binary Search Tree | Data Structures | In-order Traversal | DFS

## Approach:
The problem can be solved using in-order traversal of BST:

1. **Key Property of BST:**
   - In-order traversal of a BST visits nodes in ascending order
   - The kth node visited during in-order traversal is the kth smallest element

2. **Algorithm:**
   - Perform in-order traversal (Left -> Root -> Right)
   - Maintain a counter to track the number of nodes visited
   - When counter equals k, return the current node's value
   - If traversal completes and k is not reached, return -1

3. **Optimization:**
   - Stop traversal as soon as kth element is found
   - No need to traverse the entire tree

4. **Implementation Approaches:**
   - **Iterative:** Use a stack for in-order traversal
   - **Recursive:** Pass counter as reference/global variable

## Related Interview Experiences:
- BST Properties and Traversals
- Finding kth Largest Element in BST
- Morris Traversal for O(1) Space
- Validate Binary Search Tree

## Related Articles:
- Binary Search Tree Operations
- Tree Traversal Techniques
- In-order Traversal Applications