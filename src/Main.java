import in.rgukt.r081247.java.datastructure.list.Dequeue;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Deque<Integer> a = new LinkedList<>();
        a.peek();
        a.add(1);
        a.add(-1);

        System.out.println(a);

    }
}
