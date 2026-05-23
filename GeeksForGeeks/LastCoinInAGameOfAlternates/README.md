# Last Coin in a Game of Alternates | Two Pointer Greedy | GeeksForGeeks

---
> Video description: https://youtu.be/G87wg6W_JpA

[Problem](https://www.geeksforgeeks.org/problems/last-coin-in-a-game-of-alternates/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/G87wg6W_JpA/0.jpg)](https://youtu.be/G87wg6W_JpA)

---

**Difficulty:** Easy  
**Topics:** Array | Two Pointer | Greedy | Game Theory  
**Companies:** Amazon | Paytm | Zoho  
**Time Complexity:** O(n) | **Space Complexity:** O(1)

---

## What Is the Game of Alternates?

Two players take turns picking a coin from **either end** of a coin array. At each step, the **larger** end coin is picked and removed. The game continues until only **one coin remains** — that is the answer.

```
arr = [3, 1, 2]

Step 1: ends are 3 and 2 → pick larger (3) → arr = [1, 2]
Step 2: ends are 1 and 2 → pick larger (2) → arr = [1]

Last coin = 1
```

---

## Problem Statement — Find the Last Remaining Coin After Greedy Alternating Picks

Given an array `arr[]` of coin values, at each step the **larger** of the two end coins is removed. Return the **last coin** left in the array.

## Examples

**Example 1:**
```
Input:  arr[] = [3, 1, 2]
Output: 1

Step 1: 3 vs 2 → remove 3 (larger) → [1, 2]
Step 2: 1 vs 2 → remove 2 (larger) → [1]
Last coin = 1
```

**Example 2:**
```
Input:  arr[] = [5, 3, 1, 4, 2]
Output: 1

Step 1: 5 vs 2 → remove 5 → [3, 1, 4, 2]
Step 2: 3 vs 2 → remove 3 → [1, 4, 2]
Step 3: 1 vs 2 → remove 2 → [1, 4]
Step 4: 1 vs 4 → remove 4 → [1]
Last coin = 1
```

**Example 3:**
```
Input:  arr[] = [4, 4]
Output: 4

Step 1: 4 vs 4 → equal → remove right → [4]
Last coin = 4
```

## Constraints
- 1 ≤ arr.size() ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁵

---

## Approach — Two Pointer Greedy

**Key Insight:** Use two pointers `l` and `r` at both ends. At each step, the larger end coin is removed by advancing its pointer inward. When the pointers meet, the single remaining coin is the answer.

No auxiliary space or actual removal is needed — pointer movement simulates the removal.

### Decision Rule

```
if arr[l] > arr[r]:
    remove left coin  →  l++
else:
    remove right coin →  r--

When l == r → arr[l] is the last coin
```

### Walkthrough

```
arr = [5, 3, 1, 4, 2]
       l           r

Step 1: arr[l]=5 > arr[r]=2 → l++
        arr = [5, 3, 1, 4, 2]
                  l        r

Step 2: arr[l]=3 > arr[r]=2 → l++
        arr = [5, 3, 1, 4, 2]
                     l     r

Step 3: arr[l]=1 <= arr[r]=2 → r--
        arr = [5, 3, 1, 4, 2]
                     l  r

Step 4: arr[l]=1 <= arr[r]=4 → r--
        arr = [5, 3, 1, 4, 2]
                     l,r

l == r → last coin = arr[2] = 1 ✓
```

### Why Two Pointer Works

The greedy rule (always remove the larger end) converges the pointers until one element remains. Since the larger coin is always eliminated first, the smallest coin in the array tends to survive — it never "wins" a comparison at either end.

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — each step advances one pointer, at most n-1 steps |
| **Space** | O(1) — no extra data structures, pointers only |

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Returning arr[l] vs arr[r] without checking which is correct**
When l == r, both point to the same element. Either `arr[l]` or `arr[r]` works — they are identical at convergence.

**Mistake 2: Using a queue or deque to simulate removal**
That costs O(n) space and O(n²) time unnecessarily. The two-pointer approach simulates removal in O(1) per step.

**Mistake 3: Off-by-one on the equal case**
When `arr[l] == arr[r]`, the code picks `r--` (remove right). Either side is valid when equal, but the choice must be consistent to avoid an infinite loop.

---

## Related Problems
- Find the Winner of a Circular Game — LeetCode 1823
- Predict the Winner — LeetCode 486
- Last Stone Weight — LeetCode 1046

---

## Tags

Two Pointer | Greedy | Game Theory | Array | Last Coin | Alternating Pick | Java | GeeksForGeeks | Last Coin in a Game of Alternates

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/LastCoinInAGameOfAlternates

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ Game rules explained — what "alternates" actually means
✅ Why Two Pointer is the optimal approach (O(1) space)
✅ Step-by-step dry run with multiple examples
✅ O(n) time · O(1) space solution
✅ Common mistakes with equal coins and pointer convergence

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Last Coin in a Game of Alternates · Two Pointer · Greedy · Game Theory · Array · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/last-coin-in-a-game-of-alternates/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#LastCoinGame #GeeksForGeeks #TwoPointer #GreedyAlgorithm #GameTheory
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
```
