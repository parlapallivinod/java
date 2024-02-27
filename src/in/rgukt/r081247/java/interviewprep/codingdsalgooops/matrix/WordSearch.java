package in.rgukt.r081247.java.interviewprep.codingdsalgooops.matrix;


public class WordSearch {
    public static void main(String[] args) {
        /*
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'},
        };
        String word = "";
        boolean result = false;

        word = "ABCCED";
        result = Solution1.exist(board, word);
        System.out.println(result);

        word = "SEE";
        result = Solution1.exist(board, word);
        System.out.println(result);

        word = "ABCB";
        result = Solution1.exist(board, word);
        System.out.println(result);
         */

        char[][] board = {
                {'A', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'},
        };
        String word = "";
        boolean result = false;

        word = "AAB";
        result = Solution1.exist(board, word);
        System.out.println(result);

    }


    /**
     * Approach   : Backtracking using DFS: dfs on each cell, for each search remember visited cells, and remove cur visited cell right before you return from dfs;
     * Complexity : Time: O(m*n*3^l) ; Space: O(l(length of word))
     */
    public static class Solution1 {
        public static boolean exist(char[][] board, String word) {
            char[] wordLetters = word.toCharArray();
            boolean[][] visited = new boolean[board.length][board[0].length];

            for (int i = 0; i < board.length; i++)
                for (int j = 0; j < board[i].length; j++)
                    if (wordExists(board, i, j, wordLetters, 0, visited))
                            return true;
            return false;
        }

        private static boolean wordExists(char[][] board, int i, int j, char[] wordLetters, int index, boolean[][] visited) {
            if (index >= wordLetters.length)
                return true;
            if( i < 0 || i >= board.length || j < 0 || j >= board[i].length ||
                    visited[i][j] ||
                    board[i][j] != wordLetters[index])
                return false;

            visited[i][j] = true;
            boolean result = wordExists(board, i, j + 1, wordLetters, index + 1, visited) ||
                    wordExists(board, i + 1, j, wordLetters, index + 1, visited) ||
                    wordExists(board, i, j - 1, wordLetters, index + 1, visited) ||
                    wordExists(board, i - 1, j, wordLetters, index + 1, visited);

            visited[i][j] = false;
            return result;
        }
    }
}
