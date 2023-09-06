package in.rgukt.r081247.java.interviewprep.codingdsalgooops.interval;

import java.util.*;
import java.util.stream.Collectors;

public class MeetingRoomsII {
    public static void main(String[] args) {
        Interval[] intervals = new Interval[] {
                new Interval(0, 30),
                new Interval(5, 10),
                new Interval(15, 20)
            };
        /*
        MeetingRooms.Interval[] intervals = new MeetingRooms.Interval[] {
                new MeetingRooms.Interval(0, 30),
                new MeetingRooms.Interval(30, 40),
                new MeetingRooms.Interval(40, 60)
        };
        */

        long start = System.currentTimeMillis();
        int rooms = Solution3.minimumRooms(intervals);
        System.out.println("rooms: " + rooms);
        System.out.println("time: " + (System.currentTimeMillis() - start) + " ms");
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

        @Override
        public String toString() {
            return "(" +
                     start +
                    "," + end +
                    ')';
        }
    }

    /**
     * Approach : Brute Force
     * Time Complexity: O(n^2) Space Complexity: O(n)
     */
    public static class Solution1 {
        public static void main(String[] args) {

        }

        public static int minimumRooms(Interval[] intervals) {
            List<List<Interval>> rooms = new ArrayList<>();
            if(intervals.length == 0)
                return 0;
            rooms.add(new ArrayList<>());

            for(int i = 0; i < intervals.length; i++) {
                boolean conflicts = false;
                for(int j = 0; j < rooms.size(); j++) {
                    List<Interval> room = rooms.get(j);
                    conflicts = false;

                    for(int k = 0; k < room.size(); k++) {
                        Interval interval = room.get(k);
                        if(intervalsConflict(intervals[i], interval)) {
                            conflicts = true;
                            break;
                        }
                    }

                    if(!conflicts) {
                        room.add(intervals[i]);
                        break;
                    }
                }

                if(conflicts) {
                    List<Interval> newRoom = new ArrayList<>();
                    newRoom.add(intervals[i]);
                    rooms.add(newRoom);
                }
            }

            System.out.println(rooms);
            return rooms.size();
        }

        public static boolean intervalsConflict(Interval interval1, Interval interval2) {
            if(interval1.start < interval2.end && interval1.end > interval2.start)
                return true;
            else
                return false;
        }
    }

    /**
     * Approach : Sorting
     * Time Complexity: O(nlogn) Space Complexity: O(n)
     */
    public static class Solution2 {
        public static int minimumRooms(Interval[] intervals) {
            List<Interval> intervalList = Arrays.stream(intervals)
                    .sorted(Comparator.comparing(Interval::getStart).thenComparing(Interval::getEnd))
                    .collect(Collectors.toList());
            int maxRooms = 0;
            for(int i = 0; i < intervalList.size(); i++) {
                int rooms = 1;
                int j = i - 1;
                while (j >= 0) {
                    if(intervalsConflict(intervalList.get(i), intervalList.get(j)))
                        rooms++;
                    else
                        break;
                    j--;
                }
                maxRooms = Math.max(maxRooms, rooms);
            }
            return maxRooms;
        }

        public static boolean intervalsConflict(Interval interval1, Interval interval2) {
            if(interval1.start < interval2.end && interval1.end > interval2.start)
                return true;
            else
                return false;
        }
    }

    /**
     * Approach: we care about the points in time where we are starting/ending a meeting, we already are given those, just separate start/end and traverse counting num of meetings going at these points in time;
     * for each meeting check if a prev meeting has finished before curr started, using min heap;
     *
     * Time Complexity: O(nlogn) Space Complexity: O(n)
     */
    public static class Solution3 {
        public static int minimumRooms(Interval[] intervals) {
            List<Integer> starts = new ArrayList<>(intervals.length);
            List<Integer> ends = new ArrayList<>(intervals.length);
            for(int i = 0; i < intervals.length; i++) {
                starts.add(intervals[i].start);
                ends.add(intervals[i].end);
            }
            Collections.sort(starts);
            Collections.sort(ends);
            int maxRoomsNeeded = 0;
            int roomsNeeded = 0;
            int s = 0;
            int e = 0;
            while (s < intervals.length) {
                if(starts.get(s) < ends.get(e)) {
                    roomsNeeded++;
                    s++;
                } else {
                    roomsNeeded--;
                    e++;
                }
                maxRoomsNeeded = Math.max(maxRoomsNeeded, roomsNeeded);
            }
            return maxRoomsNeeded;
        }
    }

    /**
     * Approach: for each meeting check if a prev meeting has finished before curr started, using min heap;
     * Time Complexity: O(nlogn) Space Complexity: O(n)
     */
    public static class Solution4 {
        public static int minimumRooms(Interval[] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(Interval::getStart));
            Queue<Integer> pq = new PriorityQueue<>();
            for(int i = 0; i < intervals.length; i++) {
                if(pq.size() == 0) { // first meeting
                    pq.offer(intervals[i].end);
                } else {
                    if (pq.peek() > intervals[i].start) { // new room created
                        pq.offer(intervals[i].end);
                    } else { // old room has been used
                        pq.poll();
                        pq.offer(intervals[i].end);
                    }
                }
            }
            return pq.size();
        }
    }
}
