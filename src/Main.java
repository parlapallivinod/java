import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.*;

public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new PriorityQueue<>((i1, i2) -> i1 - i2);
        q.offer(10);
        q.offer(9);
        q.offer(8);
        q.offer(7);
        q.offer(6);
        q.offer(5);
        q.offer(4);

        while (!q.isEmpty())
            System.out.println(q.poll());

    }
}
