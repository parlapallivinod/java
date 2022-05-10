package in.rgukt.r081247.java.interviewprep.codingdsalgooops.binary;

public class Numberof1Bits {
    /* Approach: Iterate the bits of an unsigned integer from right by using right shift, and at each iteration check the parity of the first bit from right.
     * Complexity : Time: O(1)  ; Space: O(1)
     */
    public class Solution1 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int ones = 0;
            while (n != 0) {
                ones += n & 1;
                n = n >>> 1;
            }
            return ones;
        }
    }

    /* Approach: More optimized solution, no. of iteration will be equal to no. of set bits
     * Complexity : Time: O(1) ; Space: O(1)
     */
    public class Solution2 {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int ones = 0;
            while (n != 0) {
                ones += 1;
                n = n & (n - 1);
            }
            return ones;
        }
    }
}