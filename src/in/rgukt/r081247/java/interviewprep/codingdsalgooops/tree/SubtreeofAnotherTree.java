package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;

import java.util.Deque;
import java.util.LinkedList;

public class SubtreeofAnotherTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Approach   : DFS
     * Time complexity : O(mn), Space complexity : O(m + n)
     */
    class Solution2 {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (root == null)
                return false;
            else if (areSame(root, subRoot))
                return true;
            else
                return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        public boolean areSame(TreeNode rootNode, TreeNode subRoot) {
            if (rootNode == null || subRoot == null)
                return rootNode == null && subRoot == null;
            else if (rootNode.val == subRoot.val)
                return areSame(rootNode.left, subRoot.left) && areSame(rootNode.right, subRoot.right);
            else
                return false;
        }
    }


    /**
     * Approach   : BFS
     * Time complexity : O(mn), Space complexity : O(m + n)
     */
    class Solution1 {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            Deque<TreeNode> nodes = new LinkedList<>();
            nodes.add(root);
            while (!nodes.isEmpty()) {
                for (int i = 0; i < nodes.size(); i++) {
                    TreeNode node = nodes.poll();
                    if (areSame(node, subRoot))
                        return true;
                    if (node.left != null)
                        nodes.offer(node.left);
                    if (node.right != null)
                        nodes.offer(node.right);
                }
            }
            return false;
        }

        public boolean areSame(TreeNode rootNode, TreeNode subRoot) {
            if (rootNode == null || subRoot == null)
                return rootNode == null && subRoot == null;
            if (rootNode.val != subRoot.val)
                return false;
            return areSame(rootNode.left, subRoot.left) && areSame(rootNode.right, subRoot.right);
        }
    }
}
