package GeeksForGeeks.TransformToSumTree;

/* Structure for Tree Node
class Node {
    int data;
    Node left, right;

    // Constructor
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
};
*/

class Node {
    int data;
    Node left, right;

    // Constructor
    Node(int val) {
        data = val;
        left = null;
        right = null;
    }
};

/*
    new value is equal to the sum of all values in its 
    left and right subtrees
    
    
    node.data <- new_value = left_subtree_sum + right_subtree_sum
    backup 
        I'm not using node.data here directly
        but my parent needs this
        
    return backup + left_sum + right_sum
*/

class Solution {
    public int toSumTree(Node root) {
        if (root == null) {
            return 0;
        }
        
        // new_value = left_sum + right_sum
        int left = toSumTree(root.left);
        int right = toSumTree(root.right);
        
        int curData = root.data;
        root.data = left + right;
        
        return curData + left + right;
    }
}
