import java.util.*;

class Node {
    int data;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

/*
Objective
    get K element
    must be closest to target
    
Approach 0:
    BST -> array :: process for the closest
    target :: 17
    BST :: 20, 8, 22, 4, 12, N, N, N, N, 10, 14
    sorted Array :: 4, 8, 10, (12, 14, 20), 22
    S.C. : O(n) :: n >= k  but, n >>>> k
   
Approach  1:
    BST to it's fullest
    inorder traversal :: sorted list
        result is always consicative // finding
        result is of size k         // ask from the problem
        
    do the in-order traversal
        - keep track of these k elements
        - check for any update (if getting closer elements)
            - if good update array
            - check for the next one
            
        DS : k elements
        in order
        
        left -> cur -> right
        iter -> check for the above  -> iter
*/

class Solution {
    public ArrayList<Integer> getKClosest(Node root, int target, int k) {
        Deque<Integer> queue = new LinkedList<>();
        process(root, queue, target, k);
        // queue would be populated here
        return new ArrayList<>(queue);
    }
    
    void process(
        Node root, 
        Deque<Integer> queue,
        int target, 
        int k
    ) {
        // check for null
        if (root == null) {
            return;
        }
        
        // left 
        process(root.left, queue, target, k);
        
        // cur
        // queue :: [i, i+1, i+2 ... last] :: I'm on the root
        // first :: smallest - target >>> 
        // last :: largest  - target >
        // [1,2,3,4] 
        // target :: 5
        // first 1 ~ 5 >>> 
        // last 4 ~ 5 > 
        if (queue.size() < k) {
            // I want k element
            // I'm short of it so I'll populate
            queue.offer(root.data);
        } else if (
            // if I can take this root data or not
            // is root data closer to target
            Math.abs(queue.getFirst() - target) >
            Math.abs(root.data - target)
        ) {
            // root data is closer
            // so it a match feed it in our queue
            queue.pollFirst();
            queue.offerLast(root.data);
        } else {
            // root data is far
            // root data is much greater
            return;
        }
        
        // right
        process(root.right, queue, target, k);
    }
    
}

