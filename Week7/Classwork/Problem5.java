public interface Servicable {
    void performMaintenance();
    boolean needsMaintenance();
}

public abstract class Vehicle {
    private static int fleetCounter = 0;
    private final String vehicleId;
    private String model;

    public Vehicle(String model) {
        fleetCounter++;
        this.vehicleId = "FLEET-" + fleetCounter;
        this.model = model;
    }

    public abstract double calculateFuelEfficiency();

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }
}

class ElectricCar extends Vehicle implements Servicable {
    private double batteryCapacity;
    private double kmDriven;
    private boolean maintenanceNeeded = false;

    public ElectricCar(String model, double batteryCapacity) {
        super(model);
        this.batteryCapacity = batteryCapacity;
        this.kmDriven = 0;
    }

    public void drive(double distanceKm) {
        this.kmDriven += distanceKm;
        if (kmDriven >= 10000) {
            maintenanceNeeded = true;
        }
    }

    @Override
    public double calculateFuelEfficiency() {
        return batteryCapacity > 0 ? kmDriven / batteryCapacity : 0;
    }

    @Override
    public void performMaintenance() {
        this.maintenanceNeeded = false;
        this.kmDriven = 0;
    }

    @Override
    public boolean needsMaintenance() {
        return maintenanceNeeded;
    }
}

class FuelTruck extends Vehicle implements Servicable {
    private double fuelCapacity;
    private double fuelConsumed;
    private double kmDriven;
    private boolean maintenanceNeeded = false;

    public FuelTruck(String model, double fuelCapacity) {
        super(model);
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumed = 0;
        this.kmDriven = 0;
    }

    public void drive(double distanceKm, double fuelUsed) {
        this.kmDriven += distanceKm;
        this.fuelConsumed += fuelUsed;
        if (kmDriven >= 15000) {
            maintenanceNeeded = true;
        }
    }

    @Override
    public double calculateFuelEfficiency() {
        return fuelConsumed > 0 ? kmDriven / fuelConsumed : 0;
    }

    @Override
    public void performMaintenance() {
        this.maintenanceNeeded = false;
        this.kmDriven = 0;
        this.fuelConsumed = 0;
    }

    @Override
    public boolean needsMaintenance() {
        return maintenanceNeeded;
    }
}

class FleetManager {
    public static void serviceAllEligible(Servicable[] items) {
        if (items == null) return;
        for (Servicable item : items) {
            if (item != null && item.needsMaintenance()) {
                item.performMaintenance();
            }
        }
    }
}
