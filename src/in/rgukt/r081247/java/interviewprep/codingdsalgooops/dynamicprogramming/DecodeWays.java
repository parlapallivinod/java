package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DecodeWays {

    public static void main() {
        System.out.println(new Solution2().numDecodings("2020"));
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution1 {

        public int numDecodings(String s) {
            Set<String> set = Set.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                    "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                    "21", "22", "23", "24", "25", "26");

            int[] count = new int[s.length()+1];
            count[0] = 1;
            if(set.contains(s.substring(0, 1)))
                count[1] = 1;
            else
                return 0;

            for(int i=1;i<s.length();i++) {
                if(set.contains(s.substring(i, i+1)))
                    count[i+1] = count[i];
                else
                    count[i+1] = 0;

                if(set.contains(s.substring(i-1, i+1)))
                    count[i+1] += count[i-1];

                if(count[i+1] == 0) {
                    System.out.println(Arrays.toString(count));
                    return 0;
                }
            }
            System.out.println(Arrays.toString(count));
            return count[count.length-1];
        }
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution2 {

        public int numDecodings(String s) {

            int count0 = 1;
            int count1 = 0;
            Integer val = 0;
            if(s.charAt(0) >= '1' && s.charAt(0) <= '9')
                count1 = 1;
            else
                return 0;

            for(int i=1;i<s.length();i++) {
                int temp = count1;
                if(s.charAt(i) >= '1' && s.charAt(i) <= '9')
                    count1 = count1;
                else
                    count1 = 0;

                val = Integer.valueOf(s.substring(i-1, i+1));
                if(val >=10 && val <= 26)
                    count1 += count0;

                if(count1 == 0) {
                    return 0;
                }
                count0 = temp;
            }
            return count1;
        }
    }
}
