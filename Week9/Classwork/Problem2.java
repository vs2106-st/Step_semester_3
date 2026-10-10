public class Problem2 {
    public static int[] longestStreak(int[] costs, long budget) {
        int maxLen = 0;
        int startIdx = -1;
        int left = 0;
        long currentSum = 0;

        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];

            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }

            if (currentSum <= budget) {
                int currentLen = right - left + 1;
                if (currentLen > maxLen) {
                    maxLen = currentLen;
                    startIdx = left;
                }
            }
        }

        if (maxLen == 0) {
            return new int[]{0, -1};
        }
        return new int[]{maxLen, startIdx};
    }
}
