package in.rgukt.r081247.java.concurrency.defogtech;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

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
}
