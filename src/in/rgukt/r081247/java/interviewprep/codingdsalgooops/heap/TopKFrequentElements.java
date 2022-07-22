package in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap;



import java.nio.channels.Pipe;
import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {

    }

    public static class Solution1 {
        public int[] topKFrequent(int[] nums, int k) {
            int[] res = new int[k];
            PriorityQueue<Integer> p = new PriorityQueue<>();
            Map<Integer, Integer> map  = new HashMap<>();
            for(int num: nums) {
                Integer count = map.getOrDefault(num, 0);
                map.put(num, count+1);
            }
            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
            for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
                pq.add(entry);
            }
            for(int i=0;i<k;i++) {
                res[i] = pq.poll().getKey();
            }
            return res;
        }
    }
}
