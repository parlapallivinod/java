package in.rgukt.r081247.java;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Solution {

    private static ScopedValue<String> KEY = ScopedValue.newInstance();
    private static ScopedValue<String> KEY1 = ScopedValue.newInstance();
    private static ScopedValue<String> KEY2 = ScopedValue.newInstance();

    public static void main(String[] args) throws Exception {
        /*ScopedValue.CallableOp<String, RuntimeException> c = ()->{ return KEY.isBound()? KEY.get(): "Unbound"; };

        System.out.println(c.call());
        String restult = ScopedValue.where(KEY, "ABC").call(()->{ return KEY.isBound()? KEY.get(): "Unbound";});
        System.out.println(restult);
        String r2 = ScopedValue.where(KEY, "ABC").call(c);
        System.out.println(r2);*/
        //System.out.println(doCall());


        /*Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(KEY1.isBound()? KEY.get(): "KEY1 Unbound");
                System.out.println(KEY2.isBound()? KEY2.get(): "KEY2 Unbound");
                String ret = null;
                try(StructuredTaskScope<String> scope = new StructuredTaskScope<>()) {
                    StructuredTaskScope.Subtask<String> task1 = scope.fork(()->{
                        return KEY1.isBound()? KEY.get(): "KEY1 Unbound";
                    });
                    StructuredTaskScope.Subtask<String> task2 = scope.fork(()->{
                        return KEY2.isBound()? KEY2.get(): "KEY2 Unbound";
                    });

                    scope.join();
                    ret = task1.get();
                    ret += task2.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return ret;
            }
        };*/
        String result = ScopedValue.where(KEY1, "ABC").where(KEY2, "DEF").call(()-> {return doCall();});
        System.out.println(result);
        System.out.println(KEY1.isBound()? KEY1.get(): "KEY1 Unbound");
        System.out.println(KEY2.isBound()? KEY2.get(): "KEY2 Unbound");

    }

    public static String doCall() {
        System.out.println(KEY1.isBound()? KEY1.get(): "KEY1 Unbound");
        System.out.println(KEY2.isBound()? KEY2.get(): "KEY2 Unbound");
        String ret = null;
        try(StructuredTaskScope<String> scope = new StructuredTaskScope<>()) {
            StructuredTaskScope.Subtask<String> task1 = scope.fork(()->{
                return KEY1.isBound()? KEY1.get(): "KEY1 Unbound";
            });
            StructuredTaskScope.Subtask<String> task2 = scope.fork(()->{
                return KEY2.isBound()? KEY2.get(): "KEY2 Unbound";
            });

            scope.join();
            ret = task1.get();
            ret += task2.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

}




/*
(AB), (BC), (CD)
(ABC), (BCD)
(ABCD)
 */