package in.rgukt.r081247.java.interviewprep.codingdsalgooops.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /*
     * Approach   : dict to remember visited nodes
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution1 {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> visitedNodes = new HashSet<>();
            while(head != null) {
                if(visitedNodes.contains(head))
                    return true;
                visitedNodes.add(head);
                head = head.next;
            }
            return false;
        }
    }

    /*
     * Approach   : two pointers at different speeds, if they meet there is loop
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution2 {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null)
                return false;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    return true;
                }
            }
            return false;
        }
    }
}
