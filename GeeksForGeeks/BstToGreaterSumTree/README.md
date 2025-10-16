# BST to Greater Sum Tree
---

> Video description: https://youtu.be/4kimy_ElvaU

[Problem](https://www.geeksforgeeks.org/problems/bst-to-greater-sum-tree/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/4kimy_ElvaU/0.jpg)](https://youtu.be/4kimy_ElvaU)

---

**Difficulty:** Medium  
**Accuracy:** 66.73%  
**Submissions:** 16K+  
**Points:** 4  
**Average Time:** 40m

Given the root of a BST with unique node values, transform it into greater sum tree where each node contains sum of all nodes greater than that node.

## Examples:

**Example 1:**
```
Input: root = [11, 2, 29, 1, 7, 15, 40, N, N, N, N, N, N, 35, N]
        11
       /  \
      2    29
     / \   / \
    1   7 15  40
              /
             35

Output: [119, 137, 75, 139, 130, 104, 0, N, N, N, N, N, N, 40, N]
        119
       /   \
     137    75
     / \    / \
   139 130 104 0
               /
              40

Explanation: Every node is replaced with the sum of nodes greater than itself.
```

**Example 2:**
```
Input: root = [2, 1, 6, N, N, 3, 7]
      2
     / \
    1   6
       / \
      3   7

Output: [16, 18, 7, N, N, 13, 0]
       16
      /  \
     18   7
         / \
        13  0

Explanation: Every node is replaced with the sum of nodes greater than itself.
```

## Constraints:
- 1 ≤ node->data ≤ 3×10⁴
- 1 ≤ number of nodes ≤ 3×10⁴

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(h) where h is the height of the tree

## Company Tags:
Amazon | Microsoft

## Topic Tags:
Trees | Binary Search Tree | Data Structures | Reverse In-order Traversal | Recursion

## Approach:
The problem can be solved using reverse in-order traversal:

1. **Key Observation:**
   - In a BST, reverse in-order traversal (Right -> Root -> Left) visits nodes in descending order
   - We need to replace each node with the sum of all nodes greater than it
   - By traversing in descending order, we can maintain a running sum

2. **Algorithm:**
   - Maintain a variable to track cumulative sum of nodes processed so far
   - Perform reverse in-order traversal:
     - **Right Subtree:** Process right subtree first (larger values)
     - **Current Node:** 
       - Add current sum to node's value
       - Update node's value with this sum
       - Update cumulative sum for next nodes
     - **Left Subtree:** Process left subtree (smaller values)

3. **Step-by-Step Process:**
   - Initialize sum = 0
   - Traverse right subtree (all nodes greater than current)
   - Update current node: node->data = node->data + sum
   - Update sum for next iteration: sum = node->data
   - Traverse left subtree

4. **Implementation Approaches:**
   - **Recursive:** Pass sum as reference or use class variable
   - **Iterative:** Use stack with reverse in-order traversal

5. **Example Walkthrough (Example 2):**
```
   Initial Tree: 2, 1, 6, 3, 7
   
   Reverse In-order: 7 -> 6 -> 3 -> 2 -> 1
   
   Visit 7: sum=0, new_value=7+0=7, sum becomes 7
   Visit 6: sum=7, new_value=6+7=13, sum becomes 13
   Visit 3: sum=13, new_value=3+13=16, sum becomes 16
   Visit 2: sum=16, new_value=2+16=18, sum becomes 18
   Visit 1: sum=18, new_value=1+18=19, sum becomes 19
   
   Wait, let me recalculate based on output...
   
   Actually: Each node = sum of all nodes GREATER than it
   Visit 7: sum=0 (no greater nodes), new_value=0
   Visit 6: sum=7, new_value=7
   Visit 3: sum=7+6=13, new_value=13
   Visit 2: sum=7+6+3=16, new_value=16
   Visit 1: sum=7+6+3+2=18, new_value=18
```

## Related Interview Experiences:
- Binary Search Tree to Greater Sum Tree (LeetCode 1038)
- Convert BST to Balanced BST
- BST Iterator Problems
- Tree Traversal Variations

## Related Articles:
- Binary Search Tree Traversals
- Reverse In-order Traversal
- Tree Modification Problems
- Cumulative Sum in Trees
  