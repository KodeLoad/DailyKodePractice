# Make BST from Array | Array to BST

----

problem link : https://www.geeksforgeeks.org/problems/k-pangrams0909/1

[Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/fJeHYjjJP4E/0.jpg)](https://youtu.be/fJeHYjjJP4E)

---

Difficulty: EasyAccuracy: 46.02%Submissions: 79K+Points: 2

Given a sorted array. Convert it into a Height Balanced Binary Search Tree (BST). Return the root of the BST.

    Height-balanced BST means a binary tree in which the depth of the left subtree and the right subtree of every node never differ by more than 1.

Note: The driver code will check the BST, if it is a Height-balanced BST, the output will be true otherwise the output will be false.

```
Examples :

Input: nums = [1, 2, 3, 4]
Output: true
Explanation: The preorder traversal of the following BST formed is [2, 1, 3, 4]:
           2
         /   \
        1     3
               \
                4

Input: nums = [1, 2, 3, 4, 5, 6, 7]
Ouput: true
Explanation: The preorder traversal of the following BST formed is [4, 2, 1, 3, 6, 5, 7]:
        4
       / \
      2   6
     / \   / \
    1  3  5  7
```

> Expected Time Complexity: O(n)
> Expected Auxillary Space: O(n)

Constraints:
1 ≤ nums.size() ≤ 105
1 ≤ nums[i] ≤ 105


Company Tags
Snapdeal | Adobe | Cisco
