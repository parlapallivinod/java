/**
 * @author Vinod Parlapalli
 * Created on 2019/11/01
 *
 */

package in.rgukt.r081247.java.functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FP002_FlatMap {

    public static void main(String[] args) {
        List<String> boys = new ArrayList<>();
        boys.add("Ram");
        boys.add("Sam");

        List<String> girls = new ArrayList<>();
        girls.add("Sita");
        girls.add("Laxmi");

        List<List<String>> streams = new ArrayList<>();
        streams.add(boys);
        streams.add(girls);

        List<String> boysGirls = streams.stream().flatMap(children -> children.stream()).collect(Collectors.toList());
        System.out.println(boysGirls);
    }
}
