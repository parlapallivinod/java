package in.rgukt.r081247.java.designpattern.behavioral.observer;

public class CurrentConditionsDisplay implements Observer {
    private Subject weatherData;
    private float humidity;
    private float temparature;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temparature = temp;
        this.humidity = humidity;

        System.out.println("temp: " + this.temparature + " humidity: " + this.humidity);
    }
}
