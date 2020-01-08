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
            new Thread(System.out::println).start();


    }

}
