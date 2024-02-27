package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

public class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(Solution1.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(Solution1.isPalindrome("race a car"));
        System.out.println(Solution1.isPalindrome(" "));
    }

    /**
     * Approach   : Using Stack
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution1 {
        public static boolean isPalindrome(String s) {
            int start = 0;
            int end = s.length() - 1;
            char startChar, endChar;
            while (start < end) {
                startChar = s.charAt(start);
                endChar = s.charAt(end);

                if(!Character.isLetterOrDigit(startChar))
                    start++;
                else if(!Character.isLetterOrDigit(endChar))
                    end--;
                else {
                    if(Character.toLowerCase(startChar) != Character.toLowerCase(endChar))
                        return false;

                    start++;
                    end--;
                }
            }
            return true;
        }
    }

}
