# Flattening a Linked List
---

> Video description: https://youtu.be/fBhfieakkC0

[Problem](https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/fBhfieakkC0/0.jpg)](https://youtu.be/fBhfieakkC0)

---

**Difficulty:** Medium  
**Accuracy:** 44.15%  
**Submissions:** 278K+  
**Points:** 4  
**Average Time:** 35m

Given a **linked list** where every node represents a linked list and contains two pointers:
1. A **next** pointer to the next node in the main list
2. A **bottom** pointer to a linked list where this node is head

Each of the sub-linked lists is in **sorted order**. Flatten the linked list such that all the nodes appear in a **single level** while maintaining the **sorted order**.

**Note:** The flattened list should use only **bottom pointer** for linking.

## Examples:

**Example 1:**
```
Input:
5 -> 10 -> 19 -> 28
|    |     |     |
7    20    22    35
|          |     |
8          50    40
|                |
30               45

Output: 5->7->8->10->19->20->22->28->30->35->40->45->50

Explanation: The resultant linked list has every node in a single level.
(Note: | represents bottom pointer)
```

**Example 2:**
```
Input:
5 -> 10 -> 19
|    |     
7    20    
|          
8          

Output: 5->7->8->10->19->20

Explanation: All nodes are flattened into a single sorted list.
```

## Constraints:
- 0 ≤ number of nodes ≤ 50
- 1 ≤ node->data ≤ 10³
- All linked lists are sorted

## Expected Complexities:
- **Time Complexity:** O(N × M) where N = nodes in main list, M = nodes in sub-lists
- **Space Complexity:** O(1) or O(N) for recursion

## Company Tags:
Amazon | Microsoft | Google | Flipkart | Adobe | Paytm | Samsung

## Topic Tags:
Linked List | Merge Sort | Recursion | Data Structures

## Approach:

### Problem Understanding:

**Structure:**
```
Node structure:
- data: integer value
- next: pointer to next node (horizontal)
- bottom: pointer to bottom node (vertical)

Visual:
5 -> 10 -> 19
|    |     
7    20    
|          
8          
```

**Goal:**
- Merge all sub-lists into single sorted list
- Use only bottom pointers in result
- Maintain sorted order

### Key Observations:

1. **Each vertical list is sorted**
2. **Horizontal nodes form separate sorted lists**
3. **Problem reduces to merging K sorted lists**
4. **Can use merge sort approach**

### Approach 1: Merge Two Lists at a Time (Optimal)

**Algorithm:**
```
function flatten(root):
    // Base case
    if root == null or root.next == null:
        return root
    
    // Recursively flatten rest of list
    root.next = flatten(root.next)
    
    // Merge current list with flattened rest
    root = merge(root, root.next)
    
    return root

function merge(a, b):
    // Merge two sorted linked lists
    if a == null: return b
    if b == null: return a
    
    result = null
    if a.data <= b.data:
        result = a
        result.bottom = merge(a.bottom, b)
    else:
        result = b
        result.bottom = merge(a, b.bottom)
    
    result.next = null  // Important!
    return result
```

**Complexity:**
- Time: O(N × M) - merge operations
- Space: O(N) - recursion stack

### Approach 2: Priority Queue (Alternative)

**Algorithm:**
```
1. Add all head nodes to min heap
2. Extract minimum
3. Add its bottom node to heap
4. Build result list
5. Repeat until heap empty
```

**Complexity:**
- Time: O(N × M × log K) where K = number of lists
- Space: O(K) for heap

### Visual Understanding:

**Step-by-step merge:**
```
Initial:
5 -> 10 -> 19
|    |     
7    20    
|          
8          

Step 1: Flatten right part (10->19)
5 -> [10->19->20 merged]
|     
7     
|     
8     

Step 2: Merge 5's list with result
[5->7->8->10->19->20]

Final result (bottom pointers):
5->7->8->10->19->20
```

### Why Merge Approach Works:

**Recursion Strategy:**
```
1. Process from rightmost list
2. Each recursive call flattens rest
3. Merge current with flattened result
4. Maintains sorted order
```

**Merge Two Sorted Lists:**
```
Similar to merging in merge sort:
- Compare heads of both lists
- Choose smaller
- Recursively merge rest
- Use bottom pointers only
```

### Edge Cases:

1. **Empty list:** return null
2. **Single node:** return as is
3. **No bottom nodes:** merge horizontal lists
4. **All same values:** maintain order
5. **Long vertical chains:** handle deep recursion

### Common Mistakes:

1. **Using next pointer in result**
   - Must use only bottom pointer
   - Set next = null after merge

2. **Not handling null properly**
   - Check both lists in merge

3. **Wrong merge order**
   - Should maintain sorted order

4. **Stack overflow**
   - Deep recursion for long lists
   - Consider iterative approach

### Key Concepts:

**Merge Two Sorted Lists:**
```python
def merge(a, b):
    if not a: return b
    if not b: return a
    
    if a.data <= b.data:
        result = a
        result.bottom = merge(a.bottom, b)
    else:
        result = b
        result.bottom = merge(a, b.bottom)
    
    result.next = None
    return result
```

**Main Flatten Function:**
```python
def flatten(root):
    if not root or not root.next:
        return root
    
    # Flatten rest
    root.next = flatten(root.next)
    
    # Merge current with rest
    root = merge(root, root.next)
    
    return root
```

### Optimization Notes:

**Space Optimization:**
- Use iterative merge if possible
- Avoid deep recursion

**Time Optimization:**
- Early termination in merge
- Efficient comparison

### Comparison with Array Merge:

| Aspect | Array | Linked List |
|--------|-------|-------------|
| Access | O(1) | O(n) |
| Space | O(n) extra | O(1) in-place |
| Merge | Create new array | Reconnect pointers |
| Complexity | Same time | Better space |

## Related Problems:
- Merge Two Sorted Lists (LeetCode 21)
- Merge K Sorted Lists (LeetCode 23)
- Flatten Binary Tree to Linked List (LeetCode 114)
- Merge Sorted Array (LeetCode 88)
- Sort List (LeetCode 148)

## Related Articles:
- Merge Sort Algorithm
- Linked List Manipulation
- K-way Merge Problems
- Recursion in Linked Lists
- Priority Queue Applications

## Keywords:
flatten linked list, merge sorted lists, 2d linked list, vertical linked list, bottom pointer, merge k sorted lists, linked list flattening, geeksforgeeks hard, linked list algorithms, recursive merge

---

**SEO Tags:** #LinkedList #MergeSort #Recursion #2DLinkedList #FlattenLinkedList #MergeSortedLists #DSA #CodingInterview #GeeksforGeeks #FAANG

**Problem Category:** Linked List Manipulation, Merge Algorithms, Recursion

**Difficulty Level:** Medium-Hard (Requires merge logic understanding)

**Prerequisites:**
- Merge Two Sorted Lists
- Linked List Traversal
- Recursion Concepts
- Pointer Manipulation

**Learning Outcomes:**
- Master merge algorithms
- Handle 2D linked structures
- Recursive problem solving
- Pointer manipulation skills

**Interview Frequency:** High (Popular in product companies)

**Key Technique:** Recursive merge of sorted linked lists using bottom pointers
