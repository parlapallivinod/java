package in.rgukt.r081247.java.designpattern.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public abstract class Shape implements Cloneable{

    private String name;

    private static Map<String, Shape> shapes = new HashMap<>();

    static {
        shapes.put("circle1", new Circle("circle1", 1));
        shapes.put("circle2", new Circle("circle2", 2));
        shapes.put("rectangle1", new Rectangle("rectangle1", 1, 1));
        shapes.put("rectangle2", new Rectangle("rectangle2", 2, 2));
    }

    public static Shape getShape(String name) {
        Shape shape = shapes.get(name);
        if (shape != null)
            return shape.clone();
        else
            return null;
    }

    protected Shape(String name) {
        this.name = name;
    }

    protected Shape(Shape clone) {
        this.name = clone.name;
    }

    public abstract Shape clone();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
