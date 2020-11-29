package in.rgukt.r081247.java.designpattern.creational.prototype;

import in.rgukt.r081247.java.designpattern.creational.singleton.Singleton;

public class Main {

    public static void main(String[] args) {
        Circle cir1 = (Circle) Shape.getShape("circle1");
        System.out.println(cir1);
        Circle cir2 = (Circle) Shape.getShape("circle2");
        System.out.println(cir2);
        Circle cir3 = (Circle) Shape.getShape("circle3");
        System.out.println(cir3);
        Circle cir22 = (Circle) Shape.getShape("circle2");
        System.out.println(cir2 == cir22);

        Rectangle rect1 = (Rectangle) Shape.getShape("rectangle1");
        System.out.println(rect1);
        Rectangle rect2 = (Rectangle) Shape.getShape("rectangle2");
        System.out.println(rect2);
        Rectangle rect3 = (Rectangle) Shape.getShape("rectangle3");
        System.out.println(rect3);
        Rectangle rect11 = (Rectangle) Shape.getShape("rectangle1");
        System.out.println(rect1 == rect11);
    }
}
