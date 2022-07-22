import in.rgukt.r081247.java.datastructure.list.Dequeue;
import in.rgukt.r081247.java.designpattern.creational.builder.*;
import in.rgukt.r081247.java.designpattern.creational.factory.Button;
import in.rgukt.r081247.java.interviewprep.codingdsalgooops.heap.MergeKSortedLists;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;



public class Main {
    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilder();
        CarManualBuilder carManualBuilder = new CarManualBuilder();
        Director director = new Director();

        director.constructCityCar(carBuilder);
        Car car = carBuilder.getResult();

        director.constructCityCar(carManualBuilder);
        Manual manual = carManualBuilder.getResult();

        System.out.println(car.print());
        System.out.println(manual.print());

        director.constructSportsCar(carBuilder);
        car = carBuilder.getResult();

        director.constructSportsCar(carManualBuilder);
        manual = carManualBuilder.getResult();

        System.out.println(car.print());
        System.out.println(manual.print());


    }

    public static class Car {
        private final int seats;
        private final Engine engine;

        public Car(int seats, Engine engine) {
            this.seats = seats;
            this.engine = engine;
        }

        public int getSeats() {
            return seats;
        }
        public Engine getEngine() {
            return engine;
        }

        public String print() {
            String info = "Car\n";
            info += "Count of seats: " + seats + "\n";
            info += "Engine: volume - " + engine.getVolume() + "; mileage - " + engine.getMileage() + "\n";
            return info;
        }
    }

    public static class Manual {
        private final int seats;
        private final Engine engine;

        public Manual(int seats, Engine engine) {
            this.seats = seats;
            this.engine = engine;
        }

        public String print() {
            String info = "Manual\n";
            info += "Count of seats: " + seats + "\n";
            info += "Engine: volume - " + engine.getVolume() + "; mileage - " + engine.getMileage() + "\n";
            return info;
        }
    }

    public static class Engine {
        private final double volume;
        private double mileage;
        private boolean started;

        public Engine(double volume, double mileage) {
            this.volume = volume;
            this.mileage = mileage;
        }

        public void on() {
            started = true;
        }

        public void off() {
            started = false;
        }

        public boolean isStarted() {
            return started;
        }

        public void go(double mileage) {
            if (started) {
                this.mileage += mileage;
            } else {
                System.err.println("Cannot go(), you must start engine first!");
            }
        }

        public double getVolume() {
            return volume;
        }

        public double getMileage() {
            return mileage;
        }
    }

    public static interface Builder {
        void reset();
        void setSeats(int seats);
        void setEngine(Engine engine);
    }

    public static class CarBuilder implements Builder {
        private int seats;
        private Engine engine;

        @Override
        public void reset() {
            this.seats = 0;
            this.engine = null;
        }

        @Override
        public void setSeats(int seats) {
            this.seats = seats;
        }

        @Override
        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        public Car getResult() {
            return new Car(seats, engine);
        }
    }

    public static class CarManualBuilder implements Builder {
        private int seats;
        private Engine engine;

        @Override
        public void reset() {
            seats = 0;
            engine = null;
        }

        @Override
        public void setSeats(int seats) {
            this.seats = seats;
        }

        @Override
        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        public Manual getResult() {
            return new Manual(seats, engine);
        }
    }

    public static class Director {
        public void constructSportsCar(Builder builder) {
            builder.reset();
            builder.setSeats(2);
            builder.setEngine(new Engine(3.0, 0));
        }

        public void constructCityCar(Builder builder) {
            builder.reset();
            builder.setSeats(2);
            builder.setEngine(new Engine(1.2, 0));
        }

        public void constructSUV(Builder builder) {
            builder.reset();
            builder.setSeats(4);
            builder.setEngine(new Engine(2.5, 0));
        }
    }
}


