/**
 * @author Vinod Parlapalli
 * Created on 2019/11/01
 * Used to compare time consumption with String concatenation and StringBuilder concatenation.
 */

package in.rgukt.r081247.java.util.comparison;

import in.rgukt.r081247.java.util.comparison.util.Timer;

import java.util.stream.IntStream;

public class C001_StringBuilderVsString {
    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();

        for (int k = 3; k <= 9; k++) {
            timer.start();
            String str = IntStream.rangeClosed(1, (int) Math.pow(10, k))
                    .mapToObj(i -> (Object) new StringBuilder("X"))
                    .reduce(new StringBuilder(""), (x, y) -> ((StringBuilder) x).append(y.toString())).toString();
            //System.out.println(str);
            System.out.println("Using StringBuilder(" + (int) Math.pow(10, k) + "): " + timer.stop());

            timer.start();
            str = IntStream.rangeClosed(1, (int) Math.pow(10, k))
                    .mapToObj(i -> ("X"))
                    .reduce("", (x, y) -> (x + y));
            //System.out.println(str);
            System.out.println("Using String(" + (int) Math.pow(10, k) + "): " + timer.stop());
        }
    }
}
