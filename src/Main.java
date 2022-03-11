import in.rgukt.r081247.java.datastructure.list.Dequeue;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        LocalDate startDate = LocalDate.of(2015, 2, 20);
        LocalDate endDate = LocalDate.of(2017, 1, 15);

        Period period = Period.between(startDate, endDate);
        System.out.println("Years:" + period.getYears() +
                " months:" + period.getMonths() +
                " days:"+ period.getDays() + "Negative: " + period.isNegative());
    }

    class static Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int overlapps = 0;
            for (int i=0;i<intervals.length;i++) {
                if (checkOverlap(intervals[i], newInterval))
                    overlapps++;
            }
            int[][] newIntervals = new int[intervals.length + 1 - overlapps][2];
            int newIntervalIndex = 0;
            int i;
            for (i=0;i<intervals.length;i++) {
                if (checkOverlap(intervals[i], newInterval)) {
                    newInterval = overlap(intervals[i], newInterval);
                }
                 else if (newInterval[0] < intervals[i][0]) {
                    newIntervals[newIntervalIndex][0] = newInterval[0];
                    newIntervals[newIntervalIndex][1] = newInterval[1];
                    newIntervalIndex++;
                } else {
                    newIntervals[newIntervalIndex][0] = intervals[i][0];
                    newIntervals[newIntervalIndex][1] = intervals[i][1];
                    newIntervalIndex++;
                }
            }

            if (newInterval[0] < intervals[newIntervalIndex][0]) {
            newIntervals[newIntervalIndex][0] = newInterval[0];
            newIntervals[newIntervalIndex][1] = newInterval[1];
            newIntervalIndex++;

            return newIntervals;

        }

        public boolean checkOverlap(int[] source, int[] target) {
            if (target[0] <= source[1] && target[1] >= source[0])
                return true;
            else
                return false;
        }

        public int[] overlap(int[] source, int[] target) {
            if (!checkOverlap(source, target))
                throw new IllegalArgumentException("intervals don't overlap");

            return new int[] {source[0] <= target[0]?source[0]:target[0], source[1] >= target[1]?source[1]:target[1]};
        }
    }
}
