package in.rgukt.r081247.java.designpatterns.behavioral.strategy;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("I'am flying!!!");
    }
}
