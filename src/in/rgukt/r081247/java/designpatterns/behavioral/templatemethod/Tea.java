package in.rgukt.r081247.java.designpatterns.behavioral.templatemethod;

public class Tea extends CaffeignBeverage {
    void brew() {
        System.out.println("Steeping the tea");
    }

    void addCondiments() {
        System.out.println("Adding the Lemon");
    }
}