# Overlapping Intervals
---

> Video description: https://youtu.be/Uj0U7ZD1t9Y

[Problem](https://www.geeksforgeeks.org/problems/overlapping-intervals--170633/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/Uj0U7ZD1t9Y/0.jpg)](https://youtu.be/Uj0U7ZD1t9Y)

---

**Difficulty:** Medium  
**Accuracy:** 57.41%  
**Submissions:** 119K+  
**Points:** 4  
**Average Time:** 20m

Given an array of Intervals `arr[][]`, where `arr[i] = [starti, endi]`. The task is to merge all of the overlapping Intervals.

## Examples:

**Example 1:**
```
Input: arr[][] = [[1, 3], [2, 4], [6, 8], [9, 10]]
Output: [[1, 4], [6, 8], [9, 10]]

Explanation: 
In the given intervals we have only two overlapping intervals here:
- [1, 3] and [2, 4] overlap because 2 is within [1, 3]
- They merge to become [1, 4]

Visual representation:
Timeline:  0  1  2  3  4  5  6  7  8  9  10
[1,3]:        |-----|
[2,4]:           |-----|
Merged:       |--------|

[6,8]:                    |-----|
[9,10]:                         |---|

Final: [[1, 4], [6, 8], [9, 10]]
```

**Example 2:**
```
Input: arr[][] = [[6, 8], [1, 9], [2, 4], [4, 7]]
Output: [[1, 9]]

Explanation:
All intervals overlap with [1, 9]:
- [1, 9] spans from 1 to 9
- [2, 4] is completely within [1, 9]
- [4, 7] is completely within [1, 9]
- [6, 8] is completely within [1, 9]

Visual representation:
Timeline:  0  1  2  3  4  5  6  7  8  9  10
[1,9]:        |---------------------|
[2,4]:           |-----|
[4,7]:                 |--------|
[6,8]:                    |-----|

All merge into: [1, 9]
```

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ starti ≤ endi ≤ 10⁶

## Expected Complexities:
- **Time Complexity:** O(n log n)
- **Space Complexity:** O(n)

## Company Tags:
Amazon | Microsoft | Google | Nutanix | Zoho

## Topic Tags:
Arrays | Sorting | Intervals | Greedy

## Approach:

### Problem Understanding:

**When do intervals overlap?**
Two intervals [a, b] and [c, d] overlap if:
- They share at least one common point
- Condition: `a ≤ d AND c ≤ b`
- Simplified: After sorting by start time, check if `c ≤ b`

**What is merging?**
- Combine overlapping intervals into one
- Merged interval: [min(start1, start2), max(end1, end2)]
- After sorting: [start1, max(end1, end2)]

### Key Observations:

1. **Sorting simplifies the problem:**
   - Sort intervals by start time
   - After sorting, we only need to check consecutive intervals
   - If current interval overlaps with previous, merge them

2. **Overlap condition (after sorting):**
   - If `current.start ≤ previous.end`, they overlap
   - Merge by extending previous.end to max(previous.end, current.end)

3. **Non-overlapping intervals:**
   - If `current.start > previous.end`, no overlap
   - Add previous interval to result
   - Start tracking new interval

4. **Greedy approach works:**
   - Process intervals from left to right
   - Merge as we go
   - Optimal solution guaranteed

### Algorithm:
```
// Step 1: Sort intervals by start time
sort(arr by start time)

// Step 2: Initialize result with first interval
result = []
current = arr[0]

// Step 3: Process remaining intervals
for i from 1 to n-1:
    if arr[i].start <= current.end:
        // Overlapping - merge
        current.end = max(current.end, arr[i].end)
    else:
        // Non-overlapping - save current and move to next
        result.add(current)
        current = arr[i]

// Step 4: Don't forget last interval
result.add(current)

return result
```

### Step-by-Step Walkthrough (Example 1):
```
Input: arr = [[1,3], [2,4], [6,8], [9,10]]

Step 1: Sort by start time
Already sorted: [[1,3], [2,4], [6,8], [9,10]]

Step 2: Initialize
current = [1, 3]
result = []

Step 3: Process intervals

i=1, arr[1] = [2, 4]:
- arr[1].start = 2
- current.end = 3
- 2 <= 3? Yes, overlapping!
- Merge: current.end = max(3, 4) = 4
- current = [1, 4]

i=2, arr[2] = [6, 8]:
- arr[2].start = 6
- current.end = 4
- 6 <= 4? No, not overlapping!
- Save current: result = [[1, 4]]
- current = [6, 8]

i=3, arr[3] = [9, 10]:
- arr[3].start = 9
- current.end = 8
- 9 <= 8? No, not overlapping!
- Save current: result = [[1, 4], [6, 8]]
- current = [9, 10]

Step 4: Add last interval
result = [[1, 4], [6, 8], [9, 10]]

Output: [[1, 4], [6, 8], [9, 10]] ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: arr = [[6,8], [1,9], [2,4], [4,7]]

Step 1: Sort by start time
Sorted: [[1,9], [2,4], [4,7], [6,8]]

Step 2: Initialize
current = [1, 9]
result = []

Step 3: Process intervals

i=1, arr[1] = [2, 4]:
- 2 <= 9? Yes, overlapping!
- Merge: current.end = max(9, 4) = 9
- current = [1, 9]

i=2, arr[2] = [4, 7]:
- 4 <= 9? Yes, overlapping!
- Merge: current.end = max(9, 7) = 9
- current = [1, 9]

i=3, arr[3] = [6, 8]:
- 6 <= 9? Yes, overlapping!
- Merge: current.end = max(9, 8) = 9
- current = [1, 9]

Step 4: Add last interval
result = [[1, 9]]

Output: [[1, 9]] ✓
```

### Visual Understanding:

**Before Merging (Example 1):**
```
Timeline:  1  2  3  4  5  6  7  8  9  10
Interval 1:  [-----]
Interval 2:     [-----]
Interval 3:              [-----]
Interval 4:                    [--]
```

**After Merging:**
```
Timeline:  1  2  3  4  5  6  7  8  9  10
Merged 1:  [--------]
Interval 3:              [-----]
Interval 4:                    [--]
```

### Detailed Example (Complex Case):
```
Input: arr = [[1,4], [4,5], [5,6], [8,9], [9,11]]

After sorting: Already sorted

Process:
current = [1, 4]

[4, 5]: 4 <= 4? Yes → merge to [1, 5]
[5, 6]: 5 <= 5? Yes → merge to [1, 6]
[8, 9]: 8 <= 6? No → save [1, 6], current = [8, 9]
[9, 11]: 9 <= 9? Yes → merge to [8, 11]

Output: [[1, 6], [8, 11]]

Visual:
[1,4]:   |---|
[4,5]:       |---|
[5,6]:           |---|
Result:  |-----------|

[8,9]:               |---|
[9,11]:                  |-----|
Result:              |----------|
```

### Edge Cases:

1. **Single interval:**
   - Input: [[1, 5]]
   - Output: [[1, 5]]
   - Nothing to merge

2. **No overlaps:**
   - Input: [[1, 2], [3, 4], [5, 6]]
   - Output: [[1, 2], [3, 4], [5, 6]]
   - All intervals remain separate

3. **All intervals merge into one:**
   - Input: [[1, 3], [2, 5], [4, 6]]
   - Output: [[1, 6]]

4. **Touching intervals (edge case):**
   - Input: [[1, 2], [2, 3]]
   - 2 <= 2? Yes, they overlap at point 2
   - Output: [[1, 3]]

5. **One interval contains all others:**
   - Input: [[1, 10], [2, 3], [4, 5]]
   - Output: [[1, 10]]

6. **Identical intervals:**
   - Input: [[1, 3], [1, 3], [1, 3]]
   - Output: [[1, 3]]

### Why Sorting Works:

**Without sorting:**
```
Input: [[6,8], [1,3], [2,4]]
If we process in this order:
- Can't determine if [1,3] and [2,4] overlap without looking ahead
- Need to compare every interval with every other: O(n²)
```

**With sorting:**
```
Sorted: [[1,3], [2,4], [6,8]]
Process left to right:
- Current overlaps only need to check next interval
- Linear scan: O(n)
```

### Overlap Conditions:

**Visual representation:**
```
Interval A: [----]
Interval B:    [----]  (Overlap: B.start <= A.end)

Interval A: [----]
Interval B:        [----]  (No overlap: B.start > A.end)

Interval A: [----------]
Interval B:   [----]    (Complete overlap: B fully inside A)
```

### Common Mistakes:

1. **Not sorting first:**
   - Problem becomes much harder
   - Miss overlaps that aren't adjacent

2. **Wrong overlap condition:**
```
   Wrong: arr[i].start < current.end
   Right: arr[i].start <= current.end
   (Equal case means touching intervals)
```

3. **Forgetting to update end:**
```
   Wrong: current.end = arr[i].end
   Right: current.end = max(current.end, arr[i].end)
   (Current interval might extend beyond new one)
```

4. **Not adding last interval:**
   - After loop ends, current interval still needs to be added

5. **Modifying input array:**
   - Better to create new result array
   - Keeps input unchanged

### Implementation Details:

**Sorting comparator:**
```python
# Sort by start time, if equal then by end time
arr.sort(key=lambda x: (x[0], x[1]))

# Or simply:
arr.sort()  # Default sorts by first element, then second
```

**Merge logic:**
```python
if current_end >= next_start:
    # Merge
    current_end = max(current_end, next_end)
else:
    # No overlap
    result.append([current_start, current_end])
    current_start = next_start
    current_end = next_end
```

### Complexity Analysis:

**Time Complexity: O(n log n)**
- Sorting: O(n log n)
- Single pass through sorted array: O(n)
- Total: O(n log n) dominated by sorting

**Space Complexity: O(n)**
- Result array: O(n) in worst case (no overlaps)
- Sorting space: O(log n) to O(n) depending on algorithm
- Total: O(n)

**Best case:** All intervals merge into one → O(1) space for result
**Worst case:** No intervals overlap → O(n) space for result

### Optimization Notes:

**Can we do better than O(n log n)?**
- No, if input is unsorted
- Sorting is necessary: Ω(n log n)
- Linear scan is optimal: O(n)

**Space optimization:**
- If allowed to modify input, sort in-place
- Reuse input array for result when possible

## Related Interview Experiences:
- Merge Intervals (LeetCode 56)
- Insert Interval
- Interval List Intersections
- Employee Free Time
- Meeting Rooms II
- Non-overlapping Intervals

## Related Articles:
- Interval Problems Patterns
- Sorting Algorithms
- Greedy Algorithms
- Timeline Processing