public class EntryGate {
    private ParkingLot lot;

    public EntryGate(ParkingLot lot) {
        this.lot = lot;
    }

    public void setLot(ParkingLot lot) {
        this.lot = lot;
    }

    public Ticket generateTicket(Vehicle vehicle) {
        return lot.parkVehicle(vehicle);
    }
}