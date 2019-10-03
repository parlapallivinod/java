package in.rgukt.r081247.java.designpatterns.behavioral.strategy;

public class Main {

    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performFly();
        duck.performQuack();
        duck.display();

        duck = new ModelDuck();
        duck.performFly();
        duck.setFlyBehavior(new FlyWithWings());
        duck.performFly();
        duck.performQuack();
        duck.display();
    }
}
