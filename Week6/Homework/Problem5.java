import java.util.Arrays;

class FullEventTicket {
    private static int ticketCounter = 0;

    private final String internalCode;
    private String attendeeId;
    private double basePrice;
    private double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public FullEventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        ticketCounter++;
        this.internalCode = "TCK-" + ticketCounter;
        this.attendeeId = attendeeId.trim();
        this.basePrice = basePrice;
        this.amountPaid = 0.0;
    }

    public static int getTicketCounter() {
        return ticketCounter;
    }

    public String getInternalCode() {
        return internalCode;
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

    public void pay(double amount, String mode) {
        System.out.println("Processing payment via " + mode);
        pay(amount);
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

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'C') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(4));
    }

    public static String settleNightlyLedger(EventTicket[] tickets) {
        if (tickets == null) {
            return "0 processed | 0 null skipped | 0 workshop | 0 general";
        }

        int processed = 0;
        int nullSkipped = 0;
        int workshopCount = 0;
        int generalCount = 0;

        for (EventTicket ticket : tickets) {
            if (ticket == null) {
                nullSkipped++;
            } else {
                processed++;
                if (ticket instanceof WorkshopTicket) {
                    workshopCount++;
                } else {
                    generalCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + workshopCount + " workshop | " + generalCount + " general";
    }
}
