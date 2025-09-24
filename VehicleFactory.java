public class VehicleFactory {

    public static Vehicle createVehicle(VehicleType type, String vehicleNo) {
        switch (type) {
            case BIKE:
                return new Bike(vehicleNo);
            case CAR:
                return new Car(vehicleNo);
            case BUS:
                return new Bus(vehicleNo);
            case ELECTRIC_BIKE:
                return new ElectricBike(vehicleNo, true);
            case ELECTRIC_CAR:
                return new ElectricCar(vehicleNo, true);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
