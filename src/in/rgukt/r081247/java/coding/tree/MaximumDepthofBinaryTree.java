package in.rgukt.r081247.java.coding.tree;


import java.io.*;
        import java.util.*;

public class MaximumDepthofBinaryTree {

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

        public int getHeight() {
            int height = -1;
            Deque<Node> parent = new LinkedList<>();
            Deque<Node> child = new LinkedList<>();
            parent.offer(root);

            while (!parent.isEmpty()) {
                Node n = parent.poll();
                if (n.leftChild != null) {
                    child.offer(n.leftChild);
                }
                if (n.rightChild != null) {
                    child.offer(n.rightChild);
                }

                if (parent.isEmpty()) {
                    height++;
                    Deque<Node> temp = parent;
                    parent = child;
                    child = temp;
                }

            }

            return height;

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

        System.out.println(t.getHeight());
    }
}

