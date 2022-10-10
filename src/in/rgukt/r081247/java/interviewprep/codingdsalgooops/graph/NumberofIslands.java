package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class NumberofIslands {
    public static void main(String[] args) {
        /*
        char[][] grid = {
                            {'1', '1', '1', '1', '0'},
                            {'1', '1', '0', '1', '0'},
                            {'1', '1', '0', '0', '0'},
                            {'0', '0', '0', '0', '0'}
                        };
         */
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
                        };
        int islands = Solution1.numIslands(grid);
        System.out.println(islands);
    }

    /*
     * Approach   : BFS
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes)
     */
    public static class Solution2 {
        public static int numIslands(char[][] grid) {
            int islands = 0;
            boolean[][] visited = new boolean[grid.length][grid[grid.length-1].length];
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j] == '0' || visited[i][j])
                        continue;
                    bfs(grid, visited, i, j);
                    islands++;
                }
            }
            return islands;
        }

        public static void bfs(char[][] grid, boolean[][] visited, int i, int j) {
            Deque<int[]> nodes = new LinkedList<>();
            int[][] adjs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
            int rows = grid.length;
            int columns = grid[grid.length-1].length;

            nodes.offer(new int[]{i, j});
            visited[i][j] = true;
            while(!nodes.isEmpty()) {
                int[] node = nodes.poll();
                for(int[] adj: adjs) {
                    int row = node[0] + adj[0];
                    int column = node[1] + adj[1];
                    if(0 <= row  && row < rows && 0 <= column && column < columns && grid[row][column] == '1' && (!visited[row][column])) {
                        nodes.offer(new int[]{row, column});
                        visited[row][column] = true;
                    }
                }
            }
        }
    }

    /*
     * Approach   : DFS
     * Complexity : Time: O(nodes + edges) ; Space: O(nodes)
     */
    public static class Solution1 {
        public static int numIslands(char[][] grid) {
            int islands = 0;
            boolean[][] visited = new boolean[grid.length][grid[grid.length-1].length];
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[i].length; j++) {
                    if(grid[i][j] == '0' || visited[i][j])
                        continue;
                    dfs(grid, visited, i, j);
                    islands++;
                }
            }
            return islands;
        }

        public static void dfs(char[][] grid, boolean[][] visited, int i, int j) {
            if(i < 0  || i >= grid.length || j < 0 || j >= grid[grid.length-1].length || grid[i][j] == '0' || visited[i][j])
                return;
            visited[i][j] = true;
            dfs(grid, visited, i, j+1);
            dfs(grid, visited, i, j-1);
            dfs(grid, visited, i-1, j);
            dfs(grid, visited, i+1, j);
        }
    }
}
