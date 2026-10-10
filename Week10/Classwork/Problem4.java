public class Problem4 {
    public static int lowerBound(int[] prices, int target) {
        int left = 0, right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static int upperBound(int[] prices, int target) {
        int left = 0, right = prices.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (prices[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int low = 150;
        int high = 300;

        int lowerIndex = lowerBound(prices, low);
        int upperIndex = upperBound(prices, high) - 1;
        int count = (upperIndex >= lowerIndex) ? (upperIndex - lowerIndex + 1) : 0;

        System.out.println("Lower bound index " + lowerIndex);
        System.out.println("Upper bound index " + upperIndex);
        System.out.println("Count " + count);
    }
}
