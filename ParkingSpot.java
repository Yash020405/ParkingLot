public class ParkingSpot {
    private final int id;
    private final SpotType type;
    private Vehicle currentVehicle;

    public ParkingSpot(int id, SpotType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public SpotType getType() {
        return type;
    }

    public boolean isAvailable() {
        return currentVehicle == null;
    }

    public void park(Vehicle vehicle) {
        if (!isAvailable())
            throw new IllegalStateException("Spot already occupied!");
        this.currentVehicle = vehicle;
    }

    public void vacate() {
        this.currentVehicle = null;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}
