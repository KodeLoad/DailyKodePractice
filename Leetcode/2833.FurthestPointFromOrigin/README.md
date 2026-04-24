# 2833. Furthest Point From Origin | LeetCode Easy | Greedy | String
---

> Video Solution: [https://youtu.be/eX_XLxQPCew](https://youtu.be/eX_XLxQPCew)

[Problem](https://leetcode.com/problems/furthest-point-from-origin/description/) | [Java Solution](./Solution.java)

[![thumbnail](https://img.youtube.com/vi/eX_XLxQPCew/maxresdefault.jpg)](https://youtu.be/eX_XLxQPCew)

---

**Difficulty:** Easy
**Daily Question:** 2026-04-24

You are on a number line starting at position `0`. You are given a string `moves` of length `n` consisting only of:
- `'L'` — move **left** (position − 1)
- `'R'` — move **right** (position + 1)
- `'_'` — **wildcard**, you choose to go left or right

Return the **furthest distance from the origin** you can reach by optimally assigning each `'_'`.

## Examples

**Example 1:**
<code>
Input: moves = "L_RL__R"
Output: 3
Explanation: countL=2, countR=2, blanks=3 → abs(2-2) + 3 = 3
</code>

**Example 2:**
<code>
Input: moves = "_R__LL_"
Output: 5
Explanation: countL=2, countR=1, blanks=4 → abs(2-1) + 4 = 5
</code>

**Example 3:**
<code>
Input: moves = "LR"
Output: 0
Explanation: countL=1, countR=1, blanks=0 → abs(1-1) + 0 = 0
</code>

## Constraints

- `1 <= moves.length <= 50`
- `moves` consists only of `'L'`, `'R'`, and `'_'`

---

## Approach — Greedy Count

The key insight: the fixed `L` and `R` moves give a **net displacement**. Every `'_'` wildcard should be assigned to whichever side the net displacement already favors (or either side if equal), because stacking wildcards in one direction always maximizes the absolute distance.

```
countL   = number of 'L' in moves
countR   = number of 'R' in moves
blanks   = number of '_' in moves

answer   = abs(countL - countR) + blanks
```

No simulation needed — a single pass over the string is sufficient.

---

## Implementation

<code>
public int furthestDistanceFromOrigin(String moves) {
    int left = 0, right = 0, blank = 0;
    for (char c : moves.toCharArray()) {
        if (c == 'L') left++;
        else if (c == 'R') right++;
        else blank++;
    }
    return Math.abs(left - right) + blank;
}
</code>

---

## Complexity Analysis

| Metric | Value |
|---|---|
| Time Complexity | O(n) — single pass |
| Space Complexity | O(1) — three counters |

---

## Key Takeaways

- **Greedy Observation:** Wildcards should always extend the dominant direction; splitting them never helps.
- **Absolute Difference Pattern:** `abs(countL - countR) + blanks` is the entire solution — no simulation required.
- **Wildcard Problems:** When a character can be freely assigned, determine which assignment maximizes the objective before iterating.

## Keywords

furthest point from origin LeetCode 2833, LeetCode easy greedy java, wildcard movement number line, L R blank string problem, LeetCode daily question April 2026, coding interview java greedy.

---

**SEO Tags:** #LeetCode #Java #EasyProblems #Greedy #StringManipulation #DailyQuestion #OBrutus #CodingInterview

**Learning Outcomes:**
- Recognizing that wildcards should be greedily assigned to one side.
- Reducing a simulation problem to a single arithmetic formula.
- Applying the absolute-difference pattern on character-count problems.
