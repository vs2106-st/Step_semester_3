public abstract class PaymentMethod {
    private static int counter = 1000;
    private final String transactionId;

    public PaymentMethod() {
        counter++;
        this.transactionId = "TXN-" + counter;
    }

    public abstract String processPayment(double amount);

    public String processPayment(double amount, String note) {
        return processPayment(amount) + " (" + note + ")";
    }

    public String getTransactionId() {
        return transactionId;
    }

    public static void printConfirmation(PaymentMethod payment, double amount) {
        if (payment != null) {
            System.out.println(payment.processPayment(amount));
        }
    }

    public static void testUpcasting() {
        // Upcasting: a CreditCardPayment reference stored as its parent abstract type PaymentMethod
        PaymentMethod ref = new CreditCardPayment("4471");
        printConfirmation(ref, 250.0);
    }
}

class CreditCardPayment extends PaymentMethod {
    private String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        super();
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {
        return "Charged $" + amount + " to card ending " + cardNumberLastFour + " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {
    public CashPayment() {
        super();
    }

    @Override
    public String processPayment(double amount) {
        return "Received $" + amount + " in cash - Txn " + getTransactionId();
    }
}
