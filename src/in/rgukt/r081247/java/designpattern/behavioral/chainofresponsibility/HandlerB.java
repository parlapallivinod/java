package in.rgukt.r081247.java.designpattern.behavioral.chainofresponsibility;

public class HandlerB extends BaseHandler {
    @Override
    public String handle(String request) {
        return "B" + super.handle(request);
    }
}
