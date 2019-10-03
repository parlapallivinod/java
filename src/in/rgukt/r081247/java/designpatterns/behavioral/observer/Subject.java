package in.rgukt.r081247.java.designpatterns.behavioral.observer;

public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
