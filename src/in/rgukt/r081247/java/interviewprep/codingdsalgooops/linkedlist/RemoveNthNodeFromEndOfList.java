package in.rgukt.r081247.java.interviewprep.codingdsalgooops.linkedlist;

import javax.swing.*;

public class RemoveNthNodeFromEndOfList {
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
     * Approach   : Iterative
     * Complexity : Time: O(n) ; Space: O(1)
     */
    class Solution1{
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int count = 0;
            ListNode temp = head;
            while(temp != null) {
                count++;
                temp = temp.next;
            }
            count = count - n + 1;
            if(count == 1)
                return head.next;
            temp = head;
            while(count > 2) {
                count--;
                temp = temp.next;
            }
            temp.next = temp.next.next;
            return head;
        }
    }

    /*
     * Approach   : 2 Pointers
     * Complexity : Time: O(n) ; Space: O(1)
     */
    class Solution2{
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode p1, p2;
            p1 = null;
            p2 = head;
            while(n > 1) {
                p2 = p2.next;
                n--;
            }
            if(p2.next != null){
                p1 = head;
                p2 = p2.next;
            }
            while(p2.next != null) {
                p2 = p2.next;
                p1 = p1.next;
            }

            if(p1 == null)
                return head.next;

            p1.next = p1.next.next;
            return head;
        }
    }

}
