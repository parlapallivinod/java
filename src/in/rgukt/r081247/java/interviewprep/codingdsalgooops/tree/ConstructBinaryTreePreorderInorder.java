package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;


import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ConstructBinaryTreePreorderInorder {
    public static void main() {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
    }

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
     * Approach   : Recursive DFS copying arrays down
     * Time complexity : O(N^2)
     * Space complexity : O(2N)
     */
    public static class Solution1 {
        public static void main() {

            int[] preorder = {3,9,20,15,7};
            int[] inorder = {9,3,15,20,7};

            TreeNode t = buildTree(preorder, inorder);
        }

        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            System.out.println("preorder: " + Arrays.toString(preorder) + ", inorder: " + Arrays.toString(inorder));
            TreeNode treeNode = new TreeNode(preorder[0]);
            int key = preorder[0];
            int i = -1;
            for (int j = 0; j < inorder.length; j++) {
                if (inorder[j] == key) {
                    i = j;
                    break;
                }
            }
            //System.out.println("i: " + i);
            if (i > 0) {
                int[] lpreorder = Arrays.copyOfRange(preorder, 1, (1 + i));
                int[] linorder = Arrays.copyOfRange(inorder, 0, i);
                treeNode.left = buildTree(lpreorder, linorder);
            }
            if (i < (inorder.length - 1)) {
                int size = inorder.length - 1 - i;
                int[] rpreorder = Arrays.copyOfRange(preorder, preorder.length - size, preorder.length);
                int[] rinorder = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                treeNode.right = buildTree(rpreorder, rinorder);
            }
            return treeNode;
        }
    }

    /**
     * Approach   : Recursive DFS passing indexes down
     * Time complexity : O(N^2)
     * Space complexity : O(N)
     */
    public static class Solution2 {
        public static void main() {

            int[] preorder = {3,9,20,15,7};
            int[] inorder = {9,3,15,20,7};

            TreeNode t = buildTree(preorder, inorder);
        }

        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
        }

        public static TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
            //System.out.println("preorder: " + Arrays.toString(Arrays.copyOfRange(preorder, s1, e1+1)) + ", inorder: " + Arrays.toString(Arrays.copyOfRange(inorder, s2, e2+1)));
            TreeNode treeNode = new TreeNode(preorder[s1]);
            int key = preorder[s1];
            int i = -1;
            for (int j = s2; j <= e2; j++) {
                if (inorder[j] == key) {
                    i = j;
                    break;
                }
            }
            if (i > s2) {
                int size = i-s2;
                treeNode.left = buildTree(preorder, s1+1, s1+size, inorder, s2, i-1);
            }
            if (i < e2) {
                int size = e2-i;
                treeNode.right = buildTree(preorder, e1 - size+1, e1, inorder, i+1, e2);
            }
            return treeNode;
        }
    }

    /**
     * Approach   : Recursive DFS passing indexes down and map for inorder
     * Time complexity : O(N)
     * Space complexity : O(N)
     */
    public static class Solution3 {
        public static void main() {

            int[] preorder = {3,9,20,15,7};
            int[] inorder = {9,3,15,20,7};

            TreeNode t = buildTree(preorder, inorder);
        }

        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0;i<inorder.length;i++)
                map.put(inorder[i], i);
            return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, map);
        }

        public static TreeNode buildTree(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2, Map<Integer, Integer> map) {
            //System.out.println("preorder: " + Arrays.toString(Arrays.copyOfRange(preorder, s1, e1+1)) + ", inorder: " + Arrays.toString(Arrays.copyOfRange(inorder, s2, e2+1)));
            TreeNode treeNode = new TreeNode(preorder[s1]);
            int key = preorder[s1];
            int i = map.get(key);
            if (i > s2) {
                int size = i-s2;
                treeNode.left = buildTree(preorder, s1+1, s1+size, inorder, s2, i-1,map);
            }
            if (i < e2) {
                int size = e2-i;
                treeNode.right = buildTree(preorder, e1 - size+1, e1, inorder, i+1, e2, map);
            }
            return treeNode;
        }
    }



}
