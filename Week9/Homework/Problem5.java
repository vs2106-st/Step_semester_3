public class Problem5 {
    public static int maxSumSubarray(int[] sales, int k) {
        if (sales == null || sales.length < k || k <= 0) {
            return 0;
        }

        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += sales[i];
        }

        int maxSum = currentSum;

        for (int i = k; i < sales.length; i++) {
            currentSum += sales[i] - sales[i - k];
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        return maxSum;
    }
}
