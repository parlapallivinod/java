package in.rgukt.r081247.java.designpatterns.behavioral.templatemethod;

public class Main {
    public static void main(String[] args) {
        CaffeignBeverage coffee = new Coffee();
        coffee.prepareRecipe();

        CaffeignBeverage tea = new Tea();
        tea.prepareRecipe();
    }
}