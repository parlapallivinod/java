package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {5, 1, 5, 1, 2, 6, 2, 6};
        System.out.println(Solution3.rob(nums));
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution2 {
        public static int rob(int[] nums) {
            if(nums.length == 1)
                return nums[0];
            int[] max = new int[nums.length];
            max[0] = nums[0];
            max[1] = Math.max(nums[0], nums[1]);
            for(int i = 2; i < nums.length; i++) {
                max[i] = Math.max(max[i-1], max[i-2] + nums[i]);
            }
            return max[nums.length-1];
        }
    }

    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution3 {
        public static int rob(int[] nums) {
            int rob1 = 0, rob2 = 0;
            // {rob1, rob2, n, n+1, n+2, n+3, ...}
            for(int i = 0; i < nums.length; i++) {
                int temp = Math.max(nums[i] + rob1, rob2);
                rob1 = rob2;
                rob2 = temp;
            }
            return rob2;
        }
    }


    /**
     * Approach   : Recursion(Brute Force)
     * Complexity : Time: O(2^n) ; Space: O(n)
     */
    public static class Solution4 {
        public static int max = 0;
        public static int rob(int[] nums) {
            max = 0;
            Deque<Integer> indexes = new LinkedList<>();
            robRec(nums, 0, 0, indexes);
            return max;
        }
        public static void robRec(int[] nums, int index, int sum, Deque<Integer> indexes) {
            if(index  >= nums.length) {
                max = Math.max(max, sum);
                return;
            }

            if(indexes.isEmpty() || (indexes.peekLast() != index - 1)) {
                indexes.offerLast(index);
                robRec(nums, index + 1, sum + nums[index], indexes);
                indexes.pollLast();
            }

            robRec(nums, index + 1, sum, indexes);
        }
    }
}
