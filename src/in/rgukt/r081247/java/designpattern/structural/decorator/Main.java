package in.rgukt.r081247.java.designpattern.structural.decorator;

public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        beverage = new Mocha(beverage);
        System.out.println(beverage.getDescription());
        beverage = new Whip(beverage);
        System.out.println(beverage.getDescription());
        System.out.println(beverage.cost());

        Beverage hb =  new HouseBlend();
        hb = new Whip(hb);
        System.out.println(hb.getDescription());
        hb = new Whip(hb);
        hb = new Mocha(hb);
        System.out.println(hb.getDescription());
        System.out.println(hb.cost());
    }
}