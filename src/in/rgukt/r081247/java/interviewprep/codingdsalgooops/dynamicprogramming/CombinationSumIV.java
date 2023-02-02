package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.Arrays;

public class CombinationSumIV {
    public static void main(String[] args) {
        Solution1 sol1 = new Solution1();
        int[] nums = {9};
        System.out.println(sol1.combinationSum4(nums, 3));
    }

    /**
     * Approach   : Recursion (Top Down Approach)
     * Complexity : Time: O(nums length^target) ; Space: O(target)
     */
    public static class Solution1 {
        public int combinationSum4(int[] nums, int target) {
            if (target == 0)
                return 1;
            int res = 0;
            for(int num: nums) {
                if(target >= num) {
                    res += combinationSum4(nums, target-num);
                }
            }
            return res;
        }
    }

    public static class Solution2 {
        /**
         * Approach   : Dynamic Programming (Bottom Up)
         * Complexity : Time: O(target * nums length) ; Space: O(target)
         */
        public int combinationSum4(int[] nums, int target) {
           int[] count = new int[target + 1];
           count[0] = 1;
           for(int i = 1;i <= target; i++) {
               int sum = 0;
               for(int num: nums) {
                   if(i - num >= 0)
                       sum += count[i-num];
               }
               count[i] = sum;
           }
           return count[target];
        }
    }

    /**
     * Approach   : Recursion (Top Down Approach)
     * Complexity : Time: O(nums length^target) ; Space: O(target)
     */
    public static class Solution3 {
        public int combinationSum4(int[] nums, int target) {
            int[] count = new int[target + 1];
            Arrays.fill(count, -1);
            count[0] = 1;
            return combinationSum4Rec(nums, target, count);
        }

        public int combinationSum4Rec(int[] nums, int target, int[] count) {
           if(count[target] != -1)
               return count[target];
           int res = 0;
           for(int num: nums) {
               if(target - num >= 0) {
                   if(count[target-num] == -1)
                       combinationSum4Rec(nums, target - num, count);
                   res += count[target-num];
               }
           }
           count[target] = res;
           return res;
        }
    }
}
