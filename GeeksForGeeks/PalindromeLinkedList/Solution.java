package GeeksForGeeks.PalindromeLinkedList;

class Node {
    int data;
    Node next;

    Node(int d) {
        data = d;
        next = null;
    }
}

/*
 * 
 * l -> r : ? :: store?
 * l <- r : ?
 * 
 * can we break this LL in 2 parts
 * 
 * A B | B A
 * 0 && 3
 * 1 && 2
 * 
 * A B == A B or (reverse of (B A))
 * 
 * A B | A C
 * 
 * 
 * 1. we get the apt pos to cut
 * mid point
 * 
 * 2. we reverse the last portion
 * 
 * 3. check equality
 * 
 * 4. we restore the linked list
 * 
 */

class Solution {
    public boolean isPalindrome(Node head) {
        Node mid = getMid(head);

        Node rm = reverse(mid);
        var equal = isEqual(head, rm);

        mid.next = reverse(rm);

        return equal;
    }

    boolean isEqual(Node n1, Node n2) {
        while (n1 != null && n2 != null) {
            if (n1.data != n2.data) {
                return false;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        return true;
    }

    Node reverse(Node node) {
        Node prev = null;
        Node cur = node;

        while (cur != null) {
            Node next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    Node getMid(Node node) {
        Node s, f;
        s = f = node;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        return s;
    }
}
