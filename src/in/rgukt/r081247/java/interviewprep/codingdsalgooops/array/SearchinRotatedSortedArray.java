package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

public class SearchinRotatedSortedArray {

    /**
     * Approach: Binary Search(first find max element index, then apply binary search for 2 sorted arrays)
     * Complexity : Time: O(2logn) ; Space: O(logn)
     */
    static class Solution2 {
        public static void main(String[] args) {

        }

        public int search(int[] nums, int target) {
           int maxIndex = findMaxIndex(nums, 0, nums.length-1);
           //int maxIndex = findMaxIndexLoop(nums);
           int index = binarysearch(nums, 0, maxIndex, target);
           if(index == -1)
               index = binarysearch(nums, maxIndex+1, nums.length-1, target);
           return index;
        }

        public int findMaxIndex(int[] nums, int start, int end) {
            if(start == end)
                return start;
            else if(start+1 == end) {
                if(nums[start] > nums[end])
                    return start;
                else
                    return end;
            }

            int mid = start + (end-start)/2;
            if(nums[mid] > nums[start])
                return findMaxIndex(nums, mid, end);
            else
                return findMaxIndex(nums, start, mid-1);
        }

        public int findMaxIndexLoop(int[] nums) {
           int start = 0;
           int end = nums.length-1;
           while(start<=end) {
               if(start == end)
                   return start;
               else if(start == end-1) {
                   if(nums[start] > nums[end])
                       return start;
                   else
                       return end;
               }

               int mid = start + (end-start)/2;
               if(nums[mid] > nums[start])
                   start = mid;
               else
                   end = mid-1;
           }
           return -1;
        }

        public int binarysearch(int[] nums, int start, int end, int num) {
            if(start > end)
                return -1;
            int mid = start + (end-start)/2;

            if(nums[mid] == num)
                return mid;
            else if(num < nums[mid])
                return binarysearch(nums, start, mid-1, num);
            else
                return binarysearch(nums, mid+1, end, num);
        }
    }

    /**
     * Approach: Binary Search customized
     * Complexity : Time: O(logn) ; Space: O(1)
     */
    static class Solution3 {
        public static void main(String[] args) {

        }

        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length -1;
            while(left <= right) {
                int mid = left + (right-left)/2;
                if(nums[mid] == target)
                    return mid;
                // left sorted array
                if(nums[left] <= nums[mid]) {
                    if(target > nums[mid] || target < nums[left])
                        left = mid + 1;
                    else
                        right = mid - 1;
                    // right sorted array
                } else {
                    if(target < nums[mid] || target > nums[right])
                        right = mid -1;
                    else
                        left = mid + 1;
                }
            }
            return -1;
        }
    }

}
