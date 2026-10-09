import java.util.*;
public class Problem1 {
    static boolean IsVowel (char c){
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (int i = 0; i < vowels.length; i++){
            if (c == (vowels[i])){
                return true;
            }
        }
        return false;
    }
    static void countVowelsAndConsonants(String text){
        int vcount = 0, ccount = 0;
                for (int i = 0; i < text.length(); i++){
                    if (IsVowel(text.charAt(i))){
                        vcount++;
                    }
                    else if (text.charAt(i) != ' '){
                        ccount++;
                    }
                }
        System.out.println("Consonants: " + ccount + " | Vowels: "+ vcount);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);    
        System.out.println("Enter the String to Count Vowels and Consonants: ");
        String sentence = sc.nextLine();
        countVowelsAndConsonants(sentence);
        sc.close();
    }
}
