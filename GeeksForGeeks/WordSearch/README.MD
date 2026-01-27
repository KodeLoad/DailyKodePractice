# Word Search
---

> Video Solution: [https://youtu.be/WSXlxtKcLO0](https://youtu.be/WSXlxtKcLO0)

[Problem](https://www.geeksforgeeks.org/problems/word-search/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/WSXlxtKcLO0/0.jpg)](https://youtu.be/WSXlxtKcLO0)

---

**Difficulty:** Medium  
**Accuracy:** 32.69%  
**Submissions:** 100K+  
**Points:** 4  
**Average Time:** 45m

Given a 2D board of characters and a word, find if the word exists in the grid. The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

## Examples:

**Example 1:**
<code>
Input: mat[][] = [["a", "g", "b", "c"], ["q", "e", "e", "l"], ["g", "b", "k", "s"]], word = "geeks"
Output: true

Explanation: 
G (0,1) -> E (1,1) -> E (1,2) -> K (2,2) -> S (2,3)
</code>

**Example 2:**
<code>
Input: mat[][] = [["a", "b", "c", "e"], ["s", "f", "c", "s"], ["a", "d", "e", "e"]], word = "sabfs"
Output: true
</code>

**Example 3:**
<code>
Input: mat[][] = [["a", "b", "c", "e"], ["s", "f", "c", "s"], ["a", "d", "e", "e"]], word = "abcb"
Output: false
</code>

## Constraints:
- 1 ≤ n, m ≤ 100
- 1 ≤ word.length ≤ 10³
- Mat and word contains only lowercase and uppercase English letters.

## Expected Complexities:
- **Time Complexity:** O(N * M * 4^L) where L is the length of the word.
- **Space Complexity:** O(L) for the recursion stack.

## Topic Tags:
DFS | Backtracking | Matrix | Recursion

---

## Approach: Backtracking (DFS)

### 1. Why DFS over BFS? [00:01:08]
Interviewers love this problem because it tests your **decision-making**. 
- **BFS:** Explores all possibilities level by level. It is memory-intensive for deep paths and better suited for finding the shortest path or counts.
- **DFS:** Explores one path completely. Since we only need to return `true` or `false` (existential check), DFS allows for **early exit** (pruning) as soon as the word is found, making it much more efficient in practice. [00:02:23]

### 2. The Backtracking Logic [00:03:05]
To ensure we don't reuse a cell in the same word construction, we need a way to "mark" the cell as visited.
- **The Trick:** Temporarily replace the character in the matrix with a special character (like `#` or `*`) before recursing. [00:07:20]
- **The Restore:** After the recursive calls return, restore the original character. This is the "Backtracking" step. [00:07:28]

### 3. Algorithm Steps: [00:08:21]
1. Iterate through every cell $(i, j)$ in the matrix.
2. If `mat[i][j]` matches the first character of the word, start the DFS. [00:09:02]
3. In DFS:
    - Check boundaries and character matches. [00:03:52]
    - If all characters are matched, return `true`. [00:04:32]
    - Mark current cell as visited.
    - Recursively check all 4 adjacent directions (Up, Down, Left, Right). [00:05:53]
    - Backtrack: Restore the cell value. [00:07:16]

---

## Implementation (Conceptual): [00:03:30]
<code>
boolean dfs(char[][] mat, String word, int i, int j, int index) {
    if (index == word.length()) return true;
    if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length || mat[i][j] != word.charAt(index)) {
        return false;
    }

    char temp = mat[i][j];
    mat[i][j] = '#'; // Mark as visited

    boolean found = dfs(mat, word, i + 1, j, index + 1) ||
                    dfs(mat, word, i - 1, j, index + 1) ||
                    dfs(mat, word, i, j + 1, index + 1) ||
                    dfs(mat, word, i, j - 1, index + 1);

    mat[i][j] = temp; // Backtrack
    return found;
}
</code>

---

## Key Takeaways:
- **Pruning:** If any branch returns `true`, use the `||` operator to avoid unnecessary further exploration. [00:06:28]
- **In-place Marking:** Replacing characters directly in the matrix saves $O(N \times M)$ extra space that a `visited[][]` boolean array would require. [00:07:44]
- **Starting Point:** We must attempt to start the word from every single cell in the grid. [00:08:21]

## Related Problems:
- [Word Search II](https://leetcode.com/problems/word-search-ii/) (Trie + DFS)
- [Number of Islands](https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1)
- [Rat in a Maze](https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1)

## Keywords:
word search gfg, word search interview question, dfs backtracking matrix, recursion matrix java, search word in grid, dsa backtracking problems, geeksforgeeks potd.

---

**SEO Tags:** #DFS #Backtracking #Recursion #Matrix #DSA #GeeksforGeeks #CodingInterview #Java #Algorithms #WordSearch

**Learning Outcomes:**
- Mastering the Backtracking pattern in 2D grids.
- Understanding the strategic choice between BFS and DFS.
- Implementing in-place state management to optimize space.
