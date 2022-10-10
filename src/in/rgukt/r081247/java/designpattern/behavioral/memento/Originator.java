package in.rgukt.r081247.java.designpattern.behavioral.memento;

public class Originator {
    private String state;

    public Originator(String state) {
        this.state = state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Memento save() {
        return new ConcreteMemento(state);
    }
    public void restore(Memento m) {
        ConcreteMemento cm = (ConcreteMemento) m;
        this.state = cm.getState();
    }

    public String toString() {
        return "Originator{" + "state: " + state + "}";
    }
}
