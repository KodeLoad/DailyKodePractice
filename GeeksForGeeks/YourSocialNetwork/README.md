# Your Social Network | DFS Ancestor Chain on a Rooted Tree | GeeksForGeeks

---
> Video description: https://youtu.be/tsldcKZ9KRU

[Problem](https://www.geeksforgeeks.org/problems/your-social-network0328/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/tsldcKZ9KRU/0.jpg)](https://youtu.be/tsldcKZ9KRU)

---

**Difficulty:** Medium  
**Topics:** Graph | Tree | DFS | Recursion | Ancestors | HashMap  
**Companies:** Amazon | Microsoft | Flipkart | Adobe  
**Time Complexity:** O(n²) worst case | **Space Complexity:** O(n) recursion + O(n²) output

---

## What Does "Your Social Network" Actually Ask?

Every user `i` (from `2` to `n`) has **exactly one friend**, and that friend always has a **smaller user number**. User `1` has no friend.

That single rule is the whole problem. "Exactly one parent, and the parent number is always smaller" means the friendship graph can never contain a cycle — it is a **tree rooted at user 1**.

```
arr[] = [1, 2]        arr[i-2] is the friend of user i

arr[0] = 1  →  user 2's friend is 1
arr[1] = 2  →  user 3's friend is 2

        1          ← root (no friend)
        ↑
        2
        ↑
        3

"Users reachable from i"  ==  "all ancestors of i"
"Number of links"         ==  "distance up to that ancestor"
```

So for every user you simply walk **upward to the root**, recording `[i, ancestor, distance]` at each step.

---

## Problem Statement — Find All Reachable Friends and Their Link Distance

Geek is building a social networking site called **Geeksbook** with `n` users numbered `1` to `n`.

- Each user `i` (`2 ≤ i ≤ n`) has exactly **one** friend, whose user number is **smaller** than `i`.
- User `1` has no friend.
- The friends of users `2 … n` are given in an array `arr[]` of size `n - 1`, where **`arr[i - 2]` is the friend of user `i`** (so `arr[0]` is user 2's friend, `arr[1]` is user 3's friend, and so on).

For every user `i` from `2` to `n`, find **all** users `j` (`1 ≤ j < i`) reachable from `i` by following friend links. For each reachable pair, produce `[i, j, k]` where `k` is the number of links that must be followed to get from `i` to `j`.

## Examples

**Example 1:**
```
Input:  arr[] = [1, 2]
Output: [[2, 1, 1], [3, 1, 2], [3, 2, 1]]

Links: 2 → 1,  3 → 2

User 2 reaches 1 in 1 link            → [2, 1, 1]
User 3 reaches 1 in 2 links (3→2→1)   → [3, 1, 2]
User 3 reaches 2 in 1 link            → [3, 2, 1]
```

**Example 2:**
```
Input:  arr[] = [1, 1]
Output: [[2, 1, 1], [3, 1, 1]]

Links: 2 → 1,  3 → 1

        1
       ↗ ↖
      2   3

Both 2 and 3 reach only user 1, each in a single link.
```

**Example 3 (branching tree):**
```
Input:  arr[] = [1, 1, 2, 3]
Output: [[2, 1, 1], [3, 1, 1], [4, 1, 2], [4, 2, 1], [5, 1, 2], [5, 3, 1]]

Links: 2 → 1,  3 → 1,  4 → 2,  5 → 3

            1
           ↗ ↖
          2   3
          ↑   ↑
          4   5

User 4: 4→2 (1 link), 4→2→1 (2 links)
User 5: 5→3 (1 link), 5→3→1 (2 links)
```

## Constraints
- 2 ≤ arr.size() ≤ 500
- 1 ≤ arr[i] ≤ 500
- `arr[i - 2] < i` is guaranteed by the problem (friend always has a smaller number)

---

## Approach — DFS to the Root, Record on the Way Back

**Key Insight:** Because every user's friend has a strictly smaller number, there are **no cycles and no visited-set needed**. Each user has exactly one outgoing edge, so the "reachable set" of user `i` is just the single path `i → parent → grandparent → … → 1`. The answer is the **ancestor chain** of every node.

### Step 1 — Fix the Off-by-Two Mapping

The array is 0-indexed but users start at 2, so build an explicit `user → friend` map instead of fighting the index math inside the recursion:

```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < a.length; i++) {
    map.put(i + 2, a[i]);     // user (i+2)'s friend is a[i]
}
```

User `1` is deliberately **absent** from the map — `map.get(1)` returning `null` is what terminates the recursion.

### Step 2 — Walk Up, Append on the Return

```java
void dfs(Map<Integer,Integer> map, int ori, int cur,
         ArrayList<ArrayList<Integer>> res, int jump) {
    var adj = map.get(cur);
    if (adj == null) return;        // reached user 1 → stop

    dfs(map, ori, adj, res, jump + 1);   // go deeper (further up the tree) FIRST

    ArrayList<Integer> list = new ArrayList<>();
    list.add(ori); list.add(adj); list.add(jump);
    res.add(list);                       // record on the way back
}
```

Two details carry the whole solution:

- **`ori` never changes** — it is the user we started from, so every row records a pair `(start, ancestor)`, not a parent–child edge.
- **Recursing before adding** (post-order) means rows come out **farthest ancestor first**. Since ancestors have strictly decreasing user numbers as you go up, this is exactly the ordering GFG expects: `[3,1,2]` before `[3,2,1]`.

> Flip those two lines and you get `[[3,2,1],[3,1,2]]` — the right pairs in the wrong order.

### Algorithm

1. Build `map` where `map[i + 2] = arr[i]` for every index `i`.
2. For each user `i` from `2` to `n` (i.e. `arr.length + 1`), call `dfs(map, i, i, res, 1)` — starting `jump = 1` because the immediate friend is one link away.
3. Inside `dfs`, look up `adj = map.get(cur)`. If it is `null`, `cur` is user 1 — return.
4. Recurse into `adj` with `jump + 1` to reach the root first.
5. On unwinding, append `[ori, adj, jump]`.
6. Return `res` once every user has been processed.

### Walkthrough — Example 1

```
arr = [1, 2]        map = {2→1, 3→2}        n = 3

i = 2:  dfs(ori=2, cur=2, jump=1)
          adj = map[2] = 1
          dfs(ori=2, cur=1, jump=2)
            adj = map[1] = null → return
          add [2, 1, 1]
        res = [[2,1,1]]

i = 3:  dfs(ori=3, cur=3, jump=1)
          adj = map[3] = 2
          dfs(ori=3, cur=2, jump=2)
            adj = map[2] = 1
            dfs(ori=3, cur=1, jump=3)
              adj = null → return
            add [3, 1, 2]          ← deepest ancestor recorded first
          add [3, 2, 1]            ← immediate friend recorded last
        res = [[2,1,1], [3,1,2], [3,2,1]]

Output: [[2, 1, 1], [3, 1, 2], [3, 2, 1]]  ✓
```

### Walkthrough — Worst Case (Straight Chain)

```
arr = [1, 2, 3, 4, 5]     →   6 → 5 → 4 → 3 → 2 → 1

i=2 → 1 row
i=3 → 2 rows
i=4 → 3 rows
i=5 → 4 rows
i=6 → 5 rows
      ───────
      15 rows = 5·6/2

Output: [[2,1,1], [3,1,2],[3,2,1], [4,1,3],[4,2,2],[4,3,1],
         [5,1,4],[5,2,3],[5,3,2],[5,4,1],
         [6,1,5],[6,2,4],[6,3,3],[6,4,2],[6,5,1]]
```

A single chain gives `1 + 2 + … + (n−1) = O(n²)` rows. That is the **output size itself**, not algorithmic waste — no approach can beat it, because every one of those rows must be returned.

### Complexity

| | Value |
|---|---|
| **Time** | O(n²) worst case — equals the number of output rows (sum of all node depths); O(n log n) on a balanced tree |
| **Space** | O(n) for the map + O(n) recursion depth (worst case a full chain) + O(n²) for the result list |

With `n ≤ 501`, the worst case is ~125k rows — comfortably fast.

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Off-by-two on the array index**
`arr[0]` is the friend of user **2**, not user 0 or user 1. Reading `arr[i]` as "user `i`'s friend" shifts every single answer.
```java
// Wrong
map.put(i, a[i]);
// Correct
map.put(i + 2, a[i]);
```

**Mistake 2: Only recording the direct friend**
Returning just `[i, arr[i-2], 1]` for each user answers the wrong question. The problem asks for **every** reachable user — the full ancestor chain, each with its own link count.

**Mistake 3: Appending before recursing (wrong row order)**
GFG expects, for each user, the farthest ancestor first. Post-order (recurse, *then* add) produces that naturally; pre-order reverses it.

**Mistake 4: Starting `jump` at 0**
The immediate friend is **1** link away, not 0. Seeding `jump = 1` in the initial call makes every subsequent depth correct.

**Mistake 5: Passing `cur` instead of `ori` into the row**
`ori` must stay pinned to the user the walk started from. Using `cur` records parent→child edges and loses the multi-hop pairs entirely.

**Mistake 6: Forgetting the `null` base case**
User 1 is intentionally not a key in the map. Without `if (adj == null) return;` the first walk to the root throws a `NullPointerException` on unboxing.

**Mistake 7: Adding a visited set "to be safe"**
Tempting if you treat this as a general graph — but it actively breaks things. Different users legitimately share ancestors (see Example 3), and a shared visited set would skip valid pairs. The `parent < child` guarantee already makes cycles impossible.

---

## Related Problems
- All Ancestors of a Node in a Directed Acyclic Graph — LeetCode 2192
- Kth Ancestor in a Tree — GFG
- Lowest Common Ancestor of a Binary Tree — LeetCode 236
- Count the Number of Complete Components — LeetCode 2685
- Number of Provinces — LeetCode 547

---

## Tags

Graph | Tree | DFS | Recursion | Ancestors | Rooted Tree | Parent Array | HashMap | Reachability | Post-order Traversal | Java | GeeksForGeeks | Your Social Network | Geeksbook

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/YourSocialNetwork

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ Why "friend number is always smaller" secretly makes this a TREE
✅ Reachable users = ancestor chain — no visited set needed, ever
✅ The arr[i-2] off-by-two mapping that breaks most first attempts
✅ Why we recurse FIRST and append on the way back (output order!)
✅ Full dry run + the O(n²) chain worst case explained
✅ 7 common mistakes — wrong jump seed, ori vs cur, missing base case

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Your Social Network · Geeksbook · DFS · Rooted Tree · Ancestors · Parent Array · Recursion · Graph · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/your-social-network0328/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#GeeksforGeeks #graph #tree #DFS #Recursion #Ancestors #CodingInterview #DSA
#Algorithms #GFGPOTD #Programming #ProblemSolving #OBrutus #TechInterview
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
#YourSocialNetwork #TreeTraversal
```
