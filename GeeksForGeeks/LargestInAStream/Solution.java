package GeeksForGeeks.LargestInAStream;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
    1, 2, 3, 4, 5, 6
    
    k = 4
    1           :: -1   
    2           :: -1
    3           :: -1
    1 2 3 4     :: 1
    1 2 3 4 5   :: 2
        ...
        
    n
    1-1
    1-2
    ...
    1-n
    O(N*N)
    
    -------
    
    
    HEAP
    PriorityQueue
    
    heap :: min or max
    max kth
    
        |-----|
    1 2 2 4 5 6 7
    
    min of kth window
    heap and considering k elements
    n :
    ----- get the k
    k elements only I just need top element
*/
class Solution {
    static ArrayList<Integer> kthLargest(int[] a, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int element : a) {
            queue.offer(element);
            if (queue.size() > k) {
                queue.poll(); // remove
            }
            res.add(
                queue.size() < k? -1 : queue.peek()
            );
        }
        
        return res;
    }
}


