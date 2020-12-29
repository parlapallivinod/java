package in.rgukt.r081247.java.designpattern.creational.factory;

public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("WindowsButton render()");
    }

    @Override
    public void onClick() {
        System.out.println("WindowsButton onClick()");
    }
}
