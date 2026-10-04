# Perimeter of Shapes in Binary Matrix | DFS Flood Fill | GeeksForGeeks

---
> Video description: https://youtu.be/hF0MpseZ570

[Problem](https://www.geeksforgeeks.org/problems/find-perimeter-of-shapes/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/hF0MpseZ570/0.jpg)](https://youtu.be/hF0MpseZ570)

---

**Difficulty:** Easy  
**Topics:** Matrix | DFS | Graph | Flood Fill | Connected Components  
**Companies:** Amazon | Microsoft | Google | Flipkart  
**Time Complexity:** O(n × m) | **Space Complexity:** O(n × m) (recursion stack, worst case all 1s)

---

## What Does "Perimeter of Shapes" Mean?

Every cell containing `1` is a unit square. Two `1`-cells are **adjacent** only if they share a common side (no diagonals). Wherever two `1`-cells touch, the shared edge is **internal** and does not count toward the perimeter — only the exposed outer edges do.

```
mat = [[1, 0],
       [1, 1]]

(0,0)──(0,1)        A single 1-cell has perimeter 4.
  │                 Two adjacent 1-cells share one edge,
(1,0)──(1,1)        so together they have 4 + 4 − 2 = 6.

This shape has 3 cells, 2 shared edges → 3×4 − 2×2 = 8
```

---

## Problem Statement — Find Perimeter of Shapes Formed by 1s in a Binary Matrix

Given a binary matrix `mat[][]` of size `n × m`, where each cell contains either `0` or `1`, find the **total perimeter** of all the figures (shapes) formed by the cells containing `1`. There can be multiple disconnected shapes in the same matrix — sum up the perimeter of every shape.

## Examples

**Example 1:**
```
Input:  mat[][] = [[0, 1, 0, 0, 0],
                    [1, 1, 1, 0, 0],
                    [1, 0, 0, 0, 0]]
Output: 12

The five 1-cells form a single connected shape with perimeter 12.
```

**Example 2:**
```
Input:  mat[][] = [[1, 0],
                    [1, 1]]
Output: 8

Two shared edges (between the two vertical 1s, and between the
two horizontal 1s in the bottom row) reduce 3×4=12 down to 8.
```

**Example 3 (multiple shapes):**
```
Input:  mat[][] = [[1, 0, 1],
                    [0, 0, 1]]
Output: 4 + 6 = 10

Top-left 1 is an isolated shape → perimeter 4.
The two 1s on the right share one edge → perimeter 6.
Total = 10.
```

## Constraints
- 1 ≤ n, m ≤ 100
- mat[i][j] ∈ {0, 1}

---

## Approach — DFS Flood Fill with Edge Contribution

**Key Insight:** For any `1`-cell, it contributes exactly `4 − (number of its 4-directional neighbors that are also 1)` to the total perimeter. Summed over every `1`-cell in a shape, this gives that shape's exact perimeter — shared internal edges cancel out, only outer edges remain.

Instead of computing this with a plain double loop, the DFS version **floods** each shape once, marks visited cells so they're never re-processed, and counts the exposed edges while it explores:

- **Out of bounds** → this side is exposed → `+1`.
- **Neighbor is `0`** → this side is exposed → `+1`.
- **Neighbor is already visited (`-1`)** → internal edge, already accounted for → `+0`.
- **Neighbor is an unvisited `1`** → mark it visited and recurse into it, adding whatever it contributes.

### The Direction Trick

```java
int[] d = {0, 1, 0, -1};
for (int x = 0; x < 4; x++) {
    sum += dfs(a, i + d[x], j + d[(x + 1) % 4]);
}
```
Pairing `d[x]` with `d[(x+1)%4]` cycles through `(0,1) → (1,0) → (0,-1) → (-1,0)`: right, down, left, up — the four side-neighbors, generated from one array instead of four hardcoded pairs.

### Algorithm

1. Scan every cell `(i, j)`. Skip if it isn't `1` (covers both `0`s and already-visited `-1`s).
2. On the first unvisited `1` of a shape, call `dfs(a, i, j)` — this explores and tallies the **entire** shape's perimeter in one recursive call, marking every cell of it `-1` along the way.
3. Add the returned value to the running `sum`.
4. Because visited cells become `-1` (≠ 1), the outer loop automatically skips the rest of that shape — each shape is counted exactly once.
5. Return `sum` after the full scan.

### Walkthrough — Example 2

```
mat = [[1, 0],
       [1, 1]]

dfs(0,0): mark (0,0) = -1
  right (0,1) = 0        → +1
  down  (1,0) = 1 (new)  → recurse:
      dfs(1,0): mark (1,0) = -1
        right (1,1) = 1 (new) → recurse:
            dfs(1,1): mark (1,1) = -1
              right (1,2) OOB → +1
              down  (2,1) OOB → +1
              left  (1,0) = -1 (visited) → +0
              up    (0,1) = 0  → +1
              returns 3
        down  (2,0) OOB → +1
        left  (1,-1) OOB → +1
        up    (0,0) = -1 (visited) → +0
        returns 3 + 1 + 1 + 0 = 5
  left (0,-1) OOB → +1
  up   (-1,0) OOB → +1
  returns 1 + 5 + 1 + 1 = 8

Output: 8 ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n × m) — each cell is visited and marked exactly once |
| **Space** | O(n × m) — recursion stack depth in the worst case (entire grid is one shape) |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Not distinguishing "visited 1" from "0" with a separate sentinel**
If visited cells are reset to `0` instead of a marker like `-1`, the algorithm can't tell an internal edge (already counted) from a true boundary edge (`+1`), causing over-counting.

**Mistake 2: Counting diagonal neighbors as adjacent**
Only cells sharing a **side** reduce the perimeter. Two `1`s touching only at a corner are still two separate shapes of perimeter 4 each.

**Mistake 3: Only processing one shape**
The matrix can contain several disconnected groups of `1`s. The outer double loop must call DFS from **every** unvisited `1`, not just the first one found.

**Mistake 4: Double-counting the shared edge between two shape cells**
Returning `+1` when the neighbor is a visited `1` (instead of `+0`) recounts internal edges that were already tallied from the other direction, inflating the answer.

**Mistake 5: Forgetting matrix bounds checks before indexing neighbors**
Checking `a[i][j]` before confirming `i, j` are in range throws an `ArrayIndexOutOfBoundsException` — bounds must be checked first, and an out-of-bounds neighbor itself counts as `+1` (an exposed edge).

---

## Related Problems
- Island Perimeter — LeetCode 463
- Number of Islands — LeetCode 200
- Max Area of Island — LeetCode 695
- Flood Fill — LeetCode 733

---

## Tags

DFS | Flood Fill | Matrix | Graph | Connected Components | Grid Traversal | Recursion | Java | GeeksForGeeks | Perimeter of Shapes | Island Perimeter

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/PerimeterOfShapesInBinaryMatrix

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ The "4 minus adjacent 1s" insight behind every perimeter cell
✅ DFS flood fill that counts exposed edges while marking visited cells
✅ The 4-direction trick generated from a single array
✅ Handling multiple disconnected shapes in the same matrix
✅ Full dry run + common traps — diagonals, double counting, missing shapes

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Perimeter of Shapes in Binary Matrix · DFS · Flood Fill · Matrix · Graph · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/find-perimeter-of-shapes/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#GeeksforGeeks #matrix #DFS #FloodFill #CodingInterview #DSA #Algorithms
#GFGPOTD #Programming #ProblemSolving #OBrutus #TechInterview #DataStructures
#JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad #graph #ConnectedComponents
```
