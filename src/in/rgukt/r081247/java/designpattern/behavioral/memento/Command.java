package in.rgukt.r081247.java.designpattern.behavioral.memento;

public class Command {
    private Editor editor;
    private Snapshot backup;

    public void makeBackup() {
        backup = editor.creatSnapshot();
    }

    public void undo() {
        if (backup != null)
            backup.restore();
    }
}
