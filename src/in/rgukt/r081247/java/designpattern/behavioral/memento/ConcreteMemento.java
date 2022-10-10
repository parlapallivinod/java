package in.rgukt.r081247.java.designpattern.behavioral.memento;

public class ConcreteMemento implements Memento {
    private String state;
    public ConcreteMemento(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }
}
