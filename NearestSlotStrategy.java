import java.util.*;

public class NearestSlotStrategy implements SlotAllocationStrategy {
    private final Map<Integer, Integer> spotDistances;
    
    public NearestSlotStrategy() {
        spotDistances = new HashMap<>();
        // Hardcode distances - spot ID -> distance from gate
        spotDistances.put(1, 5);
        spotDistances.put(2, 8);
        spotDistances.put(3, 12);
        spotDistances.put(4, 15);
        spotDistances.put(5, 10);
        spotDistances.put(6, 7); 
    }
    
    @Override
    public ParkingSpot findSpot(List<ParkingSpot> spots, Vehicle vehicle) {
        
        TreeSet<ParkingSpot> sortedSpots = new TreeSet<>((s1, s2) -> {
            int d1 = getSpotDistance(s1.getId());
            int d2 = getSpotDistance(s2.getId());
            
            if (d1 != d2) {
                return Integer.compare(d1, d2);
            }
            return Integer.compare(s1.getId(), s2.getId());
        });
        
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && canPark(vehicle.getType(), spot.getType())) {
                sortedSpots.add(spot);
            }
        }
        
        return sortedSpots.isEmpty() ? null : sortedSpots.first();
    }
    
    private int getSpotDistance(int spotId) {
        return spotDistances.getOrDefault(spotId, 999);
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
