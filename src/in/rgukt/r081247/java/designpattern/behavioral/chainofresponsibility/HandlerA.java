package in.rgukt.r081247.java.designpattern.behavioral.chainofresponsibility;

public class HandlerA extends BaseHandler {
    @Override
    public String handle(String request) {
        return "A" + super.handle(request);
    }
}
