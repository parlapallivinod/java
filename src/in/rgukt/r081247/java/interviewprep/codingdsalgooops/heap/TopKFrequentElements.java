package in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap;



import java.nio.channels.Pipe;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TopKFrequentElements {
    public static void main(String[] args) {

    }

    /**
     * Approach: minheap that’s kept at size k, if its bigger than k pop the min, by the end it should be left with k largest;
     * Complexity : Time: O(nlogk) ; Space: O(n + k)
     */
    public static class Solution1 {
        public int[] topKFrequent(int[] nums, int k) {
            int[] res = new int[k];
            Map<Integer, Integer> map  = new HashMap<>();
            for(int num: nums)
                map.put(num, map.getOrDefault(num, 0) + 1);
            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
            for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
                pq.add(entry);
                if(pq.size() > k)
                    pq.poll();
            }
            for(int i=0;i<k;i++) {
                res[i] = pq.poll().getKey();
            }
            return res;
        }
    }

    /**
     * Approach:
     * Complexity : Time: O(n) ; Space: O(n)
     */
    public static class Solution2 {
        public int[] topKFrequent(int[] nums, int k) {
            int[] res = new int[k];
            List<List<Integer>> countList = new ArrayList<>(nums.length + 1);
            for(int i=0;i<=nums.length;i++)
                countList.add(new ArrayList<>());

            Map<Integer, Integer> map  = new HashMap<>();
            for(int num: nums)
                map.put(num, map.getOrDefault(num, 0) + 1);
            for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
               countList.get(entry.getValue()).add(entry.getKey());
            }

            int j = 0;
            outer:
            for(int i=nums.length;i>0;i--) {
                List<Integer> list = countList.get(i);
                for(Integer key: list) {
                    res[j++] = key;
                    if(j >= k)
                        break outer;
                }
            }
            return res;
        }
    }
}
