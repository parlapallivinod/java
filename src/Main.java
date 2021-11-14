import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.*;

public class Main {
    public static void main(String[] args) {
       CommonStore<Integer> store = new CommonStore<>(10);
       Producer p1 = new Producer(store);
        Producer p2 = new Producer(store);
       Consumer c1 = new Consumer(store);
       //Consumer c2 = new Consumer(store);

       Thread p1t = new Thread(p1, "Producer 1");
       Thread p2t = new Thread(p1, "Producer 2");
       Thread c1t = new Thread(c1, "Consumer 1");
       Thread c2t = new Thread(c1, "Consumer 2");

       p1t.start();
       p2t.start();
       c1t.start();
       c2t.start();

       try {
           p1t.join();
           p2t.join();
           c1t.join();
           c2t.join();
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }

    public static class CommonStore<T> {

        private Deque<T> arr = new LinkedList<>();
        private int size;

        public CommonStore(int size) {
            this.size = size;
        }

        public synchronized  void put(T element) {
            while (arr.size() >= size) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            arr.addLast(element);

            notifyAll();

        }

        public synchronized T  get() {
            while (arr.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            T element = arr.removeFirst();
            notifyAll();
            return element;
        }
    }

    public static class Producer implements Runnable{
        private Random r = new Random();
        private CommonStore<Integer> store = null;

        public Producer(CommonStore<Integer> store) {
            this.store = store;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Integer i = r.nextInt(1000);
                store.put(i);
                System.out.println("Producer[" + Thread.currentThread().getName() + "]: " + i);
            }
        }
    }

    public static class Consumer implements Runnable{
        private CommonStore<Integer> store = null;

        public Consumer(CommonStore<Integer> store) {
            this.store = store;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Integer i = store.get();
                System.out.println("                                                                               Consumer[" + Thread.currentThread().getName() + "]: " + i);
            }
        }
    }
}
