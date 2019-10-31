package in.rgukt.r081247.java.designpattern.behavioral.command;

public class Main {
    public static void main(String[] args) {
       RemoteControl remoteControl = new RemoteControl();
       Light light = new Light();
       LightOnCommand lightOnComand = new LightOnCommand(light);
       LightOffCommand lightOffCommand = new LightOffCommand(light);

       remoteControl.setCommand(0, lightOnComand, lightOffCommand);

       remoteControl.onButtonWasPushed(0);
       remoteControl.offButtonWasPushed(0);
       remoteControl.undoButtonWasPushed();

       remoteControl.offButtonWasPushed(0);
       remoteControl.onButtonWasPushed(0);
       remoteControl.undoButtonWasPushed();

    }
}
