# Sum of Nodes in BST Range
---

> Video description: https://youtu.be/jzCF0HKXpRs

[Problem](https://www.geeksforgeeks.org/problems/range-sum-of-bst/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/jzCF0HKXpRs/0.jpg)](https://youtu.be/jzCF0HKXpRs)

---

**Difficulty:** Medium  
**Accuracy:** 90.42%  
**Submissions:** 21+  
**Points:** 4  

Given the root of a Binary Search Tree and two integers l and r, the task is to find the sum of all nodes that lie between l and r, including both l and r.

## Examples:

**Example 1:**
```
Input: root[] = [22, 12, 30, 8, 20], l = 10, r = 22

           22
          /  \
        12    30
        / \
       8   20

Output: 54
Explanation: The nodes in the given Tree that lies in the range [10, 22] are {12, 20, 22}. Therefore, the sum of nodes is 12 + 20 + 22 = 54.
```

**Example 2:**
```
Input: root[] = [8, 5, 11, 3, 6, N, 20], l = 11, r = 15  
              8
            /   \
          5     11
         / \      \
        3   6     20

Output: 11
Explanation: The nodes in the given Tree that lies in 
the range [11, 15] is {11}. Therefore, the sum of node is 11.
```

## Constraints:
- 0 ≤ number of nodes ≤ 104
- 0 ≤ node->data ≤ 104
- 0 ≤ l ≤ r ≤ 104

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

## Company Tags:
Flipkart | Google | Facebook

## Topic Tags:
Trees | Binary Tree | Data Structures | Recursion | DFS | Dynamic Programming
