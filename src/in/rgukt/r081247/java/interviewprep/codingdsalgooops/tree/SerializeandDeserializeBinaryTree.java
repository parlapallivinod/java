package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;

import java.util.*;

public class SerializeandDeserializeBinaryTree {
    public static void main(String[] args) {

        TreeNode t7 = new TreeNode(7);
        TreeNode t6 = new TreeNode(6);
        TreeNode t5 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        t4.left = t6;
        t4.right = t7;
        TreeNode t3 = new TreeNode(3);
        t3.left = t4;
        t3.right = t5;
        TreeNode t2 = new TreeNode(2);
        TreeNode t1 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        /*
        Codec1 ser = new Codec1();
        String s = ser.serialize(t1);
        System.out.println(s);
        String[] strs = s.split(",");
        for(int i=0;i<strs.length;i++)
            System.out.print(strs[i]+",");
        System.out.println();
        TreeNode r = ser.deserialize(s);
        System.out.println(ser.serialize(r));
         */
        Codec2 ser = new Codec2();
        String s = ser.serialize(t1);
        System.out.println(s);
    }
    /**
     * Definition for a binary tree node.
     */
     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    /**
     * Approach   : Recursive preorder
     * Time complexity : O(n), Space complexity : O(n)
     */
    public static class Codec2 {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            recSerialize(sb, root);
            return sb.toString();
        }
        public void recSerialize(StringBuilder sb, TreeNode node) {
            if (node == null) {
                sb.append("# ");
            } else {
                sb.append(node.val + " ");
                recSerialize(sb, node.left);
                recSerialize(sb, node.right);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nodes = data.split(" ");
            List<String> list = Arrays.asList(nodes);
            return recDeseriaize(list.iterator());
        }
        public TreeNode recDeseriaize(Iterator<String> itr) {
            String str = itr.next();
            if(str.equals("#"))
                return null;
            TreeNode node = new TreeNode(Integer.parseInt(str));
            node.left = recDeseriaize(itr);
            node.right = recDeseriaize(itr);
            return node;
        }
    }

    /**
     * Approach   : bfs every single non-null node is added to string, and it's children are added too,
     * even if they're null, deserialize by adding each non-null node to queue, deque node, it's children are next two nodes in string;
     * Time complexity : O(n), Space complexity : O(n)
     */
    public static class Codec1 {
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            Deque<TreeNode> deque = new LinkedList<>();
            if(root != null)
                deque.offer(root);
            while(!deque.isEmpty()) {
                int len = deque.size();
                for(int i = 0; i < len; i++) {
                    TreeNode node = deque.poll();
                    if(node == null)
                        
                        sb.append("null,");
                    else {
                        sb.append(node.val);
                        sb.append(",");
                        deque.offer(node.left);
                        deque.offer(node.right);
                    }
                }
            }
            return sb.toString();
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nodesValues = data.split(",");
            Deque<TreeNode> nodes = new LinkedList<>();
            if(nodesValues.length == 1)
                return null;
            TreeNode root = new TreeNode(Integer.parseInt(nodesValues[0]));
            nodes.offer(root);
            int i = 0;
            while(!nodes.isEmpty()) {
                TreeNode node = nodes.poll();
                i++;
                if(!nodesValues[i].equals("null")) {
                    TreeNode lchild = new TreeNode(Integer.parseInt(nodesValues[i]));
                    node.left = lchild;
                    nodes.offer(lchild);
                }
                i++;
                if(!nodesValues[i].equals("null")) {
                    TreeNode rchild = new TreeNode(Integer.parseInt(nodesValues[i]));
                    node.right = rchild;
                    nodes.offer(rchild);
                }
            }
            return root;
        }
    }
// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
}
