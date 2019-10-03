package in.rgukt.r081247.java.designpatterns.creational.builder;

public class Main {

    public static void main(String[] args) {
        Phone.Builder builder = new Phone.Builder()
                .backPanel("Sandstone")
                .frontPanel("AMOLED")
                .camera("12 MP")
                .processor("Snapdragon 625");

        Phone phone = builder.build();

        System.out.println(phone);
    }
}
