package in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class FindMedianFromDataStream {

    /**
     * Approach: 2 Priority Queues(max heap to store elements <= keys, min heap to store elements > key)
     * Complexity : Time: O(nlogn) ; Space: O(n)
     */
    public static class MedianDataStream {
        Queue<Integer> maxHeap = new PriorityQueue<>((i1, i2) -> i2 - i1);
        Queue<Integer> minHeap = new PriorityQueue<>();
        Integer median = null;

        /*
        int getMedian (int element) {
            if(median == null) {
                maxHeap.offer(element);
            }
            else if(element <= median) {
                if(maxHeap.size() > minHeap.size())
                    minHeap.offer(maxHeap.poll());
                maxHeap.offer(element);

            } else {
                if(minHeap.size() > maxHeap.size())
                    maxHeap.offer(minHeap.poll());
                minHeap.offer(element);
            }

            if (maxHeap.size() > minHeap.size()) {
                median = maxHeap.peek();
            } else if(maxHeap.size() < minHeap.size()) {
                median = minHeap.peek();
            } else {
                median = (minHeap.peek() + maxHeap.peek())/2;
            }

            return median;
        }
         */
        int getMedian (int element) {
            if(median == null)
                maxHeap.offer(element);
            else if(element <= median)
                maxHeap.offer(element);
            else
                minHeap.offer(element);

            if(maxHeap.size() == minHeap.size() + 2)
                minHeap.offer(maxHeap.poll());
            else if(minHeap.size() == maxHeap.size() + 2)
                maxHeap.offer(minHeap.poll());

            if (maxHeap.size() > minHeap.size()) {
                median = maxHeap.peek();
            } else if(maxHeap.size() < minHeap.size()) {
                median = minHeap.peek();
            } else {
                median = (minHeap.peek() + maxHeap.peek())/2;
            }

            return median;
        }
    }

    public static void main(String[] args) {
        MedianDataStream md1 = new MedianDataStream();
        System.out.println(md1.getMedian(5));
        System.out.println(md1.getMedian(15));
        System.out.println(md1.getMedian(1));
        System.out.println(md1.getMedian(3));
        System.out.println(md1.getMedian(8));

        MedianDataStream md2 = new MedianDataStream();
        System.out.println(md2.getMedian(15));//15
        System.out.println(md2.getMedian(14));//14
        System.out.println(md2.getMedian(13));//14
        System.out.println(md2.getMedian(12));//13
        System.out.println(md2.getMedian(10));//13
    }
}
