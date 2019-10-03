package in.rgukt.r081247.java.designpatterns.creational.abstractfactory;

public interface OSFactory {

    Phone create(ManufacturerType manufacturerType);
}