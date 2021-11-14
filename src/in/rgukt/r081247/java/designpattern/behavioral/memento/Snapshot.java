package in.rgukt.r081247.java.designpattern.behavioral.memento;

public class Snapshot {
    private Editor editor;
    private String text;
    private Integer curX, curY, selectionWidth;

    public Snapshot(Editor editor, String text, Integer x, Integer y, Integer selectionWidth) {
        this.editor = editor;
        this.text = text;
        this.curX = x;
        this.curY = y;
        this.selectionWidth = selectionWidth;
    }

    public void restore() {
        editor.setText(text);
        editor.setCursor(curX, curY);
        editor.setSelectionWidth(selectionWidth);
    }

}
