# Median of BST
---

> Video description: https://youtu.be/rOEqgoDO1YA

[Problem](https://www.geeksforgeeks.org/problems/median-of-bst/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/rOEqgoDO1YA/0.jpg)](https://youtu.be/rOEqgoDO1YA)

---

**Difficulty:** Medium  
**Accuracy:** 27.43%  
**Submissions:** 98K+  
**Points:** 4  
**Average Time:** 45m

You are given the root of a Binary Search Tree, find the median of it.

Let the nodes of the BST, when written in ascending order (inorder traversal), be represented as V₁, V₂, V₃, …, Vₙ, where n is the total number of nodes in the BST.

**Median Rules:**
- If number of nodes are **even**: return V(n/2)
- If number of nodes are **odd**: return V((n+1)/2)

## Examples:

**Example 1:**
```
Input: root = [20, 8, 22, 4, 12, N, N, N, N, 10, 14]
        20
       /  \
      8    22
     / \
    4   12
       /  \
      10  14

Output: 12
Explanation: The inorder of given BST is 4, 8, 10, 12, 14, 20, 22. 
Here, n = 7, so, here median will be ((7+1)/2)th value, i.e., 4th value, i.e, 12.
```

**Example 2:**
```
Input: root = [5, 4, 8, 1]
      5
     / \
    4   8
   /
  1

Output: 4
Explanation: The inorder of given BST is 1, 4, 5, 8. 
Here, n = 4(even), so, here median will be (4/2)th value, i.e., 2nd value, i.e, 4.
```

## Constraints:
- 1 ≤ number of nodes ≤ 10⁵
- 1 ≤ node.data ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(h) where h is the height of the tree

## Company Tags:
Amazon

## Topic Tags:
Trees | Binary Search Tree | Data Structures | In-order Traversal | Morris Traversal

## Approach:

### Approach 1: Using Array (Simple)
1. **Store In-order Traversal:**
   - Perform in-order traversal and store all node values in an array
   - BST in-order gives sorted order
   
2. **Find Median:**
   - Count total nodes (n)
   - If n is odd: return array[(n+1)/2 - 1] (using 0-based indexing)
   - If n is even: return array[n/2 - 1] (using 0-based indexing)

3. **Complexity:**
   - Time: O(n) for traversal
   - Space: O(n) for storing values

### Approach 2: Two-Pass Solution (Optimized Space)
1. **First Pass - Count Nodes:**
   - Count total number of nodes in BST
   - Calculate median position based on count

2. **Second Pass - Find Median:**
   - Perform in-order traversal again
   - Use counter to track current position
   - Return value when counter reaches median position

3. **Complexity:**
   - Time: O(n) - two traversals
   - Space: O(h) - recursion stack only

### Approach 3: Morris Traversal (Most Optimized)
1. **Morris In-order Traversal:**
   - First pass: Count total nodes using Morris traversal
   - Second pass: Find median element using Morris traversal
   - No recursion or stack needed

2. **Complexity:**
   - Time: O(n)
   - Space: O(1) - constant space

### Key Observations:
- **Median Position Calculation:**
  - For odd n (e.g., n=7): median index = (7+1)/2 = 4th element (index 3)
  - For even n (e.g., n=4): median index = 4/2 = 2nd element (index 1)
  
- **BST Property:**
  - In-order traversal gives sorted sequence
  - No need for explicit sorting

- **Index Conversion:**
  - Problem uses 1-based indexing
  - Array implementation uses 0-based indexing
  - Remember to adjust: position - 1 for array index

## Related Interview Experiences:
- Finding kth Smallest Element in BST
- BST In-order Traversal Problems
- Morris Traversal Applications
- Streaming Median Problems

## Related Articles:
- Binary Search Tree Traversals
- Morris In-order Traversal
- Finding Median in Data Structures
- Space-Optimized Tree Algorithms