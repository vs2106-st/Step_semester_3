import java.util.Scanner;

public class SrmStudent {
    String name;
    String regNo;
    int attendance;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public boolean isEligible() {
        return this.attendance >= 75;
    }

    public static double classAverage(SrmStudent[] students) {
        if (students == null || students.length == 0) return 0.0;
        
        int totalAttendance = 0;
        for (SrmStudent student : students) {
            totalAttendance += student.attendance;
        }
        return (double) totalAttendance / students.length;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        SrmStudent[] students = new SrmStudent[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter details for Student " + (i + 1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Registration No: ");
            String regNo = scanner.nextLine();
            System.out.print("Attendance Percentage: ");
            int attendance = scanner.nextInt();
            scanner.nextLine();

            students[i] = new SrmStudent(name, regNo, attendance);
        }

        System.out.println("\n--- Student Status ---");
        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.name + " (" + s.regNo + ") - " + s.attendance + "% - " + status);
        }

        double avg = SrmStudent.classAverage(students);
        System.out.println("\nClass average: " + avg + "%");

        scanner.close();
    }
}
