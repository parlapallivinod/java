package in.rgukt.r081247.java.interviewprep.codingdsalgooops.binary;

public class MissingNumber {
    /* Approach: Sum Formula
     * Complexity : Time: O(n) ; Space: O(1)
     * compute expected sum - real sum
     * Issue :- It can cause the Overflow issue because in this we are adding the element, So if the array element is very large then it can cause Integer Overflow.
     */
    class Solution1 {
        public int missingNumber(int[] nums) {
            int n = nums.length;
            int sum = (n * (n + 1)) / 2;
            for(int i: nums)
                sum -= i;
            return sum;
        }
    }

    /* Approach: XOR Bit Manipulation :- In this we take the XOR of given array and the first n natural number.
     * Complexity : Time: O(n) ; Space: O(1)
     * xor n with each index and value;
     */
    class Solution2 {
        public int missingNumber(int[] nums) {
            int nx = 0;
            for(int i = 0; i <= nums.length; i++)
                nx ^= i;
            for(int i: nums)
                nx ^= i;
            return nx;
        }
    }
}
