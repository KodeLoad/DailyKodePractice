package Leetcode.DeleteNodesFromLinkedListPresentInArray;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> set = Arrays
            .stream(nums)
            .boxed()
            .collect(Collectors.toSet());
        ListNode dummy = new ListNode(-99);
        ListNode itr = dummy;

        while (head != null) {
            if (!set.contains(head.val)) {
                itr.next = new ListNode(head.val);
                itr = itr.next;
            }
            head = head.next;
        }

        return dummy.next;
    }
}