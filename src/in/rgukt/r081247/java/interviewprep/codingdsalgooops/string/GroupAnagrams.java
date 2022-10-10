package in.rgukt.r081247.java.interviewprep.codingdsalgooops.string;

import java.util.*;

public class GroupAnagrams {


    /**
     * Approach   : Categorize by Count
     * Complexity : Time: O(nk) ; Space: O(nk)
     *  k: max length all strings
     */
    public static class Solution4 {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> res = new HashMap<>();
            int[] count = new int[26];
            for(String str: strs) {
                Arrays.fill(count, 0);
                char[] chars = str.toCharArray();
                for(char c: chars)
                    count[c - 'a']++;
                StringBuilder sb = new StringBuilder();
                for(int i: count) {
                    sb.append('#');
                    sb.append(i);
                }
                String key = sb.toString();
                if(!res.containsKey(key))
                    res.put(key, new ArrayList<>());
                res.get(key).add(str);
            }
            return new ArrayList(res.values());
        }
    }

    /**
     * Approach   : Categorize by Sorted String
     * Complexity : Time: O(nklogk) ; Space: O(nk)
     *  k: max length all strings
     */
    public static class Solution3 {
        public List<List<String>> groupAnagrams(String[] strs) {
           Map<String, List<String>> res = new HashMap<>();
           for(String str: strs) {
               char[] chars = str.toCharArray();
               Arrays.sort(chars);
               String sortedString = String.valueOf(chars);
               if(!res.containsKey(sortedString))
                   res.put(sortedString, new ArrayList<>());
              res.get(sortedString).add(str);
           }
            return new ArrayList(res.values());
        }
    }

    /**
     * Approach   :
     * Complexity : Time: O(n^2k) ; Space: O(nk)
     *  k: max length all strings
     */
    public static class Solution1 {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> list = new LinkedList<>();
            for(int i = 0; i < strs.length; i++) {
                boolean matches = false;
                for(List<String> anagrams: list) {
                    if(isAnagram(anagrams.get(0), strs[i])) {
                        anagrams.add(strs[i]);
                        matches = true;
                        break;
                    }
                }
                if(!matches) {
                    List<String> newList = new LinkedList<>();
                    newList.add(strs[i]);
                    list.add(newList);
                }
            }
            return list;
        }

        public boolean isAnagram(String s, String t) {
            if(s.length() != t.length())
                return false;
            int[] c1 = new int[26];
            int[] c2 = new int[26];
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
