package in.rgukt.r081247.java.designpattern.creational.abstractfactory;

public class AndroidFactory implements OSFactory {

    public Phone create(ManufacturerType manufacturerType) {
        switch (manufacturerType) {
            case GOOGLE:
                return new GooglePhone();
            case ONEPLUS:
                return new OnePlus5T();

            default:
                return null;
        }
    }
}