import java.util.Arrays;

class RaceEntry {
    private String bibNumber;
    private double entryFee;
    private double amountPaid;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;
        this.amountPaid = 0.0;
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

    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;

        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Registered: " + registered + " | Rejected: " + rejected;
    }
}

class RunnerEntry extends RaceEntry {
    private String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + getBalanceDue();
    }
}
