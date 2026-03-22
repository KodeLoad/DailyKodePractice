# Rotten Oranges - BFS Approach
---

> Video Solution: [https://youtu.be/ktGa6pvsSX8](https://youtu.be/ktGa6pvsSX8)

[Problem](https://www.geeksforgeeks.org/problems/rotten-oranges2536/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/ktGa6pvsSX8/0.jpg)](https://youtu.be/ktGa6pvsSX8)

---

**Difficulty:** Medium  
**Accuracy:** 46.51%  
**Submissions:** 200K+  
**Points:** 4  
**Average Time:** 35m

Given a grid of dimension `n x m` where each cell in the grid can have values `0`, `1`, or `2`:
- `0`: Empty cell
- `1`: Fresh orange
- `2`: Rotten orange

A rotten orange at index `(i, j)` can rot all fresh oranges at its 4-way adjacent positions (up, down, left, right). Your task is to find the **minimum time** required to rot all oranges. If it is impossible to rot all oranges, return `-1`.

## Examples:

**Example 1:**
<code>
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Explanation: All oranges in the grid become rotten in 4 units of time. [00:01:26]
</code>

**Example 2:**
<code>
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange at (2,0) is never rotten because it's isolated by empty cells. [00:01:33]
</code>

## Constraints:
- 1 ≤ n, m ≤ 500
- grid[i][j] is either 0, 1, or 2.

## Expected Complexities:
- **Time Complexity:** O(N * M)
- **Space Complexity:** O(N * M)

## Topic Tags:
Matrix | Breadth-First Search (BFS) | Graphs

---

## Approach: Multisource Breadth-First Search (BFS)

### 1. Why BFS over DFS? [00:03:58]
Rotten oranges act as sources of infection that spread simultaneously layer by layer. **BFS** naturally models this level-order traversal (time step by time step), whereas DFS would require significant recomputation to find the *minimum* time for each cell. [00:04:12]


### 2. Implementation Strategy [00:05:28]
1.  **Queue Initialization:** Scan the entire grid and add all initial rotten oranges (value `2`) into a `Queue`. These are our starting points. [00:07:42]
2.  **Distance Tracking:** Use a `dist[][]` matrix or a `time` variable to track the steps taken from the initial sources. [00:06:41]
3.  **Propagation:** While the queue is not empty:
    - Pop a rotten orange and check its 4 neighbors. [00:10:00]
    - If a neighbor is a fresh orange (`1`), mark it as rotten (`2`) and add its coordinates to the queue with `time + 1`. [00:13:23]
4.  **Final Validation:** After the BFS, scan the grid once more. If any fresh orange (`1`) remains, return `-1`. Otherwise, return the maximum time recorded. [00:14:09]

---

## Implementation (Conceptual): [00:08:23]
<code>
public int orangesRotting(int[][] grid) {
    int rows = grid.length, cols = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    int freshCount = 0;

    // Phase 1: Initialize Queue
    for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
            if (grid[r][c] == 2) queue.add(new int[]{r, c, 0});
            else if (grid[r][c] == 1) freshCount++;
        }
    }

    int time = 0;
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    
    // Phase 2: Spread Infection
    while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        time = curr[2];
        for (int[] d : dirs) {
            int nr = curr[0] + d[0], nc = curr[1] + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                grid[nr][nc] = 2;
                freshCount--;
                queue.add(new int[]{nr, nc, time + 1});
            }
        }
    }
    return freshCount == 0 ? time : -1;
}
</code>

---

## Key Takeaways:
- **Multisource BFS:** Start BFS from all sources simultaneously to find the shortest time path for all affected nodes. [00:05:36]
- **Time Complexity:** $O(N \times M)$ because every cell is visited and processed at most once. [00:15:57]
- **Space Complexity:** $O(N \times M)$ for the queue in the worst case (where most oranges start as rotten). [00:15:57]

## Related Problems:
- [Walls and Gates](https://leetcode.com/problems/walls-and-gates/)
- [01 Matrix](https://leetcode.com/problems/01-matrix/)
- [Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix/)

## Keywords:
rotten oranges solution bfs, multisource bfs grid, geeksforgeeks rotten oranges java, graph traversal grid, coding interview rotten oranges, matrix infection problem, dsa shortest path grid.

---

**SEO Tags:** #BFS #Graphs #Matrix #DSA #GeeksforGeeks #CodingInterview #Java #Python #Algorithm #OBrutus #TechInterview

**Learning Outcomes:**
- Mastering Multisource BFS for shortest path/time problems in a grid.
- Understanding the trade-offs between BFS and DFS for layer-based propagation.
- Efficiently handling boundaries and state changes in matrix problems.
