package in.rgukt.r081247.java.interviewprep.codingdsalgooops.linkedlist;

public class ReverseLinkedList {
    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /*
     * Approach   : Recursive
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution1 {
        public ListNode reverseList(ListNode head) {
            if (head == null) {
                return head;
            }
            return recursiveReverseList(head, null);
        }

        public ListNode recursiveReverseList(ListNode node, ListNode prev) {
            ListNode next = node.next;
            node.next = prev;
            if (next == null)
                return node;
            return recursiveReverseList(next, node);
        }
    }

    /*
     * Approach   : Iterative
     * Complexity : Time: O(n) ; Space: O(1)
     */
    class Solution2 {
        public ListNode reverseList(ListNode head) {
            ListNode current = head;
            ListNode previous = null;
            ListNode next = null;

            while(current != null) {
                next = current.next;
                current.next = previous;

                previous = current;
                current = next;
            }
            return previous;
        }
    }

    /*
     * Approach   : Recursive
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution3 {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null)
                return head;
            ListNode reverseListHead = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return reverseListHead;
        }
    }
}
