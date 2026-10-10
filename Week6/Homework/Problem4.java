class FestAnnouncer {

    public static String announceAll(EventTicket[] tickets) {
        if (tickets == null || tickets.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (EventTicket ticket : tickets) {
            if (ticket != null) {
                sb.append(ticket.printTicket());

                if (ticket instanceof HackathonTicket) {
                    HackathonTicket hackathon = (HackathonTicket) ticket;
                    sb.append(" [Team via downcast: ")
                      .append(hackathon.getTeamName())
                      .append("]");
                }

                sb.append(" | ");
            }
        }

        return sb.toString();
    }
}
