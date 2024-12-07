package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
       /* System.out.println(Solution1.longestPalindrome("babad"));
        System.out.println(Solution1.longestPalindrome("cbbd"));*/

        System.out.println(Solution2.longestPalindrome("babad"));
        System.out.println(Solution2.longestPalindrome("cbbd"));
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(n^2) ; Space: O(n^2)
     */
    public static class Solution1 {
        public static String longestPalindrome(String s) {
            boolean[][] lps = new boolean[s.length()][s.length()];
            int length = -1;
            int index = -1;
            for(int i=0; i<s.length(); i++) {
               for(int j=i; j < s.length(); j++) {
                   if(i == 0) {
                       lps[i][j] = true;
                   } else if(i == 1 && s.charAt(j) == s.charAt(j-i)) {
                       lps[i][j] = true;
                   } else {
                       if((s.charAt(j) == s.charAt(j-i)) && lps[i-2][j-1])
                           lps[i][j] = true;
                   }
                   if(lps[i][j] && length < i+1) {
                       length = i + 1;
                       index = j - i;
                   }
               }
            }
           return s.substring(index, index + length);
        }
    };

    /**
     * Approach   : foreach char in str, consider it were the middle, consider if pali was odd or even;
     * Complexity : Time: O(n^2) ; Space: O(1)
     */
    public static class Solution2 {
        public static String longestPalindrome(String s) {
            int length = -1;
            String ret = null;
            for(int i = 0; i < s.length();i++) {
                // odd palindrome
                int j = i-1;
                int k = i+1;
                while(j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                    j--;
                    k++;
                }
                if(k-j-1 > length) {
                    length = k-j-1;
                    ret = s.substring(j+1, j+1+length);
                }

                // even palindrome
                int m = i-1;
                int n = i;
                while(m >= 0 && n < s.length() && s.charAt(m) == s.charAt(n)) {
                    m--;
                    n++;
                }
                if(n-m-1 > length) {
                    length = n-m-1;
                    ret = s.substring(m+1, m+1+length);
                }
            }
            return ret;
        }
    };
}
