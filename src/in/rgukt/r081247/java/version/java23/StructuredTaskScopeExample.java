package in.rgukt.r081247.java.version.java23;

import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;
import java.util.function.Supplier;

public class StructuredTaskScopeExample {
    public static void structuredTaskScopeSuccessExample() {
        try(StructuredTaskScope.ShutdownOnSuccess<Integer> scope = new StructuredTaskScope.ShutdownOnSuccess<Integer>()) {
            Callable<Integer> c1 = () -> {
                System.out.println(Thread.currentThread());
               /* if(1 == 1)
                    throw new RuntimeException("exception");*/
                return 1;

            };
            Callable<Integer> c2 = () -> {
                System.out.println(Thread.currentThread());
                /*if(1 == 1)
                    throw new RuntimeException("exception");*/
                return 2;
            };

            StructuredTaskScope.Subtask<Integer> s1 =scope.fork(c1);
            StructuredTaskScope.Subtask<Integer> s2 =scope.fork(c2);
            scope.join();
            Integer result = scope.result(e -> new RuntimeException(e));

            System.out.println(result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void structuredTaskScopeFailureExample() {
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

    public static void structuredTaskScopeExample() {
        try(StructuredTaskScope<Integer> scope = new StructuredTaskScope<Integer>()) {
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

            StructuredTaskScope.Subtask<Integer> s1 =scope.fork(c1);
            StructuredTaskScope.Subtask<Integer> s2 =scope.fork(c2);
            scope.join();


            System.out.println(s1.state());
            if(s1.state() == StructuredTaskScope.Subtask.State.SUCCESS)
                System.out.println(s1.get());

            System.out.println(s2.state());
            if(s2.state() == StructuredTaskScope.Subtask.State.SUCCESS)
                System.out.println(s2.get());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        //structuredTaskScopeFailureExample();
        //structuredTaskScopeSuccessExample();
        structuredTaskScopeExample();
    }
}
