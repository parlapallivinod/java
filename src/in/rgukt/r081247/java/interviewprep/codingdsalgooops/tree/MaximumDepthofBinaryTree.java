package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;

import java.util.Deque;
import java.util.LinkedList;

public class MaximumDepthofBinaryTree {
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
     * Approach   : Recursion DFS
     * Complexity : Time: O(n) ; Space: O(log(n)(max depth path))
     */
    class Solution1 {
        public int maxDepth(TreeNode root) {
            int depth = 0;
            if (root == null)
                return 0;
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            depth = Math.max(leftDepth + 1, rightDepth + 1);
            return depth;
        }
    }

    /**
     * Approach   : BFS
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution2 {
        public int maxDepth(TreeNode root) {
            if(root == null)
                return 0;
            int depth = 0;
            Deque<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while(!queue.isEmpty()) {
                depth++;
                int size = queue.size();
                while(size > 0) {
                    TreeNode node = queue.poll();
                    if(node.left != null)
                        queue.offer(node.left);
                    if(node.right != null)
                        queue.offer(node.right);
                    size--;
                }
            }
            return depth;
        }
    }
}

