package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {
    /**
     * Approach   :
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            int longestSubStringLength = 0;
            Map<Character, Integer> map = new HashMap<>();
            for(int right = 0, left = 0; right < s.length(); right++) {
                Character c = s.charAt(right);
                if (map.containsKey(c) && map.get(c) >= left)
                    left = map.get(c) + 1;
                longestSubStringLength = Math.max(longestSubStringLength, right - left + 1);
                map.put(c, right);
            }
            return longestSubStringLength;
        }
    }


    /**
     * Approach   : Sliding Window
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution2 {
        public int lengthOfLongestSubstring(String s) {
            int maxLen = 0;
            Set<Character> window = new HashSet<>();
            int left = 0, right = 0;
            while (right < s.length()) {
                while (window.contains(s.charAt(right))) {
                    window.remove(s.charAt(left++));
                }
                window.add(s.charAt(right++));
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }
    }
}
