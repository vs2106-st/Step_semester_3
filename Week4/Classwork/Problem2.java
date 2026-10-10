import java.util.Arrays;

class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0) {
            throw new IllegalArgumentException("Fare cannot be negative and passenger count must be greater than zero.");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {
        if (totalFare == 0.0) {
            double[] breakdown = new double[passengerCount];
            Arrays.fill(breakdown, 0.0);
            return breakdown;
        }

        long totalCents = Math.round(totalFare * 100);
        long baseShare = totalCents / passengerCount;
        long remainder = totalCents % passengerCount;

        double[] breakdown = new double[passengerCount];

        for (int i = 0; i < passengerCount; i++) {
            long share = baseShare;
            if (i >= passengerCount - remainder) {
                share += 1;
            }
            breakdown[i] = share / 100.0;
        }

        return breakdown;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        FareSplitter fs1 = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(fs1.fareBreakdown()));

        FareSplitter fs2 = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(fs2.fareBreakdown()));
    }
}
