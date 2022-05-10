package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

public class LongestIncreasingSubsequence {
    /**
     * Approach   : Recursive
     * Complexity : Time: ; Space:
     */
    public class Solution1 {
        public int lengthOfLIS(int[] nums) {
            if(nums.length == 0)
                return 0;
            int count = 0;
            for(int i = 0; i < nums.length; i++) {
                int tempCount = lisRecursive(nums, i, 1);
                if(tempCount > count)
                    count = tempCount;
            }
            return count;
        }
        public int lisRecursive(int[] nums, int index, int count) {
            int maxCount = count;
            for(int i = index+1; i < nums.length; i++) {
                if(nums[i] > nums[index]) {
                    int tempCount = lisRecursive(nums, i, count + 1);
                    if(tempCount > maxCount)
                        maxCount = tempCount;
                }
            }
            return maxCount;
        }
    }

    public static int lengthOfLIS(int[] nums) {
        if(nums.length == 0)
            return 0;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            int tempCount = lisRecursive(nums, i, 1);
            if(tempCount > count)
                count = tempCount;
        }
        return count;
    }

    public static int lisRecursive(int[] nums, int index, int count) {
        int maxCount = count;
        for(int i = index+1; i < nums.length; i++) {
            if(nums[i] > nums[index]) {
                int tempCount = lisRecursive(nums, i, count + 1);
                if(tempCount > maxCount)
                    maxCount = tempCount;
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{10,9,2,5,3,7,101,18};
        //int[] nums = new int[]{0,1,0,3,2,3};
        int[] nums = new int[]{7,7,7,7,7,7,7};
        System.out.println("count: " + lengthOfLIS(nums));
    }
}
