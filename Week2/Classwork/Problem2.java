import java.util.*;
public class Problem2 {
    static void parseStudentRecord(String csvLine){
        String[] parts = csvLine.split(",");
        if (parts.length != 3) System.out.println("Invalid Input");
        else {
            System.out.println("Name: " + parts[0] + " | Roll No: " + parts[1] + " | Department: " + parts [2]);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the CSV Line: ");
        String csv = sc.nextLine();
        parseStudentRecord(csv);
        sc.close();
    }
}
