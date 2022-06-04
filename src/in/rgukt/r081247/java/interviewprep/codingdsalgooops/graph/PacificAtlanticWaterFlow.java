package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.*;

public class PacificAtlanticWaterFlow {

    /**
     *  Two Queue and add all the Pacific border to one queue; Atlantic border to another queue.
     * Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
     * BFS: Water flood from ocean to the cell. Since water can only flow from high/equal cell to low cell, add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.
     * Approach   : BFS calls
     * Complexity : O(2*(nodes + edges)) = O(2*(nodes + 4 nodes)) = O(nodes)
     * Space: O(nodes)
     */
    public static class Solution1 {
        public static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public static List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> res = new LinkedList<>();
            if(heights == null || heights.length == 0 || heights[0].length == 0)
                return res;
            int n = heights.length;
            int m = heights[0].length;
            //One visited map for each ocean
            boolean[][] pacific = new boolean[n][m];
            boolean[][] atlantic = new boolean[n][m];
            Queue<List<Integer>> pQueue = new LinkedList<>();
            Queue<List<Integer>> aQueue = new LinkedList<>();
            for(int i=0;i<n;i++) {
                pQueue.offer(List.of(i, 0));
                pacific[i][0] = true;
                aQueue.offer(List.of(i, m-1));
                atlantic[i][m-1] = true;
            }
            for(int i=0;i<m;i++) {
                pQueue.offer(List.of(0, i));
                pacific[0][i] = true;
                aQueue.offer(List.of(n-1, i));
                atlantic[n-1][i] = true;
            }
            bfs(heights, pQueue, pacific);
            bfs(heights, aQueue, atlantic);
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(pacific[i][j] && atlantic[i][j])
                        res.add(List.of(i, j));
                }
            }
            return res;
        }

        public static void bfs(int[][] heights, Queue<List<Integer>> queue, boolean[][] visited) {
            int n = heights.length;
            int m = heights[0].length;
            while(!queue.isEmpty()) {
                List<Integer> cur = queue.poll();
                for(int[] d: dir) {
                    int x = cur.get(0) + d[0];
                    int y = cur.get(1)  + d[1];
                    if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || heights[cur.get(0)][cur.get(1)] > heights[x][y])
                        continue;
                    queue.offer(List.of(x, y));
                    visited[x][y] = true;
                }
            }
        }

    }

    /**
     *  Two Queue and add all the Pacific border to one queue; Atlantic border to another queue.
     * Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
     * DFS: Water flood from ocean to the cell. Since water can only flow from high/equal cell to low cell, add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.
     * Approach   : DFS calls
     * Complexity : 2n + 2m DFS calls O(2(n+m)*(nodes + edges)) = O(2(n+m)*(5*nodes)) = O((n+m)*nodes)
     * Space: O(nodes)
     */
    public static class Solution2 {
        public static int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public static List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> res = new LinkedList<>();
            if(heights == null || heights.length == 0 || heights[0].length == 0)
                return res;
            int n = heights.length;
            int m = heights[0].length;
            //One visited map for each ocean
            boolean[][] pacific = new boolean[n][m];
            boolean[][] atlantic = new boolean[n][m];
            for(int i=0;i<n;i++) {
                dfs(heights, pacific, Integer.MIN_VALUE, i, 0);
                dfs(heights, atlantic, Integer.MIN_VALUE, i, m - 1);
            }
            for(int i=0;i<m;i++) {
                dfs(heights, pacific, Integer.MIN_VALUE, 0, i);
                dfs(heights, atlantic, Integer.MIN_VALUE, n-1, i);
            }
            for(int i=0;i<n;i++) {
                for(int j=0;j<m;j++) {
                    if(pacific[i][j] && atlantic[i][j])
                        res.add(List.of(i, j));
                }
            }
            return res;
        }


        public static void dfs(int[][] heights, boolean[][] visited, int height, int x, int y) {
            int n = heights.length;
            int m = heights[0].length;
            if (x < 0 || x >=n || y < 0 || y >= m || visited[x][y] || heights[x][y] < height)
                return;
            visited[x][y] = true;
            for(int[] d: dir) {
                dfs(heights, visited, heights[x][y], x+d[0], y+d[1]);
            }
        }

    }

    /*
     * Approach   : My Solution using BFS on each node
     * Complexity :
     *      Time: O(nodes(nodes + edges)) = O(nodes(nodes+(4*nodes)) = O(5n^2)
     *      Space: O(nodes)
     */
    public static class SolutionN {
        public static List<List<Integer>> pacificAtlantic(int[][] heights) {
            List<List<Integer>> indices = new LinkedList<>();
            if (heights == null || heights.length == 0 || heights[0].length == 0)
                return indices;

            for(int i=0;i<heights.length;i++) {
                for(int j=0;j<heights[i].length;j++) {
                    if(bfs(heights, i, j)) {
                        List<Integer> index = List.of(i, j);
                        indices.add(index);
                    }
                }
            }
            return indices;
        }

        public static boolean bfs(int[][] heights, int row, int column) {
            //System.out.println("bfs row=" + row +  " column=" + column);
            boolean pacific = false;
            boolean atlantic = false;
            int rows = heights.length;
            int columns = heights[heights.length-1].length;

            boolean[][] visited = new boolean[rows][columns];
            Deque<int[]> queue = new LinkedList<>();

            visited[row][column] = true;
            queue.add(new int[]{row, column});

            while(!queue.isEmpty()) {
                int[] n = queue.poll();
                //System.out.println(n);
                int r = n[0];
                int c = n[1];

                if(r == 0 || c == 0)
                    pacific = true;
                if(r == rows - 1 || c == columns - 1)
                    atlantic = true;

                if(pacific && atlantic)
                    return true;

                int re = r;
                int ce = c + 1;
                if(isValidNode(re, ce, rows, columns) && heights[r][c] >= heights[re][ce] && (!visited[re][ce])) {
                    queue.offer(new int[]{re, ce});
                    visited[re][ce] = true;
                }

                int rw = r;
                int cw = c - 1;
                if(isValidNode(rw, cw, rows, columns) && heights[r][c] >= heights[rw][cw] && (!visited[rw][cw])) {
                    queue.offer(new int[]{rw, cw});
                    visited[rw][cw] = true;
                }

                int rn = r - 1;
                int cn = c;
                if(isValidNode(rn, cn, rows, columns) && heights[r][c] >= heights[rn][cn] && (!visited[rn][cn])) {
                    queue.offer(new int[]{rn, cn});
                    visited[rn][cn] = true;
                }

                int rs = r + 1;
                int cs = c;
                if(isValidNode(rs, cs, rows, columns) && heights[r][c] >= heights[rs][cs] && (!visited[rs][cs])) {
                    queue.offer(new int[]{rs, cs});
                    visited[rs][cs] = true;
                }
            }
            return false;
        }

        public static boolean isValidNode(int row, int column, int rows, int columns) {
            if(row < 0 || row >= rows || column < 0 || column >= columns)
                return false;
            return true;
        }
    }

    public static void main(String[] args) {
        int[][] heights = new int[][] {{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
        //int[][] heights = new int[][] {{2,1}, {1,2}};
        List<List<Integer>> indices = Solution2.pacificAtlantic(heights);
        System.out.println(indices);
    }
}
