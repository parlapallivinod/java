package in.rgukt.r081247.java.designpattern.creational.builder;

public class CarManualBuilder implements Builder{
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    private void reset() {
        this.type = null;
        this.seats = 0;
        this.engine = null;
        this.transmission = null;
        this.tripComputer = null;
        this.gpsNavigator = null;
    }

    @Override
    public void setCarType(CarType type) {
        this.type = type;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator gpsNavigator) {
        this.gpsNavigator = gpsNavigator;
    }

    public Manual getResult() {
        Manual manual =  new Manual(type, seats, engine, transmission, tripComputer, gpsNavigator);
        reset();
        return manual;
    }
}
