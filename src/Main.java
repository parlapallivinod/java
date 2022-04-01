import in.rgukt.r081247.java.datastructure.list.Dequeue;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of",
                "strings", "to", "use", "as", "a", "demo");
        Map<Boolean, List<String>> oddEvenStrings = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length()%2 == 0));
        System.out.println(oddEvenStrings);
        Map<Integer, List<String>> lengthStrings = strings.stream()
                .collect(Collectors.groupingBy(s -> s.length()));
        System.out.println(lengthStrings);

        Map<Boolean, Long> oddEvenStrings1 = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length()%2 == 0, Collectors.counting()));
        System.out.println(oddEvenStrings1);
        Map<Integer, List<String>> lengthStrings1 = strings.stream()
                .collect(Collectors.groupingBy(s -> s.length(), Collectors.mapping(Function.identity(), Collectors.toList())));
        System.out.println(lengthStrings1);

        Map<Integer, Set<String>> lengthStrings3 = strings.stream()
                .collect(Collectors.groupingBy(s -> s.length(), Collectors.mapping(Function.identity(), Collectors.toSet())));
        System.out.println(lengthStrings3);

        Map<Integer, Optional<String>> list = strings.stream()
                .collect(Collectors.groupingBy(s -> s.length(), Collectors.minBy(Comparator.naturalOrder())));
        System.out.println(list);
    }
}
