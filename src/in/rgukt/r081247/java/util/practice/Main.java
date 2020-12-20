package in.rgukt.r081247.java.util.practice;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(10);
        q.offer(1);
        q.offer(9);
        q.offer(2);
        q.offer(8);
        q.offer(3);
        q.offer(7);
        q.offer(4);
        q.offer(6);
        q.offer(5);

        System.out.println(q);
        mergeSort(q);
        System.out.println(q);
    }

    public static <T extends Comparable<? super T>> void mergeSort(Queue<T> q) {
        Queue<T> q1 = new LinkedList<>();
        Queue<T> q2 = new LinkedList<>();

        int size = q.size();
        if(size < 2) {
            return;
        }

        int mid = size / 2;
        while (mid > 0) {
            q1.offer(q.remove());
            mid--;
        }
        while (!q.isEmpty()) {
            q2.offer(q.remove());
        }

        mergeSort(q1);
        mergeSort(q2);
        merge(q1, q2, q);
    }

    public static <T extends Comparable<? super T>> void merge(Queue<T> q1, Queue<T> q2, Queue<T> q) {
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty())
                q.offer(q2.remove());
            else if (q2.isEmpty()) {
                q.offer(q1.remove());
            } else {
                if(q1.peek().compareTo(q2.peek()) <= 0) {
                    q.offer(q1.remove());
                } else {
                    q.offer(q2.remove());
                }
            }
        }
    }
}
