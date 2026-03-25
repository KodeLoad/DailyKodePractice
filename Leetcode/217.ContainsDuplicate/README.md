# 217 Contains Duplicate
---

> Video description: https://youtu.be/to8NbdEwxKY?si=JHJcnb1lHAwPxPd2

[Problem](https://leetcode.com/problems/contains-duplicate/) | [Java Solution](./Solution.java) | [Discussion](https://leetcode.com/problems/contains-duplicate/discuss/)

[![img](https://img.youtube.com/vi/to8NbdEwxKY/0.jpg)](https://youtu.be/to8NbdEwxKY?si=JHJcnb1lHAwPxPd2)

---

**Difficulty:** Easy  
**Acceptance Rate:** 61.5%  
**Submissions:** 4.8M+  
**Accepted:** 2.9M+  
**Topics:** Array | Hash Table | Sorting  
**Average Time:** 10m

Given an integer array `nums`, return `true` if any value appears **at least twice** in the array, and return `false` if every element is distinct.

## Examples:

**Example 1:**
```
Input: nums = [1,2,3,1]
Output: true

Explanation: 
The element 1 appears at index 0 and 3.
```

**Example 2:**
```
Input: nums = [1,2,3,4]
Output: false

Explanation: 
All elements are distinct.
```

**Example 3:**
```
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true

Explanation:
Multiple duplicates exist (1, 3, 4, 2 all appear more than once).
```

## Constraints:
- 1 ≤ nums.length ≤ 10⁵
- -10⁹ ≤ nums[i] ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(n)

## Company Tags:
Amazon | Google | Microsoft | Apple | Facebook | Adobe | Bloomberg | Uber | Yahoo

## Topic Tags:
Array | Hash Table | Hash Set | Sorting

## Approach:

### Approach 1: Hash Set (Optimal)

**Algorithm:**
```
seen = new HashSet()

for num in nums:
    if num in seen:
        return true
    seen.add(num)

return false
```

**Complexity:** O(n) Time, O(n) Space

### Approach 2: Sorting

**Algorithm:**
```
sort(nums)

for i from 0 to n-2:
    if nums[i] == nums[i+1]:
        return true

return false
```

**Complexity:** O(n log n) Time, O(1) Space

### Approach 3: Brute Force (TLE)

**Algorithm:**
```
for i from 0 to n-1:
    for j from i+1 to n-1:
        if nums[i] == nums[j]:
            return true
return false
```

**Complexity:** O(n²) Time - Too Slow!

### Visual Understanding:

**Hash Set Approach:**
```
nums = [1, 2, 3, 1]
seen = {}

Step 1: Check 1
  1 in seen? No
  seen = {1}

Step 2: Check 2
  2 in seen? No
  seen = {1, 2}

Step 3: Check 3
  3 in seen? No
  seen = {1, 2, 3}

Step 4: Check 1
  1 in seen? Yes! ← Duplicate found
  Return true ✓
```

**Sorting Approach:**
```
nums = [1, 2, 3, 1]

After sorting: [1, 1, 2, 3]

Compare adjacent:
1 == 1? Yes! ← Duplicate found
Return true ✓
```

### Why Hash Set Works:

**Logic:**
```
Hash Set properties:
- Contains only unique elements
- O(1) average lookup time
- O(1) average insertion time

Strategy:
- Track elements we've seen
- First duplicate triggers immediate return
- If loop completes, all unique
```

### Comparison of Approaches:

| Approach | Time | Space | When to Use |
|----------|------|-------|-------------|
| Hash Set | O(n) | O(n) | Best overall ✓ |
| Sorting | O(n log n) | O(1)* | Space critical |
| Brute Force | O(n²) | O(1) | Never (TLE) |

*Assuming in-place sort; some sorts use O(log n) or O(n) space

### Edge Cases:

1. **All duplicates:** [1,1,1,1] → true
2. **All unique:** [1,2,3,4,5] → false
3. **Single element:** [1] → false
4. **Two elements same:** [1,1] → true
5. **Two elements different:** [1,2] → false
6. **Duplicate at ends:** [5,2,3,4,5] → true
7. **Large numbers:** [-10⁹, 10⁹, -10⁹] → true

### Common Mistakes:

1. **Using list instead of set**
   ```
   Wrong: if num in list (O(n) lookup)
   Right: if num in set (O(1) lookup)
   ```

2. **Modifying input for sorting**
   - Original array changed
   - May not be allowed

3. **Wrong return value**
   ```
   Wrong: Return duplicate element
   Right: Return true/false
   ```

4. **Not handling negatives**
   - Hash set works with negative numbers
   - No special handling needed

### Implementation (Hash Set):

```python
def containsDuplicate(nums):
    seen = set()
    for num in nums:
        if num in seen:
            return True
        seen.add(num)
    return False
```

**One-liner:**
```python
def containsDuplicate(nums):
    return len(nums) != len(set(nums))
```

### Implementation (Sorting):

```python
def containsDuplicate(nums):
    nums.sort()
    for i in range(len(nums) - 1):
        if nums[i] == nums[i + 1]:
            return True
    return False
```

### Java Implementation:

```java
// Hash Set approach
public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
        if (!seen.add(num)) {  // add() returns false if duplicate
            return true;
        }
    }
    return false;
}

// One-liner using Stream
public boolean containsDuplicate(int[] nums) {
    return Arrays.stream(nums).distinct().count() < nums.length;
}
```

### Space Optimization:

**When to use sorting:**
```
If space is limited:
- Sort in-place: O(1) extra space
- Trade: O(n log n) time vs O(n) time
- Good for embedded systems, memory-constrained
```

**When to use hash set:**
```
Standard interviews:
- O(n) time is optimal
- O(n) space is acceptable
- Cleaner, more readable code
```

### Follow-up Variations:

**Contains Duplicate II:**
- Check if duplicates are within k distance
- Use sliding window with hash set

**Contains Duplicate III:**
- Check if duplicates within value range t
- Use TreeSet or bucketing

### Why This Problem Matters:

**Real-world Applications:**
```
1. Database: Check unique constraints
2. Validation: Ensure no duplicate IDs
3. Deduplication: Remove duplicates
4. Caching: Prevent duplicate entries
```

**Interview Perspective:**
```
- Tests basic data structure knowledge
- Hash table understanding
- Time-space tradeoffs
- Common warm-up question
```

### Optimization Tips:

**Early Return:**
```python
# Already optimal - returns on first duplicate
# No need to check all elements
```

**Set Construction:**
```python
# Pythonic but less efficient for early return
def containsDuplicate(nums):
    return len(set(nums)) < len(nums)

# Builds entire set even if duplicate found early
```

### Performance Analysis:

**Best Case:** O(1)
```
First two elements are duplicates
[5, 5, ...]
```

**Worst Case:** O(n)
```
No duplicates or last two are duplicates
[1, 2, 3, 4, 5]
```

**Average Case:** O(n)
```
Duplicate somewhere in middle
```

### Memory Usage:

**Hash Set Space:**
```
Best: O(1) - early duplicate
Worst: O(n) - all unique, store all
Average: O(n/2) ≈ O(n)
```

## Related Problems:
- Contains Duplicate II (LeetCode 219)
- Contains Duplicate III (LeetCode 220)
- Single Number (LeetCode 136)
- Find All Duplicates in an Array (LeetCode 442)
- Intersection of Two Arrays (LeetCode 349)

## Related Articles:
- Hash Table Fundamentals
- Set Data Structure
- Array Deduplication
- Sorting Algorithms
- Time-Space Tradeoffs

## Keywords:
contains duplicate leetcode, find duplicate array, hash set duplicate detection, array duplicates, check duplicate elements, leetcode easy, hash table problems, duplicate finder, array validation

---

**SEO Tags:** #Array #HashTable #HashSet #Duplicate #LeetCode #Easy #Interview #FAANG #DataStructures #SetOperations

**Problem Category:** Array Manipulation, Hash Table, Duplicate Detection

**Difficulty Level:** Easy (Fundamental problem)

**Prerequisites:**
- Hash Set/Hash Table basics
- Array iteration
- Set operations

**Learning Outcomes:**
- Master hash set usage
- Understand time-space tradeoffs
- Learn duplicate detection patterns
- Practice clean code

**Interview Frequency:** Very High (Common warm-up)

**Key Technique:** Use Hash Set for O(n) duplicate detection with O(1) lookup per element

**One-Line Solution:** `len(nums) != len(set(nums))` - though iteration with early return is more efficient
