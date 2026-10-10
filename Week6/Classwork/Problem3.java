class Problem3TestRunner {
    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println("Balance due: " + r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println("History before edit: " + Arrays.toString(history));
        
        if (history.length > 0) {
            history[0] = 999;
        }
        
        System.out.println("History after edit: " + Arrays.toString(r.getLateFeeHistory()));
    }
}
