class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    String fullProfile() {
        double pay = 0.0;
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else if (employee != null) {
            pay = employee.getSalary();
        }

        String slotText = (slot != null) ? slot.slotNo : "no parking assigned";
        return name + " | Pay: Rs " + pay + " | Slot: " + slotText;
    }

    public static void main(String[] args) {
        ParkingSlot slot1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot slot2 = new ParkingSlot("A2", 5, 4);

        Employee e1 = new ManagerEmployee(101, "Divya", 70000.0, 8000.0);
        Employee e2 = new Employee(102, "Karan", 40000.0);
        Employee e3 = new InternEmployee(103, "Meera", 12000.0, 10000.0);

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E101", e1, slot1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E102", e2, slot2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E103", e3, null);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
