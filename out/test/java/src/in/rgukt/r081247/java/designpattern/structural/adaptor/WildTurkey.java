package in.rgukt.r081247.java.designpattern.structural.adaptor;

public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gooble gooble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying short distance");
    }
}
