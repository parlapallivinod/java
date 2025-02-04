package in.rgukt.r081247.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Sample {

    static record Letter(char codePoint) {
        Letter(char codePoint) {
            this.codePoint = Character.toLowerCase(codePoint);
        }
    }
    static record LetterCount(long count) implements Comparable<LetterCount> {
        @Override
        public int compareTo(LetterCount o) {
            return Long.compare(this.count, o.count);
        }
    }
    static record LetterByCount(Letter letter, LetterCount count) {
        LetterByCount(Map.Entry<Letter, LetterCount> entry) {
            this(entry.getKey(), entry.getValue());
        }
    }
    static record LettersByCount(LetterCount count, List<Letter> letters) {
        LettersByCount(Map.Entry<LetterCount, List<Letter>> entry) {
            this(entry.getKey(), entry.getValue());
        }

        public static Comparator<? super LettersByCount> comparingByCount() {
            return Comparator.comparing(LettersByCount::count);
        }


    }
    public static void main(String[] args) throws Exception {
        Lock l = new ReentrantLock();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 start");
                l.lock();
                l.lock();
                l.unlock();
                System.out.println("t1 end");
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start");
                l.lock();
                System.out.println("t2 start");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}
