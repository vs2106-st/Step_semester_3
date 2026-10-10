import java.util.Arrays;

public final class IntakeSummary {
    private final String patientId;
    private final String[] wardCodes;

    public IntakeSummary(String patientId, String[] wardCodes) {
        if (wardCodes == null) {
            throw new IllegalArgumentException("Construction rejected: wardCodes array cannot be null.");
        }

        for (String code : wardCodes) {
            if (!isValidWardCode(code)) {
                throw new IllegalArgumentException("Construction rejected: Invalid ward code format.");
            }
        }

        this.patientId = patientId;
        this.wardCodes = Arrays.copyOf(wardCodes, wardCodes.length);
    }

    private static boolean isValidWardCode(String code) {
        if (code == null || code.length() != 5 || !code.startsWith("WD-")) {
            return false;
        }
        for (int i = 3; i < 5; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getWardCodes() {
        return Arrays.copyOf(wardCodes, wardCodes.length);
    }

    public IntakeSummary withCorrectedWardCode(int index, String newCode) {
        if (index < 0 || index >= wardCodes.length) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        String[] updatedCodes = getWardCodes();
        updatedCodes[index] = newCode;
        return new IntakeSummary(this.patientId, updatedCodes);
    }

    public static String processNightlyReconciliation(IntakeSummary[] summaries) {
        if (summaries == null) {
            return "0 processed | 0 null skipped | 0 emergency-only | 0 regular";
        }

        int processed = 0;
        int nullSkipped = 0;
        int emergencyOnly = 0;
        int regular = 0;

        for (IntakeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
            } else {
                processed++;
                if (summary instanceof EmergencyIntakeSummary) {
                    emergencyOnly++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + emergencyOnly + " emergency-only | " + regular + " regular";
    }
}

class EmergencyIntakeSummary extends IntakeSummary {
    private final String emergencyLevel;

    public EmergencyIntakeSummary(String patientId, String[] wardCodes, String emergencyLevel) {
        super(patientId, wardCodes);
        this.emergencyLevel = emergencyLevel;
    }

    public String getEmergencyLevel() {
        return emergencyLevel;
    }
}
