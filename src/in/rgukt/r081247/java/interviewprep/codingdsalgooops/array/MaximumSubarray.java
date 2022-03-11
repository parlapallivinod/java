package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

class MaximumSubarray {
    // Brute Force Time: O(n2) Space: O(1)
    static class Solution1 {
        public static int findMaximumSum(int[] nums) {
            int maxSum = Integer.MIN_VALUE;
            int sum = 0;

            // do for each subarray starting with `i`
            for (int i = 0; i < nums.length; i++) {
                // calculate the sum of subarray `nums[i…j]`
                sum = 0;    // reset sum
                // do for each subarray ending at `j`
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];

                    // if the current subarray sum is greater than the maximum
                    // sum calculated so far, update the maximum sum
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }
            }
            return maxSum;
        }
    }

    // DP solution Time: O(n) Space: O(n)
    static class Solution2 {
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE;
            int[] dp = new int[nums.length+1]; //dp(i) = max sum of subarray ending at (i-1)'th index
            int i = 1;

            for(int num: nums) {
                dp[i] = Math.max(dp[i-1] + num, num);
                max = Math.max(max, dp[i++]);
            }

            return max;
        }
    }

    // Divide and Conquer Time: O(nlogn) Space: (logn)
    static class Solution4 {
        // Function to find the maximum subarray sum using divide-and-conquer
        public static int findMaximumSum(int[] nums, int left, int right) {
            // If the array contains 0 or 1 element
            if (right == left) {
                return nums[left];
            }

            // Find the middle array element
            int mid = (left + right) / 2;

            // Find maximum subarray sum for the left subarray,
            // including the middle element
            int leftMax = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = mid; i >= left; i--) {
                sum += nums[i];
                if (sum > leftMax) {
                    leftMax = sum;
                }
            }

            // Find maximum subarray sum for the right subarray,
            // excluding the middle element
            int rightMax = Integer.MIN_VALUE;
            sum = 0;    // reset sum to 0
            for (int i = mid + 1; i <= right; i++) {
                sum += nums[i];
                if (sum > rightMax) {
                    rightMax = sum;
                }
            }

            // Recursively find the maximum subarray sum for the left
            // and right subarray, and take maximum
            int maxLeftRight = Integer.max(findMaximumSum(nums, left, mid),
                    findMaximumSum(nums, mid + 1, right));

            // return the maximum of the three
            return Integer.max(maxLeftRight, leftMax + rightMax);
        }

        // Wrapper over findMaximumSum() function
        public static int findMaximumSum(int[] nums) {
            // base case
            if (nums == null || nums.length == 0) {
                return 0;
            }

            return findMaximumSum(nums, 0, nums.length - 1);
        }

        public static void main(String[] args) {
            int[] nums = {2, -4, 1, 9, -6, 7, -3};

            System.out.println("The maximum sum of the subarray is " +
                    findMaximumSum(nums));
        }
    }

    //Kadane's Algorithm (Optimization of Above DP solution): Since we only need DP[i-1] to calculate DP[i]. There is no need of storing all values, we can get our solution done by one variable as well.
    // Time: O(n) Space: O(1)
    static class Solution3 {
        public int maxSubArray(int[] nums) {
            int curr = 0;
            int max = Integer.MIN_VALUE;

            for(int num: nums) {
                // Same as DP[i] = Math.max(DP[i-1] + num, num);
                curr = Math.max(curr + num, num);
                max = Math.max(max, curr);
            }

            return max;
        }
    }



    public int maxSubArray(int[] nums) {
        int bestSum = Integer.MIN_VALUE;
        int bestSumStartIndex = 0;
        int bestSumEndIndex = 0;
        int currentSum = 0;
        int currentSumStartIndex = 0;
        int currentSumEndIndex = 0;
        for(int i=0;i<nums.length;i++) {
            if (nums[i] >= currentSum + nums[i]) {
                currentSum = nums[i];
                currentSumStartIndex = currentSumEndIndex = i;
            } else {
                currentSum += nums[i];
                currentSumEndIndex = i;
            }

            if (currentSum > bestSum) {
                bestSum = currentSum;
                bestSumStartIndex = currentSumStartIndex;
                bestSumEndIndex = currentSumEndIndex;
            }
        }

        return bestSum;
    }


}
