package in.rgukt.r081247.java.interviewprep.codingdsalgooops.graph;

import java.util.*;
import java.util.stream.Collectors;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        //int[] nums = {100,4,200,1,3,2};
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(Solution1.longestConsecutive(nums));
    }

    /*
     * Approach   :
     * Complexity : Time: O(nodes); Space: O(nodes)
     */
    public static class Solution1 {
        public static int longestConsecutive(int[] nums) {
            Set<Integer> set = new HashSet<>();
            for(int num: nums)
                set.add(num);
            int longest = 0;
            for(int num: nums) {
                if(set.contains(num-1))
                    continue;
                int curNum = num;
                int curLen = 0;
                while(set.contains(curNum + curLen)) {
                    curLen++;
                }
                longest = Math.max(longest, curLen);
            }
            return longest;
        }
    }

    /*
     * Approach   : BFS
     * Complexity : Time: O(nodes + edges) = O(nodes + 2 * nodes) = O(nodes) ; Space: O(nodes)
     */
    public static class Solution2 {
        public static int longestConsecutive(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            int longest = 0;
            boolean[] visited = new boolean[nums.length];
            Map<Integer, Integer> nodes = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                nodes.put(nums[i], i);
            }
            for(int i = 0; i < nums.length; i++) {
                if(!visited[i] && nodes.get(nums[i]) == i) {
                    int count = bfs(nums, i, visited, nodes);
                    if(count > longest)
                        longest = count;
                }
            }
            return longest;
        }

        public static int bfs(int[] nums, int i, boolean[] visited, Map<Integer, Integer> nodes) {
            int count = 0;
            Deque<Integer> queue = new LinkedList<>();
            if(!visited[i]) {
                queue.offer(nums[i]);
                visited[i] = true;
                count++;
            }
            while(!queue.isEmpty()) {
                int node = queue.poll();
                if(nodes.containsKey(node - 1) && (!visited[nodes.get(node - 1)])) {
                    queue.offer(node - 1);
                    visited[nodes.get(node - 1)] = true;
                    count++;
                }
                if(nodes.containsKey(node + 1) && (!visited[nodes.get(node + 1)])) {
                    queue.offer(node + 1);
                    visited[nodes.get(node + 1)] = true;
                    count++;
                }
            }
            return count;
        }
    }

    /*
     * Approach   : Iterative DFS
     * Complexity : Time: O(nodes + edges) = O(nodes + 2 * nodes) = O(nodes) ; Space: O(nodes)
     */
    public static class Solution3 {
        public static int longestConsecutive(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            int longest = 0;
            boolean[] visited = new boolean[nums.length];
            Map<Integer, Integer> nodes = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                nodes.put(nums[i], i);
            }
            for(int i = 0; i < nums.length; i++) {
                if(!visited[i] && nodes.get(nums[i]) == i) {
                    int count = dfs(nums, i, visited, nodes);
                    if(count > longest)
                        longest = count;
                }
            }
            return longest;
        }

        public static int dfs(int[] nums, int i, boolean[] visited, Map<Integer, Integer> nodes) {
            int count = 0;
            Deque<Integer> queue = new LinkedList<>();
            if(!visited[i]) {
                queue.push(nums[i]);
                visited[i] = true;
                count++;
            }
            while(!queue.isEmpty()) {
                int node = queue.pop();
                if(nodes.containsKey(node - 1) && (!visited[nodes.get(node - 1)])) {
                    queue.offer(node - 1);
                    visited[nodes.get(node - 1)] = true;
                    count++;
                }
                if(nodes.containsKey(node + 1) && (!visited[nodes.get(node + 1)])) {
                    queue.offer(node + 1);
                    visited[nodes.get(node + 1)] = true;
                    count++;
                }
            }
            return count;
        }
    }

    /*
     * Approach   : DFS
     * Complexity : Time: O(nodes + edges) = O(nodes + 2 * nodes) = O(nodes) ; Space: O(nodes)
     */
    public static class Solution4 {
        public static int longestConsecutive(int[] nums) {
            if(nums == null || nums.length == 0)
                return 0;
            int longest = 0;
            boolean[] visited = new boolean[nums.length];
            Map<Integer, Integer> nodes = new HashMap<>();
            for(int i = 0; i < nums.length; i++) {
                nodes.put(nums[i], i);
            }
            for(int i = 0; i < nums.length; i++) {
                if(!visited[i] && nodes.get(nums[i]) == i) {
                    int count = dfs(nums, i, visited, nodes, 0);
                    if(count > longest)
                        longest = count;
                }
            }
            return longest;
        }

        public static int dfs(int[] nums, int i, boolean[] visited, Map<Integer, Integer> nodes, int count) {
            int node = nums[i];
            if(visited[i]) {
                return count;
            }
            visited[i] = true;
            count++;
            if(nodes.containsKey(node - 1) && (!visited[nodes.get(node - 1)]))
                count = dfs(nums, nodes.get(node - 1), visited, nodes, count);

            if(nodes.containsKey(node + 1) && (!visited[nodes.get(node + 1)]))
                count = dfs(nums, nodes.get(node + 1), visited, nodes, count);
            return count;
        }
    }
}
