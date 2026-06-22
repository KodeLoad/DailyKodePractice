# Maximum Area Between Bars (Dam of Candies) | Two Pointer Greedy | GeeksForGeeks

---
> Video description: https://youtu.be/OAh5hnRDzUk

[Problem](https://www.geeksforgeeks.org/problems/dam-of-candies--141631/1) | [Java Solution](./Solution.java)

[![img](https://img.youtube.com/vi/OAh5hnRDzUk/0.jpg)](https://youtu.be/OAh5hnRDzUk)

---

**Difficulty:** Medium  
**Topics:** Array | Two Pointer | Greedy  
**Companies:** Amazon | Microsoft | Google | Adobe  
**Time Complexity:** O(n) | **Space Complexity:** O(1)

---

## What Is the Dam of Candies Problem?

Given a row of vertical bars with varying heights, two bars act as the **walls of a dam**. The dam can hold candies (or water) up to the height of the **shorter** wall, spread across the **gap** between the two bars.

```
Bars:     5   3   1   4   5
Index:    0   1   2   3   4

Choosing bars at index 0 (h=5) and index 4 (h=5):
  height  = min(5, 5) = 5
  gap     = 4 - 0 - 1 = 3   (spaces between the bars)
  area    = 5 × 3 = 15
```

The goal is to choose **two bars** that maximize this area.

---

## Problem Statement — Find Maximum Candy/Water Area Between Two Bars

Given a list `arr[]` of bar heights, find the **maximum area** that can be formed between any two bars, where:

```
area = min(arr[l], arr[r]) × (r - l - 1)
```

## Examples

**Example 1:**
```
Input:  arr[] = [5, 3, 1, 4, 5]
Output: 15

Bars at index 0 (h=5) and index 4 (h=5):
  area = min(5,5) × (4-0-1) = 5 × 3 = 15  ← maximum
```

**Example 2:**
```
Input:  arr[] = [1, 5, 4, 3]
Output: 6

Bars at index 1 (h=5) and index 3 (h=3):
  area = min(5,3) × (3-1-1) = 3 × 1 = 3

Bars at index 0 (h=1) and index 3 (h=3):
  area = min(1,3) × (3-0-1) = 1 × 2 = 2

Bars at index 1 (h=5) and index 2 (h=4):  ← nope, gap=0
  area = min(5,4) × (2-1-1) = 4 × 0 = 0

Bars at index 0 (h=1) and index 2 (h=4):
  area = min(1,4) × (2-0-1) = 1 × 1 = 1

Best: bars at index 0 and 3: min(1,3) × 2 = 2 ...
      bars at index 1 and 3: min(5,3) × 1 = 3 ...

Hmm let's check all:
  (0,1): min(1,5)×0 = 0
  (0,2): min(1,4)×1 = 1
  (0,3): min(1,3)×2 = 2
  (1,2): min(5,4)×0 = 0
  (1,3): min(5,3)×1 = 3
  (2,3): min(4,3)×0 = 0

Output: 3
```

**Example 3:**
```
Input:  arr[] = [3, 5, 1, 2, 4, 5]
Output: 15

Bars at index 1 (h=5) and index 5 (h=5):
  area = min(5,5) × (5-1-1) = 5 × 3 = 15  ← maximum
```

## Constraints
- 2 ≤ arr.size() ≤ 10⁵
- 1 ≤ arr[i] ≤ 10⁴

---

## Approach — Two Pointer Greedy

**Key Insight:** Start with the widest possible span — pointers at both ends. The area is limited by the **shorter** bar. Moving the taller bar inward can never improve the area (width decreases AND the height stays capped at the same shorter bar). So always move the **shorter** bar inward, hoping to find a taller bar that compensates for the reduced width.

### Why Move the Shorter Bar?

```
l=0 (h=3),  r=4 (h=5)

If we move r inward:
  - width shrinks
  - height is still capped by l (h=3)
  - area can only decrease or stay the same ✗

If we move l inward:
  - width shrinks
  - but we might find a taller bar that increases the height cap
  - area might improve ✓
```

Moving the taller bar is never beneficial — the shorter bar is always the bottleneck.

### Algorithm

1. Set `l = 0`, `r = arr.size() - 1`, `res = 0`.
2. While `l < r`:
   - Compute `dis = r - l - 1` (gap between bars).
   - The shorter bar determines the height:
     - If `arr[l] < arr[r]`: `height = arr[l]`, move `l++`.
     - Else: `height = arr[r]`, move `r--`.
   - Update `res = max(res, height × dis)`.
3. Return `res`.

### Walkthrough

```
arr = [5, 3, 1, 4, 5]
       l              r

Step 1: l=0(5), r=4(5)  dis=3  min=5  area=15  res=15
        arr[l]==arr[r] → r--

Step 2: l=0(5), r=3(4)  dis=2  min=4  area=8   res=15
        arr[l]=5 > arr[r]=4 → r--

Step 3: l=0(5), r=2(1)  dis=1  min=1  area=1   res=15
        arr[l]=5 > arr[r]=1 → r--

Step 4: l=0(5), r=1(3)  dis=0  min=3  area=0   res=15
        arr[l]=5 > arr[r]=3 → r--

l=0, r=0 → l not < r → stop

Output: 15 ✓
```

### Complexity

| | Value |
|---|---|
| **Time** | O(n) — each pointer moves inward at most n times total |
| **Space** | O(1) — only scalar variables used |

---

## How This Differs from Container With Most Water (LeetCode 11)

| | Dam of Candies (GFG) | Container With Most Water (LC 11) |
|---|---|---|
| Width formula | `r - l - 1` (gap between bars) | `r - l` (distance including endpoints) |
| Core idea | Same two-pointer greedy | Same two-pointer greedy |

The only difference is whether the two bars themselves are included in the width.

---

## Common Mistakes — Why Does My Solution Fail?

**Mistake 1: Using `r - l` instead of `r - l - 1`**
This problem counts only the **gap** between bars, not the bars themselves. Width = `r - l - 1`.

**Mistake 2: Moving the taller bar inward**
Always move the shorter bar. Moving the taller bar can never increase the area since the height is already capped by the shorter one.

**Mistake 3: Brute force O(n²)**
Checking all pairs is too slow for n = 10⁵. The two-pointer approach solves it in O(n).

---

## Related Problems
- Container With Most Water — LeetCode 11
- Trapping Rain Water — LeetCode 42
- Largest Rectangle in Histogram — LeetCode 84

---

## Tags

Two Pointer | Greedy | Array | Maximum Area | Dam of Candies | Container With Most Water | Java | GeeksForGeeks | Maximum Area Between Bars

---

## YouTube Comment — Copy-Paste Ready

```
🔥 Source Code → https://github.com/KodeLoad/DailyKodePractice/tree/mainline/GeeksForGeeks/MaximumAreaBetweenBars

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
📌 What you'll find in this video:
✅ Dam of Candies vs Container With Most Water — the key difference
✅ Why always move the SHORTER bar (greedy proof)
✅ Step-by-step dry run with full walkthrough
✅ O(n) time · O(1) space — no brute force needed
✅ Common mistakes with width formula (r-l vs r-l-1)

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔎 TOPICS COVERED:
Dam of Candies · Maximum Area Between Bars · Two Pointer · Greedy · Array · Java · GeeksForGeeks Daily Challenge

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
🔗 Problem Link → https://www.geeksforgeeks.org/problems/dam-of-candies--141631/1

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
⭐ If this helped you, LIKE + SUBSCRIBE — it helps more developers find this content!
📬 Drop your approach in the comments — let's discuss!

#DamOfCandies #MaximumAreaBetweenBars #GeeksForGeeks #TwoPointer #GreedyAlgorithm
#DataStructures #JavaProgramming #DailyChallenge #DSAWithOBrutus #KodeLoad
```
