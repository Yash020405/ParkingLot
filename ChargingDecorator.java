public class ChargingDecorator implements PricingStrategy {
    private final PricingStrategy basePricingStrategy;
    private final double chargingFeePerHour;

    public ChargingDecorator(PricingStrategy basePricingStrategy, double chargingFeePerHour) {
        this.basePricingStrategy = basePricingStrategy;
        this.chargingFeePerHour = chargingFeePerHour;
    }

    @Override
    public double calculatePrice(Ticket ticket) {
        double basePrice = basePricingStrategy.calculatePrice(ticket);

        if (isElectricVehicleUsingChargingSpot(ticket)) {
            long durationInMillis = System.currentTimeMillis() - ticket.getEntryTime();
            long hours = (durationInMillis / (1000 * 60 * 60)) + 1;
            double chargingFee = chargingFeePerHour * hours;

            return basePrice + chargingFee;
        }

        return basePrice;
    }

    private boolean isElectricVehicleUsingChargingSpot(Ticket ticket) {
        Vehicle vehicle = ticket.getVehicle();
        ParkingSpot spot = ticket.getSpot();

        return (vehicle instanceof ElectricVehicle) &&
                (spot.getType() == SpotType.ELECTRIC) &&
                ((ElectricVehicle) vehicle).wantsCharging();
    }
}
