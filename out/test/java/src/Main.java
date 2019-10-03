import java.util.*;

public class Main {

    public static class Employee {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Employee(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Employee)) return false;
            Employee employee = (Employee) o;
            return Objects.equals(getName(), employee.getName());
        }

        public String toString() {
            return "Employee{hash: " + this.hashCode() + ", name: " + this.getName() + "}";
        }
    }

    public static void main(String[] args) {

        Employee e1 = new Employee("Vinod");
        Employee e2 = new Employee("Vinod");
        Employee e3 = new Employee("Vinod");
        Employee e4 = new Employee("Vinod");
        Employee e5 = new Employee("Vinod");

        System.out.println("e1 == e1 : " + e1.equals(e2));
        System.out.println("e1 hashcode: " + e1.hashCode() + " e2 hashcode: " + e2.hashCode());
        Map<Employee, Integer> map = new HashMap<>();
        map.put(e1, 1);
        map.put(e2, 2);
        map.put(e3, 2);
        map.put(e4, 2);
        map.put(e5, 2);
        System.out.println(map.size());

        for (Map.Entry<Employee, Integer> e : map.entrySet()) {
            System.out.println("Key: " + e.getKey() + ", Value: " + e.getValue());
        }


        Set<Employee> set = new HashSet<>();
        System.out.println(set.add(e1));
        System.out.println(set.add(e2));
        System.out.println(set.add(e3));
        System.out.println(set.add(e4));
        System.out.println(set.add(e5));

        for (Employee e : set) {
            System.out.println(e);
        }

    }
}
