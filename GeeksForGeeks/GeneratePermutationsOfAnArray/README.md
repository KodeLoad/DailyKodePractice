# Generate Permutations of an Array
---

> Video Solution: [https://youtu.be/YnKaRgGDd0g](https://youtu.be/YnKaRgGDd0g)

[Problem](https://www.geeksforgeeks.org/problems/generate-permutations-of-an-array/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/YnKaRgGDd0g/0.jpg)](https://youtu.be/YnKaRgGDd0g)

---

**Difficulty:** Medium  
**Accuracy:** 54.32%  
**Submissions:** 120K+  
**Points:** 4  
**Average Time:** 35m

Given an array `arr[]` of distinct integers, return all the possible permutations of the array in any order.

## Examples:

**Example 1:**
<code>
Input: arr[] = [1, 2, 3]
Output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

Explanation: There are 3! = 6 possible permutations for an array of size 3. [00:01:15]
</code>

**Example 2:**
<code>
Input: arr[] = [0, 1]
Output: [[0, 1], [1, 0]]
</code>

## Constraints:
- 1 ≤ arr.size() ≤ 9
- -10 ≤ arr[i] ≤ 10
- All the integers of `arr` are unique.

## Expected Complexities:
- **Time Complexity:** O(N! * N)
- **Space Complexity:** O(N! * N)

## Topic Tags:
Recursion | Backtracking | Algorithms

---

## Approach: Recursive Backtracking (Swapping Method)

### 1. Intuition [00:02:45]
A permutation is an arrangement of all elements of a set. For an array of size $N$, we have $N$ choices for the first position, $N-1$ for the second, and so on, leading to $N!$ total permutations. 

To generate these, we can use a recursive approach where we fix one element at a specific position and recursively generate permutations for the remaining elements.

### 2. The Swapping Logic [00:04:12]
Instead of using extra space (like a "visited" array), we can perform permutations **in-place** by swapping elements.
- **Base Case:** If the current index equals the array size, we've found a valid permutation. Add a copy of the current array to the result list. [00:05:50]
- **Recursive Step:** Iterate from the current `index` to the end of the array. Swap the element at `index` with the element at `i`, recurse for `index + 1`, and then **swap back** (backtrack) to restore the array for the next iteration. [00:07:30]

---

## Implementation (Conceptual):
<code>
def generatePermutations(arr):
    res = []
    
    def backtrack(index):
        if index == len(arr):
            res.append(list(arr))
            return
            
        for i in range(index, len(arr)):
            # Swap to fix element at 'index'
            arr[index], arr[i] = arr[i], arr[index]
            
            backtrack(index + 1)
            
            # Swap back (Backtrack)
            arr[index], arr[i] = arr[i], arr[index]

    backtrack(0)
    return res
</code>

---

## Key Takeaways:
- **In-place Optimization:** Swapping elements directly in the input array avoids the $O(N)$ space overhead of a boolean "used" array. [00:08:45]
- **Factorial Growth:** Time complexity is $O(N!)$ because there are $N!$ leaves in the recursion tree, and at each leaf, we do $O(N)$ work to copy the list. [00:09:15]
- **Backtracking Essence:** The "swap back" step is critical; it ensures that the array returns to its original state before the next branch of recursion starts. [00:07:55]

## Related Problems:
- [Permutations II (With Duplicates)](https://leetcode.com/problems/permutations-ii/)
- [Next Permutation](https://www.geeksforgeeks.org/problems/next-permutation5226/1)
- [String Permutations](https://www.geeksforgeeks.org/problems/permutations-of-a-given-string2041/1)

## Keywords:
generate permutations, backtracking recursion, in-place swap permutations, array permutations java, recursion tree, dsa interview questions, factorial time complexity.

---

**SEO Tags:** #Backtracking #Recursion #Permutations #Algorithms #DSA #GeeksforGeeks #CodingInterview #Java #Python

**Learning Outcomes:**
- Visualizing recursion trees for combinatorial problems.
- Implementing the swap-based backtracking pattern.
- Understanding the difference between permutations and combinations.