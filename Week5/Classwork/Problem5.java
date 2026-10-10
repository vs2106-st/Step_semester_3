import java.util.Arrays;

public final class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null) {
            throw new IllegalArgumentException("Construction rejected: bookIds array cannot be null.");
        }

        for (String id : bookIds) {
            if (!isValidBookId(id)) {
                throw new IllegalArgumentException("Construction rejected: Invalid book ID format.");
            }
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    private static boolean isValidBookId(String id) {
        if (id == null || id.length() != 6 || !id.startsWith("BK-")) {
            return false;
        }
        for (int i = 3; i < 6; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        String[] newBookIds = getBookIds();
        newBookIds[index] = newId;
        return new LoanReceipt(this.memberId, newBookIds);
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        if (receipts == null) {
            return "0 processed | 0 null skipped | 0 reference-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (LoanReceipt receipt : receipts) {
            if (receipt == null) {
                nullSkipped++;
            } else {
                processed++;
                if (receipt instanceof ReferenceOnlyLoanReceipt) {
                    referenceOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
