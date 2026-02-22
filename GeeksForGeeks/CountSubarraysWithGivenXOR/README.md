# Count Subarrays with Given XOR
---

> Video Solution: [https://youtu.be/P_T9c8xIIZg](https://youtu.be/P_T9c8xIIZg)

[Problem](https://www.geeksforgeeks.org/problems/count-subarray-with-given-xor/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/P_T9c8xIIZg/0.jpg)](https://youtu.be/P_T9c8xIIZg)

---

**Difficulty:** Medium  
**Accuracy:** 49.62%  
**Submissions:** 120K+  
**Points:** 4  
**Average Time:** 35m

Given an array of integers `arr[]` and a number `k`, count the number of subarrays having XOR of their elements as `k`.

## Examples:

**Example 1:**
<code>
Input: arr[] = [4, 2, 2, 6, 4], k = 6
Output: 4
Explanation: The subarrays having XOR of their elements as 6 are [4, 2], [4, 2, 2, 6, 4], [2, 2, 6], and [6]. [00:01:45]
</code>

**Example 2:**
<code>
Input: arr[] = [5, 6, 7, 8, 9], k = 5
Output: 2
</code>

## Constraints:
- 1 ≤ arr.size() ≤ 10⁵
- 0 ≤ arr[i] ≤ 10⁶
- 0 ≤ k ≤ 10⁶

## Expected Complexities:
- **Time Complexity:** O(N)
- **Space Complexity:** O(N)

## Topic Tags:
Array | Prefix Sum | Hash | Bit Manipulation

---

## Approach: Prefix XOR + Hash Map

### 1. The Core Idea [00:02:08]
This problem is a variation of the "Subarray Sum Equals K" problem, but instead of sums, we use the properties of XOR. 
- **Property 1:** $A \oplus A = 0$
- **Property 2:** $A \oplus 0 = A$
- **Derived Property:** If the XOR sum from index $0$ to $i$ is $XR$, and the XOR sum from $0$ to some index $j$ (where $j < i$) is $Y$, then the XOR sum of the subarray from $j+1$ to $i$ is $XR \oplus Y$. [00:04:43]

### 2. Finding the Target [00:05:34]
We want the subarray XOR to be $k$.
So, $XR \oplus Y = k$.
By rearranging the XOR properties, we get $Y = XR \oplus k$.
This means at any current index with a cumulative XOR of $XR$, we need to check how many times $XR \oplus k$ has occurred previously in our prefix XOR history. [00:06:52]

### 3. Implementation Steps: [00:08:35]
1. Initialize a `HashMap` to store the frequency of prefix XORs.
2. Put `(0, 1)` in the map initially to handle cases where the current prefix XOR itself equals `k`. [00:10:17]
3. Iterate through the array, calculating the cumulative `currentXR`. [00:09:56]
4. Calculate `target = currentXR ^ k`. [00:10:52]
5. Add the frequency of `target` from the map to our total `count`. [00:11:07]
6. Update the frequency of `currentXR` in the map. [00:11:42]

---

## Implementation (Conceptual): [00:09:22]
<code>
public long subarrayXor(int arr[], int n, int k) {
    long count = 0;
    int currentXR = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    
    // Base case: XOR sum of 0 has appeared once
    map.put(0, 1);
    
    for (int num : arr) {
        currentXR ^= num;
        int target = currentXR ^ k;
        
        if (map.containsKey(target)) {
            count += map.get(target);
        }
        
        map.put(currentXR, map.getOrDefault(currentXR, 0) + 1);
    }
    return count;
}
</code>

---

## Key Takeaways:
- **Prefix XOR Technique:** Similar to prefix sum, this allows us to compute any subarray property in $O(1)$ after $O(N)$ preprocessing. [00:12:28]
- **Hash Map for Frequency:** Enables $O(1)$ lookups for previous XOR states. [00:09:09]
- **Bit Manipulation:** Using XOR properties effectively eliminates the need for $O(N^2)$ nested loops. [00:01:32]

## Related Problems:
- [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)
- [Subarray Sums Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)
- [Longest Subarray with sum K](https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1)

## Keywords:
count subarray with given xor, prefix xor hashmap, bit manipulation subarrays, gfg potd solution, java xor subarray, coding interview questions, subarray xor properties.

---

**SEO Tags:** #XOR #PrefixSum #BitManipulation #Algorithms #DSA #GeeksforGeeks #CodingInterview #Java #OBrutus #ProblemSolving

**Learning Outcomes:**
- Understanding XOR properties for subarray problems.
- Leveraging HashMaps for linear time search in combinatorial problems.
- Applying the prefix sum pattern to non-additive operations.
