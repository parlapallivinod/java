package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

import java.util.*;

public class ThreeSum {
    //Approach 1: Brute Force
    // Time: O(n^3) Space: O(n)
    class Solution1 {
        public List<List<Integer>> threeSum(int[] nums) {
            Set<List<Integer>> set = new HashSet<>();
            Arrays.sort(nums);
            for(int i = 0; i < nums.length; i++) {
                for(int j = i + 1; j < nums.length; j++) {
                    for(int k = j + 1; k < nums.length; k++) {
                        if(nums[i] + nums[j] + nums[k] == 0) {
                            List<Integer> temp = List.of(nums[i], nums[j], nums[k]);
                            set.add(temp);
                        }
                    }
                }
            }
            List<List<Integer>> list = new LinkedList<>();
            list.addAll(set);
            return list;
        }
    }

    //Approach 2: using Hash Map
    // Time: O(n2) Space: O(n)
    class Solution2 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            if(nums.length < 3)
                return new LinkedList<>();
            if(nums[0] > 0)
                return new LinkedList<>();

            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            List<List<Integer>> ret = new LinkedList<>();
            for(int i = 0; i < nums.length - 2; i++) {
                if(nums[i] > 0)
                    break;
                for(int j = i + 1; j < nums.length - 1; j++) {
                    Integer k = map.get(-(nums[i] + nums[j]));
                    if(k != null && k > j) {
                        List<Integer> temp = new ArrayList<>(3);
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        ret.add(temp);
                    }
                    j = map.get(nums[j]);
                }
                i = map.get(nums[i]);
            }
            return ret;
        }
    }

    //Approach 3: Two Pointer approach
    // Time: O(n2) Space: O(1)
    class Solution3 {
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            if(nums.length < 3)
                return new LinkedList<>();
            if(nums[0] > 0)
                return new LinkedList<>();

            List<List<Integer>> set = new LinkedList<>();
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] > 0)
                    break;
                if( i > 0 && nums[i] == nums[i-1])
                    continue;
                int l = i + 1;
                int r = nums.length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[l] + nums[r];
                    if (sum > 0) {
                        r--;
                    } else if (sum < 0) {
                        l++;
                    } else {
                        List<Integer> list = new ArrayList<>(3);
                        list.add(nums[i]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        set.add(list);
                        int last_low_occurence = nums[l] , last_high_occurence = nums[r];
                        while (nums[l] == last_low_occurence && l < r)
                            l++;
                        while (nums[r] == last_high_occurence && l < r)
                            r--;
                    }
                }
            }
            return set;
        }
    }
}
