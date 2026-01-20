# Implement Undo & Redo System
---

> Video Solution: [https://youtu.be/mwlq24Mphvk](https://youtu.be/mwlq24Mphvk)

[Problem](https://www.geeksforgeeks.org/problems/implement-undo-redo/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/mwlq24Mphvk/0.jpg)](https://youtu.be/mwlq24Mphvk)

---

**Difficulty:** Medium  
**Accuracy:** 52.14%  
**Submissions:** 15K+  
**Points:** 4  
**Average Time:** 25m

Design a text document system that supports basic editing operations: **Append**, **Undo**, **Redo**, and **Read**. The initial document is empty.

## Operations:
1. **Append(X):** Add character <code>X</code> to the end of the document.
2. **Undo():** Revert the most recent <code>Append</code> operation.
3. **Redo():** Reapply the most recent <code>Undo</code> operation.
4. **Read():** Return the current content of the document.

## Examples:

**Example 1:**
<code>
Input: 
Append('a'), Append('b'), Undo(), Read()
Output: "a"
Explanation: After appending 'a' and 'b', document is "ab". Undo removes 'b', leaving "a". [00:01:46]
</code>

**Example 2:**
<code>
Input:
Append('a'), Append('b'), Undo(), Redo(), Read()
Output: "ab"
Explanation: Undo removes 'b', but Redo restores it. [00:02:20]
</code>

## Expected Complexities:
- **Time Complexity:** O(1) for each operation (except Read, which is O(N)).
- **Space Complexity:** O(N) to store the history of characters.

---

## Design: The Two-Stack Approach

### 1. Intuition [00:04:33]
Undo and Redo follow the **LIFO (Last-In, First-Out)** principle. When you undo, the character added *last* is the *first* one removed. When you redo, the character undone *last* is the *first* one restored. This makes **Stacks** the ideal data structure.

### 2. State Management [00:06:48]
- **Undo Stack:** Stores the sequence of characters currently in the document.
- **Redo Stack:** Stores the characters that were recently removed via Undo.
- **String Builder:** Used to construct the current document content efficiently. [00:06:10]

### 3. Logic Breakdown:

- **Append(ch):** [00:08:02]
  - Push <code>ch</code> to the **Undo Stack**.
  - Append <code>ch</code> to the **String Builder**.
  - **Crucial:** Clear the **Redo Stack** because a new operation invalidates the previous redo history.

- **Undo():** [00:08:44]
  - If Undo Stack is not empty:
    - Pop <code>ch</code> from the **Undo Stack**.
    - Push <code>ch</code> to the **Redo Stack**.
    - Remove the last character from the **String Builder**. [00:11:27]

- **Redo():** [00:10:07]
  - If Redo Stack is not empty:
    - Pop <code>ch</code> from the **Redo Stack**.
    - Push <code>ch</code> to the **Undo Stack**.
    - Append <code>ch</code> to the **String Builder**.

---

## Implementation (Java Style):
<code>
class Solution {
    Stack&lt;Character&gt; undoStack = new Stack&lt;&gt;();
    Stack&lt;Character&gt; redoStack = new Stack&lt;&gt;();
    StringBuilder sb = new StringBuilder();

    void append(char ch) {
        undoStack.push(ch);
        sb.append(ch);
        redoStack.clear(); // New append clears redo history
    }

    void undo() {
        if (!undoStack.isEmpty()) {
            char ch = undoStack.pop();
            redoStack.push(ch);
            sb.setLength(sb.length() - 1);
        }
    }

    void redo() {
        if (!redoStack.isEmpty()) {
            char ch = redoStack.pop();
            undoStack.push(ch);
            sb.append(ch);
        }
    }

    String read() {
        return sb.toString();
    }
}
</code>

---

## Key Takeaways:
- **Undo/Redo is a classic stack problem.** [00:05:02]
- **Redo Invalidation:** A common mistake is forgetting to clear the Redo stack when a new character is appended. [00:12:19]
- **O(1) Efficiency:** Using <code>setLength()</code> on StringBuilder allows O(1) removal of the last character. [00:11:27]

## Related Design Problems:
- [Design Browser History (LeetCode 1472)](https://leetcode.com/problems/design-browser-history/)
- [Min Stack Implementation](https://www.geeksforgeeks.org/design-a-stack-that-supports-getmin-in-o1-time-and-o1-extra-space/)
- [Implement Stack using Queues](https://www.geeksforgeeks.org/problems/stack-using-two-queues/1)

## Keywords:
implement undo redo, design patterns, stack data structure, lifo principle, stringbuilder optimization, coding interview design, geeksforgeeks potd, system design basics.

---

**SEO Tags:** #SystemDesign #Stack #UndoRedo #DataStructures #DSA #GeeksforGeeks #Java #CodingInterview #ProgrammingPatterns

**Learning Outcomes:**
- Designing state-dependent systems with Stacks.
- Understanding the interaction between primary state (Undo) and secondary history (Redo).
- Managing edge cases like empty operations and history invalidation.
