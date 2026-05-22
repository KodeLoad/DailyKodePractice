# Transform to Sum Tree | Binary Tree | Post-order DFS | GeeksForGeeks

---
> Video description: https://youtu.be/pFr98ndtEjk

[Problem](https://www.geeksforgeeks.org/problems/transform-to-sum-tree/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/pFr98ndtEjk/0.jpg)](https://youtu.be/pFr98ndtEjk)

---

**Difficulty:** Medium  
**Topics:** Binary Tree | Post-order Traversal | Recursion | DFS  
**Companies:** Amazon | Microsoft | Flipkart  
**Time Complexity:** O(n) | **Space Complexity:** O(h)

---

## What Is a Sum Tree?

A **Sum Tree** is a binary tree where every node's value equals the **sum of all values in its left and right subtrees**. Leaf nodes become `0` (they have no children, so their subtree sum is 0).

```
Original Tree:         Sum Tree:
       10                  30
      /  \                /  \
     -2    6             -2    6
    / \   / \           / \   / \
   8   -4  7   5       0   0  0   0

Node 10 → left_sum(-2+8-4) + right_sum(6+7+5) = 2 + 18 = 20... 

Wait — sum tree replaces each node with sum of ALL nodes in both subtrees:
  Leaf 8   → 0 (no children)
  Leaf -4  → 0
  Node -2  → 8 + (-4) = 4... 

Actually: new_value = left_subtree_sum + right_subtree_sum
  where subtree_sum = node's original value + all descendants
```

---

## Problem Statement — Convert Binary Tree to Sum Tree In-Place

Given a binary tree, transform it into a **Sum Tree** where each node's value is replaced by the **sum of all original values in its left and right subtrees**. Modify the tree **in-place** and return nothing.

## Examples

**Example 1:**
```
Input:
         1
        / \
       2   3

Output:
         5
        / \
       0   0

Node 2 (leaf) → 0
Node 3 (leaf) → 0
Node 1        → original(2) + original(3) = 2 + 3 = 5
```

**Example 2:**
```
Input:
          10
         /  \
        -2    6
       / \   / \
      8  -4  7   5

Output:
          20
         /  \
          4    12
         / \   / \
         0   0  0   0

Node 8   (leaf) → 0
Node -4  (leaf) → 0
Node 7   (leaf) → 0
Node 5   (leaf) → 0
Node -2          → 8 + (-4) = 4
Node  6          → 7 + 5    = 12
Node 10          → (8-4-2) + (7+5+6) = ... 
                  = subtreeSum(-2) + subtreeSum(6)
                  = (4 + 8 - 4) + (12 + 7 + 5)
                  = 8 + 24 = ... 

Simpler: new(10) = original(-2) + original(8) + original(-4)
                 + original(6)  + original(7)  + original(5)
               = -2 + 8 - 4 + 6 + 7 + 5 = 20 ✓
```

## Constraints
- 1 ≤ number of nodes ≤ 10⁴
- -10⁴ ≤ node.data ≤ 10⁴

---

## Approach — Post-order DFS (Bottom-Up Recursion)

**Key Insight:** To compute a node's new value, we need the sums of its left and right subtrees first — this is naturally a **post-order** (left → right → root) traversal.

The tricky part: when updating a node's value, the parent still needs the node's **original contribution** to the overall subtree sum. So the recursive function returns the **total subtree sum** (original value + children sums) for the parent to use, while simultaneously updating the node's value.

### Return Value

```
toSumTree(node) returns:
    node.original_data + sum_of_all_descendants
    = curData + left + right

This is what the parent needs to correctly compute its new value.
```

### Algorithm

1. Base case: if `node == null`, return `0`.
2. Recurse left → get `left` (total sum of left subtree including root of left).
3. Recurse right → get `right` (total sum of right subtree including root of right).
4. Save `curData = node.data` (backup before overwriting).
5. Set `node.data = left + right` (new sum tree value).
6. Return `curData + left + right` (original contribution for parent).

### Walkthrough

```
Tree:
       1
      / \
     2   3

toSumTree(2):
  left  = toSumTree(null) = 0
  right = toSumTree(null) = 0
  curData = 2
  node.data = 0 + 0 = 0
  return 2 + 0 + 0 = 2   ← parent gets 2 (original value of node 2)

toSumTree(3):
  left  = 0,  right = 0
  curData = 3
  node.data = 0
  return 3   ← parent gets 3

toSumTree(1):
  left  = 2  (from node 2's subtree sum)
  right = 3  (from node 3's subtree sum)
  curData = 1
  node.data = 2 + 3 = 5
  return 1 + 2 + 3 = 6

Result:
       5
      / \
     0   0   ✓
```

### Why Post-order and Not Pre-order?

Pre-order would overwrite a node's value **before** its children have reported their sums. Post-order processes children first, so by the time we update the parent, both child subtree sums are already known.

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — every node visited exactly once |
| **Space** | O(h) — recursion stack depth equals tree height (O(log n) balanced, O(n) skewed) |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Not saving the original value before overwriting**
```java
// Wrong — node.data is already changed when children recurse
root.data = left + right;
return root.data + left + right;  // returns wrong sum to parent

// Correct — backup first
int curData = root.data;
root.data = left + right;
return curData + left + right;
```

**Mistake 2: Using pre-order instead of post-order**
Updating the node before getting child results means children see the already-modified parent value if they reference it.

**Mistake 3: Returning only left + right instead of curData + left + right**
The parent needs the original value of this node included in the subtree sum — without it, the grandparent's new value will be wrong.

---

## Related Problems
- Size of Binary Tree
- Height of Binary Tree
- Check for Sum Tree

---

## Tags

Binary Tree | Post-order DFS | Sum Tree | In-Place Tree Modification | Recursion | Bottom-Up | Java | GeeksForGeeks | Transform to Sum Tree

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/TransformToSumTree

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ What a Sum Tree is and how it differs from the original tree
✅ Why post-order DFS is the only correct traversal order here
✅ The "backup before overwrite" trick — why it's critical
✅ Step-by-step dry run with full tree walkthrough
✅ Common mistakes that silently give wrong answers

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Transform to Sum Tree · Post-order DFS · Binary Tree · Recursion · In-Place Tree Modification · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/transform-to-sum-tree/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#TransformToSumTree #GeeksForGeeks #BinaryTree #PostOrderDFS #TreeProblems
#DataStructures #JavaProgramming #DailyChallenge #Recursion #DSAWithOBrutus #KodeLoad
```
