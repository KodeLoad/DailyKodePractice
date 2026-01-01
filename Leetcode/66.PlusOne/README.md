# Plus One
---

> Video description: https://youtu.be/ImYlb4IQoyA

[Problem](https://leetcode.com/problems/plus-one/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://leetcode.com/problems/plus-one/discuss/)

[![img](https://img.youtube.com/vi/ImYlb4IQoyA/0.jpg)](https://youtu.be/ImYlb4IQoyA)

---

**Difficulty:** Easy  
**Acceptance Rate:** 45.2%  
**Submissions:** 3.2M+  
**Accepted:** 1.4M+  
**Topics:** Array | Math  
**Average Time:** 15m

You are given a **large integer** represented as an integer array `digits`, where each `digits[i]` is the i<sup>th</sup> digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading `0`'s.

Increment the large integer by one and return the resulting array of digits.

## Examples:

**Example 1:**
```
Input: digits = [1,2,3]
Output: [1,2,4]

Explanation: 
The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
```

**Example 2:**
```
Input: digits = [4,3,2,1]
Output: [4,3,2,2]

Explanation: 
The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
```

**Example 3:**
```
Input: digits = [9]
Output: [1,0]

Explanation: 
The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
```

**Example 4:**
```
Input: digits = [9,9,9]
Output: [1,0,0,0]

Explanation:
The array represents the integer 999.
Incrementing by one gives 999 + 1 = 1000.
Thus, the result should be [1,0,0,0].
```

## Constraints:
- 1 ≤ digits.length ≤ 100
- 0 ≤ digits[i] ≤ 9
- digits does not contain any leading 0's

## Expected Complexities:
- **Time Complexity:** O(n)
- **Space Complexity:** O(1) or O(n) depending on output consideration

## Company Tags:
Google | Amazon | Microsoft | Facebook | Apple | Adobe | Bloomberg

## Topic Tags:
Array | Math | Big Integer | Carry Logic

## Approach:

### Problem Understanding:

**Key Points:**
1. Number represented as array of digits
2. Most significant digit is at index 0
3. Need to add 1 to the number
4. Handle carry propagation
5. May need extra digit for overflow (999 → 1000)

**Challenge:**
- Cannot convert to integer (number may be very large)
- Must handle carry logic manually
- Edge case: all 9's require new array

### Key Observations:

1. **Simple Case (No Carry):**
   - If last digit < 9, just increment and return
   - Example: [1,2,3] → [1,2,4]

2. **Carry Propagation:**
   - Start from rightmost digit
   - If digit is 9, it becomes 0 and carry to next
   - Example: [1,2,9] → [1,3,0]

3. **All 9's Case:**
   - Need new array with extra digit
   - Example: [9,9,9] → [1,0,0,0]

4. **Optimization:**
   - Can process from right to left
   - Stop as soon as no carry needed

### Algorithm:
```
function plusOne(digits):
    n = digits.length
    
    // Traverse from right to left
    for i from n-1 to 0:
        // If digit is less than 9, just increment and return
        if digits[i] < 9:
            digits[i]++
            return digits
        
        // If digit is 9, set to 0 and continue (carry)
        digits[i] = 0
    
    // If we reach here, all digits were 9
    // Need new array with extra digit
    result = new array of size n+1
    result[0] = 1
    // Rest are already 0 by default
    
    return result
```

### Step-by-Step Walkthrough (Example 1):
```
Input: digits = [1, 2, 3]

Start from rightmost:
i = 2 (index of 3)

Check digits[2] = 3:
- 3 < 9? Yes!
- Increment: 3 + 1 = 4
- digits[2] = 4
- Return [1, 2, 4]

Output: [1, 2, 4] ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: digits = [4, 3, 2, 1]

Start from rightmost:
i = 3 (index of 1)

Check digits[3] = 1:
- 1 < 9? Yes!
- Increment: 1 + 1 = 2
- digits[3] = 2
- Return [4, 3, 2, 2]

Output: [4, 3, 2, 2] ✓
```

### Step-by-Step Walkthrough (Example 3):
```
Input: digits = [9]

Start from rightmost:
i = 0 (index of 9)

Check digits[0] = 9:
- 9 < 9? No
- Set digits[0] = 0
- digits = [0]
- Loop ends (i reaches 0)

All digits were 9, need overflow:
- Create new array of size 2
- result = [1, 0]

Output: [1, 0] ✓
```

### Step-by-Step Walkthrough (Example 4):
```
Input: digits = [9, 9, 9]

Iteration 1 (i = 2):
- digits[2] = 9
- 9 < 9? No
- Set digits[2] = 0
- digits = [9, 9, 0]
- Continue

Iteration 2 (i = 1):
- digits[1] = 9
- 9 < 9? No
- Set digits[1] = 0
- digits = [9, 0, 0]
- Continue

Iteration 3 (i = 0):
- digits[0] = 9
- 9 < 9? No
- Set digits[0] = 0
- digits = [0, 0, 0]
- Loop ends

All digits were 9:
- Create new array of size 4
- result = [1, 0, 0, 0]

Output: [1, 0, 0, 0] ✓
```

### Visual Understanding:

**Case 1: No Carry Needed**
```
[1, 2, 3] + 1
         ↑
    increment this
        ↓
[1, 2, 4]
Done!
```

**Case 2: Single Carry**
```
[1, 2, 9] + 1
         ↑
    9 becomes 0, carry 1
        ↓
[1, 3, 0]
    ↑
   carry propagates
```

**Case 3: Multiple Carries**
```
[1, 9, 9] + 1
         ↑
    9 becomes 0, carry
        ↓
[1, 9, 0]
    ↑
    9 becomes 0, carry
        ↓
[2, 0, 0]
 ↑
 carry absorbed
```

**Case 4: Overflow (All 9's)**
```
[9, 9, 9] + 1
All become 0, need new digit
        ↓
[1, 0, 0, 0]
 ↑
 new digit added
```

### Why This Works:

**Carry Logic:**
```
Adding 1 to rightmost digit:
- If < 9: increment and done
- If = 9: becomes 0, carry to next digit

This is exactly how addition works in decimal:
  999
+   1
-----
 1000

Each 9 becomes 0 with carry propagating left
```

**Mathematical Proof:**
```
For number represented as [d₀, d₁, ..., dₙ₋₁]:
Value = d₀×10^(n-1) + d₁×10^(n-2) + ... + dₙ₋₁×10^0

Adding 1:
- If dₙ₋₁ < 9: simply increment dₙ₋₁
- If dₙ₋₁ = 9: dₙ₋₁ becomes 0, add 1 to dₙ₋₂
- Continue until no carry or reach beginning
```

### Edge Cases:

1. **Single digit (not 9):**
   - Input: [5]
   - Output: [6]

2. **Single digit (9):**
   - Input: [9]
   - Output: [1, 0]

3. **No 9's:**
   - Input: [1, 2, 3, 4, 5]
   - Output: [1, 2, 3, 4, 6]

4. **All 9's:**
   - Input: [9, 9, 9, 9]
   - Output: [1, 0, 0, 0, 0]

5. **9's in middle:**
   - Input: [1, 9, 9, 8]
   - Output: [1, 9, 9, 9]

6. **Trailing 9's:**
   - Input: [1, 2, 9, 9]
   - Output: [1, 3, 0, 0]

7. **Maximum length:**
   - Input: 100 digits
   - Must handle efficiently

### Common Mistakes:

1. **Converting to integer:**
```
   Wrong: int num = arrayToInt(digits); // Overflow!
   Right: Process digits directly
```

2. **Not handling overflow:**
```
   Wrong: Forget to create new array for all 9's
   Right: Check if loop completes without return
```

3. **Wrong array size:**
```
   Wrong: new int[n] for overflow
   Right: new int[n + 1] for overflow
```

4. **Not initializing new array:**
```
   Wrong: Forget to set result[0] = 1
   Right: result[0] = 1, rest are 0 by default
```

5. **Modifying input unnecessarily:**
   - If all 9's, don't need to modify original
   - Just create new array

### Implementation Details:

**Optimal Solution:**
```python
def plusOne(digits):
    n = len(digits)
    
    # Traverse from right to left
    for i in range(n - 1, -1, -1):
        # If current digit is less than 9
        if digits[i] < 9:
            digits[i] += 1
            return digits
        
        # Current digit is 9, set to 0
        digits[i] = 0
    
    # If we're here, all digits were 9
    # Need new array with leading 1
    return [1] + digits
```

**Alternative (More Explicit):**
```python
def plusOne(digits):
    carry = 1
    
    for i in range(len(digits) - 1, -1, -1):
        total = digits[i] + carry
        digits[i] = total % 10
        carry = total // 10
        
        if carry == 0:
            break
    
    if carry == 1:
        return [1] + digits
    
    return digits
```

**Java Implementation:**
```java
public int[] plusOne(int[] digits) {
    int n = digits.length;
    
    for (int i = n - 1; i >= 0; i--) {
        if (digits[i] < 9) {
            digits[i]++;
            return digits;
        }
        digits[i] = 0;
    }
    
    // All digits were 9
    int[] result = new int[n + 1];
    result[0] = 1;
    return result;
}
```

### Complexity Analysis:

**Time Complexity: O(n)**
- Worst case: traverse all digits (all 9's)
- Best case: O(1) if last digit < 9
- Average case: O(1) typically

**Space Complexity:**
- **Input space:** O(1) - modify in place
- **Output space:** O(n) or O(n+1) for result
- **Auxiliary space:** O(1) - only constant variables

**Why This is Optimal:**
- Must examine at least last digit: Ω(1)
- Worst case must check all: Ω(n)
- Our solution: O(n)
- Cannot do better

### Optimization Notes:

**Early Termination:**
```
Most numbers don't have trailing 9's
Average case: O(1) time
Only rare cases (like 999) need O(n)
```

**Memory Efficiency:**
```
Modify input array when possible
Only allocate new array when overflow needed
```

### Related Variations:

**Plus K (instead of 1):**
```python
def plusK(digits, k):
    carry = k
    for i in range(len(digits) - 1, -1, -1):
        total = digits[i] + carry
        digits[i] = total % 10
        carry = total // 10
    
    if carry > 0:
        return list(str(carry)) + digits
    return digits
```

**Minus One:**
```python
def minusOne(digits):
    # Similar logic but with borrow instead of carry
    # Handle leading zeros after subtraction
```

## Related Interview Experiences:
- Add Two Numbers (LeetCode 2)
- Multiply Strings (LeetCode 43)
- Add Binary (LeetCode 67)
- Add to Array-Form of Integer (LeetCode 989)
- Plus One Linked List (LeetCode 369)

## Related Articles:
- Big Integer Arithmetic
- Carry Propagation in Addition
- Array Manipulation Techniques
- In-place Array Modification
- Overflow Handling in Arrays

## Keywords:
plus one leetcode, add one to array, big integer addition, carry propagation, array manipulation, increment large number, digit array, leetcode easy, array algorithms, mathematical operations on arrays

---

**SEO Tags:** #Array #Math #BigInteger #CarryLogic #LeetCode #EasyProblem #CodingInterview #FAANG #DataStructures #Algorithms #ArrayManipulation

**Problem Category:** Array Manipulation, Mathematical Operations, Big Integer Arithmetic

**Difficulty Level:** Easy (Good for Beginners)

**Prerequisites:**
- Basic Array Operations
- Understanding of Decimal Addition
- Carry Logic in Arithmetic

**Learning Outcomes:**
- Master array manipulation techniques
- Understand carry propagation
- Handle edge cases in array problems
- Practice in-place modifications
- Learn big integer operations

**Interview Frequency:** Very High (Common warm-up question)

**Real-world Applications:**
- Financial calculations with large numbers
- Cryptography (large number operations)
- Scientific computing
- Database systems handling large integers
