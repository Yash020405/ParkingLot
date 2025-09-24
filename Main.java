import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new ParkingSpot(1, SpotType.SMALL));
        floor1Spots.add(new ParkingSpot(2, SpotType.SMALL));
        floor1Spots.add(new ParkingSpot(3, SpotType.MEDIUM));
        floor1Spots.add(new ParkingSpot(4, SpotType.LARGE));
        floor1Spots.add(new ParkingSpot(5, SpotType.ELECTRIC));
        floor1Spots.add(new ParkingSpot(6, SpotType.ELECTRIC));

        ParkingFloor floor1 = new ParkingFloor(1, floor1Spots);
        List<ParkingFloor> floors = Arrays.asList(floor1);

        System.out.println("=== Parking Lot Demo ===");

        // Using Decorator Pattern: Base pricing + Charging decorator
        PricingStrategy basePricing = new HourlyPricingStrategy(10.0);
        PricingStrategy pricingWithCharging = new ChargingDecorator(basePricing, 5.0); // $5/hour for charging

        ParkingLot lot = new ParkingLot.Builder()
                .floors(floors)
                .allocationStrategy(new FirstAvailableSlotStrategy())
                .pricingStrategy(pricingWithCharging)
                .build();

        EntryGate entryGate = new EntryGate(lot);
        ExitGate exitGate = new ExitGate(lot);

        runDemo(entryGate, exitGate);
    }

    private static void runDemo(EntryGate entryGate, ExitGate exitGate) {
        Vehicle bike1 = new Bike("BIKE-123");
        Vehicle bike2 = new Bike("BIKE-456");
        Vehicle car1 = new Car("CAR-789");

        Ticket t1 = entryGate.generateTicket(bike1);
        if (t1 != null) {
            System.out.println("Bike1 parked at spot " + t1.getSpot().getId());
        }

        Ticket t2 = entryGate.generateTicket(bike2);
        if (t2 != null) {
            System.out.println("Bike2 parked at spot " + t2.getSpot().getId());
        }

        Ticket t3 = entryGate.generateTicket(car1);
        if (t3 != null) {
            System.out.println("Car parked at spot " + t3.getSpot().getId());
        }

        Vehicle bike3 = new Bike("BIKE-999");
        Ticket t4 = entryGate.generateTicket(bike3);
        if (t4 != null) {
            System.out.println("Bike3 parked at spot " + t4.getSpot().getId());
        }

        Vehicle eCar = new ElectricCar("ECAR-001", true);
        Ticket t5 = entryGate.generateTicket(eCar);
        if (t5 != null) {
            System.out.println(
                    "Electric car parked at spot " + t5.getSpot().getId() + " (Type: " + t5.getSpot().getType() + ")");
        }

        Vehicle eBike = new ElectricBike("EBIKE-001", true);
        Ticket t6 = entryGate.generateTicket(eBike);
        if (t6 != null) {
            System.out.println(
                    "Electric bike parked at spot " + t6.getSpot().getId() + " (Type: " + t6.getSpot().getType() + ")");
        }

        Vehicle bus = new Bus("BUS-001");
        Ticket t7 = entryGate.generateTicket(bus);
        if (t7 != null) {
            System.out
                    .println("Bus parked at spot " + t7.getSpot().getId() + " (Type: " + t7.getSpot().getType() + ")");
        }

        Vehicle extraCar = new Car("CAR-999");
        Ticket t8 = entryGate.generateTicket(extraCar);
        if (t8 == null) {
            System.out.println("Extra car could not be parked - no spots available");
        }

        if (t1 != null) {
            double price = exitGate.processExit(t1);
            System.out.println("Bike1 exited, price = $" + String.format("%.2f", price));
        }

        if (t5 != null) {
            double price = exitGate.processExit(t5);
            System.out.println(
                    "Electric car exited, price = $" + String.format("%.2f", price) + " (includes charging fee)");
        }

        Ticket t9 = entryGate.generateTicket(extraCar);
        if (t9 != null) {
            System.out.println("Extra car now parked at spot " + t9.getSpot().getId());
        }
    }
}
