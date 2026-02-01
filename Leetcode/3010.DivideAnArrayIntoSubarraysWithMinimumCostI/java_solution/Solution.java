package Leetcode.java_solution.DivideAnArrayIntoSubarraysWithMinimumCostI;

import java.util.Collections;
import java.util.PriorityQueue;

/*
    3 sub arrays

        * ---- * --- * --*
        0     1    2    3   :: index 
        10    3    1    1   :: value

    I have to take the first element
    min 
        1 -> n
    2 min
*/
class Solution {
    public int minimumCost(int[] a) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(
            Collections.reverseOrder()
        );

        for (int i = 1; i < a.length; i++) {
            queue.offer(a[i]);
            if (queue.size() > 2) {
                queue.poll();
            }
        }
        
        return a[0] + queue.poll() + queue.poll();
    }
}