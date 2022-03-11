package in.rgukt.r081247.java.interviewprep.codingdsalgooops.interval;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    /*
     * Approach   :
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution1 {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> newIntervals = new ArrayList<>();

            for(int i = 0; i < intervals.length; i++) {
                if(newInterval[1] < intervals[i][0]) {
                    newIntervals.add(newInterval);
                    newIntervals.addAll(List.of(intervals).subList(i, intervals.length));
                    int[][] arr = new int[newIntervals.size()][];
                    return (int[][]) newIntervals.toArray(arr);
                } else if(newInterval[0] > intervals[i][1]) {
                    newIntervals.add(intervals[i]);
                } else {
                    newInterval = new int[]{Math.min(intervals[i][0], newInterval[0]), Math.max(intervals[i][1], newInterval[1])};
                }
            }

            newIntervals.add(newInterval);
            int[][] arr = new int[newIntervals.size()][];
            return (int[][]) newIntervals.toArray(arr);
        }
    }
}
