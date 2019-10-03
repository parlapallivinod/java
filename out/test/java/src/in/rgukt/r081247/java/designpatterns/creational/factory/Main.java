package in.rgukt.r081247.java.designpatterns.creational.factory;

public class Main {

    public static void main(String[] args) {
        Phone android = PhoneFactory.getPhone(PhoneType.ANDROID);
        Phone iphone = PhoneFactory.getPhone(PhoneType.IPHONE);
        Phone iphone2 = PhoneFactory.getPhone(PhoneType.IPHONE);

        System.out.println(android);
        System.out.println(iphone);
        System.out.println(iphone2);
    }
}
