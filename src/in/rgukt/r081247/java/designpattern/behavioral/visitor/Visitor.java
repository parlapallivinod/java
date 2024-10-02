package in.rgukt.r081247.java.designpattern.behavioral.visitor;

public interface Visitor {
    String visit(Dot dot);
    String visit(Circle circle);
    String visit(Rectangle rectangle);
    String visit(CompoundShape cg);
}
