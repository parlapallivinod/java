package in.rgukt.r081247.java.designpattern.structural.decorator;

public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }
    @Override
    public double cost() {
        return 1.99;
    }
}
