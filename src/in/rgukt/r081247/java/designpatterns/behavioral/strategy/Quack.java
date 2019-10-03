package in.rgukt.r081247.java.designpatterns.behavioral.strategy;

public class Quack implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
