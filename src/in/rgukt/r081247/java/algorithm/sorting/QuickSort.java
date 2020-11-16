package in.rgukt.r081247.java.algorithm.sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuickSort {
    public static <T extends Comparable<? super T>> void quickSort(Queue<T> queue) {
        if (queue.size() < 2)
            return;
        T pivot = queue.poll();
        Queue<T> less = new LinkedList<>();
        Queue<T> more = new LinkedList<>();
        while(!queue.isEmpty()) {
            T element = queue.poll();
            if (element.compareTo(pivot) < 0) {
                less.offer(element);
            } else {
                more.offer(element);
            }
        }
        quickSort(less);
        quickSort(more);

        while (!less.isEmpty()) {
            queue.offer(less.poll());
        }
        queue.offer(pivot);
        while (!more.isEmpty()) {
            queue.offer(more.poll());
        }
    }

    public static <T extends Comparable<? super T>> void quickSortInPlace(T[] arr, int a, int b) {
        if ((b - a) < 1)
            return;

        T pivot = arr[a];
        int start = a + 1;
        int end = b;

        while (start <= end) {
            while (start <= b && arr[start].compareTo(pivot) <= 0){
                start++;
            }

            while(end > a && arr[end].compareTo(pivot) > 0) {
                end--;
            }

            if (start <= end) {
                T temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;

            }
        }

        T temp = arr[end];
        arr[end] = arr[a];
        arr[a] = temp;

        quickSortInPlace(arr, a, end - 1);
        quickSortInPlace(arr, end + 1, b);
    }

    public static void main(String[] args) {
        /*
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 10000; i > 0; i--) {
            queue.offer(i);
        }

        //System.out.println("queue: " + queue);
        long start = System.currentTimeMillis();
        quickSort(queue);
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) + " ms");
        //System.out.println("queue: " + queue);
         */
        int size = 100000;
        Integer[] arr = new Integer[size];
        for (int i = size; i > 0; i--) {
            arr[size - i] = i;
        }

        /*
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
        */

        long start = System.currentTimeMillis();
        quickSortInPlace(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("time: " + time + " ms");

        /*
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
         */
    }
}


