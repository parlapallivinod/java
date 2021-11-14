package in.rgukt.r081247.java.designpattern.behavioral.mediator;

public class Checkbox extends Component {
    public boolean checked;

    public Checkbox(Mediator dialog) {
        super(dialog);
    }

    public void check() {
        dialog.notify(this, "check");
    }
}
