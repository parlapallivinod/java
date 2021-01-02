package in.rgukt.r081247.java.designpattern.creational.abstractfactory;

public class ApplicationConfigurator {
    public static void main(String[] args) {
        Application app = new Application(new WinFactory());
        app.createUI();
        app.paint();
    }
}
