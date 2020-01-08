/**
 * @author Vinod Parlapalli
 * Created on 2019/11/01
 *
 */

package in.rgukt.r081247.java.functionalprogramming;

public class FP001_Runnable {
    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }
        };

        Thread thread = new Thread(runnable, "runnable");
        thread.start();
        System.out.println(thread);
    }
}
