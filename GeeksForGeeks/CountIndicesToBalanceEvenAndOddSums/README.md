# Count Indices to Balance Even and Odd Sums
---

> Video description: https://youtu.be/ugdp5U8oCCI

[Problem](https://www.geeksforgeeks.org/problems/count-indices-to-balance-even-and-odd-sums/1) | [Java Solution](./Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/ugdp5U8oCCI/0.jpg)](https://youtu.be/ugdp5U8oCCI)

---

Count Indices to Balance Even and Odd Sums

> Difficulty: MediumAccuracy: 68.3%Submissions: 1K+Points: 4

Given an array arr[], count the number of indices such that deleting the element at that index and shifting all elements after it one position left results in an array where the sum of elements at even indices equals the sum at odd indices.

Examples:

    Input: arr[] = [2, 1, 6, 4]
    Output: 1

> Explaination: After removing arr[1], the resulting array will be [2, 6, 4] the sums of elements at odd index is arr[1] = 6 and the sum of elements at even index is arr[0] + arr[2] = 6.

    Input: arr[] = [1, 1, 1]
    Output: 3

> Explaination: Removing any element makes the sum of odd and even indexed elements equal.

Constraints:
    1 ≤ arr.size() ≤ 10**5
    0 ≤ arr[i] ≤ 10**4

Expected Complexities
Topic Tags
Related Articles
