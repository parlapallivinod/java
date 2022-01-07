package in.rgukt.r081247.java.designpattern.behavioral.chainofresponsibility;

public class HandlerC extends BaseHandler {
    @Override
    public String handle(String request) {
        return "C" + super.handle(request);
    }
}
