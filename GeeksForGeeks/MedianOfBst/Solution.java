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


class Solution {
    Integer get(Node root, int[] target) {
        if (root == null) {
            return null;
        }
        if (target[0] == 0) {
            return root.data;
        }
        
        Integer l = get(root.left, target);
        if (l != null) {
            return l;
        }
        
        target[0]--;
        if (target[0] == 0) {
            return root.data;
        }
        if (target[0] < 0) {
            return null;
        }
        
        return get(root.right, target);
    }
    
    int count(Node root) {
        if (root == null) {
            return 0;
        }
        
        return 1 + count(root.left) + count(root.right);
    }
    
    public int findMedian(Node root) {
        int cnt = count(root);
        int mid = (cnt%2 == 0)? cnt/2 : (cnt+1)/2;
        return get(root, new int[]{mid});
    }
}



