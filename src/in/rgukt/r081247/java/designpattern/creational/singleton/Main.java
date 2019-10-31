package in.rgukt.r081247.java.designpattern.creational.singleton;

public class Main {

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        System.out.println(s1);

        Singleton s2 = Singleton.getInstance();
        System.out.println(s2);

        Singleton s3 = Singleton.getInstance();
        System.out.println(s3);
    }
}
