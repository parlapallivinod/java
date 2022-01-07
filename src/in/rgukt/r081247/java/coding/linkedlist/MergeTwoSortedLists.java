package in.rgukt.r081247.java.coding.linkedlist;

import java.io.*;
        import java.util.*;

public class MergeTwoSortedLists {

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static class SLList {
        public Node head;
        public Node tail;

        public void add(int data) {
            Node node = new Node(data);

            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
    }

    public static SLList mergeSLList(SLList l1, SLList l2) {
        SLList list = new SLList();
        Node l1head = l1.head;
        Node l2head = l2.head;

        while (l1head != null && l2head != null) {
            if (l1head.data < l2head.data) {
                list.add(l1head.data);
                l1head = l1head.next;
            } else {
                list.add(l2head.data);
                l2head = l2head.next;
            }
        }

        while (l1head != null ) {
            list.add(l1head.data);
            l1head = l1head.next;
        }

        while (l2head != null ) {
            list.add(l2head.data);
            l2head = l2head.next;
        }

        //System.out.println("returning");
        return list;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();

        while (count > 0) {
            //System.out.println("count: " + count);
            int l1size = sc.nextInt();
            SLList l1 = new SLList();
            while(l1size > 0) {
                //System.out.println("l1size: " + l1size);
                int data = sc.nextInt();
                l1.add(data);
                l1size--;
            }

            int l2size = sc.nextInt();
            SLList l2 = new SLList();
            while(l2size > 0) {
                //System.out.println("l2size: " + l2size);
                int data = sc.nextInt();
                l2.add(data);
                l2size--;
            }

            SLList list = mergeSLList(l1, l2);
            Node n = list.head;
            while (n != null) {
                System.out.print(n.data + " ");
                n = n.next;
            }
            System.out.println();

            count--;
        }
    }
}

