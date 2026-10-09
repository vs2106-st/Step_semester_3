import java.util.*;
public class Problem5 {
    static void printFilteredWordFrequency(String feedback) {
        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");
        String[] words = feedback.split("\\s+");
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        HashMap<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            boolean isStopWord = false;
            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }
            if (!isStopWord) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }
        while (!frequency.isEmpty()) {
            String highestWord = "";
            int highestCount = 0;
            for (String word : frequency.keySet()) {
                if (frequency.get(word) > highestCount) {
                    highestWord = word;
                    highestCount = frequency.get(word);
                }
            }
            System.out.println(highestWord + ": " + highestCount);
            frequency.remove(highestWord);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter feedback: ");
        String feedback = sc.nextLine();
        printFilteredWordFrequency(feedback);
        sc.close();
    }
}
