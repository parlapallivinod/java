package in.rgukt.r081247.java.concurrency.defogtech;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer2 {
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
        private ReentrantLock lock = new ReentrantLock(true);
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();

        public MyBlockingQueue(int size) {
            queue = new LinkedList<>();
            this.max = size;
        }

        public void put(E e) {
            lock.lock();
            try {
                while (queue.size() == max) {
                    try {
                        notFull.await();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
                queue.add(e);
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public E take() {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                E item = queue.remove();
                notFull.signalAll();
                return item;
            } finally {
                lock.unlock();
            }
        }

        public String toString() {
            lock.lock();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (E e: queue) {
                sb.append(e + ", ");
            }
            sb.append("]");
            lock.unlock();
            return sb.toString();
        }
    }
}
