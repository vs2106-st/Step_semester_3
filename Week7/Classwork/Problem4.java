public interface PaymentMethod {
    void pay(double amount);

    default String generateReceipt(double amount) {
        return "Receipt generated for amount: $" + String.format("%.2f", amount);
    }

    static boolean validateAmount(double amount) {
        return amount > 0;
    }
}

class CreditCardPayment implements PaymentMethod {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        if (PaymentMethod.validateAmount(amount)) {
            System.out.println("Paid $" + amount + " using Credit Card ending in " + cardNumber.substring(Math.max(0, cardNumber.length() - 4)));
        }
    }
}

class UPIPayment implements PaymentMethod {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        if (PaymentMethod.validateAmount(amount)) {
            System.out.println("Paid $" + amount + " using UPI ID: " + upiId);
        }
    }

    @Override
    public String generateReceipt(double amount) {
        return "Instant UPI Receipt for $" + String.format("%.2f", amount) + " sent to registered mobile.";
    }
}
