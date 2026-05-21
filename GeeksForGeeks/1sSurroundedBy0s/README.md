# Count 1s Surrounded by 0s in a Binary Matrix | BFS Border Flood Fill | GeeksForGeeks

---
> Video description: https://youtu.be/vSDzRBp9QeQ

[Problem](https://www.geeksforgeeks.org/problems/1s-surrounded-by-0s/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/vSDzRBp9QeQ/0.jpg)](https://youtu.be/vSDzRBp9QeQ)

---

**Difficulty:** Medium  
**Topics:** Matrix | BFS | Flood Fill | Graph Traversal  
**Companies:** Amazon | Microsoft | Google | Samsung  
**Time Complexity:** O(n × m) | **Space Complexity:** O(n × m)

---

## What Does "1s Surrounded by 0s" Mean?

A `1` in the matrix is **surrounded by 0s** if it is **not connected** (4-directionally) to any `1` on the border of the matrix. Border-connected `1`s can "escape" — only fully interior islands count.

```
Matrix:
  0 0 0 0
  0 1 1 0
  0 1 0 0
  0 0 0 0

The 1s at (1,1), (1,2), (2,1) form an interior island → count = 3
```

---

## Problem Statement — Count Interior 1s Not Touching the Border

Given a binary matrix `mat[][]` of size `n × m`, count the number of `1`s that are **completely surrounded by 0s** — i.e., not connected to any `1` on the edge of the matrix.

## Examples

**Example 1:**
```
Input:
  mat[][] = [[0, 0, 0, 0],
             [0, 1, 1, 0],
             [0, 1, 0, 0],
             [0, 0, 0, 0]]

Output: 3

Explanation: The three 1s form an island entirely in the interior.
             None of them touch the border → all 3 are surrounded ✓
```

**Example 2:**
```
Input:
  mat[][] = [[0, 0, 0, 0],
             [0, 1, 1, 0],
             [0, 1, 0, 1],
             [0, 0, 0, 0]]

Output: 3

Explanation: The 1 at (2,3) is on the border → not counted.
             The three interior 1s at (1,1),(1,2),(2,1) → count = 3
```

**Example 3:**
```
Input:
  mat[][] = [[1, 0, 0],
             [0, 1, 0],
             [0, 0, 1]]

Output: 1

Explanation: Corner 1s touch the border → not counted.
             Only the center 1 at (1,1) is surrounded → count = 1
```

## Constraints
- 1 ≤ n, m ≤ 500
- mat[i][j] ∈ {0, 1}

---

## Approach — Two-Phase BFS (Border Elimination + Interior Count)

**Key Insight:** Instead of checking for each `1` whether it touches the border (expensive), flip the problem: first **eliminate** all border-connected `1`s using BFS, then count what remains in the interior.

### Phase 1 — Flood Fill from All Border 1s

Seed the BFS queue with every `1` on the four edges of the matrix. Run BFS to mark all `1`s reachable from the border as visited (set to `0`). These are the ones that are NOT surrounded — they connect to the border.

### Phase 2 — Count Remaining Interior 1s

Iterate only through the inner cells `[1..n-2][1..m-2]`. Any `1` still present here is fully surrounded. BFS from it to count the entire connected island and add to the result.

### Walkthrough

```
Matrix:
  0  0  0  0
  0  1  1  0
  0  1  0  0
  0  0  0  0

Phase 1: scan all border cells — no 1s on border found.
         BFS eliminates nothing.

Phase 2: scan interior [1..2][1..2]:
  (1,1) = 1 → BFS → visits (1,1),(1,2),(2,1) → cnt = 3
  (1,2) = 0  (already visited)
  (2,1) = 0  (already visited)

Output: 3
```

```
Matrix:
  0  0  0  0
  0  1  1  0
  0  1  0  1   ← (2,3) is on the border
  0  0  0  0

Phase 1: border scan finds (2,3)=1 → BFS marks it → set to 0.

Phase 2: scan interior:
  (1,1),(1,2),(2,1) remain as 1s → BFS → cnt = 3

Output: 3
```

### Why BFS and Not DFS?

Both work here. BFS is preferred because it processes all border-connected cells level by level, and the iterative queue avoids stack overflow risks on large matrices (500×500 = 250,000 cells).

### Complexity

| | Value |
|---|---|
| **Time** | O(n × m) — each cell visited at most twice (Phase 1 + Phase 2) |
| **Space** | O(n × m) — BFS queue in the worst case |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Counting border-touching 1s**
Any `1` reachable from a border `1` must be excluded. A simple interior check `i > 0 && i < n-1 && j > 0 && j < m-1` is not enough — a long chain of `1`s can start from an interior cell but connect back to the border.

**Mistake 2: Marking visited only on dequeue**
If you mark visited only when dequeuing, the same cell can be enqueued multiple times, causing incorrect counts or TLE. Mark visited (set to `0`) immediately when enqueuing.

**Mistake 3: Checking 8 directions instead of 4**
This problem uses **4-directional** connectivity (up, down, left, right), not 8. Using diagonals gives wrong results.

---

## Related Problems
- Number of Islands — LeetCode 200
- Surrounded Regions — LeetCode 130
- Flood Fill — LeetCode 733

---

## Tags

BFS | Flood Fill | Binary Matrix | Graph Traversal | Border Elimination | Interior Islands | Java | GeeksForGeeks | 1s Surrounded by 0s | Count Enclosed 1s

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/1sSurroundedBy0s

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ What "surrounded by 0s" actually means (border-connected vs interior)
✅ Two-phase BFS: border flood fill → interior count
✅ Step-by-step dry run on multiple examples
✅ Time O(n×m) · Space O(n×m) solution
✅ Common mistakes — why checking interior alone is wrong

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
1s Surrounded by 0s · BFS · Flood Fill · Binary Matrix · Border Elimination · Interior Islands · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/1s-surrounded-by-0s/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#1sSurroundedBy0s #GeeksForGeeks #BFSProblems #FloodFill #MatrixProblems
#DataStructures #JavaProgramming #DailyChallenge #GraphTraversal #DSAWithOBrutus #KodeLoad
```
