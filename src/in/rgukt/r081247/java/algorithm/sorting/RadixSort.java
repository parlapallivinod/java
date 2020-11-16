package in.rgukt.r081247.java.algorithm.sorting;

public class RadixSort {

    public static void radixSort(int[] nums) {
        int digits = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int localDigits = 0;
            while (num != 0) {
                num = num / 10;
                localDigits++;
            }
            if (localDigits > digits)
                digits = localDigits;
        }
        System.out.println("Digits: " + digits);

        int mod = 10;
        int div = 1;
        for (int i = 0; i < digits; i++) {
            int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int j = 0; j < nums.length; j++){
                int digit = (nums[j] % mod) / div;
                count[digit]++;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }

            int[] intNums = new int[nums.length];
            for (int j = nums.length - 1; j >= 0; j--) {
                int digit = (nums[j] % mod) / div;
                intNums[count[digit] - 1] = nums[j];
                count[digit]--;
            }
            for (int j = 0; j < nums.length; j++) {
                nums[j] = intNums[j];
            }

            mod = mod * 10;
            div = div * 10;
        }
    }

    public static void main(String[] args) {
        int[] nums = {329, 219, 119, 323, 213, 321, 111, 101, 100};
        radixSort(nums);

        /*
        int digits = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int localDigits = 0;
            while (num != 0) {
                num = num / 10;
                localDigits++;
            }
            if (localDigits > digits)
                digits = localDigits;
        }
        System.out.println("Digits: " + digits);

        int mod = 10;
        int div = 1;
        for (int i = 0; i < digits; i++) {
            int[] count = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            for (int j = 0; j < nums.length; j++){
                int digit = (nums[j] % mod) / div;
                count[digit]++;
            }

            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }
            
            int[] intNums = new int[nums.length];
            for (int j = nums.length - 1; j >= 0; j--) {
                int digit = (nums[j] % mod) / div;
                intNums[count[digit] - 1] = nums[j];
                count[digit]--;
            }
            for (int j = 0; j < nums.length; j++) {
                nums[j] = intNums[j];
            }

            mod = mod * 10;
            div = div * 10;
        }
        */

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ", ");
        }
    }
}
