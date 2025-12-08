# Construct List Using Given q XOR Queries
---

> Video description: https://www.youtube.com/watch?v=enyuqljxFjk

[Problem](https://www.geeksforgeeks.org/problems/construct-list-using-given-q-xor-queries/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/enyuqljxFjk/0.jpg)](https://www.youtube.com/watch?v=enyuqljxFjk)

---

**Difficulty:** Medium  
**Accuracy:** 50.86%  
**Submissions:** 37K+  
**Points:** 4  
**Average Time:** 20m

Given a list s that initially contains only a single value 0. There will be q queries of the following types:

- **0 x:** Insert x in the list
- **1 x:** For every element a in s, replace it with a ^ x. ('^' denotes the bitwise XOR operator)

Return the sorted list after performing the given q queries.

## Examples:

**Example 1:**
```
Input:
q = 5
queries[] = {{0, 6}, {0, 3}, {0, 2}, {1, 4}, {1, 5}}

Output:
1 2 3 7

Explanation:
[0] (initial value)
[0, 6] (add 6 to list)
[0, 6, 3] (add 3 to list)
[0, 6, 3, 2] (add 2 to list)
[4, 2, 7, 6] (XOR each element by 4)
[1, 7, 2, 3] (XOR each element by 5)

The sorted list after performing all the queries is [1, 2, 3, 7].
```

**Example 2:**
```
Input:
q = 3
queries[] = {{0, 2}, {1, 3}, {0, 5}}

Output:
1 3 5

Explanation:
[0] (initial value)
[0, 2] (add 2 to list)
[3, 1] (XOR each element by 3)
[3, 1, 5] (add 5 to list)

The sorted list after performing all the queries is [1, 3, 5].
```

## Constraints:
- 1 ≤ q ≤ 10⁵
- 0 ≤ x ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(q × log(q))
- **Space Complexity:** O(l), where l is the length of output list

## Company Tags:
Amazon | Google

## Topic Tags:
Bit Manipulation | XOR | Arrays | Sorting

## Approach:

### Brute Force Analysis (TLE):

**Naive Approach:**
```
list = [0]
for each query (type, x):
    if type == 0:
        list.append(x)
    else:  // type == 1
        for i in range(len(list)):
            list[i] ^= x
sort(list)
return list
```

**Why this fails:**
- Time: O(q²) - XOR operation on all elements for each type-1 query
- Too slow for q = 10⁵

### Key Observations:

1. **XOR Properties:**
   - a ^ b ^ b = a (XOR twice with same value returns original)
   - XOR is commutative: a ^ b = b ^ a
   - XOR is associative: (a ^ b) ^ c = a ^ (b ^ c)

2. **Cumulative XOR Trick:**
   - Instead of XORing each element immediately, track cumulative XOR
   - When inserting new element, XOR it with current cumulative value
   - At the end, XOR all elements with cumulative value

3. **Critical Insight:**
   - Keep track of cumulative XOR of all type-1 queries
   - When adding element with type-0 query, add (x ^ cumulative_xor)
   - This accounts for all future XOR operations that haven't happened yet
   - At the end, XOR all elements with final cumulative_xor

4. **Order of Operations:**
   - Type-1 queries affect all existing elements
   - But we can reverse the logic:
     - Instead of XORing existing elements, XOR new elements before adding
     - Then apply final cumulative XOR at the end

### Optimal Algorithm:

**Approach 1: Forward Processing**
```
list = []
cumulative_xor = 0

for each query (type, x):
    if type == 0:
        // Add element XORed with current cumulative
        list.append(x ^ cumulative_xor)
    else:  // type == 1
        // Update cumulative XOR
        cumulative_xor ^= x

// Add initial 0 (also affected by all XORs)
list.append(cumulative_xor)

// Sort and return
sort(list)
return list
```

**Approach 2: Reverse Processing**
```
list = [0]
cumulative_xor = 0

// Process queries in reverse
for each query (type, x) in reverse:
    if type == 1:
        cumulative_xor ^= x
    else:  // type == 0
        list.append(x ^ cumulative_xor)

// All elements including initial 0
for i in range(len(list)):
    list[i] ^= cumulative_xor

sort(list)
return list
```

**Complexity:**
- Time: O(q) for processing + O(q log q) for sorting = O(q log q)
- Space: O(q) for storing list

### Step-by-Step Walkthrough (Example 1):

**Using Forward Processing:**
```
queries = {{0,6}, {0,3}, {0,2}, {1,4}, {1,5}}

Initial: list = [], cumulative_xor = 0

Query 1: {0, 6}
- Type 0: Add 6 ^ 0 = 6
- list = [6], cumulative_xor = 0

Query 2: {0, 3}
- Type 0: Add 3 ^ 0 = 3
- list = [6, 3], cumulative_xor = 0

Query 3: {0, 2}
- Type 0: Add 2 ^ 0 = 2
- list = [6, 3, 2], cumulative_xor = 0

Query 4: {1, 4}
- Type 1: Update cumulative
- list = [6, 3, 2], cumulative_xor = 0 ^ 4 = 4

Query 5: {1, 5}
- Type 1: Update cumulative
- list = [6, 3, 2], cumulative_xor = 4 ^ 5 = 1

Final: Add initial 0 affected by all XORs
- list = [6, 3, 2, 0 ^ 1] = [6, 3, 2, 1]

But wait, we need to XOR existing elements too!
Actually, the elements added before type-1 queries need to be XORed.

Let me recalculate with correct approach...
```

**Correct Forward Processing:**
```
Initial: list = [], cumulative_xor = 0

Query 1: {0, 6}
- Add: 6 ^ cumulative_xor = 6 ^ 0 = 6
- list = [6]

Query 2: {0, 3}
- Add: 3 ^ cumulative_xor = 3 ^ 0 = 3
- list = [6, 3]

Query 3: {0, 2}
- Add: 2 ^ cumulative_xor = 2 ^ 0 = 2
- list = [6, 3, 2]

Query 4: {1, 4}
- cumulative_xor = 0 ^ 4 = 4
- This affects all existing and future elements

Query 5: {1, 5}
- cumulative_xor = 4 ^ 5 = 1
- This affects all existing and future elements

Add initial 0:
- 0 ^ cumulative_xor = 0 ^ 1 = 1
- list = [6, 3, 2, 1]

But we need to XOR all elements with cumulative:
Actually, elements added BEFORE type-1 queries need additional XOR.

This approach is getting complex. Let's use reverse processing!
```

**Using Reverse Processing (Cleaner):**
```
queries = {{0,6}, {0,3}, {0,2}, {1,4}, {1,5}}

Initial: list = [0], cumulative_xor = 0

Process in REVERSE order:

Query 5: {1, 5}
- Type 1: cumulative_xor = 0 ^ 5 = 5

Query 4: {1, 4}
- Type 1: cumulative_xor = 5 ^ 4 = 1

Query 3: {0, 2}
- Type 0: Add 2 ^ 1 = 3
- list = [0, 3]

Query 2: {0, 3}
- Type 0: Add 3 ^ 1 = 2
- list = [0, 3, 2]

Query 1: {0, 6}
- Type 0: Add 6 ^ 1 = 7
- list = [0, 3, 2, 7]

Apply cumulative XOR to all:
- list = [0^1, 3^1, 2^1, 7^1] = [1, 2, 3, 6]

Wait, this doesn't match either...
```

Let me reconsider the correct approach:

### Correct Understanding:

**Key Insight:**
- Track cumulative XOR from all type-1 queries seen so far
- When adding element, XOR it with current cumulative
- Don't forget the initial 0 which also gets XORed by everything

**Correct Algorithm:**
```
list = []
xor_val = 0

for each query (type, x):
    if type == 0:
        list.append(x)
    else:  // type == 1
        xor_val ^= x

// XOR all elements with final xor_val
for i in range(len(list)):
    list[i] ^= xor_val

// Don't forget initial 0
list.append(xor_val)

sort(list)
return list
```

### Step-by-Step (Example 1) - Correct:
```
queries = {{0,6}, {0,3}, {0,2}, {1,4}, {1,5}}

Process queries:
Query {0, 6}: list = [6]
Query {0, 3}: list = [6, 3]
Query {0, 2}: list = [6, 3, 2]
Query {1, 4}: xor_val = 4
Query {1, 5}: xor_val = 4 ^ 5 = 1

XOR all elements:
list = [6^1, 3^1, 2^1] = [7, 2, 3]

Add initial 0:
list = [7, 2, 3, 1]

Sort: [1, 2, 3, 7] ✓
```

### Step-by-Step (Example 2) - Correct:
```
queries = {{0,2}, {1,3}, {0,5}}

Process queries:
Query {0, 2}: list = [2]
Query {1, 3}: xor_val = 3
Query {0, 5}: list = [2, 5]

XOR all elements:
list = [2^3, 5^3] = [1, 6]

Wait, that's wrong too...

Let me trace more carefully:
```

**Most Careful Trace (Example 2):**
```
Initial: [0]

{0, 2}: [0, 2]
{1, 3}: [0^3, 2^3] = [3, 1]
{0, 5}: [3, 1, 5]

Sorted: [1, 3, 5] ✓

So type-0 queries AFTER type-1 should NOT be affected by that XOR!
```

### Final Correct Algorithm:
```
list = [0]  // Start with 0
xor_val = 0

for each query (type, x):
    if type == 0:
        // New element should have cumulative XOR applied
        list.append(x ^ xor_val)
    else:  // type == 1
        // Update cumulative XOR
        xor_val ^= x

// Apply final XOR to ALL elements (including initial 0)
for i in range(len(list)):
    list[i] ^= xor_val

sort(list)
return list
```

### Final Trace (Example 2):
```
Initial: list = [0], xor_val = 0

{0, 2}: list.append(2 ^ 0) = [0, 2], xor_val = 0
{1, 3}: xor_val = 0 ^ 3 = 3
{0, 5}: list.append(5 ^ 3) = [0, 2, 6], xor_val = 3

Apply final XOR:
list = [0^3, 2^3, 6^3] = [3, 1, 5]

Sort: [1, 3, 5] ✓
```

## Common Mistakes:

1. **Not tracking cumulative XOR**
2. **Applying XOR twice to same elements**
3. **Forgetting initial 0**
4. **Not XORing new elements with cumulative before adding**

## Related Interview Experiences:
- XOR Queries of a Subarray
- Range XOR Queries
- Maximum XOR of Two Numbers
- Single Number variations

## Related Articles:
- XOR Properties and Applications
- Cumulative Operations Optimization
- Query Processing Techniques