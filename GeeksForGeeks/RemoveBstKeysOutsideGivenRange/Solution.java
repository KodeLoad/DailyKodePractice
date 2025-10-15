class Node {
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

/*

        cur
less            greater


- if outsie range
    -- remove any number outside
        * cur > :: we want lesser number we will go to left 
        * cur < :: we want greater number we will go to right 

    left and right assignemt based upon valid nodes
    
    we return the parent or current node

*/

class Solution {
    Node removekeys(Node root, int l, int r) {
        if (root == null) {
            return null;
        }
        
        if (root.data < l || root.data > r) {
            // current root is for sure out of the range
            // we wont take current
            // check for left or right
            if (root.data > r) {
                // we wanted lesser number
                return removekeys(root.left, l, r);
            } else {
                 // we wanted greater number
                 return removekeys(root.right, l, r);
            }
        }
        
        // I am choosing current node
        Node cur = new Node(root.data);
        
        // setting child
        cur.left = removekeys(root.left, l, r);
        cur.right = removekeys(root.right, l, r);

        return cur;
    }
}
