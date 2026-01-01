class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

/*
    
    L1 -> map or set
    L2 -> for each node 
        node exist in set then 
        it's the first intersection
    SC: O(n)

    SC: (1)

        i
        1 -> 2 ->
                  3 -> 4 -> 199 -> 69 -> null
  -1 -> 10-> 20-> 
   j

    exactly i and j start at same point
        from the intersection
    

    make i and j to occur at same distance
    
    1 -> 
        count number of nodes
        l1 => 6
        l2 => 7
    
        l2 has more numbers 
            start l2 first with epoch
            j should pre-advance for (l2 - l1) => 1
            
        overall 3 iterations
    
    2 -> 
        can we do better?
        
            i 
            j
            start from actual heads
            go till the end
            
            loop back with alternate list
            check equality 
            boom!
*/

class Solution {
    public Node intersectPoint(Node head1, Node head2) {
        Node itr1, itr2;
        itr1 = head1;
        itr2 = head2;

        while (itr1 != itr2) {
            itr2 = itr2.next;
            itr1 = itr1.next;

            if (itr1 == null) {
                itr1 = head2;
            }
            if (itr2 == null) {
                itr2 = head1;
            }
        }

        return itr1;
    }
}
