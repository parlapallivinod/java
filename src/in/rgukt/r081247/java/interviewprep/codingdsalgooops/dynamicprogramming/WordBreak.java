package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordBreak {

    public static void main(String[] args) {
        /*
        String s = "leetcode";
        List<String> wordDict = List.of("leet","code");
         */
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = List.of("a","aa","aaa","aaaa","aaaaa");
        long start = System.currentTimeMillis();
        boolean count = Solution3.wordBreak(s, wordDict);
        System.out.println("time consume: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println(count);

        start = System.currentTimeMillis();
        count =Solution2.wordBreak(s, wordDict);
        System.out.println("time consume: " + (System.currentTimeMillis()- start) + "ms");
        System.out.println(count);

        start = System.currentTimeMillis();
        count =Solution1.wordBreak(s, wordDict);
        System.out.println("time consume: " + (System.currentTimeMillis()- start) + "ms");
        System.out.println(count);

    }

    /**
     * Approach   : Recursive(Brute Force)
     * Complexity : Time:O(m^height) ; Space:O(height)
     * height = length(s)/minlength(wordDict strings)
     */
    public static class Solution1 {
        public static boolean wordBreak(String s, List<String> wordDict) {
           return wordBreak(s, 0, wordDict);
        }

        private static boolean wordBreak(String s, int index, List<String> wordDict) {
            if(index >= s.length())
                return true;
            for(int i = 0; i < wordDict.size(); i++) {
                if(index + wordDict.get(i).length() > s.length())
                    continue;
                if(!s.substring(index, index + wordDict.get(i).length()).equals(wordDict.get(i)))
                    continue;
                boolean ret = wordBreak(s, index + wordDict.get(i).length(), wordDict);
                if(ret)
                    return ret;
            }
            return false;
        }
    }

    /**
     * Approach   : Recursive with Memory/Cache
     * Complexity : Time:O() ; Space:O(n)
     * n = s.length(), m = wordDict.size()
     */
    public static class Solution2 {
        private static Map<Integer, Boolean> cache;
        public static boolean wordBreak(String s, List<String> wordDict) {
            cache = new HashMap<>();
            return wordBreak(s, 0, wordDict);
        }

        private static boolean wordBreak(String s, int index, List<String> wordDict) {
            if(cache.containsKey(index))
                return cache.get(index);
            if(index >= s.length())
                return true;
            for(int i = 0; i < wordDict.size(); i++) {
                if(index + wordDict.get(i).length() > s.length())
                    continue;
                if(!s.substring(index, index + wordDict.get(i).length()).equals(wordDict.get(i)))
                    continue;
                boolean ret = wordBreak(s, index + wordDict.get(i).length(), wordDict);
                cache.put(index + wordDict.get(i).length(), ret);
                if(ret)
                    return ret;
            }
            return false;
        }
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time:O((nmn) ; Space:O(n)
     * n = s.length(), m = wordDict.size()
     */
    public static class Solution3 {
        public static boolean wordBreak(String s, List<String> wordDict) {
            boolean[] res = new boolean[s.length() + 1];
            res[0] = true;
            for(int i = 0; i < s.length(); i++) {
                for(int j = 0; j < wordDict.size(); j++) {
                    if(i + 1 < wordDict.get(j).length())
                        continue;

                    if(s.substring(i + 1 - wordDict.get(j).length(), i + 1).equals(wordDict.get(j)) && res[i + 1 - wordDict.get(j).length()]) {
                        res[i+1] = true;
                        break;
                    }
                }
            }
            return res[s.length()];
        }
    }
}
