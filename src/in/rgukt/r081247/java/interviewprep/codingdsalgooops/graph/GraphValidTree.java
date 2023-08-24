package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.*;

public class GraphValidTree {
    public static void main(String[] args) {
        int n = 5;
        List<int[]> edges = List.of(
                new int[] {0, 1},
                new int[] {0, 2},
                new int[] {0, 3},
                new int[] {1, 4}
        );
        long start = System.currentTimeMillis();
        boolean res = Solution1.graphValidTree(n, edges);
        System.out.println("res: " + res);
        System.out.println("time: " + (System.currentTimeMillis() - start) + " ms");
        /*
        int n = 5;
        List<int[]> edges = List.of(
                new int[] {0, 1},
                new int[] {0, 2},
                new int[] {1, 2},
                new int[] {1, 4});
        boolean res = Solution1.graphValidTree(n, edges);
        System.out.println("res: " + res);
         */
    }
    /*
     * Approach   : dfs
     *
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes + edges)
     */
    public static class Solution1 {
        public static boolean graphValidTree(int n, List<int[]> edges) {
            if (n == 0)
                return true;
            List<Set<Integer>> adjacencyMap = new ArrayList<>(n);
            for(int i = 0; i < n; i++)
                adjacencyMap.add(new HashSet<>());
            for(int[] edge: edges) {
                adjacencyMap.get(edge[0]).add(edge[1]);
                adjacencyMap.get(edge[1]).add(edge[0]);
            }
            Set<Integer> visited = new HashSet<>();
            return dfs(-1, 0, adjacencyMap, visited) && (visited.size() == n);
        }
        public static boolean dfs(int parent, int source, List<Set<Integer>> adjacencyMap, Set<Integer> visited) {
            if(visited.contains(source))
                return false;
            visited.add(source);

            for(int vertex: adjacencyMap.get(source)) {
                if(vertex == parent)
                    continue;
                if(!dfs(source, vertex, adjacencyMap, visited))
                    return false;
            }
            return true;
        }
    }
}
