import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Registration Number: ");
        String regNo = scanner.nextLine();

        System.out.print("Enter Total Fee: ");
        double totalFee = scanner.nextDouble();

        FeeAccount account = new FeeAccount(regNo, totalFee);

        System.out.print("Enter payment amount: ");
        double payment = scanner.nextDouble();

        System.out.print("Pay in two installments? (1 for Yes, 0 for No): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            account.payInTwoInstallments(payment);
        } else {
            account.pay(payment);
        }

        System.out.println("\nCurrent Due: Rs " + account.getDue());

        System.out.print("Enter Scholarship Percentage: ");
        double scholarship = scanner.nextDouble();
        System.out.println("Effective Due after Scholarship: Rs " + account.effectiveDue(scholarship));

        scanner.close();
    }
}
