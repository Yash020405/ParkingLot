public class Ticket {
    private final long entryTime;
    private final Vehicle vehicle;
    private final ParkingSpot spot;

    public Ticket(long entryTime, Vehicle vehicle, ParkingSpot spot) {
        this.entryTime = entryTime;
        this.vehicle = vehicle;
        this.spot = spot;
    }

    // Getters
    public long getEntryTime() { return entryTime; }
    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}