# Longest alternating subsequence

---

Link to problem: [GFG](https://www.geeksforgeeks.org/problems/longest-alternating-subsequence5951/1)
Solution: [Java](./Solution.java)
[![img](https://img.youtube.com/vi/dERu3JMbyws/0.jpg)](https://www.youtube.com/watch?v=dERu3JMbyws)

---

You are given an array arr. Your task is to find the longest length of a good sequence. A good sequence {x1, x2, .. xn} is an alternating sequence if its elements satisfy one of the following relations :

1.  x1 < x2 > x3 < x4 > x5. . . . . and so on
2.  x1 >x2 < x3 > x4 < x5. . . . . and so on

```
Examples:

Input: arr= [1, 5, 4]
Output: 3
Explanation: The entire sequenece is a good sequence.

Input: arr= [1, 17, 5, 10, 13, 15, 10, 5, 16, 8]
Output: 7
Explanation: There are several subsequences that achieve this length. 
One maximum length good subsequences is [1, 17, 10, 13, 10, 16, 8].
```

Expected Time Complexity: O(n)
Expected Space Complexity: O(1)

Constraints:
1 <= nums.size() <= 105 
1 <= nums[i] <= 104 
