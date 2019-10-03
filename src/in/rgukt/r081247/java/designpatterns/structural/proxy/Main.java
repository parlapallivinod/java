package in.rgukt.r081247.java.designpatterns.structural.proxy;

import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {
        PersonBean person = new PersonBeanImpl();
        person.setName("Vinod");

        PersonBean ownerProxy = getOwnerProxy(person);
        //ownerProxy.setHotOrNotRating(10);
        System.out.println("Name: " + ownerProxy.getName());
        ownerProxy.setName("Harish");
        System.out.println("Name: " + ownerProxy.getName());
        System.out.println("Rating: " + ownerProxy.getHotOrNotRating());

        PersonBean nonOwnerProxy = getNonOwnerProxy(person);
        nonOwnerProxy.setHotOrNotRating(6);
        nonOwnerProxy.setHotOrNotRating(8);
        System.out.println("Rating: " + nonOwnerProxy.getHotOrNotRating());
        System.out.println("Name: " + nonOwnerProxy.getName());
        nonOwnerProxy.setName("Harish");
    }

    public static PersonBean getOwnerProxy(PersonBean person) {
        return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
    }

    public static PersonBean getNonOwnerProxy(PersonBean person) {
        return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
    }
}