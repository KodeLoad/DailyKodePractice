# Check if Linked List is Palindrome
---

> Video description: https://youtu.be/co78-s35teU

[Problem](https://www.geeksforgeeks.org/problems/check-if-linked-list-is-pallindrome/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/co78-s35teU/0.jpg)](https://youtu.be/co78-s35teU)

---

**Difficulty:** Medium  
**Accuracy:** 41.48%  
**Submissions:** 489K+  
**Points:** 4  
**Average Time:** 25m

Given a singly linked list of integers. The task is to check if the given linked list is **palindrome** or not.

A palindrome linked list reads the same forward and backward. For example, 1→2→3→2→1 is a palindrome.

## Examples:

**Example 1:**
```
Input: LinkedList: 1->2->1
Output: true

Explanation: 
1 -> 2 -> 1
Reading forward: 1, 2, 1
Reading backward: 1, 2, 1
Same in both directions, hence palindrome.
```

**Example 2:**
```
Input: LinkedList: 1->2
Output: false

Explanation:
1 -> 2
Forward: 1, 2
Backward: 2, 1
Different, hence not palindrome.
```

**Example 3:**
```
Input: LinkedList: 1->2->2->1
Output: true

Explanation:
1 -> 2 -> 2 -> 1
Forward: 1, 2, 2, 1
Backward: 1, 2, 2, 1
Same, hence palindrome.
```

## Constraints:
- 1 ≤ number of nodes ≤ 10⁵
- 1 ≤ node->data ≤ 10³

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Microsoft | Google | Facebook | Adobe | Samsung

## Topic Tags:
Linked List | Two Pointers | Stack | Recursion

## Approach:

### Problem Understanding:

**Palindrome Definition:**
- Reads same forward and backward
- First element = Last element
- Second element = Second-last element
- And so on...

**Challenge with Linked List:**
- Can only traverse forward
- No direct access to elements from end
- Need strategy to compare elements

### Approach 1: Using Stack (Simple)

**Algorithm:**
```
1. Push all elements to stack
2. Traverse list again
3. Pop from stack and compare
4. If all match → palindrome
```

**Complexity:**
- Time: O(n)
- Space: O(n)

### Approach 2: Reverse Second Half (Optimal)

**Key Steps:**
1. Find middle of linked list (slow/fast pointer)
2. Reverse second half
3. Compare first half with reversed second half
4. Restore list (optional)

**Algorithm:**
```
function isPalindrome(head):
    if head == null or head.next == null:
        return true
    
    // Step 1: Find middle
    slow = head
    fast = head
    while fast != null and fast.next != null:
        slow = slow.next
        fast = fast.next.next
    
    // Step 2: Reverse second half
    secondHalf = reverse(slow)
    
    // Step 3: Compare
    firstHalf = head
    while secondHalf != null:
        if firstHalf.data != secondHalf.data:
            return false
        firstHalf = firstHalf.next
        secondHalf = secondHalf.next
    
    return true

function reverse(head):
    prev = null
    current = head
    while current != null:
        next = current.next
        current.next = prev
        prev = current
        current = next
    return prev
```

### Step-by-Step Walkthrough:

**Example: 1→2→3→2→1**

```
Step 1: Find Middle
slow = 1, fast = 1
Iteration 1: slow = 2, fast = 3
Iteration 2: slow = 3, fast = null
Middle found at 3

List state:
1 → 2 → 3 → 2 → 1
        ↑
      middle

Step 2: Reverse from middle
Before: 3 → 2 → 1
After:  1 → 2 → 3

Step 3: Compare
First half:  1 → 2 → 3
Second half: 1 → 2 → 3
Match! ✓

Output: true
```

### Visual Understanding:

**Even length palindrome: 1→2→2→1**
```
Original: 1 → 2 → 2 → 1

Find middle:
slow stops at second 2

Reverse second half:
1 → 2    1 → 2
        ↙
Compare these parts
1 = 1 ✓
2 = 2 ✓
Palindrome!
```

**Non-palindrome: 1→2→3**
```
Original: 1 → 2 → 3

Find middle:
slow stops at 2

Reverse second half:
1 → 2    3 → 2

Compare:
1 ≠ 3 ✗
Not palindrome!
```

### Edge Cases:

1. **Empty list:** true
2. **Single node:** true
3. **Two nodes same:** 1→1 → true
4. **Two nodes different:** 1→2 → false
5. **Odd length:** 1→2→3→2→1 → true
6. **Even length:** 1→2→2→1 → true
7. **All same values:** 5→5→5→5 → true

### Common Mistakes:

1. **Not handling odd/even length differently**
2. **Off-by-one in middle finding**
3. **Not restoring original list**
4. **Memory leaks in reversal**

### Implementation:

```python
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

def isPalindrome(head):
    if not head or not head.next:
        return True
    
    # Find middle
    slow = fast = head
    while fast and fast.next:
        slow = slow.next
        fast = fast.next.next
    
    # Reverse second half
    second = reverse(slow)
    
    # Compare
    first = head
    while second:
        if first.data != second.data:
            return False
        first = first.next
        second = second.next
    
    return True

def reverse(head):
    prev = None
    while head:
        nxt = head.next
        head.next = prev
        prev = head
        head = nxt
    return prev
```

### Complexity Analysis:

**Time: O(n)**
- Find middle: O(n/2)
- Reverse: O(n/2)
- Compare: O(n/2)
- Total: O(n)

**Space: O(1)**
- Only pointers used
- No extra data structures

## Related Problems:
- Reverse Linked List
- Middle of Linked List
- Palindrome Number
- Valid Palindrome

## Keywords:
palindrome linked list, check palindrome, linked list palindrome, two pointer technique, reverse linked list, O(1) space solution, linked list algorithms, DSA interview questions

---

**Tags:** #LinkedList #Palindrome #TwoPointers #LinkedListReversal #DSA #CodingInterview #GeeksforGeeks
