package in.rgukt.r081247.java.coding.array;

import java.util.*;
class TwoSum {
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
            if (map.containsKey(nums[i]) && map.containsKey(target-nums[i]) && map.get(target-nums[i]) != i) {
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
            if (map.containsKey(nums[i]) && map.containsKey(target-nums[i])) {
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
