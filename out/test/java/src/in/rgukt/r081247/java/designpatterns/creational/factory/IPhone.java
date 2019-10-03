package in.rgukt.r081247.java.designpatterns.creational.factory;

public class IPhone extends Phone {
    protected void createPhone() {
        specifications.add(new IPhoneCamera());
        specifications.add(new IPhonePanel());
        specifications.add(new IPhoneProcessor());
    }
}