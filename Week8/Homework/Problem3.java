import java.util.*;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() { return roomNumber; }

    public abstract double calculatePrice(int days);
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 200.0;
    }
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * 150.0;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class Reservation {
    private Room room;
    private Customer customer;
    private String startDate;
    private String endDate;
    private int days;
    private boolean isCancelled = false;

    public Reservation(Room room, Customer customer, String startDate, String endDate, int days) {
        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
    }

    public Room getRoom() { return room; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public boolean isCancelled() { return isCancelled; }

    public double getTotalPrice() {
        return room.calculatePrice(days);
    }

    public void cancel() {
        this.isCancelled = true;
    }
}

class Hotel {
    private List<Reservation> reservations = new ArrayList<>();

    public Reservation bookRoom(Customer customer, Room room, String startDate, String endDate, int days) {
        for (Reservation r : reservations) {
            if (!r.isCancelled() && r.getRoom().getRoomNumber().equals(room.getRoomNumber())) {
                if (isOverlapping(r.getStartDate(), r.getEndDate(), startDate, endDate)) {
                    System.out.println("Booking failed: " + room.getRoomNumber() + " is not available for " + startDate + " to " + endDate + ".");
                    return null;
                }
            }
        }
        Reservation reservation = new Reservation(room, customer, startDate, endDate, days);
        reservations.add(reservation);
        System.out.println(room.getRoomNumber() + " booked from " + startDate + " to " + endDate + ". Total price: $" + String.format("%.2f", reservation.getTotalPrice()) + ".");
        return reservation;
    }

    public void cancelReservation(Reservation reservation) {
        if (reservation != null && !reservation.isCancelled()) {
            reservation.cancel();
            System.out.println("Reservation for " + reservation.getRoom().getRoomNumber() + " cancelled successfully.");
        }
    }

    private boolean isOverlapping(String start1, String end1, String start2, String end2) {
        return start1.compareTo(end2) < 0 && start2.compareTo(end1) < 0;
    }
}

public class Question3Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Customer customer = new Customer("Customer");

        Room deluxe101 = new DeluxeRoom("Deluxe Room 101");
        Room standard205 = new StandardRoom("Standard Room 205");

        Reservation r1 = hotel.bookRoom(customer, deluxe101, "2024-12-01", "2024-12-05", 4);
        Reservation r2 = hotel.bookRoom(customer, standard205, "2024-12-03", "2024-12-07", 4);

        hotel.bookRoom(customer, deluxe101, "2024-12-03", "2024-12-07", 4);

        hotel.cancelReservation(r1);
    }
}
