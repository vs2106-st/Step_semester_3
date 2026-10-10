import java.util.HashSet;

class BusTicket {
    private final String passengerName;
    private final String destination;
    private boolean checkedIn = false;

    public BusTicket(String passengerName, String destination) {
        if (!isValidName(passengerName) || !isValidDestination(destination)) {
            throw new IllegalArgumentException("Invalid passenger name or destination.");
        }
        this.passengerName = passengerName;
        this.destination = destination;
    }

    private static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        for (char c : name.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidDestination(String destination) {
        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }
        for (char c : destination.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public void markCheckedIn() {
        if (this.checkedIn) {
            throw new IllegalStateException("Ticket has already been checked in.");
        }
        this.checkedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        HashSet<String> acceptedBookings = new HashSet<>();

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            String name = booking[0];
            String dest = booking[1];

            try {
                BusTicket ticket = new BusTicket(name, dest);
                String key = name.trim().toLowerCase() + "|" + dest.trim().toLowerCase();

                if (acceptedBookings.contains(key)) {
                    duplicates++;
                } else {
                    acceptedBookings.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] rawBookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        processBatch(rawBookings);
    }
}
