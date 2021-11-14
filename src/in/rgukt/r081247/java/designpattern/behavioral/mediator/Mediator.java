package in.rgukt.r081247.java.designpattern.behavioral.mediator;

// The mediator interface declares a method used by components
// to notify the mediator about various events. The mediator may
// react to these events and pass the execution to other
// components.
public interface Mediator {
    public void notify(Component sender, String event);
}
