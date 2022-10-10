package in.rgukt.r081247.java.interviewprep.codingdsalgooops.interval;


import java.util.*;

public class NonOverlappingIntervals {
    public static void main(String[] args) {
        int[][] intervals1 = {{58, 95}, {66, 98}, {82, 97}, {95, 99}};
        int[][] intervals2 = {{1, 2}, {1, 2}, {1, 2}};
        Arrays.sort(intervals2, (int[] a1, int[] a2)-> (a1[0] - a2[0] != 0) ? (a1[0] - a2[0]) : (a1[1] - a2[1]));
        for(int[] num: intervals2) {
            System.out.print(Arrays.toString(num) + ", ");
        }
        System.out.println();
        System.out.println(Solution3.eraseOverlapIntervals(intervals2));


    }

    /*
     * Approach   : Greedy
     * Complexity : Time: O(nlogn) ; Space: O(n))
     */
    public static class Solution4 {
        public static int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, (int[] a1, int[] a2)-> a1[0] - a2[0]);
            int count = 0;
            int left = 0;
            int right = 1;
            while(right < intervals.length) {
                if(intervals[left][1] <= intervals[right][0]) {
                    left = right;
                    right += 1;
                } else if(intervals[left][1] < intervals[right][1]) {
                    count++;
                    right++;
                }
                else if(intervals[left][1] >= intervals[right][1]) {
                    count++;
                    left = right;
                    right++;
                }
            }
            return count;
        }
    }

    /*
     * Approach   : Dynamic Programming
     * Complexity : Time: O(n^2) ; Space: O(n))
     */
    public static class Solution3 {
        public static int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, (int[] a1, int[] a2)-> (a1[0] - a2[0] != 0) ? (a1[0] - a2[0]) : (a1[1] - a2[1]));
            int[] counts = new int[intervals.length];
            counts[0] = 1;
            for(int i = 1; i < intervals.length; i++) {
                int count = 0;
                int j = i - 1;
                while(j >= 0) {
                    if(intervals[i][0] < intervals[j][1])
                        j--;
                }
                if(j < 0)
                    count = 0;
                else
                    count = counts[j];
                count++;
                counts[i] = Math.max(counts[i-1], count);
            }
            return intervals.length - counts[counts.length-1];
        }
    }

    /*
     * Approach   : Recursion
     * Complexity : Time: O(2^n) ; Space: O(n))
     */
    public static class Solution2 {
        public static int mcount = 0;
        public static int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, (int[] a1, int[] a2)-> (a1[0] - a2[0] != 0) ? (a1[0] - a2[0]) : (a1[1] - a2[1]));
            Deque<Integer> stack = new LinkedList<>();
            eraseOverlapIntervals(intervals, 0, stack);
            return intervals.length - mcount;
        }
        public static void eraseOverlapIntervals(int[][] intervals, int i, Deque<Integer> stack) {
            if(i >= intervals.length) {
                mcount = Math.max(mcount, stack.size());
                return;
            }
            if(stack.isEmpty()) {
                stack.push(i);
            } else {
                int prev = stack.peek();
                if(intervals[i][0] >= intervals[prev][1]) {
                    stack.push(i);
                }
            }
            if(!stack.isEmpty() && stack.peek() == i) {
                eraseOverlapIntervals(intervals, i + 1, stack);
                stack.pop();
            }
            eraseOverlapIntervals(intervals, i+1, stack);
        }
    }

    /*
     * Approach   : Recursion
     * Complexity : Time: O(2^n) ; Space: O(n))
     */
    public static class Solution1 {
        public static int mcount = 0;
        public static int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, (int[] a1, int[] a2)-> (a1[0] - a2[0] != 0) ? (a1[0] - a2[0]) : (a1[1] - a2[1]));
            Deque<Integer> stack = new LinkedList<>();
            eraseOverlapIntervals(intervals, 0, stack);
            return intervals.length - mcount;
        }
        public static void eraseOverlapIntervals(int[][] intervals, int i, Deque<Integer> stack) {
            if(i >= intervals.length) {
                mcount = Math.max(mcount, stack.size());
                return;
            }
            if(stack.isEmpty()) {
                stack.push(i);
            } else {
                int prev = stack.peek();
                if(intervals[i][0] >= intervals[prev][1]) {
                    stack.push(i);
                }
            }
            eraseOverlapIntervals(intervals, i+1, stack);
            if(!stack.isEmpty() && stack.peek() == i)
                stack.pop();

            eraseOverlapIntervals(intervals, i+1, stack);
        }
    }

}
