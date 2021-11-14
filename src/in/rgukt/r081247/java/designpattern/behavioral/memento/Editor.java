package in.rgukt.r081247.java.designpattern.behavioral.memento;

public class Editor {
    private String text;
    private Integer curX, curY, selectionWidth;

    public void setText(String text) {
        this.text = text;
    }
    public void setCursor(Integer x, Integer y) {
        this.curX = x;
        this.curY = y;
    }
    public void setSelectionWidth(Integer width) {
        this.selectionWidth = width;
    }

    public Snapshot creatSnapshot() {
        return new Snapshot(this, text, curX, curY, selectionWidth);
    }
}
