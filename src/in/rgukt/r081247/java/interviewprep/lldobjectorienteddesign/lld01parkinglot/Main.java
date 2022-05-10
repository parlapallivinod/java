package in.rgukt.r081247.java.interviewprep.lldobjectorienteddesign.lld01parkinglot;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(3, new int[] {10, 10});
        parkingLot.printDetails();

        Long bike1 = parkingLot.park(VehicleType.BIKE);
        parkingLot.printDetails();

        Long car1 = parkingLot.park(VehicleType.CAR);
        parkingLot.printDetails();

        parkingLot.unPark(bike1);
        parkingLot.printDetails();

        parkingLot.unPark(bike1);
        parkingLot.printDetails();

        parkingLot.unPark(car1);
        parkingLot.printDetails();

        for(int i = 0; i < 33; i++) {
            parkingLot.park(VehicleType.BIKE);
            parkingLot.park(VehicleType.CAR);
        }

        parkingLot.printDetails();

    }
}
