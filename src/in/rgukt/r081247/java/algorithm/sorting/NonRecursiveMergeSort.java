package in.rgukt.r081247.java.algorithm.sorting;

import java.util.Comparator;

public class NonRecursiveMergeSort {
    public static void main(String[] args) {

    }

    public static <K> void merge(K[] in, K[] out, Comparator<K> comp, int start, int inc) {
        int end1 = Math.min(start + inc, in.length);
        int end2 = Math.min(start + 2 * inc, in.length);
        int x = start;
        int y = start + inc;
        int z = start;
        while (x < end1 && y < end2) {
            if (comp.compare(in[x], in[y]) < 0)
                out[z++] = in[x++];
            else
                out[z++] = in[y++];
        }
        if (x < end1)
            System.arraycopy(in, x, out, z, end1 - x);
        else if (y < end2)
            System.arraycopy(in, y, out, z, end2 - y);
    }
}
