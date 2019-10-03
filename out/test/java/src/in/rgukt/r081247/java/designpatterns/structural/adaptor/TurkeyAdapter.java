package in.rgukt.r081247.java.designpatterns.structural.adaptor;

public class TurkeyAdapter implements Duck {
    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 1; i <= 5; i++) {
            turkey.fly();
        }
    }
}
