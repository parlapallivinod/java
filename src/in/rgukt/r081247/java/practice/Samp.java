package in.rgukt.r081247.java.practice;

import java.util.stream.IntStream;

public class Samp {

    private static class CounterThread implements Runnable {
        private int from;
        private int count;

        public CounterThread(int from, int count) {
            this.from = from;
            this.count = count;
        }
        @Override
        public void run() {
            for (int i = from; i < from + count; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

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
