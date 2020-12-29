package in.rgukt.r081247.java.designpattern.creational.factory;

public class WindowsDialog extends Dialog {
    @Override
    protected Button createButton() {
        return new WindowsButton();
    }
}
