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
        List<Employee> list = new LinkedList<>();
        list.add(new Employee(1l, "s", 100l));
        list.add(new Employee(2l, "s", 200l));
        list.add(new Employee(3l, "n", 300l));

        /*
        Optional<Employee> emp = list.stream().skip(1).findFirst();
        if(emp.isPresent())
            System.out.println(emp);

         */

        /*
        Map<String, List<Long>> output = list.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.mapping(emp->emp.getSalary(), Collectors.toList())));
        System.out.println(output);
         */
        /*
        Map<String, List<Employee>> output = list.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.toList()));
        System.out.println(output);
         */

        /*
        Map<Boolean, List<Employee>> output = list.stream()
                .collect(Collectors.partitioningBy(e->e.getName().equals("s"), Collectors.toList()));
        System.out.println(output);

         */

        /*
        List<Employee> output = list.stream()
                .sorted(Comparator.comparing(Employee::getName).thenComparing(Comparator.comparing(Employee::getSalary)))
                .collect(Collectors.toList());
        System.out.println(output);
         */



        Map<Boolean, Optional<Employee>> map = list.stream()
                .collect(Collectors.partitioningBy(e->e.getName().equals("s"), Collectors.minBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(map);

    }
}


