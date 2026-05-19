# Minimum Multiplications to Reach End | BFS Shortest Path | GeeksForGeeks

---
> Video description: https://youtu.be/MyKbZRBYGmE

[Problem](https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/MyKbZRBYGmE/0.jpg)](https://youtu.be/MyKbZRBYGmE)

---

**Difficulty:** Medium  
**Topics:** Graph | BFS | Shortest Path | Modular Arithmetic  
**Companies:** Amazon | Google | Microsoft  
**Time Complexity:** O(1000 × n) | **Space Complexity:** O(1000)

---

## What Is This Problem?

Given a `start` value, an `end` value, and an array of multipliers, find the **minimum number of multiplications** needed to transform `start` into `end`. Each multiplication result is taken **modulo 1000**, which bounds the search space to values `0–999`.

If it is impossible to reach `end`, return `-1`.

---

## Problem Statement — Minimum Steps to Reach End via Multiplication mod 1000

Given an integer array `arr`, an integer `start`, and an integer `end`, at each step you can multiply the current value by any element in `arr` and take the result modulo 1000. Return the **minimum number of steps** to reach `end` from `start`.

## Examples

**Example 1:**
```
Input:  arr[] = [2, 5, 7], start = 3, end = 30
Output: 2

Step 1: 3 × 10 ... try multipliers:
  3 × 2 = 6
  3 × 5 = 15
  3 × 7 = 21

Step 2 from 15:
  15 × 2 = 30 = end ✓

Minimum steps = 2
```

**Example 2:**
```
Input:  arr[] = [3, 4, 65], start = 7, end = 66
Output: 1

  7 × 3  = 21
  7 × 4  = 28
  7 × 65 = 455 % 1000 = 455  ✗
  ...

Actually: step 1: try each multiplier, find the shortest path via BFS.
```

**Example 3:**
```
Input:  arr[] = [2, 5], start = 4, end = 7
Output: -1

No combination of multiplications mod 1000 starting from 4 can reach 7.
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁴
- 0 ≤ arr[i] ≤ 10⁴
- 1 ≤ start, end < 1000

---

## Why Not DFS?

DFS explores one branch deeply before backtracking — it may find A path to `end`, but not necessarily the **shortest** one. Since each step has equal weight (cost = 1), BFS is the right tool: it explores all states reachable in `k` steps before trying `k+1`, guaranteeing the first time we hit `end` is the minimum.

The `% 1000` bound is the key insight — it limits all possible values to `[0, 999]`, making the state space finite and BFS tractable.

---

## Approach — BFS on State Space [0, 999]

**Key Insight:** Every intermediate value, after `% 1000`, lies in the range `[0, 999]`. This gives a state space of exactly 1000 nodes. Run BFS where each node is a current value, and each edge is "multiply by one element of `arr`, then mod 1000".

### Algorithm

1. If `start == end`, return `0`.
2. Push `[start, 0]` (value, steps) into a BFS queue.
3. Maintain a `visited[1000]` array to avoid revisiting states.
4. For each state `(val, step)` dequeued:
   - For each multiplier `n` in `arr`:
     - Compute `x = (val * n) % 1000`.
     - If `x == end` → return `step + 1`.
     - If not visited → mark visited and enqueue `[x, step + 1]`.
5. If queue empties without finding `end` → return `-1`.

### Walkthrough

```
arr = [2, 5, 7],  start = 3,  end = 30

Queue: [(3, 0)]
visited[3] = true

Dequeue (3, 0):
  3 × 2 = 6   → enqueue (6,  1)
  3 × 5 = 15  → enqueue (15, 1)
  3 × 7 = 21  → enqueue (21, 1)

Dequeue (6, 1):
  6 × 2 = 12  → enqueue (12, 2)
  6 × 5 = 30  → 30 == end ✓  return 2
```

### Why Only 1000 States?

```
x = (val * n) % 1000   →   x ∈ [0, 999]

Max unique states = 1000
Max edges per state = arr.size()
Total work = O(1000 × n)
```

Once a value is visited, we never need to reach it again via a longer path — BFS guarantees the first visit is the shortest.

### Complexity

| | Value |
|---|---|
| **Time** | O(1000 × n) — 1000 states, n multipliers each |
| **Space** | O(1000) — visited array + queue bounded by state space |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Forgetting mod 1000**
Without `% 1000` the values grow unboundedly and the state space becomes infinite.

**Mistake 2: Using DFS instead of BFS**
DFS finds a path but not the shortest one. Always use BFS for minimum-step problems with uniform edge weights.

**Mistake 3: Not marking visited before enqueuing**
Marking visited only on dequeue can enqueue the same state multiple times, causing TLE. Mark it immediately when enqueuing.

---

## Related Problems
- Word Ladder — LeetCode 127
- Minimum Steps to One
- Jump Game — LeetCode 55

---

## Tags

BFS | Shortest Path | Graph | Modular Arithmetic | State Space | Minimum Steps | Java | GeeksForGeeks | Minimum Multiplications to Reach End
