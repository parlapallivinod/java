package in.rgukt.r081247.java;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

class Solution {
    public static class Counter {
        public long count = 0;
    }
    public static void main(String[] args) {
        try(StructuredTaskScope.ShutdownOnFailure scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Callable<Integer> c1 = () -> {
                System.out.println(Thread.currentThread());
                if(1 == 1)
                    throw new RuntimeException("exception");
                return 1;

            };
            Callable<Integer> c2 = () -> {
                System.out.println(Thread.currentThread());
                /*if(1 == 1)
                    throw new RuntimeException("exception");*/
                return 2;
            };

           Supplier<Integer> s1 =scope.fork(c1);
           Supplier<Integer> s2 =scope.fork(c2);
           scope.join();

           scope.throwIfFailed(e -> new RuntimeException(e));

            System.out.println(s1.get());
            System.out.println(s2.get());
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}