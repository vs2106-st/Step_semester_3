public class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment amount: " + amount);
            return;
        }
        this.amountPaid += amount;
    }

    public double getDue() {
        return totalFee - amountPaid;
    }

    public void payInTwoInstallments(double amount) {
        double installment = amount / 2.0;
        pay(installment);
        pay(installment);
    }

    public double effectiveDue(double scholarshipPercent) {
        double currentDue = getDue();
        return currentDue * (1 - (scholarshipPercent / 100.0));
    }

    public static void main(String[] args) {
        FeeAccount accountA = new FeeAccount("RA01", 200000);
        accountA.payInTwoInstallments(120000);
        System.out.println("Account A due: Rs " + accountA.getDue());

        FeeAccount accountB = new FeeAccount("RA02", 180000);
        System.out.println("Account B effective due (20% scholarship): Rs " + accountB.effectiveDue(20));
    }
}
