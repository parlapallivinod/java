package in.rgukt.r081247.java.version.java23;

import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;

public class ScopedValueExample {
    private static ScopedValue<String> KEY = ScopedValue.newInstance();
    private static ScopedValue<String> KEY1 = ScopedValue.newInstance();
    private static ScopedValue<String> KEY2 = ScopedValue.newInstance();

    public static void main(String[] args) {
       // example1();
        example2();
    }

    public static void example1() {
        ScopedValue.CallableOp<String, RuntimeException> c = ()->{ return KEY.isBound()? KEY.get(): "Unbound"; };
        System.out.println(c.call());
        String restult = ScopedValue.where(KEY, "ABC").call(()->{ return KEY.isBound()? KEY.get(): "Unbound";});
        System.out.println(restult);
        String r2 = ScopedValue.where(KEY, "ABC").call(c);
        System.out.println(r2);
    }

    public static void example2() {
        String result = ScopedValue.where(KEY1, "ABC").where(KEY2, "DEF").call(()-> {return doCall();});
        System.out.println(result);
        System.out.println(KEY1.isBound()? KEY1.get(): "KEY1 Unbound");
        System.out.println(KEY2.isBound()? KEY2.get(): "KEY2 Unbound");
    }

    public static String doCall() {
        System.out.println(KEY1.isBound()? KEY1.get(): "KEY1 Unbound");
        System.out.println(KEY2.isBound()? KEY2.get(): "KEY2 Unbound");
        String ret = null;
        Callable<String> c1 = ()-> KEY1.isBound()? KEY1.get(): "KEY1 Unbound";
        Callable<String> c2 = ()-> KEY2.isBound()? KEY2.get(): "KEY2 Unbound";

        try(StructuredTaskScope<String> scope = new StructuredTaskScope<>()) {
            StructuredTaskScope.Subtask<String> task1 = scope.fork(c1);
            StructuredTaskScope.Subtask<String> task2 = scope.fork(c2);

            scope.join();
            ret = task1.get();
            ret += task2.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }


}
