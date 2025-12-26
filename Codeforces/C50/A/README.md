# Domino Piling
---

> Video description: https://youtu.be/exiTixvA5eE

[Problem](https://codeforces.com/contest/50/problem/A) | [Java Solution](./Solution.java) | [Discussion](https://codeforces.com/blog/entry/)

[![img](https://img.youtube.com/vi/exiTixvA5eE/0.jpg)](https://youtu.be/exiTixvA5eE)

---

**Contest:** Codeforces Round 50  
**Difficulty:** 800 (Easy)  
**Problem ID:** 50A  
**Average Time:** 5m

You are given a rectangular board of M × N squares. Also you are given an unlimited number of standard domino pieces of 2 × 1 squares. You are allowed to rotate the pieces. You are asked to place as many dominoes as possible on the board so as to meet the following conditions:

1. Each domino completely covers two squares.
2. No two dominoes overlap.
3. Each domino lies entirely on the board. It is allowed to touch the edges of the board.

Find the maximum number of dominoes, which can be placed under these restrictions.

## Input Format:

In a single line you are given two integers M and N — board sizes in squares (1 ≤ M ≤ 16, 1 ≤ N ≤ 16).

## Output Format:

Output one number — the maximal number of dominoes, which can be placed.

## Examples:

**Example 1:**
```
Input:
2 4

Output:
4

Explanation:
Board: 2 rows × 4 columns = 8 squares total

Domino placement (each domino covers 2 squares):
[==] [==]
[==] [==]

Total dominoes: 4
All 8 squares are covered.
```

**Example 2:**
```
Input:
3 3

Output:
4

Explanation:
Board: 3 rows × 3 columns = 9 squares total

Domino placement:
[==] [=]
[==] [=]
[==] |

Total dominoes: 4
One square remains uncovered (odd total).
```

**Example 3:**
```
Input:
1 1

Output:
0

Explanation:
Board: 1 row × 1 column = 1 square total
Cannot place any domino (need at least 2 squares).
```

**Example 4:**
```
Input:
5 5

Output:
12

Explanation:
Board: 5 rows × 5 columns = 25 squares total
Maximum dominoes: 25 / 2 = 12 (with 1 square remaining)
```

## Constraints:
- 1 ≤ M ≤ 16
- 1 ≤ N ≤ 16

## Expected Complexities:
- **Time Complexity:** O(1)
- **Space Complexity:** O(1)

## Tags:
Math | Greedy | Implementation

## Approach:

### Problem Understanding:

**Key Points:**
1. Board has M × N squares
2. Each domino covers exactly 2 squares
3. Want to place maximum number of dominoes
4. No overlap, no partial coverage allowed

**Intuition:**
- Total squares = M × N
- Each domino uses 2 squares
- Maximum dominoes = Total squares / 2
- If total squares is odd, one square remains uncovered

### Key Observations:

1. **Simple Division:**
   - Each domino covers 2 squares
   - Maximum dominoes = ⌊(M × N) / 2⌋
   - Floor division handles odd total automatically

2. **Mathematical Formula:**
```
   Answer = (M × N) / 2
   
   Using integer division automatically gives floor
```

3. **Why This Works:**
   - We can always achieve this maximum
   - Placement strategy exists for any M × N board
   - Greedy placement: fill row by row or column by column

4. **Edge Cases:**
   - If M × N is odd, one square must remain empty
   - If M = 1 and N = 1, answer is 0
   - Always possible to place ⌊(M × N) / 2⌋ dominoes

### Algorithm:
```
function maxDominoes(M, N):
    totalSquares = M * N
    return totalSquares / 2  // Integer division
```

**That's it! The solution is just one line.**

### Step-by-Step Walkthrough (Example 1):
```
Input: M = 2, N = 4

Calculate:
- Total squares = 2 × 4 = 8
- Maximum dominoes = 8 / 2 = 4

Output: 4

Visual placement:
Row 1: [==] [==]
Row 2: [==] [==]

4 horizontal dominoes cover all 8 squares ✓
```

### Step-by-Step Walkthrough (Example 2):
```
Input: M = 3, N = 3

Calculate:
- Total squares = 3 × 3 = 9
- Maximum dominoes = 9 / 2 = 4 (integer division)

Output: 4

Visual placement (one possible arrangement):
[==] =
[==] =
[==] X

Where:
- [==] represents horizontal domino
- = represents part of vertical domino
- X represents uncovered square

4 dominoes cover 8 squares, 1 square remains ✓
```

### Step-by-Step Walkthrough (Example 4):
```
Input: M = 5, N = 5

Calculate:
- Total squares = 5 × 5 = 25
- Maximum dominoes = 25 / 2 = 12

Output: 12

12 dominoes cover 24 squares, 1 square remains uncovered
```

### Why This Formula Works:

**Proof of Optimality:**

1. **Upper Bound:**
   - Each domino covers 2 squares
   - Total squares = M × N
   - Cannot place more than ⌊(M × N) / 2⌋ dominoes

2. **Achievability:**
   - This maximum is always achievable
   - Simple strategy: place dominoes row by row

**Placement Strategy (proof by construction):**
```
Strategy 1: Row-by-row horizontal placement
- If N is even: Place N/2 dominoes per row
- If N is odd: Place (N-1)/2 dominoes per row, 
  then fill remaining column vertically

Strategy 2: Column-by-column vertical placement
- Similar logic with columns

At least one strategy works for any M × N board.
```

### Visual Examples:

**Example: 4 × 3 board**
```
Total squares: 12
Maximum dominoes: 6

Placement:
[==] [=]
[==] [=]
[==] [=]
[==] [=]

All 12 squares covered with 6 dominoes ✓
```

**Example: 3 × 3 board (odd total)**
```
Total squares: 9
Maximum dominoes: 4

Placement option 1:
[==] =
[==] =
[==] X

Placement option 2:
[===]
[===]
[=] X

Either way, 1 square must remain empty.
```

**Example: 5 × 4 board**
```
Total squares: 20
Maximum dominoes: 10

Placement:
[==][==]
[==][==]
[==][==]
[==][==]
[==][==]

All 20 squares covered with 10 dominoes ✓
```

### Edge Cases:

1. **Minimum board (1 × 1):**
   - Input: M = 1, N = 1
   - Total = 1
   - Output: 0 (cannot place any domino)

2. **Single row:**
   - Input: M = 1, N = 8
   - Total = 8
   - Output: 4 (all horizontal dominoes)

3. **Single column:**
   - Input: M = 8, N = 1
   - Total = 8
   - Output: 4 (all vertical dominoes)

4. **Square board (even):**
   - Input: M = 4, N = 4
   - Total = 16
   - Output: 8

5. **Square board (odd):**
   - Input: M = 5, N = 5
   - Total = 25
   - Output: 12 (one square remains)

6. **Maximum constraints:**
   - Input: M = 16, N = 16
   - Total = 256
   - Output: 128

### Common Mistakes:

1. **Trying complex placement logic:**
   - Don't need to simulate actual placement
   - Simple division gives answer

2. **Forgetting integer division:**
```
   Wrong: (M * N) / 2.0  (floating point)
   Right: (M * N) / 2    (integer division)
```

3. **Overthinking edge cases:**
   - Formula works for all valid inputs
   - No special handling needed

4. **Worrying about placement existence:**
   - For any M × N, ⌊(M × N) / 2⌋ is always achievable
   - Don't need to prove placement exists

### Implementation:

**C++:**
```cpp
#include <iostream>
using namespace std;

int main() {
    int M, N;
    cin >> M >> N;
    cout << (M * N) / 2 << endl;
    return 0;
}
```

**Python:**
```python
M, N = map(int, input().split())
print((M * N) // 2)
```

**Java:**
```java
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        System.out.println((M * N) / 2);
    }
}
```

**JavaScript:**
```javascript
const readline = require('readline');
const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.on('line', (line) => {
    const [M, N] = line.split(' ').map(Number);
    console.log(Math.floor((M * N) / 2));
    rl.close();
});
```

### Complexity Analysis:

**Time Complexity: O(1)**
- Single multiplication
- Single division
- Constant time regardless of M and N values

**Space Complexity: O(1)**
- Only storing M, N, and result
- No data structures needed

**Why This is Optimal:**
- Cannot do better than O(1)
- Problem only requires mathematical calculation
- No simulation or placement computation needed

### Mathematical Insights:

**Even Total Squares:**
```
If M × N is even:
- All squares can be covered
- No square remains empty
- Perfect tiling exists
```

**Odd Total Squares:**
```
If M × N is odd:
- One square must remain empty
- Cannot tile odd number of squares with 2-square dominoes
- Maximum = (M × N - 1) / 2 = ⌊(M × N) / 2⌋
```

**Parity Analysis:**
```
M × N is even when:
- M is even (any N), OR
- N is even (any M)

M × N is odd when:
- Both M and N are odd
```

### Why Placement Always Exists:

**Constructive Proof:**
```
For any M × N board:

If N is even:
- Place horizontal dominoes row by row
- Each row gets N/2 dominoes
- Total: M × (N/2) = (M × N) / 2 ✓

If N is odd and M is even:
- Place (N-1)/2 horizontal dominoes per row
- Fill last column with M/2 vertical dominoes
- Total: M × (N-1)/2 + M/2 = (M × N) / 2 ✓

If both M and N are odd:
- Place (N-1)/2 horizontal dominoes per row
- Fill last column with (M-1)/2 vertical dominoes
- 1 square at bottom-right remains empty
- Total: M × (N-1)/2 + (M-1)/2 = (M × N - 1) / 2 ✓

All cases achieve the maximum!
```

## Related Problems:
- Tiling a Rectangle with the Fewest Squares
- Domino and Tromino Tiling (LeetCode 790)
- Minimum Tiling
- Rectangle Packing

## Related Articles:
- Mathematical Problem Solving
- Greedy Algorithms
- Tiling Problems
- Combinatorics Basics