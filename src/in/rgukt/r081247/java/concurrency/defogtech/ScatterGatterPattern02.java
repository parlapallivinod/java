package in.rgukt.r081247.java.concurrency.defogtech;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.*;

public class ScatterGatterPattern02 {
    public static void main(String[] args) {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<>());
        Random random = new Random();

        CompletableFuture<Void> task1 =  CompletableFuture.runAsync(new Task("url1", "product1", prices, random));
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(new Task("url2", "product2", prices, random));
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(new Task("url2", "product2", prices, random));

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(task1, task2, task3);


        try {
            allTasks.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        System.out.println(prices);
    }
    public static class Task implements Runnable {
        private String url;
        private String productId;
        private Set<Integer> prices;
        private Random random;

        public Task(String url, String productId, Set<Integer> prices, Random random) {
            this.url = url;
            this.productId = productId;
            this.prices = prices;
            this.random = random;
        }

        @Override
        public void run() {
            Integer price = random.nextInt(10);
            try {
                Thread.sleep(random.nextInt(4) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prices.add(price);
        }
    }
}
