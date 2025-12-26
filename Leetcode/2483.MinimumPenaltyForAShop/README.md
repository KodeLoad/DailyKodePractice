# Minimum Penalty for a Shop
---

> Video description: https://youtu.be/6H3dnD5XiwM

[Problem](https://leetcode.com/problems/minimum-penalty-for-a-shop/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/6H3dnD5XiwM/0.jpg)](https://youtu.be/6H3dnD5XiwM)

---

**Difficulty:** Medium  
**Topics:** String | Prefix Sum  
**Average Time:** 25m

You are given the customer visit log of a shop represented by a **0-indexed** string `customers` consisting only of characters `'N'` and `'Y'`:

- if the i<sup>th</sup> character is `'Y'`, it means that customers come at the i<sup>th</sup> hour
- whereas `'N'` indicates that no customers come at the i<sup>th</sup> hour.

If the shop closes at the j<sup>th</sup> hour (`0 ≤ j ≤ n`), the **penalty** is calculated as follows:

- For every hour when the shop is **open** and no customers come, the penalty increases by `1`.
- For every hour when the shop is **closed** and customers come, the penalty increases by `1`.

Return the **earliest** hour at which the shop must be closed to incur a **minimum** penalty.

**Note** that if a shop closes at the j<sup>th</sup> hour, it means the shop is closed at the hour j.

## Examples:

**Example 1:**
```
Input: customers = "YYNY"
Output: 2

Explanation:
- Closing at hour 0: penalty = 1+1+0+1 = 3 (Y,Y,N,Y all missed)
- Closing at hour 1: penalty = 0+1+0+1 = 2 (first Y served, rest missed)
- Closing at hour 2: penalty = 0+0+0+1 = 1 (Y,Y served, N doesn't matter, last Y missed)
- Closing at hour 3: penalty = 0+0+1+1 = 2 (Y,Y,N served, last Y missed)
- Closing at hour 4: penalty = 0+0+1+0 = 1 (all served, penalty for N)

Minimum penalty is 1, occurring at the earliest hour 2.
```

**Example 2:**
```
Input: customers = "NNNNN"
Output: 0

Explanation:
It is best to close the shop at hour 0 as no customers arrive.
Closing at hour 0: penalty = 0 (no customers ever)
Closing later incurs penalty for each N hour the shop is open.
```

**Example 3:**
```
Input: customers = "YYYY"
Output: 4

Explanation:
It is best to close the shop at hour 4 to serve all customers.
Any earlier closing misses customers and incurs penalties.
```

## Constraints:
- 1 ≤ customers.length ≤ 10⁵
- customers consists only of characters `'Y'` and `'N'`

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1)

## Company Tags:
Amazon | Google | Microsoft

## Topic Tags:
String | Prefix Sum | Array | Greedy

## Approach:

### Problem Understanding:

**Penalty Rules:**
1. **Shop open + No customers (N):** +1 penalty
2. **Shop closed + Customers come (Y):** +1 penalty
3. **Shop open + Customers come (Y):** 0 penalty
4. **Shop closed + No customers (N):** 0 penalty

**Closing at hour j means:**
- Hours [0, j-1]: Shop is open
- Hours [j, n-1]: Shop is closed
- If j = 0: Shop never opens
- If j = n: Shop open for all hours

### Brute Force Analysis:

**Try Every Closing Hour:**
```
minPenalty = infinity
bestHour = -1

for j from 0 to n:
    penalty = 0
    
    // Count penalty when open (before j)
    for i from 0 to j-1:
        if customers[i] == 'N':
            penalty++
    
    // Count penalty when closed (from j onwards)
    for i from j to n-1:
        if customers[i] == 'Y':
            penalty++
    
    if penalty < minPenalty:
        minPenalty = penalty
        bestHour = j

return bestHour
```

**Complexity:**
- Time: O(n²) - nested loops
- Too slow for n = 10⁵

### Key Observations:

1. **Penalty Components:**
   - Opening penalty: Count of 'N's before closing hour
   - Closing penalty: Count of 'Y's from closing hour onwards

2. **Prefix Sum Insight:**
   - Precompute counts to avoid recalculation
   - For each position, we can calculate penalty in O(1)

3. **Incremental Calculation:**
   - As we move closing hour right by 1:
     - If current hour is 'Y': closing penalty decreases by 1
     - If current hour is 'N': opening penalty increases by 1

4. **Optimal Strategy:**
   - Start from initial penalty (close at hour 0)
   - Move closing hour right, update penalty incrementally
   - Track minimum penalty and earliest hour

### Optimal Algorithm: Single Pass with Running Penalty
```
function bestClosingTime(customers):
    n = customers.length
    
    // Initial penalty: close at hour 0 (all Y's are missed)
    penalty = count of 'Y' in customers
    minPenalty = penalty
    bestHour = 0
    
    // Try closing at each hour from 1 to n
    for i from 0 to n-1:
        if customers[i] == 'Y':
            penalty--  // One less Y is missed if we stay open
        else:  // customers[i] == 'N'
            penalty++  // Penalty for staying open with no customers
        
        if penalty < minPenalty:
            minPenalty = penalty
            bestHour = i + 1
    
    return bestHour
```

### Step-by-Step Walkthrough (Example 1):
```
Input: customers = "YYNY"
n = 4

Step 1: Initialize
- Count Y's in string: 3
- penalty = 3 (if we close at hour 0)
- minPenalty = 3
- bestHour = 0

Step 2: Process each hour

Hour 0 (customers[0] = 'Y'):
- Before: penalty = 3
- 'Y' found: penalty-- = 2
- 2 < 3? Yes! Update: minPenalty = 2, bestHour = 1
- Meaning: Close at hour 1 (after serving first customer)

Hour 1 (customers[1] = 'Y'):
- Before: penalty = 2
- 'Y' found: penalty-- = 1
- 1 < 2? Yes! Update: minPenalty = 1, bestHour = 2
- Meaning: Close at hour 2 (after serving first two customers)

Hour 2 (customers[2] = 'N'):
- Before: penalty = 1
- 'N' found: penalty++ = 2
- 2 < 1? No, don't update

Hour 3 (customers[3] = 'Y'):
- Before: penalty = 2
- 'Y' found: penalty-- = 1
- 1 < 1? No, don't update (same penalty but not earlier)

Output: bestHour = 2

Verification:
Close at hour 2 means open for hours [0, 1], closed for [2, 3]
- Hour 0: Y, open → no penalty ✓
- Hour 1: Y, open → no penalty ✓
- Hour 2: N, closed → no penalty ✓
- Hour 3: Y, closed → penalty = 1 ✓
Total penalty = 1 (minimum) ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: customers = "NNNNN"
n = 5

Step 1: Initialize
- Count Y's: 0
- penalty = 0 (if close at hour 0)
- minPenalty = 0
- bestHour = 0

Step 2: Process each hour

Hour 0 (customers[0] = 'N'):
- 'N' found: penalty++ = 1
- 1 < 0? No

Hour 1 (customers[1] = 'N'):
- 'N' found: penalty++ = 2
- 2 < 0? No

...all subsequent hours increase penalty

Output: bestHour = 0

Verification:
Close at hour 0 (never open):
- All N's are when closed → no penalty ✓
Total penalty = 0 (minimum) ✓
```

### Step-by-Step Walkthrough (Example 3):
```
Input: customers = "YYYY"
n = 4

Step 1: Initialize
- Count Y's: 4
- penalty = 4 (if close at hour 0)
- minPenalty = 4
- bestHour = 0

Step 2: Process each hour

Hour 0 (customers[0] = 'Y'):
- penalty-- = 3
- Update: minPenalty = 3, bestHour = 1

Hour 1 (customers[1] = 'Y'):
- penalty-- = 2
- Update: minPenalty = 2, bestHour = 2

Hour 2 (customers[2] = 'Y'):
- penalty-- = 1
- Update: minPenalty = 1, bestHour = 3

Hour 3 (customers[3] = 'Y'):
- penalty-- = 0
- Update: minPenalty = 0, bestHour = 4

Output: bestHour = 4

Verification:
Close at hour 4 (open all hours):
- All Y's served when open → no penalty ✓
Total penalty = 0 (minimum) ✓
```

### Visual Understanding:

**Example: "YNYY"**
```
Closing times and penalties:

Close at 0: |YNYY    penalty = 0+1+0+0 = 1 (all Y missed when closed)
Close at 1: Y|NYY    penalty = 0+0+0+0 = 0 (Y served, rest missed)
Close at 2: YN|YY    penalty = 0+1+0+0 = 1 (N open penalty)
Close at 3: YNY|Y    penalty = 0+1+0+0 = 1 (N open penalty)
Close at 4: YNYY|    penalty = 0+1+0+0 = 1 (N open penalty)

Minimum = 0 at hour 1
```

**Penalty Calculation Pattern:**
```
customers = "YYNY"

Initial (close at 0): penalty = count(Y) = 3

Move to hour 1:
- Process customers[0] = 'Y'
- Y means: if we stay open, we serve this customer
- penalty decreases: 3 - 1 = 2

Move to hour 2:
- Process customers[1] = 'Y'
- penalty decreases: 2 - 1 = 1

Move to hour 3:
- Process customers[2] = 'N'
- N means: if we stay open, we waste this hour
- penalty increases: 1 + 1 = 2

Move to hour 4:
- Process customers[3] = 'Y'
- penalty decreases: 2 - 1 = 1
```

### Why This Works:

**Mathematical Explanation:**
```
Penalty at closing hour j:
= (# of N before j) + (# of Y from j onwards)

When moving from j to j+1:
- Add customers[j] to "open" hours
- Remove customers[j] from "closed" hours

If customers[j] == 'Y':
  - One less Y in closed portion → penalty--

If customers[j] == 'N':
  - One more N in open portion → penalty++

This incremental update maintains correct penalty value.
```

**Proof of Correctness:**
```
Initial penalty (j=0):
= 0 + count(Y in [0, n-1])
= total Y's (all missed when closed)

After processing i hours:
= count(N in [0, i-1]) + count(Y in [i, n-1])

This matches the definition of penalty for closing at hour i.
```

### Edge Cases:

1. **All customers ('Y'):**
   - Input: "YYYY"
   - Output: 4 (stay open all hours)
   - Penalty: 0

2. **No customers ('N'):**
   - Input: "NNNN"
   - Output: 0 (never open)
   - Penalty: 0

3. **Single character:**
   - Input: "Y"
   - Output: 1 (open for 1 hour)
   - Input: "N"
   - Output: 0 (never open)

4. **All Y except one N:**
   - Input: "YYYNY"
   - Best to stay open all time, penalty = 1 for the N

5. **Alternating pattern:**
   - Input: "YNYN"
   - Need to calculate for each position

6. **Multiple optimal hours:**
   - Return earliest one (guaranteed by checking `<` not `<=`)

### Common Mistakes:

1. **Wrong initial penalty:**
```
   Wrong: penalty = 0
   Right: penalty = count of all Y's
```

2. **Not returning earliest hour:**
```
   Wrong: if penalty <= minPenalty (uses <=)
   Right: if penalty < minPenalty (uses <)
```

3. **Off-by-one in hour assignment:**
```
   Wrong: bestHour = i
   Right: bestHour = i + 1
   (closing at hour i+1 means served hours [0, i])
```

4. **Forgetting to update penalty:**
   - Must update penalty for each character
   - Direction depends on Y or N

5. **Wrong penalty update direction:**
```
   When seeing 'Y': should decrease (serving customer)
   When seeing 'N': should increase (wasted hour)
```

### Implementation Variations:

**Approach 1: Count-based (shown above)**
```python
def bestClosingTime(customers):
    penalty = customers.count('Y')
    min_penalty = penalty
    best_hour = 0
    
    for i, c in enumerate(customers):
        penalty += 1 if c == 'N' else -1
        if penalty < min_penalty:
            min_penalty = penalty
            best_hour = i + 1
    
    return best_hour
```

**Approach 2: Prefix/Suffix arrays**
```python
def bestClosingTime(customers):
    n = len(customers)
    
    # prefix[i] = count of N before i
    prefix = [0] * (n + 1)
    for i in range(n):
        prefix[i + 1] = prefix[i] + (1 if customers[i] == 'N' else 0)
    
    # suffix[i] = count of Y from i onwards
    suffix = [0] * (n + 1)
    for i in range(n - 1, -1, -1):
        suffix[i] = suffix[i + 1] + (1 if customers[i] == 'Y' else 0)
    
    min_penalty = float('inf')
    best_hour = 0
    
    for j in range(n + 1):
        penalty = prefix[j] + suffix[j]
        if penalty < min_penalty:
            min_penalty = penalty
            best_hour = j
    
    return best_hour
```

### Complexity Analysis:

**Time Complexity: O(n)**
- Initial count: O(n)
- Single pass through string: O(n)
- Total: O(n)

**Space Complexity: O(1)**
- Only using constant variables
- No additional data structures
- Optimal space usage

**Why This is Optimal:**
- Must examine each character at least once: Ω(n)
- Our solution: O(n) with O(1) space
- Cannot do better than linear time

## Related Interview Experiences:
- Best Time to Buy and Sell Stock
- Maximum Subarray
- Range Sum Query
- Minimum Cost to Move Chips
- Defuse the Bomb

## Related Articles:
- Prefix Sum Techniques
- Greedy Algorithms
- String Processing
- Optimization Problems
- Running Sum Patterns