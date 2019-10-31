package in.rgukt.r081247.java.designpattern.structural.composite;

public class Main {
    public static void main(String[] args) {
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");

        MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
        allMenus.add(pancakeHouseMenu);

        MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
        dinerMenu.add(new MenuItem(
                "Pasta",
                "Pasta description",
                3.89,
                true
        ));
        MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");
        dessertMenu.add(new MenuItem(
                "Apple Pie",
                "Apple Pie description",
                1.59,
                true
        ));
        dinerMenu.add(dessertMenu);
        allMenus.add(dinerMenu);

        MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
        allMenus.add(cafeMenu);

        Waitress waitress = new Waitress(allMenus);
        waitress.printMenu();
    }
}