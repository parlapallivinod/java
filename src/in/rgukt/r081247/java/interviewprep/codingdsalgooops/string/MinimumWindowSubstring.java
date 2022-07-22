package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {

        /*
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String res = Solution1.minWindow(s, t);
        System.out.println("res: " + res);

         */


        /*
        String s = "a";
        String t = "a";
        String res = Solution1.minWindow(s, t);
        System.out.println("res: " + res);
        */
        /*
        String s = "a";
        String t = "aa";
        String res = Solution1.minWindow(s, t);
        System.out.println("res: " + res);
         */

        String s = "ADOBECODEBANC";
        String t = "";
        String res = Solution1.minWindow(s, t);
        System.out.println("res: " + res);

    }

    /**
     * Approach   : Sliding Window
     * Complexity : Time: O(s + t) ; Space: O(s + t)
     */
    public static class Solution1 {
        public static String minWindow(String s, String t) {
            if(s.length() == 0 || t.length() == 0)
                return "";

            // Dictionary which keeps a count of all the unique characters in t.
            Map<Character, Integer> dictT = new HashMap<>();
            for(int i = 0; i < t.length(); i++) {
                int count = dictT.getOrDefault(t.charAt(i), 0);
                dictT.put(t.charAt(i), count + 1);
            }

            // Number of unique characters in t, which need to be present in the desired window.
            int required = dictT.size();

            // Left and Right pointer
            int l = 0, r = 0;

            // formed is used to keep track of how many unique characters in t
            // are present in the current window in its desired frequency.
            // e.g. if t is "AABC" then the window must have tow A's, one B and one C.
            // Thus formed would be = 3 when all these conditions are met.
            int formed = 0;

            // Dictionary which keeps a count of all the unique characters in the current window.
            Map<Character, Integer> windowCount = new HashMap<Character, Integer>();

            // ans list of the form (window length, left, right)
            int[] ans = {-1, 0, 0};

            while(r < s.length()) {
                // Add one character from the right to the window
                char c = s.charAt(r);
                int count = windowCount.getOrDefault(c, 0);
                windowCount.put(c, count + 1);

                // If the frequency of the current character added equals to the
                // desired count in t then increment the formed count by 1.
                if(dictT.containsKey(c) && windowCount.get(c).intValue() == dictT.get(c).intValue())
                    formed++;

                // Try and contract the window till the point where it ceases to be 'desirable'.
                while(l <= r && formed == required) {
                    c = s.charAt(l);
                    // Save the smallest window until now
                    if(ans[0] == -1 || r - l + 1 < ans[0]) {
                        ans[0] = r - l + 1;
                        ans[1] = l;
                        ans[2] = r;
                    }

                    // The character at the position pointed by the
                    // 'Left' pointer is no longer a part of the window
                    windowCount.put(c, windowCount.get(c) - 1);
                    if(dictT.containsKey(c) && windowCount.get(c).intValue() < dictT.get(c).intValue())
                        formed--;

                    // Move the left pointer ahead, this would help to look for a new window.
                    l++;
                }

                // Keep expanding the window once we are done contracting.
                r++;
            }

            return ans[0] == -1? "": s.substring(ans[1], ans[2] + 1);
        }
    }
}
