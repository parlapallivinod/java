package in.rgukt.r081247.java.algorithm.sorting;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NonRecursiveMergeSort {
    public static void main(String[] args) {
        List<Integer> list = Stream.iterate(100_000_000, i -> i - 1)
                .limit(100_000_000).collect(Collectors.toList());
        Integer[] nums = new Integer[list.size()];
        nums = list.toArray(nums);
        long start = System.currentTimeMillis();
        mergeSortBottomUp(nums, (i, j) -> i - j);
        System.out.println(System.currentTimeMillis() - start);
        //Arrays.stream(nums).forEach(x -> System.out.print(x + ", "));
    }

    /** Merges in[start..start+inc-1] and in[start+inc..start+2*inc-1] into out. */
    public static <K> void merge(K[] in, K[] out, Comparator<K> comp, int start, int inc) {
        int end1 = Math.min(start + inc, in.length);        // boundary for run 1
        int end2 = Math.min(start + 2 * inc, in.length);    // boundary for run 2
        int x = start;                                      // index into run 1
        int y = start + inc;                                // index into run 2
        int z = start;                                      // index into output
        while (x < end1 && y < end2) {
            if (comp.compare(in[x], in[y]) < 0)
                out[z++] = in[x++];                         // take next from run 1
            else
                out[z++] = in[y++];                         // take next from run 2
        }
        if (x < end1)
            System.arraycopy(in, x, out, z, end1 - x); // copy rest of run 1
        else if (y < end2)
            System.arraycopy(in, y, out, z, end2 - y); // copy rest of run 2
    }

    /** Merge-sort contents of data array. */
    public static <K> void mergeSortBottomUp(K[] orig, Comparator<K> comp) {
        int n = orig.length;
        K[] src = orig;                             // alias for the original
        K[] dest = (K[]) new Object[n];             // make a new temporary array
        K[] temp;                                   // reference used only for swapping

        for (int i = 1; i < n; i *= 2) {              // each iteration sorts all runs of length i
            for (int j = 0; j < n; j += 2 * i) {    // each pass merges two runs of length i
                merge(src, dest, comp, j, i);
            }
            temp = src;                             // reverse roles of the arrays
            src = dest;
            dest = temp;
        }

        if (orig != src) {
            System.arraycopy(src, 0, orig, 0, n); // additional copy to get results to original
        }
    }
}
