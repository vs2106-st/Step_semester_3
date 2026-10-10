class RaceAnnouncer {

    public static String announceAll(RaceEntry[] entries) {
        if (entries == null || entries.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (RaceEntry entry : entries) {
            if (entry != null) {
                sb.append(entry.announce());

                if (entry instanceof RelayTeamEntry) {
                    RelayTeamEntry relay = (RelayTeamEntry) entry;
                    sb.append(" [Team size via downcast: ")
                      .append(relay.getTeamSize())
                      .append("]");
                }

                sb.append(" | ");
            }
        }

        return sb.toString();
    }
}
