package in.rgukt.r081247.java.designpattern.behavioral.chainofresponsibility;

public interface Handler {
    public void setNext(Handler h);
    public String handle(String request);
}
