package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {

        /*
        String s = "ABAB";
        int k = 2;
        */
        /*
        String s = "AABABBA";
        int k = 1;
        /*
        /*
        String s = "ABAA";
        int k = 0;
        */

        String s = "KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF";
        int k = 4;

        int res = Solution1.characterReplacement(s, k);
        System.out.println("res: "+ res);
    }

    /**
     * Approach   : Sliding Window Optimized
     * Complexity : Time: O(n) ; Space: O(26)
     */
    public static class Solution1 {
        public static int characterReplacement(String s, int k) {
            int[] count = new int[26];
            int result = 0;
            int maxFrequency = 0;

            int left = 0;
            for(int right = 0; right < s.length(); right++) {
                count[s.charAt(right) - 'A']++;
                maxFrequency = Math.max(maxFrequency, count[s.charAt(right) - 'A']);
                while(right - left + 1 - maxFrequency > k) {
                    count[s.charAt(left) - 'A']--;
                    left++;
                }
                result = Math.max(result, right - left + 1);
            }
            return result;
        }
    }

    /**
     * Approach   : Sliding Window Not optimized
     * Complexity : Time: O(26n) ; Space: O(n)
     */
    public static class Solution2 {
        public static int characterReplacement(String s, int k) {
            int[] count = new int[26];
            int result = 0;

            int left = 0;
            for(int right = 0; right < s.length(); right++) {
                count[s.charAt(right) - 'A']++;
                int maxCount = Arrays.stream(count).max().orElse(0);
                while(right - left + 1 - maxCount > k) {
                    count[s.charAt(left) - 'A']--;
                    left++;
                    maxCount = Arrays.stream(count).max().orElse(0);
                }
                result = Math.max(result, right - left + 1);
            }
            return result;
        }
    }
}
