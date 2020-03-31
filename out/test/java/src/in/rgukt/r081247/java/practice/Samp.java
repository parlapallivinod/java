package in.rgukt.r081247.java.practice;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Samp {

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor) {
        return (key1, key2) -> keyExtractor.apply(key1).compareTo(keyExtractor.apply(key2));
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return (key1, key2) -> keyComparator.compare(keyExtractor.apply(key1), keyExtractor.apply(key2));
    }


    public static void main(String[] args) {

        Stream<String> strings = Stream.of("Z12", "Y1", "X");
        System.out.println(strings.sorted(comparing(String::length, (key1, key2) -> -(key1 - key2))).collect(Collectors.toList()));
    }

}
