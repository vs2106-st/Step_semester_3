import java.util.*;

public class Problem3 {
    public static int countPeriods(int[] transactions, long k) {
        Map<Long, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1);
        long currentSum = 0;
        int count = 0;

        for (int val : transactions) {
            currentSum += val;
            if (prefixCounts.containsKey(currentSum - k)) {
                count += prefixCounts.get(currentSum - k);
            }
            prefixCounts.put(currentSum, prefixCounts.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}
