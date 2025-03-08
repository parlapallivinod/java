package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(Solution1.uniquePaths(7, 3));
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(mn) ; Space: O(mn)
     */
    static class Solution1 {
        public static int uniquePaths(int m, int n) {
            int[][] count = new int[m][n];

            for(int i=0;i<n;i++){
                count[0][i] = 1;
            }

            for(int i=0;i<m;i++) {
                count[i][0] = 1;
            }

            for(int i=1;i<m;i++) {
                for(int j=1;j<n;j++) {
                    count[i][j] = count[i][j-1] + count[i-1][j];
                }
            }

            return count[m-1][n-1];
        }
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(mn) ; Space: O(n)
     */
    static class Solution2 {
        public static int uniquePaths(int m, int n) {
            int[] ol = new int[n];

            for(int i=0;i<n;i++){
                ol[i] = 1;
            }

            for(int i=1;i<m;i++) {
                int[] ne = new int[n];
                ne[0] = 1;
                for(int j=1;j<n;j++) {
                    ne[j] = ne[j-1] + ol[j];
                }
                ol = ne;
            }

            return ol[ol.length-1];
        }
    }
}
