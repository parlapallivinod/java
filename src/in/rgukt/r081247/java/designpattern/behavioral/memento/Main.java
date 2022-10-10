package in.rgukt.r081247.java.designpattern.behavioral.memento;

public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator("");
        System.out.println(originator);
        CareTaker careTaker = new CareTaker(originator);

        careTaker.doSomething();
        originator.setState("a");
        System.out.println(originator);

        careTaker.doSomething();
        originator.setState("ab");
        System.out.println(originator);

        careTaker.doSomething();
        originator.setState("abc");
        System.out.println(originator);

        careTaker.undo();
        System.out.println(originator);

        careTaker.undo();
        System.out.println(originator);

        originator.setState("ad");
        System.out.println(originator);
        careTaker.doSomething();


        originator.setState("ade");
        System.out.println(originator);

        careTaker.undo();
        System.out.println(originator);

        careTaker.undo();
        System.out.println(originator);

        careTaker.undo();
        System.out.println(originator);

        careTaker.undo();
        System.out.println(originator);

        careTaker.undo();
        System.out.println(originator);





    }
}
