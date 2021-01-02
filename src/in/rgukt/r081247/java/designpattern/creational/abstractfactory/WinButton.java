package in.rgukt.r081247.java.designpattern.creational.abstractfactory;

public class WinButton implements Button {
    @Override
    public void paint() {
        System.out.println("WinButton paint()");
    }
}
