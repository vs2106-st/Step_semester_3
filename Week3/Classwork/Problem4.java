import java.util.Scanner;

public class SrmStudent {
    String name;
    String regNo;
    int attendance;

    static String university = "SRM Institute of Science and Technology";
    static int admissionCount = 0;

    public SrmStudent(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        admissionCount++;
        this.regNo = "RA2311003010" + (10 + admissionCount);
    }

    public void printIdCard() {
        System.out.println(name + " | " + regNo + " | " + university);
    }

    public static void printTotalAdmissions() {
        System.out.println("Students admitted so far: " + admissionCount);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students to admit: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        SrmStudent[] students = new SrmStudent[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Attendance: ");
            int attendance = scanner.nextInt();
            scanner.nextLine();

            students[i] = new SrmStudent(name, attendance);
        }

        System.out.println("\n--- ID Cards Generated ---");
        for (SrmStudent s : students) {
            s.printIdCard();
        }

        System.out.println();
        SrmStudent.printTotalAdmissions();

        scanner.close();
    }
}
