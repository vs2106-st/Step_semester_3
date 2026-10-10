import java.util.*;

enum Status {
    BOOKED, PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED, CANCELLED
}

interface ShippingType {
    double calculateCharge(double weightKg);
    String getName();
}

class StandardShipping implements ShippingType {
    @Override
    public double calculateCharge(double weightKg) {
        return 40.0 + (10.0 * weightKg);
    }
    @Override
    public String getName() { return "Standard"; }
}

class ExpressShipping implements ShippingType {
    @Override
    public double calculateCharge(double weightKg) {
        return 80.0 + (15.0 * weightKg);
    }
    @Override
    public String getName() { return "Express"; }
}

class FragileShipping implements ShippingType {
    private StandardShipping base = new StandardShipping();
    @Override
    public double calculateCharge(double weightKg) {
        return base.calculateCharge(weightKg) + 50.0;
    }
    @Override
    public String getName() { return "Fragile"; }
}

interface NotificationChannel {
    void notify(String parcelId, Status status);
}

class SmsChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, Status status) {
        System.out.println("[SMS] " + parcelId + " is now " + status + ".");
    }
}

class EmailChannel implements NotificationChannel {
    @Override
    public void notify(String parcelId, Status status) {
        System.out.println("[Email] " + parcelId + " is now " + status + ".");
    }
}

class Parcel {
    private String id;
    private double weightKg;
    private ShippingType shippingType;
    private Status status;
    private List<NotificationChannel> channels = new ArrayList<>();

    public Parcel(String id, double weightKg, ShippingType shippingType) {
        this.id = id;
        this.weightKg = weightKg;
        this.shippingType = shippingType;
        this.status = Status.BOOKED;
    }

    public String getId() { return id; }
    public Status getStatus() { return status; }

    public void subscribe(NotificationChannel channel) {
        channels.add(channel);
    }

    private void notifyChannels() {
        for (NotificationChannel channel : channels) {
            channel.notify(id, status);
        }
    }

    public double getCharge() {
        return shippingType.calculateCharge(weightKg);
    }

    public void book() {
        System.out.println("Parcel " + id + " booked (" + shippingType.getName() + ", " + (int)weightKg + " kg). Charge: ₹" + String.format("%.2f", getCharge()) + ".");
        notifyChannels();
    }

    public void updateStatus(Status newStatus) {
        if (isValidTransition(status, newStatus)) {
            status = newStatus;
            notifyChannels();
        } else {
            System.out.println("Invalid transition: " + status + " → " + newStatus + " is not allowed.");
        }
    }

    public void cancel() {
        if (status == Status.BOOKED) {
            status = Status.CANCELLED;
            notifyChannels();
        } else {
            System.out.println("Cancellation failed: " + id + " can be cancelled only while BOOKED.");
        }
    }

    private boolean isValidTransition(Status current, Status next) {
        switch (current) {
            case BOOKED: return next == Status.PICKED_UP;
            case PICKED_UP: return next == Status.IN_TRANSIT;
            case IN_TRANSIT: return next == Status.OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY: return next == Status.DELIVERED;
            default: return false;
        }
    }
}

public class Question2Main {
    public static void main(String[] args) {
        Parcel p101 = new Parcel("P101", 2.0, new ExpressShipping());
        p101.subscribe(new SmsChannel());
        p101.subscribe(new EmailChannel());

        p101.book();
        p101.updateStatus(Status.PICKED_UP);
        p101.cancel();
        p101.updateStatus(Status.IN_TRANSIT);
        p101.updateStatus(Status.DELIVERED);
    }
}
