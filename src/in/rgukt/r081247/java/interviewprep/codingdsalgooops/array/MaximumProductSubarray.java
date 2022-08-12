package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {2,3,-2,4,-2};
        Solution2.maxProduct(nums);
    }

    /**
     * Approach: BruteForce
     * Complexity : Time: O(n2) ; Space: O(1)
     */
    public static class Solution1 {
        public static int maxProduct(int[] nums) {
            int maxProduct = nums[0];
            int start = 0;
            int end = 0;
            for(int i=0;i<nums.length;i++) {
                int product = 1;
                for(int j=i;j<nums.length;j++) {
                    product *= nums[j];
                    if(product > maxProduct) {
                        maxProduct = product;
                        start = i;
                        end = j;
                    }
                }
            }
            System.out.println("maxProduct: " + maxProduct);
            System.out.println("start: " + start);
            System.out.println("end: " + end);
            return maxProduct;
        }
    }

    /**
     * Approach: Dynamic Programming(dp: compute max and max-abs-val for each prefix subarr;)
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution2 {
        public static int maxProduct(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            int[] max = new int[nums.length];
            int[] min = new int[nums.length];
            max[0] = nums[0];
            min[0] = nums[0];
            int result = nums[0];
            int prevMax;
            for(int i=1;i<nums.length;i++) {
                max[i] = Math.max(min[i-1] * nums[i], Math.max(max[i-1] * nums[i], nums[i]));
                min[i] = Math.min(min[i-1] * nums[i], Math.min(max[i-1] * nums[i], nums[i]));
                if(max[i] > result)
                    result = max[i];
            }
            System.out.println("maxProduct: " + result);
            return result;
        }
    }

    /**
     * Approach: Dynamic Programming Space Optimized(dp: compute max and max-abs-val for each prefix subarr)
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public static class Solution3 {
        public static int maxProduct(int[] nums) {
            int maxProduct = nums[0];
            int minProduct = nums[0];
            int result = nums[0];
            int prevMax;
            for(int i=1;i<nums.length;i++) {
                prevMax = maxProduct;
                maxProduct = Math.max(minProduct * nums[i], Math.max(maxProduct * nums[i], nums[i]));
                minProduct = Math.min(minProduct * nums[i], Math.min(prevMax * nums[i], nums[i]));
                if(maxProduct > result)
                    result = maxProduct;
            }
            System.out.println("maxProduct: " + result);
            return result;
        }
    }
}
