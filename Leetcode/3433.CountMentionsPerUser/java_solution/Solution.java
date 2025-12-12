package Leetcode.CountMentionsPerUser.java_solution;

import java.util.*;

/**

MESSAGE
    HERE    : then we need to compare based operations!
    ALL     : no cmp
    id...   : no cmp

OFFLINE event:
    make offline/online transition first and then go for msgs
    time + 60 is offline


    events should be in proper order?
        t1 -> t2 -> t3 ... 
        100     10  50
        10 -> 50 -> 100
    (not neccesary)

    consider offlines first
    have mechanism to understand online users quickly

    upon getting offline event till +60min
                                t0 + 60min
            nextOnline [id] -> t0 + 60

            at any point of time if you give me timestamp
            t0 = 0
            nextOnline = 60
            cur = 70
            70 > 60 -> it's online
            curTime > nextOnline[id]


            cur = 20 
            20 < 60 -> it's offline
 */
class Solution {
    final int TYPE = 0;

    public int[] countMentions(int n, List<List<String>> events) {
        int[] res = new int[n];
        // timestampt ordering
        events.sort((e1, e2) -> {
            int t1 = Integer.parseInt(e1.get(1));
            int t2 = Integer.parseInt(e2.get(1));
            if (t1 == t2) {
                if (e1.get(TYPE).equals("OFFLINE")) { 
                    // priority
                    return -1;
                } else {
                    return 1;
                }
            }

            // now if t1 != t2
            return Integer.compare(t1, t2);
        });

        // offline or event mgmt
        int[] nextOnline = new int[n];
        int all = 0;
        for (var event : events) {
            int curTime = Integer.parseInt(event.get(1));
            if (event.get(TYPE).equals("OFFLINE")) {
                // mark this offline
                var id = Integer.parseInt(event.get(2));
                nextOnline[id] = curTime + 60;
                continue;
            }

            // we handle messages
            if (event.get(2).equals("ALL")) {
                all++;
                continue;
            }

            if (event.get(2).equals("HERE")) {
                for (int id = 0; id < n; id++) {
                    // isOnline means
                    if (curTime >= nextOnline[id]) {
                        res[id]++;
                    }
                }
                continue;
            }

            // im handeling individuals
            for (var s : event.get(2).split(" ")) {
                int id = Integer.parseInt(s.substring(2));
                res[id]++;
            }
        }

        for (int id = 0; id < n; id++) {
            res[id] += all;
        }

        return res;
    }
}

/**
    int[][] id -> list of offlines

                    all -> no issue
                    ind -> no issue
                    here -> 
                            id timestamp in offline ids :: log(n)
                                (curTime - 60) -> (curTimestamp)
 */