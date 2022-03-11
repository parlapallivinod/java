package in.rgukt.r081247.java.concurrency.defogtech;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer3 {
    public static void main(String[] args) throws InterruptedException {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(10);

        Runnable producer = () -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                    Integer i = (int) (Math.random() * 100);
                    //System.out.println("Thread: " + Thread.currentThread().getName() + ", Put: " +  i + ", Queue: " + queue);
                    queue.put(i);
                    System.out.println("Thread: " + Thread.currentThread().getName() + ", Put: " +  i + ", Queue: " + queue);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread p1 = new Thread(producer);
        p1.start();
        Thread p2 = new Thread(producer);
        p2.start();

        Runnable consumer = () -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    Integer i = queue.take();
                    System.out.println("Thread: " + Thread.currentThread().getName() + ", Take: " +  i+ ", Queue: " + queue);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread c1 = new Thread(consumer);
        c1.start();
        Thread c2 = new Thread(consumer);
        c2.start();
        //Thread.sleep(1000);
    }

    public static class MyBlockingQueue<E> {
        private int max;
        private Queue<E> queue = new LinkedList<>();

        private Object notEmpty = new Object();
        private Object notFull = new Object();

        public MyBlockingQueue(int size) {
            queue = new LinkedList<>();
            this.max = size;
        }

        public synchronized void put(E e) {
            while (queue.size() == max) {
                try {
                    wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            queue.add(e);
            notifyAll();
        }

        public synchronized E take() {
            while (queue.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            E item = queue.remove();
            notifyAll();
            return item;
        }

        public synchronized String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (E e: queue) {
                sb.append(e + ", ");
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
