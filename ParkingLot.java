import java.util.*;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final SlotAllocationStrategy allocationStrategy;
    private final PricingStrategy pricingStrategy;
    
    private ParkingLot(List<ParkingFloor> floors, SlotAllocationStrategy allocationStrategy, PricingStrategy pricingStrategy) {
        this.floors = floors;
        this.allocationStrategy = allocationStrategy;
        this.pricingStrategy = pricingStrategy;
    }
    
    public Ticket parkVehicle(Vehicle vehicle) {
        List<ParkingSpot> allSpots = new ArrayList<>();
        for (ParkingFloor floor : floors) {
            allSpots.addAll(floor.getSpots());
        }
        
        ParkingSpot spot = allocationStrategy.findSpot(allSpots, vehicle);
        if (spot != null) {
            spot.park(vehicle);
            return new Ticket(System.currentTimeMillis(), vehicle, spot);
        }
        
        return null;
    }
    
    public double unparkVehicle(Ticket ticket) {
        ParkingSpot spot = ticket.getSpot();
        spot.vacate();
        return pricingStrategy.calculatePrice(ticket);
    }
    
    public static class Builder {
        private List<ParkingFloor> floors;
        private SlotAllocationStrategy allocationStrategy;
        private PricingStrategy pricingStrategy;
                
                
        public Builder floors(List<ParkingFloor> floors) {
            this.floors = floors;
            return this;
        }
        
        public Builder allocationStrategy(SlotAllocationStrategy strategy) {
            this.allocationStrategy = strategy;
            return this;
        }
        
        public Builder pricingStrategy(PricingStrategy strategy) {
            this.pricingStrategy = strategy;
            return this;
        }
        
        public ParkingLot build() {
            return new ParkingLot(floors, allocationStrategy, pricingStrategy);
        }
    }
}
