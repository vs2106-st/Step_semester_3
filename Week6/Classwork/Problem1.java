class EliteRunnerEntry extends RunnerEntry {
    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber, double entryFee, String category, double sponsorBonus) {
        super(bibNumber, entryFee, category);
        this.sponsorBonus = sponsorBonus;
    }

    public double getSponsorBonus() {
        return sponsorBonus;
    }

    @Override
    public String announce() {
        return "Elite Runner | Bib: " + getBibNumber() + " | Category: " + getCategory() + " | Sponsor Bonus: " + sponsorBonus + " | Balance: " + getBalanceDue();
    }
}

class RelayTeamEntry extends RaceEntry {
    private int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize + " | Balance: " + getBalanceDue();
    }
}

class RaceFinanceUtils {

    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry instanceof RunnerEntry) {
            return "Single-inheritance subclass";
        }
        return "Base race entry";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double totalBalance = 0.0;
        if (entries != null) {
            for (RaceEntry entry : entries) {
                if (entry != null) {
                    totalBalance += entry.getBalanceDue();
                }
            }
        }
        return totalBalance;
    }
}
