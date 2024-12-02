package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(Solution1.longestPalindrome("babad"));
        System.out.println(Solution1.longestPalindrome("cbbd"));
    }
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
}
