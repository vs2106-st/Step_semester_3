import java.util.*;
public class Problem5{
    static String normalizeReference(String s) {
        s = s.trim();
        return s.substring(0, 3).toUpperCase() + s.substring(3);
    }
    static String validateAndFormat(String s) {
        if (s.length() != 14) return "Invalid: wrong length";
        for (int i = 0; i < 3; i++)
            if (!Character.isLetter(s.charAt(i)))
                return "Invalid: bank code must be 3 letters";
        for (int i = 3; i < 14; i++)
            if (!Character.isDigit(s.charAt(i)))
                return "Invalid: body must contain only digits";
        String d = s.substring(3, 9);
        String bank = s.substring(0,3);
        return new StringBuilder("[").append(bank.toUpperCase())
                .append("] DATE: ").append(d.substring(0, 2)).append("/")
                .append(d.substring(2, 4)).append("/")
                .append(d.substring(4)).append(" | SEQ: ")
                .append(s.substring(9)).toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Transaction ID: ");
        String id = sc.nextLine();
        System.out.println(validateAndFormat(id));
    }
}
