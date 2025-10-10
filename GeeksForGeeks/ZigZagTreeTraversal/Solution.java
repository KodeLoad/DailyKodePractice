import java.util.*;

class Node {
    int data;
    Node left, right;
    Node(int d)
    {
        data=d;
        left=right=null;
    }
}

/*

        1           level 1 ltr              -> 1
    2       3       level 2 rtl (reverse)    -> 3 2
  4   5   6   7     level 3 ltr              -> 4 5 6 7

reverse based upon level
    -> level order traversal (for justifying ltr r rtl)

    -> keep track of each level

       each level complete when we finish till the last
       1 -> NULL
       2 3 -> NULL
       4 5 6 7 -> NULL

        * null indicates end of level
        * if seen null level is complete
        * if seen null no further elements beyond this node
        * so child should also be finished adding siblings up
        * if seen null offer null to queue

    -> keep a tracker based upon reverse print or not
*/

class Solution {
    ArrayList<Integer> zigZagTraversal(Node root) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        List<Integer> level = new LinkedList<>();
        boolean isReverse = false;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        while (!queue.isEmpty()) {
            // process it
            Node cur = queue.poll();
            if (cur == null) {
                // we hit a level
                if (isReverse) {
                    Collections.reverse(level);
                }

                res.addAll(level);
                level.clear();
                isReverse ^= true;

                // check for the tree completion
                if (queue.isEmpty()) {
                    break;
                }

                queue.offer(null);
                continue;
            }

            level.add(cur.data);
            if (cur.left != null) queue.offer(cur.left);
            if (cur.right != null) queue.offer(cur.right);
        }

        return res;
    }
}






