import in.rgukt.r081247.java.datastructure.list.Dequeue;
import in.rgukt.r081247.java.designpattern.creational.builder.*;
import in.rgukt.r081247.java.designpattern.creational.factory.Button;
import in.rgukt.r081247.java.designpattern.structural.bridge.Device;
import in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap.MergeKSortedLists;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Main {
    public static void main(String[] args) {

        I1 top1 = new C1();
        I1 top2 = new ACC1(top1);
        I1 top3 = new ACC2(top2);
        I1 top4 = new ACC1(top3);
        System.out.println(top4.cost());

    }

    public static interface I1 {
        public double cost();
    }

    public static class C1 implements I1 {
        private double price = 10;

        @Override
        public double cost() {
            System.out.println("C1 cost: " + price);
            return price;
        }
    }

    public static class C2 implements I1 {
        private double price = 11;

        @Override
        public double cost() {
            System.out.println("C1 cost: " + price);
            return price;
        }
    }

    public static abstract class AC implements I1 {

    }

    public static class ACC1 extends AC {
        private I1 inner;
        private double price = 0.5;

        public ACC1(I1 inner) {
            this.inner = inner;
        }

        @Override
        public double cost() {
            System.out.println("ACC1 cost: " + price);
            return price + inner.cost();
        }
    }

    public static class ACC2 extends AC {
        private I1 inner;
        private double price = 0.1;

        public ACC2(I1 inner) {
            this.inner = inner;
        }

        @Override
        public double cost() {
            System.out.println("ACC2 cost: " + price);
            return price + inner.cost();
        }
    }
}


