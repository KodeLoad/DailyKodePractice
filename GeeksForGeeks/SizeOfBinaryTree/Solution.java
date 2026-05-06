package GeeksForGeeks.SizeOfBinaryTree;

/*
Definition for Node
class Node
{
    int data;
    Node left;
    Node right;

    Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Node {
    int data;
    Node left;
    Node right;

    Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
}

// BFS && DFS
class Solution {
    public int getSize(Node root) { // get count => count nodes
        if (root == null) {
            return 0;
        }

        return 1 
            + getSize(root.left) 
            + getSize(root.right);
    }
}
