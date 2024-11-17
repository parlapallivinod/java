package in.rgukt.r081247.java.version.java23;

import java.io.IOException;
import java.util.*;
        import java.util.concurrent.*;
        import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ThreadingModelsPerformance {
    public static ExecutorService es1 = Executors.newFixedThreadPool(20);
    public static ExecutorService es2 = Executors.newFixedThreadPool(10);
    public static ExecutorService es3 = Executors.newFixedThreadPool(10);
    public static ExecutorService es4 = Executors.newVirtualThreadPerTaskExecutor();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*long start = System.currentTimeMillis();
        List<Future<Integer>> list = new ArrayList<>();
        for(int i = 1; i < 101; i++) {
            int j = i;
            list.add(es1.submit(()->normalThread(j)));
        }
        for(Future<Integer> f: list) {
           f.get();
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");*/

        /*long start = System.currentTimeMillis();
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        for(int i = 1; i < 101; i++)
            list.add(completableFutureSync(i));
        CompletableFuture.allOf(list.toArray(new CompletableFuture<?>[0])).join();
        System.out.println((System.currentTimeMillis() - start)+ " ms");*/

       /* long start = System.currentTimeMillis();
        List<CompletableFuture<Void>> list = new ArrayList<>();
        for(int i = 1; i < 101; i++)
            list.add(completableFutureAsync(i));
        CompletableFuture.allOf(list.toArray(new CompletableFuture<?>[0])).join();
        System.out.println((System.currentTimeMillis() - start) + " ms");*/

        long start = System.currentTimeMillis();
        List<Future<Integer>> list = new ArrayList<>();
        for(int i = 1; i < 101; i++) {
            int j = i;
            list.add(es4.submit(()->virtualThread(j)));
        }
        for(Future<Integer> f: list) {
            f.get();
        }
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    public static Integer normalThread(int i) {
        if(i == 1)
            System.out.println(Thread.currentThread() + ": " + i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(i == 1)
            System.out.println(Thread.currentThread() + ": " + i);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread() + ": " + i);
        return 1;
    }

    public static CompletableFuture<Integer> completableFutureSync(int i) {
        return CompletableFuture.supplyAsync(() -> {
            if(i == 1)
                System.out.println(Thread.currentThread()+ ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(i == 1)
                System.out.println(Thread.currentThread()+ ": " + i);
            return 0;
        }, es2).thenApplyAsync(j->{
            if(i == 1)
                System.out.println(Thread.currentThread()+ ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread()+ ": " + i);
            return 1;
        }, es3);
    }

    public static CompletableFuture<Void> completableFutureAsync(int i) {
        CompletableFuture<Integer> r1 = CompletableFuture.supplyAsync(() -> {
            if(i == 1)
                System.out.println(Thread.currentThread()+ ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(i == 1)
                System.out.println(Thread.currentThread()+ ": " + i);
            return 0;
        }, es2);

        CompletableFuture<Integer> r2 = CompletableFuture.supplyAsync(()->{
            if(i == 1)
                System.out.println(Thread.currentThread()+ ": " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread()+ ": " + i);
            return 1;
        }, es3);

        return CompletableFuture.allOf(r1, r2);
    }

    public static Integer virtualThread(int i) {
        if(i == 1)
            System.out.println(Thread.currentThread() + ": " + i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(i == 1)
            System.out.println(Thread.currentThread() + ": " + i);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread() + ": " + i);
        return 1;
    }
}

