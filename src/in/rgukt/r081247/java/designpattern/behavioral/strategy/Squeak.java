package in.rgukt.r081247.java.designpattern.behavioral.strategy;

public class Squeak implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}
