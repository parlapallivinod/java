package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

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
     * Approach   : Level by Level traversal using Queues
     * Time complexity : O(n), Space complexity : O(n)
     */
    public class Solution1 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new LinkedList<>();
            Deque<TreeNode> parents = new LinkedList<>();
            Deque<TreeNode> children = new LinkedList<>();
            if(root != null)
                parents.add(root);
            while(!parents.isEmpty()) {
                List<Integer> list = new LinkedList<>();
                while(!parents.isEmpty()) {
                    TreeNode node = parents.poll();
                    list.add(node.val);
                    if(node.left != null)
                        children.offer(node.left);
                    if(node.right != null)
                        children.offer(node.right);
                }
                result.add(list);
                Deque<TreeNode> temp = parents;
                parents = children;
                children = temp;
            }
            return result;
        }
    }
}
