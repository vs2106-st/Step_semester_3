import java.util.*;

public class Problem3 {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Sci", "Eng"};
        
        Map<String, int[]> studentMarks = new LinkedHashMap<>();
        studentMarks.put("Anu", new int[]{80, 90, 70});
        studentMarks.put("Ravi", new int[]{60, 85});
        studentMarks.put("Meena", new int[]{95, 75, 88});

        List<String> totalsList = new ArrayList<>();
        for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
            int total = 0;
            for (int score : entry.getValue()) {
                total += score;
            }
            totalsList.add(entry.getKey() + " " + total);
        }
        System.out.println("Totals " + String.join(", ", totalsList));

        List<String> toppersList = new ArrayList<>();
        for (int i = 0; i < subjects.length; i++) {
            String topperName = "";
            int maxScore = -1;

            for (Map.Entry<String, int[]> entry : studentMarks.entrySet()) {
                int[] marks = entry.getValue();
                if (i < marks.length) {
                    if (marks[i] > maxScore) {
                        maxScore = marks[i];
                        topperName = entry.getKey();
                    }
                }
            }

            if (maxScore != -1) {
                toppersList.add(subjects[i] + " " + topperName + " " + maxScore);
            }
        }
        System.out.println("toppers " + String.join(", ", toppersList));
    }
}
