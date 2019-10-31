package in.rgukt.r081247.java.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        List<List<Integer>> permutations = Permutations.getPermutations(list);
        int count = 1;
        for(List<Integer> permutation : permutations) {
            System.out.println(count + ": " + permutation);
            count++;
        }

    }

    private static <T> void permutate(List<T> in, List<T> out, List<List<T>> permutations) {
        if (in.size() == 0) {
            permutations.add(out);
            return;
        }

        for (int i = 0; i < in.size(); i++) {
            List<T> tempin = new ArrayList<>(in);
            List<T> tempout = new ArrayList<>(out);
            T obj = tempin.remove(i);
            tempout.add(obj);
            permutate(tempin, tempout, permutations);
        }
    }
    /**
     *
     * @author Vinod Parlapalli
     * @param list
     * @return
     */
    public static <T> List<List<T>> getPermutations(List<T> list) {
        List<List<T>> permutations = new ArrayList<>();
        List<T> out= new ArrayList<>();
        permutate(list, out, permutations);
        return permutations;
    }
}
