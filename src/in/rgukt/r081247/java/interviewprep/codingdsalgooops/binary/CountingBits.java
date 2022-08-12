package in.rgukt.r081247.java.interviewprep.codingdsalgooops.binary;

public class CountingBits {

    /* Approach: Brute Force
     * Complexity : Time: O(nlogn)  ; Space: O(n)
     */
    class Solution1 {
        public int[] countBits(int n) {
            int[] res = new int[n+1];
            for(int i = 0; i <= n; i++) {
                int count = 0;
                int v = i;
                while(v != 0) {
                    if((v & 1) != 0)
                        count++;
                    v = v >>> 1;
                }
                res[i] = count;
            }
            return res;
        }
    }

    /* Approach: Dynamic Programming, bits(k) = bits(k >> 1) + bits(k & 1), bits of k = bits of k/2 + first bit of k
     * Complexity : Time: O(n)  ; Space: O(n)
     */
    class Solutio2 {
        public int[] countBits(int n) {
            int[] res = new int[n+1];
            res[0] = 0;
            for(int i = 1; i <= n; i++) {
                res[i] = res[i >>> 1] + (i & 1);
                //res[i] = res[i / 2] + (i % 2);
            }
            return res;
        }
    }

    /* Approach: Dynamic Programming,
     * Complexity : Time: O(n)  ; Space: O(n)
     */
    class Solutio3 {
        public int[] countBits(int n) {
            int[] res = new int[n+1];
            res[0] = 0;
            int offset = 1;
            for(int i = 1; i <= n; i++) {
                if (offset * 2 == i)
                    offset = i;
                res[i] = 1 + res[i - offset];
            }
            return res;
        }
    }
}
