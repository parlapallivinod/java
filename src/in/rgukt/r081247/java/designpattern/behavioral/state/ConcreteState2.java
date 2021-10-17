package in.rgukt.r081247.java.designpattern.behavioral.state;

public class ConcreteState2 implements State {
    private Context context;

    public ConcreteState2(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void doThis() {
        System.out.println("ConcreteState2.doThis()");
        context.changeState(new ConcreteState1(context));
    }

    @Override
    public void doThat() {
        System.out.println("ConcreteState2.doThat()");
    }
}
