import java.util.Arrays;

class FullRaceEntry {
    private static int bibCounter = 0;

    private final String entryCode;
    private String bibNumber;
    private double entryFee;
    private double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public FullRaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }

        bibCounter++;
        this.entryCode = "EC-" + bibCounter;
        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.amountPaid = 0.0;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void pay(double amount) {
        this.amountPaid += amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        double totalLateFees = 0.0;
        for (int i = 0; i < lateFeeCount; i++) {
            totalLateFees += lateFeeHistory[i];
        }
        return (entryFee + totalLateFees) - amountPaid;
    }

    protected void applyLateFee(double amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }

        return Character.isUpperCase(code.charAt(4));
    }

    public static String settleNight(RaceEntry[] entries) {
        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
            } else {
                processed++;
                if (entry instanceof RelayTeamEntry) {
                    relayCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + relayCount + " relay | " + individualCount + " individual";
    }
}
