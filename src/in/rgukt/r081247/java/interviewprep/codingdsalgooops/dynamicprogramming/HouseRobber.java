package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {5, 1, 5, 1, 2, 6, 2, 6};
        System.out.println(Solution1.rob(nums));
    }

    public static class Solution1 {
        public static int rob(int[] nums) {
            int[] without = new int[nums.length];
            int[] with = new int[nums.length];
            without[0] = 0;
            with[0] = nums[0];
            for(int i = 1; i < nums.length; i++) {
                without[i] = Math.max(with[i-1], without[i-1]);
                with[i] = without[i-1] + nums[i];
            }
            /*
            Deque<Integer> list = new LinkedList<Integer>();
            int max = Math.max(with[nums.length-1], without[nums.length-1]);
            for(int i=nums.length-1;i>=0;i--) {
                if(with[i] == max) {
                    list.addFirst(nums[i]);
                    max -= nums[i];
                }
            }
            System.out.println(list);
             */
            return Math.max(with[nums.length-1], without[nums.length-1]);
        }
    }

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
}
