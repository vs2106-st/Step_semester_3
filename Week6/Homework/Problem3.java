class Problem3Demo {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket("STU1234", 1200, "AI/ML");
        w.pay(1200);
        w.applyLateFee(100);
        System.out.println("Balance due: " + w.getBalanceDue()); // Outputs 200.0

        double[] history = w.getLateFeeHistory();
        System.out.println("Recorded history: " + Arrays.toString(history)); // [200.0]

        // Modifying defensive copy does not mutate the original history
        if (history.length > 0) {
            history[0] = 999;
        }

        System.out.println("After modification attempt: " + Arrays.toString(w.getLateFeeHistory())); // [200.0]
    }
}
