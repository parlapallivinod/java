package in.rgukt.r081247.java.designpattern.behavioral.memento;

import java.util.Deque;
import java.util.LinkedList;

public class CareTaker {
    private Originator originator;
    Deque<Memento> history;

    public CareTaker(Originator originator) {
        this.originator = originator;
        history = new LinkedList<>();
    }

    public void doSomething() {
        Memento m = originator.save();
        history.push(m);
    }

    public void undo() {
        if(history.isEmpty())
            return;
        Memento m = history.pop();
        originator.restore(m);
    }
}
