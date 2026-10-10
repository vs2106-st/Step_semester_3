class DeliveryAccount {
    private static String systemStatus;

    static {
        systemStatus = "RECONCILIATION_ENGINE_ACTIVE";
    }

    private final String studentId;
    private final double orderValue;
    private final SurgeFeeCalculator calculator;

    public DeliveryAccount(String studentId, double orderValue) {
        if (studentId == null || studentId.trim().isEmpty() || orderValue < 0) {
            throw new IllegalArgumentException("Invalid student ID or order value.");
        }
        this.studentId = studentId;
        this.orderValue = orderValue;
        this.calculator = new SurgeFeeCalculator(1.0);
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 300.0);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public final double calculateSurgeFee(int delayMinutes) {
        return calculator.calculateSurgeFee(this.orderValue, delayMinutes);
    }

    public static void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        double surgeFee = account.calculateSurgeFee(delayMinutes);
        double netSettlement = amount + surgeFee;
        System.out.println("Account " + account.getStudentId() + " settled. Net: Rs " + netSettlement + " (Surge Fee: Rs " + surgeFee + ")");
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            System.out.println("Batch aborted: missing input arrays.");
            return;
        }

        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            System.out.println("Batch rejected: Input array lengths do not match.");
            return;
        }

        int processedCount = 0;
        int nullSkippedCount = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        for (int i = 0; i < accounts.length; i++) {
            DeliveryAccount acc = accounts[i];

            if (acc == null) {
                nullSkippedCount++;
                continue;
            }

            processedCount++;
            double surgeFee = acc.calculateSurgeFee(delayMinutesArray[i]);
            grandTotalSurgeFees += surgeFee;

            if (acc instanceof PremiumDeliveryAccount) {
                premiumCount++;
                PremiumDeliveryAccount premiumAcc = (PremiumDeliveryAccount) acc;
                premiumAcc.settlePremiumAccount(amounts[i], delayMinutesArray[i]);
            } else {
                regularCount++;
                processAccount(acc, amounts[i], delayMinutesArray[i]);
            }
        }

        System.out.println(processedCount + " processed | " + nullSkippedCount + " null skipped | " 
            + premiumCount + " premium | " + regularCount + " regular | grand total surge fees = Rs " + grandTotalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new PremiumDeliveryAccount("STU001", 500.0),
            null,
            new DeliveryAccount("STU002", 300.0)
        };
        double[] amounts = {500.0, 400.0, 300.0};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}

class PremiumDeliveryAccount extends DeliveryAccount {
    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public void settlePremiumAccount(double amount, int delayMinutes) {
        double surgeFee = calculateSurgeFee(delayMinutes) * 0.5; // Premium gets 50% discount on surge fee
        double netSettlement = amount + surgeFee;
        System.out.println("Premium Account " + getStudentId() + " settled. Net: Rs " + netSettlement + " (Discounted Surge Fee: Rs " + surgeFee + ")");
    }
}
