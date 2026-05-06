# Size of Binary Tree | Count Nodes | DFS Recursion | GeeksForGeeks

---
> Video description: https://youtu.be/9aEUavrWLGA

[Problem](https://www.geeksforgeeks.org/problems/size-of-binary-tree/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/9aEUavrWLGA/0.jpg)](https://youtu.be/9aEUavrWLGA)

---

**Difficulty:** Easy  
**Topics:** Binary Tree | Tree Traversal | Recursion | DFS  
**Companies:** Amazon | Flipkart | Microsoft

---

## Problem Statement

Given a binary tree, find its **size** — the total number of nodes present in the tree.

## Examples

**Example 1:**
```
Input:
        1
       / \
      2   3
     / \
    4   5

Output: 5

Explanation: The tree has 5 nodes: 1, 2, 3, 4, 5.
```

**Example 2:**
```
Input:
        1
       /
      2
     /
    3

Output: 3

Explanation: The tree has 3 nodes: 1, 2, 3.
```

**Example 3:**
```
Input:
        10

Output: 1

Explanation: Only the root node exists.
```

## Constraints
- 1 ≤ number of nodes ≤ 10⁵
- 1 ≤ node data ≤ 10³

---

## Approach — DFS Recursion

**Key Insight:** The size of any tree rooted at a node is:

```
size(node) = 1 + size(left subtree) + size(right subtree)
```

The `1` counts the current node itself. Recurse down both subtrees and sum everything up. The base case is a `null` node — it contributes `0`.

### Walkthrough

```
        1
       / \
      2   3
     / \
    4   5

getSize(1)
  = 1 + getSize(2) + getSize(3)
          |               |
          |           1 + getSize(null) + getSize(null)
          |           = 1 + 0 + 0 = 1
          |
     1 + getSize(4) + getSize(5)
             |               |
         1 + 0 + 0         1 + 0 + 0
             = 1               = 1
         = 1 + 1 + 1 = 3

= 1 + 3 + 1 = 5  ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — every node is visited exactly once |
| **Space** | O(h) — recursion stack depth equals tree height h (O(log n) balanced, O(n) skewed) |

---

## Related Problems
- Height of Binary Tree
- Count Leaf Nodes
- Mirror of a Binary Tree

## Tags
`binary-tree` `recursion` `DFS` `tree-traversal` `count-nodes` `postorder` `java` `geeksforgeeks`
