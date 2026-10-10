public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException("Minimum penalty percent cannot be negative.");
        }
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Ticket fare and minutes late cannot be negative.");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double tieredPenalty = 0.0;

        int tier1Minutes = Math.min(minutesLate, 5);
        tieredPenalty += tier1Minutes * (0.005 * ticketFare);

        if (minutesLate > 5) {
            int tier2Minutes = Math.min(minutesLate - 5, 10);
            tieredPenalty += tier2Minutes * (0.01 * ticketFare);
        }

        if (minutesLate > 15) {
            int tier3Minutes = minutesLate - 15;
            tieredPenalty += tier3Minutes * (0.02 * ticketFare);
        }

        double flatFloorPenalty = ticketFare * (minimumPenaltyPercent / 100.0);

        return Math.max(tieredPenalty, flatFloorPenalty);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);

        System.out.println("ticketFare = 1000, minutesLate = 0 -> Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("ticketFare = 1000, minutesLate = 1 -> Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("ticketFare = 1000, minutesLate = 16 -> Rs " + calc.calculatePenalty(1000, 16));
    }
}
