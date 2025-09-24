public class ExitGate {
    private ParkingLot lot;

    public ExitGate(ParkingLot lot) {
        this.lot = lot;
    }

    public void setLot(ParkingLot lot) {
        this.lot = lot;
    }

    public double processExit(Ticket ticket) {
        return lot.unparkVehicle(ticket);
    }
}