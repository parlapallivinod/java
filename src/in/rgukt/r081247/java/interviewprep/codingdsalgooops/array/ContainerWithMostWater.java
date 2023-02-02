package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

public class ContainerWithMostWater {
    //Approach 1: Brute Force
    // Time: O(n^2) Space: O(1)
    public static class Solution1 {
        public int maxArea(int[] height) {
            int area = 0;
            for(int i = 0; i < height.length - 1; i++)
                for(int j = i + 1; j < height.length; j++)
                    area = Math.max(area, (j-i) * Math.min(height[i], height[j]));
            return area;
        }
    }

    //Approach 1: Two Pointer Approach
    // Time: O(n) Space: O(1)
    public static class Solution3 {
        public int maxArea(int[] heights) {
            int maxArea = 0;
            int left = 0;
            int right = heights.length - 1;
            while(left < right) {
                int widht = right - left;
                int height = Math.min(heights[left], heights[right]);
                maxArea = Math.max(maxArea, widht * height);
                while(heights[left] <= height && left < right)
                    left++;
                while(heights[right] <= height && left < right)
                    right--;
            }
            return maxArea;
        }
    }
}
