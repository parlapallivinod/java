package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    // Approach: Using Set
    // Time: O(n) Space: O(n)
    public class Solution1 {
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> numsSet = new HashSet<>();
            for (int num: nums) {
                if (numsSet.contains(num))
                    return true;
                numsSet.add(num);
            }
            return false;
        }
    }

    // Approach: Sorting
    // Time: O(nlogn) Space: O(n)
    public class Solution2 {
        public boolean containsDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] == nums[i+1])
                    return true;
            }
            return false;
        }
    }
}
