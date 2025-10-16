class Node{
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
    Get the Greater numbers from current node

    sum them up
    and store it

    and then proceed to next smaller nodes


    right -> cur -> left

    a sum to store the current running sum

*/


class Solution {
    public static void transformTree(Node root) {
        helper(root, new int[]{0});
    }

    static void helper(Node root, int[] sum) {
        if (root == null) {
            return;
        }

        // go to the right
        helper(root.right, sum);

        // store the number
        int cur = root.data;
        root.data = sum[0];
        sum[0] += cur; // cumulative sum

        // go to left
        helper(root.left, sum);
    }
}




