package in.rgukt.r081247.java.concurrency.defogtech;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Semaphore2 {
    public static void main(String[] args) throws InterruptedException {
        //Semaphore semaphore = new Semaphore(3);
        Semaphore semaphore = new  Semaphore(3);
        ExecutorService service = Executors.newFixedThreadPool(50);
        IntStream.range(1, 1000).forEach(i -> service.execute(new Semaphore1.Task(semaphore)));
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
    }

    static class Task implements Runnable {
        private  Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {

            // some processing
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // IO call to the slow service
            //semaphore.release();
            // rest of processing
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static class Semaphore {
        private volatile int permissions;

        public Semaphore(int permissions) {
            this.permissions = permissions;
        }

        public synchronized void acquire() throws InterruptedException {
            while (permissions == 0)
                wait();
            permissions--;
        }

        public synchronized void release() {
            permissions++;
            notifyAll();
        }
    }
}
