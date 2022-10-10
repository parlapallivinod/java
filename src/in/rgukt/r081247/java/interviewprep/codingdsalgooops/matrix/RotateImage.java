package in.rgukt.r081247.java.interviewprep.codingdsalgooops.matrix;

public class RotateImage {
    /**
     * Approach   :
     * rotate layer-by-layer, use that it's a square as advantage, rotate positions in reverse order, store a in temp, a = b, b = c, c = d, d = temp;
     * Complexity : Time: O(n*n) ; Space: O(1)
     */
    public static class Solution1 {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            int left = 0;
            int right = n - 1;
            while(left < right) {
                int top = left;
                int bottom = right;
                for(int i=0;i<(right-left);i++) {
                    int topLeft = matrix[top][left+i];
                    matrix[top][left+i] = matrix[bottom-i][left];
                    matrix[bottom-i][left] = matrix[bottom][right-i];
                    matrix[bottom][right-i] = matrix[top+i][right];
                    matrix[top+i][right] = topLeft;
                }
                left++;
                right--;
            }
        }
    }
}
