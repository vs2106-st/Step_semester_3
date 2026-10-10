public interface Exportable {
    String exportData();
}

class ExportTracker {
    private static int totalExports = 0;

    public static synchronized void incrementExport() {
        totalExports++;
    }

    public static int getTotalExports() {
        return totalExports;
    }

    public static void exportAll(Exportable[] items) {
        if (items == null) return;
        for (Exportable item : items) {
            if (item != null) {
                System.out.println(item.exportData());
            }
        }
    }
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportTracker.incrementExport();
        return "Exported report: " + reportName;
    }

    public static int getTotalExports() {
        return ExportTracker.getTotalExports();
    }

    public static void exportAll(Exportable[] items) {
        ExportTracker.exportAll(items);
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportTracker.incrementExport();
        return "Exported profile: " + username;
    }
}
