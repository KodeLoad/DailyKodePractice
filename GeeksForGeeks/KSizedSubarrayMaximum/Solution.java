package GeeksForGeeks.KSizedSubarrayMaximum;

/*
    k = 3
    
    0  1  2  3  4  5  6  7  8 :: index
    1, 2, 3, 1, 4, 5, 2, 3, 6 :: value
    
    // find max         :: superior
    // find till k      :: expiry
    
    1 2 3 -> 1
        3              
    
    choose between :: 3(2), 1(3)
    
    1 (2 3 1) -> 4
         3    

    choose between :: 4(4)
    
    larger -> smaller
    most likely to expier -> recently added
    
    -> [] <-
    <- [] ->
    
*/

class Solution {
    public ArrayList<Integer>
    maxOfSubarrays(int[] a, int k) {
        
        ArrayList<Integer> res = new ArrayList<>();
        // to store index
        Deque<Integer> queue = new LinkedList<>();
        
        // inital process for first window
        for (int i = 0; i < k; i++) {
            // make space
            while (
                !queue.isEmpty() && 
                a[queue.peekLast()] <= a[i]
            ) {
                queue.pollLast();
            }
            
            queue.offerLast(i);
        }
        res.add(a[queue.peekFirst()]);
        
        // check the rest
        for (int i = k; i < a.length; i++) {
            // make space
            // expiry
            while (!queue.isEmpty() 
                && queue.peekFirst() <= i - k
            ) {
                queue.pollFirst();
            }
            
            // superiority
            while (
                !queue.isEmpty()
                && a[queue.peekLast()] <= a[i]
            ) {
                queue.pollLast();
            }
            
            // made space successfully
            queue.offerLast(i);
            
            res.add(a[queue.peekFirst()]);
        }
        
        return res;
    }
}
