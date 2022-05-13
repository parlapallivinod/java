package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * Approach   : Dynamic Programming 1D
     * Complexity : Time:O(n^2) ; Space:O(n)
     */
    public class Solution2 {
        public int lengthOfLIS(int[] nums) {
            if (nums.length == 0)
                return 0;

            Deque<Integer> longestSequence = new LinkedList<>();
            int maxLength = -1;
            int lastIndex = -1;
            int[] length = new int[nums.length];
            int[] index = new int[nums.length];
            for(int i = 0; i < nums.length; i++) {
                length[i] = 1;
                index[i] = -1;
            }

            for(int i = 0; i < nums.length; i++) {
                for(int j = i-1; j >= 0; j--) {
                    if(nums[i] > nums[j] && length[j] >= length[i]) {
                        length[i] = length[j] + 1;
                        index[i] = j;
                    }
                }
            }

            for(int i = 0; i < nums.length; i++) {
                if(length[i] > maxLength) {
                    maxLength = length[i];
                    lastIndex = i;
                }
            }

            while(lastIndex != -1) {
                longestSequence.addFirst(nums[lastIndex]);
                lastIndex = index[lastIndex];
            }

            System.out.println("list: " + longestSequence);
            return maxLength;
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

    public static int lengthOfLISDP1D(int[] nums) {
        if (nums.length == 0)
            return 0;

        Deque<Integer> longestSequence = new LinkedList<>();
        int maxLength = -1;
        int lastIndex = -1;
        int[] length = new int[nums.length];
        int[] index = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            length[i] = 1;
            index[i] = -1;
        }

        for(int i = 0; i < nums.length; i++) {
            for(int j = i-1; j >= 0; j--) {
                if(nums[i] > nums[j] && length[j] >= length[i]) {
                    length[i] = length[j] + 1;
                    index[i] = j;
                }
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(length[i] > maxLength) {
                maxLength = length[i];
                lastIndex = i;
            }
        }

        while(lastIndex != -1) {
            longestSequence.addFirst(nums[lastIndex]);
            lastIndex = index[lastIndex];
        }

        System.out.println("list: " + longestSequence);
        return maxLength;
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{10,9,2,5,3,7,101,18};
        int[] nums = new int[]{0,1,0,3,2,3};
        //int[] nums = new int[]{7,7,7,7,7,7,7};
        System.out.println("count: " + lengthOfLISDP1D(nums));
    }
}
