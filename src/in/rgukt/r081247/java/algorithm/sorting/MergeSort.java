package in.rgukt.r081247.java.algorithm.sorting;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {10, 8, 6, 4, 2, 9, 7, 5, 3, 1};
        mergeSort(nums, 0, nums.length - 1);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
    }

    private static void merge(int[] nums, int start, int mid, int end) {
        int[] first = new int[mid - start + 2];
        for (int i = 0; i < first.length - 1; i++) {
            first[i] = nums[start + i];
        }
        first[first.length - 1] = Integer.MAX_VALUE;

        int[] second = new int[end - mid + 1];
        for (int i = 0; i < second.length - 1; i++) {
            second[i] = nums[mid + 1 + i];
        }
        second[second.length - 1] = Integer.MAX_VALUE;

        int m = 0;
        int n = 0;
        for (int i = 0; i < (end - start + 1); i++) {
            if (first[m] <= second[n]) {
                nums[start + i] = first[m];
                m++;
            } else {
                nums[start + i] = second[n];
                n++;
            }
        }
    }

    public static void mergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }
}
