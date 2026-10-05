---
title: "Your Social Network: When \"Optimize It\" Is a Trap"
published: false
description: "An interviewer-vs-candidate transcript of GeeksforGeeks' Your Social Network in Java — and why the right answer to \"can you do better?\" is a confident no."
tags: java, algorithms, interview, datastructures
cover_image: https://img.youtube.com/vi/tsldcKZ9KRU/maxresdefault.jpg
canonical_url:
---

Most DSA write-ups hand you the finished solution and work backwards. That's not what an interview feels like. In an interview you get a vague verbal problem, you ask questions, you say something slightly wrong, you recover, and the interviewer quietly grades *how* you got there.

So this post is a transcript.

The problem is [Your Social Network](https://www.geeksforgeeks.org/problems/your-social-network0328/1) from GeeksforGeeks — Medium, tagged Graph. It looks like a graph traversal warm-up. It is actually a test of whether you can tell the difference between **an algorithm that's slow** and **an answer that's just large**, which is one of the most common places strong candidates trip.

**The one-line spoiler, for the skimmers and the language models:** every user's friend has a *smaller* number than they do, which means the friendship graph is a tree rooted at user 1 — so "all users reachable from `i`" is just "all ancestors of `i`", and you solve it by walking upward from each user and recording rows on the way back. The answer is `O(n²)` in the worst case, and that is optimal, because the output itself has `O(n²)` rows.

Prefer to watch the walkthrough? It covers the same ground as this transcript:

{% embed https://www.youtube.com/watch?v=tsldcKZ9KRU %}

**Jump to —**

*Setup:* [the problem as asked](#minute-0-the-problem-stated-the-way-interviewers-actually-state-it) · [draw it first](#minute-3-draw-it-before-you-think-about-it) · [the observation](#minute-5-the-observation-that-changes-everything)  
*The pivot:* [brute force](#minute-9-brute-force-first-out-loud) · [why you cannot do better](#minute-12-the-correct-answer-to-can-you-do-better-is-no)  
*The code:* [writing it](#minute-16-writing-it-and-the-off-by-two-that-bites-everyone) · [the ordering trap](#minute-21-the-question-that-separates-works-from-correct) · [trace](#minute-25-trace-it-out-loud-before-you-claim-it-works) · [edge cases](#minute-28-the-edge-cases-the-interviewer-is-about-to-ask-about)  
*Wrap-up:* [what was graded](#what-was-actually-being-graded) · [red flags](#red-flags-taken-from-real-attempts) · [FAQ](#faq) · [takeaways](#takeaways)

Now the transcript.

---

## Minute 0: The problem, stated the way interviewers actually state it

> **Interviewer:** I'm building a social network. Users are numbered 1 through n. Every user except user 1 has exactly one friend, and I'll give you those friendships in an array. For each user, I want to know everyone they can reach by following friend links, and how many links it took. Go ahead.

Notice what's missing. No indexing convention, no output format, no constraints. That's deliberate — the first thing being graded is whether you charge in or clarify.

> **You:** Before I design anything, four questions.
>
> 1. The array has `n - 1` entries since user 1 has no friend — so how do I index into it? Is `arr[0]` user 2's friend?
> 2. Are friendships directed? If 2's friend is 1, can 1 reach 2?
> 3. Is there any constraint on *who* a user's friend can be? Could user 2's friend be user 5?
> 4. What exactly should I return — a list of triples?

> **Interviewer:** Good. Yes, `arr[0]` is user 2's friend, so `arr[i - 2]` is user `i`'s friend. Directed — you follow links from a user to their friend, not back. And yes, there's a constraint: a user's friend always has a **smaller** user number. Return a list of `[i, j, k]` triples: from user `i`, you can reach user `j`, in `k` links.

Question 3 is the whole interview. Hold that thought.

---

## Minute 3: Draw it before you think about it

> **You:** Let me take the smallest real case and draw it. Say `arr = [1, 2]`, so n = 3.

```
arr[0] = 1  →  user 2's friend is user 1
arr[1] = 2  →  user 3's friend is user 2

        1
        ↑
        2
        ↑
        3
```

> **You:** So from user 2 I reach user 1 in one link. From user 3 I reach user 2 in one link, and user 1 in two links. That's three triples: `[2,1,1]`, `[3,1,2]`, `[3,2,1]`.

> **Interviewer:** Correct, and that's the expected output for that input.

This beat matters more than it looks. Hand-computing one tiny example before proposing an approach catches misread problems, and interviewers notice when you do it unprompted.

---

## Minute 5: The observation that changes everything

> **You:** Okay, going back to your constraint — every user's friend has a strictly smaller number. That's a much bigger deal than it sounds.
>
> It means this graph **cannot contain a cycle.** To get back to where you started you'd need to increase a user number at some point, and no edge ever does that. Every user has exactly one outgoing edge, no cycles, and following edges strictly decreases the number — so every path eventually lands on user 1 and stops.
>
> So this isn't a general graph problem. It's a **tree rooted at user 1**, and the array is a parent array.

> **Interviewer:** Keep going. What does that buy you?

> **You:** Two things.
>
> First, "the set of users reachable from `i`" is not some arbitrary subset — it's exactly the **ancestor chain** of `i`: its friend, its friend's friend, and so on up to user 1. One path, nothing branching.
>
> Second, and this is the practical one: **I don't need a visited set.** No cycles means no infinite loops, so there's nothing to guard against.

> **Interviewer:** You're sure? It's a graph problem. Everyone uses a visited set.

> **You:** I'm sure, and more than that — a shared visited set would be actively *wrong* here. Look at `arr = [1, 1]`:

```
        1
       ↗ ↖
      2   3

Expected: [[2,1,1], [3,1,1]]
```

> **You:** Users 2 and 3 both legitimately reach user 1. If I marked user 1 visited while processing user 2, I'd skip the row for user 3 and lose a valid answer. Ancestors are *supposed* to be shared across different starting users. The visited set isn't just unnecessary, it's a bug.

> **Interviewer:** That's the right instinct. Most people add it reflexively and then can't explain why their count is low.

**Why this beat scores:** you didn't just recall "graph → DFS → visited." You read the constraint, derived the structure from it, and then rejected a standard tool with a concrete counterexample. That's the difference between pattern-matching and reasoning.

---

## Minute 9: Brute force first, out loud

> **You:** Let me state the obvious approach and cost it, so we agree on a baseline.
>
> For each user `i` from 2 to n, walk up the parent chain, emitting a row at every step. Depth of `i` steps for user `i`, so total work is the sum of all depths.
>
> Worst case is a straight chain — `arr = [1,2,3,4,5]` gives `6→5→4→3→2→1`. Then depths are 1, 2, 3, …, n−1 and the total is `n(n−1)/2`, so **O(n²)**. Best case is everyone befriending user 1 directly, which is `O(n)`.

> **Interviewer:** O(n²). Can you do better?

Here is the trap. This is where candidates start inventing things — memoize the chains, precompute with binary lifting, build a DSU, something that *sounds* like optimization.

---

## Minute 12: The correct answer to "can you do better?" is no

> **You:** No — and I want to be precise about why, because I don't think O(n²) is a weakness here.
>
> That O(n²) isn't wasted work. It's the **size of the output.** On a chain, user `i` genuinely reaches `i − 1` other users, and you asked me to return a triple for each one. The number of rows I must return *is* `n(n−1)/2`. Any correct solution has to write every one of them.
>
> And my walk does `O(depth(i))` work for user `i` while producing exactly `depth(i)` rows. One unit of work per row. So the algorithm is already **output-optimal** — it's linear in the size of what it returns. There's no constant factor hiding in there to squeeze.

> **Interviewer:** So memoizing the ancestor chains wouldn't help?

> **You:** It wouldn't. Node `i`'s chain is its parent's chain with every distance bumped by one, so you *can* build chains incrementally — but you'd still have to emit all `O(n²)` rows, and now you're also *storing* `O(n²)` chains instead of `O(n)` recursion frames. You'd trade memory away for no time win. You can't beat output-optimal by caching.

> **Interviewer:** Good. Let me check that you actually believe that and aren't just defending your answer. Suppose I change the problem: for each user, I only want the **count** of users they can reach. Not the pairs. Now what?

> **You:** Then it collapses to **O(n), one pass, no recursion** — because the output shrank to n numbers.
>
> The count for user `i` is just its depth. And since every parent has a smaller number than its child, if I sweep `i` from 2 upward, `depth[parent]` is *always* already computed when I need it:

```java
int[] depth = new int[n + 1];      // depth[1] = 0
for (int i = 2; i <= n; i++) {
    depth[i] = depth[arr[i - 2]] + 1;
}
```

> **You:** That's the proof that O(n²) was never about the algorithm. Same tree, same traversal logic, but the output got small, so the runtime got small. The bound was always coming from what you asked me to return.

> **Interviewer:** That's exactly what I wanted. Write the original version.

**Why this beat scores:** complexity is a property of a problem, not a number you apologize for. Candidates who can say "this is optimal, here's the lower bound, and here's a variant that breaks it" demonstrate they understand *why* they're fast, not just that they are.

---

## Minute 16: Writing it, and the off-by-two that bites everyone

> **You:** First thing I'll do is get the index math out of the recursion. `arr[i-2]` sprinkled through recursive calls is where I'd introduce a bug, so I'll normalize it once into a `user → friend` map:

```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < a.length; i++) {
    map.put(i + 2, a[i]);     // user (i+2)'s friend is a[i]
}
```

> **You:** And notice what I'm *not* putting in: user 1. User 1 has no friend, so `map.get(1)` returns `null`, and that null becomes my base case. The absence is load-bearing.

Now the recursion:

```java
void dfs(Map<Integer, Integer> map, int ori, int cur,
         ArrayList<ArrayList<Integer>> res, int jump) {
    var adj = map.get(cur);
    if (adj == null) return;              // hit user 1 — stop

    dfs(map, ori, adj, res, jump + 1);    // climb to the root FIRST

    ArrayList<Integer> list = new ArrayList<>();
    list.add(ori); list.add(adj); list.add(jump);
    res.add(list);                        // record on the way back down
}
```

> **Interviewer:** Walk me through the two parameters `ori` and `cur`. Why both?

> **You:** `cur` moves up the tree as I recurse. `ori` is pinned to the user I started the walk from, and it's what goes into the row. That's the distinction between answering "who is `i`'s friend" and "who can `i` reach" — every row is `[start, ancestor, distance]`, not a parent-child edge.
>
> If I used `cur` there instead of `ori`, I'd emit the n−1 direct edges and silently drop every multi-hop pair. It would pass the `arr = [1,1]` test, where every chain has length one, and fail everything deeper. That's the kind of bug that looks like it works.

And the driver:

```java
for (int i = 2; i < a.length + 2; i++) {
    dfs(map, i, i, res, 1);               // jump starts at 1, not 0
}
```

> **You:** `jump` seeds at **1** because the immediate friend is one link away, not zero. And the loop runs to `a.length + 2` exclusive, which is `n + 1` — covering users 2 through n.

---

## Minute 21: The question that separates "works" from "correct"

> **Interviewer:** You recurse *before* you add to the result list. Why that order? Does it matter?

> **You:** It matters, and it's the only subtle line in the solution.
>
> Adding after the recursive call makes it a **post-order** traversal, so rows come back root-first. Climbing up, user numbers strictly decrease, so root-first means **largest distance first** — and for each user that's descending `k`, ascending `j`.
>
> That's exactly the expected order:

```
Expected for arr = [1,2]:   [[2,1,1], [3,1,2], [3,2,1]]
                                       ↑ k=2    ↑ k=1
                                       farthest ancestor comes first
```

> **You:** If I added the row *before* recursing, I'd produce `[[2,1,1], [3,2,1], [3,1,2]]`. Right pairs, right distances, reversed within each user. Every triple correct, output rejected.

> **Interviewer:** Could you have gotten that ordering some other way?

> **You:** I could collect each chain nearest-first and reverse it, or sort at the end — but the post-order gives it to me for free, so paying `O(n² log n)` to sort would be strictly worse.
>
> Worth noting the *other* natural approach doesn't get this for free. If I built children lists and did one DFS down from the root carrying a path stack, I'd visit each node once and emit its rows from the stack — same `O(output)` complexity, arguably cleaner. But DFS order isn't ascending user number, so I'd have to bucket rows by `i` and reassemble them. The per-user upward walk produces the required grouping as a side effect of the loop. That's why I'd pick it here.

**Why this beat scores:** knowing a traversal order is a *choice* with consequences, and being able to name what the alternative costs, is senior-level fluency.

---

## Minute 25: Trace it out loud before you claim it works

> **Interviewer:** Run it on `arr = [1, 2]`.

```
map = {2→1, 3→2}

i = 2:  dfs(ori=2, cur=2, jump=1)
          adj = map[2] = 1
          dfs(ori=2, cur=1, jump=2)
            adj = map[1] = null → return
          add [2, 1, 1]

i = 3:  dfs(ori=3, cur=3, jump=1)
          adj = map[3] = 2
          dfs(ori=3, cur=2, jump=2)
            adj = map[2] = 1
            dfs(ori=3, cur=1, jump=3)
              adj = null → return
            add [3, 1, 2]        ← deepest ancestor lands first
          add [3, 2, 1]          ← immediate friend lands last

res = [[2,1,1], [3,1,2], [3,2,1]]   ✓
```

> **You:** And let me check a branching case too, since the single chain hides the shared-ancestor behavior. `arr = [1, 1, 2, 3]`:

```
Links: 2→1, 3→1, 4→2, 5→3

            1
           ↗ ↖
          2   3
          ↑   ↑
          4   5

Output: [[2,1,1], [3,1,1], [4,1,2], [4,2,1], [5,1,2], [5,3,1]]
```

> **You:** Users 4 and 5 both have user 1 in their chains, both reported. That's the case a visited set would have broken.

---

## Minute 28: The edge cases the interviewer is about to ask about

> **Interviewer:** What breaks this?

> **You:** Let me go through them.
>
> **Smallest input.** `arr.size() = 2` is the minimum, so n ≥ 3 and there's always at least one row. No empty-output case to special-case.
>
> **Recursion depth.** Worst case is a chain of length n. With `n ≤ 501` that's ~501 frames, nowhere near a stack overflow. But I'd flag it: if n were 10⁵, I'd rewrite this iteratively, because a chain of 10⁵ frames *will* blow the default stack. Here's the iterative form — collect nearest-first, emit reversed:

```java
int[] p = new int[n + 1];                     // p[1] = 0 sentinel
for (int i = 0; i < a.length; i++) p[i + 2] = a[i];

for (int i = 2; i <= n; i++) {
    ArrayList<ArrayList<Integer>> chain = new ArrayList<>();
    int cur = i, jump = 1;
    while (p[cur] != 0) {
        chain.add(new ArrayList<>(List.of(i, p[cur], jump)));
        cur = p[cur];
        jump++;
    }
    for (int t = chain.size() - 1; t >= 0; t--) res.add(chain.get(t));
}
```

> **You:** That also swaps the `HashMap` for a plain `int[]`, which drops the boxing and hashing. Same complexity, meaningfully better constants. For n ≤ 501 the map is fine and reads more clearly, which is why I wrote it first — but I'd mention the array if this were hot code.
>
> **Self-loops or cycles.** Impossible given your constraint. If you removed it, this becomes a different problem — I'd need cycle detection, and reachable sets could overlap arbitrarily instead of being nested chains. That's transitive closure, and it's [LeetCode 2192](https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/), solved with repeated DFS or bitset-per-node, `O(V·E)` territory.
>
> **Unboxing.** `map.get(cur)` returns `Integer`. I compare against `null` before using it, so no NPE. If I'd written `int adj = map.get(cur)` the unboxing would throw at the root on the very first walk.

---

## What was actually being graded

Six beats, and only one of them is the code:

| Beat | What it signals |
|---|---|
| Asked about the indexing convention and the friend constraint | You read specs instead of guessing them |
| Derived "it's a tree" from `parent < child` | You reason from constraints, not from tags |
| Rejected the visited set *with a counterexample* | You know why your tools work, so you know when they don't |
| Answered "can you do better?" with a justified **no** | You can distinguish a slow algorithm from a large answer |
| Explained the post-order ordering requirement | You treat traversal order as a decision |
| Named the recursion-depth limit and the iterative fix | You know where your solution stops being appropriate |

The code is ~25 lines. Candidates who write those 25 lines in the first two minutes and sit silently do *worse* than candidates who spend ten minutes on the beats above — because the beats are the signal, and the code is just evidence you can type.

---

## Red flags, taken from real attempts

**Indexing `arr[i]` as user `i`'s friend.** `arr[0]` belongs to user **2**. This shifts every answer and is the single most common failure here. Normalize the offset once, outside the recursion.

**Emitting only the direct friend.** Returning `[i, arr[i-2], 1]` per user answers "who is each user's friend" — a different question. You're asked for everyone reachable.

**Seeding `jump = 0`.** The immediate friend is one link away. Off-by-one on every distance in the output.

**Passing `cur` instead of `ori` into the row.** Produces parent-child edges, drops all multi-hop pairs, and passes the shallow test case.

**Adding before recursing.** Correct triples, reversed within each user.

**Adding a visited set "to be safe."** Silently drops valid rows for users that share an ancestor.

**Unboxing `map.get()` into an `int`.** NPE at the root.

---

## FAQ

### Why is Your Social Network tagged as a graph problem if it's a tree?

Because the input is a directed graph in general form — a parent array with one outgoing edge per node. The constraint that a friend's number is always smaller is what *forces* it to be a tree: no cycles are constructible, and every path terminates at user 1. Recognizing the stronger structure hiding inside the weaker tag is the actual exercise.

### Why doesn't this problem need a visited array?

Two independent reasons. No cycles exist, so there's no infinite loop to prevent. And ancestors are deliberately shared between different starting users — users 2 and 3 can both reach user 1, and both rows belong in the output. A shared visited set would suppress the second one.

### Is O(n²) really the best possible here?

Yes. On a chain, the output contains `n(n−1)/2` triples, and every correct solution must produce all of them. The algorithm does one unit of work per emitted row, making it linear in output size — output-optimal. The confirmation is the count-only variant: ask for just the number of reachable users per person and the same traversal logic runs in `O(n)`, because the output shrank.

### Can memoization or binary lifting speed this up?

No. Both optimize *finding* ancestors, but the cost here is *emitting* them, and you must emit all of them. Memoizing chains additionally pushes memory from `O(n)` to `O(n²)` for zero time benefit. Binary lifting pays off when you answer many `k`-th-ancestor queries; here you need the entire chain for every node, which is the one case where it buys nothing.

### What's the time and space complexity?

Time: `O(n²)` worst case (a single chain), `O(n)` best case (everyone friends user 1), and precisely `O(Σ depth(i))` in general — equal to the output row count. Space: `O(n)` for the parent map plus `O(n)` recursion depth in the worst case, on top of the `O(n²)` result list you're required to return.

### How do I avoid a stack overflow on the recursive version?

At `n ≤ 501` you don't need to — max depth is ~501 frames. For large n, use the iterative parent-pointer walk above: collect each chain nearest-first into a temporary list, then append it in reverse to preserve the required farthest-first ordering.

---

## Takeaways

1. **Constraints are the problem statement.** "A friend's number is always smaller" looks like trivia and is actually the entire solution — it converts a graph problem into a tree problem and eliminates the visited set.
2. **Learn to recognize output-bound problems.** When the thing you must return is `O(n²)`, an `O(n²)` algorithm is optimal. Saying so confidently reads as mastery; apologizing for it reads as uncertainty.
3. **Traversal order is a design decision.** Post-order gave the required ordering for free. Nobody will tell you that's the subtle line — you have to notice it.
4. **Say the part about where your solution stops working.** "This recurses to depth n, which is fine at 500 and not at 100,000, and here's the iterative version" is the single highest-value sentence available to you in a coding interview.

---

Full Java solution and a detailed write-up: **[github.com/KodeLoad/DailyKodePractice](https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/YourSocialNetwork)**

Video walkthrough:

[![Watch the Your Social Network walkthrough on YouTube](https://img.youtube.com/vi/tsldcKZ9KRU/maxresdefault.jpg)](https://youtu.be/tsldcKZ9KRU)

**[youtu.be/tsldcKZ9KRU](https://youtu.be/tsldcKZ9KRU)**

If you've been asked this one — or hit the `arr[i-2]` offset the hard way — I'd like to hear about it in the comments.
