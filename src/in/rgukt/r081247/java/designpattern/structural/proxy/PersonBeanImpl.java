package in.rgukt.r081247.java.designpattern.structural.proxy;

public class PersonBeanImpl implements PersonBean {
    private String name;
    private int rating;
    private int ratingCount = 0;

    public String getName() {
        return name;
    }

    public int getHotOrNotRating() {
        if (ratingCount == 0)
            return 0;
        return rating / ratingCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotOrNotRating(int rating) {
        this.rating += rating;
        ratingCount++;
    }
}