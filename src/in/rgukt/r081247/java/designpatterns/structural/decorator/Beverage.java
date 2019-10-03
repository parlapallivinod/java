package in.rgukt.r081247.java.designpatterns.structural.decorator;

public abstract class Beverage {
    String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
