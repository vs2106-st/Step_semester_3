import java.util.*;
public class Problem4 {
    static boolean allDigit(String phone){
        for (int i = 0; i < phone.length(); i++){
            int ascii = (int)phone.charAt(i);
            if(ascii >= 48 && ascii <= 57){
                continue;
            } else {
                return false;
            }
        }
        return false;
    }
    static String maskPhoneNumber(String phone){
        if (phone.length() != 10 || allDigit(phone)) return "Invalid Phone Number";
        StringBuilder masked = new StringBuilder();
        masked.append("XXXXXX-");
        masked.append(phone.substring(6));
        return masked.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Phone Number: ");
        String Phone = sc.nextLine();
        System.out.println(maskPhoneNumber(Phone));
        sc.close();
    }
}
