package GeeksForGeeks.FlatteningALinkedList;


class Node {
    int data;
    Node next;
    Node bottom;

    Node(int x) {
        data = x;
        next = null;
        bottom = null;
    }
}

/*
    merge next and bottom
    
    A    B
    a -> d -> null
    |    |
    b    e
    |    |
    c    f
    
    the next pointer is clear 
        then my problem is simpler
        (solved)
    
    merge the list with bottom pointers
        return the resulted list
        
    X    Y
    a ->
    |   
    
    
    
    X     Y
         c* -> null
    |    |
    b*    e
    |    |
    d    f
    
    
    a 
    merge(X.bottom, Y)
    
    a
    |
    b
    |
    c
    |
    d ...
    
    return the first pointer (a) ?
*/

class Solution {
    Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;
        
        Node res;
        if (a.data < b.data) {
            // chose a
            a.bottom = merge(a.bottom, b);
            res = a;
        } else {
            // chose b
            b.bottom = merge(a, b.bottom);
            res = b;
        }
        
        return res;
    }
    
    // function responsible to flatten the nodes
    public Node flatten(Node root) {
        if (root == null || root.next == null) {
            return root;
        }
        
        // I want to simplify the next nodes
        var cleanNextNode = flatten(root.next);
        
        return merge(root, cleanNextNode);
    }
}

