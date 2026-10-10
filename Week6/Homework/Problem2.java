class PremiumWorkshopTicket extends WorkshopTicket {
    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId, double basePrice, String track, double kitFee) {
        super(attendeeId, basePrice, track);
        this.kitFee = kitFee;
    }

    public double getKitFee() {
        return kitFee;
    }

    @Override
    public double getBalanceDue() {
        return super.getBalanceDue() + kitFee;
    }

    @Override
    public String printTicket() {
        return "Premium Workshop Ticket | Track: " + getTrack() + " | Kit Fee: " + kitFee + " | Balance Due: " + getBalanceDue();
    }
}

class HackathonTicket extends EventTicket {
    private String teamName;

    public HackathonTicket(String attendeeId, double basePrice, String teamName) {
        super(attendeeId, basePrice);
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String printTicket() {
        return "Hackathon Ticket | Team: " + teamName + " | Balance Due: " + getBalanceDue();
    }
}

class TicketClassifier {

    public static String classifyGeneration(EventTicket ticket) {
        if (ticket instanceof PremiumWorkshopTicket) {
            return "Multilevel descendant (3 generations deep)";
        } else if (ticket instanceof HackathonTicket) {
            return "Hierarchical sibling (independent branch)";
        } else if (ticket instanceof WorkshopTicket) {
            return "Single-inheritance subclass";
        } else if (ticket instanceof EventTicket) {
            return "Base ticket";
        }
        return "Unknown ticket type";
    }

    public static double getTotalBalanceDue(EventTicket[] tickets) {
        double totalBalance = 0.0;
        if (tickets != null) {
            for (EventTicket ticket : tickets) {
                if (ticket != null) {
                    totalBalance += ticket.getBalanceDue();
                }
            }
        }
        return totalBalance;
    }
}
