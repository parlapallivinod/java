package in.rgukt.r081247.java.designpattern.behavioral.mediator;

// The concrete mediator class. The intertwined web of
// connections between individual components has been untangled
// and moved into the mediator.
public class AthenticationDialog implements Mediator{
    private String title;
    private Checkbox loginOrRegisterChkBx;
    private Textbox loginUsername, loginPassword;
    private Textbox registrationUsername, registrationPassword, registrationEmail;
    private Button okBtn, cancelBtn;

    public AthenticationDialog() {
        // Create all component objects and pass the current
        // mediator into their constructors to establish links.
    }

    // When something happens with a component, it notifies the
    // mediator. Upon receiving a notification, the mediator may
    // do something on its own or pass the request to another
    // component.
    @Override
    public void notify(Component sender, String event) {
        if (sender == loginOrRegisterChkBx && event == "check") {
            if (loginOrRegisterChkBx.checked) {
                title = "Log in";
                // 1. Show login form components
                // 2. Hide registration form components
            } else {
                title = "Register";
                // 1. Show registration form components.
                // 2. Hide login form components
            }
        } else if (sender == okBtn && event == "click") {
            if (loginOrRegisterChkBx.checked) {
                // Try to find a user using login credentials.
            } else {
                // 1. Create a user account using data from the
                // registration fields.
                // 2. Log that user in.
                // ...
            }
        }
    }
}
