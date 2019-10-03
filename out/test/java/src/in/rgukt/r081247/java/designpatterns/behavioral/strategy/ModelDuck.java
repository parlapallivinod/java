package in.rgukt.r081247.java.designpatterns.behavioral.strategy;

public class ModelDuck extends Duck {

    public ModelDuck() {
        setFlyBehavior(new FlyNoWay());
        setQuackBehavior(new Quack());
    }
    @Override
    public void display() {
        System.out.println("I'm a model duck");
    }
}
