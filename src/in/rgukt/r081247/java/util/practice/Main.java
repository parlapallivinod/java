package in.rgukt.r081247.java.util.practice;

import java.util.*;

public class Main {
    public static void main(String[] args) {
       List<String> list = new ArrayList<>();
       list.add("One");
       list.add("Two");
       list.add("Three");
       list.add("Four");
       list.add("Five");
       System.out.println(list);

       list.remove("Two");
       System.out.println(list);

       list.remove(1);
       System.out.println(list);
    }
}