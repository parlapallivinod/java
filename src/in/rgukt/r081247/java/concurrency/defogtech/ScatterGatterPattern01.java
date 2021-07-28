package in.rgukt.r081247.java.concurrency.defogtech;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScatterGatterPattern01 {
    public static void main(String[] args) {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch latch = new CountDownLatch(3);
        Random random = new Random();

        ExecutorService threadPool = Executors.newFixedThreadPool(4);

        threadPool.submit(new Task("url1", "product1", prices, latch, random));
        threadPool.submit(new Task("url2", "product2", prices, latch, random));
        threadPool.submit(new Task("url2", "product2", prices, latch, random));

        try {
            latch.await(3, TimeUnit.SECONDS);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdownNow();
        System.out.println(prices);
    }
    public static class Task implements Runnable {
        private String url;
        private String productId;
        private Set<Integer> prices;
        private CountDownLatch latch;
        private Random random;

        public Task(String url, String productId, Set<Integer> prices, CountDownLatch latch, Random random) {
            this.url = url;
            this.productId = productId;
            this.prices = prices;
            this.latch = latch;
            this.random = random;
        }

        @Override
        public void run() {
            Integer price = random.nextInt(10);
            try {
                Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
            latch.countDown();
        }
    }
}
