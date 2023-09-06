package in.rgukt.r081247.java.interviewprep.codingdsalgooops.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {
    public static void main(String[] args) {
        /*
        Interval[] intervals = new Interval[] {
                new Interval(0, 30),
                new Interval(5, 10),
                new Interval(15, 20)
            };
        */
        Interval[] intervals = new Interval[] {
                new Interval(0, 30),
                new Interval(30, 40),
                new Interval(40, 60)
        };

        boolean res = Solution1.canAttendMeetings(intervals);
        System.out.println("res: " + res);
    }

    public static class Interval {
        public int start;
        public int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
    public static class Solution1 {
        /**
         * Approach: sort intervals by start time,
         * if second interval doesn’t overlap with first, then third def wont overlap with first;
         *
         * Time Complexity: O(nlogn), Space Complexity: O(n)
         */
        public static boolean canAttendMeetings(Interval[] intervals) {
            List<Interval> sortedIntervals = Arrays.stream(intervals)
                    .sorted(Comparator.comparing(Interval::getStart))
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            for(int i = 0; i < sortedIntervals.size() - 1; i++) {
                if(intervals[i+1].start < intervals[i].end)
                    return false;
            }
            return true;
        }


    }
}
