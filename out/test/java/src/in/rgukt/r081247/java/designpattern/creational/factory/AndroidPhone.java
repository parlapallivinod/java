package in.rgukt.r081247.java.designpattern.creational.factory;

public class AndroidPhone extends Phone {

    @Override
    protected void createPhone() {
        specifications.add(new AndroidCamera());
        specifications.add(new AndroidPanel());
        specifications.add(new AndroidProcessor());
    }
}