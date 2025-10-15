class Node {
    int data;
    Node left, right;

    public Node(int d)
    {
        data = d;
        left = right = null;
    }
}

/*

                       .20.
        .8.(1)                          22
4(2)                .12.(-1)
               10(0)        14(-2)

*/

class Solution {
    public int kthSmallest(Node root, int k) {
        int[] X = new int[]{k, Integer.MIN_VALUE};
        Integer x = helper(root, X);
        return x == null? -1 : x;
    }

    Integer helper(Node root, int[] k) {
        if (root == null || k[0] < 0) {
            return null;
        }

       Integer left = helper(root.left, k);

       k[0]--;
        if (k[0] == 0) {
            return root.data;
        }
        if (k[0] < 0) {
            return left;
        }

        return helper(root.right, k);
    }
}