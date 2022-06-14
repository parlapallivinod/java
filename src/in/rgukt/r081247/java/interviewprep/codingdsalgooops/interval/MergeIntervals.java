package in.rgukt.r081247.java.interviewprep.codingdsalgooops.interval;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MergeIntervals {
    /*
     * Approach   : sort each interval, overlapping intervals should be adjacent, iterate and build solution
     * Complexity : Time: O(nlog(n)) ; Space: O(logn) (or O(n))
     */
    public static class Solution1 {
        public static int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length == 0 || intervals.length == 1)
                return intervals;
            Arrays.sort(intervals, (int[] i1, int[] i2) -> i1[0] - i2[0]);
            Deque<int[]> merged = new LinkedList<>();
            for (int[] interval : intervals) {
                // if the list of merged intervals is empty or if the current
                // interval does not overlap with the previous, simply append it.
                if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                    merged.addLast(interval);
                // otherwise, there is overlap, so we merge the current and previous
                // intervals.
                } else {
                    merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
                }
            }
            return merged.toArray(new int[merged.size()][]);
        }
    }

    /*
     * Approach   : If we draw a graph (with intervals as nodes) that contains undirected edges between all pairs of intervals that overlap, then all intervals in each connected component of the graph can be merged into a single interval.
     * Complexity : Time: O(n^2) ; Space: O(n^2)
     */
    public static class Solution2 {
        private static Map<int[], List<int[]>> graph;
        private static  Map<Integer, List<int[]>> nodesInComp;
        private static Set<int[]> visited;

        // return whether two intervals overlap (inclusive)
        public static boolean overlap(int[] interval1, int[] interval2) {
            return interval1[0] <= interval2[1] && interval1[1] >= interval2[0];
        }

        // build a graph where an undirected edge between intervals u and v exists
        // iff u and v overlap.
        public static void buildGraph(int[][] intervals) {
            graph = new HashMap<>();
            for(int[] interval: intervals) {
                graph.put(interval, new LinkedList<>());
            }

            for(int[] interval1: intervals) {
                for(int[] interval2: intervals) {
                    if(interval1 != interval2 && overlap(interval1, interval2)) {
                        graph.get(interval1).add(interval2);
                    }
                }
            }

        }

        // merges all of the nodes in this connected component into one interval.
        public static  int[] mergeNodes(List<int[]> nodes) {
            int minStart = nodes.get(0)[0];
            int maxEnd = nodes.get(0)[1];
            for (int[] node : nodes) {
                minStart = Math.min(minStart, node[0]);
                maxEnd = Math.max(maxEnd, node[1]);
            }
            return new int[] {minStart, maxEnd};
        }

        // use depth-first search to mark all nodes in the same connected component
        // with the same integer.
        public static void markComponentDFS(int[] interval, int compNumer) {
            Deque<int[]> stack = new LinkedList<>();
            stack.push(interval);
            visited.add(interval);

            while(!stack.isEmpty()) {
                int[] presentInterval = stack.pop();
                if(nodesInComp.get(compNumer) == null)
                    nodesInComp.put(compNumer, new LinkedList<>());
                nodesInComp.get(compNumer).add(presentInterval);
                for(int[] adjacentInterval: graph.get(presentInterval)) {
                    if(!visited.contains(adjacentInterval)) {
                        stack.push(adjacentInterval);
                        visited.add(adjacentInterval);
                    }
                }
            }
        }
        // gets the connected components of the interval overlap graph.
        public static void buildComponents(int[][] intervals) {
            nodesInComp = new HashMap<>();
            visited = new HashSet<>();
            int compNumber = 0;
            for(int[] interval: intervals) {
                if(!visited.contains(interval)) {
                    markComponentDFS(interval, compNumber);
                    compNumber++;
                }
            }
        }

        public static int[][] merge(int[][] intervals) {
            buildGraph(intervals);
            /*
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<int[], List<int[]>> entry: graph.entrySet()) {
                sb.append(Arrays.toString(entry.getKey()) + ": [" );

                for(int[] v: entry.getValue()) {
                    sb.append(Arrays.toString(v) + ", ");
                }
                sb.append("]\n" );
            }
            System.out.println(sb.toString());
            */
            buildComponents(intervals);
            /*
            sb = new StringBuilder();
            for(Map.Entry<Integer, List<int[]>> entry: nodesInComp.entrySet()) {
                sb.append(entry.getKey() + ": [" );

                for(int[] v: entry.getValue()) {
                    sb.append(Arrays.toString(v) + ", ");
                }
                sb.append("]\n" );
            }
            System.out.println(sb.toString());
            */

            List<int[]> merged = new LinkedList<>();
            for(Map.Entry<Integer, List<int[]>> entry: nodesInComp.entrySet()) {
                merged.add(mergeNodes(entry.getValue()));
            }
            return merged.toArray(new int[merged.size()][]);
        }
    }

    public static void main(String[] args) {
        /*
        //int[][] intervals = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = new int[][] {{1,4},{4,5}};
        int[][] mergedIntervals = Solution1.merge(intervals);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<mergedIntervals.length;i++)
            sb.append(Arrays.toString(mergedIntervals[i]));
        System.out.println(sb.toString());
         */

        //int[][] intervals = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = new int[][] {{1,4},{4,5}};
        int[][] mergedIntervals = Solution2.merge(intervals);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<mergedIntervals.length;i++)
            sb.append(Arrays.toString(mergedIntervals[i]));
        System.out.println(sb.toString());
    }
}
