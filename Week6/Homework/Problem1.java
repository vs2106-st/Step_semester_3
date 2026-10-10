import java.util.Arrays;

class EventTicket {
    private String attendeeId;
    private double basePrice;
    private double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.attendeeId = attendeeId.trim();
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void pay(double amount) {
        this.amountPaid += amount;
    }

    public double getBalanceDue() {
        double totalLateFees = 0.0;
        for (int i = 0; i < lateFeeCount; i++) {
            totalLateFees += lateFeeHistory[i];
        }
        return (basePrice + totalLateFees) - amountPaid;
    }

    protected void applyLateFee(double amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public String printTicket() {
        return "Standard Event Ticket | Balance Due: " + getBalanceDue();
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        if (attendeeIds != null) {
            for (String id : attendeeIds) {
                try {
                    new EventTicket(id, basePrice);
                    registered++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    private String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }

    public WorkshopTicket(double basePrice) {
        super("DUMMY_ID", basePrice);
    }

    public String getTrack() {
        return track;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    @Override
    public String printTicket() {
        return "Workshop Ticket | Track: " + track + " | Balance Due: " + getBalanceDue();
    }
}
