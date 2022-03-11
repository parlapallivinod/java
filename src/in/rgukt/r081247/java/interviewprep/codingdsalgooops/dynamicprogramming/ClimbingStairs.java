package in.rgukt.r081247.java.interviewprep.codingdsalgooops.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

    /**
     * Approach   : Recursion (Top Down Approach)
     * Complexity : Time: O(2^n) ; Space: O(n)
     */
    class Solution1 {
        public int climbStairs(int n) {
            if (n == 0) {
                return 0;
            }
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    /**
     * Approach   : Recursion + Memorization (Top Down Approach)
     * Complexity : Time: O(n) ; Space: O(n)
     */
    class Solution {
        public int climbStairs(int n) {
            Map<Integer, Integer> memo = new HashMap<>();
            memo.put(1, 1);
            memo.put(2, 2);
            return climbStairs(n, memo);
        }

        private int climbStairs(int n, Map<Integer, Integer> memo) {
            if (memo.containsKey(n)) {
                return memo.get(n);
            }
            memo.put(n, climbStairs(n - 1, memo) + climbStairs(n - 2, memo));
            return memo.get(n);
        }
    }

    // Approach: Dynamic Programming Time: O(n) Space: O(n)
    class Solution3 {
        public int climbStairs(int n) {
            if (n == 1)
                return 1;
            if (n == 2)
                return 2;

            int[] arr = new int[n+1];
            arr[1] = 1; // ways to climb 1 step
            arr[2] = 2; // ways to climb 2 steps
            for (int i=3;i<=n;i++) {
                arr[i] = arr[i-1] + arr[i-2];
            }
            return arr[n];
        }
    }

    /**
     * Approach   : DP + Optimization (Bottom Up Approach)
     * Complexity : Time: O(n) ; Space: O(1)
     */
    class Solution4 {
        public int climbStairs(int n) {
            if (n <= 1) {
                return 1;
            }

            int prev1 = 1;
            int prev2 = 2;

            for (int i = 3; i <= n; i++) {
                int newValue = prev1 + prev2;
                prev1 = prev2;
                prev2 = newValue;
            }

            return prev2;
        }
    }
}
