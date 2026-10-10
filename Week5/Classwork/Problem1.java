import java.util.LinkedHashMap;
import java.util.Map;

class LibraryMember {
    private String membershipId;
    String branchCode; // default (package-private)
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
        if (membershipId == null || membershipId.trim().length() < 4) {
            throw new IllegalArgumentException("Construction rejected: Invalid membershipId.");
        }
        this.membershipId = membershipId.trim();
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
            case "protected":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        Map<String, int[]> counts = new LinkedHashMap<>();
        counts.put("private", new int[]{0, 0});
        counts.put("default", new int[]{0, 0});
        counts.put("protected", new int[]{0, 0});
        counts.put("public", new int[]{0, 0});

        for (String[] attempt : attempts) {
            if (attempt != null && attempt.length >= 2) {
                String modifier = attempt[0];
                String context = attempt[1];
                String result = classifyAccess(modifier, context);

                if (counts.containsKey(modifier)) {
                    if ("ALLOWED".equals(result)) {
                        counts.get(modifier)[0]++;
                    } else {
                        counts.get(modifier)[1]++;
                    }
                }
            }
        }

        StringBuilder summary = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, int[]> entry : counts.entrySet()) {
            if (i > 0) {
                summary.append(" | ");
            }
            summary.append(entry.getKey())
                   .append(": ")
                   .append(entry.getValue()[0])
                   .append(" allowed / ")
                   .append(entry.getValue()[1])
                   .append(" denied");
            i++;
        }

        return summary.toString();
    }
}
