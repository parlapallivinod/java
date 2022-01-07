package in.rgukt.r081247.java.coding.tree;

import java.io.*;
import java.util.*;

public class LowestCommonAncestorofBST {

    public static class Node {
        public Node parent;
        public Node leftChild;
        public Node rightChild;
        public int data;
    }

    public static class Tree {
        public Node root;

        public void add(int data) {
            Node n = new Node();
            n.data = data;

            if (root == null) {
                root = n;
                return;
            }

            Node temp = root;
            while (temp != null) {
                if (n.data < temp.data) {
                    if (temp.leftChild != null) {
                        temp = temp.leftChild;
                    } else {
                        n.parent = temp;
                        temp.leftChild = n;
                        break;
                    }
                } else {
                    if (temp.rightChild != null) {
                        temp = temp.rightChild;
                    } else {
                        n.parent = temp;
                        temp.rightChild = n;
                        break;
                    }
                }
            }

        }

        public void preorder() {
            Deque<Node> q = new LinkedList<>();
            q.add(root);

            while (!q.isEmpty()) {
                Node n = q.pop();
                System.out.print(n.data + " ");
                if (n.rightChild != null)
                    q.push(n.rightChild);
                if (n.leftChild != null)
                    q.push(n.leftChild);


            }

        }

        public Node find(int data) {
            Node temp = root;
            while (temp != null) {
                if (temp.data == data) {
                    return temp;
                } else if (data >= temp.data) {
                    temp = temp.rightChild;
                } else {
                    temp = temp.leftChild;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        //System.out.println("count: " + count);
        Tree t = new Tree();

        while (count > 0) {
            int data = sc.nextInt();
            t.add(data);
            count--;
        }

        int data1 = sc.nextInt();
        int data2 = sc.nextInt();

        Node node1 = t.find(data1);
        Node node2 = t.find(data2);

        Deque<Node> dq1 = new LinkedList<>();
        Deque<Node> dq2 = new LinkedList<>();

        while (node1 != null) {
            dq1.addFirst(node1);
            node1 = node1.parent;
        }

        while (node2 != null) {
            dq2.addFirst(node2);
            node2 = node2.parent;
        }

        Node prev = dq1.getFirst();
        while ((!dq1.isEmpty()) && (!dq2.isEmpty()) && (dq1.getFirst() == dq2.getFirst())) {
            prev = dq1.getFirst();
            dq1.removeFirst();
            dq2.removeFirst();
        }

        System.out.println(prev.data);

    }
}
