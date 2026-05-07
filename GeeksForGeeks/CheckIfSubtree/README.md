# Check if a Tree is Subtree of Another Tree | DFS | GeeksForGeeks

---
> Video description: https://youtu.be/xOS3lxbpnCI

[Problem](https://www.geeksforgeeks.org/problems/check-if-subtree/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/xOS3lxbpnCI/0.jpg)](https://youtu.be/xOS3lxbpnCI)

---

**Difficulty:** Easy  
**Topics:** Binary Tree | Tree Traversal | Recursion | DFS  
**Companies:** Amazon | Microsoft | Google | Adobe

---

## Problem Statement

Given two binary trees with roots `root1` and `root2`, check whether `root2` is a **subtree** of `root1`.

A tree `T2` is a subtree of `T1` if there exists a node in `T1` such that the subtree rooted at that node is **identical** in structure and node values to `T2`.

> Note: An empty tree is a subtree of every tree.

## Examples

**Example 1:**
```
root1:          root2:
      26               10
     /  \             /  \
   10    3           4    6
   / \    \
  4   6    3

Output: true

Explanation: The subtree rooted at 10 in root1 is identical to root2.
```

**Example 2:**
```
root1:          root2:
      26               10
     /  \             /  \
   10    3           4    6
   / \    \           \
  4   6    3           2

Output: false

Explanation: root2 has a right child (2) under 4, but root1's matching
             node 4 has no children — structures differ.
```

**Example 3:**
```
root1:    1        root2: (null)

Output: true

Explanation: An empty tree is always a subtree.
```

## Constraints
- 1 ≤ number of nodes ≤ 10³
- 1 ≤ node data ≤ 10⁴

---

## Approach — DFS with Exact-Match Check

**Analogy:** Just like substring search — for each character in `s`, check whether the string starting there matches `t`. Here, for each node in `root1`, check whether the subtree rooted there exactly matches `root2`.

### Two helper functions

**`isSubTree(root1, root2)`** — traverse every node of `root1`:
- If `root2` is null → it is trivially a subtree, return `true`.
- If `root1` is null but `root2` is not → no match possible, return `false`.
- Try an exact match starting at the current node (`check`).
- If that fails, recurse into left and right subtrees.

**`check(r1, r2)`** — verify two trees are identical:
- Both null → identical ✓
- One null, other not → not identical ✗
- Values differ → not identical ✗
- Recurse: left must match left, right must match right.

### Walkthrough

```
root1:              root2:
      26                 10
     /  \               /  \
   10    3             4    6
   / \    \
  4   6    3

isSubTree(26, 10):
  check(26, 10) → 26 ≠ 10 → false
  isSubTree(10, 10):
    check(10, 10):
      10 == 10 ✓
      check(4, 4):
        4 == 4 ✓
        check(null, null) → true
        check(null, null) → true  ✓
      check(6, 6):
        6 == 6 ✓
        check(null, null) → true
        check(null, null) → true  ✓
    → true ✓

Output: true
```

### Complexity

| | Value |
|---|---|
| **Time** | O(m × n) — for each of the m nodes in root1, an exact match costs O(n) where n = size of root2 |
| **Space** | O(h₁) — recursion stack depth equals height of root1 |

---

## Related Problems
- Check if Two Trees are Identical
- Size of Binary Tree
- Height of Binary Tree

## Tags
`binary-tree` `subtree` `recursion` `DFS` `tree-matching` `identical-trees` `java` `geeksforgeeks`
