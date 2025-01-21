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
        String str = "aabbccdddeeeefffff";
        Map<String, Long> counts = str.chars()
                .filter(Character::isAlphabetic)
                .map(Character::toLowerCase)
                .mapToObj(Character::toString)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()));

        Map<Long, List<String>> c = counts.entrySet().stream()
                .collect(
                        Collectors.groupingBy(Map.Entry::getValue,
                                Collectors.mapping(Map.Entry::getKey,
                                        Collectors.toList())));
        List<Map.Entry<Long, List<String>>> g = c.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .toList();
        System.out.println(g);


        Map<Letter, LetterCount> cs = str.chars()
                .filter(Character::isAlphabetic)
                .mapToObj(i -> new Letter((char) i))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.collectingAndThen(Collectors.counting(), LetterCount::new)));
        Map<LetterCount, List<Letter>> map = cs.entrySet().stream()
                .map(LetterByCount::new)
                .collect(Collectors.groupingBy(
                        LetterByCount::count,
                        Collectors.mapping(LetterByCount::letter, Collectors.toList())));
        LettersByCount mostSeenLetter = map.entrySet().stream()
                .map(LettersByCount::new)
                .max(LettersByCount.comparingByCount())
                .orElseThrow();

        List<LettersByCount> sorted = map.entrySet().stream()
                .map(LettersByCount::new)
                .sorted(LettersByCount.comparingByCount().reversed())
                .toList();

        System.out.println(mostSeenLetter);
        System.out.println(sorted);

    }

}
