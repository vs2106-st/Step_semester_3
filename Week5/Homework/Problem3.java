class PatientInventory {
    private double vitalsScore;

    public PatientInventory(double vitalsScore) {
        if (vitalsScore < 0.0 || vitalsScore > 100.0) {
            throw new IllegalArgumentException("Construction rejected: vitalsScore must be between 0.0 and 100.0.");
        }
        this.vitalsScore = vitalsScore;
    }

    public void updateVitals(double score) {
        if (score >= 0.0 && score <= 100.0) {
            this.vitalsScore = score;
        }
    }

    public double getVitalsScore() {
        return vitalsScore;
    }
}
