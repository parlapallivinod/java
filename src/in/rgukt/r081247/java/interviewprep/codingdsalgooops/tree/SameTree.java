package in.rgukt.r081247.java.interviewprep.codingdsalgooops.tree;

import java.util.Deque;
import java.util.LinkedList;

public class SameTree {
    public static void main(String[] args) {
        TreeNode pleft = new TreeNode(2);
        TreeNode pright = new TreeNode(3);
        TreeNode proot = new TreeNode(1, pleft, pright );

        TreeNode qleft = new TreeNode(2);
        TreeNode qright = new TreeNode(3);
        TreeNode qroot = new TreeNode(1, qleft, qright );

        boolean result = Solution2.isSameTree(proot, qroot);
        System.out.println(result);

    }

     // Definition for a binary tree node.
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

        public String toString() {
            return String.valueOf(val);
        }
    }

    /**
     * Approach   : Recursive DFS
     * recursive dfs on both trees at the same time; 
     * Time complexity : O(N), where N is a number of nodes in the tree, since one visits each node exactly once.
     * Space complexity : O(log⁡(N)) in the best case of completely balanced tree and O(N) in the worst case of completely unbalanced tree, to keep a recursion stack
     */
    public static class Solution1 {
        public static boolean isSameTree(TreeNode p, TreeNode q) {
            if(p == null && q == null)
                return true;
            else if(p == null || q == null)
                return false;
            else if(p.val != q.val)
                return false;

            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    /**
     * Approach   : Iterative BFS
     * iterative bfs compare each level of both trees
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution2 {
        public static boolean isSameTree(TreeNode p, TreeNode q) {
            Deque<TreeNode> pNodes= new LinkedList<>();
            Deque<TreeNode> qNodes = new LinkedList<>();
            pNodes.offer(p);
            qNodes.offer(q);
            while(pNodes.size() != 0  || qNodes.size() != 0) {
                //System.out.println("pParentDeque.size(): " + pParentDeque.size() + ", qParentDeque.size(): " + qParentDeque.size());
                //System.out.println("pParentDeque: " + pParentDeque + ", qParentDeque: " + qParentDeque);
                if(pNodes.size() != qNodes.size())
                    return false;

                TreeNode pTreeNode = pNodes.poll();
                TreeNode qTreeNode = qNodes.poll();
                if(pTreeNode == null && qTreeNode == null)
                    continue;
                else if(pTreeNode == null || qTreeNode == null)
                    return false;
                else if(pTreeNode.val != qTreeNode.val) {
                    return false;
                }
                pNodes.offer(pTreeNode.left);
                pNodes.offer(pTreeNode.right);
                qNodes.offer(qTreeNode.left);
                qNodes.offer(qTreeNode.right);
            }
            return true;
        }
    }
}
