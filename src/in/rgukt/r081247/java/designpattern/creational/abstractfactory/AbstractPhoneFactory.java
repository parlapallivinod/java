package in.rgukt.r081247.java.designpattern.creational.abstractfactory;

public abstract class AbstractPhoneFactory {

    public static OSFactory getFactory(OSType osType) {

        switch (osType) {
            case ANDROID:
                return new AndroidFactory();
            case WINDOWS:
                return new WindowsFactory();
            default:
                return null;
        }
    }
}
