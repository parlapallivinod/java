package in.rgukt.r081247.java.designpattern.creational.factory;

public class Main {

    public static void main(String[] args) {
        Dialog windowsDialog = new WindowsDialog();
        windowsDialog.render();
        Dialog webDialog = new WebDialog();
        webDialog.render();
    }
}
