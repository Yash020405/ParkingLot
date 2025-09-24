import java.util.List;

public class FirstAvailableSlotStrategy implements SlotAllocationStrategy {

    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && canPark(vehicle.getType(), spot.getType())) {
                return spot;
            }
        }
        return null;
    }

    private boolean canPark(VehicleType vehicleType, SpotType spotType) {
        switch (vehicleType) {
            case BIKE:
            case ELECTRIC_BIKE:
                return spotType == SpotType.SMALL || spotType == SpotType.MEDIUM ||
                        spotType == SpotType.LARGE || spotType == SpotType.ELECTRIC;
            case CAR:
            case ELECTRIC_CAR:
                return spotType == SpotType.MEDIUM || spotType == SpotType.LARGE ||
                        spotType == SpotType.ELECTRIC;
            case BUS:
                return spotType == SpotType.LARGE;
            default:
                return false;
        }
    }
}
