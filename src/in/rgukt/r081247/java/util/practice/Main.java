package in.rgukt.r081247.java.util.practice;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Sample.Builder builder1 = new Sample.Builder();
        Sample sample1 = builder1.field1("one").field2("two").build();
        System.out.println(sample1);

        Sample sample2 = builder1.field1("1").field2("2").build();
        System.out.println(sample2);
    }
}