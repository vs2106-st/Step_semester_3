import java.util.*;

enum TicketCategory {
    STUDENT(100.0, 0.50),
    FACULTY(150.0, 0.20),
    GENERAL(200.0, 0.0);

    private final double basePrice;
    private final double discountRate;

    TicketCategory(double basePrice, double discountRate) {
        this.basePrice = basePrice;
        this.discountRate = discountRate;
    }

    public double getPrice() {
        return basePrice * (1.0 - discountRate);
    }
}

enum OrderStatus {
    CREATED, PAID, CANCELLED
}

class Ticket {
    private static int counter = 1000;
    private String ticketId;
    private TicketCategory category;

    public Ticket(TicketCategory category) {
        this.ticketId = "TICK-" + (++counter);
        this.category = category;
    }

    public String getTicketId() { return ticketId; }
    public TicketCategory getCategory() { return category; }
    public double getPrice() { return category.getPrice(); }
}

class Event {
    private String name;
    private int availableSeats;

    public Event(String name, int totalSeats) {
        this.name = name;
        this.availableSeats = totalSeats;
    }

    public String getName() { return name; }
    public int getAvailableSeats() { return availableSeats; }

    public boolean reserveSeats(int count) {
        if (availableSeats >= count) {
            availableSeats -= count;
            return true;
        }
        return false;
    }

    public void releaseSeats(int count) {
        availableSeats += count;
    }
}

class Order {
    private static int orderCounter = 5000;
    private String orderId;
    private Event event;
    private List<Ticket> tickets = new ArrayList<>();
    private OrderStatus status;

    public Order(Event event, Map<TicketCategory, Integer> requestedTickets) {
        this.orderId = "ORD-" + (++orderCounter);
        this.event = event;
        this.status = OrderStatus.CREATED;

        int totalRequested = 0;
        for (int count : requestedTickets.values()) {
            totalRequested += count;
        }

        if (event.reserveSeats(totalRequested)) {
            for (Map.Entry<TicketCategory, Integer> entry : requestedTickets.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    tickets.add(new Ticket(entry.getKey()));
                }
            }
            System.out.println("Order " + orderId + " created for " + event.getName() + " (" + totalRequested + " tickets). Total: ₹" + String.format("%.2f", getTotalAmount()) + ".");
        } else {
            System.out.println("Booking failed: Not enough seats available for " + event.getName() + ".");
        }
    }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }

    public double getTotalAmount() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.getPrice();
        }
        return total;
    }

    public void pay() {
        if (status == OrderStatus.CREATED) {
            status = OrderStatus.PAID;
            System.out.println("Payment successful for " + orderId + ". Tickets issued.");
        } else {
            System.out.println("Payment failed: Order " + orderId + " is already " + status + ".");
        }
    }

    public void cancel() {
        if (status == OrderStatus.CREATED) {
            status = OrderStatus.CANCELLED;
            event.releaseSeats(tickets.size());
            System.out.println("Order " + orderId + " cancelled. Seats released.");
        } else if (status == OrderStatus.PAID) {
            System.out.println("Cancellation failed: Paid order " + orderId + " cannot be cancelled.");
        }
    }
}

public class Question4Main {
    public static void main(String[] args) {
        Event techFest = new Event("TechFest 2026", 5);

        Map<TicketCategory, Integer> req1 = new HashMap<>();
        req1.put(TicketCategory.STUDENT, 2);
        req1.put(TicketCategory.FACULTY, 1);
