package in.rgukt.r081247.java.designpattern.behavioral.strategy;

public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("I'am flying!!!");
    }
}
