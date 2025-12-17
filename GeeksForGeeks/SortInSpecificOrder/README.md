# Sort in Specific Order
---

> Video description: https://youtu.be/x630RAfJovM

[Problem](https://www.geeksforgeeks.org/problems/sort-in-specific-order2422/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/x630RAfJovM/0.jpg)](https://youtu.be/x630RAfJovM)

---

**Difficulty:** Medium  
**Accuracy:** 52.55%  
**Submissions:** 47K+  
**Points:** 4  
**Average Time:** 25m

Given an array `arr[]` of positive integers. Your have to sort them so that the first part of the array contains odd numbers sorted in **descending order**, and the rest of the portion contains even numbers sorted in **ascending order**.

## Examples:

**Example 1:**
```
Input: arr[] = [1, 2, 3, 5, 4, 7, 10]
Output: [7, 5, 3, 1, 2, 4, 10]

Explanation: 
Odd numbers: 1, 3, 5, 7
Sorted in descending order: 7, 5, 3, 1

Even numbers: 2, 4, 10
Sorted in ascending order: 2, 4, 10

Final array: [7, 5, 3, 1, 2, 4, 10]
```

**Example 2:**
```
Input: arr[] = [0, 4, 5, 3, 7, 2, 1]
Output: [7, 5, 3, 1, 0, 2, 4]

Explanation:
Odd numbers: 1, 3, 5, 7
Sorted in descending order: 7, 5, 3, 1

Even numbers: 0, 2, 4
Sorted in ascending order: 0, 2, 4

Final array: [7, 5, 3, 1, 0, 2, 4]
```

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ arr[i] ≤ 10⁹

## Expected Complexities:
- **Time Complexity:** O(n log n)
- **Space Complexity:** O(n)

## Company Tags:
Zoho | Microsoft

## Topic Tags:
Arrays | Sorting | Two Pointers | Partition

## Approach:

### Problem Understanding:

**Requirements:**
1. Split array into odd and even numbers
2. Sort odd numbers in **descending** order
3. Sort even numbers in **ascending** order
4. Place odd numbers first, then even numbers

**Visual representation:**
```
Original: [1, 2, 3, 5, 4, 7, 10]

Odd:  [1, 3, 5, 7] → Sort descending → [7, 5, 3, 1]
Even: [2, 4, 10]    → Sort ascending  → [2, 4, 10]

Result: [7, 5, 3, 1, 2, 4, 10]
```

### Approach 1: Separate Arrays (Simple and Clear)

**Algorithm:**
```
odd = []
even = []

// Step 1: Separate odd and even numbers
for num in arr:
    if num % 2 == 0:
        even.append(num)
    else:
        odd.append(num)

// Step 2: Sort both arrays
sort(odd in descending order)
sort(even in ascending order)

// Step 3: Merge back
result = odd + even
return result
```

**Complexity:**
- Time: O(n log n) - sorting dominates
- Space: O(n) - two separate arrays

### Approach 2: In-place with Custom Comparator

**Algorithm:**
```
sort(arr with custom comparator):
    comparator(a, b):
        // Both odd
        if a is odd AND b is odd:
            return b - a  // descending order
        
        // Both even
        if a is even AND b is even:
            return a - b  // ascending order
        
        // One odd, one even
        if a is odd:
            return -1  // odd comes first
        else:
            return 1   // even comes after
```

**Complexity:**
- Time: O(n log n) - sorting
- Space: O(log n) - sorting stack space

### Approach 3: Two-Pass Sort

**Algorithm:**
```
// Step 1: Partition - odds first, evens second
partition array (odds before evens)

// Step 2: Sort odd section (descending)
oddEnd = count of odd numbers
sort(arr[0...oddEnd-1] in descending order)

// Step 3: Sort even section (ascending)
sort(arr[oddEnd...n-1] in ascending order)

return arr
```

**Complexity:**
- Time: O(n log n)
- Space: O(1) - in-place

### Step-by-Step Walkthrough (Example 1):

**Using Approach 1 (Separate Arrays):**
```
Input: arr = [1, 2, 3, 5, 4, 7, 10]

Step 1: Separate odd and even
Process each element:
- 1 is odd → odd = [1]
- 2 is even → even = [2]
- 3 is odd → odd = [1, 3]
- 5 is odd → odd = [1, 3, 5]
- 4 is even → even = [2, 4]
- 7 is odd → odd = [1, 3, 5, 7]
- 10 is even → even = [2, 4, 10]

Result:
odd = [1, 3, 5, 7]
even = [2, 4, 10]

Step 2: Sort
odd (descending): [7, 5, 3, 1]
even (ascending): [2, 4, 10]

Step 3: Concatenate
result = [7, 5, 3, 1] + [2, 4, 10]
       = [7, 5, 3, 1, 2, 4, 10]

Output: [7, 5, 3, 1, 2, 4, 10] ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: arr = [0, 4, 5, 3, 7, 2, 1]

Step 1: Separate
- 0 is even → even = [0]
- 4 is even → even = [0, 4]
- 5 is odd → odd = [5]
- 3 is odd → odd = [5, 3]
- 7 is odd → odd = [5, 3, 7]
- 2 is even → even = [0, 4, 2]
- 1 is odd → odd = [5, 3, 7, 1]

Result:
odd = [5, 3, 7, 1]
even = [0, 4, 2]

Step 2: Sort
odd (descending): [7, 5, 3, 1]
even (ascending): [0, 2, 4]

Step 3: Concatenate
result = [7, 5, 3, 1, 0, 2, 4]

Output: [7, 5, 3, 1, 0, 2, 4] ✓
```

### Detailed Custom Comparator Example:
```
Input: [3, 2, 5, 4, 1]

Using custom comparator:

Compare 3 and 2:
- 3 is odd, 2 is even
- Odd comes first
- Order: 3 before 2

Compare 3 and 5:
- Both odd
- Descending: 5 > 3
- Order: 5 before 3

Compare 2 and 4:
- Both even
- Ascending: 2 < 4
- Order: 2 before 4

After sorting with comparator:
[5, 3, 1, 2, 4]

Verification:
Odd part: [5, 3, 1] (descending) ✓
Even part: [2, 4] (ascending) ✓
```

### Implementation Details:

**Checking odd/even:**
```python
def isOdd(num):
    return num % 2 == 1

def isEven(num):
    return num % 2 == 0
```

**Custom comparator (Python):**
```python
from functools import cmp_to_key

def custom_compare(a, b):
    # Both odd - descending
    if a % 2 == 1 and b % 2 == 1:
        return b - a
    
    # Both even - ascending
    if a % 2 == 0 and b % 2 == 0:
        return a - b
    
    # One odd, one even - odd first
    if a % 2 == 1:
        return -1
    else:
        return 1

arr.sort(key=cmp_to_key(custom_compare))
```

**Separate and merge approach:**
```python
odd = [x for x in arr if x % 2 == 1]
even = [x for x in arr if x % 2 == 0]

odd.sort(reverse=True)  # Descending
even.sort()             # Ascending

result = odd + even
```

### Edge Cases:

1. **All odd numbers:**
   - Input: [1, 3, 5, 7, 9]
   - Output: [9, 7, 5, 3, 1]
   - Just sort descending

2. **All even numbers:**
   - Input: [2, 4, 6, 8]
   - Output: [2, 4, 6, 8]
   - Just sort ascending

3. **Single element (odd):**
   - Input: [5]
   - Output: [5]

4. **Single element (even):**
   - Input: [4]
   - Output: [4]

5. **Alternating pattern:**
   - Input: [1, 2, 3, 4, 5, 6]
   - Output: [5, 3, 1, 2, 4, 6]

6. **Contains zero:**
   - Input: [0, 1, 2, 3]
   - 0 is even
   - Output: [3, 1, 0, 2]

7. **Duplicate values:**
   - Input: [3, 3, 2, 2, 1, 1]
   - Output: [3, 3, 1, 1, 2, 2]

8. **Large numbers:**
   - Input: [10⁹, 10⁹-1, 2, 1]
   - Handle correctly based on parity

### Why This Works:

**Separation ensures correctness:**
```
By separating odd and even:
- Can apply different sorting orders independently
- No interference between the two groups
- Final concatenation maintains order
```

**Custom comparator handles all cases:**
```
Comparator logic:
1. Odd vs Odd → Use descending order
2. Even vs Even → Use ascending order
3. Odd vs Even → Odd always comes first

This ensures correct relative ordering
```

### Common Mistakes:

1. **Wrong sort order:**
```
   Wrong: Sort odd ascending
   Right: Sort odd descending
```

2. **Forgetting to separate:**
   - Can't sort entire array with single rule
   - Need to handle odd and even separately

3. **Wrong concatenation order:**
```
   Wrong: even + odd
   Right: odd + even
```

4. **Off-by-one in partition:**
   - When using in-place approach
   - Must correctly identify boundary

5. **Not handling duplicates:**
   - Duplicate odd/even numbers should maintain order
   - Stable sort preferred

### Performance Comparison:

**Approach 1 (Separate Arrays):**
- Pros: Simple, clear, easy to implement
- Cons: Extra O(n) space
- Best for: Readability, when space isn't critical

**Approach 2 (Custom Comparator):**
- Pros: Clean code, built-in sort
- Cons: Comparator might be slower
- Best for: Language supports custom comparators well

**Approach 3 (Two-Pass):**
- Pros: Can be in-place
- Cons: More complex, two sort operations
- Best for: Space-critical applications

### Complexity Analysis:

**Time Complexity: O(n log n)**
- Separating: O(n)
- Sorting odd array: O(k log k) where k = odd count
- Sorting even array: O((n-k) log (n-k))
- Total: O(n log n) dominated by sorting

**Space Complexity:**
- Approach 1: O(n) - two separate arrays
- Approach 2: O(log n) - sort stack space
- Approach 3: O(1) - in-place (with O(log n) for stack)

### Optimization Notes:

**Can we do better than O(n log n)?**
- No, comparison-based sorting is Ω(n log n)
- Separation is O(n) but sorting dominates
- Optimal for comparison-based approach

**When to use which approach:**
- Small arrays: Approach 1 (simplest)
- Large arrays with space: Approach 1
- Space-critical: Approach 3
- Language with good comparators: Approach 2

## Related Interview Experiences:
- Sort Colors (Dutch National Flag)
- Partition Array
- Segregate Even and Odd Numbers
- Sort Array By Parity
- Custom Sort String
- Relative Sort Array

## Related Articles:
- Custom Sorting Techniques
- Partition Algorithms
- Comparator Functions
- Array Segregation Problems
- Two-Pointer Techniques
