package in.rgukt.r081247.java.designpatterns.behavioral.templatemethod;

public class Coffee extends CaffeignBeverage {
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}