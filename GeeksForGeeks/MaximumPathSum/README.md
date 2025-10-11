# Maximum Path Sum
---

> Video description: https://youtu.be/0IH5KQxd1hU

[Problem](https://www.geeksforgeeks.org/problems/maximum-path-sum-from-any-node/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/0IH5KQxd1hU/0.jpg)](https://youtu.be/0IH5KQxd1hU)

---

**Difficulty:** Medium  
**Accuracy:** 42.92%  
**Submissions:** 110K+  
**Points:** 4  
**Average Time:** 45m

Given the root of a binary tree, your task is to find the maximum path sum. The path may start and end at any node in the tree.

## Examples:

**Example 1:**
```
Input: root[] = [10, 2, 10, 20, 1, N, -25, N, N, N, N, 3, 4]
        10
       /  \
      2    10
     / \     \
    20  1    -25
           /    \
          3      4

Output: 42
Explanation: Max path sum is represented using green colour nodes in the above binary tree.
Path: 20 -> 2 -> 10 -> 10 = 42
```

**Example 2:**
```
Input: root[] = [-17, 11, 4, 20, -2, 10]
         -17
        /   \
       11    4
      / \   /
     20 -2 10

Output: 31
Explanation: Max path sum is represented using green colour nodes in the above binary tree.
Path: 20 -> 11 -> 4 -> 10 = 31
```

## Constraints:
- 1 ≤ number of nodes ≤ 10³
- -10⁴ ≤ node->data ≤ 10⁴

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(h) where h is the height of the tree

## Company Tags:
Flipkart | Google | Facebook

## Topic Tags:
Trees | Binary Tree | Data Structures | Recursion | DFS | Dynamic Programming

## Approach:
The problem can be solved using a recursive approach:
1. **Two Cases at Each Node:**
   - Path passes through the current node (connecting left and right subtrees)
   - Path extends from current node to its parent (either via left or right subtree)

2. **Algorithm:**
   - For each node, calculate the maximum path sum from left and right subtrees
   - Consider negative contributions as 0 (ignore negative paths)
   - Update global maximum with path passing through current node
   - Return the maximum path that can be extended to parent

3. **Key Insight:**
   - A path that connects left and right subtrees cannot be extended further
   - Only single-direction paths can be extended to parent nodes

## Related Interview Experiences:
- Binary Tree Maximum Path Sum
- Tree Traversal Problems
- Recursive Tree Algorithms
- Dynamic Programming on Trees

## Related Articles:
- Binary Tree Traversals
- Tree Data Structure
- Recursion and Backtracking
