package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectedComponentsUndirectedGraph {
    public static void main(String[] args) {
        int count = Solution1.countComponents(6, new int[][] {{0,1},{1,2},{2,3},{4,5}});
        System.out.println(count);
    }
    /*
     * Approach   : Depth First Search
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes + edges)
     */
    public static class Solution1 {
        public static int countComponents(int n, int[][] edges) {
            Map<Integer, Set<Integer>> adjacencyMap = new HashMap<>();
            for(int i=0; i<n; i++)
                adjacencyMap.put(i, new HashSet<>());

            for(int[] edge: edges) {
               adjacencyMap.get(edge[0]).add(edge[1]);
               adjacencyMap.get(edge[1]).add(edge[0]);
            }
            System.out.println(adjacencyMap);

            int count = 0;
            Set<Integer> visited = new HashSet<>();
            Map<Integer, Integer> forest = new HashMap<>();
            for(int i = 0; i < n; i++) {
                if(!visited.contains(i)) {
                    dfs(i, adjacencyMap, visited, forest);
                    count++;
                }

            }
            return count;
        }
        public static void dfs(int vertex, Map<Integer, Set<Integer>> adjacencyMap, Set<Integer> visited, Map<Integer,Integer> forest) {
            visited.add(vertex);
            for(int adjVertex: adjacencyMap.get(vertex)) {
                if(!visited.contains(adjVertex)) {
                    forest.put(vertex, adjVertex);
                    dfs(adjVertex, adjacencyMap, visited, forest);
                }
            }
        }
    }

    /*
     * Approach   : Disjoint Set Union
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes + edges)
     */
    public static class Solution2 {
        public static int countComponents(int n, int[][] edges) {
            DSU dsu = new DSU(n);
            int res = n;
            for(int[] edge: edges) {
                if(dsu.union(edge[0], edge[1]))
                    res--;
            }
            return res;
        }

        public static class DSU {
            int[] parent;
            int[] rank;

            public DSU(int size) {
                parent = new int[size];
                rank = new int[size];
                for(int i = 0; i < size; i++) {
                    parent[i] = i;
                    rank[i] = 1;
                }
            }

            public int find(int node) {
                int curr = node;
                while(curr != parent[curr]) {
                    parent[curr] = parent[parent[curr]];
                    curr = parent[curr];
                }
                return curr;
            }

            public boolean union(int u, int v) {
                int pu = find(u);
                int pv = find(v);
                if(pu == pv)
                    return false;
                if(rank[pv] > rank[pu]) {
                    int temp = pv;
                    pv = pu;
                    pu = temp;
                }

                rank[pu] += rank[pv];
                parent[pv] = pu;
                return true;
            }
        }
    }
}
