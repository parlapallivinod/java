package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

public class FindMinimuminRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(Solution1.findMin(nums));
    }

    /**
     * Approach: Binary Search
     * Complexity : Time: O(logn) ; Space: O(1)
     */
    public static class Solution1 {
        public static int findMin(int[] nums) {
            int result = -1;
            int left = 0;
            int right = nums.length - 1;
            while(left <= right) {
                if(nums[left] <= nums[right]) {
                    result = nums[left];
                    break;
                }
                int mid = left + (right - left) / 2;
                if(nums[mid] < nums[right])
                    right = mid;
                else
                    left = mid  + 1;
            }
            return result;
        }
    }

    /**
     * Approach: Binary Search
     * Complexity : Time: O(logn) ; Space: O(1)
     */
    public static class Solution2 {
        public static int findMin(int[] nums) {
            if(nums.length == 0)
                return -1;
            if(nums.length == 1)
                return nums[0];

            int left = 0;
            int right = nums.length - 1;
            int mid;
            while(left < right) {
                mid = left + (right - left) / 2;
                if(mid > 0 && nums[mid] < nums[mid-1])
                    return nums[mid];
                else if(nums[left] <= nums[mid] && nums[mid] > nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            return nums[left];
        }
    }

    /**
     * Approach: Binary Search
     * Complexity : Time: O(logn) ; Space: O(1)
     */
    public static class Solution3 {
        public static int findMin(int[] nums) {
            int result = nums[0];
            int left = 0;
            int right = nums.length - 1;
            while(left <= right) {
                if(nums[left] < nums[right]) {
                    result = Math.min(result, nums[left]);
                    break;
                }
                int mid = left + (right - left) / 2;
                result = Math.min(result, nums[mid]);
                if(nums[mid] < nums[right])
                    right = mid - 1;
                else
                    left = mid  + 1;
            }
            return result;
        }
    }

}
