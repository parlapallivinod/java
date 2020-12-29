package in.rgukt.r081247.java.designpattern.creational.factory;

public class HTMLButton implements Button {
    @Override
    public void render() {
        System.out.println("HTMLButton render()");
    }

    @Override
    public void onClick() {
        System.out.println("HTMLButton onClick()");
    }
}
