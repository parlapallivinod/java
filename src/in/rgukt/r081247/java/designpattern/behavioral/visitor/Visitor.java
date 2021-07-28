package in.rgukt.r081247.java.designpattern.behavioral.visitor;

public interface Visitor {
    String visitDot(Dot dot);
    String visitCircle(Circle circle);
    String visitRectangle(Rectangle rectangle);
    String visitCompoundGraphic(CompoundShape cg);
}
