public class Problem2{
    static void checkTypingAccuracy(String original, String typed) {
        int length = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        for (int i = 0; i < length; i++) {
            char o = original.charAt(i);
            char t = typed.charAt(i);
            if (o == t) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1;
            }
        }
        double accuracy = ((double) matched / length) * 100;
        StringBuilder result = new StringBuilder();
        result.append("Matched: ").append(matched).append("/").append(length);
        result.append(" | Accuracy: ").append(String.format("%.2f", accuracy)).append("%");
        if (firstMismatchPos == -1) {
            result.append(" | No Mismatches");
        } else {
            char origChar = original.charAt(firstMismatchPos - 1);
            char typedChar = typed.charAt(firstMismatchPos - 1);
            result.append(" | First Mismatch at position ").append(firstMismatchPos);
            result.append(" ('").append(origChar).append("' vs '").append(typedChar).append("')");
        }
        System.out.println(result);
    }
    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}
