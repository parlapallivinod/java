package in.rgukt.r081247.java;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Solution {
    public static void main(String[] args) throws Exception {
        Median m= new Median();
        System.out.println(m.add(1));
        System.out.println(m.add(2));
        System.out.println(m.add(3));
        System.out.println(m.add(4));
        System.out.println(m.add(5));
        System.out.println(m.add(6));
    }

    public static class Median {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;

        public Median() {
            maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public Double add(Integer item) {
            if(maxHeap.isEmpty() || minHeap.isEmpty()) {
                maxHeap.offer(item);
            } else {
                if(item <= maxHeap.peek())
                    maxHeap.offer(item);
                else
                    minHeap.offer(item);
            }
            if(maxHeap.size() > minHeap.size() + 1)
                minHeap.offer(maxHeap.poll());
            else if (minHeap.size() > maxHeap.size() + 1)
                maxHeap.offer(minHeap.poll());

            System.out.println(maxHeap);
            System.out.println(minHeap);

            return this.getMedian();
        }

        public Double getMedian() {
            if(maxHeap.size() > minHeap.size())
                return maxHeap.peek().doubleValue();
            else if(minHeap.size() > maxHeap.size())
                return minHeap.peek().doubleValue();
            else
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}