package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;

import java.util.Deque;
import java.util.LinkedList;

public class InvertOrFlipBinaryTree {
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Approach   : Recursive DFS
     * Time complexity : O(N)
     * Space complexity : O(N)
     */
    public static class Solution1 {
        public static TreeNode invertTree(TreeNode root) {
            if (root == null)
                return root;

            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            invertTree(root.left);
            invertTree(root.right);
            return root;
        }
    }

    /**
     * Approach   : Iterative BFS
     * Time complexity : O(N)
     * Space complexity : O(N)
     */
    public static class Solution2 {
        public static TreeNode invertTree(TreeNode root) {
            Deque<TreeNode> list = new LinkedList<>();
            if(root != null)
                list.offer(root);
            while(!list.isEmpty()) {
                TreeNode node = list.poll();

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                if(node.left != null)
                    list.offer(node.left);
                if(node.right != null)
                    list.offer(node.right);
            }
            return root;
        }
    }

    /**
     * Approach   : Iterative DFS
     * Time complexity : O(N)
     * Space complexity : O(N)
     */
    public static class Solution3 {
        public static TreeNode invertTree(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<>();
            if(root != null)
                stack.push(root);
            while(!stack.isEmpty()) {
                TreeNode node = stack.pop();

                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;

                if(node.right != null)
                    stack.push(node.right);
                if(node.left != null)
                    stack.push(node.left);
            }
            return root;
        }
    }

}
