package in.rgukt.r081247.java.interviewprep.lldobjectorienteddesign.lld01parkinglot;

public class Place {
    private Long id;
    private Long floor;
    private VehicleType vehicleType;
    private Boolean taken = false;

    public Place(long id, long floor, VehicleType vehicleType) {
        this.id = id;
        this.floor = floor;
        this.vehicleType = vehicleType;
    }

    public Long getId() {
        return id;
    }

    public Long getFloor() {
        return floor;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", floor=" + floor +
                ", vehicleType=" + vehicleType +
                ", taken=" + taken +
                '}';
    }
}
