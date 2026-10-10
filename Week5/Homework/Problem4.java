public class PatientRecordBean {
    private String patientId;
    private String name;
    private boolean priorityPatient;
    private String internalNotes;

    public PatientRecordBean() {
        this(null, null);
    }

    public PatientRecordBean(String name) {
        this(null, name);
    }

    public PatientRecordBean(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
        this.priorityPatient = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (this.patientId == null) {
            this.patientId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPriorityPatient() {
        return priorityPatient;
    }

    public void setPriorityPatient(boolean priority) {
        this.priorityPatient = priority;
    }

    public void setInternalNotes(String notes) {
        this.internalNotes = notes;
    }
}
