public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("Minimum surge percent cannot be negative.");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes cannot be negative.");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double tieredFee = 0.0;

        int tier1Minutes = Math.min(delayMinutes, 5);
        tieredFee += tier1Minutes * (0.005 * orderValue);

        if (delayMinutes > 5) {
            int tier2Minutes = Math.min(delayMinutes - 5, 10);
            tieredFee += tier2Minutes * (0.01 * orderValue);
        }

        if (delayMinutes > 15) {
            int tier3Minutes = delayMinutes - 15;
            tieredFee += tier3Minutes * (0.02 * orderValue);
        }

        double flatFloorFee = orderValue * (minimumSurgePercent / 100.0);

        return Math.max(tieredFee, flatFloorFee);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);

        System.out.println("orderValue = 500, delayMinutes = 0 -> Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("orderValue = 500, delayMinutes = 1 -> Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("orderValue = 500, delayMinutes = 16 -> Rs " + calc.calculateSurgeFee(500, 16));
    }
}
