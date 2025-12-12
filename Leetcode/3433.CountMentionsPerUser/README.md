# Count Mentions Per User
---

> Video description: https://youtu.be/XTTyXr7JWKo

[Problem](https://leetcode.com/problems/count-mentions-per-user/) | [Java Solution](./java_solution/Solution.java) | [Discussion](https://www.reddit.com/r/OBrutus/)

[![img](https://img.youtube.com/vi/XTTyXr7JWKo/0.jpg)](https://youtu.be/XTTyXr7JWKo)

---

**Difficulty:** Medium  
**Acceptance Rate:** 49.9%  
**Submissions:** 142.1K+  
**Accepted:** 70.9K+  
**Topics:** Array | String | Hash Table | Simulation  
**Average Time:** 35m

You are given an integer `numberOfUsers` representing the total number of users and an array `events` of size n × 3.

Each `events[i]` can be either of the following two types:

**1. Message Event:** `["MESSAGE", "timestampi", "mentions_stringi"]`
- This event indicates that a set of users was mentioned in a message at `timestampi`.
- The `mentions_stringi` string can contain one of the following tokens:
  - `id<number>`: where `<number>` is an integer in range [0, numberOfUsers - 1]. There can be multiple ids separated by a single whitespace and may contain duplicates. This can mention even offline users.
  - `ALL`: mentions all users.
  - `HERE`: mentions all online users.

**2. Offline Event:** `["OFFLINE", "timestampi", "idi"]`
- This event indicates that user `idi` became offline at `timestampi` for 60 time units.
- The user will automatically be online again at time `timestampi + 60`.

Return an array `mentions` where `mentions[i]` represents the number of mentions the user with id `i` has across all MESSAGE events.

**Important Notes:**
- All users are initially online
- If a user goes offline or comes back online, their status change is processed before handling any message event that occurs at the same timestamp
- A user can be mentioned multiple times in a single message event, and each mention should be counted separately

## Examples:

**Example 1:**
```
Input: numberOfUsers = 2, 
       events = [["MESSAGE","10","id1 id0"],
                 ["OFFLINE","11","0"],
                 ["MESSAGE","71","HERE"]]

Output: [2,2]

Explanation:
Initially, all users are online.

Timestamp 10: "MESSAGE" - id1 and id0 are mentioned
  mentions = [1,1]

Timestamp 11: "OFFLINE" - user 0 goes offline

Timestamp 71: user 0 comes back online (11 + 60 = 71)
              "MESSAGE" - "HERE" mentions all online users
  mentions = [2,2]
```

**Example 2:**
```
Input: numberOfUsers = 2,
       events = [["MESSAGE","10","id1 id0"],
                 ["OFFLINE","11","0"],
                 ["MESSAGE","12","ALL"]]

Output: [2,2]

Explanation:
Initially, all users are online.

Timestamp 10: "MESSAGE" - id1 and id0 are mentioned
  mentions = [1,1]

Timestamp 11: "OFFLINE" - user 0 goes offline

Timestamp 12: "MESSAGE" - "ALL" mentions all users (including offline)
  mentions = [2,2]
```

**Example 3:**
```
Input: numberOfUsers = 2,
       events = [["OFFLINE","10","0"],
                 ["MESSAGE","12","HERE"]]

Output: [0,1]

Explanation:
Initially, all users are online.

Timestamp 10: "OFFLINE" - user 0 goes offline

Timestamp 12: "MESSAGE" - "HERE" mentions only online users
              user 0 is still offline (10 + 60 = 70 > 12)
  mentions = [0,1]
```

## Constraints:
- 1 ≤ numberOfUsers ≤ 100
- 1 ≤ events.length ≤ 100
- events[i].length == 3
- events[i][0] will be one of MESSAGE or OFFLINE
- 1 ≤ int(events[i][1]) ≤ 10⁵
- The number of id<number> mentions in any "MESSAGE" event is between 1 and 100
- 0 ≤ <number> ≤ numberOfUsers - 1
- It is guaranteed that the user id referenced in the OFFLINE event is online at the time the event occurs

## Expected Complexities:
- **Time Complexity:** O(n × m) where n is number of events and m is average mentions per event
- **Space Complexity:** O(numberOfUsers)

## Company Tags:
Meta | Google | Amazon

## Topic Tags:
Array | String | Hash Table | Simulation | String Parsing

## Approach:

### Problem Understanding:

**Key Components:**
1. Track user online/offline status
2. Track when offline users come back online
3. Process events in chronological order
4. Count mentions based on event type

**Event Types:**
- **OFFLINE**: User goes offline for 60 time units
- **MESSAGE with id<number>**: Mention specific users (can be offline)
- **MESSAGE with ALL**: Mention all users (including offline)
- **MESSAGE with HERE**: Mention only online users

### Key Observations:

1. **User Status Management:**
   - All users start online
   - When user goes offline at time T, they return at time T + 60
   - Status changes are processed before messages at same timestamp

2. **Mention Types:**
   - `id<number>`: Direct mention (offline status doesn't matter)
   - `ALL`: Mentions everyone regardless of status
   - `HERE`: Only mentions users who are currently online

3. **Duplicate Mentions:**
   - Same user can be mentioned multiple times in one message
   - Each mention counts separately
   - Example: "id0 id0 id0" counts as 3 mentions for user 0

4. **Processing Order:**
   - Events must be sorted by timestamp
   - At each timestamp:
     1. First, update all user statuses (offline → online if time reached)
     2. Then, process OFFLINE events
     3. Finally, process MESSAGE events

### Algorithm:
```
// Initialize
mentions = array of size numberOfUsers, all zeros
offlineUntil = array of size numberOfUsers, all zeros (or -1)

// Sort events by timestamp if not already sorted

for each event in events:
    timestamp = int(event[1])
    
    // Step 1: Bring offline users back online
    for user in 0 to numberOfUsers-1:
        if offlineUntil[user] > 0 and offlineUntil[user] <= timestamp:
            offlineUntil[user] = 0  // User is now online
    
    // Step 2: Process event
    if event[0] == "OFFLINE":
        userId = int(event[2])
        offlineUntil[userId] = timestamp + 60
    
    else if event[0] == "MESSAGE":
        mentionString = event[2]
        
        if mentionString == "ALL":
            // Mention all users
            for user in 0 to numberOfUsers-1:
                mentions[user]++
        
        else if mentionString == "HERE":
            // Mention only online users
            for user in 0 to numberOfUsers-1:
                if offlineUntil[user] == 0:  // User is online
                    mentions[user]++
        
        else:
            // Parse individual mentions
            tokens = split mentionString by whitespace
            for each token in tokens:
                if token starts with "id":
                    userId = extract number from token
                    mentions[userId]++

return mentions
```

### Step-by-Step Walkthrough (Example 1):
```
numberOfUsers = 2
events = [["MESSAGE","10","id1 id0"],
          ["OFFLINE","11","0"],
          ["MESSAGE","71","HERE"]]

Initialize:
mentions = [0, 0]
offlineUntil = [0, 0]  // 0 means online

Event 1: ["MESSAGE", "10", "id1 id0"]
- timestamp = 10
- Check offline users: none to bring back
- Parse "id1 id0": split into ["id1", "id0"]
  - Mention user 1: mentions = [0, 1]
  - Mention user 0: mentions = [1, 1]

Event 2: ["OFFLINE", "11", "0"]
- timestamp = 11
- Check offline users: none to bring back
- User 0 goes offline until 11 + 60 = 71
  - offlineUntil = [71, 0]

Event 3: ["MESSAGE", "71", "HERE"]
- timestamp = 71
- Check offline users:
  - User 0: offlineUntil[0] = 71, 71 <= 71, so bring back online
  - offlineUntil = [0, 0]
- Parse "HERE": mention all online users
  - User 0 is online: mentions[0]++ → mentions = [2, 1]
  - User 1 is online: mentions[1]++ → mentions = [2, 2]

Output: [2, 2] ✓
```

### Step-by-Step Walkthrough (Example 3):
```
numberOfUsers = 2
events = [["OFFLINE","10","0"],
          ["MESSAGE","12","HERE"]]

Initialize:
mentions = [0, 0]
offlineUntil = [0, 0]

Event 1: ["OFFLINE", "10", "0"]
- timestamp = 10
- User 0 goes offline until 10 + 60 = 70
  - offlineUntil = [70, 0]

Event 2: ["MESSAGE", "12", "HERE"]
- timestamp = 12
- Check offline users:
  - User 0: offlineUntil[0] = 70, 70 > 12, still offline
  - offlineUntil = [70, 0]
- Parse "HERE": mention only online users
  - User 0 is offline: skip
  - User 1 is online: mentions[1]++ → mentions = [0, 1]

Output: [0, 1] ✓
```

### Detailed Example (Multiple Mentions):
```
numberOfUsers = 3
events = [["MESSAGE","5","id0 id1 id0 id2 id0"]]

Event: ["MESSAGE", "5", "id0 id1 id0 id2 id0"]
- Parse: ["id0", "id1", "id0", "id2", "id0"]
- User 0 mentioned 3 times: mentions[0] = 3
- User 1 mentioned 1 time: mentions[1] = 1
- User 2 mentioned 1 time: mentions[2] = 1

Output: [3, 1, 1]
```

### String Parsing Details:

**Parsing mention string:**
```
Input: "id1 id0 id2"

Step 1: Split by whitespace
tokens = ["id1", "id0", "id2"]

Step 2: For each token starting with "id"
- Extract number: "id1" → 1
- Extract number: "id0" → 0
- Extract number: "id2" → 2

Step 3: Increment mentions
mentions[1]++
mentions[0]++
mentions[2]++
```

### Edge Cases:

1. **User comes back online at exact timestamp:**
```
   events = [["OFFLINE","10","0"],["MESSAGE","70","HERE"]]
   At timestamp 70: user 0 comes back (10+60=70), then message processed
   User 0 is online for "HERE" mention
```

2. **Multiple offline periods:**
```
   User can go offline multiple times
   Each OFFLINE resets the offlineUntil time
```

3. **Duplicate mentions in same message:**
```
   "id0 id0 id0" → user 0 gets 3 mentions
   Each occurrence counts
```

4. **ALL vs HERE at same timestamp:**
```
   "ALL" mentions everyone regardless of status
   "HERE" only mentions online users
```

5. **Empty mentions (edge case not in problem):**
```
   If mention string is empty, no mentions counted
```

### Implementation Details:

**Checking if user is online:**
```python
def isOnline(userId, currentTime):
    return offlineUntil[userId] == 0 or offlineUntil[userId] <= currentTime
```

**Updating online status:**
```python
def updateOnlineStatus(currentTime):
    for userId in range(numberOfUsers):
        if offlineUntil[userId] > 0 and offlineUntil[userId] <= currentTime:
            offlineUntil[userId] = 0
```

**Parsing mentions:**
```python
def parseMentions(mentionString):
    if mentionString == "ALL":
        return list(range(numberOfUsers))
    elif mentionString == "HERE":
        return [i for i in range(numberOfUsers) if isOnline(i)]
    else:
        mentions = []
        tokens = mentionString.split()
        for token in tokens:
            if token.startswith("id"):
                userId = int(token[2:])
                mentions.append(userId)
        return mentions
```

### Common Mistakes:

1. **Not processing status changes first:**
   - Must check if users come back online before processing messages
   - Order matters at same timestamp

2. **Forgetting duplicate counts:**
   - "id0 id0" should count as 2 mentions, not 1
   - Don't use sets for counting

3. **Wrong offline duration:**
   - User is offline for exactly 60 time units
   - Returns online at timestamp + 60 (inclusive)

4. **ALL vs HERE confusion:**
   - ALL: everyone (including offline)
   - HERE: only online users

5. **Not initializing all users as online:**
   - All users start online (offlineUntil = 0)

### Optimization Notes:

**Time Complexity Analysis:**
- Processing each event: O(1) to O(numberOfUsers)
- Parsing mentions: O(number of mentions in string)
- Overall: O(n × (m + numberOfUsers)) where n is events, m is mentions

**Space Complexity:**
- mentions array: O(numberOfUsers)
- offlineUntil array: O(numberOfUsers)
- Total: O(numberOfUsers)

**Optimization ideas:**
- If events not sorted, sort by timestamp: O(n log n)
- Use hash map instead of array if numberOfUsers is very large and sparse

## Related Interview Experiences:
- Design Twitter
- Logger Rate Limiter
- Design Hit Counter
- Task Scheduler
- Meeting Rooms II

## Related Articles:
- Event Processing and Simulation
- String Parsing Techniques
- State Management in Algorithms
- Time-based User Status Tracking