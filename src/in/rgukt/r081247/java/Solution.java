package in.rgukt.r081247.java;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int overlaps = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (checkOverlap(intervals[i], newInterval))
                overlaps++;
        }

        int[][] newIntervals = new int[intervals.length + 1 - overlaps][2];
        int newIntervalIndex = 0;
        boolean newIntervalDone = false;
        for (int i = 0; i < intervals.length; i++) {
            if (checkOverlap(intervals[i], newInterval)) {
                newInterval = mergeIntervals(intervals[i], newInterval);
                continue;
            } else if (newInterval[0] < intervals[i][0] && (!newIntervalDone)) {
                newIntervals[newIntervalIndex][0] = newInterval[0];
                newIntervals[newIntervalIndex][1] = newInterval[1];
                newIntervalIndex++;
                newIntervalDone = true;
            }
            newIntervals[newIntervalIndex][0] = intervals[i][0];
            newIntervals[newIntervalIndex][1] = intervals[i][1];
            newIntervalIndex++;

        }

        if (!newIntervalDone) {
            newIntervals[newIntervalIndex][0] = newInterval[0];
            newIntervals[newIntervalIndex][1] = newInterval[1];
            newIntervalIndex++;
            newIntervalDone = true;
        }

        return newIntervals;
    }

    public boolean checkOverlap(int[] source, int[] target){
        if (target[0] <= source[1] && target[1] >= source[0])
            return true;
        else
            return false;
    }

    public int[] mergeIntervals( int[] source, int[] target){
        if (!checkOverlap(source, target))
            throw new IllegalArgumentException("intervals don't overlap");
        return new int[]{source[0] <= target[0] ? source[0] : target[0], source[1] >= target[1] ? source[1] : target[1]};
    }
}