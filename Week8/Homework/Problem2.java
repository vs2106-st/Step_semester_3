import java.util.*;

abstract class Vehicle {
    private String licensePlate;
    private String model;
    private boolean isRented = false;

    public Vehicle(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
    }

    public String getLicensePlate() { return licensePlate; }
    public String getModel() { return model; }
    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }

    public abstract double calculateRentalCharge(int days);
}

class StandardCar extends Vehicle {
    public StandardCar(String licensePlate, String model) {
        super(licensePlate, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 50.0;
    }
}

class LuxuryCar extends Vehicle {
    public LuxuryCar(String licensePlate, String model) {
        super(licensePlate, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 100.0;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private int days;
    private boolean isActive;

    public Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
        this.isActive = true;
        vehicle.setRented(true);
    }

    public Vehicle getVehicle() { return vehicle; }
    public boolean isActive() { return isActive; }

    public double getTotalCharge() {
        return vehicle.calculateRentalCharge(days);
    }

    public void returnVehicle() {
        this.isActive = false;
        vehicle.setRented(false);
        System.out.println(vehicle.getModel() + " returned. Now available.");
    }
}

class RentalService {
    public Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (vehicle.isRented()) {
            System.out.println("Rental failed: " + vehicle.getModel() + " is currently rented.");
            return null;
        }
        Rental rental = new Rental(vehicle, customer, days);
        System.out.println(vehicle.getModel() + " rented for " + days + " days. Total charge: $" + String.format("%.2f", rental.getTotalCharge()));
        return rental;
    }
}

public class Question2Main {
    public static void main(String[] args) {
        RentalService service = new RentalService();
        Customer customer = new Customer("John");

        Vehicle luxuryCar = new LuxuryCar("LUX-01", "Luxury Car A");
        Vehicle standardCar = new StandardCar("STD-01", "Standard Car B");

        Rental r1 = service.rentVehicle(customer, luxuryCar, 3);
        Rental r2 = service.rentVehicle(customer, standardCar, 5);

        if (r1 != null) {
            r1.returnVehicle();
        }
    }
}
