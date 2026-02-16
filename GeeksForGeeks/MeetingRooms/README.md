# 📅 Attend All Meetings (Meeting Rooms I) - GFG POTD Solution

Digital documentation and solution guide for the "Attend All Meetings" problem on GeeksforGeeks. This repository contains the intuition, optimized approach, and code implementation.

---

## 🔗 Quick Links
- **Problem Link:** [GeeksforGeeks - Attend All Meetings](https://www.geeksforgeeks.org/problems/attend-all-meetings/1)
- **Video Explanation:** [Watch Step-by-Step Tutorial](https://youtu.be/1u6t7rJSV0Q)

---

## 📝 Problem Description
You are given a 2D array `arr[][]` where:
* `arr[i][0]` = Starting time of the $i^{th}$ meeting.
* `arr[i][1]` = Ending time of the $i^{th}$ meeting.

**Goal:** Determine if a person can attend all meetings.
**Condition:** A person can attend a meeting if its start time is **greater than or equal to** the previous meeting's end time (no overlapping).

### **Example Case**
**Input:** `arr = [[1, 4], [10, 15], [7, 10]]`  
**Output:** `true`  
**Reasoning:** Sorting the meetings gives `[1, 4]`, `[7, 10]`, and `[10, 15]`. Since $4 \le 7$ and $10 \le 10$, all meetings can be attended.

---

## 💡 Solution Intuition (Greedy & Sorting)
To identify overlaps efficiently, we must process the meetings in chronological order.

1.  **Sort the Intervals:** Sort the meetings based on their **start times**. This ensures that we only need to compare each meeting with the one immediately following it.
2.  **Linear Check:** Iterate through the sorted array. If the `end time` of meeting $i$ is strictly greater than the `start time` of meeting $i+1$, a conflict exists.
3.  **Result:** If no conflicts are found after checking all pairs, return `true`.

---

## 💻 Code Implementation (Java)

```java
import java.util.Arrays;

class Solution {
    public boolean canAttend(int[][] arr) {
        // Step 1: Sort by start time - O(N log N)
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Step 2: Check for overlapping intervals - O(N)
        for (int i = 0; i < arr.length - 1; i++) {
            // If current meeting's end time > next meeting's start time
            if (arr[i][1] > arr[i + 1][0]) {
                return false;
            }
        }
        
        return true;
    }
}
```


📊 Complexity AnalysisTime Complexity: $O(N \log N)$Sorting the $N$ meetings takes $O(N \log N)$ time, followed by a single pass through the array $O(N)$.Space Complexity: $O(1)$The algorithm operates in-place on the input array (depending on the sorting implementation's overhead).🚀 SEO & Meta TagsKeywords: GeeksforGeeks POTD, Meeting Rooms I, Attend All Meetings GFG, Interval Overlap Java, Greedy Algorithm, Sorting Intervals, Coding Interview Questions.Category: Data Structures and Algorithms (DSA)Difficulty: Easy/Medium🎥 Video CreditsSpecial thanks to the video walkthrough for the visual logic:Attend All Meetings | Meeting Rooms I | Interval SortingCreated for educational purposes. Feel free to star ⭐ this repo if it helped!
Would you like me to add a **Python** or **C++** version of the code to this README