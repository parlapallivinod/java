package in.rgukt.r081247.java.designpatterns.behavioral.strategy;

public class FlyNoWay implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
}
