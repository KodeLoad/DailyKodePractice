# ZigZag Tree Traversal
---

> Video description: https://youtu.be/TcZhrjh2Yqg

[Problem](https://www.geeksforgeeks.org/problems/zigzag-tree-traversal/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/TcZhrjh2Yqg/0.jpg)](https://youtu.be/TcZhrjh2Yqg)

---

**Difficulty:** Medium  
**Accuracy:** 54.05%  
**Submissions:** 185K+  
**Points:** 4  
**Average Time:** 30m

Given the root of a binary tree. You have to find the zig-zag level order traversal of the binary tree.

**Note:** In zig zag traversal we traverse the nodes from left to right for odd-numbered levels, and from right to left for even-numbered levels.

## Examples:

**Example 1:**
```
Input: root = [1, 2, 3, 4, 5, 6, 7]
          1
        /   \
       2     3
      / \   / \
     4   5 6   7

Output: [1, 3, 2, 4, 5, 6, 7]

Explanation:
Level 1 (left to right): [1]
Level 2 (right to left): [3, 2]
Level 3 (left to right): [4, 5, 6, 7]
Final result: [1, 3, 2, 4, 5, 6, 7]
```

**Example 2:**
```
Input: root = [7, 9, 7, 8, 8, 6, N, 10, 9]
           7
         /   \
        9     7
       / \   /
      8   8 6
     /   /
    10  9

Output: [7, 7, 9, 8, 8, 6, 9, 10]

Explanation:
Level 1 (left to right): [7]
Level 2 (right to left): [7, 9]
Level 3 (left to right): [8, 8, 6]
Level 4 (right to left): [9, 10]
Final result: [7, 7, 9, 8, 8, 6, 9, 10]
```

## Constraints:
- 1 ≤ number of nodes ≤ 10⁵
- 1 ≤ node->data ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

## Company Tags:
Flipkart | Amazon | Microsoft | Snapdeal | FactSet | Hike | Walmart | Cisco

## Topic Tags:
Trees | Binary Tree | Data Structures | Queue | Level Order Traversal | BFS

## Approach:
The problem can be solved using level-order traversal with alternating direction:
1. Use a queue for level-order traversal
2. Use a flag to determine the direction (left-to-right or right-to-left)
3. For each level, collect nodes and reverse if needed
4. Toggle the direction flag after each level

## Related Interview Experiences:
- Binary Tree Traversals
- Level Order Traversal
- Queue-based problems
- Tree Data Structure
