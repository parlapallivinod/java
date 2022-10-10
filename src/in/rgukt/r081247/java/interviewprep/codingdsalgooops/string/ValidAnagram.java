package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    /**
     * Approach   : Sorting
     * Complexity : Time: O(nlogn) ; Space: O(1)
     */
    public static class Solution3 {
        public boolean isAnagram(String s, String t) {
           char[] sa = s.toCharArray();
           char[] st = t.toCharArray();
           Arrays.sort(sa);
           Arrays.sort(st);
           return Arrays.equals(sa, st);
        }
    }

    /**
     * Approach   : HashMap
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution2 {
        public boolean isAnagram(String s, String t) {
            Map<Character, Integer> map = new HashMap<>();
            if(s.length() != t.length())
                return false;
            for(int i=0;i<s.length();i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
                map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
            }
            for(int v : map.values())
                if(v != 0)
                    return false;
            return true;
        }
    }

    /**
     * Approach   : Array
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution1 {
        public boolean isAnagram(String s, String t) {
            int[] c1 = new int[26];
            int[] c2 = new int[26];
            if(s.length() != t.length())
                return false;
            for(int i=0;i<s.length();i++) {
                c1[s.charAt(i) - 'a']++;
                c2[t.charAt(i) - 'a']++;
            }
            for(int i=0;i<26;i++)
                if(c1[i] != c2[i])
                    return false;
            return true;
        }
    }
}
