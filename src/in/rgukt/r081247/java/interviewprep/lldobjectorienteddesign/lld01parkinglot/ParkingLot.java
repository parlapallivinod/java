package in.rgukt.r081247.java.interviewprep.lldobjectorienteddesign.lld01parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ParkingLot {
    private AtomicLong counter = new AtomicLong(1);
    private List<List<Place>> floors;
    int[] slots;

    public ParkingLot(int noOfFloors, int[] slots) {
        this.slots = slots;
        floors = new ArrayList<>(noOfFloors);
        for(int i = 0; i < noOfFloors; i++) {
            List<Place> floor = new ArrayList<>();
            for(VehicleType vehicleType: VehicleType.values()) {
                for(int j = 0; j < slots[vehicleType.ordinal()]; j++) {
                    Place place = new Place(counter.getAndIncrement(), i+1, vehicleType);
                    floor.add(place);
                }
            }
            floors.add(floor);
        }
    }

    public Long park(VehicleType vehicleType) {
        for(int i = 0; i < floors.size(); i++) {
            for(Place place: floors.get(i)) {
                if(place.getVehicleType().equals(vehicleType) && !place.getTaken()) {
                    place.setTaken(true);
                    System.out.println("Parked at: " + place);
                    return place.getId();
                }
            }
        }
        System.out.println("No Free Parking Slot Found");
        return null;
    }

    public boolean unPark(Long id) {
        for(int i = 0; i < floors.size(); i++) {
            for(Place place: floors.get(i)) {
                if(place.getId() == id) {
                    if(place.getTaken()) {
                        place.setTaken(false);
                        System.out.println("Freed Parking Slot: " + place);
                        return true;
                    } else {
                        System.out.println("Parking Slot is already Free: " + place);
                        return false;
                    }
                }
            }
        }
        System.out.println("Parking Slot Not Found with Id: " + id);
        return false;
    }

    public void printDetails() {
        System.out.println("Parking Lot Details");

        for(int i = 0; i < floors.size(); i++) {
            System.out.println("Floor: " + (i+1));
            int[] parked = new int[VehicleType.values().length];
            int[] free = new int[VehicleType.values().length];
            for(Place place: floors.get(i)) {
                if(place.getTaken())
                    parked[place.getVehicleType().ordinal()]++;
                else
                    free[place.getVehicleType().ordinal()]++;
            }
            for(VehicleType vehicleType: VehicleType.values()) {
                System.out.println("Vehicle Type: " + vehicleType.name() + ", Total Places: " + slots[vehicleType.ordinal()]
                        + ", Parked: " + parked[vehicleType.ordinal()] + ", Free: " + free[vehicleType.ordinal()]);
            }
        }
        System.out.println();
    }
}
