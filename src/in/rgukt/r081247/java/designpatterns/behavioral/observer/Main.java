package in.rgukt.r081247.java.designpatterns.behavioral.observer;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay observer1 = new CurrentConditionsDisplay(weatherData);
        CurrentConditionsDisplay observer2 = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(100, 150, 20);

        weatherData.removeObserver(observer1);
        weatherData.setMeasurements(50, 75, 10);
    }
}
