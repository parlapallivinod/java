package in.rgukt.r081247.java.designpattern.behavioral.state;

public class Context {
    private State state;
    
    public void changeState(State state) {
        this.state = state;
    }

    public void doThis() {
        state.doThis();
    }

    public void doThat() {
        state.doThat();
    }
}
