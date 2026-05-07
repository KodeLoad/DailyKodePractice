package GeeksForGeeks.CheckIfSubtree;

/*
Definition for Node
class Node {
    int data;
    Node left;
    Node right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}
*/

class Node {
    int data;
    Node left;
    Node right;

    Node(int x) {
        data = x;
        left = right = null;
    }
}

/*
        s = Hello World
        t =   llo

        for each character ch of s:
            check if this could be start of t
            and then compare t and the string from ch
*/
class Solution {
    public boolean isSubTree(Node root1, Node root2) {
        if (root2 == null) {
            return true;
        }

        if (root1 == null) {
            return false;
        }

        var cur = check(root1, root2);

        return cur
            || isSubTree(root1.left, root2)
            || isSubTree(root1.right, root2);
    }

    boolean check(Node r1, Node r2) {
        // check structre 
        if (r1 == null && r2 == null) {
            return true;
        }

        if (r1 == null || r2 == null) {
            return false;
        }


        // check value
        if (r1.data != r2.data) {
            return false;
        }

        return check(r1.left, r2.left)
            && check(r1.right, r2.right);
    }
}
