package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.*;

public class CourseSchedule {

    /*
     * Approach   : Topological Sort
     * Complexity : Time: O(nodes * edges) ; Space: O(nodes)
     */
    public static class Solution1 {
        public static boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] inDegree = new int[numCourses];
            List<Integer> topo = new ArrayList<>(numCourses);
            Deque<Integer> ready = new LinkedList<>();

            for(int[] prerequisite: prerequisites) {
                inDegree[prerequisite[0]]++;
            }
            for(int i=0;i<inDegree.length;i++) {
                if(inDegree[i] == 0)
                    ready.offer(i);
            }

            while(!ready.isEmpty()) {
                int course = ready.poll();
                topo.add(course);
                for(int[] prerequisite: prerequisites) {
                    if(prerequisite[1] == course) {
                        inDegree[prerequisite[0]]--;
                        if(inDegree[prerequisite[0]] == 0)
                            ready.offer(prerequisite[0]);
                    }
                }
            }

            return topo.size() == numCourses ? true: false;
        }
    }

    /*
     * Approach   : Topological Sort
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes + edges)
     */
    public static class Solution2 {
        public static boolean canFinish(int numCourses, int[][] prerequisites) {
            int[] inDegree = new int[numCourses];
            List<Set<Integer>> outEdges = new ArrayList<>(numCourses);
            List<Integer> topo = new ArrayList<>(numCourses);
            Deque<Integer> ready = new LinkedList<>();

            for(int i=0;i<numCourses;i++)
                outEdges.add(new LinkedHashSet<>());

            for(int[] prerequisite: prerequisites) {
                inDegree[prerequisite[0]]++;
                outEdges.get(prerequisite[1]).add(prerequisite[0]);
            }
            for(int i=0;i<inDegree.length;i++) {
                if(inDegree[i] == 0)
                    ready.offer(i);
            }

            while(!ready.isEmpty()) {
                int course = ready.poll();
                topo.add(course);
                for(int nextCourse: outEdges.get(course)) {
                    inDegree[nextCourse]--;
                    if(inDegree[nextCourse] == 0)
                        ready.offer(nextCourse);
                }
            }

            return topo.size() == numCourses ? true: false;
        }
    }

    public static void main(String[] args) {

        int numCourses = 4;
        int[][] prerequisites = new int[][] {{1, 0}, {1, 2}, {3, 1}};

        /*
        int numCourses = 2;
        int[][] prerequisites = new int[][] {{1, 0}, {0, 1}};
        */

        //System.out.println(Solution1.canFinish(numCourses, prerequisites));
        System.out.println(Solution2.canFinish(numCourses, prerequisites));
    }
}
