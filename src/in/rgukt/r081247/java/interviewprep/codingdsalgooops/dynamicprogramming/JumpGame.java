package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.*;

public class JumpGame {
    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        System.out.println(Solution3.canJump(new int[]{1}));
    }
    /**
     * Approach   : Dynamic Programming
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution1 {
        public static boolean canJump(int[] nums) {
            boolean[] reachable = new boolean[nums.length];
            int[] steps = new int[nums.length];
            reachable[0] = true;
            steps[0] = nums[0];
            for(int i = 1;i < nums.length; i++) {
                if(steps[i-1] > 0 && reachable[i-1])
                    reachable[i] = true;
                else
                    reachable[i] = false;
                steps[i] = Math.max(steps[i-1]-1, nums[i]);
            }
            return reachable[nums.length-1];
        }
    }

    /**
     * Approach   : Greedy
     * Complexity : Time: O(nlogn) ; Space: O(n)
     */
    public static class Solution2 {
        public static  boolean canJump(int[] nums) {
            boolean[] visited = new boolean[nums.length];
            Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Map.Entry<Integer,Integer>::getKey).reversed());
            queue.offer(new AbstractMap.SimpleEntry(nums[0], 0));
            visited[0] = true;
            while(!queue.isEmpty()) {
                Map.Entry<Integer, Integer> entry = queue.poll();
                System.out.println(entry);
                if(entry.getKey() >= nums.length-1)
                    return true;
                for(int i=entry.getValue()+1;i<=entry.getKey();i++) {
                    if(visited[i])
                        continue;

                    if(queue.isEmpty() || queue.peek().getKey() < nums[i] + i)
                        queue.offer(new AbstractMap.SimpleEntry(nums[i]+i, i));
                    visited[i] = true;
                }
            }
            return false;
        }
    }

    /**
     * Approach   : Recursive
     * Complexity : Time: O(n^2) ; Space: O(n)
     */
    public static class Solution3 {
        public static  boolean canJump(int[] nums) {
           boolean[] nonvisitable = new boolean[nums.length];
           return canJump(nums, 0, nonvisitable);
        }

        public static boolean canJump(int[] nums, int i, boolean[] nonvisitable) {
            System.out.println("i: " + i + ", nonvisitable: " + Arrays.toString(nonvisitable));
            if(nonvisitable[i])
                return false;
            if(nums[i] + i >= nums.length-1)
                return true;

            for(int j = i+1;j<=nums[i]+i;j++) {
                boolean result = canJump(nums, j, nonvisitable);
                if(result)
                    return true;
            }

            nonvisitable[i] = true;
            return false;
        }
    };

    /**
     * Approach   : Recursive
     * Complexity : Time: O(n^2) ; Space: O(n)
     */
    public static class Solution4 {
        public static  boolean canJump(int[] nums) {
            boolean[] nonvisitable = new boolean[nums.length];
            return canJump(nums, 0, nonvisitable);
        }

        public static boolean canJump(int[] nums, int i, boolean[] nonvisitable) {
            System.out.println("i: " + i + ", nonvisitable: " + Arrays.toString(nonvisitable));
            if(nonvisitable[i])
                return false;
            if(nums[i] + i >= nums.length-1)
                return true;

            for(int j = nums[i]+i;j>=i+1;j--) {
                boolean result = canJump(nums, j, nonvisitable);
                if(result)
                    return true;
            }

            nonvisitable[i] = true;
            return false;
        }
    };

    /**
     * Approach   : Greedy
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution5 {
        public static  boolean canJump(int[] nums) {
            int goal = nums.length-1;
            for(int i = goal-1;i >=0;i--) {
                if(nums[i]+i >= goal)
                    goal = i;
            }
            return goal == 0? true: false;
        }
    };
}
