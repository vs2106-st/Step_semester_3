import java.util.*;
public class Problem3 {
    static String validateFileExtension(String filename){
        String[] extension = {"pdf", "docx" , "zip"};
        int index = filename.lastIndexOf('.');
        String ext = filename.substring(index + 1);
        for(int i = 0; i < extension.length; i++){
            if (ext.equalsIgnoreCase(extension[i])) return "Accepted";
        }
        return "Rejected - invalid file type ";
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter File Name: ");
        String file =sc.nextLine();
        System.out.println(validateFileExtension(file));
        sc.close();
    }
}
