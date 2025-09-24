import java.util.List;

public interface SlotAllocationStrategy {
    ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle);
}