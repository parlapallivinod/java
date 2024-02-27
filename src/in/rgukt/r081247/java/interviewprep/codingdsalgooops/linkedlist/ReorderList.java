package in.rgukt.r081247.java.interviewprep.codingdsalgooops.linkedlist;

import java.util.Deque;
import java.util.LinkedList;

public class ReorderList {
    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {

    }

    /*
     * Approach   : Using Deque, store all nodes in deque
     * and get nodes from front and end to form new linked list
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution1 {
        public void reorderList(ListNode head) {
            // store all nodes in deque
            Deque<ListNode> list = new LinkedList<>();
            while(head != null) {
                list.offer(head);
                head = head.next;
            }
            // get nodes from front and end to form new linked list
            ListNode prevNode = null;
            if(!list.isEmpty())
                prevNode = list.pollFirst();
            ListNode nextNode = null;
            while(!list.isEmpty()) {
                if(!list.isEmpty()) {
                    nextNode = list.pollLast();
                    nextNode.next = null;
                    prevNode.next = nextNode;
                    prevNode = nextNode;

                }
                if(!list.isEmpty()) {
                    nextNode = list.pollFirst();
                    nextNode.next = null;
                    prevNode.next = nextNode;
                    prevNode = nextNode;
                }
            }
        }
    }

    /*
     * Approach : reverse second half of linked list,
     * use 2 pointers, one for first half of linked list,
     * second for second half to merge both into single linked list.
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution3 {
        public void reorderList(ListNode head) {
            if(head == null)
                return;
            // find middle
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            // reverse second half
            ListNode second = slow.next;
            slow.next = null;
            ListNode prev = null;
            while(second != null) {
                ListNode next = second.next;
                second.next = prev;
                prev = second;
                second = next;
            }

            // merge two halves
            ListNode first = head;
            second = prev;
            while (second != null) {
                ListNode firstNext = first.next, secondNext = second.next;
                first.next = second;
                second.next = firstNext;
                first = firstNext;
                second = secondNext;
            }
        }
    }
}
