package in.rgukt.r081247.java.coding.tree;

import java.io.*;
        import java.util.*;

public class BinaryTreeLevelOrderTraversal {

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
            Deque<Node> top = new LinkedList<>();
            Deque<Node> below = new LinkedList<>();
            top.offer(root);

            while (!top.isEmpty()) {
                Node n = top.poll();
                System.out.print(n.data + " ");
                if (n.leftChild != null)
                    below.offer(n.leftChild);
                if (n.rightChild != null)
                    below.offer(n.rightChild);


                if (top.isEmpty()) {
                    Deque<Node> temp = top;
                    top = below;
                    below = temp;
                }
            }

        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        //System.out.println("count: " + count);
        Tree t = new Tree();

        while (count > 0) {
            int data = sc.nextInt();
            t.add(data);
            count--;
        }

        t.preorder();
    }
}

