package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;

public class BinaryTreeMaximumPathSum {
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
     * Space complexity : O(log(N))
     */
    public static class Solution1 {
        public static int maxPathSum(TreeNode root) {
            int[] result = maxPathSumRec(root);
            return result[1] == Integer.MIN_VALUE ? 0: result[1];
        }

        public static int[] maxPathSumRec(TreeNode node) {
            if(node == null)
                return new int[] {0, Integer.MIN_VALUE};

            int[] left = maxPathSumRec(node.left);
            int[] right = maxPathSumRec(node.right);

            int fullPathMaxSum = node.val + left[0] + right[0];
            int halfPathMaxSum = Math.max(node.val, Math.max(node.val + left[0], node.val + right[0]));
            int max = Math.max(Math.max(Math.max(left[1], right[1]), halfPathMaxSum), fullPathMaxSum);
            return new int[]{halfPathMaxSum, max};
        }
    }
}
