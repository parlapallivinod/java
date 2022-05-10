package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

public class ProductofArrayExceptSelf {

    /**
     * Approach: Prefix & Suffix Products
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution1 {
        public int[] productExceptSelf(int[] nums) {
            int[] pref = new int[nums.length];
            int[] suf = new int[nums.length];
            int[] answer = new int[nums.length];

            pref[0] = nums[0];
            for(int i=1; i<nums.length; i++) {
                pref[i] = pref[i-1] * nums[i];
            }

            suf[nums.length-1] = nums[nums.length-1];
            for(int i=nums.length-2; i>0; i--) {
                suf[i] = suf[i+1] * nums[i];
            }

            for(int i=0;i<nums.length;i++) {
                if (i == 0) {
                    answer[i] = suf[i+1];
                } else if (i == nums.length-1) {
                    answer[i] = pref[i-1];
                } else {
                    answer[i] = pref[i-1] * suf[i+1];
                }
            }

            return answer;
        }
    }

    /**
     * Approach: Space Optimized Prefix & Suffix Products
     * Complexity : Time: O(n) ; Space: O(1) (Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
     */
    class Solution2 {
        public int[] productExceptSelf(int[] nums) {
            int[] answer = new int[nums.length];

            answer[nums.length-1] = nums[nums.length-1];
            for(int i=nums.length-2; i>0; i--) {
                answer[i] = answer[i+1] * nums[i];
            }

            nums[0] = nums[0];
            for(int i=1; i<nums.length; i++) {
                nums[i] = nums[i-1] * nums[i];
            }

            for(int i=0;i<nums.length;i++) {
                if (i == 0) {
                    answer[i] = answer[i+1];
                } else if (i == nums.length-1) {
                    answer[i] = nums[i-1];
                } else {
                    answer[i] = nums[i-1] * answer[i+1];
                }
            }

            return answer;
        }
    }

    /**
     * Approach: Space Optimized Prefix & Suffix Products
     * Complexity : Time: O(n) ; Space: O(1) (Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
     */
    class Solution3 {
        public int[] productExceptSelf(int[] nums) {
            int[] answer = new int[nums.length];
            for (int i=0; i<nums.length; i++) {
                answer[i] = 1;
            }

            int pref = 1;
            int suf = 1;
            int len = nums.length;
            for(int i=0;i<len;i++) {
                answer[i] *= pref;
                pref *= nums[i];
                answer[len-i-1] *= suf;
                suf *= nums[len-i-1];
            }

            return answer;
        }
    }
}
