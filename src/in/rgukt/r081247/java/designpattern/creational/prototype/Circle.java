package in.rgukt.r081247.java.designpattern.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class Circle extends Shape{
    private int radius;

    protected Circle(String name, int radius) {
        super(name);
        this.radius = radius;
    }

    protected Circle(Circle clone) {
        super(clone);
        this.radius = clone.radius;
    }

    @Override
    public Circle clone() {
        return new Circle(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "name=" + super.getName() +
                ", radius=" + radius +
                '}';
    }
}
