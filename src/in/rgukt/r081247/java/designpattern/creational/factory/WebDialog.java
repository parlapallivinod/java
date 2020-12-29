package in.rgukt.r081247.java.designpattern.creational.factory;

public class WebDialog extends Dialog {
    @Override
    protected Button createButton() {
        return new HTMLButton();
    }
}
