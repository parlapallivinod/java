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

public class Main {
    public static void main(String[] args){
        SQueue<Integer> sq = new SQueue<>();
        List<Future<?>> list = new LinkedList<>();
        ExecutorService es = Executors.newFixedThreadPool(10);

        for (int i=0;i<10;i++) {
            ProducerTask<Integer> pt = new ProducerTask<>(sq, i);
            Future<?> fp = es.submit(pt);
            list.add(fp);
        }

        for (int i=0;i<10;i++) {
            ConsumerTask<Integer> ct = new ConsumerTask<>(sq);
            Future<?> fc = es.submit(ct);
            list.add(fc);
        }

        es.shutdown();

        /*
        for (int i=0;i<list.size();i++) {
            try {
                if (!list.get(i).isDone()) {
                    list.get(i).get();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

         */
    }

    public static class SQueue<T> {
        private T object;
        Lock lock = new ReentrantLock();
        Condition gotCondition = lock.newCondition();
        Condition emptyCondition = lock.newCondition();

        public void put(T object) {
            lock.lock();
            while (this.object != null) {
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.object = object;
            System.out.println(Thread.currentThread().getName() + ": put: " + object);
            gotCondition.signal();
            lock.unlock();
        }

        public T get() {
            lock.lock();
            while (this.object == null) {
                try {
                    gotCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T temp = object;
            object = null;
            emptyCondition.signal();
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + ": get: " + temp);
            return temp;
        }

    }

    public static class ProducerTask<T> implements Runnable {
        private SQueue<T> sq;
        T object;

        public ProducerTask(SQueue<T> sq, T object) {
            this.sq = sq;
            this.object = object;
        }

        @Override
        public void run() {
            sq.put(object);

        }
    }

    public static class ConsumerTask<T> implements Runnable {
        private SQueue<T> sq;

        public ConsumerTask(SQueue<T> sq) {
            this.sq = sq;
        }

        @Override
        public void run() {
            T object = sq.get();
        }
    }
}