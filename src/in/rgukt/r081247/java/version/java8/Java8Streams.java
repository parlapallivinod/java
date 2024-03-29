package in.rgukt.r081247.java.version.java8;

import java.util.*;
import java.util.stream.Collectors;

public class Java8Streams {
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


        /*
        Map<Boolean, Optional<Employee>> map = list.stream()
                .collect(Collectors.partitioningBy(e->e.getName().equals("s"), Collectors.minBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(map);
        */

        /*
        List<Employee> e = list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName))
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);
        */

        /*
        Map<String, Optional<Employee>> max = list.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
        System.out.println(max);
         */

        Map<String, Map<Long, List<Employee>>> res = list.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy(Employee::getSalary, Collectors.toList())));
        System.out.println(res);
    }

    public void nthHighest() {
        int nth = 6;
        List<Integer> list = List.of(10, 10, 9, 9, 8, 8, 7, 7, 6, 6, 11);
        Optional<Integer> nthmax = list.stream().distinct().sorted(Comparator.reverseOrder()).skip(nth - 1).findFirst();
        if(nthmax.isPresent())
            System.out.println(nthmax.get());
    }
}
