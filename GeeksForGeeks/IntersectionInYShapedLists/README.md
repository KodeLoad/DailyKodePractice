# Intersection Point in Y Shaped Linked Lists
---

> Video description: https://youtu.be/F4igkbbjcEM

[Problem](https://www.geeksforgeeks.org/problems/intersection-point-in-y-shapped-linked-lists/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/F4igkbbjcEM/0.jpg)](https://youtu.be/F4igkbbjcEM)

---

**Difficulty:** Medium  
**Accuracy:** 44.67%  
**Submissions:** 475K+  
**Points:** 4  
**Average Time:** 30m

Given two singly linked lists, return the point where two linked lists intersect. If the linked lists do not merge at any point, return **-1**.

**Note:** The linked lists must retain their original structure after the function returns.

## Examples:

**Example 1:**
```
Input:
LinkedList1: 3->6->9->common
LinkedList2: 10->common
common: 15->30->null

Output: 15

Explanation:
List1: 3 → 6 → 9 ↘
                  15 → 30 → null
List2: 10 --------↗

The intersection point is at node with value 15.
```

**Example 2:**
```
Input:
LinkedList1: 4->1->common
LinkedList2: 5->6->1->common
common: 8->4->5->null

Output: 8

Explanation:
List1: 4 → 1 --------↘
                      8 → 4 → 5 → null
List2: 5 → 6 → 1 ----↗

The intersection point is at node with value 8.
```

**Example 3:**
```
Input:
LinkedList1: 1->2->3
LinkedList2: 4->5->6

Output: -1

Explanation:
List1: 1 → 2 → 3 → null
List2: 4 → 5 → 6 → null

No intersection point exists.
```

## Constraints:
- 2 ≤ size of first linkedlist + size of second linkedlist ≤ 2 × 10⁵
- -1000 ≤ node->data ≤ 1000

## Expected Complexities:
- **Time Complexity:** O(m + n) where m, n are lengths of linked lists
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Microsoft | Google | Facebook | Adobe | Bloomberg | Apple | Uber

## Topic Tags:
Linked List | Two Pointers | Hash Table

## Approach:

### Problem Understanding:

**Y-Shaped Linked List:**
- Two separate linked lists
- At some point, they merge and share remaining nodes
- After intersection, both lists follow same path
- Need to find first common node

**Key Observations:**
1. If lists intersect, they share same tail
2. Different lengths before intersection
3. After intersection, paths are identical
4. Must find exact intersection node

### Brute Force Analysis:

**Approach 1: Nested Loop**
```
for each node in list1:
    for each node in list2:
        if node1 == node2:
            return node
return -1
```

**Complexity:**
- Time: O(m × n) - too slow
- Space: O(1)

**Approach 2: Using Hash Set**
```
// Store all nodes from list1
set = new HashSet()
while list1 != null:
    set.add(list1)
    list1 = list1.next

// Check nodes in list2
while list2 != null:
    if list2 in set:
        return list2.data
    list2 = list2.next

return -1
```

**Complexity:**
- Time: O(m + n)
- Space: O(m) - not optimal

### Optimal Approach: Two Pointer Technique

**Key Insight:**
- If we traverse both lists and switch to other list after reaching end
- Both pointers will meet at intersection point
- They cover same total distance

**Algorithm:**
```
function getIntersectionNode(head1, head2):
    if head1 == null or head2 == null:
        return -1
    
    ptr1 = head1
    ptr2 = head2
    
    // Traverse until they meet
    while ptr1 != ptr2:
        // Move ptr1: if end reached, switch to head2
        if ptr1 == null:
            ptr1 = head2
        else:
            ptr1 = ptr1.next
        
        // Move ptr2: if end reached, switch to head1
        if ptr2 == null:
            ptr2 = head1
        else:
            ptr2 = ptr2.next
    
    // ptr1 == ptr2 (either intersection or both null)
    if ptr1 == null:
        return -1
    return ptr1.data
```

### Why This Works:

**Mathematical Proof:**
```
Let:
- Length before intersection in list1 = a
- Length before intersection in list2 = b
- Length after intersection (common part) = c

Total length of list1 = a + c
Total length of list2 = b + c

Pointer 1 travels: a + c + b
Pointer 2 travels: b + c + a

Both travel same distance: a + b + c
They meet at intersection point!
```

**Visual Understanding:**
```
List1: A → A → A → C → C → C
       ↑____________↑
       a=3         intersection

List2: B → B → C → C → C
       ↑____↑
       b=2  intersection

Pointer 1: A A A C C C | B B C (meets at C)
Pointer 2: B B C C C | A A A C (meets at C)
           
Distance: (3 + 3) for ptr1 switch + 2 = 8
Distance: (2 + 3) for ptr2 switch + 3 = 8
Same distance, meet at intersection!
```

### Step-by-Step Walkthrough (Example 1):
```
Input:
List1: 3 → 6 → 9 → 15 → 30 → null
List2: 10 → 15 → 30 → null

Initialize:
ptr1 = 3 (head1)
ptr2 = 10 (head2)

Iteration 1:
ptr1: 3 → 6, ptr2: 10 → 15
ptr1 ≠ ptr2, continue

Iteration 2:
ptr1: 6 → 9, ptr2: 15 → 30
ptr1 ≠ ptr2, continue

Iteration 3:
ptr1: 9 → 15, ptr2: 30 → null
ptr1 ≠ ptr2, continue

Iteration 4:
ptr1: 15 → 30, ptr2: null → head1 (10)
ptr1 ≠ ptr2, continue

Iteration 5:
ptr1: 30 → null, ptr2: 10 → head2 (3)
ptr1 ≠ ptr2, continue

Iteration 6:
ptr1: null → head2 (10), ptr2: 3 → 6
ptr1 = 10, ptr2 = 6
ptr1 ≠ ptr2, continue

Iteration 7:
ptr1: 10 → 15, ptr2: 6 → 9
ptr1 ≠ ptr2, continue

Iteration 8:
ptr1: 15, ptr2: 9 → 15
ptr1 ≠ ptr2, continue

Iteration 9:
ptr1: 15, ptr2: 15
ptr1 == ptr2! Found intersection!

Output: 15 ✓
```

### Step-by-Step Walkthrough (Example 3 - No Intersection):
```
Input:
List1: 1 → 2 → 3 → null
List2: 4 → 5 → 6 → null

Process:
ptr1 traverses: 1 → 2 → 3 → null → 4 → 5 → 6 → null
ptr2 traverses: 4 → 5 → 6 → null → 1 → 2 → 3 → null

Both reach null at same time
ptr1 == ptr2 == null

Output: -1 ✓
```

### Alternative Approach: Length Difference

**Algorithm:**
```
1. Calculate length of both lists: len1, len2
2. Find difference: diff = |len1 - len2|
3. Move pointer in longer list by diff steps
4. Now both pointers are equidistant from end
5. Move both together until they meet
```

**Complexity:**
- Time: O(m + n)
- Space: O(1)

### Visual Examples:

**Example with Different Lengths:**
```
List1: 1 → 2 → 3 → 4 ↘
                       5 → 6 → 7
List2: 9 → 10 --------↗

Length difference = 4 - 2 = 2

After adjusting:
List1 (from 3): 3 → 4 → 5 → 6 → 7
List2 (from 9): 9 → 10 → 5 → 6 → 7
                         ↑
                    meet here at 5
```

### Edge Cases:

1. **One or both lists empty:**
   - Input: null, list2
   - Output: -1

2. **No intersection:**
   - Input: 1→2→3, 4→5→6
   - Output: -1

3. **Intersection at head:**
   - List1: 5 → 6 → 7
   - List2: 5 → 6 → 7
   - Output: 5

4. **One list is subset:**
   - List1: 1 → 2 → 3 → 4 → 5
   - List2: 3 → 4 → 5
   - Output: 3

5. **Same length lists:**
   - Both lists equal length
   - Algorithm works same way

6. **Single node intersection:**
   - List1: 1 → 2 → 3
   - List2: 4 → 3
   - Output: 3

### Common Mistakes:

1. **Comparing values instead of nodes:**
```
   Wrong: if ptr1.data == ptr2.data
   Right: if ptr1 == ptr2
```

2. **Not handling null properly:**
   - Must check both null cases
   - Return -1 if no intersection

3. **Infinite loop:**
   - Ensure pointers switch lists correctly
   - Both must reach null if no intersection

4. **Modifying list structure:**
   - Problem requires maintaining original structure
   - Don't break or reconnect nodes

5. **Not returning node data:**
   - Return ptr.data, not ptr itself
   - Return -1 for no intersection

### Implementation Details:

**Two Pointer (Optimal):**
```python
def getIntersectionNode(head1, head2):
    if not head1 or not head2:
        return -1
    
    ptr1, ptr2 = head1, head2
    
    while ptr1 != ptr2:
        ptr1 = ptr1.next if ptr1 else head2
        ptr2 = ptr2.next if ptr2 else head1
    
    return ptr1.data if ptr1 else -1
```

**Length Difference Method:**
```python
def getIntersectionNode(head1, head2):
    len1 = getLength(head1)
    len2 = getLength(head2)
    
    # Move ahead in longer list
    if len1 > len2:
        for _ in range(len1 - len2):
            head1 = head1.next
    else:
        for _ in range(len2 - len1):
            head2 = head2.next
    
    # Move together
    while head1 and head2:
        if head1 == head2:
            return head1.data
        head1 = head1.next
        head2 = head2.next
    
    return -1

def getLength(head):
    count = 0
    while head:
        count += 1
        head = head.next
    return count
```

### Complexity Analysis:

**Two Pointer Approach:**
- **Time:** O(m + n)
  - Each pointer traverses at most m + n nodes
  - Single pass through combined length
- **Space:** O(1)
  - Only two pointers used
  - No extra data structures

**Hash Set Approach:**
- **Time:** O(m + n)
- **Space:** O(m) or O(n)
- Trade-off: More space for simpler logic

**Why Two Pointer is Better:**
- Same time complexity
- Optimal space: O(1)
- Elegant solution
- Industry standard approach

### Proof of Correctness:

**Case 1: Lists Intersect**
```
Both pointers travel same total distance:
- Distance = (length to intersection in list1) + 
             (length to intersection in list2) + 
             (common length)
They meet at intersection node
```

**Case 2: No Intersection**
```
Both pointers travel: len1 + len2
Both reach null at same time
ptr1 == ptr2 == null
Return -1
```

## Related Interview Experiences:
- Intersection of Two Linked Lists (LeetCode 160)
- Merge Two Sorted Lists
- Middle of Linked List
- Linked List Cycle II
- Remove Nth Node From End

## Related Articles:
- Two Pointer Technique in Linked Lists
- Linked List Interview Problems
- Y-Shaped Data Structures
- Floyd's Cycle Detection Algorithm
- Linked List Manipulation

## Keywords:
intersection point linked list, Y shaped linked list, two pointer linked list, linked list intersection, find merge point, linked list algorithms, O(1) space linked list, DSA interview questions, geeksforgeeks linked list

---

**SEO Tags:** #LinkedList #Intersection #TwoPointers #YShapedList #LinkedListMerge #DSA #CodingInterview #GeeksforGeeks #FAANG #DataStructures #Algorithms

**Problem Category:** Linked List Manipulation, Two Pointer Technique, Interview Preparation

**Difficulty Level:** Medium (Optimal Solution Required)

**Prerequisites:** 
- Basic Linked List Operations
- Two Pointer Technique
- Space Complexity Optimization

**Learning Outcomes:**
- Master two pointer technique in linked lists
- Understand space-time trade-offs
- Learn Y-shaped data structure problems
- Practice optimal algorithm design
