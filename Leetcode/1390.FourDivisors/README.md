# Four Divisors
---

> Video description: https://youtu.be/ahFu2fJtyd8

[Problem](https://leetcode.com/problems/four-divisors/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://leetcode.com/problems/four-divisors/discuss/)

[![img](https://img.youtube.com/vi/ahFu2fJtyd8/0.jpg)](https://youtu.be/ahFu2fJtyd8)

---

**Difficulty:** Medium  
**Acceptance Rate:** 42.8%  
**Submissions:** 89K+  
**Accepted:** 38K+  
**Topics:** Array | Math  
**Average Time:** 25m

Given an integer array `nums`, return the sum of divisors of the integers in that array that have **exactly four divisors**. If there is no such integer in the array, return `0`.

## Examples:

**Example 1:**
```
Input: nums = [21,4,7]
Output: 32

Explanation: 
21 has 4 divisors: 1, 3, 7, 21 → sum = 32
4 has 3 divisors: 1, 2, 4 → doesn't count
7 has 2 divisors: 1, 7 → doesn't count
Answer: 32
```

**Example 2:**
```
Input: nums = [21,21]
Output: 64

Explanation:
Each 21 has 4 divisors: 1, 3, 7, 21 → sum = 32
Total: 32 + 32 = 64
```

**Example 3:**
```
Input: nums = [1,2,3,4,5]
Output: 0

Explanation:
1 → divisors: 1 (count = 1)
2 → divisors: 1, 2 (count = 2)
3 → divisors: 1, 3 (count = 2)
4 → divisors: 1, 2, 4 (count = 3)
5 → divisors: 1, 5 (count = 2)
No number has exactly 4 divisors.
```

## Constraints:
- 1 ≤ nums.length ≤ 10⁴
- 1 ≤ nums[i] ≤ 10⁵

## Expected Complexities:
- **Time Complexity:** O(n × √m) where m is max element
- **Space Complexity:** O(1)

## Company Tags:
Google | Amazon | Adobe

## Topic Tags:
Array | Math | Number Theory | Divisors

## Approach:

### Problem Understanding:

**When does a number have exactly 4 divisors?**

1. **Prime cubed:** p³ = p × p × p
   - Divisors: 1, p, p², p³
   - Example: 8 = 2³ → {1, 2, 4, 8}

2. **Product of two distinct primes:** p × q
   - Divisors: 1, p, q, p×q
   - Example: 21 = 3 × 7 → {1, 3, 7, 21}

### Key Observations:

1. **Find divisors up to √n only**
   - Divisors come in pairs
   - If d divides n, so does n/d

2. **Count while finding**
   - Stop early if count > 4
   - Track sum simultaneously

3. **Edge cases:**
   - Perfect squares need special handling
   - 1 has only 1 divisor

### Optimal Algorithm:
```
function sumFourDivisors(nums):
    totalSum = 0
    
    for num in nums:
        divisorSum = getDivisorSum(num)
        if divisorSum != 0:
            totalSum += divisorSum
    
    return totalSum

function getDivisorSum(num):
    count = 0
    sum = 0
    
    for i from 1 to √num:
        if num % i == 0:
            count++
            sum += i
            
            if i != num/i:  // Not perfect square
                count++
                sum += num/i
            
            if count > 4:
                return 0  // Early exit
    
    return sum if count == 4 else 0
```

**Complexity:**
- Time: O(n × √m)
- Space: O(1)

### Visual Understanding:

**Finding divisors of 21:**
```
Check i = 1 to √21 ≈ 4.58

i = 1: 21 % 1 = 0
  → Add 1 and 21/1 = 21
  → count = 2, sum = 1 + 21 = 22

i = 2: 21 % 2 ≠ 0 → skip

i = 3: 21 % 3 = 0
  → Add 3 and 21/3 = 7
  → count = 4, sum = 22 + 3 + 7 = 32

i = 4: 21 % 4 ≠ 0 → skip

count == 4 → return 32 ✓
```

**Perfect square example (16):**
```
i = 1: divisors {1, 16}, count = 2
i = 2: divisors {2, 8}, count = 4
i = 4: divisor {4} only (4 = 16/4)
  → Don't count twice!
  → count = 5 (not 4)
```

### When Numbers Have 4 Divisors:

**Type 1: p³ (prime cubed)**
```
8 = 2³ → {1, 2, 4, 8}
27 = 3³ → {1, 3, 9, 27}
125 = 5³ → {1, 5, 25, 125}
```

**Type 2: p × q (two distinct primes)**
```
6 = 2×3 → {1, 2, 3, 6}
10 = 2×5 → {1, 2, 5, 10}
15 = 3×5 → {1, 3, 5, 15}
21 = 3×7 → {1, 3, 7, 21}
```

### Edge Cases:

1. **Number 1:** 1 divisor → skip
2. **Primes:** 2 divisors → skip
3. **Perfect squares:** Handle carefully
4. **Large numbers:** Optimize with √n
5. **Duplicates in array:** Count each occurrence

### Common Mistakes:

1. **Counting divisors twice**
```
   Wrong: Always count i and num/i
   Right: Check if i == num/i (perfect square)
```

2. **Not optimizing to √n**
```
   Wrong: Check all i from 1 to n
   Right: Check i from 1 to √n
```

3. **Wrong perfect square check**
```
   Wrong: i * i == num
   Right: i == num/i (handles precision)
```

4. **Not returning 0 early**
   - Continue checking even after count > 4
   - Wastes computation

### Optimization Techniques:

**Early Termination:**
```python
if count > 4:
    return 0  # No need to continue
```

**Efficient Square Root:**
```python
limit = int(num ** 0.5)
for i in range(1, limit + 1):
    # Check divisors
```

**Handle Perfect Squares:**
```python
if i * i == num:
    count += 1
    sum += i
else:
    count += 2
    sum += i + num // i
```

### Mathematical Properties:

**Divisor Function:**
```
Number of divisors = (a₁+1)(a₂+1)...(aₖ+1)
where n = p₁^a₁ × p₂^a₂ × ... × pₖ^aₖ

For exactly 4 divisors:
- (a₁+1) = 4 → n = p³
- (a₁+1)(a₂+1) = 2×2 → n = p×q
```

**Sum of Divisors:**
```
For n = p×q: sum = 1 + p + q + pq
For n = p³: sum = 1 + p + p² + p³
```

### Quick Implementation:
```python
def sumFourDivisors(nums):
    def getDivisorSum(n):
        divisors = set()
        for i in range(1, int(n**0.5) + 1):
            if n % i == 0:
                divisors.add(i)
                divisors.add(n // i)
        return sum(divisors) if len(divisors) == 4 else 0
    
    return sum(getDivisorSum(num) for num in nums)
```

### Performance Tips:

**Avoid Repeated Calculations:**
```python
# Cache results for duplicate numbers
cache = {}
for num in nums:
    if num not in cache:
        cache[num] = getDivisorSum(num)
    total += cache[num]
```

## Related Problems:
- Count Primes (LeetCode 204)
- Perfect Squares (LeetCode 279)
- Ugly Number (LeetCode 263)
- Prime Factorization
- Divisor Game (LeetCode 1025)

## Related Articles:
- Number Theory Basics
- Divisor Finding Algorithms
- Prime Factorization
- Mathematical Problem Solving
- Optimization Techniques

## Keywords:
four divisors leetcode, count divisors, divisor sum, number theory, prime factorization, mathematical algorithms, divisor finding, leetcode medium, competitive programming

---

**SEO Tags:** #Math #NumberTheory #Divisors #Array #Optimization #LeetCode #Medium #PrimeNumbers #DivisorSum #Algorithm

**Problem Category:** Mathematical, Number Theory, Divisor Problems

**Difficulty Level:** Medium (Requires optimization)

**Prerequisites:**
- Basic Number Theory
- Square Root Optimization
- Prime Numbers Understanding

**Learning Outcomes:**
- Efficient divisor finding
- Mathematical optimization
- Early termination techniques
- Number theory application

**Interview Frequency:** Medium (Tests mathematical thinking)

**Key Technique:** Find divisors in O(√n) time with early termination
