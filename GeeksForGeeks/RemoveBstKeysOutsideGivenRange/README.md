# Remove BST keys outside given range
---

> Video description: https://youtu.be/tmKA1s0vq9E

[Problem](https://www.geeksforgeeks.org/problems/remove-bst-keys-outside-given-range/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/tmKA1s0vq9E/0.jpg)](https://youtu.be/tmKA1s0vq9E)

---

**Difficulty:** Medium  
**Accuracy:** 62.05%  
**Submissions:** 9K+  
**Points:** 4  
**Average Time:** 45m

Given the root of a Binary Search Tree (BST) and two integers l and r, remove all the nodes whose values lie outside the range [l, r].

**Note:** The modified tree should also be BST.

## Examples:

**Example 1:**
```
Input: root = [6, -13, 14, N, -8, 13, 15, N, N, 7], l = -10, r = 13
        6
       / \
     -13  14
       \  / \
       -8 13 15
           /
          7

Output: [6, -8, 13, N, N, 7]
        6
       / \
     -8  13
          /
         7

Explanation: All the nodes outside the range [-10, 13] are removed and the modified tree is a valid BST.
```

**Example 2:**
```
Input: root = [14, 4, 16, 2, 8, 15, N, -8, 3, 7, 10], l = 2, r = 6
        14
       /  \
      4    16
     / \   /
    2   8 15
   / \ / \
  -8 3 7 10

Output: [4, 2, N, N, 3]
      4
     /
    2
     \
      3

Explanation: All the nodes outside the range [2, 6] are removed and the modified tree is a valid BST.
```

## Constraints:
- 1 ≤ number of nodes ≤ 10⁴
- 1 ≤ node->data ≤ 10⁴
- 1 ≤ l ≤ r ≤ 10⁴

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(h) where h is the height of the tree

## Company Tags:
Microsoft | Samsung

## Topic Tags:
Trees | Binary Search Tree | Data Structures | Recursion | Tree Modification

## Approach:
The problem can be solved using a recursive post-order approach:

1. **BST Property Utilization:**
   - If current node's value is less than l, remove the entire left subtree
   - If current node's value is greater than r, remove the entire right subtree
   - Recursively process left and right subtrees before processing current node

2. **Algorithm:**
   - **Base Case:** If node is null, return null
   - **Recursive Step:**
     - Recursively trim the left subtree
     - Recursively trim the right subtree
   - **Node Processing:**
     - If node->data < l: Return the trimmed right subtree (discard current node and left subtree)
     - If node->data > r: Return the trimmed left subtree (discard current node and right subtree)
     - If l ≤ node->data ≤ r: Keep the node with trimmed subtrees

3. **Key Insight:**
   - Use BST property to prune entire subtrees efficiently
   - Post-order processing ensures children are handled before parent
   - The resulting tree maintains BST properties

4. **Edge Cases:**
   - Empty tree returns null
   - Single node either kept or removed based on range
   - All nodes outside range results in empty tree (return null)

## Related Interview Experiences:
- BST Deletion and Modification
- Range-based Tree Operations
- Trimming Binary Search Trees
- BST Validation after Modification

## Related Articles:
- Binary Search Tree Properties
- Tree Recursion Patterns
- BST Node Deletion
- In-place Tree Modification