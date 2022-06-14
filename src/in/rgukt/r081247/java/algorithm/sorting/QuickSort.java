package in.rgukt.r081247.java.algorithm.sorting;

import java.util.*;

public class QuickSort {
    /** Quick-sort contents of a queue. */
    public static <T> void quickSort(Queue<T> queue, Comparator<T> comp) {
        int n = queue.size();
        if (n < 2)                                  // queue is trivially sorted
            return;
        T pivot = queue.peek();                     // using first as arbitrary pivot
        Queue<T> less = new LinkedList<>();
        Queue<T> equal = new LinkedList<>();
        Queue<T> more = new LinkedList<>();
        while(!queue.isEmpty()) {                   // divide original into L, E, and G
            T element = queue.poll();
            int c = comp.compare(element, pivot);
            if (c < 0) {                            // element is less than pivot
                less.offer(element);
            } else if (c == 0) {                    // element is equal to pivot
                equal.offer(element);
            } else {                                // element is greater than pivot
                more.offer(element);
            }
        }

        // conquer
        quickSort(less, comp);                      // sort elements less than pivot
        quickSort(more, comp);                      // sort elements greater than pivot

        // concatenate results
        while (!less.isEmpty()) {
            queue.offer(less.poll());
        }
        while (!equal.isEmpty()) {
            queue.offer(equal.poll());
        }
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
        Random random = new Random();
        for (int i = 10; i > 0; i--) {
            queue.offer(random.nextInt(10));
        }

        System.out.println("queue: " + queue);
        long start = System.currentTimeMillis();
        quickSort(queue, (a, b) -> a - b);
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start) + " ms");
        System.out.println("queue: " + queue);
        */


        int size = 10;
        Integer[] arr = new Integer[size];
        for (int i = size; i > 0; i--) {
            arr[size - i] = i;
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();


        long start = System.currentTimeMillis();
        quickSortInPlace(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("time: " + time + " ms");


        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}


