public class Problem3{
    static void findLongestStreak(String signalLog) {
        int longestLength = 1;
        char longestChar = signalLog.charAt(0);
        int currentLength = 1;
        char currentChar = signalLog.charAt(0);
        for (int i = 1; i < signalLog.length(); i++) {
            char c = signalLog.charAt(i);
            if (c == currentChar) {
                currentLength++;
            } else {
                currentChar = c;
                currentLength = 1;
            }
            if (currentLength > longestLength) {
                longestLength = currentLength;
                longestChar = currentChar;
            }
        }
        System.out.println("Longest Streak: '" + longestChar + "' repeated " + longestLength + " times");
    }
    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}
