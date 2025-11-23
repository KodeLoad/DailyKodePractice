class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // root1 and root2 are null -> true
        if (root1 == null && root2 == null) return true;

        // root1 or root2 are null return false
        if (root1 == null || root2 == null) return false;

        // root1.val != root2.val return false
        if (root1.val != root2.val) return false;

        // flipped 
        if (
            flipEquiv(root1.left, root2.right)
            && flipEquiv(root1.right, root2.left)
        ) return true;

        // dont flip it
        if (
            flipEquiv(root1.left, root2.left)
            && flipEquiv(root1.right, root2.right)
        ) return true;

        return false;
    }
}