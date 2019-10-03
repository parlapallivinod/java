package in.rgukt.r081247.java.designpatterns.structural.decorator;

public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "House Blend Coffee";
    }
    @Override
    public double cost() {
        return 0.89;
    }
}
