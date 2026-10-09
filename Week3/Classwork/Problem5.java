import java.util.Scanner;

class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    public FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0.0;
    }

    public void pay(double amount) {
        if (amount > 0) {
            this.amountPaid += amount;
        }
    }

    public double getDue() {
        return totalFee - amountPaid;
    }

    public void payInTwoInstallments(double amount) {
        pay(amount / 2.0);
        pay(amount / 2.0);
    }

    public double effectiveDue(double scholarshipPercent) {
        return getDue() * (1 - (scholarshipPercent / 100.0));
    }
}

class HostelRoom {
    String roomNo;

    public HostelRoom(String roomNo) {
        this.roomNo = roomNo;
    }
}

class SrmStudent {
    String name;
    String regNo;
    FeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    public SrmStudent(String name, String regNo, FeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }

    public String fullStatus() {
        return name + " (" + regNo + ") | Due: Rs " + feeAccount.getDue() + " | Room: " + room.roomNo;
    }
}

public class CapstoneSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of student entries: ");
        int total = scanner.nextInt();
        scanner.nextLine();

        SrmStudent[] students = new SrmStudent[total];

        for (int i = 0; i < total; i++) {
            System.out.println("\n--- Enter Details for Student " + (i + 1) + " ---");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Reg No: ");
            String regNo = scanner.nextLine();

            System.out.print("Total Fee: ");
            double totalFee = scanner.nextDouble();

            System.out.print("Amount Paid: ");
            double paid = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Hostel Room No: ");
            String roomNo = scanner.nextLine();

            FeeAccount fee = new FeeAccount(regNo, totalFee);
            fee.pay(paid);

            HostelRoom room = new HostelRoom(roomNo);
            students[i] = new SrmStudent(name, regNo, fee, room);
        }

        System.out.println("\n================ STUDENT RECORDS ================");
        for (SrmStudent s : students) {
            System.out.println(s.fullStatus());
        }

        System.out.println("\nTotal Registered Students: " + SrmStudent.totalStudents);

        scanner.close();
    }
}
