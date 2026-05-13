# Mother Vertex in a Graph | Kosaraju's Idea | DFS | GeeksForGeeks

---
> Video description: https://youtu.be/0uNQk8FCnJM

[Problem](https://www.geeksforgeeks.org/problems/mother-vertex/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/0uNQk8FCnJM/0.jpg)](https://youtu.be/0uNQk8FCnJM)

---

**Difficulty:** Medium  
**Topics:** Graph | DFS | Kosaraju's Algorithm | Strongly Connected Components  
**Companies:** Amazon | Microsoft | Google | Samsung

---

## Problem Statement

Given a **directed graph** with `V` vertices and a list of edges, find the **mother vertex** — a vertex from which all other vertices are reachable.

- If multiple mother vertices exist, return the **smallest** one.
- If no mother vertex exists, return `-1`.

> A **mother vertex** is a vertex `v` such that every other vertex in the graph can be reached by following directed edges starting from `v`.

## Examples

**Example 1:**
```
Input:  V = 4, edges = [[0,1],[0,2],[1,3],[2,3]]

Graph:
  0 → 1 → 3
  0 → 2 → 3

Output: 0

Explanation: From vertex 0 you can reach 1, 2, and 3. It is the mother vertex.
```

**Example 2:**
```
Input:  V = 3, edges = [[0,1],[1,2],[2,0]]

Graph:
  0 → 1 → 2 → 0  (cycle)

Output: 0

Explanation: All three vertices are mother vertices (cycle). Return smallest → 0.
```

**Example 3:**
```
Input:  V = 3, edges = [[0,1],[2,1]]

Graph:
  0 → 1 ← 2

Output: -1

Explanation: Neither 0 nor 2 can reach each other → no mother vertex.
```

## Constraints
- 1 ≤ V ≤ 500
- 0 ≤ edges.length ≤ V × (V - 1)

---

## Approach — Kosaraju's First Pass Idea

### Brute Force: O(V × (V + E))

Run a DFS from every vertex and check if it reaches all others. Too slow for large graphs.

### Optimal: Two-Pass DFS — O(V + E)

**Key Insight (from Kosaraju's algorithm):**  
In a DFS over the whole graph, the **last vertex to finish** (start a new DFS component) is always a candidate for the mother vertex. This is because a mother vertex, if it exists, must belong to a source SCC — and Kosaraju's first pass surfaces the last-finishing node of such a component.

### Algorithm

**Pass 1 — Find the candidate:**
- Iterate over all vertices `0` to `V-1`.
- Skip already-visited nodes.
- Run DFS from each unvisited node, marking visited nodes.
- Track the last node that **started** a new DFS component — this is the `candidate`.

**Pass 2 — Verify the candidate:**
- Reset visited array.
- Run DFS from `candidate`.
- If all `V` nodes are visited → `candidate` is a mother vertex.
- Otherwise → return `-1`.

### Walkthrough

```
V = 4, edges = [0→1, 0→2, 1→3, 2→3]

Pass 1 (find candidate):
  i=0: not visited → DFS(0) visits {0,1,2,3} → candidate = 0
  i=1: already visited, skip
  i=2: already visited, skip
  i=3: already visited, skip

candidate = 0

Pass 2 (verify):
  Reset visited = [F, F, F, F]
  DFS(0) → visits {0, 1, 3, 2}  →  all 4 visited ✓

Output: 0
```

```
V = 3, edges = [0→1, 2→1]

Pass 1 (find candidate):
  i=0: DFS(0) visits {0,1}  →  candidate = 0
  i=1: already visited, skip
  i=2: not visited → DFS(2) visits {2}  →  candidate = 2

candidate = 2

Pass 2 (verify):
  DFS(2) → visits {2,1}  →  node 0 NOT visited ✗

Output: -1
```

### Complexity

| | Value |
|---|---|
| **Time** | O(V + E) — two DFS passes over vertices and edges |
| **Space** | O(V + E) — adjacency map + visited array + recursion stack |

---

## Related Problems
- Strongly Connected Components (Kosaraju's Algorithm)
- Number of Provinces
- Find the Town Judge

## Tags
`graph` `DFS` `directed-graph` `kosaraju` `strongly-connected-components` `mother-vertex` `reachability` `java` `geeksforgeeks`
