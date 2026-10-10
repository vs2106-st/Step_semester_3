class BusTicketAccount {
    private static String systemStatus;

    static {
        systemStatus = "RECONCILIATION_ENGINE_ACTIVE";
    }

    private final String bookingId;
    private final double ticketFare;
    private final BoardingPenaltyCalculator calculator;

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (bookingId == null || bookingId.trim().isEmpty() || ticketFare < 0) {
            throw new IllegalArgumentException("Invalid booking ID or ticket fare.");
        }
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
        this.calculator = new BoardingPenaltyCalculator(1.0);
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 500.0);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public final double calculatePenalty(int minutesLate) {
        return calculator.calculatePenalty(this.ticketFare, minutesLate);
    }

    public static void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        double penalty = account.calculatePenalty(minutesLate);
        double netSettlement = amount - penalty;
        System.out.println("Account " + account.getBookingId() + " settled. Net: Rs " + netSettlement + " (Penalty: Rs " + penalty + ")");
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            System.out.println("Batch aborted: missing input arrays.");
            return;
        }

        if (accounts.length != amounts.length || accounts.length != minutesLateArray.length) {
            System.out.println("Batch rejected: Input array lengths do not match.");
            return;
        }

        int processedCount = 0;
        int nullSkippedCount = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            BusTicketAccount acc = accounts[i];

            if (acc == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            double penalty = acc.calculatePenalty(minutesLateArray[i]);
            grandTotalPenalties += penalty;

            if (acc instanceof SleeperBusTicketAccount) {
                sleeperCount++;
                SleeperBusTicketAccount sleeperAcc = (SleeperBusTicketAccount) acc;
                sleeperAcc.settleSleeperAccount(amounts[i], minutesLateArray[i]);
            } else {
                regularCount++;
                processAccount(acc, amounts[i], minutesLateArray[i]);
            }
        }

        System.out.println(processedCount + " processed | " + nullSkippedCount + " null skipped | " 
            + sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = Rs " + grandTotalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperBusTicketAccount("BK001", 2000.0),
            null,
            new BusTicketAccount("BK002", 1200.0)
        };
        double[] amounts = {1200.0, 900.0, 700.0};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}

class SleeperBusTicketAccount extends BusTicketAccount {
    public SleeperBusTicketAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public void settleSleeperAccount(double amount, int minutesLate) {
        double penalty = calculatePenalty(minutesLate);
        double sleeperSurcharge = 50.0;
        double netSettlement = amount - penalty - sleeperSurcharge;
        System.out.println("Sleeper Account " + getBookingId() + " settled. Net: Rs " + netSettlement + " (Penalty: Rs " + penalty + ", Surcharge: Rs " + sleeperSurcharge + ")");
    }
}
