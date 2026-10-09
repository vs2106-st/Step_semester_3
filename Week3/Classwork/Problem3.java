import java.util.Scanner;

public class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    public HostelRoom() {
        this.occupied = 0;
    }

    public HostelRoom(String roomNo, int beds) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = 0;
    }

    public void allot(String studentName) {
        if (occupied < beds) {
            occupied++;
            System.out.println(studentName + " allotted to room " + roomNo);
        } else {
            System.out.println("Room " + roomNo + " is full! " + studentName + " added to waiting list.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Room Number: ");
        String roomNo = scanner.nextLine();

        System.out.print("Enter Bed Capacity: ");
        int beds = scanner.nextInt();
        scanner.nextLine();

        HostelRoom room = new HostelRoom(roomNo, beds);

        System.out.print("Enter number of students to allot: ");
        int studentCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < studentCount; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String studentName = scanner.nextLine();
            room.allot(studentName);
        }

        System.out.println("\nTotal Occupied Beds: " + room.occupied + " / " + room.beds);

        scanner.close();
    }
}
