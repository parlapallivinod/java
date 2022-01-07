package in.rgukt.r081247.java.designpattern.behavioral.chainofresponsibility;

public class BaseHandler implements Handler {
    protected Handler next;
    @Override
    public void setNext(Handler h) {
        this.next  =h;
    }

    @Override
    public String handle(String request) {
        if (next != null)
            return next.handle(request);
        return request;
    }
}
