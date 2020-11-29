package in.rgukt.r081247.java.algorithm.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayMergeSort {
    /** Merge contents of arrays s1, s2 into properly sized array s. */
    public static <K> void merge(K[] s1, K[] s2, K[] s, Comparator<K> comp) {
        int i = 0, j = 0;
        while (i + j < s.length) {
            if (i == s1.length) {
                s[i+j] = s2[j++];
            } else if (j == s2.length) {
                s[i+j] = s1[i++];
            } else if (comp.compare(s1[i], s2[j]) < 0) {
                s[i+j] = s1[i++];
            } else {
                s[i+j] = s2[j++];
            }
        }
    }

    /** Merge-sort contents of array S. */
    public static <K> void mergeSort(K[] s, Comparator<K> comp) {
        int n = s.length;
        if (n < 2)
            return;
        int mid = n / 2;
        K[] s1 = Arrays.copyOfRange(s, 0, mid);
        K[] s2 = Arrays.copyOfRange(s, mid, n);
        mergeSort(s1, comp);
        mergeSort(s2, comp);
        merge(s1, s2, s, comp);
    }

    public static void main(String[] args) {
        List<Integer> list = Stream.iterate(100_000_000, i -> i - 1)
                .limit(100_000_000).collect(Collectors.toList());
        Integer[] nums = new Integer[list.size()];
        nums = list.toArray(nums);
        long start = System.currentTimeMillis();
        mergeSort(nums, (i, j) -> i - j);
        System.out.println(System.currentTimeMillis() - start);
        //Arrays.stream(nums).forEach(x -> System.out.print(x + ", "));
    }
}
