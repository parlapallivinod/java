package in.rgukt.r081247.java.practice;

public class Timer {
    private long start;
    private long stop;
    private boolean running;

    public Timer() {
        start = 0;
        stop = 0;
        running = false;
    }

    public Timer start() {
        if (running)
            return this;
        running = true;
        start = System.nanoTime();
        return this;
    }

    public Timer stop() {
        if (!running)
            return this;
        running = false;
        stop = System.nanoTime();
        return this;
    }

    /*
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Timer {\n\tTime Consumed : " + (stop - start) / 1_000_000_000.0 + " Seconds\n}");
        return sb.toString();
    }
     */

    @Override
    public String toString() {
        return "Timer{" +
                "duration=" + (stop - start) / 1_000_000_000.0 + " seconds" +
                ", start=" + start +
                ", stop=" + stop +
                ", running=" + running +
                '}';
    }
}

