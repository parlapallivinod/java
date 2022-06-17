package in.rgukt.r081247.java.interviewprep.codingdsalgooops.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
      /**
     * Approach   :
     * Complexity : Time: O(m*n) ; Space: O(m*n)
     */
    public static class Solution1 {
        public static List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> spiralList = new ArrayList<>();
            if (matrix == null || matrix.length == 0)
                return spiralList;
            int rowStart = 0;
            int columnEnd = matrix[matrix.length-1].length - 1;
            int rowEnd = matrix.length - 1;
            int columnStart = 0;

            while (rowStart <= rowEnd && columnStart <= columnEnd) {
                //System.out.println("rowTop: " + rowTop + ", rowBottom: " + rowBottom + ", columnLeft: " + columnLeft + ", columnRight: " + columnRight);
                // covering one row, traversing from left to right in latest row...
                for(int i = columnStart; i <= columnEnd; i++)
                    spiralList.add(matrix[rowStart][i]);
                // updating starring row...
                rowStart++;

                // covering one column, traversing from top to bottom in latest column...
                for(int i = rowStart; i <= rowEnd; i++)
                    spiralList.add(matrix[i][columnEnd]);
                // updating ending column...
                columnEnd--;

                if(rowStart <= rowEnd) {
                    // covering one row, traversing from right to left in latest ending row...
                    for(int i = columnEnd; i >= columnStart; i--)
                        spiralList.add(matrix[rowEnd][i]);
                    // updating ending row...
                    rowEnd--;
                }

                // convering one column, trabersing from bottom to top in starting column...
                if(columnStart <= columnEnd) {
                    for (int i = rowEnd; i >= rowStart; i--)
                        spiralList.add(matrix[i][columnStart]);
                    // updating starting colum...
                    columnStart++;
                }
            }
            return spiralList;
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> list = Solution1.spiralOrder(matrix);
        System.out.println(list);

    }
}
