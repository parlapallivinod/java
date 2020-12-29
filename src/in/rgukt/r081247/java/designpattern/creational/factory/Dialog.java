package in.rgukt.r081247.java.designpattern.creational.factory;

public abstract class Dialog {
    protected abstract Button createButton();
    public void render() {
        Button okButton = createButton();
        okButton.onClick();
        okButton.render();
    }
}
