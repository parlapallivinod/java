package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;


import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int count = Solution3.longestCommonSubsequence("pmjghexybyrgzczy", "hafcdqbgncrcbihkd");
        System.out.println("time consume: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println(count);

        start = System.currentTimeMillis();
        count = Solution2.longestCommonSubsequence("pmjghexybyrgzczy", "hafcdqbgncrcbihkd");
        System.out.println("time consume: " + (System.currentTimeMillis()- start) + "ms");
        System.out.println(count);

        start = System.currentTimeMillis();
        count = Solution1.longestCommonSubsequence("pmjghexybyrgzczy", "hafcdqbgncrcbihkd");
        System.out.println("time consume: " + (System.currentTimeMillis()- start) + "ms");
        System.out.println(count);
    }

    /**
     * Approach   : Recursion
     * Complexity : Time:O(2^(n+m) ; Space:O(n+m)
     */
    public static class Solution1 {
        public static int longestCommonSubsequence(String text1, String text2) {
            return longestCommonSubsequence(text1, text2, 0, 0);
        }

        public static int longestCommonSubsequence(String text1, String text2, int i, int j) {
            if(i == text1.length() || j == text2.length())
                return 0;
            if(text1.charAt(i) == text2.charAt(j)) {
                return 1 + longestCommonSubsequence(text1, text2, i + 1, j + 1);
            } else {
                int left = longestCommonSubsequence(text1, text2, i + 1, j);
                int right = longestCommonSubsequence(text1, text2, i, j + 1);
                return Math.max(left, right);
            }
        }
    }

    /**
     * Approach   : Top-down DP
     * Complexity : Time:O(nm) ; Space:O(nm)
     */
    public static class Solution2 {
        private static Integer[][] dp;

        public static int longestCommonSubsequence(String text1, String text2) {
            dp = new Integer[text1.length()][text2.length()];
            return longestCommonSubsequence(text1, text2, 0, 0);
        }

        public static int longestCommonSubsequence(String text1, String text2, int i, int j) {
            if(i == text1.length() || j == text2.length())
                return 0;

            if(dp[i][j] != null)
                return dp[i][j];

            if(text1.charAt(i) == text2.charAt(j)) {
                return dp[i][j] = 1 + longestCommonSubsequence(text1, text2, i + 1, j + 1);
            } else {
                int left = longestCommonSubsequence(text1, text2, i + 1, j);
                int right = longestCommonSubsequence(text1, text2, i, j + 1);
                return dp[i][j] = Math.max(left, right);
            }
        }
    }

    /**
     * Approach   : Bottom-up DP
     * Complexity : Time:O(nm) ; Space:O(nm)
     */
    public static class Solution3 {
        public static int longestCommonSubsequence(String text1, String text2) {
            int[][] count = new int[text1.length() + 1][text2.length() + 1];
            for(int i = 1; i <= text1.length(); i++) {
                for(int j = 1; j <= text2.length(); j++) {
                    if(text1.charAt(i - 1) == text2.charAt(j - 1))
                        count[i][j] = 1 + count[i-1][j-1];
                    else
                        count[i][j] = Math.max(count[i][j-1], count[i-1][j]);
                }
            }
            return count[text1.length()][text2.length()];
        }
    }

    /**
     * Approach   : Dynamic Programming 2D
     * Complexity : Time:O(nm) ; Space:O(nm)
     */
    public static class Solution10 {
        public static int longestCommonSubsequence(String text1, String text2) {
            int[][] count = new int[text1.length()][text2.length()];
            for(int i = 0; i < text1.length(); i++) {
                for(int j = 0; j < text2.length(); j++) {
                    if(i == 0 && j == 0) {
                        if(text1.charAt(i) == text2.charAt(j))
                            count[i][j] = 1;
                        else
                            count[i][j] = 0;
                    } else if(i == 0) {
                        if (text1.charAt(i) == text2.charAt(j))
                            count[i][j] = 1;
                        else
                            count[i][j] = count[i][j - 1];

                    } else if(j == 0) {
                        if(text1.charAt(i) == text2.charAt(j))
                            count[i][j] = 1;
                        else
                            count[i][j] = count[i-1][j];
                    } else {
                        if(text1.charAt(i) == text2.charAt(j))
                            count[i][j] = 1 + count[i-1][j-1];
                        else
                            count[i][j] = Math.max(count[i][j-1], count[i-1][j]);
                    }
                }
            }
            return count[text1.length() - 1][text2.length() - 1];
        }
    }
    /**
     * Approach   : Dynamic Programming 2D Code Optimized
     * Complexity : Time:O(nm) ; Space:O(nm)
     */
    public static class Solution11 {
        public static int longestCommonSubsequence(String text1, String text2) {
            int[][] count = new int[text1.length()][text2.length()];
            for(int i = 0; i < text1.length(); i++) {
                for(int j = 0; j < text2.length(); j++) {
                    if(text1.charAt(i) == text2.charAt(j))
                        count[i][j] = 1 + ((i-1 >= 0 && j-1 >= 0) ? count[i-1][j-1]: 0);
                    else
                        count[i][j] = Math.max((j-1 >= 0) ? count[i][j-1]: 0, (i-1 >= 0) ? count[i-1][j]: 0);
                }
            }
            return count[text1.length() - 1][text2.length() - 1];
        }
    }

    /**
     * Approach   : Recursion
     * Complexity : Time:O(2^(n+m) ; Space:O(n+m)
     */
    public static class Solution12 {
        public static int longestCommonSubsequence(String text1, String text2) {
           if(text1.isEmpty() || text2.isEmpty())
               return 0;
           if(text1.charAt(0) == text2.charAt(0)) {
               return 1 + longestCommonSubsequence(text1.substring(1), text2.substring(1));
           } else {
               int left = longestCommonSubsequence(text1.substring(1), text2);
               int right = longestCommonSubsequence(text1, text2.substring(1));
               return Math.max(left, right);
           }
        }
    }

    /**
     * Approach   : Recursion with Cache
     * Complexity : Time:O(2^(n+m) ; Space:O(n+m)
     */
    public static class Solution13 {
        public static Map<String, Integer> count = new HashMap<>();
        public static int longestCommonSubsequence(String text1, String text2) {
            if(count.containsKey(text1+text2))
                return count.get(text1+text2);

            if(text1.isEmpty() || text2.isEmpty())
                return 0;

            if(text1.charAt(0) == text2.charAt(0)) {
                return 1 + longestCommonSubsequence(text1.substring(1), text2.substring(1));
            } else {
                int left = longestCommonSubsequence(text1.substring(1), text2);
                int right = longestCommonSubsequence(text1, text2.substring(1));
                int temp = Math.max(left, right);
                count.put(text1+text2, temp);
                return temp;
            }
        }
    }
}
