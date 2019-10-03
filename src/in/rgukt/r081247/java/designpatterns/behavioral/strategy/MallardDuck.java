package in.rgukt.r081247.java.designpatterns.behavioral.strategy;

public class MallardDuck extends Duck {

    public MallardDuck() {
        setFlyBehavior(new FlyWithWings());
        setQuackBehavior(new Quack());
    }
    @Override
    public void display() {
        System.out.println("I'm a mallard duck");
    }
}
