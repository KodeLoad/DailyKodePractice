# Maximize Happiness of Selected Children
---

> Video description: https://youtu.be/Y9tNkxyUEws

[Problem](https://leetcode.com/problems/maximize-happiness-of-selected-children/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/Y9tNkxyUEws/0.jpg)](https://youtu.be/Y9tNkxyUEws)

---

**Difficulty:** Medium  
**Topics:** Array | Greedy | Sorting  
**Average Time:** 25m

You are given an array `happiness` of length n, and a positive integer k.

There are n children standing in a queue, where the i<sup>th</sup> child has **happiness value** `happiness[i]`. You want to select k children from these n children in k turns.

In each turn, when you select a child, the **happiness value** of all the children that have **not** been selected till now decreases by 1. Note that the happiness value **cannot** become negative and gets decremented **only** if it is positive.

Return the **maximum sum** of the happiness values of the selected children you can achieve by selecting k children.

## Examples:

**Example 1:**
```
Input: happiness = [1,2,3], k = 2
Output: 4

Explanation: We can pick 2 children in the following way:
- Pick the child with the happiness value == 3. The happiness value of the 
  remaining children becomes [0,1].
- Pick the child with the happiness value == 1. The happiness value of the 
  remaining child becomes [0]. Note that the happiness value cannot become 
  less than 0.
The sum of the happiness values of the selected children is 3 + 1 = 4.
```

**Example 2:**
```
Input: happiness = [1,1,1,1], k = 2
Output: 1

Explanation: We can pick 2 children in the following way:
- Pick any child with happiness value == 1. Remaining children's happiness 
  becomes [0,0,0].
- Pick the child with happiness value == 0 (from remaining). The happiness 
  value of the remaining child becomes [0,0].
The sum of the happiness values is 1 + 0 = 1.
```

**Example 3:**
```
Input: happiness = [2,3,4,5], k = 1
Output: 5

Explanation: We can pick 1 child in the following way:
- Pick the child with the happiness value == 5. Since we only need to pick 
  1 child, the sum of happiness values is 5.
```

## Constraints:
- 1 ≤ n == happiness.length ≤ 2 × 10⁵
- 1 ≤ happiness[i] ≤ 10⁸
- 1 ≤ k ≤ n

## Expected Complexities:
- **Time Complexity:** O(n log n)
- **Space Complexity:** O(1) or O(log n) for sorting

## Company Tags:
Google | Amazon | Microsoft

## Topic Tags:
Array | Greedy | Sorting | Priority Queue

## Approach:

### Problem Understanding:

**Key Points:**
1. Select k children from n children
2. Each turn, select one child
3. After selection, all unselected children's happiness decreases by 1
4. Happiness cannot go below 0
5. Goal: Maximize sum of selected children's happiness

**Crucial Observation:**
- When we select a child at turn i (0-indexed), its actual happiness contribution is:
  - `happiness[i] - i` (decreased by number of previous selections)
  - But cannot be negative: `max(0, happiness[i] - i)`

### Key Observations:

1. **Greedy Choice:**
   - Always pick the child with highest current happiness
   - This maximizes immediate gain
   - Even though selection decreases others' happiness, picking highest first is optimal

2. **Effect of Selection Order:**
   - First selected child: contributes full happiness
   - Second selected child: contributes (happiness - 1)
   - Third selected child: contributes (happiness - 2)
   - i-th selected child (0-indexed): contributes max(0, happiness - i)

3. **Why Greedy Works:**
   - If we have values [a, b] where a > b
   - Option 1: Pick a first, then b → a + max(0, b-1)
   - Option 2: Pick b first, then a → b + max(0, a-1)
   - Option 1 is always ≥ Option 2

4. **Sorting Insight:**
   - Sort happiness array in descending order
   - Pick first k children from sorted array
   - Apply decrements based on selection order

### Algorithm:
```
// Step 1: Sort happiness in descending order
sort(happiness in descending order)

// Step 2: Select k children greedily
totalHappiness = 0
for i from 0 to k-1:
    // i-th selection gets decreased by i
    contribution = max(0, happiness[i] - i)
    totalHappiness += contribution

return totalHappiness
```

### Step-by-Step Walkthrough (Example 1):
```
Input: happiness = [1, 2, 3], k = 2

Step 1: Sort in descending order
sorted = [3, 2, 1]

Step 2: Select k=2 children

Selection 0 (i=0):
- happiness[0] = 3
- Decrements: 0 (first selection)
- Contribution: max(0, 3 - 0) = 3
- totalHappiness = 3

Selection 1 (i=1):
- happiness[1] = 2
- Decrements: 1 (second selection)
- Contribution: max(0, 2 - 1) = 1
- totalHappiness = 3 + 1 = 4

Output: 4 ✓

Explanation:
- Pick child with happiness 3 first
- Remaining children's happiness becomes [2-1, 1-1] = [1, 0]
- Pick child with happiness 1 next
- Total: 3 + 1 = 4
```

### Step-by-Step Walkthrough (Example 2):
```
Input: happiness = [1, 1, 1, 1], k = 2

Step 1: Sort in descending order
sorted = [1, 1, 1, 1] (already sorted)

Step 2: Select k=2 children

Selection 0 (i=0):
- happiness[0] = 1
- Contribution: max(0, 1 - 0) = 1
- totalHappiness = 1

Selection 1 (i=1):
- happiness[1] = 1
- Contribution: max(0, 1 - 1) = 0
- totalHappiness = 1 + 0 = 1

Output: 1 ✓
```

### Step-by-Step Walkthrough (Example 3):
```
Input: happiness = [2, 3, 4, 5], k = 1

Step 1: Sort in descending order
sorted = [5, 4, 3, 2]

Step 2: Select k=1 child

Selection 0 (i=0):
- happiness[0] = 5
- Contribution: max(0, 5 - 0) = 5
- totalHappiness = 5

Output: 5 ✓
```

### Detailed Example (Complex Case):
```
Input: happiness = [10, 15, 8, 20, 12], k = 3

Step 1: Sort descending
sorted = [20, 15, 12, 10, 8]

Step 2: Select k=3 children

Selection 0 (i=0):
- Child with happiness 20
- Contribution: max(0, 20 - 0) = 20
- Total = 20

Selection 1 (i=1):
- Child with happiness 15 (now decreased by 1)
- Contribution: max(0, 15 - 1) = 14
- Total = 20 + 14 = 34

Selection 2 (i=2):
- Child with happiness 12 (now decreased by 2)
- Contribution: max(0, 12 - 2) = 10
- Total = 34 + 10 = 44

Output: 44

Simulation of actual process:
Turn 1: [10, 15, 8, 20, 12]
  - Pick 20 → get 20
  - Remaining: [9, 14, 7, 11] (decreased by 1)

Turn 2: [9, 14, 7, 11]
  - Pick 14 → get 14
  - Remaining: [8, 6, 10] (decreased by 1)

Turn 3: [8, 6, 10]
  - Pick 10 → get 10

Total: 20 + 14 + 10 = 44 ✓
```

### Why Greedy Works:

**Proof by Exchange Argument:**
```
Consider any two children with happiness a and b where a > b.

Case 1: Pick a first, then b
- Contribution from a: a
- Contribution from b: max(0, b - 1)
- Total: a + max(0, b - 1)

Case 2: Pick b first, then a
- Contribution from b: b
- Contribution from a: max(0, a - 1)
- Total: b + max(0, a - 1)

Since a > b:
- a + max(0, b - 1) ≥ b + max(0, a - 1)
- Picking higher value first is always better or equal

Therefore, sorting and picking greedily is optimal.
```

**Intuition:**
- Higher values suffer less from decrements (more room before hitting 0)
- Pick them early to maximize their contribution
- Lower values get decreased anyway, pick them later

### Edge Cases:

1. **k = 1:**
   - Input: happiness = [5, 3, 8], k = 1
   - Output: 8 (just pick maximum)

2. **k = n:**
   - Input: happiness = [1, 2, 3], k = 3
   - Must select all children
   - Output: 1 + 0 + 0 = 1

3. **All same happiness:**
   - Input: happiness = [5, 5, 5, 5], k = 2
   - Output: 5 + 4 = 9

4. **Small values with large k:**
   - Input: happiness = [1, 1, 1, 1, 1], k = 5
   - Output: 1 + 0 + 0 + 0 + 0 = 1

5. **Large decrements:**
   - Input: happiness = [100, 1, 1, 1], k = 4
   - Output: 100 + 0 + 0 + 0 = 100

6. **Values become 0 quickly:**
   - Input: happiness = [3, 2, 1], k = 3
   - Output: 3 + 1 + 0 = 4

### Mathematical Formula:

**For sorted array in descending order:**
```
Total = Σ(i=0 to k-1) max(0, happiness[i] - i)

This is equivalent to:
Total = Σ(i=0 to k-1) max(0, sorted[i] - decrements[i])
```

**Example:**
```
happiness = [20, 15, 10, 5], k = 3

Total = max(0, 20-0) + max(0, 15-1) + max(0, 10-2)
      = 20 + 14 + 8
      = 42
```

### Common Mistakes:

1. **Not sorting:**
   - Must sort to ensure picking highest values first
   - Random order doesn't guarantee optimal result

2. **Forgetting max(0, ...):**
```
   Wrong: happiness[i] - i
   Right: max(0, happiness[i] - i)
```

3. **Wrong sort order:**
```
   Wrong: Sort ascending
   Right: Sort descending
```

4. **Off-by-one in decrements:**
   - First selection (i=0) has 0 decrements
   - Second selection (i=1) has 1 decrement
   - i-th selection (0-indexed) has i decrements

5. **Not handling when happiness becomes 0:**
   - Once 0, contribution is 0
   - But still need to count in k selections

### Implementation Details:

**Python implementation:**
```python
def maximumHappinessSum(happiness, k):
    # Sort in descending order
    happiness.sort(reverse=True)
    
    total = 0
    for i in range(k):
        # Add contribution with decrement
        total += max(0, happiness[i] - i)
    
    return total
```

**Java implementation:**
```java
public long maximumHappinessSum(int[] happiness, int k) {
    Arrays.sort(happiness);
    long total = 0;
    int n = happiness.length;
    
    for (int i = 0; i < k; i++) {
        // Pick from end (highest values)
        long contribution = Math.max(0, happiness[n - 1 - i] - i);
        total += contribution;
    }
    
    return total;
}
```

### Complexity Analysis:

**Time Complexity: O(n log n)**
- Sorting: O(n log n)
- Selecting k children: O(k)
- Total: O(n log n) + O(k) = O(n log n)

**Space Complexity: O(1) or O(log n)**
- In-place sorting: O(1) auxiliary space
- Sorting algorithm (like quicksort): O(log n) stack space
- No additional data structures

**Optimization Notes:**
- If k << n, could use partial sort or heap
- Using max heap: O(n + k log n) time
- But full sort is simpler and sufficient

### Alternative Approach: Priority Queue
```python
import heapq

def maximumHappinessSum(happiness, k):
    # Use max heap (negate values for Python's min heap)
    heap = [-h for h in happiness]
    heapq.heapify(heap)  # O(n)
    
    total = 0
    for i in range(k):
        max_val = -heapq.heappop(heap)  # O(log n)
        total += max(0, max_val - i)
    
    return total
```

**Complexity:**
- Time: O(n + k log n)
- Space: O(n) for heap
- Advantage: Better when k << n

## Related Interview Experiences:
- Maximum Units on a Truck
- Reduce Array Size to The Half
- Minimum Cost to Connect Sticks
- Kth Largest Element in an Array
- Meeting Rooms II

## Related Articles:
- Greedy Algorithms
- Sorting Applications
- Priority Queue Problems
- Selection Algorithms