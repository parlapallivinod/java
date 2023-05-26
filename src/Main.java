import in.rgukt.r081247.java.datastructure.list.Dequeue;
import in.rgukt.r081247.java.designpattern.creational.builder.*;
import in.rgukt.r081247.java.designpattern.creational.factory.Button;
import in.rgukt.r081247.java.designpattern.structural.bridge.Device;
import in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap.MergeKSortedLists;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Main {
    public static class Employee {
        public Long id;
        public String name;
        public Long salary;

        public Employee(Long id, String name, Long salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getSalary() {
            return salary;
        }

        public void setSalary(Long salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");
        if(s1 == s2)
            System.out.println("s1 == s2");
        if(s2 == s3)
            System.out.println("s2 == s3");
        if(s3 == s4)
            System.out.println("s3 == s4");

    }
}


