package in.rgukt.r081247.java.designpattern.behavioral.state;

public class ConcreteState1 implements State {
    private Context context;

    public ConcreteState1(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void doThis() {
        System.out.println("ConcreteState1.doThis()");
        context.changeState(new ConcreteState2(context));
    }

    @Override
    public void doThat() {
        System.out.println("ConcreteState1.doThat()");
    }
}
