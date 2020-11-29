package in.rgukt.r081247.java.designpattern.creational.prototype;

public class Rectangle extends Shape{
    private int width;
    private int height;

    protected Rectangle(String name, int width ,int height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    protected Rectangle(Rectangle clone) {
        super(clone);
        this.width = clone.width;
        this.height = clone.height;
    }

    @Override
    public Rectangle clone() {
        return new Rectangle(this);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "name=" + super.getName() +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
