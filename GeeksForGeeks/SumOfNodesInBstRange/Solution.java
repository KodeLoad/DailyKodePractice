class Node {
    int data;
    Node left, right;
    Node(int val) {
        data = val;
        left = right = null;
    }
}

// TC: O(log(n))
// SC: O(log(n))

/*
BST: [1, 10]


                    25x <- root
                   /  \
                 19x    N
                /  
              12x    
             /  
            2+  
           / \  
          1  10+ 
       0.5  1.5
               
             result = 12
             
    * check the cur
    * identify if it is 
        - within range
        - range is lesser
        - range is greater (default if both didn't satisfy)
*/

class Solution {
    public int nodeSum(Node root, final int l, final int r) {
        if (root == null) {
            return 0;
        }
        
        final int cur = root.data;
        
        // - within range
        if (l <= cur && cur <= r) {
            // we are within the range
            // consider the node
            // consider the left and right 
            // as they might be within range as well
            return cur
                + nodeSum(root.left, l, r)
                + nodeSum(root.right, l, r);
        }
        
        if (cur < l) {
            // cur is way less
            // we need higher numbers
            return nodeSum(root.right, l, r);
        } else {
            return nodeSum(root.left, l, r);
        }
    }
}
