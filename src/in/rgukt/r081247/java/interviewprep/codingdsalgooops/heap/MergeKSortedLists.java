package in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap;

import java.util.*;

public class MergeKSortedLists {
     // Definition for singly-linked list.
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }

         public static void printList(ListNode head) {
             while (head != null) {
                 System.out.print(head.val + " ");
                 head = head.next;
             }
             System.out.println();
         }
     }

    /**
     * Approach: Merge k sorted lists
     * Complexity : Time: O(nlogk) ; Space: O(k)
     */
    class Solution3 {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0)
                return null;

            List<ListNode> ls = Arrays.asList(lists);
            while (ls.size() > 1) {
                List<ListNode> mergedList = new ArrayList<>();
                for (int i = 0; i < ls.size(); i += 2) {
                    ListNode list1 = ls.get(i);
                    ListNode list2 = null;
                    if (i + 1 < ls.size())
                        list2 = ls.get(i + 1);
                    mergedList.add(mergeLists(list1, list2));
                }
                ls = mergedList;
            }

            return ls.get(0);
        }

        public ListNode mergeLists(ListNode head1, ListNode head2) {
            ListNode head = new ListNode();
            ListNode tail = head;
            while (head1 != null && head2 != null) {
                ListNode node = null;
                if (head1.val <= head2.val) {
                    tail.next = head1;
                    head1 = head1.next;
                } else {
                    tail.next = head2;
                    head2 = head2.next;
                }
                tail = tail.next;
            }

            if (head1 != null)
                tail.next = head1;
            if (head2 != null)
                tail.next = head2;

            return head.next;
        }
    }

    /**
     * Approach: Priority Queue
     * Complexity : Time: O(nlogk) ; Space: O(k)
     */
    class Solution4 {
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode head = null;
            ListNode last = null;

            Queue<ListNode> queue = new PriorityQueue<>((ls1, ls2) -> ls1.val - ls2.val);
            for (ListNode list: lists) {
                if (list != null)
                    queue.offer(list);
            }

            while (!queue.isEmpty()) {
                ListNode node = queue.poll();
                if (head == null) {
                    head = last =  node;
                } else {
                    last.next = node;
                    last = node;
                }

                if (node.next != null)
                    queue.offer(node.next);
            }
            return head;
        }
    }
}
