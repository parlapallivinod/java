package in.rgukt.r081247.java.designpattern.behavioral.state;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        ConcreteState1 cs1 = new ConcreteState1(context);
        context.changeState(cs1);

        context.doThat();
        context.doThat();
        context.doThis();
        context.doThis();
        context.doThis();
        context.doThis();
        context.doThis();



    }
}
