package in.rgukt.r081247.java.designpattern.behavioral.chainofresponsibility;

public class Client {
    public static void main(String[] args) {
        HandlerA a = new HandlerA();
        Handler b = new HandlerB();
        Handler c = new HandlerC();
        a.setNext(b);
        b.setNext(c);

        System.out.println(a.handle("reqeust"));
    }
}
