package in.rgukt.r081247.java.interviewprep.codingdsalgooops.binary;

public class ReverseBits {
    /* Approach:
     * Complexity : Time: O(n) ; Space: O(1)
     */
    public class Solution1 {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int reverse = 0;
            for(int i = 0; i < 32; i++) {
                reverse = reverse << 1;
                reverse = reverse | (n & 1);
                n = n >>> 1;
            }
            return reverse;
        }
    }
}
