package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;

public class HouseRobber2 {
    public static void main(String[] args) {
        int[] nums = {5, 1, 5, 1, 2, 6, 2, 6};
        System.out.println(Solution3.robcircle(nums));
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(2n) ; Space: O(1)
     */
    static class Solution3 {

        public static int robcircle(int[] nums) {
            if(nums.length == 1)
                return nums[nums.length-1];

            int rob1 = rob(nums, 0, nums.length-2);
            int rob2 = rob(nums, 1, nums.length-1);
            return Math.max(rob1, rob2);
        }

        public static int rob(int[] nums, int start, int end) {
            int rob0 = 0;
            int rob1 = 0;
            for(int i=start;i<=end;i++) {
                int temp = Math.max(rob0 + nums[i], rob1);
                rob0 = rob1;
                rob1 = temp;
            }
            return rob1;
        }
    }
}
