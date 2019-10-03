package in.rgukt.r081247.java.designpatterns.structural.adaptor;


public class Main {
    public static void main(String[] args) {
        Turkey turkey = new WildTurkey();
        Duck duck = new TurkeyAdapter(turkey);

        duck.fly();
        duck.quack();
    }
}
