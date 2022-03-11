package in.rgukt.r081247.java.interviewprep.codingdsalgooops.array;

import java.util.*;

class TwoSum {
    //Approach 1: Brute Force Time: O(n2) Space: O(1)
    class Solution1 {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] == target - nums[i]) {
                        return new int[] { i, j };
                    }
                }
            }
            // In case there is no solution, we'll just return null
            return null;
        }
    }

    //Approach 2:  Two-pass Hash Table Time: O(n) Space: O(n)
    class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement) && map.get(complement) != i) {
                    return new int[] { i, map.get(complement) };
                }
            }
            // In case there is no solution, we'll just return null
            return null;
        }
    }

    //Approach 3: One-pass Hash Table ime: O(n) Space: O(n)
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[] { map.get(complement), i };
                }
                map.put(nums[i], i);
            }
            // In case there is no solution, we'll just return null
            return null;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int[] arr = null;
        /*
        for (int i = 0; i < nums.length - 1 ; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    arr = new int[]{i, j};
                    break;
                }
            }
        }
        return arr;
        */

        /*
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i]) && map.get(target-nums[i]) != i) {
                arr = new int[] {i,  map.get(target-nums[i])};
                break;
            }
        }
        */

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> set = map.get(nums[i]);
            if (set == null) {
                set = new HashSet<>();
                map.put(nums[i], set);
            }
            set.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                if (nums[i] == target-nums[i]) {
                    Object[] a = map.get(target-nums[i]).toArray();
                    if (a.length > 1) {
                        arr = new int[] {i, (Integer) a[1]};
                        break;
                    }
                } else {
                    Object[] a = map.get(target-nums[i]).toArray();
                    arr = new int[] {i, (Integer) a[0]};
                    break;
                }
            }
        }

        return arr;
    }
}