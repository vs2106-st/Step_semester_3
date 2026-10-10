import java.util.*;

enum LeaveStatus {
    PENDING, APPROVED, REJECTED
}

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }
}

class LeaveRequest {
    private Employee employee;
    private String startDate;
    private String endDate;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, String startDate, String endDate) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
        System.out.println("Leave request submitted by " + employee.getName() + " for " + startDate + " to " + endDate + ". Status: " + status + ".");
    }

    public Employee getEmployee() { return employee; }
    public LeaveStatus getStatus() { return status; }

    public void approve() {
        if (status == LeaveStatus.PENDING) {
            status = LeaveStatus.APPROVED;
            System.out.println("Leave request for " + employee.getName() + " approved. Status: " + status + ".");
        } else {
            System.out.println("Cannot change status: Request is already " + status + ".");
        }
    }

    public void reject() {
        if (status == LeaveStatus.PENDING) {
            status = LeaveStatus.REJECTED;
            System.out.println("Leave request for " + employee.getName() + " rejected. Status: " + status + ".");
        } else {
            System.out.println("Cannot change status: Request is already " + status + ".");
        }
    }

    public void revertToPending() {
        if (status == LeaveStatus.APPROVED || status == LeaveStatus.REJECTED) {
            System.out.println("Cannot change status: " + status + " request cannot revert to Pending.");
        } else {
            status = LeaveStatus.PENDING;
        }
    }
}

public class Question4Main {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John Doe");
        Employee jane = new PartTimeEmployee("Jane Smith");

        LeaveRequest req1 = new LeaveRequest(john, "2024-10-10", "2024-10-12");
        req1.approve();

        LeaveRequest req2 = new LeaveRequest(jane, "2024-11-01", "2024-11-05");

        req1.revertToPending();
    }
}
