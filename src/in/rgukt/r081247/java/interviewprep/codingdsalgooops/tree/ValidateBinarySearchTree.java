package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;


import java.util.ArrayList;
import java.util.List;

public class ValidateBinarySearchTree {
    public static class TreeNode {
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
     * Approach   : DFS(Inorder traversal)
     * Time complexity : O(N)
     * Space complexity : O(N)
     */
    public static class Solution1 {
        public static boolean isValidBST(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            getValues(root, list);
            for(int i=0;i<list.size()-1;i++) {
                if(list.get(i) >= list.get(i+1))
                    return false;
            }
            return true;
        }

        private static void getValues(TreeNode root, List<Integer> list) {
            if(root == null)
                return;
            getValues(root.left, list);
            list.add(root.val);
            getValues(root.right, list);
        }
    }

    /**
     * Approach   : Recursion
     * Time complexity : O(N)
     * Space complexity : O(N)
     */
    public static class Solution2 {

        public  boolean isValidBST(TreeNode root) {
            if(root != null) {
                Object[] ret = isValidBSTRec(root);
                return (Boolean) ret[2];
            }
            return true;
        }

        private  Object[] isValidBSTRec(TreeNode root) {
            Object[] ret = new Object[] {root.val, root.val, true};
            if(root.left != null) {
                Object[] left = isValidBSTRec(root.left);

                if(((Integer)left[1]) >= root.val)
                    ret[2] = false;
                ret[2] = (Boolean)ret[2] && (Boolean)left[2];
                ret[0] = Math.min((Integer)left[0], (Integer) ret[0]);
                ret[1] = Math.max((Integer)left[1], (Integer) ret[1]);
            }

            if(root.right != null) {
                Object[] right = isValidBSTRec(root.right);

                if(((Integer)right[0]) <= root.val)
                    ret[2] = false;

                ret[2] = (Boolean) ret[2] && (Boolean) right[2];
                ret[0] = Math.min((Integer)right[0], (Integer) ret[0]);
                ret[1] = Math.max((Integer)right[1], (Integer) ret[1]);
            }
            return ret;
        }
    }

    /**
     * Approach   : Recursion
     * Time complexity : O(N)
     * Space complexity : O(N)
     */
    public static class Solution3 {

        public static boolean isValidBST(TreeNode root) {
           return isValidBSTRec(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private static boolean isValidBSTRec(TreeNode root, long left, long right) {
           if(root == null)
               return true;
           if(root.val <= left || root.val >= right)
               return false;

           return isValidBSTRec(root.left,left,root.val) && isValidBSTRec(root.right,root.val,right);
        }
    }
}
